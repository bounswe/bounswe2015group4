package com.socialnow.Models;

/**
 * Created by mugekurtipek on 07/01/16.
 */
public class Comment {

    private long id;


    private String owner_token;


    private String comment_text;

    /**
     *
     *@return current id
     */
    public long getId() {
        return id;
    }
    /**
     *
     * @param id id to set
     */

    public void setId(long id) {
        this.id = id;
    }
    /**
     *
     *
     *
     * Empty constructor
     */
    public Comment() {
    }

    /**
     *@return current owner of the comment
     *
     */

    public String getOwner_token() {
        return owner_token;
    }
    /**
     *
     * @param owner_token owner of the comment to set
     */
    public void setOwner_token(String owner_token) {
        this.owner_token = owner_token;
    }
    /**
     *@return current comment
     */
    public String getComment_text() {
        return comment_text;
    }

    /**
     *
     * @param comment_text defines what is written in the comment
     */

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }





}
