package vimeominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoCommentList {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("page")
    private Integer pagina;

    @JsonProperty("per_page")
    private Integer elemPorPag;

    @JsonProperty("data")
    private List<VimeoComment> comments;

    public VimeoCommentList(Integer total, Integer pagina, Integer elemPorPag, List<VimeoComment> comments) {
        this.total = total;
        this.pagina = pagina;
        this.elemPorPag = elemPorPag;
        this.comments = comments;
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

    public List<VimeoComment> getComments() {
        return comments;
    }

    public void setComments(List<VimeoComment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "VimeoCommentList{" +
                "total=" + total +
                ", pagina=" + pagina +
                ", elemPorPag=" + elemPorPag +
                ", comments=" + comments +
                '}';
    }

    public Integer getNumPags() {
        return getTotal()/getElemPorPag()+1;
    }
}
