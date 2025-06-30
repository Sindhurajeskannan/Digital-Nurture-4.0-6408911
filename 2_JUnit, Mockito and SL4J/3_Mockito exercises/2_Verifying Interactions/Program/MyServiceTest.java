import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mockito;

public class MyServiceTest {

    @Test
    public void testVerifyInteraction() {
        // Step 1: Create mock
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Step 2: Use it
        MyService service = new MyService(mockApi);
        service.fetchData();

        // Step 3: Verify interaction
        verify(mockApi).getData(); // verifies that getData() was called once
    }
}
