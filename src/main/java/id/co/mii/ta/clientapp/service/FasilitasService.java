/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.service;

import id.co.mii.ta.clientapp.model.Fasilitas;
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
public class FasilitasService {
    
    private RestTemplate restTemplate;
    
    @Value("${server.baseUrl}/fasilitas")
    private String url;

    @Autowired
    public FasilitasService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
     public List<Fasilitas> getAll(){
         return restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Fasilitas>>() {
        }).getBody();
    }
    
    public Fasilitas getById(Integer id) { // id -> 1 data id
        return restTemplate.exchange(url.concat("/" + id), // -> pengembalian http kalau getForObject yang disediakan get
                HttpMethod.GET, null, new ParameterizedTypeReference<Fasilitas>() {
        }).getBody();
    }
    
    public Fasilitas createFasilitas(Fasilitas fasilitas) { // create -> data
        return restTemplate.exchange(url,
                HttpMethod.POST,
                new HttpEntity(fasilitas), // -> requestbody dri method sama seperti postman 
                new ParameterizedTypeReference<Fasilitas>() { // -> tipe pengembalian dri backend karna dri back end region maka typenya region
        }).getBody(); // -> ngambil isi respon
    }

    public Fasilitas updateFasilitas(Integer id, Fasilitas fasilitas) { // update ->, id -> mengambil data, Region -> data
        return restTemplate.exchange(url.concat("/" + id),
                HttpMethod.PUT, new HttpEntity(fasilitas), new ParameterizedTypeReference<Fasilitas>() {
        }).getBody();
    }

    public Fasilitas deleteFasilitas(Integer id) {// delete -> yang dibutuhkan id
        return restTemplate.exchange(url.concat("/" + id),
                HttpMethod.DELETE, null, new ParameterizedTypeReference<Fasilitas>() {
        }).getBody();
    }
    
}
