package sellphone.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sellphone.product.model.ProductModel;
import sellphone.product.model.ProductModelRepository;
import sellphone.product.model.ProductNew;
import sellphone.product.model.ProductNewRepository;

@Service
public class ProductNewService {

	@Autowired
	public ProductModelRepository pmRep;
	@Autowired
	public ProductNewRepository pnRep;

	// 從modelRepository 找 全部的model 以及對應的product
	public List<ProductModel> getProductAndModel(Integer productId) {
		return pmRep.findByProductNewProductId(productId);
	}

	public void deletebyproductId(Integer productId) {
		pnRep.deleteById(productId);
	}

	// 後台首頁 連結 homepage用
	public List<ProductNew> getAllProductNew() {
		return pnRep.findAll();
	}

	public ProductNew findoneProduct(Integer productId) {
		Optional<ProductNew> optional = pnRep.findById(productId);
		
		return optional.get();
	}
	
	public void save(ProductNew p) {
		pnRep.save(p);
	}
	public  void saveAll(List<ProductModel> pp) {
		pmRep.saveAll(pp);
	}
	
//	public void updateProduct(ProductUpdateForm productUpdateForm) {
//
//		
//		List<ProductModelDTO> productModelDTOs = productUpdateForm.getProductModelDTOs();
//		ProductNew productNew = productUpdateForm.getProductNew();
//
//		ArrayList<ProductModel> models = new ArrayList<ProductModel>();
//		for (ProductModelDTO productModelDTO2 : productModelDTOs) {
//			ProductModel productModel = new ProductModel();
//			productModel.setModelId(productModelDTO2.getModelId());
//			productModel.setCapacity(productModelDTO2.getCapacity());
//			productModel.setColor(productModelDTO2.getColor());
//			productModel.setStatus(productModelDTO2.getStatus());
//			productModel.setStockQuantity(productModelDTO2.getStockQuantity());
//			productModel.setProductPrice(productModelDTO2.getProductPrice());
//	        productModel.setProductNew(productNew);  // 確保設置關聯
//			models.add(productModel);
//		}
//		pmRep.saveAll(models);
//
//		pnRep.save(productNew);
//
//	}

}
