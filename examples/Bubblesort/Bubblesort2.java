private int[] liste = {5, 1, 4, 9, 0, 8, 6};
public int[] sortieren() {
    int a;
    for(int k = 1; k < liste.length; k++){
      for (int b = 0; b < (liste.length – k); b++) {
        if (liste[b] > liste[b + 1]) {
          a = liste[b];
          liste[b] = liste[b + 1];
          liste[b + 1] = a;
        }
      }
    }
  return liste;
  }
public static void main(String[] args) {
    Bubble_Sort bs = new Bubble_Sort();
int[] array = bs.sortieren();
for (int b = 0; b < array.length; b++) {
      System.out.println(b + 1 + „:“ + array[b]);
    }
  }
}