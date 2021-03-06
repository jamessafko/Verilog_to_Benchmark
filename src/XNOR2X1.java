import java.util.HashMap;
import java.util.List;

public class XNOR2X1 extends Gate{
	int intermediateCount = 0;


	public XNOR2X1(){
		super();
	}
		
	public void addXNOR2X1(List<String> signals, HashMap<String, Gate> hm){				
		//signals = inOut[A, B, C]
		//not(A, A_not)
		//not(B, B_not)
		//And1 (A_not, B, xor_1)
		//And2 (A, B_not, xor_2)
		//Or (xor_1, xor_2, xor_3)
		//NOT(xor_3, C)
		
		//NOT GATE
		String intermediateOne = "not_" + signals.get(0);
		super.addNot(signals.get(0), intermediateOne, hm);
		
		//NOT GATE
		String intermediateTwo = "not_" + signals.get(1);
		super.addNot(signals.get(1), intermediateTwo, hm);
		
		//AND GATE
		String intermediateThree = "XNOR2X1_" + intermediateCount++ + "_out";
		super.addAnd(intermediateOne, signals.get(1), intermediateThree, hm);
		
		//AND GATE
		String intermediateFour = "XNOR2X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(0), intermediateTwo, intermediateFour, hm);
		
		//OR GATE
		String intermediateFive = "not_" + signals.get(1);
		super.addOr(intermediateThree, intermediateFour, intermediateFive, hm);
		
		//NOT GATE
		super.addNot(intermediateFive, signals.get(2), hm);
	}
	
	
}
