/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.Role;
import id.co.mii.ta.clientapp.service.EmployeeService;
import id.co.mii.ta.clientapp.service.RoleService;
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
 * @author Mac
 */
@Controller // menuju lokasi html (template)
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

    RoleService roleService;
    EmployeeService EmployeeService;

    @GetMapping("/get-all")
    @ResponseBody
    public List<Role> getAllJSON(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return roleService.getAll();
    }

}
