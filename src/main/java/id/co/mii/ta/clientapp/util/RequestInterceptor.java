/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.util;

import java.io.IOException;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 *
 * @author Fathullah
 */
public class RequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        if (!request.getURI().getPath().equals("/api/login")) {
//            request.getHeaders().add("Authorization", "Basic " + auth.getCredentials());
//        }

        ClientHttpResponse response = execution.execute(request, body);

        return response;

    }

}