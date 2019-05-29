package dev.thiago.socialfeed.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import dev.thiago.socialfeed.data.UserDataSourceFactory;
import dev.thiago.socialfeed.structural.user.User;

public class UserViewModel extends ViewModel {
    private LiveData<PagedList<User>> userPagedList;
    private LiveData<PageKeyedDataSource<Integer, User>> userDataSource;

    public UserViewModel() {
        UserDataSourceFactory userDataSourceFactory = new UserDataSourceFactory();
        userDataSource = userDataSourceFactory.getMultable();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false).setPageSize(10).build();

        userPagedList = new LivePagedListBuilder(userDataSourceFactory, config).build();
    }

    public LiveData<PagedList<User>> getUserPagedList() {
        return userPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, User>> getUserDataSource() {
        return userDataSource;
    }
}
