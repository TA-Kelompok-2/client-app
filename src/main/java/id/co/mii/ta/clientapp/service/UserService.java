/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.service;

import id.co.mii.ta.clientapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Fathullah
 */
@Service
public class UserService {
     private RestTemplate restTemplate;

    @Value("${server.baseUrl}/user")
    private String url;

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User getDetailByUsername(String username) {
        return restTemplate.exchange(url + "/" +username, HttpMethod.GET, null,
                new ParameterizedTypeReference<User>() {
        }).getBody();
    }
}
