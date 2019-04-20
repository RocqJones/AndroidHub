package com.rocqjones.intoverflown.mkulimamarket;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity
{
    private String ChatKey,ChatName;
    private Toolbar mToolbar;
    private EditText messageINput;
    private TextView textView;
    private ImageButton sendBtn;
    private FirebaseAuth mAuth;
    private DatabaseReference MessageRef,UserRef,RetrieveMessageRef;
    private  String CurentUserId ;
    private RecyclerView recyclerView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mAuth = FirebaseAuth.getInstance();
        MessageRef = FirebaseDatabase.getInstance().getReference().child("Messages");
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        RetrieveMessageRef = FirebaseDatabase.getInstance().getReference().child("Messages");
        ChatKey = getIntent().getExtras().get("PostKey").toString();
        ChatName = getIntent().getExtras().get("username").toString();

        recyclerView = (RecyclerView) findViewById(R.id.users_messages_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        Toast.makeText(this, ChatName+ "", Toast.LENGTH_SHORT).show();

        messageINput = (EditText) findViewById(R.id.input_message);
        sendBtn = (ImageButton) findViewById(R.id.send_message_button);
        textView = (TextView) findViewById(R.id.receiver);

        textView.setText(ChatName);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage();
            }
        });

        DisplayMessages();
    }

    private void DisplayMessages() {
        CurentUserId = mAuth.getCurrentUser().getUid();

        FirebaseRecyclerOptions<Messages> options =
                new FirebaseRecyclerOptions.Builder<Messages>()
                        .setQuery(RetrieveMessageRef,Messages.class)
                        .build();

        FirebaseRecyclerAdapter<Messages,MessageViewHolder> adapter =
                new FirebaseRecyclerAdapter<Messages, MessageViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull Messages model) {
                        holder.username.setText(model.getSendername());
                        holder.message.setText(model.getMessage());
                        holder.date.setText(model.getDate());

                    }

                    @NonNull
                    @Override
                    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                       View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.content_message,viewGroup,false);
                       MessageViewHolder ViewHolder = new MessageViewHolder(view);
                       return ViewHolder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private static  class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView username,message,date;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.message_sender);
            message = (TextView) itemView.findViewById(R.id.message_display);
            date = (TextView) itemView.findViewById(R.id.message_date);
        }
    }

    private void SendMessage() {
        final String message = messageINput.getText().toString();
        if(TextUtils.isEmpty(message)) {
            Toast.makeText(this, "write your message first", Toast.LENGTH_SHORT).show();
        }
        else {
            //sender id
            CurentUserId = mAuth.getCurrentUser().getUid();

            // getting sender name
            UserRef.child(CurentUserId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                     final String sendername = dataSnapshot.child("name").getValue().toString();

                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                        String strDate = formatter.format(date);

                        final Map messageMap = new HashMap();
                        messageMap.put("from",CurentUserId);
                        messageMap.put("date",strDate);
                        messageMap.put("message",message);
                        messageMap.put("to",ChatKey);
                        messageMap.put("sendername",sendername);
                        messageMap.put("receivername",ChatName);

                        MessageRef.push().updateChildren(messageMap).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task)
                            {
                                if(task.isSuccessful()) {
                                    messageINput.setText("");
                                    Toast.makeText(ChatActivity.this, "message sent to " + ChatName, Toast.LENGTH_SHORT).show();
                                }

                                else {
                                    messageINput.setText("");
                                }
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}