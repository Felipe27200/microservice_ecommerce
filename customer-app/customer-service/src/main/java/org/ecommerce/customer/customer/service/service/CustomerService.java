package org.ecommerce.customer.customer.service.service;

import lombok.AllArgsConstructor;
import org.ecommerce.customer.customer.service.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;

}
