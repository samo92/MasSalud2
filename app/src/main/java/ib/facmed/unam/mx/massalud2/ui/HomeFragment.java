package ib.facmed.unam.mx.massalud2.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ib.facmed.unam.mx.massalud2.Adapters.PostAdapterRecyclerView;
import ib.facmed.unam.mx.massalud2.Adapters.SlidingImageAdapter;
import ib.facmed.unam.mx.massalud2.CategoryPostApi;
import ib.facmed.unam.mx.massalud2.MainActivity;
import ib.facmed.unam.mx.massalud2.R;
import ib.facmed.unam.mx.massalud2.api.ApiService;
import ib.facmed.unam.mx.massalud2.api.PostApiService;
import ib.facmed.unam.mx.massalud2.models.CategoryDetails;
import ib.facmed.unam.mx.massalud2.models.Post;
import ib.facmed.unam.mx.massalud2.models.PostRendered;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by samo92 on 04/12/2017.
 */

public class HomeFragment extends Fragment {

    ViewPager mViewPager;
    CircleIndicator indicator;

    SlidingImageAdapter slidingImageAdapter;

    PostAdapterRecyclerView postAdapter;

    //Variable para retrofit
    private PostApiService postApiService;

    public HomeFragment() {
        //Se requiere un constructor vacio
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflamos el fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        initViews(view);


        return view;
    }

    private void initViews(View view) {

        indicator = (CircleIndicator) view.findViewById(R.id.viewpager_indicator);

        //Instanciamos nuestro ViewPager
        slidingImageAdapter = new SlidingImageAdapter(getContext(),new ArrayList<PostRendered>(0));

        mViewPager = (ViewPager) view.findViewById(R.id.postViewPager);
        mViewPager.setAdapter(slidingImageAdapter);

        //Instanciamos nuestro RecyclerView
        RecyclerView postRecycler = (RecyclerView) view.findViewById(R.id.postRecycler);

        //Creamos un MANAGER para nuestro recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //Asociamos nuestro RecyclerView
        postRecycler.setLayoutManager(linearLayoutManager);

        //Instanciamos nuestro adaptador
        postAdapter =
                new PostAdapterRecyclerView(new ArrayList<PostRendered>(0), R.layout.cardview_main, getActivity());
        postRecycler.setAdapter(postAdapter);

        loadJSON();

    }

    private void loadJSON(){
        postApiService = ApiService.createApiService();
        Call<Post> responsePost = postApiService.getPostById("4");

        responsePost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post = response.body();
                String data=post.getContent().getRendered();
                limpiarJsonPost(data);
                //Log.e("RESPUESTA: ", post.getContent().getRendered() );
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void limpiarJsonPost(String data){
        String libre = String.valueOf(Html.fromHtml(data.toString()));
        libre = libre.replace('“', '"');
        libre = libre.replace('”', '"');
        libre = libre.replace('″', '"');
        Gson gson = new Gson();
        Type tipoListaPost = new TypeToken<ArrayList<PostRendered>>(){}.getType();
        ArrayList<PostRendered> postsRendered = gson.fromJson(libre, tipoListaPost);

        Log.e("RESPUESTA: ", libre);

        ArrayList<PostRendered> viewPagerImages = new ArrayList<PostRendered>();
        for(int i=0; i<4; i++){
            viewPagerImages.add(i,postsRendered.get(i));
            postsRendered.remove(i);
        }

        slidingImageAdapter.updateAdapter(viewPagerImages);
        postAdapter.updateAdapter(postsRendered);
        //indicator.setViewPager(mViewPager);
    }
}
