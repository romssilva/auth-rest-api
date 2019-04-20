package auth.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import auth.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {}
