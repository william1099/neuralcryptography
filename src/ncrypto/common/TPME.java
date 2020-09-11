package ncrypto.common;

import static ncrypto.common.Helper.*;

public class TPME implements Cloneable {
	private int K, N, L;
	private int[][] w;
	private int[] h;
	private int output;
	
	public TPME(int K, int N, int L) {
		this.K = K;
		this.N = N;
		this.L = L;
		w = new int[K][N];
		h = new int [K];
	}

	public void calcOutput(int[][] x) {
		output = 1;
		for(int k = 0; k < K; k++) {
			long sum = 1;
			for(int j = 0; j < N; j++) {
				sum += x[k][j] * w[k][j];
			}
			h[k] = signum(sum);
			output *= h[k];
		}
	}
	
	public void updateWeights(int[][] x, int output) {
		
		for(int k = 0; k < K; k++) {
			for(int j = 0; j < N; j++) {
				// hebbian rule
				w[k][j] = w[k][j] + x[k][j] * h[k] * theta(h[k], output);
				// random walk
				//w[k][j] = w[k][j] + x[k][j] * theta(h[k], output);   
				w[k][j] = g(w[k][j], this.L);
			}
		}
	}
	
	public void initWeights() {
		for(int k = 0; k < K; k++) {
			for(int j = 0; j < N; j++) {
				w[k][j] = (int) ((Math.random() * L) + 1); 
			}
		}
	}
	
	public int getOutput() {
		return this.output;
	}
	
	public int getError(TPM that) {
		int error = 0;
		for(int k = 0; k < K; k++) {
			for(int j = 0; j < N; j++) {
				error += Math.abs(this.getWeight(k, j) - that.getWeight(k, j)); 
			}
		}
		return error;
	}
	
	public int getWeight(int k, int j) {
		return this.w[k][j];
	}
	
	public int[][] getWeights() {
		return this.w;
	}
	
	public int[] getHidden() {
		return this.h;
	}
	

}
