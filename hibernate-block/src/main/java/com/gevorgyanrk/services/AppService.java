package com.gevorgyanrk.services;

import com.gevorgyanrk.entities.Customer;
import com.gevorgyanrk.entities.Product;
import com.gevorgyanrk.repositories.CustomerDao;
import com.gevorgyanrk.repositories.ProductDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppService {
    private CustomerDao customerDao;
    private ProductDao productDao;

    public Customer getCustomerById(Long id) {
        return customerDao.getCustomerById(id);
    }

    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }

    public List<Product> getCustomerProducts(Long id) {
        return customerDao.getCustomerProducts(id);
    }

    public List<Customer> getProductCustomers(Long id) {
        return productDao.getProductCustomers(id);
    }
}
