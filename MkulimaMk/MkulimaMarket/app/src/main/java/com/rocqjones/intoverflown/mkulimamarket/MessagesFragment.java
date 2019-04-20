package com.rocqjones.intoverflown.mkulimamarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment {

   private RecyclerView recyclerView;
   private DatabaseReference UserRef;
   private ProgressBar progressBar;

    public MessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_messages, container, false);
        View vw = inflater.inflate(R.layout.fragment_messages, container, false);

        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");

        recyclerView =  vw.findViewById(R.id.farmersList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        progressBar = (ProgressBar) vw.findViewById(R.id.progress);

        DisplayUsers();
       return vw;
    }

    private void DisplayUsers() {
        FirebaseRecyclerOptions<Users> options =
                new FirebaseRecyclerOptions.Builder<Users>()
                        .setQuery(UserRef,Users.class)
                        .build();
        FirebaseRecyclerAdapter <Users,USersViewAdapter> adapter =
                new FirebaseRecyclerAdapter<Users, USersViewAdapter>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull USersViewAdapter holder, int position, @NonNull Users model) {
                        final String Key = getRef(position).getKey();
                        final String name = model.getName();
                        holder.textView.setText(model.getName());

                        //set onClick so that when yoy click the holder takes you to the ChatActivity
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Transact from MessageFragment to ChatActivity
                                Intent chatIntent = new Intent(getContext(), ChatActivity.class);
                                chatIntent.putExtra("PostKey", Key);
                                chatIntent.putExtra("username", name);
                                startActivity(chatIntent);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public USersViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.container_users,viewGroup,false);
                        USersViewAdapter ViewHolder = new USersViewAdapter(view);
                        return ViewHolder;
                    }

                    public void onDataChanged() {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
    public static class USersViewAdapter extends RecyclerView.ViewHolder {
        TextView textView;

        public USersViewAdapter(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.user_username);
        }
    }
}