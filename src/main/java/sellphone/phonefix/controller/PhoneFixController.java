package sellphone.phonefix.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.transaction.Transactional;
import sellphone.phonefix.model.PhoneFixBean;
import sellphone.phonefix.model.PhoneFixRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PhoneFixController {
	@Autowired
	private PhoneFixRepository rp;

	@GetMapping("/DashBoard/phonefixs")
	public String getMethodName(Model model) {
		List<PhoneFixBean> list = rp.findAll();
		model.addAttribute("list", list);
		return "phonefix/list";
	}

	@GetMapping("/DashBoard/phonefixs/update")
	public String selectone(@RequestParam Integer fixid, Model model) {
		Optional<PhoneFixBean> phonfix = rp.findById(fixid);
		PhoneFixBean phone = phonfix.get();
		model.addAttribute("phone", phone);
		return "phonefix/updateForm";
	}

	@PostMapping("/DashBoard/phonefixs/save")
	@Transactional
	public String save(@RequestParam Integer fixID, @RequestParam String fixName, @RequestParam String fixDate,
			@RequestParam String fixCost, @RequestParam String fixPort, @RequestParam String fixState) {
//    	System.out.println("這裡是ID "+fixID);
//    	System.out.println("這裡是cost " +fixCost);
//    	System.out.println("這裡是port " +fixPort);
//    	System.out.println("這裡是name " +fixName);
//    	System.out.println("----------------------------------------------------------------------------");
		Optional<PhoneFixBean> phonfix = rp.findById(fixID);
		PhoneFixBean phone = phonfix.get();
//        PhoneFixBean phoneFixBean = new PhoneFixBean();        
		phone.setFixName(fixName);
		phone.setFixCost(fixCost);
		phone.setFixPort(fixPort);
		phone.setFixState(fixState);
		rp.save(phone);

		return "redirect:/DashBoard/phonefixs";
	}

	@GetMapping("/DashBoard/phonefixs/create1")
	public String creare1() {
		return "phonefix/form";
	}

	@PostMapping("/DashBoard/phonefixs/create")
	@Transactional
	public String create(@RequestParam String fixName, @RequestParam String fixDate, @RequestParam String fixCost,
			@RequestParam String fixPort, @RequestParam String fixState) {
		PhoneFixBean phoneFixBean = new PhoneFixBean();
		phoneFixBean.setFixName(fixName);
		phoneFixBean.setFixDate(fixDate);
		phoneFixBean.setFixCost(fixCost);
		phoneFixBean.setFixPort(fixPort);
		phoneFixBean.setFixState(fixState);
		rp.save(phoneFixBean);
		return "redirect:/DashBoard/phonefixs";
	}

	@PostMapping("/DashBoard/phonefixs/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		rp.deleteById(id);
		return "redirect:/DashBoard/phonefixs";
	}
}
