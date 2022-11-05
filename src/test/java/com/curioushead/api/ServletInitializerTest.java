package com.curioushead.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServletInitializerTest {

    @Mock
    private SpringApplicationBuilder springApplicationBuilder;

    @Test
    public void configure() {
        ServletInitializer servletInitializer = new ServletInitializer();
        when(springApplicationBuilder.sources(ApiApplication.class)).thenReturn(springApplicationBuilder);

        SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);

        verify(springApplicationBuilder).sources(ApiApplication.class);
        assertEquals(springApplicationBuilder,result);
    }
}