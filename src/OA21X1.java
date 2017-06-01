import java.util.HashMap;
import java.util.List;

public class OA21X1 extends Gate{
	int intermediateCount = 0;


	public OA21X1(){
		super();
	}
		
	public void addOA21X1(List<String> signals, HashMap<String, Gate> hm){				
		//AO21X1 (IN1,IN2,IN3,Q);
		//or (in1, in2, g_1_out)
		//and (int3, g_1_out, Q)
		
		//OR GATE
		String intermediateOne = "OA21X1_" + intermediateCount++ + "_out";
		super.addOr(signals.get(0), signals.get(1), intermediateOne, hm);
		
		
		//AND GATE
		super.addAnd(signals.get(2), intermediateOne, signals.get(3), hm);
	}
	
	
}
