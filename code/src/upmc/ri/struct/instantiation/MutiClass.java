package upmc.ri.struct.instantiation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ejml.data.D1Matrix64F;
import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.MatrixVisualization;

import upmc.ri.bin.VisualIndexes;
import upmc.ri.index.VIndexFactory;
import upmc.ri.io.ImageNetParser;

public class MutiClass implements IStructInstantiation <double[],String>{

   Map<String, Integer> mapy;
    
    public MutiClass(){
    	Map<String,Integer> mapy = new HashMap<String,Integer> ();
		Set<String> yset = enumerateY();
		List<String> ylist = new ArrayList<String>(yset);
		for (int i=0;i<ylist.size();i++){
			mapy.put(ylist.get(i), i);		
		}
		this.mapy=mapy;
    }

	@Override
	public double[] psi(double[] x, String y) {
		//假设 x 已经是bow
		//y 的编码 从零开始
//		Map<String,Integer> mapy = new HashMap<String,Integer> ();
//		Set<String> yset = enumerateY();
//		List<String> ylist = new ArrayList<String>(yset);
//		for (int i=0;i<ylist.size();i++){
//			mapy.put(ylist.get(i), i);		
//		}
	
	    int v=this.mapy.get(y);
		
		double[] map=new double [x.length*this.mapy.size()];
	
		for (int i=v*x.length;i<(v+1)*x.length;i++){
			map[i]=x[i-v*x.length];
		}
		return map;
	}

	@Override
	public double delta(String y1, String y2) {
		double a;
		if (y1.equals(y2)){
			a=0.0;
		}
		else{
			a= 1.0;
		}
		return a;
		
		
	}

	@Override
	public Set<String> enumerateY( ) {
		Set<String> ys=new HashSet<String>();
		ys.add("european_fire_salamander");
		ys.add("harp");
		ys.add("minivan");
		ys.add("taxi");
		ys.add("tree-frog");
		ys.add("wood-frog");
		ys.add("acoustic_guitar");
		ys.add("ambulance");
		ys.add("electric_guitar");	
		return ys;				
	}
	
	public void confusionMatrix(List<String> predictions,List<String> gt){
		Set<String> set = new HashSet<String>(gt);
		System.out.println(set);
		List<String> count= new ArrayList(set);
		int size=set.size();
		DenseMatrix64F A = new DenseMatrix64F (size, size);
	    System.out.println(size);
        List<Integer> deltas=new ArrayList<Integer>();
        for(int i=0;i<gt.size();i++){
        	for (int j=0;j<predictions.size();j++){
        		int row=count.indexOf(gt.get(i));
        		int col=count.indexOf(predictions.get(j));
        		A.add(row, col, 1);
        			
        		}
        		
        	}         
    		
		MatrixVisualization.show(A, "ConfusionMatrix");	
		
	}

}
