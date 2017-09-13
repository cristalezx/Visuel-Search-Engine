package upmc.ri.struct.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.javaml.utils.ArrayUtils;
import upmc.ri.struct.STrainingSample;
import upmc.ri.struct.instantiation.IStructInstantiation;
import upmc.ri.struct.instantiation.RankingInstantiation;
import upmc.ri.struct.ranking.RankingFunctions;
import upmc.ri.struct.ranking.RankingOutput;
import upmc.ri.utils.mymath;

public class RankingStructModel<X,Y> extends LinearStructModel<List<double[]>,RankingOutput>{

	public RankingStructModel(int dimpsi,RankingInstantiation instantiation) {
		super(dimpsi);
		this.isi=instantiation;
	}
	
	
	public  RankingOutput predict(STrainingSample<List<double[]>, RankingOutput> ts) {
		
//		output is ranking out put
		List<double[]> x = ts.input;
		RankingOutput y = ts.output;
		double[] psi = this.isi.psi(x, y);
		mymath my=new mymath();
		double max=Double.MIN_VALUE;
		
		double[] map = new double[x.size()];
		for (int i=0;i<x.size();i++){
			double result = my.dot(x.get(i), this.paras);	
            map[i]=result;	
		}
		int indice = ArrayUtils.maxIndex(map);
		
		List<Integer>labelsGT =new ArrayList<Integer>();
		for (int i=0;i<x.size();i++){
			labelsGT.set(i,-1);
		}
		
		labelsGT.set(indice,1);
		List<Integer> ranking=new ArrayList<Integer>();
		ranking.add(indice);
		for (int i=0;i<x.size();i++){
			if (i!=indice){
				ranking.add(i);				
			}
			
		}
		
		
		RankingOutput ro=new RankingOutput(10,ranking, labelsGT);	
	 
		// TODO Auto-generated method stub
		return ro;
	}	
	
	public RankingOutput lai(STrainingSample<List<double[]>, RankingOutput> ts) {
		// TODO Auto-generated method stub
		RankingFunctions rf=new RankingFunctions();
		 rf.loss_augmented_inference(ts, this.paras);
		
		return null;
	}

}
