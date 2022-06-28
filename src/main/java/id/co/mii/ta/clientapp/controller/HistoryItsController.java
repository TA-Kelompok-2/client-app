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
@Controller // menuju lokasi html (template)
@RequestMapping("/")
@AllArgsConstructor
public class HistoryItsController {

    private HistoryService historyService;

    @GetMapping("/historyits")
    public String getAll(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return "request/historyits";
    }

    @GetMapping("/historyapproveits")
    public String formApprove(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return "request/historyapprove";
    }

    @GetMapping("/historyits/getByStatus/{id}")
    @ResponseBody
    public List<History> getByStatus(@PathVariable Integer id, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
//        List<History> histories = historyService.getByRequest(id);
//        model.addAttribute("histories", histories);
        return historyService.getByRequest(id);
    }

    @GetMapping("/historyits/get-all")
    @ResponseBody
    public List<History> getAllJSON(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return historyService.getAll();
    }

    @GetMapping("/historyits/getById/{id}")
    @ResponseBody
    public History getById(@PathVariable Integer id, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);

        return historyService.getById(id);
    }

    @PostMapping("/historyits/createHistory")
    @ResponseBody
    public History createHistory(@RequestBody History history, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return historyService.createHistory(history);
    }

    @PutMapping("/historyits/updateHistory/{id}")
    @ResponseBody
    public History updateHistory(@PathVariable Integer id, @RequestBody HistoryRequest historyRequest, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return historyService.updateHistory(id, historyRequest);
    }

    @DeleteMapping("/historyits/deleteHistory/{id}")
    @ResponseBody
    public History delete(@PathVariable Integer id, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return historyService.deleteHistory(id);

    }
}


