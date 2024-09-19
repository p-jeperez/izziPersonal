package mx.com.izzi.student.file;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Reconcile {
    private Long id;
    private String name;
    private Integer sku;
    private Double price;
    private Date creationDate;
    private String type;

    public String toCsv() {
        return id + "," + name + "," + sku + "," + price + "," + creationDate + "," + type;
    }
}
