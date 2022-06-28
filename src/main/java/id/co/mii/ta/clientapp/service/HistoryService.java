/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.service;

import id.co.mii.ta.clientapp.model.History;
import id.co.mii.ta.clientapp.model.dto.request.HistoryRequest;
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
 * @author Fathullah
 */
@Service
public class HistoryService {

    private RestTemplate restTemplate; // -> mengkomunikasikan fronend dan beackend

    @Value("${server.baseUrl}/history") // -> server.baserUrl dari application.properties
    private String url;

    @Autowired
    public HistoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<History> getAll() {
        return restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<History>>() {
        }).getBody();
    }

    public History getById(Integer id) { // id -> 1 data id
        return restTemplate.exchange(url.concat("/" + id), // -> pengembalian http kalau getForObject yang disediakan get
                HttpMethod.GET, null, new ParameterizedTypeReference<History>() {
        }).getBody();
    }

    public List<History> getByRequest(Integer id) { // id -> 1 data id
        return restTemplate.exchange(url.concat("/request/" + id), // -> pengembalian http kalau getForObject yang disediakan get
                HttpMethod.GET, null, new ParameterizedTypeReference<List<History>>() {
        }).getBody();
    }

    public History createHistory(History history) { // create -> data
        return restTemplate.exchange(url,
                HttpMethod.POST,
                new HttpEntity(history), // -> requestbody dri method sama seperti postman 
                new ParameterizedTypeReference<History>() { // -> tipe pengembalian dri backend karna dri back end region maka typenya region
        }).getBody(); // -> ngambil isi respon
    }

    public History updateHistory(Integer id, HistoryRequest historyRequest) { // update ->, id -> mengambil data, Region -> data
        return restTemplate.exchange(url.concat("/" + id),
                HttpMethod.PUT, new HttpEntity(historyRequest), new ParameterizedTypeReference<History>() {
        }).getBody();
    }

    public History deleteHistory(Integer id) {// delete -> yang dibutuhkan id
        return restTemplate.exchange(url.concat("/" + id),
                HttpMethod.DELETE, null, new ParameterizedTypeReference<History>() {
        }).getBody();
    }

}
