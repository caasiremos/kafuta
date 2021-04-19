package com.kafuta.Kafuta.web.customertype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerTypeService {

    @Autowired
    private CustomerTypeRepository repository;

    public List<CustomerType> getCustomerTypes(){
        ArrayList<CustomerType> customerTypes = new ArrayList<>();
        repository.findAll().forEach(customerTypes::add);
        return customerTypes;
    }

    public Optional<CustomerType> getCustomerType(int id){
        return repository.findById(id);
    }

    public void createCustomerType(CustomerType customerType){
        repository.save(customerType);
    }

    public void updateCustomerTpye(CustomerType customerType){
        repository.save(customerType);
    }

    public void deleteCustomerType(int id){
        repository.deleteById(id);
    }
}
