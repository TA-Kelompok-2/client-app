/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.Request;
import id.co.mii.ta.clientapp.service.RequestService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Fathullah
 */
@Controller
@RequestMapping("/request")
public class RequestController {

    private RequestService requestService;

    @GetMapping
    public String getAll(Model model) {
        return "request/Request";
    }
    
    @GetMapping("/get-all")
    @ResponseBody
    public List<Request> getAllJSON() {
        return requestService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Request getById(@PathVariable Long id) {
        return requestService.getById(id);
    }

    @PostMapping("/createRequest")
    @ResponseBody
    public Request createRequest(@RequestBody Request request) {                                                                 // valid, ada eror atau enggak
        return requestService.createRequest(request);
    }


    @PutMapping("/updateRequest/{id}")
    @ResponseBody
    public Request updateRequest(@PathVariable Long id, @RequestBody Request request) {
        return requestService.updateRequest(id, request);
    }

    @DeleteMapping("/deleteRequest/{id}")
    @ResponseBody
    public Request delete(@PathVariable Long id) {
        return requestService.deleteRequest(id);
        
    }

    @GetMapping("/list_request")
    public String listRequest() {
        return "request/listRequest";
    }
}
