package vimeominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoChannel {

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_time")
    private String createdTime;

    private List<VimeoVideo> videos;

    public VimeoChannel(String uri, String name, String description, String createdTime) {
        this.uri = uri;
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
        this.videos=new ArrayList<>();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<VimeoVideo> getVideos() {
        return videos;
    }

    public void setVideos(List<VimeoVideo> videos) {
        this.videos = videos;
    }

    public void addVideo(VimeoVideo video) {
        this.videos.add(video);
    }

    public String getId() {
        return this.uri.split("/")[2];
    }

    public String toString() {
        return "VimeoChannel{id='" + getId() + "', " +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", videos=" + videos +
                '}';
    }
}
