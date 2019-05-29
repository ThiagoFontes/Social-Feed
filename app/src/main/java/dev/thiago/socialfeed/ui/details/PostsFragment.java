package dev.thiago.socialfeed.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import dev.thiago.socialfeed.R;
import dev.thiago.socialfeed.adapters.PostAdapter;
import dev.thiago.socialfeed.structural.Post;
import dev.thiago.socialfeed.structural.user.User;
import dev.thiago.socialfeed.viewmodel.PostViewModel;
import dev.thiago.socialfeed.viewmodel.PostViewModelFactory;
import dev.thiago.socialfeed.viewmodel.UserViewModel;

public class PostsFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private PageViewModel pageViewModel;
    private User user;

    public static PostsFragment newInstance(int userId) {
        PostsFragment fragment = new PostsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, userId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
        String json = getActivity().getIntent().getStringExtra("user");
        user = new Gson().fromJson(json, User.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_posts, container, false);

        //Recycler view that will be called on Posts fragment
        final PostAdapter postAdapter = new PostAdapter(getActivity());
        PostViewModel postViewModel = ViewModelProviders.of(this,
                new PostViewModelFactory(this.getActivity().getApplication(),user.getId())).get(PostViewModel.class);

        postViewModel.getPostPagedList().observe(this, new Observer<PagedList<Post>>() {
            @Override
            public void onChanged(PagedList<Post> posts) {
                postAdapter.submitList(posts);
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.posts_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(postAdapter);
        return root;
    }
}