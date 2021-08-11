package com.chanaka.alevel.ui.paper_list.paper_type;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chanaka.alevel.DB_Model.getkey_model;
import com.chanaka.alevel.DB_Model.paper_type_model;
import com.chanaka.alevel.DB_Model.paper_year;
import com.chanaka.alevel.DB_Model.section_model;
import com.chanaka.alevel.R;
import com.chanaka.alevel.ui.paper_list.paper_list;
import com.chanaka.alevel.ui.paper_list.paper_list_adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class paper_type extends Fragment {

    section_model submodel;

    RecyclerView recycler;
    paper_type_adapter adapter_;
    List< paper_type_model> pdflist;
    paper_type_model type_model;
    DatabaseReference mDatabase;

    section_model models;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_paper_type, container, false);
        submodel =new section_model();
        recycler=(RecyclerView) view.findViewById(R.id.paper_type_recyler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        pdflist =  new ArrayList<>();
        models = new section_model();
        mDatabase = FirebaseDatabase.getInstance().getReference("Paper").child(models.getName()).child(models.getCoresubject()).child(String.valueOf(models.getYear()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(mDatabase !=null){
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

           /*         if(!snapshot.exists()){}
                    else {
                        System.out.println("Hey This is our value");
                        for (DataSnapshot postdata:snapshot.getChildren()){
                            String keyss =postdata.getKey().toString();
                            keys_.setSubky(keyss); System.out.println("Hey This is our value1");
                            pdflist.add(keys_);
                        }

                        adapter_ = new adapter(getContext(),pdflist);
                        sub_selcecter_recycler.setAdapter(adapter_);
                    }*/

                    if(!snapshot.exists())
                    {}
                    else{   for (DataSnapshot postsnapshot:snapshot.getChildren()){
                        type_model   =postsnapshot.getValue(paper_type_model.class);
                        type_model.setPaper_type(postsnapshot.getKey());
                        pdflist.add(type_model);
                    }

                       paper_type fragment =new paper_type();
                        FragmentManager fragmentManager = getFragmentManager();
                        adapter_ = new paper_type_adapter(getContext(),pdflist,fragmentManager);
                        recycler.setAdapter(adapter_);}


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}