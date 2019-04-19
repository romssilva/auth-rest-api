package auth.repository;

import org.springframework.data.repository.CrudRepository;

import auth.model.Session;

public interface SessionRepository extends CrudRepository<Session, Long> {}