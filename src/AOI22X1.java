import java.util.HashMap;
import java.util.List;

public class AOI22X1 extends Gate{
	
	int intermediateCount = 0;


	
	public AOI22X1(){
		super();
	}
		
	public void addAOI22X1(List<String> signals, HashMap<String, Gate> hm){				
		//signals = inOut[in1, in2, in3, in4, outNot]
		//and1 (in1, in2, g_1_out) where g_1_out is intermediate gate
		//and2 (in3, in4, g_2_out)
		//nor (g_1_out, G_2_OUT, QN)
		
		//AND1 GATE
		String intermediateOne = "AOI22X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(0), signals.get(1), intermediateOne, hm);
		
		//AND2 GATE
		String intermediateTwo = "AOI22X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(2), signals.get(3), intermediateTwo, hm);
		
		//NOR GATE
		super.addNor(intermediateOne, intermediateTwo, signals.get(4), hm);
	}
	
	
}
