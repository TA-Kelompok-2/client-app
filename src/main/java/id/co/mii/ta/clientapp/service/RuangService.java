/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.service;

import id.co.mii.ta.clientapp.model.Ruang;
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
public class RuangService {
    private RestTemplate restTemplate;
    
    @Value("${server.baseUrl}/ruang")
    private String url;

    @Autowired
    public RuangService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
     public List<Ruang> getAll(){
         return restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ruang>>() {
        }).getBody();
    }
    
    public Ruang getById(Long id) { // id -> 1 data id
        return restTemplate.exchange(url.concat("/" + id), // -> pengembalian http kalau getForObject yang disediakan get
                HttpMethod.GET, null, new ParameterizedTypeReference<Ruang>() {
        }).getBody();
    }
    
    public Ruang createRuang(Ruang ruang) { // create -> data
        return restTemplate.exchange(url,
                HttpMethod.POST,
                new HttpEntity(ruang), // -> requestbody dri method sama seperti postman 
                new ParameterizedTypeReference<Ruang>() { // -> tipe pengembalian dri backend karna dri back end region maka typenya region
        }).getBody(); // -> ngambil isi respon
    }

    public Ruang updateRuang(Long id, Ruang ruang) { // update ->, id -> mengambil data, Region -> data
        return restTemplate.exchange(url.concat("/" + id),
                HttpMethod.PUT, new HttpEntity(ruang), new ParameterizedTypeReference<Ruang>() {
        }).getBody();
    }

    public Ruang deleteRuang(Long id) {// delete -> yang dibutuhkan id
        return restTemplate.exchange(url.concat("/" + id),
                HttpMethod.DELETE, null, new ParameterizedTypeReference<Ruang>() {
        }).getBody();
    }
    
}
