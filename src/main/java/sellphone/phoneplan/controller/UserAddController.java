package sellphone.phoneplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.service.PhoneplanService;

@RestController
@RequestMapping("/api/phoneplans")
public class UserAddController {

    @Autowired
    private PhoneplanService phonePlanService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<PhonePlanBean> addPhonePlanToUser(@PathVariable String userId, @RequestBody PhonePlanBean phonePlanBean) {
        PhonePlanBean savedPhonePlan = phonePlanService.addPhonePlanToUser(userId, phonePlanBean);
        return ResponseEntity.ok(savedPhonePlan);
    }
}
