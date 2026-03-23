package ap1.briseyda.arbizu.service.impl;

import ap1.briseyda.arbizu.model.Product;
import ap1.briseyda.arbizu.repository.ProductRepository;
import ap1.briseyda.arbizu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Flux<Product> findAll() {
        log.info("Mostrando todos los datos");
        return productRepository.findAll();
    }

    // CAMBIO: El parámetro ahora es Long
    @Override
    public Mono<Product> findById(Long id) {
        log.info("Mostrando datos por id: {}", id);
        return productRepository.findById(id);
    }

    @Override
    public Mono<Product> save(Product product) {
        log.info("Registrando Datos: {}", product);
        product.setEstado(true); // Siempre inicia activo
        return productRepository.save(product);
    }

    @Override
    public Mono<Product> update(Product product) {
        log.info("Actualizando Datos: {}", product);
        // Aquí no forzamos el estado a true por si ya estaba desactivado
        return productRepository.save(product);
    }

    @Override
public Mono<Product> delete(Long id) {
    log.info("Eliminando lógicamente id: {}", id);
    return productRepository.findById(id)
            // Si el ID no existe en Neon, lanzamos este error:
            .switchIfEmpty(Mono.error(new RuntimeException("No se encontró el producto con ID: " + id)))
            .flatMap(product -> {
                product.setEstado(false);
                return productRepository.save(product);
            });
}

@Override
public Mono<Product> restore(Long id) {
    log.info("Restaurando id: {}", id);
    return productRepository.findById(id)
            // Lo mismo aquí: si no existe, que nos avise
            .switchIfEmpty(Mono.error(new RuntimeException("No se encontró el producto con ID: " + id)))
            .flatMap(product -> {
                product.setEstado(true);
                return productRepository.save(product);
            });
}

}