package dev.thiago.socialfeed.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import dev.thiago.socialfeed.data.PostDataSourceFactory;
import dev.thiago.socialfeed.structural.Post;

public class PostViewModel extends ViewModel {
    private LiveData<PagedList<Post>> posts;
    private LiveData<PageKeyedDataSource<Integer, Post>> postDataSource;

    public PostViewModel(int userId) {
        PostDataSourceFactory postDataSourceFactory = new PostDataSourceFactory(userId);
        postDataSource = postDataSourceFactory.getMultable();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false).setPageSize(10).build();

        posts = new LivePagedListBuilder(postDataSourceFactory,config).build();
    }

    public LiveData<PagedList<Post>> getPostPagedList() { return posts; }
}
