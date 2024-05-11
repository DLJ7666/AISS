package vimeominer.videoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import imports.model.Channel;

import java.util.ArrayList;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    public Channel creaCanal(String nombre, String descripcion, String fechaCreacion) {
        Channel channel = null;
        String uri = "http://localhost:42000/api/videominer/channels";
        Channel datos = new Channel(nombre,descripcion,fechaCreacion);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Channel> request = new HttpEntity<>(datos, headers);
        ResponseEntity<Channel> response = restTemplate.exchange(uri, HttpMethod.POST, request, Channel.class);
        if (response.getStatusCode().value()==201) {
            channel = response.getBody();
        }
        return channel;
    }

}
