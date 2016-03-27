import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class SquareMatrixMultiply {

	public static int[][] multiplyN3(int[][] A, int [][] B) {
		int n = A.length;
		int[][] C = new int[n][n];
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				int sum = 0;
				for (int k = 0; k < n; k++) {
					sum += A[y][k] * B[k][x];
				}
				C[y][x] = sum;
			}
		}
		return C;
	}
	
}
