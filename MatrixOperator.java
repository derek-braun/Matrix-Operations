package regression;

public class MatrixOperator 
{
	public double[][] multiply(double[][] a, double[][] b)
	{
		//returns the resultant product matrix equal to a*b
		if(a[0].length != b.length)
		{
			System.out.println("Incompatible Matrices (Terminated)");
			System.exit(1);
		}
		double[][] product = new double[a.length][b[0].length];
		double partial = 0;
		
		for(int i = 0; i < a.length; i++)
		{
			for(int j = 0; j < b[0].length; j++)
			{
				for(int inst = 0; inst < a[0].length; inst++)
				{
					partial += a[i][inst] * b[inst][j];
				}
				product[i][j] = partial;
				partial = 0;
			}
		}
		
		return product;
	}
	
	public double[] vectorMultiply(double[][] a, double[] v)
	{
		double partial = 0;
		for(int i = 0; i < a.length; i++)
		{
			for(int j = 0; j < a[0].length; j++)
			{
				partial += a[i][j] * v[j];
			}
			v[i] = partial;
			partial = 0;
		}
		return v;
	}
	
	public double[][] add(double[][] a, double[][] b)
	{
		//Returns the resultant sum matrix equal to a+b
		double[][] sum = new double[a.length][a[0].length];
		
		if(a.length != b.length || a[0].length != b[0].length)
		{
			System.out.println("Cannot add matrices of different sizes (Terminated)");
			System.exit(1);
		}
		for(int i = 0; i < a.length; i++)
		{
			for(int j = 0; j < a[0].length; j++)
			{
				sum[i][j] = a[i][j] + b[i][j];
			}
		}
		return sum;
	}
	
	public double[][] transpose(double[][] a)
	{
		//Returns the transpose of matrix a
		double[][] t = new double[a[0].length][a.length];
		
		for(int row = 0; row < a.length; row++)
		{
			for(int col = 0; col < a[0].length; col++)
			{
				t[col][row] = a[row][col];
			}
		}
		
		return t;
	}
	
	public double[][] inverse(double[][] a)
	{
		double [][] eye = createIdentity(a.length);
		int next = 0;
		int cont = 0;
		
		for(int j = 0; j < a[0].length; j++)
		{
			if(a[next][next] == 0)
			{
				for(int i = next+1; i < a.length; i++)
				{
					if(a[i][next] != 0)
					{
						swapRows(a, eye, next, i);
						cont = 1;
						break;
					}
				}
			}
			if(a[next][next] != 0 || cont == 1)
			{
				divideRow(a, eye, a[next][next], next);
				if(next != a.length-1)
				{
					for(int i = next+1; i < a.length; i++)
					{
						subtractRows(a, eye, next, i, next);
					}
				}
			}
			cont = 0;
			next++;
		}
		next = 0;
		
		for(int j = a[0].length-1; j > 0; j--)
		{
			for(int i = a.length-2-next; i > -1; i--)
			{
				subtractRows(a, eye, a.length-next-1, i, j);
			}
			next++;
		}
		return eye;
	}

	public double[][] createIdentity(int n) 
	{
		//Return an nxn identity matrix
		double [][] identity = new double[n][n];
		
		int diagonal = 0;
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				if(j == diagonal)
				{
					identity[i][j] = 1;
				}
				else
				{
					identity[i][j] = 0;
				}
			}
			diagonal++;
		}
		return identity;
	}
	
	private void swapRows(double[][] a, double[][] eye, int r1, int r2)
	{
		double temp;
		for(int j = 0; j < a[0].length; j++)
		{
			temp = a[r1][j];
			a[r1][j] = a[r2][j];
			a[r2][j] = temp;
			
			temp = eye[r1][j];
			eye[r1][j] = eye[r2][j];
			eye[r2][j] = temp;
		}
	}
	
	private void divideRow(double[][] a, double[][] eye, double div, int row)
	{
		for(int j = 0; j < a[0].length; j++)
		{
			a[row][j] /= div;
			eye[row][j] /= div;
		}
	}
	
	private void subtractRows(double[][] a, double[][] eye, int r1, int r2, int col)
	{
		double mult = a[r2][col]/a[r1][col];
		for(int j = 0; j < a[0].length; j++)
		{
			a[r2][j] -= (mult*a[r1][j]);
			eye[r2][j] -= (mult*eye[r1][j]);
		}
	}

}
