package com.example.demo;

public class AuditingService<T extends SomeDoc> {
    private final Repo<T> repo;
    private final Class<T> clazz;

    public AuditingService(Repo<T> repo, Class<T> clazz) {
        this.repo = repo;
        this.clazz = clazz;
    }
}
