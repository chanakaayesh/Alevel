package com.chanaka.alevel.ui.paper_list.showtimetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chanaka.alevel.DB_Model.TimeTable_model;
import com.chanaka.alevel.DB_Model.paper_year;
import com.chanaka.alevel.R;
import com.chanaka.alevel.ui.paper_list.paper_list_adapter;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class timeTable_adapter extends RecyclerView.Adapter<timeTable_adapter.time_viewholde> {

    Context context;
    List<TimeTable_model> list;


    public timeTable_adapter(Context context, List<TimeTable_model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public time_viewholde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.tametable_resource,parent,false);
        return new time_viewholde(v);
    }

    @Override
    public void onBindViewHolder(@NonNull time_viewholde holder, int position) {
        TimeTable_model model =list.get(position);
        holder.table_subject.setText(model.getSubject());

        Picasso.with(context).load(model.getUrl())
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class time_viewholde extends RecyclerView.ViewHolder{

        TextView table_subject;
        ImageView imageView;
        public time_viewholde(@NonNull View itemView) {
            super(itemView);
            table_subject=(TextView) itemView.findViewById(R.id.timetable_subject);
            imageView=(ImageView) itemView.findViewById(R.id.timrtable_image);
        }
    }
}
