package steps;

import Base.BaseUtil;
import Base.CpqApiService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo.CreateQuoteBody;
import pojo.Customer;
import pojo.FillQuoteBody;
import Base.APIConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class CpquiApiSteps extends BaseUtil {

//    private BaseUtil base;

//    public CpquiApiSteps(BaseUtil base) {
//        this.base = base;
//    }

    public static ResponseOptions<Response> response;
    public static Customer customer = new Customer();
    public static CreateQuoteBody createQuoteBody = new CreateQuoteBody();

    @Given("Send Create Quote request with body parameters")
    public void createQuoteWithBodyParameters(DataTable arg) {
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);

        //Fill Create Quote request body
        customer.setName("AT Alpha Computers");
        customer.setId(randomCustomerId);
        customer.setCategory(table.get(0).get("category"));
        if(table.get(0).get("billingAccountCode")!=null)
            customer.setBillingAccountCode(table.get(0).get("billingAccountCode"));
        createQuoteBody.setNotes("Alpha Computers Store 2nd quote");
        createQuoteBody.setScenario(table.get(0).get("scenario"));
        createQuoteBody.setCreatedBy("AT-VIME");
        createQuoteBody.setName(randomQuoteName);
        createQuoteBody.setRelationId(randomRelationId);
        createQuoteBody.setDistributionChannel(table.get(0).get("distributionChannel"));
        createQuoteBody.setCustomer(customer);

        CpqApiService cpqApiService = new CpqApiService(
                "/api/tmnl/cpq/quote/",
                APIConstant.ApiMethods.POST,
                APIConstant.ApiVersions.v2,
                correlationId);

        response = cpqApiService.ExecuteWithBody(createQuoteBody);
        cpqApiService.CheckResponseCode(response, 201);

        //Store quote ID and ROE link in variables
        quoteId = response.getBody().jsonPath().get("id");
        quoteUrl = response.getBody().jsonPath().get("url");

        System.out.println("Created Quote URL: " + plmSever + quoteUrl);
        System.out.println("Response body is: " + response.getBody().print());
    }

    /**
     * Расширить сценарий, чтобы проверялось наличие и название параметров
     * @param requestScenario
     */
    @And("Validate JSON schema for {string} response")
    public void validateJSONSchemaForResponse(String requestScenario) {
        //Choose schema file according to scenario
        var schema = "";
        if(requestScenario.equalsIgnoreCase("Create Quote"))
            schema = "json/CreateQuoteResponseExample.json";
        else if(requestScenario.equalsIgnoreCase("Search Quote"))
            schema = "json/SearchQuoteResponseExample.json";
        else if(requestScenario.equalsIgnoreCase("Fetch Quote"))
            schema = "json/FetchQuoteResponseExample.json";
        else if(requestScenario.equalsIgnoreCase("Accept Quote"))
            schema = "json/AcceptQuoteResponseExampleV3.3.json"; //Will not work on API v1 due to in v1 response body is empty

        var responseBody = response.getBody().asString();   //returns the body as string

        assertThat(responseBody, matchesJsonSchemaInClasspath(schema)); //Compare JSON schema with template
    }

    @And("Send Search Quote request")
    public void sendSearchQuoteRequest() {
        System.out.println("Send Search Quote request");

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("customerId", randomCustomerId);
        queryParams.put("relationId", randomRelationId);

        CpqApiService cpqApiService = new CpqApiService(
                "/api/tmnl/cpq/quotes",
                APIConstant.ApiMethods.GET,
                APIConstant.ApiVersions.v1,
                correlationId);
        response = cpqApiService.ExecuteWithQueryParams(queryParams);
        cpqApiService.CheckResponseCode(response, 200);
    }

    @And("Send Complete Quote request")
    public void sendCompleteQuoteRequest() {
        CpqApiService cpqApiService = new CpqApiService(
                "/api/tmnl/cpq/quote/" + quoteId + "/complete/",
                APIConstant.ApiMethods.POST,
                APIConstant.ApiVersions.v1,
                correlationId);
        response = cpqApiService.Execute();
        cpqApiService.CheckResponseCode(response, 200);
    }

    @And("Send Fetch Quote request")
    public void sendFetchQuoteRequest() {
        CpqApiService cpqApiService = new CpqApiService(
                "/api/tmnl/cpq/quote/" + quoteId,
                APIConstant.ApiMethods.GET,
                APIConstant.ApiVersions.v1,
                correlationId);
        response = cpqApiService.Execute();
        cpqApiService.CheckResponseCode(response, 200);
    }

    @And("Verify Fetch Quote response values")
    public void verifyFetchQuoteResponseValues() {
        assertThat(response.getBody().jsonPath().get("id"), equalTo(quoteId));
        assertThat(response.getBody().jsonPath().get("name"), equalTo(randomQuoteName));
    }

    @And("Send Accept Quote request with API version {string}")
    public void sendAcceptQuoteRequestWithAPIVersion(String ver) {
        String apiVersion = null;
        if (ver.equals("v1"))
            apiVersion = APIConstant.ApiVersions.v1;
        else if (ver.equals("v2"))
            apiVersion = APIConstant.ApiVersions.v2;
        else if (ver.equals("v3"))
            apiVersion = APIConstant.ApiVersions.v3;

        CpqApiService cpqApiService = new CpqApiService(
                "/api/tmnl/cpq/quote/" + quoteId + "/accept/",
                APIConstant.ApiMethods.POST,
                apiVersion,
                correlationId);
        response = cpqApiService.Execute();

        cpqApiService.CheckResponseCode(response, 200);
    }

    @And("Send Clone Quote request")
    public void sendCloneQuoteRequest() {
        createQuoteBody.setSource(quoteId);

        CpqApiService cpqApiService = new CpqApiService(
                "/api/tmnl/cpq/quote/",
                APIConstant.ApiMethods.POST,
                APIConstant.ApiVersions.v2,
                correlationId);
        response = cpqApiService.ExecuteWithBody(createQuoteBody);
        cpqApiService.CheckResponseCode(response, 201);


        quoteId = response.getBody().jsonPath().get("id");      //Store created quote ID
        quoteUrl = response.getBody().jsonPath().get("url");    //Store link to ROE Quote

        System.out.println("Clonned Quote URL: " + plmSever + quoteUrl);
    }

    @And("Send Fill Quote request with data for {string} server")
    public void sendFillQuoteRequestWithDataForServer(String server) {
        FillQuoteBody fillQuoteBody = new FillQuoteBody();

        fillQuoteBody.setSource(quoteId);

        if (server.equalsIgnoreCase("6300")) {
            fillQuoteBody.setSubscriptionId("9154266190251625610");
            fillQuoteBody.setSimCardId("9153000166052443082");
            fillQuoteBody.setAddonId("9154266197651625994");
        } else if (server.equalsIgnoreCase("6330")) {
            fillQuoteBody.setSubscriptionId("9150776343151702499");
            fillQuoteBody.setSimCardId("9150776412751705574");
            fillQuoteBody.setAddonId("9150776412751705574");
        }

        CpqApiService cpqApiService = new CpqApiService(
                "/api/tmnl/cpq/quote/fill/",
                APIConstant.ApiMethods.POST,
                APIConstant.ApiVersions.v1,
                correlationId);

        response = cpqApiService.ExecuteWithBody(fillQuoteBody);
        cpqApiService.CheckResponseCode(response, 200);
    }
}