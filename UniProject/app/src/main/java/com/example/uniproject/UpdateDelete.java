package com.example.uniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class UpdateDelete extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 200;
    protected EditText editEmailUpdate, editTelUpdate, editDescriptionUpdate;
    protected ImageView imageViewPhotoUpdate;
    protected Button btnDelete, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        imageViewPhotoUpdate = findViewById(R.id.imageViewPhotoUpdate);
        editEmailUpdate = findViewById(R.id.editEmailUpdate);
        editTelUpdate = findViewById(R.id.editTelUpdate);
        editDescriptionUpdate = findViewById(R.id.editDescriptionUpdate);
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

    public void update(View view) {
        Bitmap image = ((BitmapDrawable)imageViewPhotoUpdate.getDrawable()).getBitmap();
        new MemoryDbHelper(this).updateMemory(new Memory(editEmailUpdate.getText().toString(),
                editTelUpdate.getText().toString(),
                editDescriptionUpdate.getText().toString(),
                image));
        finish();
    }

    public void delete(View view) {
        Bitmap image = ((BitmapDrawable)imageViewPhotoUpdate.getDrawable()).getBitmap();
        new MemoryDbHelper(this).deleteMemory(new Memory(editEmailUpdate.getText().toString(),
                editTelUpdate.getText().toString(),
                editDescriptionUpdate.getText().toString(),
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
                imageViewPhotoUpdate.setImageBitmap(BitmapFactory.decodeStream(imageStream));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewPhotoUpdate.setImageBitmap(imageBitmap);
        }
    }
}