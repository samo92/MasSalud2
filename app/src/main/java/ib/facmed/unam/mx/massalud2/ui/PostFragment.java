package ib.facmed.unam.mx.massalud2.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ib.facmed.unam.mx.massalud2.Adapters.CategoryAdapterStaggered;
import ib.facmed.unam.mx.massalud2.Adapters.PostAdapterRecyclerView;
import ib.facmed.unam.mx.massalud2.CategoryPostApi;
import ib.facmed.unam.mx.massalud2.R;
import ib.facmed.unam.mx.massalud2.api.ApiService;
import ib.facmed.unam.mx.massalud2.api.PostApiService;
import ib.facmed.unam.mx.massalud2.models.CategoryDetails;
import ib.facmed.unam.mx.massalud2.models.Post;
import ib.facmed.unam.mx.massalud2.models.PostRendered;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by samo92 on 06/12/2017.
 */

public class PostFragment extends Fragment {

    private PostAdapterRecyclerView postAdapter;

    private String postId;

    //Variable para retrofit
    private PostApiService postApiService;

    public void PostFragment(){
        //Empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recyclerview,container,false);
        postId=getArguments().getString("id");

        initViews(view);



        return view;
    }

    private void initViews(View view){

        CategoryPostApi categoryPostApi = new CategoryPostApi();
        //categoryPost=categoryPostApi.loadJSON(id);

        //Instanciamos nuestro RecyclerView
        RecyclerView postRecycler = (RecyclerView) view.findViewById(R.id.recyclerview_category);

        //Creamos un MANAGER para nuestro recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //Asociamos nuestro RecyclerView
        postRecycler.setLayoutManager(linearLayoutManager);

        //Instanciamos nuestro adaptador
        postAdapter =
                new PostAdapterRecyclerView(new ArrayList<PostRendered>(0), R.layout.cardview_main, getActivity());
        postRecycler.setAdapter(postAdapter);

        //Log.

        loadJSON(postId);

    }

    public void loadJSON(String idPost){
        postApiService = ApiService.createApiService();
        Call<Post> responsePost = postApiService.getPostById(idPost);

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
        ArrayList<PostRendered> array= gson.fromJson(libre, tipoListaPost);

        postAdapter.updateAdapter(array);

        Log.e("RESPUESTA: ", postId);
    }
}
