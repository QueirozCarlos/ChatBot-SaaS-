package com.sistema.chatbot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.chatbot.model.Product;
import com.sistema.chatbot.repository.ProductRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Hello {

    private final ChatClient chatClient;

    private final ProductRepository productRepository;

    private final ObjectMapper objectMapper;

    public Hello(ChatClient.Builder builder,ProductRepository reportRepository,ObjectMapper objectMapper) {
        this.chatClient = builder.build();
        this.productRepository = reportRepository;
        this.objectMapper = objectMapper;
    }


    @GetMapping("hello")
    public String hello()   {

        //TODO : CADASTRAR PRODUTOS VIA API PARA BUSCAR DO BANCO DE FORMA DINAMICA

        var s = formatarDadosParaIA((List<Product>) productRepository.findAll());

        return chatClient
                .prompt()
                .user("Gere APENAS (nao precise de outras instrucoes apenas a tabela pura) um relatório HTML formatado como tabela com a seguintes lista dados:\n" + s)
                .call()
                .content();

    }

    private String formatarDadosParaIA(List<Product> produts) {
        StringBuilder dados = new StringBuilder();
        dados.append("Nome | descricao | Valor | stock\n");

        for (var f : produts) {
            dados.append(f.getName()).append(" | ")
                    .append(f.getDescription()).append(" | ")
                    .append(String.format("R$ %.2f", f.getPrice())).append(" | ")
                    .append(f.getStockQuantity()).append("\n");
        }
        return dados.toString();
    }
}
