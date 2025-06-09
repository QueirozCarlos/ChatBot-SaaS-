package com.sistema.chatbot.service;

import com.sistema.chatbot.model.Product;
import com.sistema.chatbot.model.Report;
import com.sistema.chatbot.model.Sale;
import com.sistema.chatbot.repository.ProductRepository;
import com.sistema.chatbot.repository.ReportRepository;
import com.sistema.chatbot.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    public String generateProductsCsv() {
        Iterable<Product> products = productRepository.findAll();
        StringBuilder csv = new StringBuilder();

        // Add header
        csv.append("id,Descrição,Nome,Preço,Quantidade,Categoria\n");

        // Add data rows
        for (Product product : products) {
            csv.append(String.format("%d,%s,%s,%.2f,%d,%s\n",
                    product.getId(),
                    escapeCsvField(product.getDescription()),
                    escapeCsvField(product.getName()),
                    product.getPrice(),
                    product.getStockQuantity(),
                    escapeCsvField(product.getCategoria())
            ));
        }

        return csv.toString();
    }

    public String generateSalesCsv() {
        List<Sale> sales = saleRepository.findAll();
        StringBuilder csv = new StringBuilder();

        // Add header
        csv.append("id,customer_name,product_id,quantity,sale_date,seller_name,total_value,product_name\n");

        // Add data rows
        for (Sale sale : sales) {
            csv.append(String.format("%d,%s,%d,%d,%s,%s,%.2f,%s\n",
                    sale.getId(),
                    escapeCsvField(sale.getCustomerName()),
                    sale.getProductId(),
                    sale.getQuantity(),
                    sale.getSaleDate().toString(),
                    escapeCsvField(sale.getSellerName()),
                    sale.getTotalValue(),
                    escapeCsvField(sale.getProductName())
            ));
        }

        return csv.toString();
    }

    private String escapeCsvField(String field) {
        if (field == null) return "";
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }


public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(int id) {
        return reportRepository.findById(id);
    }

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }
}
