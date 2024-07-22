package sellphone.phoneplan.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/test")
public class DemoController {
	
	@Autowired
	private MessageSource messageSource;
	
	 /**
	 * 取得當前語系.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the string
	 */
	@RequestMapping(value = { "/getCurrentLocale" }, method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<JSONObject> changeLocale(HttpServletRequest request, HttpServletResponse response) {
		Locale locale = LocaleContextHolder.getLocale();
		
		JSONObject json = new JSONObject();
		json.put("locale", "當前語系:" + messageSource.getMessage("current.locale", null, locale));

		return new ResponseEntity<>(json, HttpStatus.OK);
	}
}
