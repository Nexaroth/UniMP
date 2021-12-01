package com.example.uniproject;

import android.provider.BaseColumns;

public class MemoryContract {

    private MemoryContract() {}

    public static final class MemoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "Problem";
        public static final String COLUMN_EMAIL = "Email";
        public static final String COLUMN_TEL = "Tel";
        public static final String COLUMN_DESCRIPTION = "Description";
        public static final String COLUMN_IMAGE = "Image";

    }
}
