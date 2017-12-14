package ib.facmed.unam.mx.massalud2.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import ib.facmed.unam.mx.massalud2.R;
import ib.facmed.unam.mx.massalud2.api.ApiService;
import ib.facmed.unam.mx.massalud2.models.CategoryDetails;
import ib.facmed.unam.mx.massalud2.models.Post;
import ib.facmed.unam.mx.massalud2.models.PostRendered;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by samo92 on 04/12/2017.
 */

public class DashboardFragment extends Fragment {

    String categoryImage[]={"http://ib.facmed.unam.mx/img/fondos/logosmall3.png"};
    String categoryName[]={"+UNAM","+Innovacion","+Historia",
            "+Descubrir","+Actualidad","+Opinion","+Global","+Vistazos"};
    String categoryId[]={"23","21","19","17","15","13","10","8"};

    private CategoryAdapterStaggered categoryAdapter;
    private ArrayList<CategoryDetails> categories;

    public void DashboardFragment(){
        //Empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recyclerview,container,false);
        initViews(view);

        return view;
    }

    private void initViews(View view){

        categories = getCategories();

        //Instanciamos nuestro RecyclerView
        RecyclerView postRecycler = (RecyclerView) view.findViewById(R.id.recyclerview_category);

        //Creamos un MANAGER para nuestro recyclerview
        StaggeredGridLayoutManager staggeredLayoutManager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //Asociamos nuestro RecyclerView
        postRecycler.setLayoutManager(staggeredLayoutManager);

        //Instanciamos nuestro adaptador
        categoryAdapter =
                new CategoryAdapterStaggered(categories, R.layout.cardview_staggeredlayout, getActivity());
        postRecycler.setAdapter(categoryAdapter);

    }

    /**
     * Este metodo debe de ser removido cuando se tengan los "id" de todas
     * las categorias
     * @return
     */
    private ArrayList<CategoryDetails> getCategories() {
        ArrayList<CategoryDetails> details = new ArrayList<>();
        for (int index=0; index<categoryName.length;index++){
            details.add(new CategoryDetails(categoryName[index],categoryImage[0],categoryId[index]));
        }
        return details;
    }

    /**Adecuar metodo para cargar las categorias
     *
     */
    /*private void loadJSON(){
        postApiService = ApiService.createApiService();
        Call<Post> responsePost = postApiService.getPostById("68");

        responsePost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post = response.body();
                String data=post.getContent().getRendered();
                limpiarJsonPost(data);
                Log.e("RESPUESTA: ", post.getContent().getRendered() );
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }*/

    /**Adecuar metodo para cargar las categorias
     *
     */
    /*private void limpiarJsonPost(String data){
        String libre = String.valueOf(Html.fromHtml(data.toString()));
        libre = libre.replace('“', '"');
        libre = libre.replace('”', '"');
        libre = libre.replace('″', '"');
        Gson gson = new Gson();
        Type tipoListaPost = new TypeToken<ArrayList<PostRendered>>(){}.getType();
        ArrayList<PostRendered> postsRendered = gson.fromJson(libre, tipoListaPost);


        postAdapter.updateAdapter(postsRendered);
    }*/
}
