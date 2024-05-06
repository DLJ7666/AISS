package vimeominer.service;


import vimeominer.model.VimeoVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class VimeoVideoService {
    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "87879038305545edc0a789f4d4733f6b";

    public VimeoVideo getVimeoChannel(String id) {
        VimeoVideo res = null;
        String uri = String.format("https://api.vimeo.com/channels/%s", id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<VimeoVideo> request = new HttpEntity<>(null, headers);

        ResponseEntity<VimeoVideo> response = restTemplate.exchange(uri, HttpMethod.GET, request, VimeoVideo.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<VimeoVideo> obtenerCommitsConToken(String id) {
        List<VimeoVideo> res = new ArrayList<>();
        String uri = String.format("https://api.github.com/repos/%s/%s/commits", id);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<VimeoVideo> request = new HttpEntity<>(null, headers);
        ResponseEntity<VimeoVideo> response = restTemplate.exchange(uri, HttpMethod.GET, request, VimeoVideo.class);
        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }
}
