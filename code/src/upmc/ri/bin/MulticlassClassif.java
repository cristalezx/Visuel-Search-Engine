package upmc.ri.bin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import upmc.ri.struct.*;
import upmc.ri.struct.instantiation.*;
import upmc.ri.struct.model.*;
import upmc.ri.struct.training.*;
import upmc.ri.struct.training.*;

public class MulticlassClassif {
	
	public void main() throws Exception{
//		Create dataset
		
	    VisualIndexes vi=new VisualIndexes();
	    DataSet dataset = vi.createdataset();
	    List testlist=dataset.listtest;
	    List trainlist = dataset.listtrain;
	    List<String> yys = new ArrayList<String>();
	    
	    for (int i=0;i<testlist.size();i++){
	    	STrainingSample<double[],String>  test = (STrainingSample<double[], String>) testlist.get(i);
	    	yys.add(test.output);
	    	
	    }
	    System.out.println(Arrays.toString(yys.toArray()));
	    

		
	    MutiClass mc=new MutiClass(); 
	    MultiClassHier mch=new MultiClassHier();
	    System.out.println("Crest dataset and muticlass finish");
	    
	    int yd=mc.enumerateY().size();
	    int dimpsi=1000*yd;


	    System.out.println("Crest Linear model ex");
	    LinearStructModel_EX<double[],String>  linmodelex=new LinearStructModel_EX<double[],String>(9000, mc);	    
	    LinearStructModel_EX<double[],String>  linmodelex1=new LinearStructModel_EX<double[],String>(9000, mch);	    
	    
//      creation d'un evaluateur
		Evaluator<double[],String> evaluateur = new Evaluator<double[],String>();
		evaluateur.setListtest(testlist);
		evaluateur.setListtrain(trainlist);
		evaluateur.setModel(linmodelex1);
  
	    System.out.println("Begin to train...");
		SGDTrainer sgdtrainer=new SGDTrainer(evaluateur, 1e-3,1e-7,100);
		sgdtrainer.train(trainlist, linmodelex1);
		
/* Predict test */
		
//		System.out.println("error");
//		evaluateur.getErr_test();
		
//		double[] a=linmodelex.getParameters();
//		System.out.println(Arrays.toString(a));
		List<String> y_real = new ArrayList<String>();
		List<String> y_pred = new ArrayList<String>();
		for (int i=0;i<testlist.size();i++){
			STrainingSample<double[],String> test = (STrainingSample<double[], String>) testlist.get(i);
			y_real.add(test.output);
			y_pred.add(linmodelex1.predict(test));
			
//			System.out.println(test.output);
//			System.out.println(linmodelex.predict(test));
//			System.out.println(linmodelex.predict(test));		
		}
//		System.out.println(Arrays.toString(y_real.toArray()));
//		System.out.println(Arrays.toString(y_pred.toArray()));
		
		mc.confusionMatrix(y_pred, y_real);
	
//		String y_pred = linmodel.predict((STrainingSample) testlist.get(0));
	}
	

}
