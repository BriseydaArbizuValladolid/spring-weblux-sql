package ap1.briseyda.arbizu.service;

import ap1.briseyda.arbizu.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    
    Flux<Product> findAll();

    Mono<Product> findById(Long id);

    Mono<Product> save(Product product);

    Mono<Product> update(Product product);

    Mono<Product> delete(Long id);
    Mono<Product> restore(Long id);
    
}
