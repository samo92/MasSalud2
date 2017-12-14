package ib.facmed.unam.mx.massalud2.Adapters;



import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Map;

import ib.facmed.unam.mx.massalud2.R;
import ib.facmed.unam.mx.massalud2.models.PostRendered;
import ib.facmed.unam.mx.massalud2.ui.WebViewActivity;

/**
 * Created by samo92 on 06/12/2017.
 */

public class SlidingImageAdapter extends PagerAdapter {

    ArrayList<PostRendered> mResources;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public SlidingImageAdapter(Context context, ArrayList<PostRendered> mResources) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.viewpager_imagepost, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.viewpager_image);
        Glide.with(mContext)
                .load(mResources.get(position).getFoto())
                .centerCrop()
                .into(imageView);

        TextView textView = (TextView) itemView.findViewById(R.id.viewpager_textview);
        textView.setText(mResources.get(position).getTitulo());

        TextView textViewCategoria = (TextView) itemView.findViewById(R.id.viewpager_textview_categoria);
        textViewCategoria.setText(mResources.get(position).getCategoria());

        container.addView(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("url",mResources.get(position).getArticulo());
                Log.e("viewpager url: ",mResources.get(position).getArticulo());
                mContext.startActivity(intent);
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }

    public void updateAdapter (ArrayList<PostRendered> items){
        mResources.clear();
        mResources=items;
        notifyDataSetChanged();
    }
}
