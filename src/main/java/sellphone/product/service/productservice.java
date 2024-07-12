package sellphone.product.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import sellphone.product.model.PhotoRepository;
import sellphone.product.model.ProductRepository;
import sellphone.product.model.Product;

@Service
public class productservice {

	@Autowired
	public ProductRepository prRep;
	
	@Autowired
	public PhotoRepository phRepo;
	
	
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

	
}
