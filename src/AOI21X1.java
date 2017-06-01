import java.util.HashMap;
import java.util.List;

public class AOI21X1 extends Gate{
	int intermediateCount = 0;


	public AOI21X1(){
		super();
	}
		
	public void addAOI21X1(List<String> signals, HashMap<String, Gate> hm){				
		//signals = inOut[in1, in2, in3, outNot]
		//and1 (in1, in2, g_1_out) where g_1_out is intermediate gate
		//nor (g_1_out, in3, QN)
		
		//AND GATE
		String intermediateOne = "AOI21X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(0), signals.get(1), intermediateOne, hm);
		
		//NOR GATE
		super.addNor(intermediateOne, signals.get(2), signals.get(3), hm);
	}
	
	
}
