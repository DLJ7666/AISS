package vimeominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoVideo {

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("release_time")
    private String releasedTime;

    private List<VimeoComment> comments;

    private List<VimeoTexttrack> texttracks;
    public VimeoVideo(String uri, String name, String description, String releasedTime) {
        this.uri = uri;
        this.name = name;
        this.description = description;
        this.releasedTime = releasedTime;
        this.comments = new ArrayList<>();
        this.texttracks = new ArrayList<>();
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

    public String getReleasedTime() {
        return releasedTime;
    }

    public void setReleasedTime(String releasedTime) {
        this.releasedTime = releasedTime;
    }

    public List<VimeoComment> getComments() {
        return comments;
    }

    public void setComments(List<VimeoComment> comments) {
        this.comments = comments;
    }

    public void addComment(VimeoComment comment) {
        this.comments.add(comment);
    }

    public void addComments(Collection<VimeoComment> comments) {
        this.comments.addAll(comments);
    }

    public List<VimeoTexttrack> getTexttracks() {
        return texttracks;
    }

    public void setTexttracks(List<VimeoTexttrack> textracks) {
        this.texttracks = textracks;
    }

    public void addTexttrack(VimeoTexttrack texttrack) {
        this.texttracks.add(texttrack);
    }

    public void addTexttracks(Collection<VimeoTexttrack> texttracks) {
        this.texttracks.addAll(texttracks);
    }

    public String getId() { return this.uri.split("/")[2]; }

    @Override
    public String toString() {
        return "VimeoVideo{id='" + getId() +
                "', name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releasedTime='" + releasedTime + '\'' +
                ", comments=" + comments +
                ", texttracks=" + texttracks +
                '}';
    }
}

