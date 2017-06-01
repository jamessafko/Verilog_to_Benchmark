import java.util.HashMap;
import java.util.List;

public class XOR4X1 extends Gate{
	int intermediateCount = 0;


	public XOR4X1(){
		super();
	}
		
	public void addXOR4X1(List<String> signals, HashMap<String, Gate> hm){				
		//signals = inOut[A, B, C, D, E]
		//not(A, A_not)
		//not(B, B_not)
		//And1 (A_not, B, xor_1)
		//And2 (A, B_not, xor_2)
		//Or (xor_1, xor_2, xor_3)
		
		//not(C, C_not)
		//not(xor_3, xor_3_not)
		//And1 (C_not, xor_3, xor_4)
		//And2 (C, xor_3_not, xor_5)
		//Or (xor_4, xor_5, xor_6)
		
		//not(D, D_not)
		//not(xor_6, xor_6_not)
		//And1 (D_not, xor_6, xor_7)
		//And2 (D, xor_6_not, xor_8)
		//Or (xor_7, xor_8, E)
		
		//LEVEL ONE///////////////////////////////////////////////////////
		//NOT GATE
		String notA = "not_" + signals.get(0);
		super.addNot(signals.get(0), notA, hm);
		
		//NOT GATE
		String notB = "not_" + signals.get(1);
		super.addNot(signals.get(1), notB, hm);
		
		//AND GATE
		String intermediateOne = "XOR4X1_" + intermediateCount++ + "_out";
		super.addAnd(notA, signals.get(1), intermediateOne, hm);
		
		//AND GATE
		String intermediateTwo = "XOR4X1_" + intermediateCount++ + "_out";
		super.addAnd(signals.get(0), notB, intermediateTwo, hm);
		
		//OR GATE
		String out1 = "XOR4X1_" + intermediateCount++ + "_out";
		super.addOr(intermediateOne, intermediateTwo, out1, hm);
		
		
		//LEVEL TWO///////////////////////////////////////////////////////
		//NOT GATE
		String notOut1 = "not_" + out1;
		super.addNot(out1, notOut1, hm);
		
		//NOT GATE
		String notC = "not_" + signals.get(2);
		super.addNot(signals.get(2), notC, hm);
		
		//AND GATE
		String intermediateThree = "XOR4X1_" + intermediateCount++ + "_out";
		super.addAnd(out1, notC, intermediateThree, hm);
		
		//AND GATE
		String intermediateFour = "XOR4X1_" + intermediateCount++ + "_out";
		super.addAnd(notOut1, signals.get(2), intermediateFour, hm);
		
		//OR GATE
		String out2 = "XOR4X1_" + intermediateCount++ + "_out";
		super.addOr(intermediateThree, intermediateFour, out2, hm);
		
		
		//LEVEL THREE///////////////////////////////////////////////////////
		//NOT GATE
		String notOut2 = "not_" + out2;
		super.addNot(out2, notOut2, hm);
		
		//NOT GATE
		String notD = "not_" + signals.get(3);
		super.addNot(signals.get(3), notD, hm);
		
		//AND GATE
		String intermediateFive = "XOR4X1_" + intermediateCount++ + "_out";
		super.addAnd(out2, notD, intermediateFive, hm);
		
		//AND GATE
		String intermediateSix = "XOR4X1_" + intermediateCount++ + "_out";
		super.addAnd(notOut2, signals.get(2), intermediateSix, hm);
		
		//OR GATE
		super.addOr(intermediateFive, intermediateSix, signals.get(4), hm);
	}
	
	
}
