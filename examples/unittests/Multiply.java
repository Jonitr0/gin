public class Multiply {

    //multiplies to non-negative integer numbers
    public static int multiply(int a, int b)
    {
        int result = slowMultiply(a,b);
        return result;
    }

    private static int slowMultiply(int a, int b)
    {
        int result = 0;
        for(int i=0; i<b; ++i)
        {
            result += a;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e)
            {

            }
        }
        return result;
    }
}
