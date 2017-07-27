package com.sgen.nonggam.api;



import com.sgen.nonggam.data.AppWidgetData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SeRan on 2016-07-03.
 */
public class CallApi {

    private Retrofit mRetrofit;

    public static CallApi mInstance;

    public static CallApi getInstance(){
        if(mInstance==null){
            mInstance=new CallApi();
        }
        return  mInstance;
    }

    private CallApi() {
        init();
    }

    private void init(){
         mRetrofit = new Retrofit.Builder()
                .baseUrl("your_url")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public  void requestAppWidgetData(final NetworkResult onNetworkResult){
        AppWidgetService widgetService = mRetrofit.create(AppWidgetService.class);


        double lat = 0;
        double lng = 0;

        /*
         위치정보 받아오기
         */


        Call<AppWidgetData> repos=  widgetService.requestAppWidgetData(lat, lng);
        repos.enqueue(new Callback<AppWidgetData>() {

            @Override
            public void onResponse(Call<AppWidgetData> call, Response<AppWidgetData> response) {
                if(response!=null&&response.body()!=null){

                    onNetworkResult.onSuccess(response.body());

                }else{
                    onNetworkResult.onFail();
                }
            }

            @Override
            public void onFailure(Call<AppWidgetData> call, Throwable t) {
                onNetworkResult.onFail();
            }
        });
    }


    public interface  NetworkResult{
         void onSuccess(AppWidgetData appWidgetData);
         void onFail();
    }


    public interface AppWidgetService {
        @GET("weather/widget")
        Call<AppWidgetData> requestAppWidgetData(@Query("lat") double lat, @Query("lng") double lng);
    }
}
