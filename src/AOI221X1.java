import java.util.HashMap;
import java.util.List;

public class AOI221X1 extends Gate{
	int intermediateCount = 0;

	public AOI221X1(){
		super();
	}
		
	public void addAOI221X1(List<String> signals, HashMap<String, Gate> hm){				
		//AOI221X1 (IN1,IN2,IN3,IN4,IN5,Q);
		//and (in1, in2, g_1_out) where g_1_out is intermediate gate
		//and (in3, in4, g_2_out)
		//nor (in5, g_1_out, G_2_OUT, QN)
		
		//And GATE
		String intermediateOne = "AOI221X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(0), signals.get(1), intermediateOne, hm);
		
		//And GATE
		String intermediateTwo = "AOI221X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(2), signals.get(3), intermediateTwo, hm);
		
		//Nor GATE
		super.addNor(signals.get(4), intermediateOne, intermediateTwo, signals.get(5), hm);
	}
}
