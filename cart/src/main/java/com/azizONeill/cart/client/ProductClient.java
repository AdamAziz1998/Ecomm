package com.azizONeill.cart.client;

import com.azizONeill.cart.dto.ProductDTO;
import com.azizONeill.cart.dto.ProductVariantDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@ConfigurationProperties(prefix = "application.config.product-url")
@FeignClient(name = "product-service", url = "${application.config.product-url}")
public interface ProductClient {

    @GetMapping("/product/{productId}")
    ProductDTO findProductById(@PathVariable("productId") UUID productId);

    @GetMapping("/productVariant/{productVariantId}")
    ProductVariantDTO findProductVariantById(@PathVariable("productVariantId") UUID productVariantId);
}
