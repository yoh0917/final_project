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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import sellphone.dashboard.user.model.Users;
import sellphone.phonefix.model.PhoneFixBean;
import sellphone.phonefix.model.PhoneFixPhotoBean;
import sellphone.phonefix.model.PhoneFixPhotoRepository;
import sellphone.phonefix.model.PhoneFixRepository;
import sellphone.phoneplan.model.UsersRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class PhoneFixController {
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private PhoneFixRepository rp;
	@Autowired
	private PhoneFixPhotoRepository photorp;

//後臺主頁秀出整體資料
	@GetMapping("/DashBoard/phonefixs")
	public String getMethodName(Model model) {
		List<PhoneFixBean> list = rp.findAll();
		model.addAttribute("list", list);
		return "phonefix/list";
	}

	
//後臺取得對應之修改資料
	@GetMapping("/DashBoard/phonefixs/update")
	public String selectone(@RequestParam Integer fixid, Model model) {
		Optional<PhoneFixBean> phonfix = rp.findById(fixid);
		PhoneFixBean phone = phonfix.get();
		model.addAttribute("phone", phone);
		return "phonefix/updateForm";
	}

	
//後臺儲存修改資料
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


	@PostMapping("/DashBoard/phonefixs/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		rp.deleteById(id);
		return "redirect:/DashBoard/phonefixs";
	}
	
	
//後臺新增資料
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

		return "訂單新增成功!!";
	}
	
	
	
	//拿到fixphotoid
	@ResponseBody
	@GetMapping("/phonefixs/fixphotoid")
	public List<Integer> findfixphoto(@RequestParam Integer fixID) {
		Optional<PhoneFixBean> optional =rp.findById(fixID);
		if (optional.isPresent()) {
			PhoneFixBean phoneFixBean = optional.get();
			List<PhoneFixPhotoBean> phoneFixPhotoBean = phoneFixBean.getFixPhotoBean();
			List<Integer> idList = new ArrayList<>();
			for (PhoneFixPhotoBean onePhoto : phoneFixPhotoBean) {
				Integer oneId = onePhoto.getId();
				idList.add(oneId);
			}
			return idList;
		}
		return null;

	}
	
	
	//將圖片丟入此phonefix
	@GetMapping("/phonefixs/downloadfixphoto")
	public ResponseEntity<?> downloadfixphoto(@RequestParam Integer id) {
		System.out.println(id);
		Optional<PhoneFixPhotoBean> optional = photorp.findById(id);
		if (optional.isPresent()) {
			PhoneFixPhotoBean phoneFixPhotoBean  = optional.get();
			byte[] photoFile = phoneFixPhotoBean.getPhotoFile();
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(photoFile);
		}

		return ResponseEntity.notFound().build();
	}
	
	//前台主頁面新增
	@GetMapping("/phonefixs/userlist")
	public String userlist() {
	    return "phonefix/userlist"; // 这里的 "userlist" 是指你的模板文件名，如 userlist.html
	}
	
//前台導向新增表單的頁面
	@GetMapping("/phonefixs/frontform1")
	public String frontform1(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		Users wanted = new Users();
		Optional<Users> users = userRepository.findById(userId);
		wanted = users.get();
		model.addAttribute("GGA", wanted);
		return "phonefix/userform";
	}
	//前台新增表單
	@Transactional
	@ResponseBody
	@PostMapping("/phonefixs/frontform")
	public String frontform(
			@RequestParam String userId, 
			@RequestParam String fixName, 
			@RequestParam String fixDate,
			@RequestParam String fixCost,
			@RequestParam String fixPort,
			@RequestParam String fixState,
			@RequestParam("file") MultipartFile[] files,
			HttpServletRequest request,Model model)
			throws IOException {
		HttpSession session = request.getSession();
		System.out.println("-----------------------------------------------------------");
		String useId = (String) session.getAttribute("userId");
		//Users user = (String) session.getAttribute("userId");
		Users users = userRepository.findById(useId).orElseThrow(() -> new RuntimeException("User not found"));
//		users.setUserId(userId);
		model.addAttribute("user",users);
		PhoneFixBean fixbean = new PhoneFixBean();
		fixbean.setUser(users);
		fixbean.setFixName(users.getUserName());
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

		return "訂單新增成功!!";
	}
//	@PostMapping("/ticketmart/api/goods")
//	public String addCart(@RequestParam("id") Integer id, HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		Integer memberId = (Integer) session.getAttribute("memberid");
//		System.out.println("這裡是ticketmart/api/goods:  BBBB" + memberId );
//		Optional<Ticket> ticketOp = ticketRepository.findById(id);
//		if (ticketOp.isPresent()) {
//			Ticket ticket = ticketOp.get();
//			cartService.addcart(memberId, id);
//			return "redirect:/ticketmart/cart";
//		} else {
//			return "redirect:/Ticketproduct/mallPage1";
//		}
//	}
}