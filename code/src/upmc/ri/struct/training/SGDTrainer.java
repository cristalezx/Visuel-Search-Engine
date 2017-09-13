package upmc.ri.struct.training;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import org.ejml.ops.NormOps;

import java.lang.Math;

import net.sf.javaml.utils.*;
import upmc.ri.struct.*;
import upmc.ri.struct.instantiation.IStructInstantiation;
import upmc.ri.struct.model.IStructModel;
import upmc.ri.utils.VectorOperations;
import upmc.ri.utils.mymath;


public class SGDTrainer<X,Y> implements ITrainer<X,Y> {

	private double lambda;
	private int max_iter;
	private double step_gradien;
	private Evaluator<X,Y> evaluator;
	
	private mymath cal;
	
	public SGDTrainer(Evaluator<X, Y> evaluator, double eps, double lambda, int max_iter) {
		super();
		this.evaluator = evaluator;
		this.step_gradien = eps;
		this.lambda = lambda;
		this.max_iter = max_iter;
	}
//	
//	public double[] getw(){
//		return this.w;		
//	}
	
	public double convex_loss(List<STrainingSample<X, Y>> lts, IStructModel<X, Y> model){
		
		int length = lts.size();
		double[] w = model.getParameters();
		IStructInstantiation<X, Y> instantiation = model.instantiation();
		double loss = 0;
		
		for(int i=0;i<length;i++){
			STrainingSample<X, Y> exemple = lts.get(i);
			X x = exemple.input;
			Y yi = exemple.output;
			
			double max = Double.MIN_VALUE;
			for(Y y : instantiation.enumerateY()){
				double delta = instantiation.delta(yi, y);
				double[] psi = instantiation.psi(x, y);
				max = Math.max(max, delta + VectorOperations.dot(psi,w));				
			}
			loss += max - VectorOperations.dot(instantiation.psi(x, yi),w);			
		}
		loss /= length;
		loss += (this.lambda/2)*VectorOperations.norm2(w);
			
		return loss;
	}


	@Override
	public void train(List<STrainingSample<X, Y>> lts, IStructModel<X, Y> model) {
	    
		double[] test_errors=new double[this.max_iter];
		double[] losss=new double[this.max_iter];
		
		double[] w=model.getParameters(); 
		mymath my=new mymath();
//		w=new double[lts.size()];
		Arrays.fill(w,0);
		
		double loss=0;
		
		IStructInstantiation<X, Y> instantiation = model.instantiation();
		
		for (int t=0;t<max_iter;t++){
			for (int i=0;i<lts.size();i++){
				 Random random = new Random();
				 int indice = random.nextInt(lts.size());
				 Y y_pred = model.lai(lts.get(indice));
				 double[] map1= model.instantiation().psi(lts.get(indice).input,y_pred);
				 double[] map2= model.instantiation().psi(lts.get(indice).input,lts.get(indice).output);
				 double[] g=my.minus(map1, map2); 
				 
				 w=my.minus(w, my.pro((my.add(my.pro(w, lambda), g)),step_gradien));				
			}
			model.setParameters(w);
			
			this.evaluator.evaluate();
			test_errors[t]=this.evaluator.getErr_test();
			losss[t]= this.convex_loss(lts, model);
//			System.out.println("Iteration: " + t);
//			System.out.println("Err train: " + this.evaluator.getErr_train());
//			System.out.println("Err test: " + this.evaluator.getErr_test());
//			System.out.println("global loss : " + this.convex_loss(lts, model));
			}
//		this.evaluator.evaluate();
//		test_errors[t]=this.evaluator.getErr_test();
//		losss[t]= this.convex_loss(lts, model);
//		System.out.println("Iteration: " + t);
//		System.out.println("Err train: " + this.evaluator.getErr_train());
//		System.out.println("Err test: " + this.evaluator.getErr_test());
//		System.out.println("global loss : " + this.convex_loss(lts, model));
		
		System.out.println(Arrays.toString(losss));
		System.out.println(Arrays.toString(test_errors));
	}
	
}
