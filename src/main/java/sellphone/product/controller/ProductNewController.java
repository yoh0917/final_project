package sellphone.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sellphone.product.dto.ProductUpdateForm;
import sellphone.product.model.ProductModel;
import sellphone.product.model.ProductNew;
import sellphone.product.service.ProductNewService;

@Controller
public class ProductNewController {
@Autowired
	public ProductNewService pnService;
	
	//找單一商品 並含有該商品的規格
	@GetMapping("/product/findone")
	public String findone(@RequestParam Integer productId,Model m) {
		ProductNew product = pnService.findoneProduct(productId);
		
		ProductUpdateForm form = new ProductUpdateForm();
		
		form.setProductModel(product.getProductModel());
		form.setProductNew(product);
		
		m.addAttribute("form",form);
		return "product/productfindone";
	}
	
	
	//商品跟商品明細都可以一次修改
	@ResponseBody
	@PostMapping("/product/update")
	public String update(@ModelAttribute ProductUpdateForm productUpdateForm) {
		List<ProductModel> productModel = productUpdateForm.getProductModel();
		ProductNew productNew = productUpdateForm.getProductNew();
		
		pnService.save(productNew);
		pnService.saveAll(productModel);
		
		
		return "OK6666";
	}
	
	
	//跳轉新增頁面
	@GetMapping("/addproduct")
	public String addPage() {
		return "product/addproductPage";
	}
	
	//新增方法
	@PostMapping("/product/add")
	public String add(@ModelAttribute ProductNew productNew) {
		List<ProductModel> productModel = productNew.getProductModel();
		pnService.saveAll(productModel);
		pnService.save(productNew);
		return "redirect:productAll";
	}
	
	
	
}
