package com.example.alarm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarm.R;
import com.example.alarm.model.InformationAlarm;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    //  //alarm info data gulo rakhboi bole  array ready korlam


    ArrayList<InformationAlarm>medicineinfo = new ArrayList<>();
    Context context;



    //create Context  Constractor for context

    public MyAdapter(ArrayList<InformationAlarm> medicineinfo, Context context) {
        this.medicineinfo = medicineinfo;
        this.context = context;
    }



    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layview = LayoutInflater.from(context).inflate(R.layout.infomodel,parent,false);
        MyViewHolder holder = new MyViewHolder(layview);



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        int pos =position +1;
        InformationAlarm ml = medicineinfo.get(position);
        holder.tv_number.setText(String.valueOf(pos));
        holder.tv_name.setText(ml.getmName());
        holder.tv_when.setText(ml.getMwhen());
        holder.tv_time.setText(ml.getMtime());
        holder.imageView_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {


                int newPosition = holder.getAdapterPosition();
                medicineinfo.remove(newPosition);
                notifyItemRemoved(newPosition);
                notifyItemRangeChanged(newPosition,medicineinfo.size());



                notifyDataSetChanged();



            }
        });




    }

    @Override
    public int getItemCount() {
        return medicineinfo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


      TextView  tv_number,tv_name,tv_when,tv_time;
      ImageView imageView_delete;



        public MyViewHolder(@NonNull View itemView) {


            super(itemView);
            tv_number = itemView.findViewById(R.id.counttv_id);
            tv_name = itemView.findViewById(R.id.medinamenametv_id);
            tv_when = itemView.findViewById(R.id.whentv_id);
            tv_time = itemView.findViewById(R.id.timetv_id);
            imageView_delete = itemView.findViewById(R.id.deleteid);










        }
    }
}
