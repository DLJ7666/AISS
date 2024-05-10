package vimeominer.vimeoservice;


import vimeominer.model.VimeoTexttrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import vimeominer.model.VimeoTexttrackList;

import java.util.ArrayList;
import java.util.List;

public class VimeoTexttrackService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "87879038305545edc0a789f4d4733f6b";

    public VimeoTexttrack getVimeoTextrack(String videoId, String texttrackId) {
        VimeoTexttrack res = null;
        String uri = String.format("https://api.vimeo.com/videos/%s/texttracks/%s", videoId, texttrackId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<VimeoTexttrack> request = new HttpEntity<>(null, headers);
        ResponseEntity<VimeoTexttrack> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                VimeoTexttrack.class);
        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<VimeoTexttrack> getVimeoTexttracks(String videoId) {
        List<VimeoTexttrack> res = new ArrayList<>();
        String uri = String.format("https://api.vimeo.com/videos/%s/texttracks", videoId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<VimeoTexttrack> request = new HttpEntity<>(null, headers);
        ResponseEntity<VimeoTexttrack> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                VimeoTexttrack.class);
        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }

    public VimeoTexttrackList getVimeoTexttrackList(String videoId, Integer page) {
        VimeoTexttrackList res = null;
        Integer pagina = page;
        if(pagina==null) {
            pagina = 1;
        }
        String uri = String.format("https://api.vimeo.com/videos/%s/texttracks?page=%d", videoId, pagina);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<VimeoTexttrackList> request = new HttpEntity<>(null, headers);
        ResponseEntity<VimeoTexttrackList> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                VimeoTexttrackList.class);
        if (response.getBody() != null) {
            res = response.getBody();
        }
        return res;
    }
}
