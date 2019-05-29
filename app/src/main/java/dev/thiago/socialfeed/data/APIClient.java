package dev.thiago.socialfeed.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private final static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static APIClient mAPIClient;
    private Retrofit retrofit;

    private APIClient() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized APIClient getAPIClient() {
        if(mAPIClient == null) {
            mAPIClient = new APIClient();
        }
        return mAPIClient;
    }

    public QueryAPI getAPI() {
        return retrofit.create(QueryAPI.class);
    }
}
