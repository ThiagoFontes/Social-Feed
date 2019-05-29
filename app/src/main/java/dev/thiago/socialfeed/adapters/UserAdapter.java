package dev.thiago.socialfeed.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import dev.thiago.socialfeed.R;
import dev.thiago.socialfeed.structural.user.User;

public class UserAdapter extends PagedListAdapter<User, UserAdapter.UserViewHolder> {
    private Context context;
    private OnUserClickListener onUserClickListener;

    public UserAdapter(Context context, OnUserClickListener onUserClickListener ) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view, onUserClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = getItem(position);

        if (user != null) {
            holder.userName.setText(user.getUsername());
            holder.name.setText(user.getName());
            holder.email.setText(user.getEmail());
        }
    }

    /**
     * It will diff Users
     */
    private static DiffUtil.ItemCallback<User> DIFF_CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        @SuppressLint("DiffUtilEquals")
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.equals(newItem);
        }
    };

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private OnUserClickListener onUserClickListener;
        private TextView userName;
        private TextView name;
        private TextView email;


        public UserViewHolder(@NonNull View itemView, OnUserClickListener onUserClickListener) {
            super(itemView);

            userName = itemView.findViewById(R.id.user_username);
            name = itemView.findViewById(R.id.user_name);
            email = itemView.findViewById(R.id.user_email);
            this.onUserClickListener = onUserClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onUserClickListener.onUserClick(getItem(getAdapterPosition()));
        }
    }

    /**
     * This interface is used to map clicks
     */
    public interface OnUserClickListener {
        void onUserClick(User user);
    }
}
