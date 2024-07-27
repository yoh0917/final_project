//package sellphone.phoneplan.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import sellphone.phoneplan.service.GeminiService;
//
//@RestController
//public class GeminiController {
//	
//	@Autowired
//	GeminiService geminiService;
//	
//	@GetMapping("/prompt")
//	public String getResponse(@RequestParam String prompt) {
//		return geminiService.callApi(prompt);
//	}
//}