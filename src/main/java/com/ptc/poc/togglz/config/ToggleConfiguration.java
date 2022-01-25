package com.ptc.poc.togglz.config;

import com.mongodb.MongoClient;
import com.ptc.poc.togglz.togglz.Features;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;
import org.togglz.mongodb.MongoStateRepository;

@Configuration
public class ToggleConfiguration {

    private final StateRepository stateRepository;

    private final MongoClient mongoClient;

    @Autowired
    public ToggleConfiguration(final StateRepository stateRepository,
                               final MongoClient mongoClient) {
        this.stateRepository = stateRepository;
        this.mongoClient = mongoClient;
    }

    @Bean
    public FeatureManager featureManager() {
        final FeatureManagerBuilder featureManagerBuilder =
                new FeatureManagerBuilder()
                        .featureProvider(featureProvider())
                        .userProvider(getUserProvider())
                        .stateRepository(getStateRepository());
        return featureManagerBuilder.build();
    }

    /*
    Provide an implementation of the UserProvider to gain access to the togglz console API
     */
    private UserProvider getUserProvider() {
        return new UserProvider() {
            @Override
            public FeatureUser getCurrentUser() {
                return new SimpleFeatureUser("admin", true);
            }
        };
    }

    private FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(Features.class);
    }

    /*
    Create a state repository to keep track of the feature's state
     */
    private StateRepository getStateRepository() {
        final StateRepository repository = MongoStateRepository
                .newBuilder(mongoClient, "password-app")
                .collection("togglz")
                .build();
        return repository;
    }
}
