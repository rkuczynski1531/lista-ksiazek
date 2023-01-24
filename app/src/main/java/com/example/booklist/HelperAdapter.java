package com.example.booklist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ThemedSpinnerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter<HelperAdapter.MyViewClass> {
    private final RecyclerViewInterface recyclerViewInterface;

    ArrayList<Book> books;
    Context context;

    public HelperAdapter(ArrayList<Book> books, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
        MyViewClass myViewClass = new MyViewClass(view, recyclerViewInterface);
        return myViewClass;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewClass myViewClass, int i) {
        myViewClass.title.setText(books.get(i).getTitle());
        myViewClass.author.setText(books.get(i).getAuthor());
        Picasso.get().load(books.get(i).getImage()).into(myViewClass.image);
        System.out.println(i);
    }

    @Override
    public int getItemCount() {
        System.out.println(books.size());
        return books.size();
    }

    public class MyViewClass extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;
        ImageView image;

        public MyViewClass(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (recyclerViewInterface != null) {
                        int pos = getAbsoluteAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
