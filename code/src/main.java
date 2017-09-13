
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.MatrixVisualization;

import upmc.ri.io.*;
import upmc.ri.index.*;
import upmc.ri.struct.DataSet;
import upmc.ri.struct.STrainingSample;
import upmc.ri.struct.instantiation.MutiClass;
import upmc.ri.bin.*;
import upmc.ri.utils.*;

public class main {


	public static void main(String[] args) throws Exception {
	
		
//		ImageNetParser imagenetparser = new ImageNetParser();
//		Set<String> files = imagenetparser.classesImageNet();
//		List<List<Integer>> Doc = imagenetparser.getWords("/Users/cristalezx/Desktop/UPMC/RI/sbow/taxi.txt");
//        List<ImageFeatures> imagef1 = imagenetparser.getFeatures("/Users/cristalezx/Desktop/UPMC/RI/sbow/"+files.toArray()[0]+".txt");
//	    System.out.println(imagef1.get(0).getiD());
//	    System.out.println(imagef1.get(1).getwords().size());
//	    List<Double> x = imagef1.get(0).getX();
//	    List<Double> y = imagef1.get(0).getY();
	    
//	    VIndexFactory vif=new VIndexFactory(); 
//	    vif.creatbag();
//	    double[] a = vif.computeBow(imagef1.get(0));
//	    System.out.println(Arrays.toString(a));
	    
//	    VisualIndexes vi=new VisualIndexes();
//	    DataSet dataset = vi.createdataset();		
//	    PCA pca=new PCA();    
//	    DataSet<double[], String> s = pca.computePCA(dataset,250);    
//	    double[] a = s.listtrain.get(0).input;
//	    System.out.println(Arrays.toString(a));
//		
//		MulticlassClassif mclif =new MulticlassClassif();
//		mclif.main();
     
		Ranking rank=new Ranking();
		rank.main();
	

  
		
	}

}
