package mx.com.izzi.student.db.model.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PRODUCT")
@NamedQueries({
        @NamedQuery(name = "ProductDb.findByName_JP", query = "SELECT d FROM ProductDb d WHERE d.name = :name_"),
        @NamedQuery(name = "ProductDb.findBySku_JP", query = "SELECT d FROM ProductDb d WHERE d.SKU = :SKU_") })
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductDb implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Size(max = 5)
    private Integer SKU;

    @Column(name = "PRICE")
    private Double price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "TYPE", length = 150)
    private String type;

}
