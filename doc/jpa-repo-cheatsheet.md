# JPA Repository Cheatsheet

This cheatsheet lists common methods provided by Spring Data `JpaRepository` and typical derived query patterns.

## Basic CRUD

- `List<T> findAll()` — return all entities.
- `Optional<T> findById(ID id)` — find by id.
- `<S extends T> S save(S entity)` — insert or update a single entity.
- `<S extends T> List<S> saveAll(Iterable<S> entities)` — save multiple entities.
- `void deleteById(ID id)` — delete entity by id.
- `void delete(T entity)` — delete given entity.
- `void deleteAll()` — delete all entities.
- `long count()` — number of entities.
- `boolean existsById(ID id)` — whether an entity with the id exists.

## Pagination & Sorting

- `Page<T> findAll(Pageable pageable)` — paged result set.
- `List<T> findAll(Sort sort)` — sorted list.

Usage example:

```java
Pageable p = PageRequest.of(0, 20, Sort.by("name").ascending());
Page<User> page = userRepository.findAll(p);
```

## Flush & Batching

- `void flush()` — flush pending changes to the database.
- `<S extends T> S saveAndFlush(S entity)` — save and immediately flush.
- `void deleteAllInBatch()` — delete all in a single batch operation.
- `void deleteInBatch(Iterable<T> entities)` — delete given entities in batch (may be deprecated depending on Spring Data version).

## Lazy-loading helper

- `T getOne(ID id)` — returns a reference (may be lazy); prefer `getById` in newer versions.

## Derived query methods (by property name)

- `List<T> findByFirstName(String firstName)`
- `Optional<T> findOneByEmail(String email)`
- `List<T> findByAgeGreaterThan(int age)`
- `List<T> findByStatusOrderByCreatedAtDesc(String status)`
- `Page<T> findByRole(String role, Pageable pageable)`
- `long countByActive(boolean active)`
- `boolean existsByEmail(String email)`
- `void deleteByStatus(String status)`

Notes on derived queries:
- Use property names exactly as in the entity.
- Combine predicates with `And`, `Or` (e.g., `findByFirstNameAndLastName`).
- Use `Is`, `Equals`, `Like`, `Containing`, `StartingWith`, `EndingWith`, etc.

## Custom queries

- `@Query("SELECT u FROM User u WHERE u.email = :email")`
  `Optional<User> findByEmail(@Param("email") String email);`

## Return types

- `T` — single entity (watch for NoSuchElement exceptions).
- `Optional<T>` — preferred for safe single-result handling.
- `List<T>` — zero-or-more results.
- `Page<T>` / `Slice<T>` — paginated results.
- `Stream<T>` — stream results (close stream afterwards).

## Tips

- Prefer `Optional<T> findById(...)` and avoid `getOne` unless you understand lazy references.
- Use `Pageable` and `Sort` for efficient pagination and ordering.
- For complex queries, use `@Query` with JPQL or native SQL when necessary.
- Check for deprecated methods between Spring Data versions (e.g., `getOne` vs `getById`).

---
Generated quick reference — add repository-specific examples as needed.
