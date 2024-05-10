package vimeominer.videoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import videominer.model.Video;

import java.util.ArrayList;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;

    public Video creaCanal(String channelId, String nombre, String descripcion, String subidoEn) {
        Video video = null;
        String uri = String.format("http://localhost:42000/api/videominer/channels/%s/videos", channelId);
        MultiValueMap<String,String> datos = new LinkedMultiValueMap<>();
        datos.put("name", new ArrayList<>());
        datos.put("description", new ArrayList<>());
        datos.put("releaseTime", new ArrayList<>());
        datos.add("name", nombre);
        datos.add("description", descripcion);
        datos.add("releaseTime", subidoEn);
        HttpEntity<Video> request = new HttpEntity<>(datos);
        ResponseEntity<Video> response = restTemplate.exchange(uri, HttpMethod.POST, request, Video.class);
        if (response.getStatusCode().value()==201) {
            video = response.getBody();
        }
        return video;
    }

}