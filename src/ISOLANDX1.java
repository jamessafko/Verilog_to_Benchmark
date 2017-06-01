import java.util.HashMap;
import java.util.List;

public class ISOLANDX1 extends Gate{
	int intermediateCount = 0;


	public ISOLANDX1(){
		super();
	}
		
	public void addISOLANDX1(List<String> signals, HashMap<String, Gate> hm){				
		//ISOLANDX1 (ISO,D,Q);
		//NOT (ISO, not_ISO)
		//AND (D, NOT_ISO, Q)

		//NOT
		String intermediateOne = "not_" + signals.get(0);
		super.addNot(signals.get(0), intermediateOne, hm);
		
		//AND
		super.addAnd(intermediateOne, signals.get(1), signals.get(2), hm);
	}
	
	
}
