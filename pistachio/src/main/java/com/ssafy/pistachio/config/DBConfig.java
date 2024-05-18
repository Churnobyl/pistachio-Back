package com.ssafy.pistachio.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

@Configuration
@MapperScan(basePackages = "com.ssafy.pistachio.model.dao")
public class DBConfig {
    @Bean
    public ApplicationRunner initializeDatabase(DataSource dataSource, ResourceLoader resourceLoader) {
        return args -> {
            Resource resource = resourceLoader.getResource("classpath:schema.sql");
            try (Connection connection = dataSource.getConnection()) {
                List<String> sqlLines = Files.readAllLines(Paths.get(resource.getURI()));
                try (Statement statement = connection.createStatement()) {
                    StringBuilder sqlBuilder = new StringBuilder();
                    boolean inBlockComment = false;
                    for (String line : sqlLines) {
                        line = line.trim();
                        if (line.startsWith("--") || line.isEmpty()) {
                            // Skip single line comments and empty lines
                            continue;
                        }
                        if (line.startsWith("/*")) {
                            // Start of block comment
                            inBlockComment = true;
                            continue;
                        }
                        if (line.endsWith("*/")) {
                            // End of block comment
                            inBlockComment = false;
                            continue;
                        }
                        if (inBlockComment) {
                            // Inside block comment, skip the line
                            continue;
                        }
                        sqlBuilder.append(line);
                        if (line.endsWith(";")) {
                            // Execute the SQL statement
                            statement.execute(sqlBuilder.toString());
                            sqlBuilder.setLength(0); // Reset the builder for the next statement
                        }
                    }
                }
            }
        };
    }
}
