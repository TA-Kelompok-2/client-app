/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.controller;

import id.co.mii.ta.clientapp.model.FasilitasRuang;
import id.co.mii.ta.clientapp.model.Request;
import id.co.mii.ta.clientapp.model.Ruang;
import id.co.mii.ta.clientapp.model.dto.request.RequestDTO;
import id.co.mii.ta.clientapp.service.EmployeeService;
import id.co.mii.ta.clientapp.service.FasilitasRuangService;
import id.co.mii.ta.clientapp.service.RequestService;
import id.co.mii.ta.clientapp.service.RuangService;
import id.co.mii.ta.clientapp.util.FileUploadUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Fathullah
 */
@Controller
@RequestMapping("/request")
@AllArgsConstructor
public class RequestController {

    private RequestService requestService;
    private FasilitasRuangService fasilitasRuangService;
    private RuangService ruangService;
    private EmployeeService EmployeeService;

    @GetMapping
    public String showForm(Model model, RequestDTO requestDTO, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        List<FasilitasRuang> fasilitasRuangs = fasilitasRuangService.getAll();
        model.addAttribute("fasilitasRuangs", fasilitasRuangs);
        List<Ruang> ruangs = ruangService.getAll();
        model.addAttribute("ruangs", ruangs);
        String empNameSession = EmployeeService.getById(empIdSession).getName();
        model.addAttribute("nameEmp", empNameSession);
        return "request/request";
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<Request> getAllJSON(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return requestService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Request getById(@PathVariable Integer id, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return requestService.getById(id);
    }

    @PostMapping
    public String createRequest(RequestDTO requestDTO, Model model, HttpServletRequest httpServletRequest, 
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
//        requestService.createRequest(requestDTO);
        
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        fileName = fileName.replace(" ", "_");
        requestDTO.setGambar(fileName);
        
        Request savedRequest = requestService.createRequest(requestDTO);
        String uploadDir = "request-photos/" + savedRequest.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        
        return "redirect:/";
    }

    @PostMapping("/updateRequest/{id}")
    @ResponseBody
    public Request updateRequest(@PathVariable Integer id, @RequestBody RequestDTO requestDTO, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return requestService.updateRequest(id, requestDTO);
    }

    @DeleteMapping("/deleteRequest/{id}")
    @ResponseBody
    public Request delete(@PathVariable Integer id, Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return requestService.deleteRequest(id);

    }

    @GetMapping("/approval")
    @ResponseBody
    public List<Request> getByApproval(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return requestService.getByApproval();
    }

    @GetMapping("/approvaladmin")
    @ResponseBody
    public List<Request> getByApprovaladmin(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return requestService.getByApprovaladmin();
    }

    @GetMapping("/approvalits")
    @ResponseBody
    public List<Request> getByApprovalits(Model model, HttpServletRequest httpServletRequest) {
        Integer empIdSession = (Integer) httpServletRequest.getSession().getAttribute("empIdSession");
        model.addAttribute("idEmp", empIdSession);
        return requestService.getByApprovalIts();
    }

}
