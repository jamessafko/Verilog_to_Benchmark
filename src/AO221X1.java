import java.util.HashMap;
import java.util.List;

public class AO221X1 extends Gate{


	int intermediateCount = 0;

	public AO221X1(){
		super();
	}
		
	public void addAO221X1(List<String> signals, HashMap<String, Gate> hm){				
		//AO221X1 (IN1,IN2,IN3,IN4,IN5,Q);
		//And(in1, in2, g_1_out)
		//ANd(in3, in4, g_2_out)
		//or (g_1_out, g_2_out, in5, q)
		
		
		//And GATE
		String intermediateOne = "AO221X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(0), signals.get(1), intermediateOne, hm);
		
		//And GATE
		String intermediateTwo = "AO221X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(2), signals.get(3), intermediateTwo, hm);
		
		//Or GATE
		super.addOr(intermediateOne, intermediateTwo, signals.get(4), signals.get(5), hm);
	}
	
	
}
