package app.graphics.elements;

import java.util.ArrayList;

public class Path {

	private ArrayList<Integer> xPts;
	private ArrayList<Integer> yPts; 

	public Path() {
		xPts = new ArrayList<Integer>();
		yPts = new ArrayList<Integer>();
	}

	public void addPoint(int x,int y) {
		xPts.add(x);
		yPts.add(y);
	}

	public int[] getPoint(int n) {
		int [] numberPair = {xPts.get(n), yPts.get(n)};
		return numberPair;
	}

	public void render(int[][] pixels) {
		if (xPts.size() > 0) {
			pixels[xPts.get(0)][yPts.get(0)] = 0xffffff;
		}
	}

}