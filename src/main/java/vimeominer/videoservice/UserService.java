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
import imports.model.User;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    public User creaUsuario(String nombre, String userLink, String pictureLink) {
        User user = null;
        String uri = "http://localhost:42000/api/videominer/users";
        User datos = new User(nombre, userLink, pictureLink);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> request = new HttpEntity<>(datos);
        ResponseEntity<User> response = restTemplate.exchange(uri, HttpMethod.POST, request, User.class);
        if (response.getStatusCode().value()==201) {
            user = response.getBody();
        }
        return user;
    }

}
