import com.google.common.eventbus.Subscribe;
import dev.yuzuki.utils.event.EventBus;
import dev.yuzuki.utils.event.Listener;
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

    @Test
    public void eventTest() {
        MockEvent event = new MockEvent();

        EventBus original = new EventBus();
        com.google.common.eventbus.EventBus guava = new com.google.common.eventbus.EventBus();
        MockListener listener = new MockListener();

        original.register(listener);
        guava.register(listener);

        long l = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            original.post(event);
        }
        long originalTime = System.currentTimeMillis() - l;

        l = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            guava.post(event);
        }

        long guavaTime = System.currentTimeMillis() - l;
        System.out.println("Original EventBus took: " + originalTime + "ms");
        System.out.println("Guava EventBus took: " + guavaTime + "ms");
    }

    private static class MockListener {
        @Listener
        @Subscribe
        public void onEvent(MockEvent event) {

        }
    }

    private static class MockEvent {
    }
}