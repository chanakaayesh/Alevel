package com.chanaka.alevel.ui.paper_list.paper_type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.chanaka.alevel.DB_Model.paper_type_model;
import com.chanaka.alevel.DB_Model.paper_year;
import com.chanaka.alevel.DB_Model.section_model;
import com.chanaka.alevel.R;
import com.chanaka.alevel.ui.ViewPDF.ViewPDF;
import com.chanaka.alevel.ui.paper_list.paper_list_adapter;

import java.util.List;

public class paper_type_adapter extends RecyclerView.Adapter<paper_type_adapter.papertype_viewHolder> {

    Context context;
    List<paper_type_model> list;
    FragmentManager fragmentManager;

    public paper_type_adapter(Context context, List<paper_type_model> list, FragmentManager fragmentManager) {
        this.context = context;
        this.list = list;
        this.fragmentManager = fragmentManager;
    }



    @NonNull
    @Override
    public papertype_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.paper_list_resource,parent,false);
        return new  papertype_viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull papertype_viewHolder holder, int position) {
      paper_type_model papertype =list.get(position);
        section_model sub_model = new section_model();
        holder.paper_type_b.setText(papertype.getPaper_type());
        holder.paper_type_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub_model.setPaper_type(holder.paper_type_b.getText().toString());


                Fragment someFragment = new ViewPDF();

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class papertype_viewHolder extends RecyclerView.ViewHolder{
        Button paper_type_b;
        public papertype_viewHolder(@NonNull View itemView) {
            super(itemView);
            paper_type_b=(Button) itemView.findViewById(R.id.paper_list_year_b);
        }
    }
}
