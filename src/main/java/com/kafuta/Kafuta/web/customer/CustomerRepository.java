package com.kafuta.Kafuta.web.customer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    List<Customer> findByDistrictId(int districtId);
}
