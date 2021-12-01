package com.example.uniproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public  class MemoryDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String DATABASE_NAME = "Problem.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MemoryContract.MemoryEntry.TABLE_NAME + " (" +
                    MemoryContract.MemoryEntry._ID + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEP +
                    MemoryContract.MemoryEntry.COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP +
                    MemoryContract.MemoryEntry.COLUMN_TEL + TEXT_TYPE + COMMA_SEP +
                    MemoryContract.MemoryEntry.COLUMN_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    MemoryContract.MemoryEntry.COLUMN_IMAGE + TEXT_TYPE + " )";

    public MemoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + MemoryContract.MemoryEntry.TABLE_NAME);
            onCreate(db);
    }

    public Cursor readAllMemories() {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(
                MemoryContract.MemoryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    void addMemory(Memory memory) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MemoryContract.MemoryEntry.COLUMN_EMAIL, memory.getEmail());
        values.put(MemoryContract.MemoryEntry.COLUMN_TEL, memory.getTel());
        values.put(MemoryContract.MemoryEntry.COLUMN_DESCRIPTION, memory.getDescription());
        values.put(MemoryContract.MemoryEntry.COLUMN_IMAGE, memory.getImageAsString());

        db.insert(MemoryContract.MemoryEntry.TABLE_NAME, null, values);

        db.close();
    }

    void updateMemory (Memory memory){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MemoryContract.MemoryEntry.COLUMN_EMAIL, memory.getEmail());
        values.put(MemoryContract.MemoryEntry.COLUMN_TEL, memory.getTel());
        values.put(MemoryContract.MemoryEntry.COLUMN_DESCRIPTION, memory.getDescription());
        values.put(MemoryContract.MemoryEntry.COLUMN_IMAGE, memory.getImageAsString());

        db.update(MemoryContract.MemoryEntry.TABLE_NAME, values, MemoryContract.MemoryEntry._ID + "=?", new String[]{String.valueOf(memory.getId())});
        db.close();
    }

     void deleteMemory (Memory memory){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(MemoryContract.MemoryEntry.TABLE_NAME, MemoryContract.MemoryEntry._ID + "=?", new String[]{String.valueOf(memory.getId())});
        db.close();
    }
}
