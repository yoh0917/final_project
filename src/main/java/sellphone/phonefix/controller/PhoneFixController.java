package sellphone.phonefix.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import sellphone.phonefix.model.PhoneFixBean;
import sellphone.phonefix.model.PhoneFixPhotoBean;
import sellphone.phonefix.model.PhoneFixPhotoRepository;
import sellphone.phonefix.model.PhoneFixRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PhoneFixController {
	@Autowired
	private PhoneFixRepository rp;
	@Autowired
	private PhoneFixPhotoRepository photorp;

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

//
//    @ResponseBody
//    @PostMapping("/DashBoard/phonefixs/create")
//    @Transactional
//    public String addHouseSend(
//            @RequestParam("fixName") String fixName,
//            @RequestParam("fixDate") String fixDate,
//            @RequestParam("fixCost") String fixCost,
//            @RequestParam("fixPort") String fixPort,
//            @RequestParam("fixState") String fixState,
//            @RequestParam("file") MultipartFile[] files) throws IOException {
//
//        System.out.println("有進入controller");
//
//        PhoneFixBean fixbean = new PhoneFixBean();
//        fixbean.setFixName(fixName);
//        fixbean.setFixDate(fixDate);
//        fixbean.setFixCost(fixCost);
//        fixbean.setFixPort(fixPort);
//        fixbean.setFixState(fixState);
//
//        List<PhoneFixPhotoBean> phoneFixPhotoBeanList = new ArrayList<>();
//
//        for (MultipartFile oneFile : files) {
//            PhoneFixPhotoBean phoneFixPhotoBean = new PhoneFixPhotoBean();
//            phoneFixPhotoBean.setPhotoFile(oneFile.getBytes());
//            phoneFixPhotoBean.setFixbean(fixbean);
//            phoneFixPhotoBeanList.add(phoneFixPhotoBean);
//        }
//
//        fixbean.setFixPhotoBean(phoneFixPhotoBeanList);
//
//        rp.save(fixbean);
//
//        return "上傳OK!!";
//    }
	@PostMapping("/DashBoard/phonefixs/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		rp.deleteById(id);
		return "redirect:/DashBoard/phonefixs";
	}

	// 下面為圖片的controller
//	@PostMapping("/DashBoard/phonefixs/create")
//	@Transactional
//	public String create(
//			@RequestParam String fixName, 
//			@RequestParam String fixDate, 
//			@RequestParam String fixCost,
//			@RequestParam String fixPort, 
//			@RequestParam String fixState) {
//		PhoneFixBean phoneFixBean = new PhoneFixBean();
//    	System.out.println("這裡是cost " +fixCost);
//    	System.out.println("這裡是port " +fixPort);
//  	System.out.println("這裡是name " +fixName);
//    	System.out.println("----------------------------------------------------------------------------");
//		phoneFixBean.setFixName(fixName);
//		phoneFixBean.setFixDate(fixDate);
//		phoneFixBean.setFixCost(fixCost);
//		phoneFixBean.setFixPort(fixPort);
//		phoneFixBean.setFixState(fixState);
//		rp.save(phoneFixBean);
//		return "redirect:/DashBoard/phonefixs";
//	}
	@Transactional
	@ResponseBody
	@PostMapping("/DashBoard/phonefixs/addphoto")
	public String addHouseSend(@RequestParam String fixName, @RequestParam String fixDate, @RequestParam String fixCost,
			@RequestParam String fixPort, @RequestParam String fixState, @RequestParam("file") MultipartFile[] files)
			throws IOException {
		System.out.println("有進入controller");
		PhoneFixBean fixbean = new PhoneFixBean();
		fixbean.setFixName(fixName);
		fixbean.setFixDate(fixDate);
		fixbean.setFixCost(fixCost);
		fixbean.setFixPort(fixPort);
		fixbean.setFixState(fixState);
        rp.save(fixbean);
		List<PhoneFixPhotoBean> phoneFixPhotoBeanList = new ArrayList<>();

		for (MultipartFile oneFile : files) {
			PhoneFixPhotoBean phoneFixPhotoBean = new PhoneFixPhotoBean();
			phoneFixPhotoBean.setPhotoFile(oneFile.getBytes());
			phoneFixPhotoBean.setFixbean(fixbean); // 多個照片set到一個houseid

			phoneFixPhotoBeanList.add(phoneFixPhotoBean);
		}

		fixbean.setFixPhotoBean(phoneFixPhotoBeanList); // 1個houseid set 到多個照片

		rp.save(fixbean);

		return "上傳OK!!";
	}
//	@PostMapping("/DashBoard/phonefixs/upload")
//	public String uploadPhoto(@RequestParam("fixID") Integer fixID, @RequestParam("file") MultipartFile file) throws IOException {
//	    Optional<PhoneFixBean> optionalPhoneFix = rp.findById(fixID);
//	    if (optionalPhoneFix.isPresent()) {
//	        PhoneFixBean phoneFixBean = optionalPhoneFix.get();
//	        PhoneFixPhotoBean photoBean = new PhoneFixPhotoBean();
//	        photoBean.setPhotoFile(file.getBytes());
//	        photoBean.setFixbean(phoneFixBean);
//	        photorp.save(photoBean);
//	    }
//	    return "redirect:/DashBoard/phonefixs";
//	}

//	@PostMapping("/DashBoard/phonefixs/delete/{id}")
//	public String delete(@PathVariable("id") int id) {
//		rp.deleteById(id);
//		return "redirect:/DashBoard/phonefixs";
//	}
	// 下面為圖片的controller
//	@PostMapping("/DashBoard/phonefixs/upload")
//	public String uploadPhoto(@RequestParam("fixID") Integer fixID, @RequestParam("file") MultipartFile file) throws IOException {
//	    Optional<PhoneFixBean> optionalPhoneFix = rp.findById(fixID);
//	    if (optionalPhoneFix.isPresent()) {
//	        PhoneFixBean phoneFixBean = optionalPhoneFix.get();
//	        PhoneFixPhotoBean photoBean = new PhoneFixPhotoBean();
//	        photoBean.setPhotoFile(file.getBytes());
//	        photoBean.setFixbean(phoneFixBean);
//	        photorp.save(photoBean);
//	    }
//	    return "redirect:/DashBoard/phonefixs";
//	}

	// http://localhost:8081/DashBoard/phonefixs/addphoto

}
