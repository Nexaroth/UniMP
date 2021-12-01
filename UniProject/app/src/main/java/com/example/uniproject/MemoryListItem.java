package com.example.uniproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MemoryListItem extends AppCompatActivity {

    protected TextView listItemTextViewEmail, listItemTextViewTel, listItemTextViewDescription;
    protected ImageView listItemImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_list_item);

        listItemImageView = findViewById(R.id.listItemImageView);

        listItemTextViewEmail = findViewById(R.id.listItemTextViewEmail);
        listItemTextViewEmail.setMovementMethod(new ScrollingMovementMethod());

        listItemTextViewTel = findViewById(R.id.listItemTextViewTel);
        listItemTextViewTel.setMovementMethod(new ScrollingMovementMethod());

        listItemTextViewDescription = findViewById(R.id.listItemTextViewDescription);
        listItemTextViewDescription.setMovementMethod(new ScrollingMovementMethod());
    }
}
