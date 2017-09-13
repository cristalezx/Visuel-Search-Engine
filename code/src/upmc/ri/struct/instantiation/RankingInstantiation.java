package upmc.ri.struct.instantiation;

import java.util.List;
import java.util.Set;

import upmc.ri.struct.ranking.RankingFunctions;
import upmc.ri.struct.ranking.RankingOutput;
import upmc.ri.utils.mymath;

public class RankingInstantiation implements IStructInstantiation<List<double[]>,RankingOutput> {

	@Override
	public double[] psi(List<double[]> x, RankingOutput y) {
		// TODO Auto-generated method stub
		mymath my=new mymath();
		double[] sum = new double[1000];
		for (int i=0;i<x.size();i++){
			for (int j=0;j<x.size();j++){
				double[] minus = my.minus(x.get(i), x.get(j));
				List<Integer> pos = y.getPositionningFromRanking();
				if (pos.get(i)>pos.get(j)){
					minus=my.pro(minus, -1.);
					sum=my.minus(sum,minus);				
				}
				
			}
		}
		return sum;
	}

	@Override
	public double delta(RankingOutput y1, RankingOutput y2) {
//		y1 为pred y2  real
		RankingFunctions rf=new RankingFunctions();
		double ap = rf.averagePrecision(y2);		
		return 1.-ap;
	}

	@Override
	public Set<RankingOutput> enumerateY() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
