package com.searchEngine.searchEngine.model;

public class SearchResult {
    private final String title;
    private final String content;
    private final float score;


    public SearchResult(String title, String content, float score) {
        this.title = title;
        this.content = content;
        this.score = score;
    }


    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }


    public float getScore() {
        return score;
    }

    

    
}
