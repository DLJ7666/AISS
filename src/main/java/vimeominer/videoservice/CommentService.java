package vimeominer.videoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import videominer.model.Comment;
import videominer.model.User;

import java.util.ArrayList;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    public Comment creaComentario(String channelId, String videoId, String texto, String creadoEn, String userId) {
        Comment comment = null;
        String uri = String.format("http://localhost:42000/api/videominer/channels/%s/videos/%s/comments",
                channelId, videoId);
        MultiValueMap<String,String> datos = new LinkedMultiValueMap<>();
        datos.put("text", new ArrayList<>());
        datos.put("createdOn", new ArrayList<>());
        datos.put("author", new ArrayList<>());
        datos.add("text", texto);
        datos.add("createdOn", creadoEn);
        datos.add("author", userId);
        HttpEntity<Comment> request = new HttpEntity<>(datos);
        ResponseEntity<Comment> response = restTemplate.exchange(uri, HttpMethod.POST, request, Comment.class);
        if (response.getStatusCode().value()==201) {
            comment = response.getBody();
        }
        return comment;
    }

}
