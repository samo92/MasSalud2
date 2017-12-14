package ib.facmed.unam.mx.massalud2.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ib.facmed.unam.mx.massalud2.R;
import ib.facmed.unam.mx.massalud2.models.PostRendered;
import ib.facmed.unam.mx.massalud2.ui.WebViewActivity;

/**
 * Created by samo92 on 29/11/2017.
 */

public class PostAdapterRecyclerView
        extends RecyclerView.Adapter<PostAdapterRecyclerView.PostViewHolder>{

    private ArrayList<PostRendered> postRenderedArray;
    private int resource;
    private Activity activity;

    public PostAdapterRecyclerView(ArrayList<PostRendered> postRenderedArray, int resource, Activity activity){
        this.postRenderedArray = postRenderedArray;
        this.resource=resource;
        this.activity=activity;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        PostRendered postRendered = postRenderedArray.get(position);
        holder.postName.setText(postRendered.getTitulo());
        holder.postCategoria.setText(postRendered.getCategoria());
        Glide.with(activity)
                .load(postRendered.getFoto())
                .centerCrop()
                .into(holder.postImage);

    }

    @Override
    public int getItemCount() {
        if(postRenderedArray.size() != 0)
            return postRenderedArray.size();
        return 0;
    }

    public void updateAdapter (ArrayList<PostRendered> items){
        postRenderedArray.clear();
        postRenderedArray=items;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        /**
         * Declaramos las variables que contiene nuestro CARDVIEW
         */
        private TextView postName;
        private TextView postCategoria;
        private ImageView postImage;

        public PostViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            postName = (TextView) itemView.findViewById(R.id.textView_titlenew);
            postCategoria = (TextView) itemView.findViewById(R.id.textView_categorynew);
            postImage = (ImageView) itemView.findViewById(R.id.post_imageView);

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(activity, WebViewActivity.class);
            intent.putExtra("url",postRenderedArray.get(getAdapterPosition()).getArticulo());
            Log.e("url: ", String.valueOf(getAdapterPosition()));
            Log.e("url: ", String.valueOf(postRenderedArray.get(getAdapterPosition()).getArticulo()));
            activity.startActivity(intent);
        }
    }
}



