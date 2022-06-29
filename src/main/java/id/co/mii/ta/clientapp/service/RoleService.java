/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.service;

import id.co.mii.ta.clientapp.model.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
@Service
public class RoleService {

    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/role")
    private String url;

    @Autowired
    public RoleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Role> getAll() {
        return restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Role>>() {
        }).getBody();
    }
}
