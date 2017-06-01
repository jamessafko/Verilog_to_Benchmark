import java.util.HashMap;
import java.util.List;

public class AO22X1 extends Gate{
	int intermediateCount = 0;


	public AO22X1(){
		super();
	}
		
	public void addAO22X1(List<String> signals, HashMap<String, Gate> hm){				
		//AO22X1 (IN1,IN2,IN3,IN4,Q);
		//and (in1, in2, g_1_out)
		//and (in3, in4, g_2_out)
		//or (g_1_out,g_2_out, Q)
		
		//and GATE
		String intermediateOne = "AO22X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(0), signals.get(1), intermediateOne, hm);
		
		//and GATE
		String intermediateTwo = "AO22X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(2), signals.get(3), intermediateTwo, hm);
		
		//or GATE
		super.addOr(intermediateOne, intermediateTwo, signals.get(4), hm);
	}
	
	
}
