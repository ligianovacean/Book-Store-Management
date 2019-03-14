package bookStore;

import bookStore.repository.AuthorRepository;
import bookStore.service.author.AuthorService;
import bookStore.service.author.AuthorServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"bookStore.repository"})
@PropertySource(value = "classpath:application.properties")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

/*
    @Bean(name = "AuthorService")
    public AuthorService authorService(AuthorRepository repository) {
        return new AuthorServiceImpl(repository);
    }*/
}
