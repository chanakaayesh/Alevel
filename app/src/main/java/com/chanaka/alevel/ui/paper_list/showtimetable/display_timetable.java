package com.chanaka.alevel.ui.paper_list.showtimetable;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chanaka.alevel.DB_Model.TimeTable_model;
import com.chanaka.alevel.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class display_timetable extends Fragment {
    RecyclerView recyclerView;
    private List<TimeTable_model> booklist;
    private DatabaseReference mDatabaseReference,removeborrowbook;
    public  static TimeTable_model bookModel;
    timeTable_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Exam TimeTable");
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_display_timetable, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.timetable_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        booklist = new ArrayList<>();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("timeTableDetails");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(mDatabaseReference !=null){

            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!snapshot.exists())
                    {}
                    else{   for (DataSnapshot postsnapshot:snapshot.getChildren()){

                        bookModel   =postsnapshot.getValue(TimeTable_model.class);
                        booklist.add(bookModel);

                        System.out.println("ayesh url is :"+ bookModel.getUrl());
                    }

                        adapter = new timeTable_adapter(getContext(),booklist);
                        recyclerView.setAdapter(adapter);}

                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}