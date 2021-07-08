public static int[] bubblesort(int[] zusortieren) {
		int temp;
		for(int i=1; i<zusortieren.length; i++) {
			for(int j=0; j<zusortieren.length-i; j++) {
				if(zusortieren[j]>zusortieren[j+1]) {
					temp=zusortieren[j];
					zusortieren[j]=zusortieren[j+1];
					zusortieren[j+1]=temp;
				}
				
			}
		}
		return zusortieren;
	}
	/*
public static void main(String[] args) {
	
		int[] unsortiert={1,5,8,2,7,4};
		int[] sortiert=bubblesort(unsortiert);
		
		for (int i = 0; i<sortiert.length; i++) {
			System.out.print(sortiert[i] + ", ");
		}
	
	}
	*/