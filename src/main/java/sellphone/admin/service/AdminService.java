package sellphone.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adrepo;
	
	public boolean checkAdminLogin(String account, String password) {
		
		Admin admin = adrepo.findByAdminAccount(account);
		if (admin != null) {
			if(admin.getAdminPassword().equals(password)) {
				return true;
			}
		}
		
		return false;
	}
	
}
