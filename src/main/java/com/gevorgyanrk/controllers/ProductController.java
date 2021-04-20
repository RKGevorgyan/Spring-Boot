package com.gevorgyanrk.controllers;

import com.gevorgyanrk.entities.Product;
import com.gevorgyanrk.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product_views/index";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id,
                              Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_views/product_form";
    }

    @PostMapping("/product_update")
    public String updateProduct(Product product) {
        productRepository.update(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        long l = 1L;
        if (!(productRepository.getMaxId() == 0)) {
            l = productRepository.getMaxId() + 1;
        }
        model.addAttribute("product", new Product(l, null, null, null));
        return "product_views/product_form";
    }

    @GetMapping("/delete/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productRepository.remove(id);
        return "redirect:/product";
    }
}
