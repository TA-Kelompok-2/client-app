/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.History;
import id.co.mii.ta.clientapp.model.Request;
import id.co.mii.ta.clientapp.service.EmployeeService;
import id.co.mii.ta.clientapp.service.HistoryService;
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
@RequestMapping("/laporan")
@AllArgsConstructor
public class LaporanController {

    private HistoryService historyService;
    private EmployeeService EmployeeService;
    private RequestService requestService;

    @GetMapping
    public String getAll(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        List<Request> histories = requestService.getAll();
        model.addAttribute("histories", histories);
        return "laporan/laporan";
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<History> getAllJSON(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return historyService.getAll();
    }

}
