package com.sistema.chatbot.service;

import com.sistema.chatbot.model.Sale;
import com.sistema.chatbot.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(int id) {
        return saleRepository.findById(id);
    }

    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }
}
