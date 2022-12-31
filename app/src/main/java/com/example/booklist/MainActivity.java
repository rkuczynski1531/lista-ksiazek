package com.example.booklist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklist.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    RecyclerView recyclerView;
    ArrayList<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset());
            JSONArray jsonArray = jsonObject.getJSONArray("books");
            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println(i);
                JSONObject bookData = jsonArray.getJSONObject(i);
                JSONObject siteJson = bookData.getJSONObject("site");
                Site site = new Site(
                        siteJson.getString("name"),
                        siteJson.getDouble("price")
                );

                JSONObject similarBookJson = bookData.getJSONObject("similarBook");
                SimilarBook similarBook = new SimilarBook(
                        similarBookJson.getString("title"),
                        similarBookJson.getString("author")
                );

                books.add(new Book(
                        bookData.getInt("id"),
                        bookData.getString("title"),
                        bookData.getString("author"),
                        bookData.getInt("pages"),
                        bookData.getString("released"),
                        bookData.getString("publishingHouse"),
                        bookData.getString("genre"),
                        bookData.getDouble("rating"),
                        bookData.getInt("numberOfRatings"),
                        bookData.getString("translator"),
                        site,
                        similarBook,
                        bookData.getString("image")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HelperAdapter helperAdapter = new HelperAdapter(books, MainActivity.this, this);
        recyclerView.setAdapter(helperAdapter);
    }

    private String JsonDataFromAsset() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("books.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, ElementContent.class);

        intent.putExtra("BOOK", books.get(position));

        startActivity(intent);
    }
}