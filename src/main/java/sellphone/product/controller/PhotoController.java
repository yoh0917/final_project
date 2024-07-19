package sellphone.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sellphone.product.model.Photo;
import sellphone.product.model.PhotoRepository;
import sellphone.product.model.Product;
import sellphone.product.model.ProductRepository;
import sellphone.product.service.productservice;

@Controller
public class PhotoController {

	@Autowired
	public PhotoRepository Prep;
	@Autowired
	public productservice pService;
	@Autowired
	public ProductRepository pRep;
	
	
	@GetMapping("/DashBoard/product/upload")
	public String home() {
		return "product/uploadpage";
	}

	
	@PostMapping("/DashBoard/product/sendphoto")
	public String addProductHouseSend(@ModelAttribute Product product, @RequestParam("file") MultipartFile[] files)
			throws IOException {		
		List<Photo> productphoto = new ArrayList<>();		
		for (MultipartFile oneFile : files) {
			Photo photo = new Photo();
			photo.setPhotoFile(oneFile.getBytes());
			photo.setProduct(product); // 多 set 1

			productphoto.add(photo);
		}
		product.setProductPhoto(productphoto);
		pService.saveProduct(product);
		return "redirect:/DashBoard/product/upload";
	}	
	@GetMapping("/photos/download")
	public ResponseEntity<byte[]> downloadPhotos(@RequestParam("photoid") Integer photoid) {	    	      
			Photo photo = Prep.findById(photoid).get();
	        byte[] photoFile = photo.getPhotoFile();		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		                                 // body,   headers , http status code
		return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);	
	}	
}
