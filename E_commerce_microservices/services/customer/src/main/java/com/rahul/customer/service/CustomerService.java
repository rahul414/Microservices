package com.rahul.customer.service;

import com.rahul.customer.dto.CustomerRequest;
import com.rahul.customer.expection.CustomerNotFoundException;
import com.rahul.customer.model.Customer;
import com.rahul.customer.model.CustomerResponse;
import com.rahul.customer.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mapper.CustomerMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {

        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {

        var customer = repository.findById(request.id())
                .orElseThrow(()-> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID :: %s",request.id())
                ));
        mergeCustomer(customer, request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address()!=null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomer() {

        return repository.findAll().stream().map(mapper::fromCustomer).collect(Collectors.toList());
    }

    public CustomerResponse findById(String id) {
        return this.repository.findById(id)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id)));
    }

    public boolean existsById(String id) {
        return this.repository.findById(id)
                .isPresent();
    }

    public void deleteCustomer(String id) {
        this.repository.deleteById(id);
    }
}
