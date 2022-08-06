package com.example.clianboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class ListViewBaseAdapter extends BaseAdapter {
    private Context context;
    private List<com.example.clianboard.ListBoardDTO> list;

    public ListViewBaseAdapter(Context context, List<com.example.clianboard.ListBoardDTO> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        com.example.clianboard.ListBoardDTO data = list.get(position);
        com.example.clianboard.ListBoardView view = null;
        if (convertView == null) {
                view = new com.example.clianboard.ListBoardView(context, data);
        } else {
                view = (com.example.clianboard.ListBoardView) convertView;
                view.setBoard(data);
        }
        return view;
    }
}
