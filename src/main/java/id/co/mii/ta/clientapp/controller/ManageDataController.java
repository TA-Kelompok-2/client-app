/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

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
@RequestMapping("/manage_data")
@AllArgsConstructor
public class ManageDataController {

    @GetMapping
    public String manageData(Model model) {
        model.addAttribute("idEmp", LoginController.empId);
        return "manage_data/fasilitas_ruang";
    }
}
