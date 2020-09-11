package ncrypto.common;

public class Helper {
	private Helper() {
		throw new AssertionError("Helper class should not be initialized");
	}
	
	public static int theta(int a, int b) {
		return (a == b) ? 1 : 0;
	}
	
	public static int signum(long x) {
		return (x > 0) ? 1 : -1;
	}
	
	public static int g(int w, int L) {
		if (w > L) return L;
		if(w < -L) return -L;
		return w;
	} 
	
	public static void printArr(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.println("");
			for(int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
		}
	}
}
