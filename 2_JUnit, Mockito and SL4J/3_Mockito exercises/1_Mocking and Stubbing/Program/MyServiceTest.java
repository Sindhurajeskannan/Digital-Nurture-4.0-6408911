import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.mockito.Mockito;

public class MyServiceTest {

    @Test
    public void testExternalApi() {
        // Step 1: Create mock
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Step 2: Stub method
        when(mockApi.getData()).thenReturn("Mock Data");

        // Step 3: Use mock in service
        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        // Step 4: Assert result
        assertEquals("Mock Data", result);
    }
}
