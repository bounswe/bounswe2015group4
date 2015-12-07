package com.socialnow.Models;

/**
 * Created by mugekurtipek on 07/12/15.
 */
public class Post {
    public String getType() {
        return type;
    }

    String type;


    private long id;


    private String owner_token;


    private String content;



    private String post_comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner_token() {
        return owner_token;
    }

    public void setOwner_token(String owner_token) {
        this.owner_token = owner_token;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPost_comments() {
        return post_comments;
    }

    public void setPost_comments(String post_comments) {
        this.post_comments = post_comments;
    }

    /*public Post(Post_Form form){
        this.setContent(form.getContent());
        this.setOwner_token(form.getOwner_token());
        this.setPost_comments(form.getPost_comments());

    }

    public Post(Error_JSON e) {
        id = -1;
        content = e.toString();
    }*/
    public Post() {
    }

}
