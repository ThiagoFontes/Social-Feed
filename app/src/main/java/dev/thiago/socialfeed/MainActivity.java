package dev.thiago.socialfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;

import dev.thiago.socialfeed.adapters.UserAdapter;
import dev.thiago.socialfeed.structural.user.User;
import dev.thiago.socialfeed.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnUserClickListener{
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userAdapter = new UserAdapter(this, this);

        userViewModel.getUserPagedList().observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> users) {
                userAdapter.submitList(users);
            }
        });


        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onUserClick(User user) {
        Intent intent = new Intent(this, DetailsActivity.class);
        //Passing user to details activity
        String userJson = new Gson().toJson(user);
        intent.putExtra("user", userJson);
        startActivity(intent);
    }
}
