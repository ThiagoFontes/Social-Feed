package dev.thiago.socialfeed.data;

import android.content.ClipData;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import dev.thiago.socialfeed.structural.user.User;

public class UserDataSourceFactory extends DataSource.Factory {
    private MutableLiveData<PageKeyedDataSource<Integer, User>> multable = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource create() {
        UserDataSource userDataSource = new UserDataSource();
        multable.postValue(userDataSource);
        return userDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, User>> getMultable() {
        return multable;
    }
}
