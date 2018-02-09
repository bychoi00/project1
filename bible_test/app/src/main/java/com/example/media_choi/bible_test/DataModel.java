package com.example.media_choi.bible_test;

/**
 * Created by Media_Choi on 2018-02-09.
 */

public class DataModel {
    public String getId() {
        return id;
    }

    public String getChapter() {
        return chapter;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    String id;
    String chapter;
    String category;
    String title;
    String body;

}
