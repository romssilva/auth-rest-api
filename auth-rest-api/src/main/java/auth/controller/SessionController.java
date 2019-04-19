package auth.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import auth.model.Session;
import auth.repository.SessionRepository;

@RestController
public class SessionController {
	
	@Autowired
	SessionRepository sessionRepository;
	
	private final String API_VERSION = "/v1";
	
	@GetMapping(API_VERSION + "/sessions")
    public List<Session> getAllSessions() {
		return (List<Session>) sessionRepository.findAll();
    }
	
	@GetMapping(API_VERSION + "/sessions/{id}")
    public Session getSession(@PathVariable Long id, HttpServletResponse response) {
		Optional<Session> session = sessionRepository.findById(id);
		
		if (session.isPresent()) {
			return session.get();
		}
		
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null;
    }
	
	@PostMapping(API_VERSION + "/sessions")
    public Session addSession(@RequestBody Session newSession, HttpServletResponse response) {
		Session session = sessionRepository.save(newSession);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return session;
    }
	
	@PutMapping(API_VERSION + "/sessions/{id}")
    public Session updateSession(@PathVariable Long id, @RequestBody Session newSession, HttpServletResponse response) {
		if (!sessionRepository.existsById(id)) {
			response.setStatus(HttpServletResponse.SC_CREATED);
		}
		return sessionRepository.save(newSession);
    }
	
	@DeleteMapping(API_VERSION + "/sessions/{id}")
    public void deleteSession(@PathVariable Long id, HttpServletResponse response) {
		sessionRepository.deleteById(id);
    }
}
