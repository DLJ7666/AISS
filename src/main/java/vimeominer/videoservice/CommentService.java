package vimeominer.videoservice;

import imports.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import imports.model.Comment;

import java.util.ArrayList;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    public Comment creaComentario(String channelId, String videoId, String commentId, String texto, String creadoEn,
                                  User user) {
        Comment comment = null;
        String uri = String.format("http://localhost:42000/api/videominer/channels/%s/videos/%s"+
                        "/comments", channelId, videoId );
        Comment datos = new Comment(commentId, texto, creadoEn, user);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Comment> request = new HttpEntity<>(datos, headers);
        ResponseEntity<Comment> response = restTemplate.exchange(uri, HttpMethod.POST, request,
                Comment.class);
        if (response.getStatusCode().value()==201) {
            comment = response.getBody();
        }
        return comment;
    }

}
