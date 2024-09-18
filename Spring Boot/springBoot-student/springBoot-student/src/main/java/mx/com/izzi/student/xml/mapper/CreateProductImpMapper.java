package mx.com.izzi.student.xml.mapper;

import lombok.extern.slf4j.Slf4j;
import mx.com.izzi.student.db.model.repository.ProductDb;
import mx.com.izzi.student.xml.xsd.java.obj.Item;
import mx.com.izzi.student.xml.xsd.java.obj.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Slf4j
public class CreateProductImpMapper {
    private final List<ProductDb> dnyMessagePublisherList;
    public CreateProductImpMapper(List<ProductDb> messageList) {
        this.dnyMessagePublisherList = messageList;
    }

    public  Item getMaps() {
        Item item = new Item();
        List<Product> productList = null;
        productList = dnyMessagePublisherList.stream().map(this::mapToCreateProduct).collect(Collectors.toList());
        Item.ProductList itemProductList = new Item.ProductList();
        itemProductList.setProduct(productList);
        item.setProductList(itemProductList);
        UUID uuid = UUID.randomUUID();
        item.setIdCorrelation(Service.VIDEO.name() + "-"+uuid);
        return item;
    }

    private Product mapToCreateProduct(ProductDb prod) {
        if (prod == null) {
            return null;
        }

        Product p = new Product();
        p.setId(prod.getId());
        p.setName(prod.getName());
        p.setSku(prod.getSKU());
        p.setPrice(BigDecimal.valueOf(prod.getPrice()));
        p.setCreationDate(String.valueOf(prod.getCreationDate()));
        log.info("\n MAPPER: " + prod.getId() + ", "+ prod.getName()+". ");
        return p;
    }
    public enum Service {
        VIDEO, INTERNET, CELULAR, TELEFONIA, OTT
    }
}
