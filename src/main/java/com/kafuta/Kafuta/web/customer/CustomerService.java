package com.kafuta.Kafuta.web.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        repository.findAll().forEach(customers::add);
        return customers;
    }

    public void createCustomer(Customer customer) {
        repository.save(customer);
    }

    public Optional<Customer> getCustomer(int id) {
        return repository.findById(id);
    }

    public void updateCustomer(Customer customer) {
        repository.save(customer);
    }

    public void deleteCustomer(int id) {
        repository.deleteById(id);
    }
}
