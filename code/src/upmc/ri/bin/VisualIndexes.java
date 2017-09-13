package upmc.ri.bin;
import  java.util.List;
import  java.util.Set;
import  java.util.ArrayList;

import  upmc.ri.index.ImageFeatures;
import  upmc.ri.index.VIndexFactory;
import  upmc.ri.io.ImageNetParser;
import  upmc.ri.struct.*;


public class VisualIndexes {
	
	public DataSet createdataset( ) throws Exception{
		ImageNetParser imagenetparser = new ImageNetParser();
		Set<String> files = imagenetparser.classesImageNet();
		int n_class=files.size();
//		List <double[]> trainX=new ArrayList<double[]>();
//		List <String> trainY=new ArrayList<String>();
//		List <double[]> testX=new ArrayList<double[]>();
//		List <String> testY=new ArrayList<String>();
		
		List<STrainingSample> listtrain=new ArrayList<STrainingSample>();
		List<STrainingSample> listtest=new ArrayList<STrainingSample>();
		
		
		VIndexFactory vif=new VIndexFactory();
		vif.creatbag();
		for (int i=0;i<n_class;i++){
			List<ImageFeatures> imagefl = imagenetparser.getFeatures("/Users/cristalezx/Desktop/UPMC/RI/sbow/"+files.toArray()[i]+".txt");
			
			for(int j=0;j<imagefl.size();j++){
				if (j<800){
					double[] xtr = vif.computeBow(imagefl.get(j));
					String ytr = files.toArray()[i].toString();
					STrainingSample<double[],String> train=new STrainingSample(xtr,ytr);
					listtrain.add(train);
				}
				else{
					double[] xte = vif.computeBow(imagefl.get(j));
					String yte = files.toArray()[i].toString();
					STrainingSample<double[],String> test=new STrainingSample(xte,yte);
					listtest.add(test);
				}
				
			}			
		}	    
//		STrainingSample<List<double[]>, List<String>> trainsample=new STrainingSample<List<double[]>, List<String>> ( trainX,trainY);
//	    STrainingSample<List<double[]>, List<String>> testsample=new STrainingSample<List<double[]>, List<String>> ( testX,testY);
	    DataSet dataset=new DataSet(listtrain,listtest);
	    
		return dataset;
		
	}

 }
