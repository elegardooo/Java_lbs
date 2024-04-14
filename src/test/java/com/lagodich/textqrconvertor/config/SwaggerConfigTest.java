package com.lagodich.textqrconvertor.config;

import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SwaggerConfigTest {

    @Test
    void api() {
        // Create an instance of SwaggerConfig
        SwaggerConfig swaggerConfig = new SwaggerConfig();

        // Call the api() method
        Docket docket = swaggerConfig.api();

        // Assert that the returned Docket object is not null
        assertNotNull(docket);

        // TODO: Add more assertions to verify the configuration of the Docket object if needed
    }
}