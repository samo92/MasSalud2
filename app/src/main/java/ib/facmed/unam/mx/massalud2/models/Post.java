package ib.facmed.unam.mx.massalud2.models;

/**
 * Created by samo92 on 22/11/2017.
 */

public class Post {

    private int id;
    private String date;
    private String dateGmt;
    private Content content;

    /**
     * No args constructor for use in serialization
     *
     */
    public Post() {
    }

    /**
     *
     * @param content
     * @param id
     * @param dateGmt
     * @param date
     */
    public Post(int id, String date, String dateGmt, Content content) {
        super();
        this.id = id;
        this.date = date;
        this.dateGmt = dateGmt;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateGmt() {
        return dateGmt;
    }

    public void setDateGmt(String dateGmt) {
        this.dateGmt = dateGmt;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

}
