import static org.junit.Assert.*;

public class MultiplyTest {
    @org.junit.Test
    public void testMultiply() throws Exception {
        assertEquals(256*100000000,Multiply.multiply(256,100000000));
    }
}
