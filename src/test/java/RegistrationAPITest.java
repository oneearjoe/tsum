import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;




public class RegistrationAPITest {

    @Test
    public void registartionPositive()
    {

        Random r = new Random();

        String eng = "abcdefghijklmnopqrstuvwxyz";
        String dig = "0123456789";
        String sum = eng + eng.toUpperCase() +  dig;
        char c = sum.charAt(r.nextInt(sum.length()) );
        String email="random1" + c;
        String myJson= "{\"user-profile\":{\"email\":\"" + email + "@mail.com\",\"password\":\"q1w2e3r4\"}}";
        Response response = null;

        response= RestAssured.given().contentType(ContentType.JSON).body(myJson).post("https://api.tsum.ru/sign-up");
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

        response= RestAssured.given().contentType(ContentType.JSON).body(myJson).post("https://api.tsum.ru/sign-up");
        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
        assertEquals (400, response.getStatusCode());


    }

}
