package auth.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auth.model.User;
import auth.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	private final String API_VERSION = "/v1";
	
	@GetMapping(API_VERSION + "/users")
    public Page<User> getAllUsers(
    		@RequestParam(value="pageNumber", required=false, defaultValue="0") int pageNumber,
    		@RequestParam(value="pageSize", required=false, defaultValue="10") int pageSize
		) {
		return userRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
	
	@GetMapping(API_VERSION + "/users/{id}")
    public User getUser(@PathVariable Long id, HttpServletResponse response) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			return user.get();
		}
		
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null;
    }
	
	@PostMapping(API_VERSION + "/users")
    public User addUser(@RequestBody User newUser, HttpServletResponse response) {
		User user = userRepository.save(newUser);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return user;
    }
	
	@PutMapping(API_VERSION + "/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser, HttpServletResponse response) {
		if (!userRepository.existsById(id)) {
			response.setStatus(HttpServletResponse.SC_CREATED);
		}
		return userRepository.save(newUser);
    }
	
	@DeleteMapping(API_VERSION + "/users/{id}")
    public void deleteUser(@PathVariable Long id, HttpServletResponse response) {
		userRepository.deleteById(id);
    }
}
