package com.chanaka.alevel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chanaka.alevel.DB_Model.section_model;

public class ViewPDF extends Fragment {

    section_model model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_p_d, container, false);

        System.out.println("Hey ayesh  :"+model.getName()+"="+model.getCoresubject()+"="+model.getYear());
        return view;
    }
}