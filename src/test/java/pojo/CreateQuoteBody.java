package pojo;

/**
 * POJO класс для сериализации body для Create Quote
 */
public class CreateQuoteBody {

    private String notes;
    private String scenario;
    private String createdBy;
    private String name;
    private String relationId;
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String distributionChannel;
    public Customer customer;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateQuoteBody() {
    }

//    /**
//     *
//     * @param notes
//     * @param scenario
//     * @param createdBy
//     * @param name
//     * @param relationId
//     * @param distributionChannel
//     * @param customer
//     */
//    public CreateQuoteBody(String notes, String scenario, String createdBy, String name, String relationId, String distributionChannel, Customer customer) {
//        super();
//        this.notes = notes;
//        this.scenario = scenario;
//        this.createdBy = createdBy;
//        this.name = name;
//        this.relationId = relationId;
//        this.distributionChannel = distributionChannel;
//        this.customer = customer;
//    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getDistributionChannel() {
        return distributionChannel;
    }

    public void setDistributionChannel(String distributionChannel) {
        this.distributionChannel = distributionChannel;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("notes", notes).append("scenario", scenario).append("createdBy", createdBy).append("name", name).append("relationId", relationId).append("distributionChannel", distributionChannel).append("customer", customer).toString();
//    }

}