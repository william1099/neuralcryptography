package ncrypto.common;

public class InputVector {
	private int K, N;
	private int[][] X;
	
	public InputVector(int K, int N) {
		this.X = new int[K][N];
		this.K = K;
		this.N = N;
	}
	
	public int getRandomVal() {
		double value = Math.random() * 2;
		return (value > 1) ? 1 : -1;
		
	}
	
	public void initInput() {
		for(int k = 0; k < K; k++) {
			for(int j = 0; j < N; j++) {
				this.X[k][j] = getRandomVal();
			}
			
		}
	}
	
	public void printInput() {
		System.out.println("------Input Vector-------");
		for(int k = 0; k < K; k++) {
			for(int j = 0; j < N; j++) {
				System.out.print(X[k][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	
	public int[][] getInputVector() {
		return this.X;
	}
}
