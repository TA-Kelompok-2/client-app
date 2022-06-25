/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.Request;
import id.co.mii.ta.clientapp.model.dto.request.HistoryRequest;
import id.co.mii.ta.clientapp.model.dto.request.RequestDTO;
import id.co.mii.ta.clientapp.service.FasilitasService;
import id.co.mii.ta.clientapp.service.RequestService;
import id.co.mii.ta.clientapp.service.RuangService;
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
    private RuangService ruangService;
    private FasilitasService fasilitasService;

    @GetMapping
    public String showForm(Model model) {
        return "request/request";
    }
    
    @GetMapping("/get-all")
    @ResponseBody
    public List<Request> getAllJSON() {
        return requestService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Request getById(@PathVariable Integer id) {
        return requestService.getById(id);
    }

    @PostMapping("/createrequest")
    @ResponseBody
    public Request createRequest(@RequestBody RequestDTO requestDTO) {                                                                 // valid, ada eror atau enggak
        return requestService.createRequest(requestDTO);
    }


    @PutMapping("/updateRequest/{id}")
    @ResponseBody
    public Request updateRequest(@PathVariable Integer id, @RequestBody RequestDTO requestDTO) {
        return requestService.updateRequest(id, requestDTO);
    }

    @DeleteMapping("/deleteRequest/{id}")
    @ResponseBody
    public Request delete(@PathVariable Integer id) {
        return requestService.deleteRequest(id);
        
    }

    @GetMapping("/list_request")
    public String listRequest() {
        return "request/listRequest";
    }
}
