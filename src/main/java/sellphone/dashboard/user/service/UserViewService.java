package sellphone.dashboard.user.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sellphone.dashboard.user.model.UserView;
import sellphone.dashboard.user.model.UserViewRepository;


@Service
public class UserViewService {

	@Autowired
	private UserViewRepository userViewRepo;
	
	public UserView findById(String id) {
		UserView userView = userViewRepo.findByUserId(id);
		return userView;
	}
	public List<UserView> findAll() {
		List<UserView> userViewList = userViewRepo.findAll();
		if (userViewList.isEmpty()) {
			userViewList = null;
		}
		
		return userViewList;
	}
}
