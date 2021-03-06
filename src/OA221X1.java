import java.util.HashMap;
import java.util.List;

public class OA221X1 extends Gate{
	int intermediateCount = 0;


	public OA221X1(){
		super();
	}
		
	public void addOA221X1(List<String> signals, HashMap<String, Gate> hm){				
		//OA221X1 (IN1,IN2,IN3,IN4,IN5,Q);
		//or (in1, in2, g_1_out) where g_1_out is intermediate gate
		//or (in3, in4, g_2_out)
		//and (in5, g_1_out, G_2_OUT, QN)
		
		//OR GATE
		String intermediateOne = "OA221X1_" + intermediateCount++ + "_out";
		super.addOr(signals.get(0), signals.get(1), intermediateOne, hm);
		
		//or GATE
		String intermediateTwo = "OA221X1_" + intermediateCount++ + "_out";
		super.addOr(signals.get(2), signals.get(3), intermediateTwo, hm);
		
		//AND GATE
		super.addAnd(signals.get(4), intermediateOne, intermediateTwo, signals.get(5), hm);
	}
	
	
}
