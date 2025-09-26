package com.example.service;

import com.example.domain.Product;
import com.example.dto.ProductRequest;
import com.example.events.ProductCreatedEvent;
import com.example.events.ProductDeletedEvent;
import com.example.events.ProductUpdatedEvent;
import com.example.repository.ProductRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final ApplicationEventPublisher publisher;

    public ProductService(ProductRepository repo, ApplicationEventPublisher publisher) {
        this.repo = repo;
        this.publisher = publisher;
    }

    @Transactional
    public Product create(ProductRequest req) {
        Product p = new Product(null, req.name(), req.price(), req.stock());
        Product saved = repo.save(p);
        publisher.publishEvent(new ProductCreatedEvent(saved));
        return saved;
    }

    @Transactional
    public Product update(Long id, ProductRequest req) {
        Product p = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        p.setName(req.name());
        p.setPrice(req.price());
        p.setStock(req.stock());

        Product saved = repo.save(p);
        publisher.publishEvent(new ProductUpdatedEvent(saved));
        return saved;
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        repo.deleteById(id);
        publisher.publishEvent(new ProductDeletedEvent(id));
    }

    @Transactional(readOnly = true)
    public Product get(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Transactional(readOnly = true)
    public List<Product> list() {
        return repo.findAll();
    }
}
