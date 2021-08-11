package com.chanaka.alevel.ui.home;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.chanaka.alevel.DB_Model.BookModel;

import com.chanaka.alevel.DB_Model.section_model;
import com.chanaka.alevel.R;
import com.chanaka.alevel.ui.paper_selection.subject_selection;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    ImageView maths_b;
    ImageView bio_b;
    ImageView commers_b;
    ImageView art_b;
    TextView maths_text;
    TextView biotext;
    TextView commerstext;
    TextView arttext;
    section_model smodel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_home, container, false);
        maths_b =(ImageView) root.findViewById(R.id.maths_b);
        bio_b =(ImageView) root.findViewById(R.id.bio_b);
        commers_b  =(ImageView) root.findViewById(R.id.commers_b);
        art_b  =(ImageView) root.findViewById(R.id.art_b);
        smodel = new section_model();
        maths_text =(TextView) root.findViewById(R.id.t1);
        biotext=(TextView) root.findViewById(R.id.t2);
        commerstext=(TextView) root.findViewById(R.id.t3);
        arttext=(TextView) root.findViewById(R.id.t4);

        maths_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextpage("Maths");
            }
        });

        biotext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextpage("Bio");
            }
        });
        commerstext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextpage("Commaz");
            }
        });
        arttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextpage("Art");
            }
        });


        maths_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  smodel.setName("Maths");*/

                nextpage("Maths");
         /*       Fragment someFragment = new subject_selection();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
                nextpage("Maths");*/
            }
        });

        bio_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  smodel.setName("Bio");
*/
         /*       Fragment someFragment = new subject_selection();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
                nextpage("Maths");*/

                nextpage("Bio");
            }
        });

      commers_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*smodel.setName("Commaz");*/

         /*       Fragment someFragment = new subject_selection();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
                nextpage("Maths");*/

                nextpage("Commaz");
            }
        });


        art_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    smodel.setName("Art");*/

         /*       Fragment someFragment = new subject_selection();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
                nextpage("Maths");*/

                nextpage("Art");
            }
        });

        return root;
    }


    public void nextpage(String sub_section){

        smodel.setName(sub_section);

        Fragment someFragment = new subject_selection();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();

    }


    public  void  Onclick(View v,String sub_section){



    }

    public  void maths(View v){

    }

}