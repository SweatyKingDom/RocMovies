package com.ndduroc.rocmovies.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ndduroc.rocmovies.Entity.Customer;
import com.ndduroc.rocmovies.Services.ICustomerService;
import com.ndduroc.rocmovies.Services.MovieService1;


@Controller
@RequestMapping("/")
public class TransferController {

    @Autowired
    private MovieService1 movieService;
    
    @Autowired
    private ICustomerService customerService;
    
    @GetMapping("/transfer") 
    public String showTransferPage(Model model) {
        List<Customer> allCustomers = customerService.getAllCustomers();
        model.addAttribute("allCustomers", allCustomers);
        return "transfer";
    }
    
    @PostMapping("/transfer")
    public String processTransfer(
            @RequestParam("sourceCustomerId") Long sourceCustomerId,
            @RequestParam("targetCustomerId") Long targetCustomerId,
            Model model) {
        
        List<Customer> allCustomers = customerService.getAllCustomers();
        model.addAttribute("allCustomers", allCustomers);
        
        try {
            boolean success = movieService.transferBorrows(sourceCustomerId, targetCustomerId);
            if (success) {
                model.addAttribute("successMessage", "Transfert réussi ! Tous les emprunts ont été transférés.");
            }
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Le transfert a échoué: " + e.getMessage());
            
        }
        
        return "transfer";
    }
}