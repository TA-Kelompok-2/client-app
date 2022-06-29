/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.Request;
import id.co.mii.ta.clientapp.service.EmployeeService;
import id.co.mii.ta.clientapp.service.RequestService;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Fathullah
 */
@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    EmployeeService EmployeeService;
    RequestService requestService;
    
    @GetMapping
    public String home(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        
        Request request = new Request();
        Integer totalRequest = requestService.getAll().size();
        Integer getaprovalD = requestService.getByApprovaladmin().size();
        Integer getaprovalDSOA = requestService.getByApproval().size();
        Integer getaprovalDSOITS = requestService.getByApprovalIts().size();
        Integer getaprovalDTOA = requestService.getByApprovalDTOA().size();
        Integer getaprovalDTOAITS = requestService.getByApprovalDTOITS().size();
        Integer getaprovalS = requestService.getByApprovalS().size();
        Integer diproses = getaprovalDSOA + getaprovalDSOITS;
        Integer ditolak = getaprovalDTOA + getaprovalDTOAITS;
        Integer pending = totalRequest - diproses - ditolak - getaprovalS;
        model.addAttribute("totalRequest", totalRequest);
        model.addAttribute("diproses", diproses);
        model.addAttribute("ditolak", ditolak);
        model.addAttribute("selesai", getaprovalS);
        model.addAttribute("pending", pending);
        return "home/home";
    }
    
}
