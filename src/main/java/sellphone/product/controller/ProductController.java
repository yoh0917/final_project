package sellphone.product.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import sellphone.product.model.Photo;
import sellphone.product.model.PhotoRepository;
import sellphone.product.model.Product;
import sellphone.product.model.ProductRepository;
import sellphone.product.model.ProductScore;
import sellphone.product.service.productservice;
import sellphone.user.model.Users;


@SessionAttributes
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
	
	//前台總商品頁
	@GetMapping("/front/productlist")
	public String frontProducts(Model model) {
		List<Product> allbyStauts = proRepo.getAllStatus();
		model.addAttribute("allbyStauts",allbyStauts);
		return "product/ProductFront";
	}
	
	//按價格查詢
	@GetMapping("/front/pricebetween")
	public String getpriceRange(@RequestParam Integer minPrice,@RequestParam Integer maxPrice,Model m) {
		List<Product> frontRangeProduct = proRepo.findByPriceRange(minPrice, maxPrice);
		m.addAttribute("frontlistProduct",frontRangeProduct);
		return "product/ProductFront";
	}
	
	//價格查詢API
	@ResponseBody
	@GetMapping("/front/api/pricebetween")
	public List<Product> ajaxPost(@RequestParam Integer minPrice,@RequestParam Integer maxPrice){
	return proRepo.findByPriceRange(minPrice, maxPrice);
	}
	
	//回到總頁面API
	@ResponseBody
	@GetMapping("/front/api/backlist")
	public List<Product> ajaxBackList(){
		return proRepo.findAll();
	}
	
	
	//前台單一商品
	@GetMapping("/front/productfindone")
	public String frontfindOne(HttpSession httpSession ,@RequestParam("productid") Integer productid, Model m) {
		Optional<Product> productoptional = proRepo.findById(productid);
		Product product = productoptional.get();
		List<ProductScore> allScore = pService.findTop4Score(productid);
		
		String userName = (String) httpSession.getAttribute("loginUsername");		
		ProductScore findbyUserNameAndProductid = pService.findbyUserIdAndProductid(userName, productid);
		System.out.println(findbyUserNameAndProductid);
		m.addAttribute("findbyUserNameAndProductid",findbyUserNameAndProductid);
		m.addAttribute("allScore",allScore);		
		m.addAttribute("product", product);
		return "product/ProductFrontOne";
	}
	
	//用brand找商品
	@GetMapping("/front/productbrand")
	public String frontBrand(@RequestParam("productbrand")String productbrand,Model m){
		List<Product> allbrand = proRepo.getAllbrand(productbrand);
		String brand = allbrand.get(0).getProductbrand();
		m.addAttribute("brand",brand);
		m.addAttribute("allbrand",allbrand);
		
		return "/product/FrontOnebrand";
	} 
	
	//價格 +品牌
	@ResponseBody
	@GetMapping("/front/api/productBrandByprice")
	public List<Product> getAllBrandByPrice(
			@RequestParam Integer minPrice,
			@RequestParam Integer maxPrice,
			@RequestParam String productbrand){
	return	proRepo.getAllbrandbyprice(minPrice, maxPrice, productbrand);
		
	}	
	
	//新增評論功能
	@ResponseBody
	@GetMapping("/api/addProductScore")
	public ProductScore addScore(HttpSession httpSession,
			@RequestParam String userName,
			@RequestParam Integer productid,
			@RequestParam Integer scorenum,
			@RequestParam String review) {
		ProductScore findbyUserNameAndProductid = pService.findbyUserIdAndProductid(userName, productid);
		
		if(findbyUserNameAndProductid == null) {
		ProductScore newScore = new ProductScore();
		newScore.setProductid(productid);
		newScore.setReview(review);
		newScore.setScorenum(scorenum);
		newScore.setUserName(userName);
		newScore.setLocalDateTime(LocalDateTime.now());
		
		Product product = pService.findbyid(productid);
		Double totalSocreNum = product.getTotalSocreNum();  //原本總分
		Integer totalScore = product.getTotalScore();		//原本總數
		Integer newtotalScore =totalScore + 1;			//新總數			
		Double newtotalnum = totalSocreNum + scorenum;	//新總分
		Double newavgScore = (double) (newtotalnum / newtotalScore); //算出新平均分數
		Double round1 = (double)Math.round(newavgScore*10)/10.0;
		
		System.out.println("scorenum:"+scorenum);
		System.out.println("total:"+totalSocreNum);
		System.out.println(newavgScore);
		
		product.setTotalSocreNum(newtotalnum);
		product.setTotalScore(newtotalScore);
		product.setAvgScore(round1);
		pService.saveProduct(product);
		
		return pService.productScoreSave(newScore);
		
		}
		return null;
	
	}
	
}

