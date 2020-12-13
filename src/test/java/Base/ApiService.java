package Base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiService extends BaseUtil {
    private RequestSpecBuilder builder = new RequestSpecBuilder();
    private String method;
    private String url;

    /**
     * ApiService constructor to pass the initial settings for the following method
     *
     * @param uri
     * @param method
     */
    public ApiService(String uri, String method, String token) {
        this.url = super.baseURI + uri;
        this.method = method;

        System.out.println("Method: " + method + ", URL: " + this.url);

        builder.addHeader("X-User", token);
        builder.addHeader("Content-Type", "application/json");
    }

    /**
     * ExecuteAPI to execute the API for GET/POST/DELETE
     *
     * @return ResponseOptions<Response>
     */
    private ResponseOptions<Response> ExecuteAPI() {
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given();
        request.spec(requestSpecification);

        if (this.method.equalsIgnoreCase(ApiConstant.ApiMethods.POST))
            return request.post(this.url);
        else if (this.method.equalsIgnoreCase(ApiConstant.ApiMethods.GET))
            return request.get(this.url);
        else if (this.method.equalsIgnoreCase(ApiConstant.ApiMethods.DELETE))
            return request.delete(this.url);
        return null;
    }

    /**
     * Execute API with query params being passed as the input of it
     *
     * @param queryPath
     * @return Response
     */
    public ResponseOptions<Response> ExecuteWithQueryParams(Map<String, String> queryPath) {
        builder.addQueryParams(queryPath);
        return ExecuteAPI();
    }

    /**
     * Execute without any parameters
     *
     * @return
     */
    public ResponseOptions<Response> Execute() {
        return ExecuteAPI();
    }

    /**
     * ExecuteWithPathParams
     *
     * @param pathParams
     * @return
     */
    public ResponseOptions<Response> ExecuteWithPathParams(Map<String, String> pathParams) {
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }

    /**
     * ExecuteWithPathParamsAndBody
     *
     * @param pathParams
     * @param body
     * @return
     */
    public ResponseOptions<Response> ExecuteWithPathParamsAndBody(Map<String, String> pathParams, Map<String, String> body) {
        builder.setBody(body);
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }

    /**
     * ExecuteWithBody
     *
     * @param body
     * @return
     */
    public ResponseOptions<Response> ExecuteWithBody(Object body) {
        builder.setBody(body);
        return ExecuteAPI();
    }

    public void CheckResponseCode(ResponseOptions<Response> response, int expectedCode) {
        System.out.println("Response code: " + response.getStatusCode());
        if (response.getStatusCode() > 299) {
            System.out.println(response.getBody().print());
        }
        assertThat(response.getStatusCode(), equalTo(expectedCode));
    }
}
