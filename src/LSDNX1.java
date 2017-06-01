import java.util.HashMap;
import java.util.List;

public class LSDNX1 extends Gate{
	int intermediateCount = 0;


	public LSDNX1(){
		super();
	}
		
	public void addLSDNX1(List<String> signals, HashMap<String, Gate> hm){						
		super.addBuf(signals.get(0), signals.get(1), hm);		
	}
	
	
}
