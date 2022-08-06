package com.example.clianboard;


public class ListBoardDTO {

    private String title;
    private String nickName;
    private String timestamp;
    private String emb;

    //getter, setter

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickName(){
        return nickName;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public String getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

    public String getEmb(){
        return emb;
    }

    public void setEmb(String emb){
        this.emb = emb;
    }

    //toString
    @Override
    public String toString(){
        return title + ":" + nickName + ":" + timestamp;
    }
}
