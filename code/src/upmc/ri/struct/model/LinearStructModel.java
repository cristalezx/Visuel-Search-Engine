package upmc.ri.struct.model;

import upmc.ri.struct.STrainingSample;
import upmc.ri.struct.instantiation.IStructInstantiation;
import upmc.ri.struct.instantiation.MutiClass;

public class LinearStructModel<X,Y> implements IStructModel<X,Y>  {
	IStructInstantiation<X,Y> isi;
	public double[] paras;//存储所有参数
	public int dimpsi;
	
	public LinearStructModel(int dimpsi) {
		this.dimpsi=dimpsi;	
		this.paras=new double[dimpsi];
	}

	@Override
	public Y predict(STrainingSample<X, Y> ts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Y lai(STrainingSample<X, Y> ts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStructInstantiation<X, Y> instantiation() {
//		MutiClass mc=new MutiClass();
//		return (IStructInstantiation<X, Y>) mc;
		return this.isi;
	}
	
//	public void text( IStructInstantiation<X, Y> i){
//		System.out.println(this.isi.enumerateY());
//	}

	@Override
	public double[] getParameters() {
		// TODO Auto-generated method stub	
		return this.paras;
	}
	public void setParameters( double[] a) {
		// TODO Auto-generated method stub	
		this.paras=a;
	}

}
