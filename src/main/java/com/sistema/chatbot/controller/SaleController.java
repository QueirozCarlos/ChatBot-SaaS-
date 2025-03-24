package com.sistema.chatbot.controller;

import com.sistema.chatbot.model.Sale;
import com.sistema.chatbot.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public Optional<Sale> getSaleById(@PathVariable int id) {
        return saleService.getSaleById(id);
    }

    @PostMapping
    public Sale createSale(@RequestBody Sale sale) {
        return saleService.saveSale(sale);
    }
}
