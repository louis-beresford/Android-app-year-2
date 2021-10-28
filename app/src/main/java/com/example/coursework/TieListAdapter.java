package com.example.coursework;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TieListAdapter extends RecyclerView.Adapter<TieListAdapter.TieViewHolder> {

    private static final String TAG = TieListAdapter.class.getSimpleName();

    private ArrayList<Ties> ties;
    private Context context;

    public TieListAdapter(Context context, ArrayList<Ties> ties) {
        this.context = context;
        this.ties = ties;
    }

    @Override
    public TieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tie_list_item, viewGroup, false);
        TieViewHolder viewHolder = new TieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TieViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        //current tie instant
        Ties t = ties.get(position);

        //Bind tie to view
        holder.nameTxt.setText(t.getName());
        holder.img.setImageResource(t.getImage());
        holder.priceTxt.setText(t.getPrice());

        Log.d(TAG, "#" + position);

        //if a item is clicked start a new activity to see it
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + ties.get(position));
                Toast.makeText(context, ties.get(position).getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, TieActivity.class);

                intent.putExtra("tie_name", ties.get(position).getName());
                intent.putExtra("tie_price", ties.get(position).getPrice());
                intent.putExtra("tie_image", resourceToUri(context, ties.get(position)
                        .getImage()).toString());
                intent.putExtra("tie_desc", ties.get(position).getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ties.size();
    }

    class TieViewHolder extends RecyclerView.ViewHolder {

        TextView nameTxt;
        ImageView img;
        TextView priceTxt;

        public TieViewHolder(View itemView) {
            super(itemView);

            //set view from xml
            nameTxt = itemView.findViewById(R.id.productDesc);
            img = itemView.findViewById(R.id.imageView);
            priceTxt = itemView.findViewById(R.id.textPrice);
        }

    }

    public static Uri resourceToUri(Context context, int resID) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                context.getResources().getResourcePackageName(resID) + '/' +
                context.getResources().getResourceTypeName(resID) + '/' +
                context.getResources().getResourceEntryName(resID));
    }
}
