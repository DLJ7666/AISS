package vimeominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoPictureList {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("page")
    private Integer pagina;

    @JsonProperty("per_page")
    private Integer elemPorPag;

    @JsonProperty("data")
    private List<VimeoPicture> pictures;

    public VimeoPictureList(Integer total, Integer pagina, Integer elemPorPag, List<VimeoPicture> pictures) {
        this.total = total;
        this.pagina = pagina;
        this.elemPorPag = elemPorPag;
        this.pictures = pictures;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public Integer getElemPorPag() {
        return elemPorPag;
    }

    public void setElemPorPag(Integer elemPorPag) {
        this.elemPorPag = elemPorPag;
    }

    public List<VimeoPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<VimeoPicture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "VimeoPictureList{" +
                "total=" + total +
                ", pagina=" + pagina +
                ", elemPorPag=" + elemPorPag +
                ", pictures=" + pictures +
                '}';
    }
}
