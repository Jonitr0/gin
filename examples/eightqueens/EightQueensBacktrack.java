import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class EightQueensBacktrack {
    public static ArrayList<int[]> AllSolutions(int n)
    {
        return Queens(n,n);
    }

    private static ArrayList<int[]> Queens(int rows, int cols)
    {
        //solution is empty field
        if(rows <= 0)
        {
            return new ArrayList<>(Arrays.asList(new int[0]));
        }
        else
        {
            return AddQueen(rows-1, cols, Queens(rows -1, cols));
        }
    }

    private static ArrayList<int[]> AddQueen(int newRow, int cols, ArrayList<int[]> solutions)
    {
        ArrayList<int[]> newSolutions = new ArrayList<>();
        for (int[] sol : solutions) {
            for (int i = 0; i < cols; i++) {
                if(NoConflict(newRow, i, sol))
                {
                    //create new array
                    int[] newSol = new int[newRow +1];
                    for (int j = 0; j < Array.getLength(sol); j++) {
                        newSol[j] = sol[j];
                    }
                    newSol[newRow] = i;
                    newSolutions.add(newSol);
                }
            }
        }
        return newSolutions;
    }

    public static boolean NoConflict(int newRow, int newCol, int[] solution)
    {
        for (int i = 0; i < newRow; i++) {
            if( solution[i] == newCol ||
                    solution[i] + i == newCol + newRow ||
                    solution[i] - i == newCol - newRow)
                return false;
        }
        return true;
    }
}
