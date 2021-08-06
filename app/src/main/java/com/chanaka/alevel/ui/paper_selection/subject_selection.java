package com.chanaka.alevel.ui.paper_selection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.chanaka.alevel.DB_Model.BookModel;
import com.chanaka.alevel.DB_Model.getkey_model;
import com.chanaka.alevel.DB_Model.section_model;
import com.chanaka.alevel.R;
import com.chanaka.alevel.ui.paper_list.paper_list;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class subject_selection extends Fragment {

    RecyclerView sub_selcecter_recycler;
    adapter adapter_;
    List<getkey_model> pdflist;
    getkey_model keys_;
    DatabaseReference mDatabase;
   /* public  static getkey_model pdfModel;*/



    section_model models;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subject_selection, container, false);

        sub_selcecter_recycler=(RecyclerView) view.findViewById(R.id.sub_selection_recycler);


        sub_selcecter_recycler.setHasFixedSize(true);
        sub_selcecter_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        pdflist =  new ArrayList<>();
        models = new section_model();

        mDatabase = FirebaseDatabase.getInstance().getReference("Paper").child(models.getName());




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//mDatabaseReference
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
                        keys_   =postsnapshot.getValue(getkey_model.class);
                        keys_.setSubky(postsnapshot.getKey());
                        pdflist.add(keys_);
                    }

                        subject_selection fragment =new subject_selection();
                        FragmentManager fragmentManager = getFragmentManager();
                        adapter_ = new adapter(getContext(),pdflist,fragmentManager);
                        sub_selcecter_recycler.setAdapter(adapter_);}


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void openAnotherpage(FragmentTransaction transaction){
        Fragment someFragment = new paper_list();
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }
}