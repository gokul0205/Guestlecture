package com.example.guestlec;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomRecyleradapter extends RecyclerView.Adapter<CustomRecyleradapter.ViewHolder> {

private static RecyclerView.RecyclerListener itemlistener;
    public Context context;
    private List<Lectures> lectures;
public int listposition;
    public CustomRecyleradapter(Context context, List string){
        this.context=context;
        this.lectures=string;
    }



    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

       return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
holder.itemView.setTag(lectures.get(position));
Lectures list=lectures.get(position);
   holder.professor.setText(list.getProfessor());
        holder.date.setText(list.getdate()+" "+list.gettime());
        holder.lecture.setText(list.getLecture());

        holder.imageView.setImageResource(R.drawable.guest);
this.listposition=position;

    }

    @Override
    public int getItemCount() {
        return lectures.size();
    }



public void listitemclick(){



}

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView professor;
        public TextView date;
        public TextView lecture;
        public ImageView imageView;
        public ViewHolder(final View itemView) {
            super(itemView);
            professor=(TextView)itemView.findViewById(R.id.Professor);
            date=(TextView)itemView.findViewById(R.id.Date);
            lecture=(TextView)itemView.findViewById(R.id.Subject);
            imageView=itemView.findViewById(R.id.imageView);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=getAdapterPosition();
                    Lectures list=lectures.get(pos);

                    Intent b =new Intent(view.getContext(),Lecturedetails.class);

                    b.putExtra("professor",list.getProfessor());
                    b.putExtra("date",list.getdate());
                    b.putExtra("time",list.gettime());
                    b.putExtra("venue",list.getVenue());
                    b.putExtra("topic",list.getLecture());
                    view.getContext().startActivity(b);
                }
            });

        }

        @Override
        public void onClick(View v) {
            int pos=getAdapterPosition();

        }
    }
}
