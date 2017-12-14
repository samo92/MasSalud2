package ib.facmed.unam.mx.massalud2;


import android.text.Html;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ib.facmed.unam.mx.massalud2.api.ApiService;
import ib.facmed.unam.mx.massalud2.api.PostApiService;
import ib.facmed.unam.mx.massalud2.models.Post;
import ib.facmed.unam.mx.massalud2.models.PostRendered;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by samo92 on 06/12/2017.
 */

public class CategoryPostApi  {

    String data;

    //Variable para retrofit
    private PostApiService postApiService;
    private ArrayList<PostRendered> arrayUpdate;

    public ArrayList<PostRendered> loadJSON(String idPost){

        postApiService = ApiService.createApiService();
        Call<Post> responsePost = postApiService.getPostById(idPost);

        responsePost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post = response.body();
                data=post.getContent().getRendered();
                arrayUpdate=limpiarJsonPost(data);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

        return arrayUpdate;
    }

    private ArrayList<PostRendered> limpiarJsonPost(String data){
        String libre = String.valueOf(Html.fromHtml(data.toString()));
        libre = libre.replace('“', '"');
        libre = libre.replace('”', '"');
        libre = libre.replace('″', '"');
        Gson gson = new Gson();
        Type tipoListaPost = new TypeToken<ArrayList<PostRendered>>(){}.getType();
        arrayUpdate = gson.fromJson(libre, tipoListaPost);
        Log.e("RESPUESTA: ", libre);
        return arrayUpdate;
        }
}
