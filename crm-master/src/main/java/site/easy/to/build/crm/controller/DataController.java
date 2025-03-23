package site.easy.to.build.crm.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import site.easy.to.build.crm.service.data.DataService;


@Controller
public class DataController {
    @Autowired
    private DataService dataService ;

    @PostMapping("/manager/action")
    public String action() {
        dataService.clearDatabase(); 
        return "redirect:/";  
    }  
}
