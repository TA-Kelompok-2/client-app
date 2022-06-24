/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.service;

import id.co.mii.ta.clientapp.model.Request;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
@Service
public class RequestService {

    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/request") 
    private String url;

    @Autowired
    public RequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

 public List<Request> getAll(){
         return restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Request>>() {
        }).getBody();
    }
    
    public Request getById(Long id) { // id -> 1 data id
        return restTemplate.exchange(url.concat("/" + id), // -> pengembalian http kalau getForObject yang disediakan get
                HttpMethod.GET, null, new ParameterizedTypeReference<Request>() {
        }).getBody();
    }
    
    public Request createRequest(Request request) { // create -> data
        return restTemplate.exchange(url,
                HttpMethod.POST,
                new HttpEntity(request), // -> requestbody dri method sama seperti postman 
                new ParameterizedTypeReference<Request>() { // -> tipe pengembalian dri backend karna dri back end region maka typenya region
        }).getBody(); // -> ngambil isi respon
    }

    public Request updateRequest(Long id, Request request) { // update ->, id -> mengambil data, Region -> data
        return restTemplate.exchange(url.concat("/" + id),
                HttpMethod.PUT, new HttpEntity(request), new ParameterizedTypeReference<Request>() {
        }).getBody();
    }

    public Request deleteRequest(Long id) {// delete -> yang dibutuhkan id
        return restTemplate.exchange(url.concat("/" + id),
                HttpMethod.DELETE, null, new ParameterizedTypeReference<Request>() {
        }).getBody();
    }

}
