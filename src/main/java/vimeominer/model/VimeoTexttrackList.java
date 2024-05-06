package vimeominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoTexttrackList {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("page")
    private Integer pagina;

    @JsonProperty("per_page")
    private Integer elemPorPag;

    @JsonProperty("data")
    private List<VimeoTexttrack> texttracks;

    public VimeoTexttrackList(Integer total, Integer pagina, Integer elemPorPag, List<VimeoTexttrack> texttracks) {
        this.total = total;
        this.pagina = pagina;
        this.elemPorPag = elemPorPag;
        this.texttracks = texttracks;
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

    public List<VimeoTexttrack> getTexttracks() {
        return texttracks;
    }

    public void setTexttracks(List<VimeoTexttrack> texttracks) {
        this.texttracks = texttracks;
    }

    @Override
    public String toString() {
        return "VimeoTexttrackList{" +
                "total=" + total +
                ", pagina=" + pagina +
                ", elemPorPag=" + elemPorPag +
                ", texttracks=" + texttracks +
                '}';
    }
}
