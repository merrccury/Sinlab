package by.alekseichik.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders headers = new HttpHeaders();
    @Test
    public void login() throws JsonProcessingException, JSONException {
        String loginUrl = "http://localhost:" + port + "/api/v1/auth/login";

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject userJson = new JSONObject();
        userJson.put("username", "root");
        userJson.put("password", "root");

        HttpEntity<String> request =
                new HttpEntity<String>(userJson.toString(), headers);
        String userResultJson = this.restTemplate
                .postForObject(loginUrl, request, String.class);
        JSONObject user = new JSONObject(userResultJson);

        assertTrue(user.getString("username").equals("root"));
    }
    @Test
    public void registration() throws JsonProcessingException, JSONException {
        String regUrl = "http://localhost:" + port + "/api/v1/reg";

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject userJson = new JSONObject();
        userJson.put("firstName", "patietFirst");
        userJson.put("lastName", "patienLast");
        userJson.put("email", "patient@gmail.com");
        userJson.put("password", "patient");

        HttpEntity<String> request =
                new HttpEntity<String>(userJson.toString(), headers);

        String result = this.restTemplate
                .postForObject(regUrl, request, String.class);

        assertNotNull(result.equals("true"));
    }

}
