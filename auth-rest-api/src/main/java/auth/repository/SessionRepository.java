package auth.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import auth.model.Session;

public interface SessionRepository extends PagingAndSortingRepository<Session, Long> {}