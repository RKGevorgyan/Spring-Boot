package com.gevorgyanrk.repositories;

import com.gevorgyanrk.entities.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductDao {
    private SessionFactory factory;
    private Session session = null;

    @PostConstruct
    public void init() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
        add(new Product("MacBook", "Ultra low and Great Power", new BigDecimal(3000)));
        add(new Product("iPhone", "The most expensive phone by credit", new BigDecimal(1000)));
        add(new Product("iPad", "More size - more cost", new BigDecimal(2000)));
    }

    @PreDestroy
    public void close() {
        factory.close();
    }

    public void add(Product product) {
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void update(Product product) {
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Product tmpProduct = session.get(Product.class, product.getId());
            tmpProduct.setDescription(product.getDescription());
            tmpProduct.setName(product.getName());
            tmpProduct.setPrice(product.getPrice());
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void remove(long id) {
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public Product findById(long id) {
        Product product = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
            return product;
        }
    }

    public List<Product> findAll() {
        List<Product> list = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            list = session.createQuery("FROM Product", Product.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
            return list;
        }
    }
}
