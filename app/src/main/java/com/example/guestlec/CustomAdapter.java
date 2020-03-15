package com.example.guestlec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList personNames;
    ArrayList personImages;
    ArrayList personcomments;
    Context context;
    public CustomAdapter(Context context, ArrayList personNames, ArrayList personImages,ArrayList personcomments) {
        this.context = context;
        this.personNames = personNames;
        this.personImages = personImages;
        this.personcomments=personcomments;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        final int index=position;
        holder.name.setText(personNames.get(position).toString());
//       holder.image.setImageResource((Integer)personImages.get(position));


        Glide
                .with(context).load(personImages.get(position))
                .override(450, 400)
                .centerCrop()
                .into(holder.image);

       holder.comments.setText(personcomments.get(position).toString());
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
            }
        });
    }


    @Override
    public int getItemCount() {
        return personNames.size();
    }
     class MyViewHolder extends RecyclerView.ViewHolder  {
        // init the item view's
        TextView name;
         ImageView image;
         TextView comments;
         MyViewHolder(final View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);
            comments=itemView.findViewById(R.id.comments);
        }
    }
}