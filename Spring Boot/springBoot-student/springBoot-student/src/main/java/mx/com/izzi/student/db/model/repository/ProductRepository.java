package mx.com.izzi.student.db.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductDb,Long> {

    ProductDb findBySku_JP(@Param("SKU_")  Integer sku);
    ProductDb findByName_JP(@Param("name_")  String name);

    @Transactional
    @Modifying
    @Query("UPDATE ProductDb c SET c.type = :messageId_UUId WHERE c.id in :ListMessageId")
    int updateProductType(@Param("ListMessageId") List<Long> ListMessageId, @Param("messageId_UUId") String messageId_UUId);
}
