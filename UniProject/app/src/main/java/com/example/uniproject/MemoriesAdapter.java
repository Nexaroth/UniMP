package com.example.uniproject;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MemoriesAdapter extends CursorAdapter {
    public MemoriesAdapter(Context context, Cursor cursor, boolean autoRequery) {
        super(context, cursor, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.memory_list_item, viewGroup, false);
        view.setTag(new ViewHolder(view));
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder)view.getTag();

        Memory memory = new Memory(cursor);

        holder.listItemTextViewEmail.setText(memory.getEmail());
        holder.listItemTextViewTel.setText(memory.getTel());
        holder.listItemTextViewDescription.setText(memory.getDescription());
        holder.listItemImageView.setImageBitmap(memory.getImage());
    }

    private class ViewHolder {
        final ImageView listItemImageView;
        final TextView listItemTextViewDescription, listItemTextViewTel,listItemTextViewEmail;
        final Button btnUpdateList;

        ViewHolder(View view) {
            btnUpdateList = view.findViewById(R.id.btnUpdateList);
            listItemImageView = view.findViewById(R.id.listItemImageView);
            listItemTextViewDescription = view.findViewById(R.id.listItemTextViewDescription);
            listItemTextViewTel = view.findViewById(R.id.listItemTextViewTel);
            listItemTextViewEmail = view.findViewById(R.id.listItemTextViewEmail);


        }
    }
}
