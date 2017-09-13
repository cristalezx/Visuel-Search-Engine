package upmc.ri.struct.instantiation;

import java.util.Set;

import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.ws4j.*;
import edu.cmu.lti.ws4j.impl.WuPalmer;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;

public class MultiClassHier extends MutiClass {
	
	private double[][] distances;
	
	
	
	public MultiClassHier(){
	
		
		ILexicalDatabase db = new NictWordNet();
		WS4JConfiguration.getInstance().setMFS(true);
		RelatednessCalculator rc=new WuPalmer(db);		
		Set<String> yset = this.enumerateY();
		this.distances=new double[yset.size()][yset.size()];
		for (String y1:yset){
			for (String y2:yset){
				double similarity = rc.calcRelatednessOfWords(y1, y2);
				int i = this.mapy.get(y1);
				int j = this.mapy.get(y1);
				this.distances[i][j]=similarity;			
			}		
		}	
	}
		
	
	@Override
	public double delta(String y1, String y2) {
		if (y1.equals(y2)){
			return 0;
			
		}
		else{
			int i = this.mapy.get(y1);
			int j = this.mapy.get(y1);			
			return 1.-this.distances[i][j];
	
		}
		
		
		
	}

	

}
