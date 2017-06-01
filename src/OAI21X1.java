import java.util.HashMap;
import java.util.List;

public class OAI21X1 extends Gate{
	int intermediateCount = 0;


	public OAI21X1(){
		super();
	}
		
	public void addOAI21X1(List<String> signals, HashMap<String, Gate> hm){				
		/* OAI21X1 (IN1,IN2,IN3,QN)
		 * 
		 * or (,IN1,IN2, g_2_out);
		 * nand #1 (IN3, g_2_out, QN);
		 * 
		 */
		
		//OR
		String intermediateOne = "OAI21X1_" + intermediateCount++ + "_out";
		super.addOr(signals.get(0), signals.get(1), intermediateOne, hm);
		
		//NAND
		super.addNand(signals.get(2), intermediateOne, signals.get(3), hm);
	}
	
	
}
