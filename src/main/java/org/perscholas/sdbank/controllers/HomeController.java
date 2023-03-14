package org.perscholas.sdbank.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.sdbank.dao.*;
import org.perscholas.sdbank.models.Accounts;
import org.perscholas.sdbank.models.Accounttxns;
import org.perscholas.sdbank.models.Cards;
import org.perscholas.sdbank.models.Customers;
import org.perscholas.sdbank.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class HomeController {

    CustomersRepoI customersRepoI;
    AccountsRepoI accountsRepoI;

    CustomerService customerService;

    AccountService accountService;

    CardRepoI cardRepoI;

    CardService cardService;

    AccounttxnsRepoI accounttxnsRepoI;

    AccounttxnService accounttxnService;

    CardtxnsRepoI cardtxnsRepoI;

    CardtxnService cardtxnService;

    @Autowired
    public HomeController(CustomersRepoI customersRepoI, AccountsRepoI accountsRepoI, CustomerService customerService, AccountService accountService, CardRepoI cardRepoI, CardService cardService, AccounttxnsRepoI accounttxnsRepoI, AccounttxnService accounttxnService, CardtxnsRepoI cardtxnsRepoI, CardtxnService cardtxnService) {
        this.customersRepoI = customersRepoI;
        this.accountsRepoI = accountsRepoI;
        this.customerService = customerService;
        this.accountService = accountService;
        this.cardRepoI = cardRepoI;
        this.cardService = cardService;
        this.accounttxnsRepoI = accounttxnsRepoI;
        this.accounttxnService = accounttxnService;
        this.cardtxnsRepoI = cardtxnsRepoI;
        this.cardtxnService = cardtxnService;
    }

    @GetMapping(value = {"/","index"})
    public String homePage(Model model) {
        model.addAttribute("customer", customersRepoI.findAll());
        return "index";
    }

    @GetMapping("form")
    public String customerForm(Model model){
        model.addAttribute("customer",new Customers());
        return "form";
    }

    @PostMapping("customerprocess")
    public String customerProcess(@ModelAttribute("customer") Customers customers) {
        customersRepoI.save(customers);
        return "redirect:/index";
    }

    @GetMapping("/deletecustomer/{id}")
    public String deletecustomer(@PathVariable(name="id") int id) throws Exception {
        customerService.deleteCustomer(id);
        return "redirect:/index";
    }

    @GetMapping("/updatecustomer/{id}")
    public String updatecustomer(Model model, @PathVariable("id") int id) {
      model.addAttribute("customer",customersRepoI.findById(id));
        return "form";
    }

    @GetMapping("accounts")
    public String accounts(Model model) {
        model.addAttribute("account", accountsRepoI.findAll());
        return "accounts";
    }

    @GetMapping("/deleteaccount/{id}")
    public String deleteaccount(@PathVariable(name="id") int id) throws Exception {
        accountService.deleteAccount(id);
        return "redirect:/accounts";
    }

    @GetMapping("/updateaccount/{id}")
    public String updateaccount(Model model, @PathVariable("id") int id) {
        model.addAttribute("account",accountsRepoI.findById(id));
        return "accountform";
    }

    @GetMapping("/updatebalance/{id}")
    public String updatebalance(Model model, @PathVariable("id") int id) {
        Set<Accounttxns> accounttxnsSet = accountsRepoI.findById(id).get().getAccounttxn();
        model.addAttribute("accounttxn",accounttxnsSet);
        return "accounttxn";
    }



    @GetMapping("accountform")
    public String accountForm(Model model){
        model.addAttribute("account",new Accounts());
        return "accountform";
    }

    @GetMapping("/depositform/{id}")
    public  String depositForm(Model model, @PathVariable("id") int id){
        Accounttxns accounttxns = new Accounttxns();
        accounttxns.setAccount(accountsRepoI.findById(id).get());
        model.addAttribute("accounttxn", accounttxns);
        return "depositform";
    }

    @PostMapping("accountprocess")
    public String accountProcess(@ModelAttribute("account") Accounts accounts) {
        if (accounts.getCustomer().getId() != null) {
            Customers c = accounts.getCustomer();
            c.addAccount(accounts);
            c = customerService.createOrUpdate(c);
            return "redirect:/accounts";
        } else {
            log.warn(accounts.getCustomer().getId().toString());
            Accounts a = accountsRepoI.findById(accounts.getId()).get();
            a.setCustomer(customersRepoI.findById(accounts.getCustomer().getId()).get());
            a = accountService.createOrUpdate(accounts);
            log.warn(a.toString());
            return "redirect:/accounts";
        }


    }

    @PostMapping("depositprocess")
    public String depositProcess(@ModelAttribute("accounttxn") Accounttxns accounttxns) {
        accountService.depositMoney(accounttxns.getAccount(),accounttxns.getAmount(),accounttxns.getDate());
        return "redirect:/accounttxn";
    }

    @GetMapping("/withdrawform/{id}")
    public  String withdrawForm(Model model, @PathVariable("id") int id){
        Accounttxns accounttxns = new Accounttxns();
        accounttxns.setAccount(accountsRepoI.findById(id).get());
        model.addAttribute("accounttxn", new Accounttxns());
        return "withdrawform";
    }

    @PostMapping("withdrawprocess")
    public String withdrawProcess(@ModelAttribute("accounttxn") Accounttxns accounttxns) {
        accountService.withdrawMoney(accounttxns.getAccount(),accounttxns.getAmount(),accounttxns.getDate());
        return "redirect:/accounttxn";
    }

    @GetMapping("cards")
    public String cards(Model model) {
        model.addAttribute("card", cardRepoI.findAll());
        return "cards";
    }

    @GetMapping("/deletecard/{id}")
    public String deletecard(@PathVariable(name="id") int id) throws Exception {
        cardService.deleteCard(id);
        return "redirect:/cards";
    }

    @GetMapping("/updatecard/{id}")
    public String updatecard(Cards cards) {
        cardRepoI.findById(cards.getId()).get();
        return "cards";
    }

    @GetMapping("cardform")
    public String cardForm(Model model){
        model.addAttribute("card",new Cards());
        return "cardform";
    }

    @PostMapping("cardprocess")
    public String cardProcess(@ModelAttribute("card") Cards cards) {
        cardRepoI.save(cards);
        return "redirect:/cards";
    }

    @GetMapping("accounttxn")
    public String accounttxn(Model model) {
        model.addAttribute("accounttxn", accounttxnsRepoI.findAll());
        return "accounttxn";
    }

    @GetMapping("cardtxn")
    public String cardtxn(Model model) {
        model.addAttribute("cardtxn", cardtxnsRepoI.findAll());
        return "cardtxn";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    public String homePage(){
        return "index";
    }

    @GetMapping("/css/mystyles.css?continue")
    public String logindefault() {
        return "redirect:/index";
    }

}
