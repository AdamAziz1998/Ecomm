package com.azizONeill.cart.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(info =
@Info(
        title = "Product Service",
        version = "0.0",
        description = "Documentation for the products of the online store application",
        license = @License(name = "Apache 2.0", url = "http://foo.bar"),
        contact = @Contact(name = "Adam O'Neill", email = "adamaoneill@gmail.com")
)
)
public class APIDocConfig {
}
