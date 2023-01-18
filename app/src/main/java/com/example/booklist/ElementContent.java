package com.example.booklist;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ElementContent extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_content);
        Book book = (Book) getIntent().getSerializableExtra("BOOK");
        if (book != null) {
            TextView titleTextView = findViewById(R.id.title);
            TextView authorTextView = findViewById(R.id.author);
            TextView pagesTextView = findViewById(R.id.pages);
            TextView releasedTextView = findViewById(R.id.released);
            TextView publishingHouseTextView = findViewById(R.id.publishingHouse);
            TextView genreTextView = findViewById(R.id.genre);
            TextView ratingTextView = findViewById(R.id.rating);
            TextView numberOfRatingsTextView = findViewById(R.id.numberOfRatings);
            TextView translatorTextView = findViewById(R.id.translator);
            titleTextView.setText(book.getTitle());
            authorTextView.setText(book.getAuthor());
            pagesTextView.setText(String.valueOf(book.getPages()));
            releasedTextView.setText(book.getReleased());
            publishingHouseTextView.setText(book.getPublishingHouse());
            genreTextView.setText(book.getGenre());
            ratingTextView.setText(String.valueOf(book.getRating()));
            numberOfRatingsTextView.setText(String.valueOf(book.getNumberOfRatings()));
            translatorTextView.setText(book.getTranslator());

            ImageView imgView = (ImageView) findViewById(R.id.image);
            int drawableId = getResources().getIdentifier(book.getImage(), "drawable", getPackageName());
            @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = getResources().getDrawable(drawableId);
            imgView.setImageDrawable(drawable);

            ExpandableTextView expTv = (ExpandableTextView) findViewById(R.id.expand_text_view).findViewById(R.id.expand_text_view);

            expTv.setText("Tutaj kupisz \n" + book.getSite().getName() + "\n" + book.getSite().getPrice());
        }

    }

}

