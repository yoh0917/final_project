package sellphone.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class ProductController {
	@Autowired
	public ProductRepository proRepo;
	@Autowired
	public productservice pService;
	@Autowired
	public PhotoRepository Prep;

	@GetMapping("/DashBoard/productlist")
	public String listProducts(Model model) {
		List<Product> products = proRepo.findAll();
		model.addAttribute("listProduct", products);
		return "product/producthomepage";
	}

	@GetMapping("/DashBoard/productfindone")
	public String findOne(@RequestParam("productid") Integer productid, Model m) {
		Optional<Product> productoptional = proRepo.findById(productid);
		Product product = productoptional.get();
		m.addAttribute("product", product);
		return "product/productsearch";
	}

	@GetMapping("/DashBoard/deleteproduct")
	public String deleteProduct(@RequestParam Integer productid) {
		pService.deletebyId(productid);
		return "redirect:/DashBoard/productlist";
	}

	@PostMapping("/DashBoard/updateproduct")
	public String updateProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile[] files,
			@RequestParam("photoid") Integer[] photoid) throws IOException {
//	    List<Photo> originalPhotos = Prep.findByProductProductid(product.getProductid());

		List<Photo> photolist = new ArrayList<>();

		if (photoid.length != 0) {
			for (Integer integer : photoid) {
				if (integer != 0) {
					Photo photo = Prep.findById(integer).get();
					photolist.add(photo);
				}
			}
			Prep.deleteByProductid(product.getProductid());
			if (!files[0].isEmpty()) {
				for (MultipartFile file : files) {
					Photo photo = new Photo();
					photo.setPhotoFile(file.getBytes());
					photo.setProduct(product);
					photolist.add(photo);
				}
			}
			product.setProductPhoto(photolist);
			pService.saveProduct(product);
		}
		return "redirect:/DashBoard/productlist";
	}

	// 跳轉到新增頁面
	@GetMapping("/DashBoard/insertproduct")
	public String insertProduct() {
		return "product/productinsert";
	}

	@PostMapping("/DashBoard/productinsertPost")
	public String postMethodName(@ModelAttribute Product product) {
		pService.saveProduct(product);
		return "redirect:/DashBoard/productlist";
	}
	
	//------------------前台----------------------
	
	
	@GetMapping("/front/productlist")
	public String frontProducts(Model model) {
		List<Product> products = proRepo.findAll();
		model.addAttribute("frontlistProduct", products);
		return "product/ProductFront";
	}
	
	@GetMapping("/front/pricebetween")
	public String getpriceRange(@RequestParam int minPrice,@RequestParam int maxPrice,Model m) {
		List<Product> frontlistProduct = proRepo.findByPriceRange(minPrice, minPrice);
		m.addAttribute("getpriceRange",frontlistProduct);
		return "product/ProductPriceFront";
	}
	
}
