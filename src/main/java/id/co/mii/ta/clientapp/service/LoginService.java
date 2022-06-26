/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.service;

import id.co.mii.ta.clientapp.model.dto.request.LoginRequest;
import id.co.mii.ta.clientapp.model.dto.response.LoginResponse;
import id.co.mii.ta.clientapp.util.BasicHeader;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Fathullah
 */
@Service
public class LoginService {

    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/login")
    private String url;

    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Boolean login(LoginRequest loginRequest) { //ceck data akun yg masuk, jika terdaftar true -> tidak false
        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());
        try {
            ResponseEntity<LoginResponse> res = restTemplate.postForEntity(url,
                    new HttpEntity(loginRequest), LoginResponse.class);

            if (res.getStatusCode() == HttpStatus.OK) { // if berhasil masuk
                setAuthentication(res.getBody(), loginRequest.getPassword()); // set auth
                return true;
            }
        } catch (Exception e) {

        }

        return false;
    }

    public void setAuthentication(LoginResponse loginResponse, String password) {
        // merubah collection ke list -> stream
        Collection<GrantedAuthority> authorities // grantedauth -> yang diberikan auth
                = loginResponse.getAuthorities()
                        .stream()
                        .map(auth -> new SimpleGrantedAuthority(auth))
                        .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                loginResponse.getUsername(),
                BasicHeader.createBasicToken(loginResponse.getUsername(), password),
                authorities);

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
