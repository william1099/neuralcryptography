package ncrypto.common;

import java.io.Serializable;

public class Calculation implements Serializable {
	static final long serialVersionUID = 42L;
	private int[][] wAlice;
	private int[][] wBob;
	private int[][] x;
	private int outputAlice;
	private int outputBob;
	
	public int[][] getWAlice() {
		return wAlice;
	}
	public void setWAlice(int[][] w) {
		this.wAlice = w;
	}
	public int[][] getWBob() {
		return wBob;
	}
	public void setWBob(int[][] w) {
		this.wBob = w;
	}
	public int[][] getX() {
		return x;
	}
	public void setX(int[][] x) {
		this.x = x;
	}
	public int getOutputAlice() {
		return outputAlice;
	}
	public void setOutputAlice(int output) {
		this.outputAlice = output;
	}
	
	public int getOutputBob() {
		return outputBob;
	}
	public void setOutputBob(int output) {
		this.outputBob = output;
	}
	
}
