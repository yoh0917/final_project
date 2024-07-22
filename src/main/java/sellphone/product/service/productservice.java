package sellphone.product.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import sellphone.product.model.PhotoRepository;
import sellphone.product.model.ProductRepository;
import sellphone.product.model.ProductScore;
import sellphone.product.model.ProductScoreRepository;
import sellphone.product.model.Product;

@Service
public class productservice {

	@Autowired
	public ProductRepository prRep;
	
	@Autowired
	public PhotoRepository phRepo;
	
	@Autowired
	public ProductScoreRepository scoreRepo;
	
	
	public Product saveProduct(Product product) {
		return prRep.save(product);
	}
	
	
	//用ID找Product
	public Product findbyid(Integer productid) {
		Optional<Product> optional = prRep.findById(productid);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}
		
	public void deletebyId(Integer productid) {
		 prRep.deleteById(productid);
	}

	
	//查詢有上架商品
	public List<Product> getAllbyStauts(){
		List<Product> allStatus = prRep.getAllStatus();
		
		return allStatus;
	}
	
	//用ID查評論
	public ProductScore findbyUserIdAndProductid(String userId,Integer productid) {
	return scoreRepo.findByUserNameAndProductid(userId, productid);		
	}
	
	
	//新增評論
	public ProductScore productScoreSave(ProductScore productScore) {
		return scoreRepo.save(productScore);
	}
	
	//找尋評論
	public List<ProductScore> findAllScore(Integer productid){
		return scoreRepo.findByProductId(productid);
	}
	
	//只找4個最新評論
	public List<ProductScore> findTop4Score(Integer productid){
		return scoreRepo.findTop4ByProductId(productid);
	}
}
