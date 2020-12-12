package pojo;

/**
 * POJO class FillQuoteBody used for serialization of Fill Quote request body
 */
public class FillQuoteBody {
    private String simCardId;
    private String source;
    private String subscriptionId;
    private String addonId;

    public FillQuoteBody() {}

    public String getSimCardId() {
        return simCardId;
    }

    public void setSimCardId(String simCardId) {
        this.simCardId = simCardId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getAddonId() {
        return addonId;
    }

    public void setAddonId(String addonId) {
        this.addonId = addonId;
    }
}
