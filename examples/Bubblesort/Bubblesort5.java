public class BubbleSort {

private static void bubbleSort(int[] toSort) {
        boolean done = false;
        while (!done) {
            done = true;

            for (int i = 0; i < toSort.length-1; i++) {
                int valA = toSort[i];
                int valB = toSort[i+1];
                if(valA > valB) {
                    done = false;
                    swap(i, i+1, toSort);
                }
            }
        }
    }

    private static void swap(int indx_a, int indx_b, int[]array) {
        int value_a = array[indx_a];
        array[indx_a] = array[indx_b];
        array[indx_b] = value_a;
    }
/*
    public static void main(String[] args) {
        int[] unsorted = {4, 13, 1, 9, 9, 2, 7, 3};

        System.out.println(Arrays.toString(unsorted));
        bubbleSort(unsorted);
        System.out.println(Arrays.toString(unsorted));


    }
}*/
