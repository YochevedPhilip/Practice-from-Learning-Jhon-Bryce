package app.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.User;
import app.core.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository; 
	
	public User addUser(User user) {
		return userRepository.save(user);
	} 
	
	public User getUser(int id) {
		return userRepository.findById(id).get();
	} 
	
	public void removeUser(int id) {
		userRepository.deleteById(id);
	}

}
