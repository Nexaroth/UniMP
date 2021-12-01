package com.example.uniproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class ReportProblem extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 200;
    protected EditText editEmail, editTel, editDescription;
    protected Button  btnBackReport;
    protected ImageView imageViewPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_problem);

        imageViewPhoto = findViewById(R.id.imageViewPhoto);
        editEmail = findViewById(R.id.editEmail);
        editTel = findViewById(R.id.editTel);
        editDescription = findViewById(R.id.editDescription);
        btnBackReport = findViewById(R.id.btnBackReport);

        btnBackReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBackIntent = new Intent(ReportProblem.this, MainActivity.class);
                startActivity(goBackIntent);
            }
        });
    }

    public void openGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
    }

    public void openCamera(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //     if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
//        }
//        else{
//            Toast.makeText(getApplicationContext(), "No camera found", Toast.LENGTH_LONG).show();
//        }
    }

    public void save(View view) {
        Bitmap image = ((BitmapDrawable)imageViewPhoto.getDrawable()).getBitmap();
        new MemoryDbHelper(this).addMemory(new Memory(editEmail.getText().toString(),
                                                             editTel.getText().toString(),
                                                             editDescription.getText().toString(),
                                                             image));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            try {
                Uri selectedImage = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                imageViewPhoto.setImageBitmap(BitmapFactory.decodeStream(imageStream));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewPhoto.setImageBitmap(imageBitmap);
        }
    }
}

