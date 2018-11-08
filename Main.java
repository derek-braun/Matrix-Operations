/*
*  Main class provides example usage and implementation of the MatrixOperation class functions.
*  Each function is tested and printed to the console using arbitrary test matrices.
*
*  Created: November 2018 by: Derek Braun
*/

public class Main 
{

	public static void main(String[] args) 
	{
		MatrixOperator mop = new MatrixOperator();
		
		System.out.println("Create a 5x5 Identity Matrix:\n");
		double[][] eye = new double[5][5];
		eye = mop.createIdentity(eye.length);
		printMatrix(eye);
		
		System.out.println("\nProduct of matrix A*B:\n");
		double[][] a = new double[][] {
			{4, 5, 6},
			{3, 2, 1},
			{8, 9, 7}
		};
		double[][] bMult = new double[][] {
			{2, 4},
			{6, 8},
			{0, 1}
		};
		printMatrix(mop.multiply(a, bMult));
		
		System.out.println("\nSum of matrix A+B:\n");
		double[][] bAdd = new double[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};		
		printMatrix(mop.add(a, bAdd));
		
		System.out.println("\nVector product of matrix A*vector:\n");	
		double[] vector = new double[] {3, 6, 9};
		printVector(mop.vectorMultiply(a, vector));
		
		System.out.println("\nTranspose of matrix A:\n");
		printMatrix(mop.transpose(a));
		
		System.out.println("\nInverse of matrix A (A^-1):\n");
		printMatrix(mop.inverse(a));
	}
	
	private static void printMatrix(double[][] a)
	{
	    for (int row = 0; row < a.length; row++) 
    	    {
		    for (int col = 0; col < a[0].length; col++) 
		    {
			if(a[row][col] < 0)
			{
				System.out.printf("%.5f\t\t", a[row][col]);
			}
			else
			{
				System.out.printf(" %.5f\t\t", a[row][col]);
			}
		    }
		    System.out.println();
             }
    	System.out.println();
	}
	
	private static void printVector(double[] v)
	{
		for(int i = 0; i < v.length; i++)
		{
	    		System.out.printf("%f\t", v[i]);
	   	}
    		System.out.println();
	}
}
