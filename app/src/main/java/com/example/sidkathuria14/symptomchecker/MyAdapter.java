package com.example.sidkathuria14.symptomchecker;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sidkathuria14.symptomchecker.models.Main_Object;

import java.util.ArrayList;

/**
 * Created by sidkathuria14 on 25/3/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

   ArrayList<Main_Object> arrayList;
   Context context;

    public MyAdapter(ArrayList<Main_Object> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View itemView =li.inflate(R.layout.item_view,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    Main_Object thisObject = arrayList.get(position);
        holder.tvName.setText(thisObject.getIssue().getName());
        holder.profName.setText(thisObject.getIssue().getProfName());
        holder.icdName.setText(thisObject.getIssue().getIcdName());
        holder.specialistId.setText(thisObject.getSpecialisation().get(0).getName());
        holder.Accuracy.setText("accuracy: " + String.valueOf(thisObject.getIssue().getAccuracy()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

       TextView tvName,Accuracy,icdName,specialistId,profName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            Accuracy = (TextView)itemView.findViewById(R.id.accuracy);
            specialistId = (TextView)itemView.findViewById(R.id.specialistId);
            icdName = (TextView)itemView.findViewById(R.id.icdName);
            profName  = (TextView)itemView.findViewById(R.id.profName);
        }
    }
}
