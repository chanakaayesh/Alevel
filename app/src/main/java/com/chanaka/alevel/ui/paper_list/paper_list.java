package com.chanaka.alevel.ui.paper_list;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chanaka.alevel.DB_Model.BookModel;
import com.chanaka.alevel.DB_Model.getkey_model;
import com.chanaka.alevel.DB_Model.paper_year;
import com.chanaka.alevel.DB_Model.section_model;
import com.chanaka.alevel.R;
import com.chanaka.alevel.ui.paper_selection.adapter;
import com.chanaka.alevel.ui.paper_selection.subject_selection;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class paper_list extends Fragment {


    section_model submodel;

    RecyclerView recycler;
    paper_list_adapter adapter_;
    List<paper_year> pdflist;
    paper_year keys_;
    DatabaseReference mDatabase;
    //public  static getkey_model pdfModel;
    section_model models;
    ProgressDialog progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Papers");
        // Inflate the layout for this fragment
       View view=  inflater.inflate(R.layout.fragment_paper_list, container, false);
       progressBar =new ProgressDialog(getActivity());
       submodel =new section_model();



        recycler=(RecyclerView) view.findViewById(R.id.paper_year_recyler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        pdflist =  new ArrayList<>();
       models = new section_model();

        mDatabase = FirebaseDatabase.getInstance().getReference("Paper").child(models.getName()).child(models.getCoresubject());



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
                        keys_   =postsnapshot.getValue(paper_year.class);
                        keys_.setPaper_year(postsnapshot.getKey());
                        pdflist.add(keys_);

                    }

                       paper_list fragment =new paper_list();
                        FragmentManager fragmentManager = getFragmentManager();
                        adapter_ = new paper_list_adapter(getContext(),pdflist,fragmentManager);
                        recycler.setAdapter(adapter_);}

                    progressBar.dismiss();


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}