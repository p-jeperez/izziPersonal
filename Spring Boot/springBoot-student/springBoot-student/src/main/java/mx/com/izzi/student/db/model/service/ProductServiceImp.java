package mx.com.izzi.student.db.model.service;

import mx.com.izzi.student.db.model.repository.ProductDb;
import mx.com.izzi.student.db.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository repository;

    public List<ProductDb> lookupProduct(){
        return repository.findAll();
    }
    public ProductDb save(ProductDb p){
        return repository.save(p);
    }
    public int updateProductType(List<Long> ListMessageId, String messageId_UUId){
        return repository.updateProductType(ListMessageId,messageId_UUId);
    }
    public ProductDb findBy_Sku(Integer sku){
        return repository.findBySku_JP(sku);
    }
    public ProductDb findBy_Name(String name){
        return repository.findByName_JP(name);
    }



}
