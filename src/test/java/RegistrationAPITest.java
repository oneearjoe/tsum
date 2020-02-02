import net.serenitybdd.junit.runners.SerenityRunner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SerenityRunner.class)
public class RegistrationAPITest {

    /**Positive**/
    @Test
    public void registartionPositive()
    {
        double a= Math.random()*100;
        String email="noname"+a;
        String myJson= "{\"user-profile\":{\"email\":\"" + email + "@mail.com\",\"password\":\"q1w2e3r4\"}}";
        Response response = null;

        response= rest().contentType(ContentType.JSON).body(myJson).post("https://api.tsum.ru/sign-up");
        System.out.println("JSON :" + myJson);
        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
        assertEquals (200, response.getStatusCode());
    }
    /**Negative**/
    @Test
    public void emailAlreadyExist()
    {
        String myJson= "{\"user-profile\":{\"email\":\"random1@gmail.com\",\"password\":\"q1w2e3r4\"}}";
        Response response = null;

        response= rest().contentType(ContentType.JSON).body(myJson).post("https://api.tsum.ru/sign-up");
        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
         assertEquals (400, response.getStatusCode());
    }
}
