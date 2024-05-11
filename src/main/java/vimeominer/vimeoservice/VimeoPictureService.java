package vimeominer.vimeoservice;


import org.springframework.stereotype.Service;
import vimeominer.model.VimeoPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import vimeominer.model.VimeoPictureList;

import java.util.ArrayList;
import java.util.List;

@Service
public class VimeoPictureService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "87879038305545edc0a789f4d4733f6b";

    public VimeoPicture getVimeoPicture(String userId, String pictureId) {
        VimeoPicture res = null;
        String uri = String.format("https://api.vimeo.com/user/%s/pictures/%s", userId, pictureId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<VimeoPicture> request = new HttpEntity<>(null, headers);

        ResponseEntity<VimeoPicture> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                VimeoPicture.class);
        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<VimeoPicture> getVimeoPictures(String userId) {
        List<VimeoPicture> res = new ArrayList<>();
        String uri = String.format("https://api.vimeo.com/user/%s/pictures", userId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<VimeoPicture> request = new HttpEntity<>(null, headers);
        ResponseEntity<VimeoPicture> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                VimeoPicture.class);
        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }

    public VimeoPictureList getVimeoPictureList(String userId) {
        VimeoPictureList res = null;
        String uri = String.format("https://api.vimeo.com/user/%s/pictures", userId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<VimeoPictureList> request = new HttpEntity<>(null, headers);
        ResponseEntity<VimeoPictureList> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                VimeoPictureList.class);
        if (response.getBody() != null) {
            res=response.getBody();
        }
        return res;
    }
}
