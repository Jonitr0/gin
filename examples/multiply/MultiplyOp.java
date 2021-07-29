public class Multiply {

    //multiplies to non-negative integer numbers
    public static int multiply(int a, int b)
    {
        int result = a*b;
        return result;
    }

    private static int slowMultiply(int a, int b)
    {
        int result = 0;
        for(int i=0; i<b; ++i)
        {
            result += a;
        }
        return result;
    }
}
