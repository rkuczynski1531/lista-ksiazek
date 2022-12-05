package com.example.booklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ThemedSpinnerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter<HelperAdapter.MyViewClass> {
    private final RecyclerViewInterface recyclerViewInterface;

    ArrayList<String> title;
    ArrayList<String> author;
    Context context;

    public HelperAdapter(ArrayList<String> title, ArrayList<String> author, Context context,
                         RecyclerViewInterface recyclerViewInterface) {
        this.title = title;
        this.author = author;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
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
        myViewClass.title.setText(title.get(i));
        myViewClass.author.setText(author.get(i));
        System.out.println(i);
//        myViewClass.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Item Clicked", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        System.out.println(title.size());
        return title.size();
    }

    public class MyViewClass extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;

        public MyViewClass(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            author = (TextView) itemView.findViewById(R.id.author);
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
