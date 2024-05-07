package vimeominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoTexttrack {

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("name")
    private String name;

    @JsonProperty("language")
    private String language;

    public VimeoTexttrack(String name, String language) {
        this.name = name;
        this.language = language;
    }

    public String getUri() {return uri;}

    public void setUri(String uri) {this.uri = uri;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getId() { return this.uri.split("/")[4]; }

    @Override
    public String toString() {
        return "VimeoTexttrack{id='" + getId() +
                "', name='" + name + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
