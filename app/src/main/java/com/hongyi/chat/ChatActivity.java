package com.hongyi.chat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private List<Message> messageList;
    private MessageAdapter messageAdapter;
    private ListView messageListView;
    private EditText messageEditText;
    private Button sendButton;

    // 此处为Message类的定义
    public class Message {
        private String text;
        private Bitmap image;

        public Message(String text, Bitmap image) {
            this.text = text;
            this.image = image;
        }

        public String getText() {
            return text;
        }

        public Bitmap getImage() {
            return image;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(this, messageList);
        messageListView = findViewById(R.id.messageListView);
        messageListView.setAdapter(messageAdapter);

        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString().trim();
                if (!message.isEmpty()) {
                    sendMessage(message);
                    messageEditText.setText("");
                }
            }
        });
    }

    private void sendMessage(String message) {
        // 创建一个新的消息对象并添加到消息列表
        Message newMessage = new Message(message, null); // 文本消息，没有图片
        messageList.add(newMessage);
        messageAdapter.notifyDataSetChanged();

        // 滚动到最新的消息
        messageListView.smoothScrollToPosition(messageList.size() - 1);
    }

    private void sendImageMessage(String imagePath) {
        // 根据图片路径创建Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

        // 创建一个新的消息对象并添加到消息列表
        Message newMessage = new Message(null, bitmap); // 图片消息，没有文本
        messageList.add(newMessage);
        messageAdapter.notifyDataSetChanged();

        // 滚动到最新的消息
        messageListView.smoothScrollToPosition(messageList.size() - 1);
    }
}
