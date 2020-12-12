package steps;

import Base.ApiService;
import Base.BaseUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo.*;
import Base.APIConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ApiSteps extends BaseUtil {

    public static ResponseOptions<Response> response;
    public static TriangleRequestBody triangleRequestBody = new TriangleRequestBody();

    @Given("Create Triangle with parameters from table")
    public void createTriangleWithBodyParameters(DataTable dataTable) {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

        //Fill request body
        triangleRequestBody.setSeparator(table.get(0).get("separator"));
        if(table.get(0).get("input")!=null) {triangleRequestBody.setInput(table.get(0).get("input"));}
        System.out.println("Request body: " + triangleRequestBody);

        ApiService apiService = new ApiService(
                "/triangle",
                APIConstant.ApiMethods.POST,
                token);

        response = apiService.ExecuteWithBody(triangleRequestBody);
        apiService.CheckResponseCode(response, 200);

        triangleId = response.getBody().jsonPath().get("id");
        firstSide = response.getBody().jsonPath().get("firstSide");
        secondSide = response.getBody().jsonPath().get("secondSide");
        thirdSide = response.getBody().jsonPath().get("thirdSide");

        System.out.println("Response body: " + response.getBody().print());
        System.out.println("triangleId: " + triangleId);
        System.out.println("firstSide: " + firstSide);
        System.out.println("secondSide: " + secondSide);
        System.out.println("thirdSide: " + thirdSide);
    }

    @And("^delete triangle with id \"([^\"]*)\"$")
    public void deleteTriangle(String triangleId) {
        ApiService apiService = new ApiService(
                "/triangle/" + triangleId,
                APIConstant.ApiMethods.DELETE,
                token);

        response = apiService.Execute();
        apiService.CheckResponseCode(response, 200);
    }

    @And("delete all triangles")
    public void deleteAllTriangles() {
        ApiService apiService = new ApiService(
                "/triangle/all",
                APIConstant.ApiMethods.GET,
                token);

        response = apiService.ExecuteWithBody(triangleRequestBody);
        apiService.CheckResponseCode(response, 200);
        var triangles = response.getBody().as(TriangleResponseBody[].class);
        for (int i = 0; i < triangles.length; i++) {
            deleteTriangle(triangles[i].getId());
        }
    }

    /**
     * @TODO add verification of parameters
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

        ApiService apiService = new ApiService(
                "/api/tmnl/cpq/quotes",
                APIConstant.ApiMethods.GET,
                token);
        response = apiService.ExecuteWithQueryParams(queryParams);
        apiService.CheckResponseCode(response, 200);
    }
}