/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.Employee;
import id.co.mii.ta.clientapp.model.Role;
import id.co.mii.ta.clientapp.model.dto.request.EmployeeRequest;
import id.co.mii.ta.clientapp.service.EmployeeService;
import id.co.mii.ta.clientapp.service.RoleService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Fathullah
 */
@Controller // menuju lokasi html (template)
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService EmployeeService;
    private RoleService roleService;

    @GetMapping
    public String getAll(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
         List<Role> roles = roleService.getAll();
        model.addAttribute("roles", roles);
        return "employee/index";
    }
    
    @GetMapping("/get-all")
    @ResponseBody
    public List<Employee> getAllJSON(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return EmployeeService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Employee getById(@PathVariable Integer id, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return EmployeeService.getById(id);
    }

    @PostMapping("/createEmployee")
    @ResponseBody
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        System.out.println(employeeRequest.getRoles());
        return EmployeeService.createEmployee(employeeRequest);
    }


    @PutMapping("/updateEmployee/{id}")
    @ResponseBody
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return EmployeeService.updateEmployee(id, employeeRequest);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    @ResponseBody
    public Employee delete(@PathVariable Integer id, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return EmployeeService.deleteEmployee(id);
        
    }
}

