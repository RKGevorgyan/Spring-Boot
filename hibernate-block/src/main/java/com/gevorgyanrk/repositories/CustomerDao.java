package com.gevorgyanrk.repositories;

import com.gevorgyanrk.entities.Customer;
import com.gevorgyanrk.entities.Product;
import com.gevorgyanrk.services.FactoryBuilder;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDao {

    private FactoryBuilder factory;
    private Session session = null;

    @Autowired
    public void setFactory(FactoryBuilder factory) {
        this.factory = factory;
    }

    public Customer getCustomerById(Long id) {
        Customer customer;
        try {
            session = factory.getFactory().getCurrentSession();
            session.beginTransaction();
            customer = session.get(Customer.class, id);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return customer;
    }

    public List<Product> getCustomerProducts(Long id) {
        List<Product> tmpList = null;
        Customer customer;
        try {
            session = factory.getFactory().getCurrentSession();
            session.beginTransaction();
            customer = session.get(Customer.class, id);
            tmpList = customer.getProducts();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tmpList;
    }
}