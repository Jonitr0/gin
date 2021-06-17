// ------------------------------ BubbleSort.java ------------------------------- \\

public class BubbleSort
{
   public static void main(String args[])
   {
      int arr[] = new int[10], max, min ;
      double tmp;

      for(int i=0 ; i<arr.length ; i++)  // Array belegen
      {
         tmp = 100*Math.random() ;  //  0 <= tmp < 100
         tmp = Math.floor(tmp) ;    //  0 <= tmp <= 99  (ganzzahlig)
         arr[i] = (int)tmp ;
      }

      System.out.println("unsortiert");
      for(int i=0 ; i<arr.length ; i++)  // Array unsortiert ausgeben
         System.out.println(arr[i]);

      // Array sortieren mit BubbleSort
      int temp ;
      for(int i=1 ; i < arr.length ; i++ )
         for(int j = arr.length - 1 ; j >= i ; j--)
            if ( arr[j-1] > arr[j] )
            {
               temp      = arr[j-1] ;
               arr[j-1] = arr[j];
               arr[j]   = temp;
            }

      System.out.println("sortiert");
      for(int i=0 ; i<arr.length ; i++)  // Array sortiert ausgeben
         System.out.println(arr[i]);

   }  // end main

}  // end class
