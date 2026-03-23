package ap1.briseyda.arbizu.rest;

import ap1.briseyda.arbizu.model.Product;
import ap1.briseyda.arbizu.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/product")

public class ProductRest {
    private final ProductService productService;

    @Autowired
    public ProductRest(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    public Flux<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> findById(@PathVariable Long id) {
        return productService.findById(id);
    }
    @PostMapping("/save")
    public Mono<Product> save(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/update/{id}")
    public Mono<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return productService.findById(id) // Primero verificamos que exista
                .flatMap(existingProduct -> {
                    product.setId(id); // Forzamos el ID de la URL al objeto
                    return productService.update(product);
                });
    }

    @PatchMapping("/delete/{id}")
    public Mono<Product> delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    @PatchMapping("/restore/{id}")
    public Mono<Product> restore(@PathVariable Long id) {
        return productService.restore(id);
    }
}
