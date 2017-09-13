package upmc.ri.utils;

public class mymath {

	public double dot(double[] a,double b[]){
		double sum=0;
		for(int i=0;i<a.length;i++){
			sum+=a[i]*b[i];		
		}
		return sum;
		
	}
	
	public double[] add(double[] a,double[] b){
		double[] add =new double[a.length];
		for (int i =0;i<a.length;i++){
			add[i]=a[i]+b[i];
		}
		return add;			
	}
	public double[] minus(double[] a,double[] b){
		double[] add =new double[a.length];
		for (int i =0;i<a.length;i++){
			add[i]=a[i]-b[i];
		}
		return add;			
	}
	public double[] pro(double[] a,double b){
		double[] add =new double[a.length];
		for (int i =0;i<a.length;i++){
			add[i]=a[i]*b;
		}
		return add;			
	}
	
}
