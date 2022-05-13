package com.example.demo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

@Configuration
public class AuditingServiceConfiguration {

    private final ConfigurableBeanFactory beanFactory;

    public AuditingServiceConfiguration(ConfigurableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @PostConstruct
    void registerServices() {
        Stream.of(DocImpl1.class, DocImpl2.class)
                .forEach(aClass ->
                        beanFactory.registerSingleton(aClass.getName(),
                                new AuditingService<>(
                                        getRepoByClass(aClass),
                                        aClass)));
    }

    private Repo<? extends SomeDoc> getRepoByClass(Class<? extends SomeDoc> aClass) {
        if (aClass.equals(DocImpl1.class)) {
            return new RepoImpl1();
        } else {
            return new RepoImpl2();
        }
    }
}
