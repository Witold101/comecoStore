package by.vistar.comeco.store.entity;

public class GoodDoc {
    private Long id;
    private Long docId;
    private Long goodId;
    private Float quantity;
    private Integer typeDoc;
    private Long storeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Integer getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(Integer typeDoc) {
        this.typeDoc = typeDoc;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
