import java.util.HashMap;
import java.util.List;

public class OA222X1 extends Gate{
	int intermediateCount = 0;


	public OA222X1(){
		super();
	}
		
	public void addOA222X1(List<String> signals, HashMap<String, Gate> hm){				
		/* OA222X1 (IN1,IN2,IN3,IN4,IN5,IN6,Q);
		 * OR (IN2,IN1, g_1_out,);
		 * OR (IN4,IN3, g_2_out,);
		 * OR (IN6,IN5, g_3_out,);
		 * AND #1 (g_1_out,g_2_out,g_3_out, Q);
		 */
		
		//OR GATE
		String intermediateOne = "OA222X1_" + intermediateCount++ + "_out";
		super.addOr(signals.get(0), signals.get(1), intermediateOne, hm);
		
		//OR GATE
		String intermediateTwo = "OA222X1_" + intermediateCount++ + "_out";
		super.addOr(signals.get(2), signals.get(3), intermediateTwo, hm);
		
		//OR
		String intermediateThree = "OA222X1_" + intermediateCount++ + "_out";
		super.addOr(signals.get(4), signals.get(5), intermediateThree, hm);
		
		//AND GATE
		super.addAnd(intermediateOne, intermediateTwo, intermediateThree, signals.get(6), hm);
	}
	
	
}
