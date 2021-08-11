package com.chanaka.alevel.ui.paperUploader;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chanaka.alevel.R;
import com.chanaka.alevel.ui.ViewPDF.ViewPDF;
import com.chanaka.alevel.ui.paper_list.timeTable_upload;


public class Admin_home extends Fragment {

    Button timetable;
    Button PaperUp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Admin Home");

        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_admin_home, container, false);
       timetable =(Button) view.findViewById(R.id.timeTable_id);
       PaperUp  =(Button) view.findViewById(R.id.paper_up);

       timetable.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Fragment someFragment = new timeTable_upload();

               FragmentTransaction transaction = getFragmentManager().beginTransaction();
               transaction.replace(R.id.nav_host_fragment, someFragment,"Admin_home" ); // give your fragment container id in first parameter
               transaction.addToBackStack("Admin_home");  // if written, this transaction will be added to backstack
               transaction.commit();

           }
       });

       PaperUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               Fragment someFragment = new paper_uploader();

               FragmentTransaction transaction = getFragmentManager().beginTransaction();
               transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
               transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
               transaction.commit();

           }
       });

       return view;
    }
}