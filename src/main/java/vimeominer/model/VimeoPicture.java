package vimeominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoPicture {

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("base_link")
    private String link;

    public VimeoPicture(String uri, String link) {
        this.uri = uri;
        this.link = link;
    }

    public String getUri() { return uri; }

    public void setUri(String uri) { this.uri = uri; }

    public VimeoPicture(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getId() { return this.uri.split("/")[4]; }

    @Override
    public String toString() {
        return "VimeoPicture{id='" + getId() +
                "', link='" + link + '\'' +
                '}';
    }
}
