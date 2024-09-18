package mx.com.izzi.student.db.model.service;

import mx.com.izzi.student.db.model.repository.ProductDb;

import java.util.List;

public interface ProductService {

    public List<ProductDb> lookupProduct();
    public ProductDb save(ProductDb p);
    public int updateProductType(List<Long> messageId, String messageId_UUId);
    public ProductDb findBy_Sku(Integer sku);
    public ProductDb findBy_Name(String name);

}
