import dev.yuzuki.utils.network.HttpClient;
import dev.yuzuki.utils.network.Request;
import org.junit.jupiter.api.Test;

public class test {

    @Test
    public void networkTest() {
        System.out.println(
                HttpClient.sendRequest(
                        Request.builder().url("https://example.com/").method(Request.Method.GET).build()
                ).toTextResponse().get()
        );
    }
}