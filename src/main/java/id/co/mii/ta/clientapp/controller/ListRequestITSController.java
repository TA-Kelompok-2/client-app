/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.Request;
import id.co.mii.ta.clientapp.service.EmployeeService;
import id.co.mii.ta.clientapp.service.RequestService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Fathullah
 */
@Controller
@RequestMapping("/listrequestits")
@AllArgsConstructor
public class ListRequestITSController {

    private RequestService requestService;
    private EmployeeService EmployeeService;

    @GetMapping
    public String getAll(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return "request/list_request_its";
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<Request> getAllJSON(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return requestService.getAll();
    }
}
