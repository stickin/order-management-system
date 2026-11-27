package io.github.stickin.oms.product.service;

import io.github.stickin.oms.product.dto.ProductRequest;
import io.github.stickin.oms.product.dto.ProductResponse;
import io.github.stickin.oms.product.mapper.ProductMapper;
import io.github.stickin.oms.product.model.Product;
import io.github.stickin.oms.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse create(final ProductRequest request) {
        final Product product = productMapper.toEntity(request);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(final UUID id) {
        return productMapper.toResponse(getProduct(id));
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .toList();
    }

    public ProductResponse update(final UUID id, final ProductRequest request) {
        final Product product = getProduct(id);
        productMapper.updateEntity(request, product);
        return productMapper.toResponse(productRepository.save(product));
    }

    public void delete(final UUID id) {
        productRepository.delete(getProduct(id));
    }

    private Product getProduct(final UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + id));
    }
}
