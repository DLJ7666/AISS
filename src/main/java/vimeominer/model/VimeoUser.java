package vimeominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoUser {

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("name")
    private String name;

    @JsonProperty("link")
    private String userLink;

    @JsonProperty("pictures")
    private List<VimeoPicture> pictures;

    public VimeoUser(String uri, String name, String userLink, List<VimeoPicture> pictures) {
        this.uri = uri;
        this.name = name;
        this.userLink = userLink;
        this.pictures = pictures;
    }

    public String getUri() {return uri;}

    public void setUri(String uri) {this.uri = uri;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserLink() {
        return userLink;
    }

    public void setUserLink(String userLink) {
        this.userLink = userLink;
    }

    public List<VimeoPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<VimeoPicture> pictures) {
        this.pictures = pictures;
    }

    public String getId() { return this.uri.split("/")[2]; }

    @Override
    public String toString() {
        return "VimeoUser{id='" + getId() +
                "', name='" + name + '\'' +
                ", userLink='" + userLink + '\'' +
                ", pictures=" + pictures +
                '}';
    }
}
