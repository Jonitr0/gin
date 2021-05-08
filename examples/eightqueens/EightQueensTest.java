import java.util.ArrayList;

import static org.junit.Assert.*;

public class EightQueensTest {

    private void CheckSolution(int n)
    {
        int[] sol = EightQueensSolution.FindSolution(n);
        assertTrue(EightQueensBacktrack.AllSolutions(n).contains(sol));
    }

    @org.junit.Test
    public void TestSolutionClassic() throws Exception {
        CheckSolution(8);
    }

    @org.junit.Test
    public void TestSolutionSmall() throws Exception {
        CheckSolution(4);
    }

    @org.junit.Test
    public void TestSolutionEmpty() throws Exception {
        CheckSolution(0);
    }
}
