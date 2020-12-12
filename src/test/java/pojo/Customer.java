package pojo;

/**
 * POJO класс для сериализации объекта customer для body в Create Quote
 */
public class Customer {

    private String name;
    private String id;
    private String billingAccountCode;
    private String category;

    /**
     * No args constructor for use in serialization
     */
    public Customer() {
    }

//    /**
//     *
//     * @param billingAccountCode
//     * @param name
//     * @param id
//     * @param category
//     */
//    public Customer(String name, String id, String billingAccountCode, String category) {
//        super();
//        this.name = name;
//        this.id = id;
//        this.billingAccountCode = billingAccountCode;
//        this.category = category;
//    }
//
//    public Customer withBillingCode(String name, String id, String billingAccountCode, String category) {
//        this.name = name;
//        this.id = id;
//        this.billingAccountCode = billingAccountCode;
//        this.category = category;
//        return this;
//    }
//
//    public Customer withoutBilligCode(String name, String id, String category) {
//        this.name = name;
//        this.id = id;
//        this.category = category;
//        return this;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillingAccountCode() {
        return billingAccountCode;
    }

    public void setBillingAccountCode(String billingAccountCode) {
        this.billingAccountCode = billingAccountCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("name", name).append("id", id).append("billingAccountCode", billingAccountCode).append("category", category).toString();
//    }
}