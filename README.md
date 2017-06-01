This program takes a Verilog file and parses it to a benchmark file.

HOW IT WORKS
Gates are stored into a hash map with the key being the gate name and the value
being a Gate object. Each gate object has ArrayLists of each simple gates 
(flip flop, xor, and, etc).
A complex gate is created with it's corresponding class name by adding to it's parent (Gate)
simple gate ArrayLists.



RUNNING
Put the desired Verilog file into the Verilog folder.
When you run the program, you will be prompted to type in the file name you intend to parse.
If any unrecognized complex gates are found, the console will print them out.
The new benchmark file will be written and saved into the Parser3 folder.

ADDING A NEW COMPLEX GATE DEFINITION
If you need to add a new gate definition, add a new class with the name of the gate.
Check out any other gate definition to reference the format of creating the class.
Make sure to call super.add(simple gate name here) for every simple gate within the complex
gate.

Then, in Parser3.java, add that class name to the gateNames string array.

Lastly, in Parser3.java, add the following to the if(gateFound) block:

else if (line.startsWith("classNameHere")) {
	if(!hm.containsKey("classNameHere"))
		hm.put("classNameHere", new classNameHere());
	((classNameHere) hm.get("classNameHere")).addclassNameHere(findOutputInputsName(line, bufhm), hm);
	(hm.get("classNameHere")).count++;
}

