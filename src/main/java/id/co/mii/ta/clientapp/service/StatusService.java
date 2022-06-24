/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.service;

import id.co.mii.ta.clientapp.model.Status;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Fathullah
 */
public class StatusService {

    private RestTemplate restTemplate; // -> mengkomunikasikan fronend dan beackend

    @Value("${server.baseUrl}/status") // -> server.baserUrl dari application.properties
    private String url;

    @Autowired
    public StatusService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Status> getAll() {
        return restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Status>>() {
        }).getBody();
    }

    public Status getById(Long id) { // id -> 1 data id
        return restTemplate.exchange(url.concat("/" + id), // -> pengembalian http kalau getForObject yang disediakan get
                HttpMethod.GET, null, new ParameterizedTypeReference<Status>() {
        }).getBody();
    }

    public Status createStatus(Status status) { // create -> data
        return restTemplate.exchange(url,
                HttpMethod.POST,
                new HttpEntity(status), // -> requestbody dri method sama seperti postman 
                new ParameterizedTypeReference<Status>() { // -> tipe pengembalian dri backend karna dri back end region maka typenya region
        }).getBody(); // -> ngambil isi respon
    }

    public Status updateStatus(Long id, Status status) { // update ->, id -> mengambil data, Region -> data
        return restTemplate.exchange(url.concat("/" + id),
                HttpMethod.PUT, new HttpEntity(status), new ParameterizedTypeReference<Status>() {
        }).getBody();
    }

    public Status deleteStatus(Long id) {// delete -> yang dibutuhkan id
        return restTemplate.exchange(url.concat("/" + id),
                HttpMethod.DELETE, null, new ParameterizedTypeReference<Status>() {
        }).getBody();
    }
}
