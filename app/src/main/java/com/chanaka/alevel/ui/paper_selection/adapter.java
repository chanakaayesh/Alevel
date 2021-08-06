package com.chanaka.alevel.ui.paper_selection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.chanaka.alevel.DB_Model.BookModel;
import com.chanaka.alevel.DB_Model.getkey_model;
import com.chanaka.alevel.DB_Model.section_model;
import com.chanaka.alevel.R;
import com.chanaka.alevel.ui.paper_list.paper_list;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.viewHolders> {

    Context context;
    List<getkey_model> list;
   subject_selection fragment1;
   FragmentManager fragmentManager;

    public adapter(Context context, List<getkey_model> list,FragmentManager fragmentManager) {
        this.context = context;
        this.list = list;
        this.fragmentManager = fragmentManager;

    }

    @NonNull
    @Override
    public viewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.sub_selection_resource,parent,false);
        return new viewHolders(v);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolders holder, int position) {
      getkey_model bookModel =list.get(position);
      section_model sub_model = new section_model();
        holder.select_sub.setText(bookModel.getSubky());
        holder.select_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub_model.setCoresubject(holder.select_sub.getText().toString());
                subject_selection subject_selections= new subject_selection();

                Fragment someFragment = new paper_list();

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

    public  class viewHolders extends  RecyclerView.ViewHolder{
        Button select_sub;
        public viewHolders(@NonNull View itemView) {
            super(itemView);
            select_sub =(Button) itemView.findViewById(R.id.select_sub);
        }
    }
}