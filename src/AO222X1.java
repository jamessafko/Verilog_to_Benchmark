import java.util.HashMap;
import java.util.List;

public class AO222X1 extends Gate{
	int intermediateCount = 0;


	public AO222X1(){
		super();
	}
		
	public void addAO222X1(List<String> signals, HashMap<String, Gate> hm){				
		/* AO222X1 (IN1,IN2,IN3,IN4,IN5,IN6,Q);
		 * and (IN2,IN1, g_1_out,);
		 * and (IN4,IN3, g_2_out,);
		 * and (IN6,IN5, g_3_out,);
		 * or #1 (g_1_out,g_2_out,g_3_out, Q);
		 */
		
		//OR GATE
		String intermediateOne = "AO222X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(0), signals.get(1), intermediateOne, hm);
		
		//or GATE
		String intermediateTwo = "AO222X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(2), signals.get(3), intermediateTwo, hm);
		
		String intermediateThree = "AO222X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(4), signals.get(5), intermediateThree, hm);
		
		//AND GATE
		super.addOr(intermediateOne, intermediateTwo, intermediateThree, signals.get(6), hm);
	}
	
	
}
