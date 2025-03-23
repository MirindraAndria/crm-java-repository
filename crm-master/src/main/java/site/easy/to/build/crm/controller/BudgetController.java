package site.easy.to.build.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import lombok.extern.flogger.Flogger;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.service.BudgetService;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.user.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller 
public class BudgetController {
    @Autowired 
    CustomerService customerService; 
    @Autowired  
    UserService userService ; 
    @Autowired 
    BudgetService budgetService ; 
      
    @GetMapping("/addBudget")
    public String addingBudget(Model model) {
        List<Customer> customers = customerService.findAll() ; 
       // List<User> users = userService.findAll() ;
        model.addAttribute("customers", customers);
       // model.addAttribute("users", users);
        return "budgetCustomer" ; 
    }
    @PostMapping("/addBudgetCustomer")
    public String customerBudget( @RequestParam int idCustomer, 
                                  @RequestParam String libelle ,
                                  @RequestParam double amount , 
                                  @RequestParam String dateBudget  
                                  ){

        //add logic to save customer budget
        budgetService.addBudget(idCustomer, libelle, amount, dateBudget );
        return "redirect:/addBudget" ; 
    }
}
