package org.perscholas.sdbank.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.sdbank.dao.CustomersRepoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@Slf4j
public class CustomerController {

    CustomersRepoI customersRepoI;

    @Autowired
    public CustomerController(CustomersRepoI customersRepoI) {
        this.customersRepoI = customersRepoI;
    }


}
