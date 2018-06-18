package ib.facmed.unam.mx.massalud2.Adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

import ib.facmed.unam.mx.massalud2.CategoryPostApi;
import ib.facmed.unam.mx.massalud2.R;
import ib.facmed.unam.mx.massalud2.models.CategoryDetails;
import ib.facmed.unam.mx.massalud2.ui.PostFragment;

/**
 * Created by samo92 on 30/11/2017.
 */

public class CategoryAdapterStaggered
        extends RecyclerView.Adapter<CategoryAdapterStaggered.CategoryViewHolder> {

    //VARIABLES
    private ArrayList<CategoryDetails> categoriaArray;
    private int resource;
    private Activity activity;

    private Random mRandom = new Random();

    //CONSTRUCTOR
    public CategoryAdapterStaggered(ArrayList<CategoryDetails> categoriaArray, int resource, Activity activity) {
        this.categoriaArray = categoriaArray;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapterStaggered.CategoryViewHolder holder, int position) {
        CategoryDetails cat = categoriaArray.get(position);
        holder.textoCategoria.setText(cat.getName());
        holder.imagenCategoria.getLayoutParams().height= getRandomIntInRange(200,150);
        Glide.with(activity)
                .load(cat.getImage())
                .centerCrop()
                .into(holder.imagenCategoria);
    }

    private int getRandomIntInRange(int max, int min) {
        return mRandom.nextInt((max-min)+min)+min;
    }

    @Override
    public int getItemCount() {
        if(categoriaArray.size() != 0)
            return categoriaArray.size();
        return 0;
    }

    public void updateAdapter (ArrayList items) {
        categoriaArray.clear();
        categoriaArray = items;
        notifyDataSetChanged();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //Declaramos los item del cardview
        private ImageView imagenCategoria;
        private TextView textoCategoria;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            //Instanciamos los item del cardview
            imagenCategoria = (ImageView) itemView.findViewById(R.id.pic_categoria);
            textoCategoria = (TextView) itemView.findViewById(R.id.textview_categoria);
        }

        @Override
        public void onClick(View view) {

            /*Bundle bundle = new Bundle();
            Log.e("RESPUESTA: ", categoriaArray.get(getAdapterPosition()).getId());
            bundle.putString("id",categoriaArray.get(getAdapterPosition()).getId());
            AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
            PostFragment postFragment = new PostFragment();
            postFragment.setArguments(bundle);
            appCompatActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, postFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null).commit();*/
        }
    }
}
