package sellphone.dashboard.user.service;

import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service
public class UserUtil {

	
	public String createUserId(String sessionId) {
//	public String createUserId() {

		java.util.Date date = new java.util.Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String year = Integer.toString(cal.get(Calendar.YEAR)).substring(2);
		
		System.out.println();
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		
//		int min = cal.get(Calendar.MINUTE);
//		int sec = cal.get(Calendar.SECOND);

//		String formattedTime = String.format("%02d%02d%02d", month, day, hour);
		String formattedTime = String.format("%02d%02d", month, day);

		String id = year + formattedTime + sessionId.substring(sessionId.length()-4);
//		String id = year + formattedTime;

		return id;
	}
	
	
}
