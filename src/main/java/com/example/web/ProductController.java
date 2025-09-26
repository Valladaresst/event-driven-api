package com.example.web;

import com.example.domain.Product;
import com.example.dto.ApiResponse;
import com.example.dto.ProductRequest;
import com.example.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    public ProductController(ProductService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> create(@RequestBody @Valid ProductRequest req) {
        Product saved = service.create(req);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location)
                .body(ApiResponse.ok("Agregado exitosamente", saved));
    }

    @GetMapping
    public ApiResponse<List<Product>> list() {
        return ApiResponse.ok("Listado de productos", service.list());
    }

    @GetMapping("/{id}")
    public ApiResponse<Product> get(@PathVariable Long id) {
        return ApiResponse.ok("Consulta exitosa", service.get(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<Product> update(@PathVariable Long id, @RequestBody @Valid ProductRequest req) {
        return ApiResponse.ok("Actualizado correctamente", service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        // 200 OK para poder enviar mensaje (si prefieres 204, no devuelve body)
        return ResponseEntity.ok(ApiResponse.ok("Borrado exitosamente", null));
    }
}
