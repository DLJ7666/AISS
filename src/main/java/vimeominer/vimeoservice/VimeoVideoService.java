package vimeominer.vimeoservice;


import org.springframework.stereotype.Service;
import vimeominer.model.VimeoVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import vimeominer.model.VimeoVideoList;

import java.util.ArrayList;
import java.util.List;

@Service
public class VimeoVideoService {
    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "87879038305545edc0a789f4d4733f6b";

    public VimeoVideo getVimeoVideo(String id) {
        VimeoVideo res = null;
        String uri = String.format("https://api.vimeo.com/videos/%s", id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<VimeoVideo> request = new HttpEntity<>(null, headers);

        ResponseEntity<VimeoVideo> response = restTemplate.exchange(uri, HttpMethod.GET, request, VimeoVideo.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<VimeoVideo> getVimeoVideos(String channelId) {
        List<VimeoVideo> res = new ArrayList<>();
        String uri = String.format("https://api.vimeo.com/channels/%s/videos", channelId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<VimeoVideo> request = new HttpEntity<>(null, headers);
        ResponseEntity<VimeoVideo> response = restTemplate.exchange(uri, HttpMethod.GET, request, VimeoVideo.class);
        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }

    public VimeoVideoList getVimeoVideoList(String channelId, Integer page) {
        VimeoVideoList res = null;
        Integer pagina = page;
        if(pagina==null) {
            pagina = 1;
        }
        String uri = String.format("https://api.vimeo.com/channels/%s/videos?page=%d", channelId, pagina);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<VimeoVideoList> request = new HttpEntity<>(null, headers);
        ResponseEntity<VimeoVideoList> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                VimeoVideoList.class);
        if (response.getBody() != null) {
            res = response.getBody();
        }
        return res;
    }
}
