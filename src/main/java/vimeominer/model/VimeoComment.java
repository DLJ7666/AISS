package vimeominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoComment {


    @JsonProperty("text")
    private String text;

    @JsonProperty("created_on")
    private String createdOn;

    @JsonProperty("user")
    private VimeoUser user;

    @JsonProperty("uri")
    private String uri;

    public VimeoComment(String uri, String text, String createdOn, VimeoUser user) {
        this.uri = uri;
        this.text = text;
        this.createdOn = createdOn;
        this.user = user;
    }

    public String getUri() { return uri; }

    public void setUri(String uri) { this.uri = uri; }

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

    public VimeoUser getUser() {
        return user;
    }

    public void setUser(VimeoUser user) {
        this.user = user;
    }

    public String getId() { return this.uri.split("/")[4]; }

    @Override
    public String toString() {
        return "VimeoComment{id='"+ getId() +
                "', text='" + text + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", user=" + user +
                '}';
    }
}
