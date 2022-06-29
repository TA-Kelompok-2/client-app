/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.Ruang;
import id.co.mii.ta.clientapp.service.EmployeeService;
import id.co.mii.ta.clientapp.service.RuangService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
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
@RequestMapping("/ruang")
@AllArgsConstructor
public class RuangController {

    private RuangService ruangService;
    private EmployeeService EmployeeService;

    @GetMapping
    public String getAll(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return "managedata/ruang";
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<Ruang> getAllJSON(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return ruangService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Ruang getById(@PathVariable Integer id, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return ruangService.getById(id);
    }

    @PostMapping("/createRuang")
    @ResponseBody
    public Ruang createRuang(@RequestBody Ruang ruang, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return ruangService.createRuang(ruang);
    }

    @PutMapping("/updateRuang/{id}")
    @ResponseBody
    public Ruang updateRuang(@PathVariable Integer id, @RequestBody Ruang ruang, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return ruangService.updateRuang(id, ruang);
    }

    @DeleteMapping("/deleteRuang/{id}")
    @ResponseBody
    public Ruang delete(@PathVariable Integer id, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return ruangService.deleteRuang(id);
    }

}
