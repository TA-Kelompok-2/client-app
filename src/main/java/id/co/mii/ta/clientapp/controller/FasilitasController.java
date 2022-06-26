/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.Fasilitas;
import id.co.mii.ta.clientapp.service.FasilitasService;
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
@RequestMapping("/fasilitas")
@AllArgsConstructor
public class FasilitasController {

    private FasilitasService fasilitasService;

    @GetMapping
    public String getAll(Model model) {
        return "managedata/fasilitas";
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<Fasilitas> getAllJSON() {
        return fasilitasService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Fasilitas getById(@PathVariable Long id) {
        return fasilitasService.getById(id);
    }

    @PostMapping("/createFasilitas")
    @ResponseBody
    public Fasilitas createFasilitas(@RequestBody Fasilitas fasilitas) { // binding result check dari                                                                                  // valid, ada eror atau enggak
        return fasilitasService.createFasilitas(fasilitas);
    }

    @PutMapping("/updateFasilitas/{id}")
    @ResponseBody
    public Fasilitas updateFasilitas(@PathVariable Long id, @RequestBody Fasilitas fasilitas) {
        return fasilitasService.updateFasilitas(id, fasilitas);
    }

    @DeleteMapping("/deleteFasilitas/{id}")
    @ResponseBody
    public Fasilitas delete(@PathVariable Long id) {
        return fasilitasService.deleteFasilitas(id);

    }

}