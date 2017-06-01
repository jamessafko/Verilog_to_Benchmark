import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//This Class is a parent class for all gate types. Each complex gate class (which is a child of Gate) 
//can have a different amounts of simple gates within it. Thus, Gate holds a list of each simple gate
//and a number of functions used to add to those lists.
//Note that each instance of Gate is stored in a hash map (see Parser main class) where the key
//is the name of the gate (whether it be simple or complex.)
//See any of the complex gate classes to understand how to define your own gate class.
public class Gate {
	//Array of Array for each simple gate type.
	//Going through the outer array goes through each instance of that gate type
	//The inner array is a list (input1Name, input2Name... inputNName, outputName)
	List<List<String>> ff; 
	List<List<String>> buf; 
	List<List<String>> xor; 
	List<List<String>> and;
	List<List<String>> or; 
	List<List<String>> inv;
	List<List<String>> nand;
	List<List<String>> nor;
	List<List<String>> xnor;
	
	int count;//Number of types of this gate
	
	public Gate(){
		count = 0;
		ff = new ArrayList<List<String>>();
		buf = new ArrayList<List<String>>();
		xor = new ArrayList<List<String>>();
		and = new ArrayList<List<String>>();
		or = new ArrayList<List<String>>();
		inv = new ArrayList<List<String>>();
		nand = new ArrayList<List<String>>();
		nor = new ArrayList<List<String>>();
		xnor = new ArrayList<List<String>>();
	}
	
	//////////////////////////////////////////////ADD FUNCTIONS////////////////////////////////////////
	//Each one of these functions adds a new list (input1Name, input2Name... inputNName, outputName) to
	//the specified gate list. I.E. addNot, takes in one and out and adds that list to the inv List.
	
	public void addNot(String one, String out, HashMap<String, Gate> hm){
		List<String> forNot = new ArrayList<String>();
		forNot.add(one);
		forNot.add(out);
		inv.add(forNot);
		if(!hm.containsKey("INV"))
			hm.put("INV", new Gate());
		(hm.get("INV")).count++;
	}
	
	public void addAnd(String one, String two, String three, String out, HashMap<String, Gate> hm){
		List<String> forAnd = new ArrayList<String>();
		forAnd.add(one);
		forAnd.add(two);
		forAnd.add(three);
		forAnd.add(out);
		and.add(forAnd);
		if(!hm.containsKey("AND"))
			hm.put("AND", new Gate());
		(hm.get("AND")).count++;
	}
	
	public void addAnd(String one, String two, String out, HashMap<String, Gate> hm){
		List<String> forAnd = new ArrayList<String>();
		forAnd.add(one);
		forAnd.add(two);
		forAnd.add(out);
		and.add(forAnd);
		if(!hm.containsKey("AND"))
			hm.put("AND", new Gate());
		(hm.get("AND")).count++;
	}
	
	public void addNor(String one, String two, String out, HashMap<String, Gate> hm){
		List<String> forNor = new ArrayList<String>();
		forNor.add(one);
		forNor.add(two);
		forNor.add(out);
		nor.add(forNor);
		if(!hm.containsKey("NOR"))
			hm.put("NOR", new Gate());
		(hm.get("NOR")).count++;
	}
	
	public void addNor(String one, String two, String three, String out, HashMap<String, Gate> hm){
		List<String> forNor = new ArrayList<String>();
		forNor.add(one);
		forNor.add(two);
		forNor.add(three);
		forNor.add(out);
		nor.add(forNor);
		if(!hm.containsKey("NOR"))
			hm.put("NOR", new Gate());
		(hm.get("NOR")).count++;
	}
	
	public void addOr(String one, String two, String out, HashMap<String, Gate> hm){
		List<String> forOr = new ArrayList<String>();
		forOr.add(one);
		forOr.add(two);
		forOr.add(out);
		or.add(forOr);
		if(!hm.containsKey("OR"))
			hm.put("OR", new Gate());
		(hm.get("OR")).count++;
	}
	
	public void addOr(String one, String two, String three, String out, HashMap<String, Gate> hm){
		List<String> forOr = new ArrayList<String>();
		forOr.add(one);
		forOr.add(two);
		forOr.add(three);
		forOr.add(out);
		or.add(forOr);
		if(!hm.containsKey("OR"))
			hm.put("OR", new Gate());
		(hm.get("OR")).count++;
	}
	
	public void addBuf(String one, String out, HashMap<String, Gate> hm){
		List<String> forBuf = new ArrayList<String>();
		forBuf.add(one);
		forBuf.add(out);
		buf.add(forBuf);
		if(!hm.containsKey("BUF"))
			hm.put("BUF", new Gate());
		(hm.get("BUF")).count++;
	}
	
	public void addNand(String one, String two, String out, HashMap<String, Gate> hm){
		List<String> forNand = new ArrayList<String>();
		forNand.add(one);
		forNand.add(two);
		forNand.add(out);
		nand.add(forNand);
		if(!hm.containsKey("NAND"))
			hm.put("NAND", new Gate());
		(hm.get("NAND")).count++;
	}
	
	public void addNand(String one, String two, String three, String out, HashMap<String, Gate> hm){
		List<String> forNand = new ArrayList<String>();
		forNand.add(one);
		forNand.add(two);
		forNand.add(three);
		forNand.add(out);
		nand.add(forNand);
		if(!hm.containsKey("NAND"))
			hm.put("NAND", new Gate());
		(hm.get("NAND")).count++;
	}
	
	// Prints any gate (other than input/output) signals in bench format
	public String printSignal(String name, List<List<String>> insOutsList) {
		String master = "";

		for (int i = 0; i < insOutsList.size(); i++) {// # of gates
			master += (insOutsList.get(i)).get((insOutsList.get(i)).size() -1) + " = " + name + "(";
			for (int j = 0; j < ((insOutsList.get(i)).size())-1; j++) {// number
				if (j == ((insOutsList.get(i)).size() - 2))
					master += (insOutsList.get(i)).get(j);
				else
					master += (insOutsList.get(i)).get(j) + ", ";
			}
			master += ")\n";
		}
		if (master != "" && master != "\n") {
			//System.out.print(master);
			return (master);
		} else
			return "";
	}
}
