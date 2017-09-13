package upmc.ri.struct.model;

import java.util.ArrayList;
import java.util.List;

import upmc.ri.struct.STrainingSample;
import upmc.ri.struct.instantiation.IStructInstantiation;
import upmc.ri.utils.mymath;
import net.sf.javaml.utils.*;

public class LinearStructModel_EX<X,Y> extends LinearStructModel<X,Y>{
   
	public LinearStructModel_EX(int dimpsi,IStructInstantiation<X,Y> instantiation) {
		super(dimpsi);
		this.isi=instantiation;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Y predict(STrainingSample<X, Y> ts) {
		
		mymath my=new mymath();	
		Y[] ys = (Y[]) this.instantiation().enumerateY().toArray();
		double[] map = new double[ys.length];
		
		for ( int i=0;i<ys.length;i++){
			double[] psi = this.instantiation().psi(ts.input, ys[i]);
			map[i]=my.dot(psi, this.paras);			
		}
		int indice = ArrayUtils.maxIndex(map);
		return ys[indice];
	}
		
	@Override
	public Y lai(STrainingSample<X, Y> ts) {
		mymath my=new mymath();
	
		Y[] ys = (Y[]) this.instantiation().enumerateY().toArray();
		double[] map = new double[ys.length];
		
		for ( int i=0;i<ys.length;i++){
			double[] psi = this.instantiation().psi(ts.input, ys[i]);
			double delta=this.instantiation().delta(ts.output,ys[i]);
			map[i]=delta+my.dot(psi, this.paras);			
		}
		
		int indice = ArrayUtils.maxIndex(map);
		return ys[indice];
	}

	
}
