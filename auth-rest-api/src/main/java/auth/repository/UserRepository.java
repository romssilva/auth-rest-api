package auth.repository;

import org.springframework.data.repository.CrudRepository;

import auth.model.User;

public interface UserRepository extends CrudRepository<User, Long> {}
