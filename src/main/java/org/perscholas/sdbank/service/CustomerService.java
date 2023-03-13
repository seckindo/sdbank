package org.perscholas.sdbank.service;

import jakarta.transaction.Transactional;
import org.perscholas.sdbank.dao.CustomersRepoI;
import org.perscholas.sdbank.models.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class CustomerService {

    CustomersRepoI customersRepoI;

    @Autowired
    public CustomerService(CustomersRepoI customersRepoI) {
        this.customersRepoI = customersRepoI;
    }

    public void deleteCustomer(int id) throws Exception {
        Optional<Customers> toBeDeleted = customersRepoI.findById(id);
        if(toBeDeleted.isPresent())
            customersRepoI.delete(toBeDeleted.get());
        else
            throw new Exception("The Customer cannot be find" + toBeDeleted);
    }

    public Customers createOrUpdate(Customers customers){
        if(customersRepoI.findBySsn(customers.getSsn()).isPresent()) {
            Customers original = customersRepoI.findBySsn(customers.getSsn()).get();
            original.setFirstName(customers.getFirstName());
            original.setLastName(customers.getLastName());
            original.setEmail(customers.getEmail());
            original.setAddressStreet(customers.getAddressStreet());
            original.setCity(customers.getCity());
            original.setState(customers.getState());
            original.setZip(customers.getZip());
            original.setDob(customers.getDob());
            original.setPob(customers.getPob());
            original.setSsn(customers.getSsn());

            return customersRepoI.save(original);
        } else {
            return customersRepoI.save(customers);
        }
    }
}
