package mx.com.izzi.student.db.model.mapper;

import mx.com.izzi.student.db.model.repository.ProductDb;
import mx.com.izzi.student.file.Reconcile;

import java.util.ArrayList;
import java.util.List;

public class MapToReconcile {

    private List<ProductDb> transaction;

    public MapToReconcile(List<ProductDb> transaction) {
        this.transaction = transaction;
    }

    public List<Reconcile> get_List() {
        List<Reconcile> reconcileList = new ArrayList<>();
        this.transaction.stream().forEach(t -> reconcileList
                .add(new Reconcile(t.getId(), t.getName(), t.getSKU(), t.getPrice(), t.getCreationDate(), t.getType())));
        return reconcileList;
    }

    public String get_String() {
        StringBuilder reconcile = new StringBuilder();
        List<Reconcile> reconcileList = get_List();
        reconcileList.stream().forEach(r -> reconcile.append(r.toCsv()).append(System.lineSeparator()));
        return reconcile.toString();
    }
}
