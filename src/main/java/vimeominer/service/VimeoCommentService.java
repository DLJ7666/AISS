package vimeominer.service;

import vimeominer.model.VimeoComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
}
