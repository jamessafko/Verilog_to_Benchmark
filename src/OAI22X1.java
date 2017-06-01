import java.util.HashMap;
import java.util.List;

public class OAI22X1 extends Gate{
	int intermediateCount = 0;


	public OAI22X1(){
		super();
	}
		
	public void addOAI22X1(List<String> signals, HashMap<String, Gate> hm){				
		//signals = inOut[in1, in2, in3, in4, outNot]
		//or (in1, in2, g_1_out) where g_1_out is intermediate gate
		//or (in3, in4, g_2_out)
		//nand (g_1_out, G_2_OUT, QN)
		
		//or GATE
		String intermediateOne = "OAI22X1_" + intermediateCount++ + "_out";
		super.addOr(signals.get(0), signals.get(1), intermediateOne, hm);
		
		//or GATE
		String intermediateTwo = "OAI22X1_" + intermediateCount++ + "_out";
		super.addOr(signals.get(2), signals.get(3), intermediateTwo, hm);
		
		//nand GATE
		super.addNand(intermediateOne, intermediateTwo, signals.get(4), hm);
	}
	
	
}
