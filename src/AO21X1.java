import java.util.HashMap;
import java.util.List;

public class AO21X1 extends Gate{
	
	int intermediateCount = 0;
	
	public AO21X1(){
		super();
	}
		
	public void addAO21X1(List<String> signals, HashMap<String, Gate> hm){				
		//AO21X1 (IN1,IN2,IN3,Q);
		//AND (in1, in2, g_1_out)
		//OR (int3, g_1_out, Q)
		
		//AND GATE
		String intermediateOne = "AO21X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(0), signals.get(1), intermediateOne, hm);
		
		
		//OR GATE
		super.addOr(signals.get(2), intermediateOne, signals.get(3), hm);
	}
	
	
}
