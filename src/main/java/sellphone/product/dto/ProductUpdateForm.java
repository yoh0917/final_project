package sellphone.product.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sellphone.product.model.ProductModel;
import sellphone.product.model.ProductNew;
@NoArgsConstructor
@Getter
@Setter
public class ProductUpdateForm {
	
    private ProductNew productNew ;
    private List<ProductModel> productModel;
 
}
