package com.example.booklist;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ElementContent extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_content);

        String title = getIntent().getStringExtra("TITLE");
        String author = getIntent().getStringExtra("AUTHOR");

        TextView titleTextView = findViewById(R.id.title);
        TextView authorTextView = findViewById(R.id.author);

        titleTextView.setText(title);
        authorTextView.setText(author);
    }
}
