package com.chanaka.alevel.ui.paperUploader;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import android.widget.Toast;

import com.chanaka.alevel.DB_Model.BookModel;
import com.chanaka.alevel.R;
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

public class paper_uploader extends Fragment {
    public Button insert_book;
    private ImageView imageView;
    private EditText subject,year;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference, mDatabaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;
    BookModel bookModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View root = inflater.inflate(R.layout.fragment_paper_uploader, container, false);


        insert_book=(Button) root.findViewById(R.id.book_insert);
        subject=(EditText)root.findViewById(R.id.paper_subject);
        year=(EditText)root.findViewById(R.id.paper_year);

        imageView=(ImageView)root.findViewById(R.id.pdf);
        Spinner spinner = (Spinner) root.findViewById(R.id.spinner);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Paper");
        readData();

        //  spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        String [] values =
                {"Bio","Maths","Commaz","Art"};
        // Creating adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        storageReference = FirebaseStorage.getInstance().getReference("PaperPDF");
        databaseReference = FirebaseDatabase.getInstance().getReference("Paper");

        insert_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(FilePathUri != null && subject.getText().toString().length() !=0
                         && year.getText().toString().length() !=0) {
                    final StorageReference storageReference1 =storageReference.child(System.currentTimeMillis() + "."+GetFileExtension(FilePathUri));
                    storageReference1.putFile(FilePathUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getActivity().getApplicationContext(),"Image Uploaded SuccessFully",Toast.LENGTH_LONG).show();
                            storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String section = spinner.getSelectedItem().toString() ;
                                    String key = databaseReference.push().getKey();
                                    bookModel =new BookModel();
                                    bookModel.setId(year.getText().toString());
                                    bookModel.setSubject(subject.getText().toString());
                                    bookModel.setYear(Integer.parseInt(year.getText().toString()));
                                    bookModel.setSection(section);
                                    bookModel.setBooktImageUrk(uri.toString());
                                    databaseReference.child(section).child(subject.getText().toString()).child(year.getText().toString()).setValue(bookModel);
                                    Toast.makeText(getActivity().getApplicationContext(),"Success fully added",Toast.LENGTH_LONG).show();
                                    subject.setText("");
                                    year.setText("");



                                    System.out.println("it is working");


                                }
                            });

                        }
                    });

                }

                else{
                    System.out.println("it is not working");
                    subject.setError("!");
                    year.setError("!");


                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("application/pdf");
                // intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);

            }
        });

      return root;
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Image_Request_Code && resultCode == RESULT_OK && data !=null && data.getData() !=null){

            FilePathUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),FilePathUri);
                imageView.setImageBitmap(bitmap);

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public String GetFileExtension(Uri uri){

        ContentResolver contentResolver = getActivity().getContentResolver() /*getContentResolver()*/;
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void readData(){
        if(mDatabaseReference !=null){
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!snapshot.exists()){}
                    else {
                        System.out.println("Hey This is our value");
                        for (DataSnapshot postdata:snapshot.getChildren()){

                            System.out.println(postdata.getKey().toString());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}