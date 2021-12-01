package com.example.uniproject;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Memory {
    private static final float PREFERRED_WIDTH = 250;
    private static final float PREFERRED_HEIGHT = 250;
    private Integer id;
    private String email;
    private String tel;
    private String description;
    private String image;

    public static final int COL_ID = 0;
    public static final int COL_EMAIL = 1;
    public static final int COL_TEL = 2;
    public static final int COL_DESCRIPTION = 3;
    public static final int COL_IMAGE = 4;

    public Memory(Cursor cursor) {
        this.id = cursor.getInt(COL_ID);
        this.email = cursor.getString(COL_EMAIL);
        this.tel = cursor.getString(COL_TEL);
        this.description = cursor.getString(COL_DESCRIPTION);
        this.image = cursor.getString(COL_IMAGE);
    }

    public Memory(String email, String tel, String description, Bitmap image) {
        this.email = email;
        this.tel = tel;
        this.description = description;
        this.image = bitmapToString(resizeBitmap(image));
    }

    public Integer getId() { return this.id; }
    public void setId(int id){ this.id=id; }

    public String getEmail() { return this.email; }
    public void setEmail(String email){ this.email=email; }

    public String getTel() {
        return this.tel;
    }
    public void setTel(String tel){ this.tel=tel; }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description){ this.description=description; }

    public Bitmap getImage() {
        return stringToBitmap(this.image);
    }
    public void setImage(String image){ this.image=image; }

    public String getImageAsString() {
        return this.image;
    }

    private static String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    private static Bitmap stringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static Bitmap resizeBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = PREFERRED_WIDTH / width;
        float scaleHeight = PREFERRED_HEIGHT / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bitmap, 0, 0, width, height, matrix, false);
        bitmap.recycle();
        return resizedBitmap;
    }
}
