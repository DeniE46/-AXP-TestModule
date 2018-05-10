package com.denie.axp.launcherDock;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denie.axp.R;

import java.util.ArrayList;


public class LauncherRecyclerViewAdapter extends RecyclerView.Adapter<LauncherRecyclerViewAdapter.LauncherViewHolder>{

    private ArrayList<LauncherModel> arrayList;
    Context mContext;
    public interface AdapterCallback{
        void onItemClicked(int position);
    }
    LauncherRecyclerViewAdapter.AdapterCallback mAdapterCallback;


    public LauncherRecyclerViewAdapter(Context context, ArrayList<LauncherModel> newList, AdapterCallback adapterCallback1) {
        this.arrayList = newList;
        mContext = context;
        mAdapterCallback = adapterCallback1;
    }

    @Override
    public LauncherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.launcher_column, parent, false);
        return new LauncherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LauncherViewHolder holder, int position) {
        LauncherModel launcherModel = arrayList.get(position);
        holder.name.setText(launcherModel.getName());
        holder.icon.setImageDrawable(launcherModel.getIcon());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class LauncherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageView icon;
        public LauncherViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_launcher);
            icon = itemView.findViewById(R.id.icon_launcher);
           itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            //startLauncher(arrayList.get(position).getPackageName());
            Toast.makeText(mContext, arrayList.get(position).getPackageName(), Toast.LENGTH_SHORT).show();
            if(mAdapterCallback != null){
                mAdapterCallback.onItemClicked(position);
            }
        }

        void startLauncher(String appPackage){

        }


        public boolean IsPackageInstalled(String packageName, PackageManager packageManager) {
            try {
                packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
                return true;
            } catch (PackageManager.NameNotFoundException e) {
                return false;
            }
        }
    }
}
