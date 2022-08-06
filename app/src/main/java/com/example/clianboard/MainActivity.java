package com.example.clianboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.clianboard.ListBoardDTO;
import com.example.clianboard.ListViewBaseAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    private List<ListBoardDTO> boardList;

    private int page = 0;

    private String webPageURL="https://www.clien.net/service/board/park?&od=T31&category=0&po="+page;

    private ListView listView;
    private ProgressBar progressBar;
    private boolean lockListView = false;
    private boolean lastItemVisibleFlag = false;
    private ListViewBaseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView = (ListView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.progress);


        boardList = new ArrayList<>();
        adapter = new ListViewBaseAdapter(this, boardList);
        listView.setAdapter(adapter);

        progressBar.setVisibility(View.GONE);

        listView.setOnScrollListener(this);

        new ListBoardTask().execute();
        boardList();



    }


    private class ListBoardTask extends AsyncTask<Void, Void, Void> {
        List<ListBoardDTO> listBoard;

        @Override
        protected Void doInBackground(Void... voids){
            try {
                Document document = Jsoup.connect(webPageURL).timeout(5000).get();
                Elements elements = document.select("div[class=list_item symph_row]");

                for (Element element : elements) {
                    ListBoardDTO data = new ListBoardDTO();

                    Elements e = element.select("span[class=subject_fixed]");
                    if (e != null && e.size() > 0) {
                        data.setTitle(e.get(0).text());
                    }
                    e = element.select("span[class=nickname]");
                    if (e != null && e.size() > 0) {
                        data.setNickName(e.get(0).text());
                    }
                    e=element.select("span[class=time popover]");
                    if (e != null && e.size()>0) {
                        data.setTimestamp(e.get(0).text());
                    }
                    /*
                    e=element.select("span.nickname > img");
                    if (e != null && e.size()>0) {
                        data.setEmb(e.attr("src"));
                    }*/
                    listBoard.add(data);
                }
                Log.d("게시판 받아오기 확인: ", listBoard.toString());
                Log.d("페이지번호 확인: ", String.valueOf(page));
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            listBoard = new ArrayList<>();
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            ListViewBaseAdapter adapter = new ListViewBaseAdapter(getBaseContext(),listBoard);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag && lockListView == false){
            progressBar.setVisibility(View.VISIBLE);

            boardList();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
    }

    private void boardList() {

        lockListView = true;
        listView.setAdapter(adapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                lockListView = false;
                page++;
            }
        },1000);
    }




}
