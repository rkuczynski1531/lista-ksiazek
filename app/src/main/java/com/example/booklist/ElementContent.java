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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ElementContent extends AppCompatActivity {

    ImageView imgView;
    @SuppressLint("SetTextI18n")
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
            pagesTextView.setText("Strony: " + book.getPages());
            releasedTextView.setText("Data wydania: " + book.getReleased());
            publishingHouseTextView.setText("Wydawnictwo: " + book.getPublishingHouse());
            genreTextView.setText("Gatunek: " + book.getGenre());
            ratingTextView.setText("Ocena: " + book.getRating());
            numberOfRatingsTextView.setText("Liczba ocen: " + book.getNumberOfRatings());
            translatorTextView.setText("Tłumacz: " + book.getTranslator());

            imgView = (ImageView) findViewById(R.id.image);
            loadImage(book.getImage());
            ExpandableTextView expTv = (ExpandableTextView) findViewById(R.id.expandTextView1).findViewById(R.id.expand_text_view);

            expTv.setText("Tutaj kupisz \n" + book.getSite().getName() + "\n" + book.getSite().getPrice());

            ExpandableTextView expTv2 = (ExpandableTextView) findViewById(R.id.expandTextView2).findViewById(R.id.expand_text_view);

            expTv2.setText("Podobne książki \n" + book.getSimilarBook().getTitle() + "\n" + book.getSimilarBook().getAuthor());
        }

    }

    private void loadImage(String url) {
        Picasso.get()
                .load(url)
                .into(imgView);

    }

}

