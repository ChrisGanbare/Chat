package com.hongyi.chat;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<ChatActivity.Message> {

    private Context context;
    private List<ChatActivity.Message> messageList;

    public MessageAdapter(Context context, List<ChatActivity.Message> messageList) {
        super(context, 0, messageList);
        this.context = context;
        this.messageList = messageList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatActivity.Message message = messageList.get(position);

        if (message.getText() != null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_message, parent, false);
            TextView messageTextView = convertView.findViewById(R.id.messageTextView);
            messageTextView.setText(message.getText());
        } else if (message.getImage() != null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_message_image, parent, false);
            ImageView messageImageView = convertView.findViewById(R.id.messageImageView);
            messageImageView.setImageBitmap(message.getImage());
        }

        return convertView;
    }
}


