package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    private static final int PAGE_SIZE = 5;

    @GetMapping
    public String showAllProducts(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page, @RequestParam(required = false) Float min, @RequestParam(required = false) Float max) {
        if (page < 1) {
            page = 1;
        }
        if (min != null & max == null) {
            model.addAllAttributes("products", productService.getMaxPrice(page - 1, PAGE_SIZE, min));
        } else if (min == null & max != null) {
            model.addAllAttributes("products", productService.getMinPrice(page - 1, PAGE_SIZE, max));
        } else if (min != null & max != null) {
            model.addAllAttributes("products", productService.getMinAndMaxPrice(page - 1, PAGE_SIZE, min, max));
        } else {
            model.addAttribute("products", productService.findAll(page - 1, 5));
        }
        return "products";
    }

    @GetMapping("/filter_product")
    public String filterProductByPrice(@RequestParam Float min, @RequestParam Float max) {
        if (min != null & max == null) {
            return "redirect:/products?min=" + min;
        } else if (min == null & max != null) {
            return "redirect:/products?max=" + max;
        } else {
            return "redirect:/products";
        }
    }





    @GetMapping("/{id}")
    @ResponseBody
    public Product getOneProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists"));
    }
}
