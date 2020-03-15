package app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
    }

    @Bean
    public ModelMapper modelMapper() {
        return modelMapper;
    }
}
