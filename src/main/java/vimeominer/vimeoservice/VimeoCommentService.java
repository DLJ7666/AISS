package vimeominer.vimeoservice;

import org.springframework.stereotype.Service;
import vimeominer.model.VimeoComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import vimeominer.model.VimeoCommentList;

import java.util.ArrayList;
import java.util.List;

@Service
public class VimeoCommentService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "87879038305545edc0a789f4d4733f6b";

    public VimeoComment getVimeoComment(String videoId, String commentId) {
        VimeoComment res = null;
        String uri = String.format("https://api.vimeo.com/videos/%s/comments/%s", videoId, commentId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<VimeoComment> request = new HttpEntity<>(null, headers);

        ResponseEntity<VimeoComment> response = restTemplate.exchange(uri, HttpMethod.GET, request, VimeoComment.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<VimeoComment> getVimeoComments(String videoId) {
        List<VimeoComment> res = new ArrayList<>();
        String uri = String.format("https://api.vimeo.com/videos/%s/comments", videoId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<VimeoComment> request = new HttpEntity<>(null, headers);
        ResponseEntity<VimeoComment> response = restTemplate.exchange(uri, HttpMethod.GET, request, VimeoComment.class);
        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }

    public VimeoCommentList getVimeoCommentList(String videoId, Integer page) {
        VimeoCommentList res = null;
        Integer pagina = page;
        if(pagina==null) {
            pagina = 1;
        }
        String uri = String.format("https://api.vimeo.com/videos/%s/comments?page=%d", videoId, pagina);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<VimeoCommentList> request = new HttpEntity<>(null, headers);
        ResponseEntity<VimeoCommentList> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                VimeoCommentList.class);
        if (response.getBody() != null) {
            res=response.getBody();
        }
        return res;
    }
}
