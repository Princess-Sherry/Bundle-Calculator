import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BundleTest {
    Bundle bundle;

    @BeforeEach
    void setUp(){
        bundle = new Bundle();
    }

    @Test
    @DisplayName("Test add bundle")
    void testAddBundle(){
        bundle.addBundle(2,500);
        assertEquals(1, bundle.getBundles().size());
        assertEquals(500, bundle.getBundles().get(2));
    }
}
