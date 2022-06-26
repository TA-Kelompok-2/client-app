/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.History;
import id.co.mii.ta.clientapp.model.dto.request.HistoryRequest;
import id.co.mii.ta.clientapp.service.HistoryService;
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
 * @author Fathullah
 */
@Controller // menuju lokasi html (template)
@RequestMapping("/")
@AllArgsConstructor
public class HistoryController {

    private HistoryService historyService;

    @GetMapping("/history")
    public String getAll(Model model) {
        model.addAttribute("idEmp", LoginController.empId);
        return "request/history";
    }

    @GetMapping("/historyapprove")
    public String formApprove(Model model) {
        return "request/historyapprove";
    }

    @GetMapping("/history/get-all")
    @ResponseBody
    public List<History> getAllJSON() {
        return historyService.getAll();
    }

    @GetMapping("/history/getById/{id}")
    @ResponseBody
    public History getById(@PathVariable Integer id) {
        return historyService.getById(id);
    }

    @PostMapping("/history/createHistory")
    @ResponseBody
    public History createHistory(@RequestBody History history) {                                                                 // valid, ada eror atau enggak
        return historyService.createHistory(history);
    }

    @PutMapping("/history/updateHistory/{id}")
    @ResponseBody
    public History updateHistory(@PathVariable Integer id, @RequestBody HistoryRequest historyRequest) {
        return historyService.updateHistory(id, historyRequest);
    }

    @DeleteMapping("/history/deleteHistory/{id}")
    @ResponseBody
    public History delete(@PathVariable Integer id) {
        return historyService.deleteHistory(id);

    }
}