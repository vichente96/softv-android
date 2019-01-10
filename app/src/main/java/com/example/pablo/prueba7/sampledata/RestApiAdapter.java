package com.example.pablo.prueba7.sampledata;

import java.io.IOException;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.pablo.prueba7.User.Login;

public class RestApiAdapter {

    public String abc="Basic: "+Login.enco;
    public Service getClientService() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        dispatcher.setMaxRequestsPerHost(1);
            OkHttpClient client = new OkHttpClient.Builder()
                        .dispatcher(dispatcher)
                        .addInterceptor(new Interceptor() {

                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {

                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", abc)
                            .build();


                    return chain.proceed(newRequest);
                }
            }).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.ROOT_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(Service.class);
        }



}