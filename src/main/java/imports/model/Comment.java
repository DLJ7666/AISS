package imports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import imports.model.User;

public class Comment {

    private static Long nextId;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("text")
    private String text;

    @JsonProperty("createdOn")
    private String createdOn;

    @JsonProperty("author")
    private User author;


    public Comment(String text, String createdOn, User author) {
        this.id= nextId;
        nextId++;
        this.text = text;
        this.createdOn = createdOn;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", author=" + author +
                '}';
    }
}
