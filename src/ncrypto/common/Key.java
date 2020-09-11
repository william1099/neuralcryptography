package ncrypto.common;

import java.io.Serializable;
import java.util.ArrayList;

public class Key implements Serializable  {
	static final long serialVersionUID = 42L;
	private int[][] key;
	private String date;
	private int n, k, l;
	private long iterasi;
	private float time;
	private ArrayList<Calculation> calculations;
	
	public Key() {
		calculations = new ArrayList<>();
	}
	
	public void addCalc(ArrayList<Calculation> calc) {
		this.calculations = calc;
	}
	
	public ArrayList<Calculation> getCalc() {
		return this.calculations;
	}
	
	public void setTime(float sec) {
		this.time = sec;
	}
	
	public float getTime() {
		return this.time;
	}
	public long getIterasi() {
		return iterasi;
	}
	public void setIterasi(long iterasi) {
		this.iterasi = iterasi;
	}
	public int[][] getKey() {
		return key;
	}
	public void setKey(int[][] key) {
		this.key = key;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	public int getL() {
		return l;
	}
	public void setL(int l) {
		this.l = l;
	}
	
	
	
}
