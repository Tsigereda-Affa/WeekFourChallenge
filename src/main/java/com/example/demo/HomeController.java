package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    TransactionRepository transactionRepository;

    @RequestMapping("/")
    public String listTransaction(Model model){
        model.addAttribute("transaction", transactionRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String courseForm(Model model){
        model.addAttribute("transaction", new Transaction());
        return "transactionform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Transaction transaction, BindingResult result)
    {
        if (result.hasErrors()) {
            return "transactionform";
        }
        transactionRepository.save(transaction);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("transaction", transactionRepository.findById(id).get());
        return "show";
    }

}
