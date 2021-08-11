package com.chanaka.alevel.ui.paper_list;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.chanaka.alevel.DB_Model.TimeTable_model;
import com.chanaka.alevel.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class timeTable_upload extends Fragment {
    ImageView timetableImage;
    EditText time_subject;
    Button submit;

    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    TimeTable_model model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("TimeTable Upload");
       View view= inflater.inflate(R.layout.fragment_time_table_upload, container, false);
       time_subject=(EditText) view.findViewById(R.id.timetable_name);
       timetableImage=(ImageView) view.findViewById(R.id.timetable_image);
       submit=(Button) view.findViewById(R.id.up_timetable);
        storageReference = FirebaseStorage.getInstance().getReference("timetable");
        databaseReference = FirebaseDatabase.getInstance().getReference("timeTableDetails");


       timetableImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent();
               intent.setType("image/*");
               intent.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);
           }
       });

       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(FilePathUri != null &&  time_subject.getText().toString().length() !=0 ) {
                   final StorageReference storageReference1 =storageReference.child(System.currentTimeMillis() + "."+GetFileExtension(FilePathUri));
                   storageReference1.putFile(FilePathUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           Toast.makeText(getContext(),"Image Uploaded SuccessFully",Toast.LENGTH_LONG).show();
                           storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                               @Override
                               public void onSuccess(Uri uri) {
                                   String key = databaseReference.push().getKey();
                                   model =new TimeTable_model();

                                   model.setId(key);
                                  model.setSubject(time_subject.getText().toString());
                                   model.setUrl(uri.toString());

                                   databaseReference.push().setValue(model);
                                   Toast.makeText(getContext(),"Success fully added",Toast.LENGTH_LONG).show();
                                  time_subject.setText("");



                               }
                           });

                       }
                   });

               }

               else{

                  time_subject.setError("!");

               }
           }
       });

       return  view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Image_Request_Code && resultCode == RESULT_OK && data !=null && data.getData() !=null){

            FilePathUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),FilePathUri);
                timetableImage.setImageBitmap(bitmap);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String GetFileExtension(Uri uri){

        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

}