package com.chanaka.alevel.ui.ViewPDF;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chanaka.alevel.DB_Model.BookModel;
import com.chanaka.alevel.DB_Model.pdf_model;
import com.chanaka.alevel.DB_Model.section_model;
import com.chanaka.alevel.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewPDF extends Fragment {

   // RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;

    com.github.barteksc.pdfviewer.PDFView pdfView;
    DatabaseReference mRef;
    section_model models;
    static  BookModel bookModel;
    pdf_model PDFmodel;
    ProgressDialog progressDialog;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_p_d, container, false);
        progressDialog = new ProgressDialog(getActivity());
        progressBar =(ProgressBar) view.findViewById(R.id.progresbar);
/*        mRecyclerView=(RecyclerView) view.findViewById(R.id.pdf_recycler);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));*/
        pdfView= (PDFView) view.findViewById(R.id.pdfview);
       // mRef = FirebaseDatabase.getInstance().getReference("Paper").child(models.getName()).child(models.getCoresubject()).child(String.valueOf(models.getYear())).child("booktImageUrk");
        mRef = FirebaseDatabase.getInstance().getReference("Paper").child(models.getName()).child(models.getCoresubject()).child(String.valueOf(models.getYear())).child(models.getPaper_type());

        System.out.println("hey ayesh :"+models.getName());
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(getActivity(), "Loading", Toast.LENGTH_LONG).show();
                progressDialog.setTitle("loading PDF");
                progressDialog.show();
       /*         String value = snapshot.getValue(String.class);
                //   text1.setText(value);
                System.out.println("hey ayesh url is :"+value);
                Toast.makeText(getContext(), "Loading", Toast.LENGTH_LONG).show();
                String url = value.toString();
                new RetrivePdfStream().execute(url);*/

                if(!snapshot.exists())
                {}
                else{


                    bookModel   =snapshot.getValue(BookModel.class);
                    System.out.println("hey ayesh urls is "+bookModel.getBooktImageUrk());

                    System.out.println("hey ayesh urls is "+bookModel.getBooktImageUrk());
                    new RetrivePdfStream().execute(bookModel.getBooktImageUrk());



            }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        return view;
    }

/*    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<BookModel,ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<BookModel, ViewHolder>(
                BookModel.class,
                R.layout.row_pdf,
                ViewHolder.class,
                mRef
        ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, BookModel bookModel, int i) {
                viewHolder.setDetails(getActivity().getApplicationContext(),bookModel.getBooktImageUrk());
            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }*/


    class RetrivePdfStream extends AsyncTask<String, Void, InputStream> {


        @Override
        protected InputStream doInBackground(String... strings) {


            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {

                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }


            } catch (IOException e) {
                return null;
            }


            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {

            progressDialog.setTitle("loading PDF");
            progressDialog.show();
            //pdfView.fromStream(inputStream).load();
            pdfView.fromStream(inputStream).load();
            progressDialog.dismiss();
        }
    }
}