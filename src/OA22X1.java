import java.util.HashMap;
import java.util.List;

public class OA22X1 extends Gate{
	int intermediateCount = 0;


	public OA22X1(){
		super();
	}
		
	public void addOA22X1(List<String> signals, HashMap<String, Gate> hm){				
		//OA22X1 (IN1,IN2,IN3,IN4,Q)
		//or (IN4,IN3, g_1_out);
		//or (IN2,IN1, g_2_out,);
		//and (g_1_out,g_2_out, Q);
		
		//OR GATE
		String intermediateOne = "OA22X1_" + intermediateCount++ + "_out";
		super.addOr(signals.get(0), signals.get(1), intermediateOne, hm);
		
		//AND2 GATE
		String intermediateTwo = "OA22X1_" + intermediateCount++ + "_out";
		super.addOr(signals.get(2), signals.get(3), intermediateTwo, hm);
		
		//AND GATE
		super.addAnd(intermediateOne, intermediateTwo, signals.get(4), hm);
	}
	
	
}
