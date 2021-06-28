import static org.junit.Assert.*;

public class MultiplyTest {
    @org.junit.Test
    public void testMultiply() throws Exception {
        assertEquals(4,Multiply.multiply(2,2));
        assertEquals(4,Multiply.multiply(1,4));
        assertEquals(27,Multiply.multiply(3,9));
        assertEquals(1024,Multiply.multiply(256,4));
    }
}
