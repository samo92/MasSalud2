package ib.facmed.unam.mx.massalud2.api;

import ib.facmed.unam.mx.massalud2.models.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by samo92 on 21/11/2017.
 */

public interface PostApiService {

    /*@GET("wp-json/wp/v2/posts/")
    Call<> getPost();*/

    @GET("wp-json/wp/v2/posts/{id}")
    Call<Post> getPostById(@Path(value="id")String id);

}
