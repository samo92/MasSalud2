package ib.facmed.unam.mx.massalud2.api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by samo92 on 21/11/2017.
 */

public class ApiService {

    public static PostApiService createApiService(){

        Retrofit retrofitBuilder = new Retrofit.Builder()
                .baseUrl("http://ib.facmed.unam.mx/massalud/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofitBuilder.create(PostApiService.class);

    }
}
