package com.codegym.pds.controller;

import com.codegym.pds.model.Product;
import com.codegym.pds.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    public IProductService productService;


    @GetMapping("/")
    public ModelAndView listProduct(){
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }
    @GetMapping("/create-product")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("products", new Product());
        return modelAndView;
    }
    @PostMapping("/create-product")
    public ModelAndView saveProduct(@ModelAttribute("products") Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("products", new Product());
        modelAndView.addObject("message", "SUCCESS!!!");
        return modelAndView;
    }
    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("products", product.get());
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }
    @PostMapping("/edit-product")
    public ModelAndView editProduct(@ModelAttribute("products") Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("products", product);
        modelAndView.addObject("message", "SUCCESS!!!");
        return modelAndView;
    }
    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("delete");
            modelAndView.addObject("products", product.get());
            return modelAndView;

        } else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("/delete-product")
    public String deleteCustomer(@ModelAttribute("products") Product product) {
         productService.remove(product.getId());
         return "redirect:/";
    }
}

