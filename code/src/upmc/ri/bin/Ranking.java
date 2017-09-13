package upmc.ri.bin;

import java.util.List;
import java.util.Set;

import upmc.ri.io.ImageNetParser;
import upmc.ri.struct.DataSet;
import upmc.ri.struct.Evaluator;
import upmc.ri.struct.instantiation.RankingInstantiation;
import upmc.ri.struct.model.RankingStructModel;
import upmc.ri.struct.ranking.RankingFunctions;
import upmc.ri.struct.ranking.RankingOutput;
import upmc.ri.struct.training.SGDTrainer;
import upmc.ri.utils.Drawing;

public class Ranking {
	public void main() throws Exception{
		
	    VisualIndexes vi=new VisualIndexes();
	    DataSet dataset = vi.createdataset();
	    List testlist=dataset.listtest;
	    List trainlist = dataset.listtrain;
	    
	    RankingFunctions rf=new RankingFunctions();
	    DataSet<List<double[]>, RankingOutput> dataforoneclass = rf.convertClassif2Ranking(dataset, "hard");
	    
	    RankingInstantiation rankins=new RankingInstantiation();
	    RankingStructModel rankmodel=new RankingStructModel(1000,rankins);
	    
		Evaluator<double[],String> evaluateur = new Evaluator<double[],String>();
		evaluateur.setListtest(testlist);
		evaluateur.setListtrain(trainlist);
		evaluateur.setModel(rankmodel);
	    
	    SGDTrainer sgdtrainer=new SGDTrainer(evaluateur, 1e-6,10,1);
		sgdtrainer.train(trainlist, rankmodel);
		
		
//	      Drawing d=new Drawing();   
//	      d.traceRecallPrecisionCurve(10, rp)
	    
		
	}


}
