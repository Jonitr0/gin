import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class EightQueensSolution {

    //naive greedy algorithm to find a single solution for the n queens problem
    public static int[] FindSolution(int n)
    {
        //start vector, filled with -1
        int[] sol = new int[n];
        Arrays.fill(sol, -1);

        //go through rows, set queens on non-blocked fields
        for (int i = 0; i < n; i++) {
            int candidate = ThreadLocalRandom.current().nextInt(0, n);
            sol[i] = candidate;

            while(true)
            {
                //no conflict, accept candidate
                if(EightQueensBacktrack.NoConflict(i,sol[i],sol))
                {
                    break;
                }
                //conflict, check next position
                else if(sol[i] < n-1 && sol[i] >= candidate)
                {
                    sol[i]++;
                }
                //conflict, check at zero and avoid infinite loop
                else if(sol[i] == n-1 && candidate != 0)
                {
                    sol[i] = 0;
                }
                //conflict, we checked every other field, we fucked up, restart
                //one could just go back one row, but that leaves a possibility for an infinite loop
                else
                {
                    return FindSolution(n);
                }
            }
        }

        return sol;
    }
}
