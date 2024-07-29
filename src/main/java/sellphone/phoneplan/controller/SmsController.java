package sellphone.phoneplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sellphone.phoneplan.model.SmsService;

@RestController
@RequestMapping("/sms")

public class SmsController {
	 @Autowired
	    private SmsService smsService;

	    @PostMapping("/send")
	    public String sendSms(@RequestParam String mobile, @RequestParam String message) {
	        boolean result = smsService.sendSMS(mobile, message);
	        return result ? "簡訊發送成功" : "簡訊發送失敗";
	    }

}
