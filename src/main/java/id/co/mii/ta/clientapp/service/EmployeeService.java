/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.service;

import id.co.mii.ta.clientapp.model.Employee;
import id.co.mii.ta.clientapp.model.dto.request.EmployeeRequest;
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
public class EmployeeService {

    private RestTemplate restTemplate; // -> mengkomunikasikan fronend dan beackend

    @Value("${server.baseUrl}/employee") // -> server.baserUrl dari application.properties
    private String url;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

 public List<Employee> getAll(){
         return restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
        }).getBody();
    }
    
    public Employee getById(Integer id) { // id -> 1 data id
        return restTemplate.exchange(url.concat("/" + id), // -> pengembalian http kalau getForObject yang disediakan get
                HttpMethod.GET, null, new ParameterizedTypeReference<Employee>() {
        }).getBody();
    }
    
    public Employee createEmployee(EmployeeRequest employeeRequest) { // create -> data
        return restTemplate.exchange(url,
                HttpMethod.POST,
                new HttpEntity(employeeRequest), // -> requestbody dri method sama seperti postman 
                new ParameterizedTypeReference<Employee>() { // -> tipe pengembalian dri backend karna dri back end region maka typenya region
        }).getBody(); // -> ngambil isi respon
    }

    public Employee updateEmployee(Integer id, EmployeeRequest employeeRequest) { // update ->, id -> mengambil data, Region -> data
        return restTemplate.exchange(url.concat("/" + id),
                HttpMethod.PUT, new HttpEntity(employeeRequest), new ParameterizedTypeReference<Employee>() {
        }).getBody();
    }

    public Employee deleteEmployee(Integer id) {// delete -> yang dibutuhkan id
        return restTemplate.exchange(url.concat("/" + id),
                HttpMethod.DELETE, null, new ParameterizedTypeReference<Employee>() {
        }).getBody();
    }
    
}

