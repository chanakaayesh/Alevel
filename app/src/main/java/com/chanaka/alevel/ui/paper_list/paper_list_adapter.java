package com.chanaka.alevel.ui.paper_list;

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

import com.chanaka.alevel.DB_Model.BookModel;
import com.chanaka.alevel.DB_Model.paper_year;
import com.chanaka.alevel.DB_Model.section_model;
import com.chanaka.alevel.R;
import com.chanaka.alevel.ViewPDF;
import com.chanaka.alevel.ui.paper_selection.adapter;
import com.chanaka.alevel.ui.paper_selection.subject_selection;

import java.util.List;

public class paper_list_adapter extends RecyclerView.Adapter<paper_list_adapter.paperlist_viewHolder> {

    Context context;
    List<paper_year> list;
    FragmentManager fragmentManager;

    public paper_list_adapter(Context context, List<paper_year> list, FragmentManager fragmentManager) {
        this.context = context;
        this.list = list;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public paperlist_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.paper_list_resource,parent,false);
        return new paperlist_viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull paperlist_viewHolder holder, int position) {
        paper_year year =list.get(position);
        section_model sub_model = new section_model();
        holder.year_b.setText(year.getPaper_year());
        holder.year_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub_model.setYear(Integer.parseInt(holder.year_b.getText().toString()));


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

    public  class paperlist_viewHolder extends RecyclerView.ViewHolder{
        Button year_b;
        public paperlist_viewHolder(@NonNull View itemView) {
            super(itemView);
            year_b=(Button) itemView.findViewById(R.id.paper_list_year_b);
        }
    }
}
