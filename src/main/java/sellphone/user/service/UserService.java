package sellphone.user.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sellphone.user.model.UserRepository;
import sellphone.user.model.UserViewRepository;
import sellphone.user.model.Users;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	

	public Users findById(String id) {
		Optional<Users> optional = userRepo.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public void insert(Users user) {
		userRepo.save(user);
	}

	public Users checkLogin(String account, String password) {

		Users user1 = userRepo.findByUserAccount(account);		
		if (user1 != null ) {
			if (user1.getPassword().equals(password)) {
				user1.setPrevlogTime(LocalDateTime.now());
				return user1;
			}
		} 

		// By contactNum
		Users user2 = userRepo.findByContactNum(account);
		if (user2 != null) {
			if (user2.getPassword().equals(password)) {
				user2.setPrevlogTime(LocalDateTime.now());
				return user2;
			}
		}
		
		return null;
	}
	
	
	public void update(Users updateUser) {
		Optional<Users> optional = userRepo.findById(updateUser.getUserId());

		if (optional.isPresent()) {
			Users user = optional.get();
			user.setUserName(updateUser.getUserName());
			user.setUserAccount(updateUser.getUserAccount());
			user.setPassword(updateUser.getPassword());
			user.setEmail(updateUser.getEmail());
			user.setContactNum(updateUser.getContactNum());
			user.setBirthday(updateUser.getBirthday());
			user.setStatus(updateUser.getStatus());
			user.setCreateTime(updateUser.getCreateTime());
			user.setPrevlogTime(updateUser.getPrevlogTime());

			userRepo.save(user);
		}
	}

	public void delete(String userId) {
		Optional<Users> optional = userRepo.findById(userId);

		if (optional.isPresent()) {
			Users user = optional.get();
			user.setStatus(-2);
			userRepo.save(user);
		}
	}



}
