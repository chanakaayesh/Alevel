package com.chanaka.alevel.ui.adminLogin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chanaka.alevel.MainActivity;
import com.chanaka.alevel.R;
import com.chanaka.alevel.ui.paperUploader.Admin_home;
import com.chanaka.alevel.ui.paper_selection.subject_selection;


public class Admin_login extends Fragment {

    Button login;
    EditText username;
    EditText password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Admin Login");
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_admin_login, container, false);

       login=(Button) view.findViewById(R.id.login_button);
       username=(EditText) view.findViewById(R.id.user_name);
       password=(EditText) view.findViewById(R.id.user_pw);


       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               if(username.getText().toString() !=null && password.getText().toString() !=null){

                   if(username.getText().toString().equals("ayesh") && password.getText().toString().equals("bird00998877"))
                   {
                       Fragment someFragment = new Admin_home();
                       FragmentTransaction transaction = getFragmentManager().beginTransaction();
                       transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
                       transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                       transaction.commit();
                   }else{

                      if(!username.getText().toString().equals("ayesh")){
                          username.setError("!");
                      }
                      if(!password.getText().toString().equals("bird00998877")){
                           password.setError("!");

                      }                   }

               }else{

                       if(username.getText().toString() !=null){
                             username.setError("enter user name");
                       }
                       if(password.getText().toString() !=null){
                             password.setError("enter user name");
                         }
               }


           }
       });
        return view;
    }
}