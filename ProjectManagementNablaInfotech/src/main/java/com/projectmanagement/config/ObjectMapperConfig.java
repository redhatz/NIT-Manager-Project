package com.projectmanagement.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Configuration
@ComponentScan(basePackages = {"com.projectmanagement"})
public class ObjectMapperConfig {

	@Bean
    @Primary
    public ObjectMapper objectMapper() {
           ObjectMapper mapper =
                        new ObjectMapper()
                        .registerModule(new ParameterNamesModule())
                        .registerModule(new Jdk8Module())
                        .registerModule(new JavaTimeModule());
           mapper.setDateFormat(new StdDateFormat());
           mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
           mapper.setSerializationInclusion(Include.NON_NULL);
           mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
           mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

           return mapper;
    }
}
