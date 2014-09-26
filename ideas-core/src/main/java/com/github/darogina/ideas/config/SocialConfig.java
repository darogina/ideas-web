package com.github.darogina.ideas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

//    private static final String GOOGLE_CONFUSER_KEY    = "214461244306-1oap90sj3aut7nm8941t1iqvml7cglcr.apps.googleusercontent.com";
//    private static final String GOOGLE_CONSUMER_SECRET = "Y4FyYjK7WyjlIYA7LafvLQ1r";
//
//    @Inject
//    private Environment environment;
//
    @Inject
    private DataSource dataSource;

    /**
     * When a new provider is added to the app, register its {@link org.springframework.social.connect.ConnectionFactory} here.
     * @see GoogleConnectionFactory
     */
//    @Bean
//    public ConnectionFactoryLocator connectionFactoryLocator() {
//        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
//        registry.addConnectionFactory(new GoogleConnectionFactory(environment.getProperty("google.clientId"),
//                environment.getProperty("google.clientSecret")));
//        return registry;
//    }

    /**
     * Singleton data access object providing access to connections across all users.
     */
//    @Bean
//    public UsersConnectionRepository usersConnectionRepository() {
//        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
//                connectionFactoryLocator(), Encryptors.noOpText());
//        repository.setConnectionSignUp(new SimpleConnectionSignUp());
//        return repository;
//    }

    /**
     * Request-scoped data access object providing access to the current user's connections.
     */
//    @Bean
//    @Scope(value="request", proxyMode= ScopedProxyMode.INTERFACES)
//    public ConnectionRepository connectionRepository() {
//        User user = SecurityContext.getCurrentUser();
//        return usersConnectionRepository().createConnectionRepository(user.getId());
//    }

    /**
     * A proxy to a request-scoped object representing the current user's primary Facebook account.
     * @throws org.springframework.social.connect.NotConnectedException if the user is not connected to facebook.
     */
//    @Bean
//    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
//    public Google google() {
//        return connectionRepository().getPrimaryConnection(Google.class).getApi();
//    }

    /**
     * The Spring MVC Controller that allows users to sign-in with their provider accounts.
     */
//    @Bean
//    public ProviderSignInController providerSignInController() {
//        return new ProviderSignInController(connectionFactoryLocator(), usersConnectionRepository(),
//                new SimpleSignInAdapter());
//    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(new GoogleConnectionFactory(
                environment.getProperty("google.clientId"),
                environment.getProperty("google.clientSecret")));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    }
}
