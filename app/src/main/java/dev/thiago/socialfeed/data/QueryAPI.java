package dev.thiago.socialfeed.data;

import java.util.List;

import dev.thiago.socialfeed.structural.Post;
import dev.thiago.socialfeed.structural.user.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QueryAPI {
    @GET("users")
    Call<List<User>> getUser (@Query("id") int id);

    @GET("users")
    Call<List<User>> getUsersList ();

    @GET("posts")
    Call<List<Post>> getPostsList (@Query("userId") int userId);
}
