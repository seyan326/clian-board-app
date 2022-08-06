package com.example.clianboard;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ListBoardView extends LinearLayout {
    //게시판 목록 1개에 보여질 요소
    private TextView rowTitle, rowNickName, rowTimeStamp;
    private ImageView rowEmb;
    private LayoutInflater inflate;

    public ListBoardView(Context context, com.example.clianboard.ListBoardDTO data) {
        super(context);

        inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflate.inflate(R.layout.list_board_view, this, true);

        rowTitle = findViewById(R.id.row_title);
        rowNickName = findViewById(R.id.row_nickName);
        rowTimeStamp = findViewById(R.id.row_time);
        rowEmb = findViewById(R.id.row_emb);

        setBoard(data);
    }

    void setBoard(com.example.clianboard.ListBoardDTO data) {
        // title setting
        if (data.getTitle()!=null&&data.getTitle().trim().length()>0)
            rowTitle.setText(data.getTitle());
        else
            rowTitle.setText("");

        // nickName setting
        if (data.getNickName()!=null&&data.getNickName().trim().length()>0)
            rowNickName.setText(data.getNickName());
        else
            rowNickName.setText("");
        // timeStamp setting
        if (data.getTimestamp()!=null&&data.getTimestamp().trim().length()>0)
            rowTimeStamp.setText(data.getTimestamp());
        else
            rowTimeStamp.setText("");
        // Emblem setting
        if (data.getEmb() != null)
            rowEmb.setImageURI(Uri.parse(""));
        else
            rowEmb.setImageURI(Uri.parse(""));

    }
}
