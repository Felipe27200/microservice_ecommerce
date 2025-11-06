package org.ecommerce.customer.customer.service.repository;

import org.ecommerce.customer.customer.service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long>
{
}
