package dev.thiago.socialfeed.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import dev.thiago.socialfeed.structural.Post;
import dev.thiago.socialfeed.structural.user.User;

public class PostDataSourceFactory extends DataSource.Factory<Integer, Post> {
    private MutableLiveData<PageKeyedDataSource<Integer, Post>> multable = new MutableLiveData<>();
    private int userID;

    public PostDataSourceFactory(int userID) {
        this.userID = userID;
    }


    @NonNull
    @Override
    public DataSource<Integer, Post> create() {
        PostDataSource postDataSource = new PostDataSource(userID);
        multable.postValue(postDataSource);
        return postDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Post>> getMultable() {
        return multable;
    }
}
