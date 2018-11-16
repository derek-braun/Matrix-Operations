package regression;

public class RegressionTools 
{
	public double[] gradientDescent(double[][] x, double[] theta, double[] y, double learn)
	{
		MatrixOperator mop = new MatrixOperator();
		
		double[] hyp = mop.vectorMultiply(x, theta);
		double[] temp = new double[theta.length];
		
		for(int n = 0; n < x.length; n++)
		{
			temp[n] = 0;
		}
		
		for(int j = 0; j < theta.length; j++)
		{
			for(int i = 0; i < x.length; i++)
			{
				temp[j] += (hyp[i]-y[i])*x[i][j];
			}
			temp[j] = learn*(1/x.length)*temp[j];
		}

		for(int i = 0; i < temp.length; i++)
		{
			temp[i] = theta[i] - temp[i];
			theta[i] = temp[i];
		}
		
		return theta;
	}
	
	public double[] normalEquation(double[][] x, double[] y)
	{
		MatrixOperator mop = new MatrixOperator();
		
		double[][] temp = x;
		double[][] xt = mop.transpose(temp);
		
		return mop.vectorMultiply(mop.multiply(mop.inverse(mop.multiply(xt, x)), xt), y);
	}
	
	public void scaleFeatures(double[][] x)
	{
		double[] mean = calculateMean(x);
		double[] sd = standardDeviation(x, mean);
		
		for(int j = 0; j < x[0].length; j++)
		{
			for(int i = 0; i < x.length; i++)
			{
				x[i][j] = (x[i][j]-mean[j])/(sd[j]);
			}
		}
	}
	
	private double[] calculateMean(double[][] x)
	{
		double[] mean = new double[x[0].length];
		for(int j = 0; j < x[0].length; j++)
		{
			mean[j] = 0;
			for(int i = 0; i < x.length; i++)
			{
				mean[j] += x[i][j];
			}
		}
		
		return mean;
	}
	
	private double[] standardDeviation(double[][] x, double[] mean)
	{
		double[] sd = new double[x[0].length];
		double partial= 0;
		for(int j = 0; j < x[0].length; j++)
		{
			for(int i = 0; i < x.length; i++)
			{
				partial += Math.pow((x[i][j]-mean[j]), 2);
			}
			sd[j] = Math.sqrt(partial/(x.length - 1));
			partial = 0;
		}
		
		return sd;
	}
}
