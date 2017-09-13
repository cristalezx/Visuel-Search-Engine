
package upmc.ri.index;

import java.util.Arrays;

import  upmc.ri.io.ImageNetParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;


public class VIndexFactory {
	HashSet< Double> bagfeatures=new HashSet<Double>();
	
	public void creatbag() throws Exception{
		ImageNetParser imagenetparser = new ImageNetParser();
		Set<String> files = imagenetparser.classesImageNet();
		int n_class=files.size();
//		HashSet< Double> bagfeatures = new HashSet<Double>();
		for (int i=0;i<n_class;i++){
			List<ImageFeatures> imagefl = imagenetparser.getFeatures("/Users/cristalezx/Desktop/UPMC/RI/sbow/"+files.toArray()[i]+".txt");
			for(int j=0;j<imagefl.size();j++){
				List<Integer> bof = imagefl.get(j).getwords();
	
		
				for (int k=0;k<bof.size();k++){
					bagfeatures.add((double)(int) bof.toArray()[k]);			
				}
				
			}		
		}
		

//		return bagfeatures;	
	}
	double[] normalisation (double[] a){
		double l2=0;
		for (int i=0;i<a.length;i++){
			l2+=Math.pow(a[i], 2);		
		}
		l2=Math.sqrt(l2);
		for (int j=0;j<a.length;j++){
			a[j]=a[j]/l2;
		}
		return a;	
		
	}

	public double[] computeBow (ImageFeatures ib) throws Exception{
//		Create the bag of features
//		double[] bag=new double[bagfeatures.size()];		
//		Iterator <Double> it = bagfeatures.iterator();
//		int i = 0;
//		while(it.hasNext()){
//			bag[i]=it.next();
//			i=i+1;
//		}
//		
		ArrayList<Double> bag = new ArrayList<Double>(bagfeatures.size());
		
//		double[] bag=new double[bagfeatures.size()];
		int i = 0;
		Iterator <Double> it = bagfeatures.iterator();
		while(it.hasNext()){
			bag.add(it.next());
//			i=i+1;
		}
		
		double[] bow=new double[bagfeatures.size()];
		Arrays.fill(bow,new Double(0));
		
//		System.out.println(bagfeatures.size());
//		System.out.println(bagfeatures.contains(444.0));
		bag.indexOf(0.0);
		
		List<Integer> bof = ib.getwords();
		for(double f : bof){
//			System.out.println(f);
			f=(double)(int)f;
			if (bagfeatures.contains(f)){
//				System.out.println("contain");
				
				int index=bag.indexOf(f);
//				System.out.println(index);			
				bow[index]+=1;
				
				
			}
			
			
		}
		
//	System.out.println(Arrays.toString(bow));
		bow=normalisation(bow);
		return bow;
		
	  }

	
	private void bag (int i, int j, int k, int l, int m) {
		// TODO Auto-generated method stub
		
	}
	

}
