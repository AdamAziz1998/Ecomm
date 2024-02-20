package com.azizONeill.cart.client;

import com.azizONeill.cart.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "product-service", url = "${application.config.product-url}")
public interface ProductClient {

    @GetMapping("/products/{productId}")
    List<ProductDTO> findProductById(@PathVariable("productId") UUID productId);
}
