import java.util.HashMap;
import java.util.List;

public class XOR3X1 extends Gate{
	int intermediateCount = 0;


	public XOR3X1(){
		super();
	}
		
	public void addXOR3X1(List<String> signals, HashMap<String, Gate> hm){				
		//signals = inOut[A, B, C, D]
		//not(A, A_not)
		//not(B, B_not)
		//And1 (A_not, B, xor_1)
		//And2 (A, B_not, xor_2)
		//Or (xor_1, xor_2, xor_3)
		
		//not(C, C_not)
		//not(xor_3, xor_3_not)
		//And1 (C_not, xor_3, xor_4)
		//And2 (C, xor_3_not, xor_5)
		//Or (xor_4, xor_5, D)
		
		//LEVEL ONE///////////////////////////////////////////////////////
		//NOT GATE
		String notA = "not_" + signals.get(0);
		super.addNot(signals.get(0), notA, hm);
		
		//NOT GATE
		String notB = "not_" + signals.get(1);
		super.addNot(signals.get(1), notB, hm);
		
		//AND GATE
		String intermediateOne = "XOR3X1_" + intermediateCount++ + "_out";
		super.addAnd(notA, signals.get(1), intermediateOne, hm);
		
		//AND GATE
		String intermediateTwo = "XOR3X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(0), notB, intermediateTwo, hm);
		
		//OR GATE
		String out1 = "XOR3X1_" + intermediateCount++ + "_out";
		super.addOr(intermediateOne, intermediateTwo, out1, hm);
		
		
		//LEVEL TWO///////////////////////////////////////////////////////
		//NOT GATE
		String notOut1 = "not_" + out1;
		super.addNot(out1, notOut1, hm);
		
		//NOT GATE
		String notC = "not_" + signals.get(2);
		super.addNot(signals.get(2), notC, hm);
		
		//AND GATE
		String intermediateThree = "XOR3X1_" + intermediateCount++ + "_out";
		super.addAnd(out1, notC, intermediateThree, hm);
		
		//AND GATE
		String intermediateFour = "XOR3X1_" + intermediateCount++ + "_out";
		super.addAnd(notOut1, signals.get(2), intermediateFour, hm);
		
		//OR GATE
		super.addOr(intermediateThree, intermediateFour, signals.get(3), hm);
	}
	
	
}
