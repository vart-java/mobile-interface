package com.vart.mobileinterface.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "com.vart.mobileinterface.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Value("${mongo.db.connection.url}")
    private String mongoDbConnectionUrl;
    @Value("${mongo.db.database}")
    private String database;
    @Value("${mongo.db.user}")
    private String user;
    @Value("${mongo.db.password}")
    private String password;

    @Override
    protected String getDatabaseName() {
        return "mobileinterface";
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoDbConnectionUrl);
        MongoCredential mongoCredential = MongoCredential.createCredential(
                user,
                database,
                password.toCharArray());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .credential(mongoCredential)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("com.vart.mobileinterface");
    }
}