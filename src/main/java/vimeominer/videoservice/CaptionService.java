package vimeominer.videoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import imports.model.Caption;

import java.util.ArrayList;

@Service
public class CaptionService {

    @Autowired
    RestTemplate restTemplate;

    public Caption creaSubtitulo(String channelId, String videoId, String captionId, String nombre,
                                 String lenguaje) {
        Caption caption = null;
        String uri = String.format("http://localhost:42000/api/videominer/channels/%s/videos/%s/captions",
                channelId, videoId);
        Caption datos = new Caption(captionId, nombre, lenguaje);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Caption> request = new HttpEntity<>(datos, headers);
        ResponseEntity<Caption> response = restTemplate.exchange(uri, HttpMethod.POST, request, Caption.class);
        if (response.getStatusCode().value()==201) {
            caption = response.getBody();
        }
        return caption;
    }

}
