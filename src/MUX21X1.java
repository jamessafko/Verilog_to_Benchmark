import java.util.HashMap;
import java.util.List;

public class MUX21X1 extends Gate{
	int intermediateCount = 0;


	public MUX21X1(){
		super();
	}
		
	public void addMUX21X1(List<String> signals, HashMap<String, Gate> hm){				
		//MUX21X1 (IN1,IN2,S,Q);
		//NOT (SEL, not_SEL)
		//AND (IN1, not_SEL, MUX21X1_1)
		//AND (IN2, SEL, MUX21X1_1)
		//OR(MUX21X1_1, MUX21X1_1, !)
		
		//NOT
		String nots = "not_" + signals.get(2);
		super.addNot(signals.get(2), nots, hm);
		
		//AND1
		String intermediateOne = "MUX21X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(0), nots, intermediateOne, hm);
		
		//AND2
		String intermediateTwo = "MUX21X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(2), signals.get(1), intermediateTwo, hm);
		
		//OR
		super.addOr(intermediateOne, intermediateTwo, signals.get(3), hm);
	}
	
	
}
