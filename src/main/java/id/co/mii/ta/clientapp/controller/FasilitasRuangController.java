/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.Fasilitas;
import id.co.mii.ta.clientapp.model.FasilitasRuang;
import id.co.mii.ta.clientapp.model.Ruang;
import id.co.mii.ta.clientapp.model.dto.request.FasilitasDTO;
import id.co.mii.ta.clientapp.service.FasilitasRuangService;
import id.co.mii.ta.clientapp.service.FasilitasService;
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
 * @author Mac
 */
@Controller // menuju lokasi html (template)
@RequestMapping("/fasilitasruang")
@AllArgsConstructor
public class FasilitasRuangController {

    FasilitasRuangService fasilitasRuangService;
    RuangService ruangService;
    FasilitasService fasilitasService;

    @GetMapping
    public String getAll(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        List<Ruang> ruangs = ruangService.getAll();
        model.addAttribute("ruangs", ruangs);
        List<Fasilitas> fasilitas = fasilitasService.getAll();
        model.addAttribute("fasilitas", fasilitas);
        return "managedata/fasilitasruang";
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<FasilitasRuang> getAllJSON(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return fasilitasRuangService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public FasilitasRuang getById(@PathVariable Integer id, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return fasilitasRuangService.getById(id);
    }

    @PostMapping("/createFasilitasRuang")
    @ResponseBody
    public FasilitasRuang createFasilitas(@RequestBody FasilitasDTO fasilitas, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return fasilitasRuangService.createFasilitasRuang(fasilitas);
    }

    @PutMapping("/updateFasilitasRuang/{id}")
    @ResponseBody
    public FasilitasRuang updateFasilitas(@PathVariable Integer id, @RequestBody FasilitasRuang fasilitas, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return fasilitasRuangService.updateFasilitasRuang(id, fasilitas);
    }

    @DeleteMapping("/deleteFasilitasRuang/{id}")
    @ResponseBody
    public FasilitasRuang delete(@PathVariable Integer id, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return fasilitasRuangService.deleteFasilitasRuang(id);

    }

}
