/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.FasilitasRuang;
import id.co.mii.ta.clientapp.service.FasilitasRuangService;
import java.util.List;
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
 * @author Mac
 */
@Controller // menuju lokasi html (template)
@RequestMapping("/fasilitasruang")
@AllArgsConstructor
public class FasilitasRuangController {
    
    FasilitasRuangService fasilitasRuangService;
    
     @GetMapping
    public String getAll(Model model) {
        model.addAttribute("idEmp", LoginController.empId);
        return "managedata/fasilitasruang";
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<FasilitasRuang> getAllJSON() {
        return fasilitasRuangService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public FasilitasRuang getById(@PathVariable Integer id) {
        return fasilitasRuangService.getById(id);
    }

    @PostMapping("/createFasilitasRuang")
    @ResponseBody
    public FasilitasRuang createFasilitas(@RequestBody FasilitasRuang fasilitas) { // binding result check dari                                                                                  // valid, ada eror atau enggak
        return fasilitasRuangService.createFasilitasRuang(fasilitas);
    }

    @PutMapping("/updateFasilitasRuang/{id}")
    @ResponseBody
    public FasilitasRuang updateFasilitas(@PathVariable Integer id, @RequestBody FasilitasRuang fasilitas) {
        return fasilitasRuangService.updateFasilitasRuang(id, fasilitas);
    }

    @DeleteMapping("/deleteFasilitasRuang/{id}")
    @ResponseBody
    public FasilitasRuang delete(@PathVariable Integer id) {
        return fasilitasRuangService.deleteFasilitasRuang(id);

    }
    
}
