package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import entity.User;
import repositories.Userrepository;

@Service
public class UserService {
	private Userrepository UserRepository;

	public List<User> allUsers() {
		List<User> users = new ArrayList<>();
		UserRepository.findAll().forEach(users::add);
		return users;
	}

}
