/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Fathullah
 */
@Controller
@RequestMapping("/")
public class RequestController {

    @GetMapping("/request")
    public String request() {
        return "request/request";
    }

    @GetMapping("/history")
    public String history() {
        return "request/history";
    }

    @GetMapping("/list_request")
    public String listRequest() {
        return "request/listRequest";
    }
}
