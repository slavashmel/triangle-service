package steps;

import Base.ApiService;
import Base.BaseUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo.*;
import Base.ApiConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiSteps extends BaseUtil {

    public static ResponseOptions<Response> response;
    public TriangleRequestBody triangleRequestBody = new TriangleRequestBody();
    public TriangleResponseBody triangle = null;
    public String triangleId = null;

    @Given("^Send (POST) request to endpoint \"([^\"]*)\" with parameters from table and check that response code is \"([^\"]*)\"$")
    public void createTriangleWithBodyParameters(final String method, final String endpoint, final int responseCode, DataTable dataTable) {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

        //Fill request body
        if (table.get(0).get("separator") != null) {
            triangleRequestBody.setSeparator(table.get(0).get("separator"));
        }
        if (table.get(0).get("input") != null) {
            triangleRequestBody.setInput(table.get(0).get("input"));
        }
        System.out.println("Request body: " + triangleRequestBody.asJson());
        System.out.println(triangleRequestBody);

        ApiService apiService = new ApiService(endpoint, method, table.get(0).get("token"));

        response = apiService.ExecuteWithBody(triangleRequestBody);
        apiService.CheckResponseCode(response, responseCode);
        System.out.println("Response body: " + response.getBody().asString());

        if (response.getStatusCode() < 299) {
            triangle = response.getBody().as(TriangleResponseBody.class);
            triangleId = triangle.getId();
            System.out.println(triangle);
        }
    }

    @Given("^Create (\\d+) (?:triangle|triangles) with parameters from table and check that response code is \"([^\"]*)\"$")
    public void createManyTriangles(final int quantity, final int responseCode, DataTable dataTable) {
        for (int i = 0; i < quantity; i++) {
            createTriangleWithBodyParameters(ApiConstant.ApiMethods.POST, "/triangle", responseCode, dataTable);
        }
    }

    @Given("^Send (GET) request to endpoint \"([^\"]*)\" and check that response code is \"([^\"]*)\"$")
    public void getTriangleWithBodyParameters(final String method, final String endpoint, final int responseCode) {
        ApiService apiService = new ApiService(endpoint + triangleId, ApiConstant.ApiMethods.GET, token);

        response = apiService.Execute();
        apiService.CheckResponseCode(response, responseCode);
        System.out.println("Response body: " + response.getBody().asString());

        if (response.getStatusCode() < 299) {
            triangle = response.getBody().as(TriangleResponseBody.class);
            triangleId = triangle.getId();
            System.out.println(triangle);
        }
    }

    @And("^delete triangle with id \"([^\"]*)\"$")
    public void deleteTriangle(String triangleId) {
        ApiService apiService = new ApiService(
                "/triangle/" + triangleId,
                ApiConstant.ApiMethods.DELETE,
                token);

        response = apiService.Execute();
        apiService.CheckResponseCode(response, 200);
    }

    @And("get all triangles")
    public void getAllTriangles() {
        ApiService apiService = new ApiService(
                "/triangle/all",
                ApiConstant.ApiMethods.GET,
                token);

        response = apiService.ExecuteWithBody(triangleRequestBody);
        apiService.CheckResponseCode(response, 200);
        var triangles = response.getBody().as(TriangleResponseBody[].class);
    }

    @And("^check that created (\\d+) triangles$")
    public void CalculateTrianglesQuantity(final int quantity) {
        ApiService apiService = new ApiService(
                "/triangle/all",
                ApiConstant.ApiMethods.GET,
                token);

        response = apiService.ExecuteWithBody(triangleRequestBody);
        apiService.CheckResponseCode(response, 200);
        var triangles = response.getBody().as(TriangleResponseBody[].class);

        assertThat(triangles.length, equalTo(quantity));
    }

    @And("delete all triangles")
    public void deleteAllTriangles() {
        ApiService apiService = new ApiService(
                "/triangle/all",
                ApiConstant.ApiMethods.GET,
                token);

        response = apiService.ExecuteWithBody(triangleRequestBody);
        apiService.CheckResponseCode(response, 200);
        var triangles = response.getBody().as(TriangleResponseBody[].class);
        for (int i = 0; i < triangles.length; i++) {
            deleteTriangle(triangles[i].getId());
        }
    }

    /**
     * @param jsonName
     * @TODO add verification of parameters
     */
    @And("Check that JSON schema of response matches {string} standard")
    public void validateJSONSchemaForResponse(String jsonName) {
        var expectedJson = "json/" + jsonName + ".json";
        var responseJson = response.getBody().asString();   //returns the body as string

        assertThat(responseJson, matchesJsonSchemaInClasspath(expectedJson)); //Compare JSON schema with template
    }

    @And("Send GET request with query params")
    public void getWithQueryParams() {
        System.out.println("Send Search Quote request");

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("param1", "paramValue1");
        queryParams.put("param2", "paramValue2");

        ApiService apiService = new ApiService(
                "/triangle",
                ApiConstant.ApiMethods.GET,
                token);
        response = apiService.ExecuteWithQueryParams(queryParams);
        apiService.CheckResponseCode(response, 200);
    }

    @And("Check that response contains parameter from table")
    public void checkThatResponseContainsParameterFromTable(final DataTable dataTable) {
        List<List<String>> table = dataTable.asLists();

        String key = table.get(0).get(0);
        String value = table.get(0).get(1);

        System.out.println(key + value);

//        @TODO finish getting attribute

    }
}