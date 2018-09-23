package com.tutorial.elasticsearch.movie;

import com.tutorial.elasticsearch.movie.config.ElasticsearchConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ElasticsearchConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}
