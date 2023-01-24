package com.example.booklist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklist.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
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
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try  {
                        String json;
                        String fileData;
                        JSONArray jsonArray = null;
                        if (isNetworkAvailable()) {
                            String requestURL = "https://my-json-server.typicode.com/rkuczynski1531/jsonServer/books/";
                            json = IOUtils.toString(new URL(requestURL), StandardCharsets.UTF_8);
                            jsonArray = new JSONArray(json);

                            String cacheFileName = "cachedBooks.json";
                            File file = new File(getCacheDir(), cacheFileName);
                            writeDataToFile(file, String.valueOf(jsonArray));
                        }
                        File cacheFileDir = new File(getCacheDir(), "cachedBooks.json");
                        FileInputStream fileInputStream = new FileInputStream(cacheFileDir);
                        fileData = readDataFromFile(fileInputStream);
                        if (!isNetworkAvailable()) {
                            jsonArray = new JSONArray(fileData);
                        }
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
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
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            thread.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        HelperAdapter helperAdapter = new HelperAdapter(books, MainActivity.this, this);
        recyclerView.setAdapter(helperAdapter);
    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void writeDataToFile(File file, String data) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            this.writeDataToFile(fileOutputStream, data);
            fileOutputStream.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void writeDataToFile(FileOutputStream fileOutputStream, String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String readDataFromFile(FileInputStream fileInputStream) {
        StringBuffer retBuf = new StringBuffer();
        try {
            if (fileInputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String lineData = bufferedReader.readLine();
                while (lineData != null) {
                    retBuf.append(lineData);
                    lineData = bufferedReader.readLine();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return retBuf.toString();
        }
    }

    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, ElementContent.class);

        intent.putExtra("BOOK", books.get(position));

        startActivity(intent);
    }
}