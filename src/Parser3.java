import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
//import java.util.Map.Entry;

public class Parser3 {

	public static void main(String[] args) {
		// FILE PATH
		String filePath = System.getProperty("user.dir"); // returns working directory
		System.out.println("This parser converts Verilog files (.v) into Bench files (.bench).\n"
				+ "It reads from the current directory this program is in /Verilog.\n"
				+ "Please enter the name of the file to read.");
		Scanner input = new Scanner(System.in);
		filePath += ("/Verilog/" + input.nextLine());
		
		System.out.println("Loading " + filePath + "\n");

		String clockSignal = "clk";

		File file = new File(filePath);

		// PARSE
		parse(file, clockSignal);
		input.close();
	}

	public static void parse(File file, String clockSignal) {
		/********************************************PARSE SETUP********************************************/
		
		//A list of gates not recognized, filled as parser loops through code
		List<String> noRecognize = new ArrayList<String>();
		
		// ArrayList of input and output names
		List<String> inputsName = new ArrayList<String>(); // ARRAY OF INS
		List<String> outputsName = new ArrayList<String>(); // ARRAY OF OUTS
		
		//keep track of what line of code you are on
		int lineNum = 0;
		
		//List of gate names to be parsed, IF YOU ADD A NEW GATE CLASS, BE SURE TO ADD ITS
		//NAME HERE
		String[] gateNames = {"input", "output", "BUF", "XOR2X1", "XNOR2X1", "XOR3X1",
				"XNOR3X1", "XOR4X1", "XNOR4X1", "AND", "OR", "NOR", 
				"NAND", "INV", "XNOR", "DFFSRX1", 
				"AOI22X1", "AOI21X1", "assign", "OAI21X1",
				"OA22X1", "OA221X1", "AOI221X1", "AO222X1", "AO22X1", "AO21X1", "NBUFFX2", 
				"DFFNX2", "ISOLANDX1", "DFFX2", "MUX21X1", "LSDNENX1", "SDFFX1", 
				"AO221X1", "LSDNX1", "OA21X1", "OA222X1", "OAI22X1", "OAI222X1", 
				"AOI222X1", "OAI221X1", "MUX21X2"};
		
		//Create hashmap of simple gates, string is their name, Gate is their data
		//Once a gateName from above is detected, it will be added/updated in the hashmap
		//with the relevant intput and output information.
		HashMap<String, Gate> hm = new HashMap<String, Gate>();

		/********************************************PARSE BEGIN********************************************/
		try {
			
			
			//Create a hash map of Buffer gate names
			HashMap<String, String> bufhm = new HashMap<String, String>();
			BufferedReader reader1 = new BufferedReader(new FileReader(file));
			String line1;
			//int kk = 0;
			while((line1 = reader1.readLine()) != null) {
				while (line1.startsWith(" "))// Remove leading white space
					line1 = line1.replaceFirst(" ", "");
				
				if(line1.length() > 5) {
					
					
					
					
					//BUF ("assign")
					if(line1.startsWith("assign")){
						//System.out.println(line1);
						String[] tokens = line1.split(" ");
						
						
						if(bufhm.containsKey(tokens[1])){
							//System.out.println("repeat");
							bufhm.put(bufhm.get(tokens[1]), tokens[3].substring(0, tokens[3].length()-1));
						}
						else
							bufhm.put(tokens[1], tokens[3].substring(0, tokens[3].length()-1));
						
						
						//System.out.println(tokens[1]);//key
						//System.out.println(tokens[3].substring(0, tokens[3].length()-1));//replacement
						//System.out.println();
						//kk++;
					}
					else if (line1.startsWith("BUF") || line1.startsWith("NBUFFX2") || line1.startsWith("LSDNX1")) {
						//System.out.println(findOutputInputsName(line1));//key
						//input, output
						//key, value
						//kk++;
						//System.out.println(line1);
						if(bufhm.containsKey(findOutputInputsName(line1, bufhm).get(0))){
							//System.out.println("repeat");
							bufhm.put(bufhm.get(findOutputInputsName(line1, bufhm).get(0)), findOutputInputsName(line1, bufhm).get(1));
							//System.out.println(findOutputInputsName(line1, bufhm).get(1) + "=" + bufhm.get(findOutputInputsName(line1, bufhm).get(0)));
						}
						else
							bufhm.put(findOutputInputsName(line1, bufhm).get(0), findOutputInputsName(line1, bufhm).get(1));
						
						//System.out.println();
					} 
				}
			}
			
			
			//Printing out buffer hash map
			//int l = 0;
			//for (Entry<String, String> entry : bufhm.entrySet()) {
			    //String key = entry.getKey();
			    //Object value = entry.getValue();
			    //System.out.println(key + " = " + value);
			    //l++;
			//}
			//System.out.println("read " + kk);
			//System.out.println("in hm " + l);
			
			reader1.close();
			
			//Reader for the input verilog file
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;

			while ((line = reader.readLine()) != null) {// Loop through each line in .v file
				lineNum++;//increase the line number
				
				while (line.startsWith(" "))// Remove leading white space
					line = line.replaceFirst(" ", "");
				
				if (!line.startsWith("//")) {// Skips if a comment
					
					if (line.length() > 5) {// Skips if short line
							
						/***********************************LOOKING FOR A GATE******************************/
						
						//If it spans multiple lines, put all into one (until you reach a ";")
						while (!(line.contains(";")) && !line.contains("endmodule")) {
							String nextLine;
							if((nextLine = reader.readLine()) == null)
								break;
							line += nextLine;
						}
						
						//Skip modules that have names that match a gate (redundant)
						for(int i = 0; i < gateNames.length; i++){
							if(line.contains("module " + gateNames[i])){
								//System.out.println(line);
								while((line = reader.readLine()) != null 
										&& !line.contains("endmodule")){}//loop until end of line								
								//System.out.println(line);
								break;
							}
						}
						
						//Any gates found in this line that match a gate in our gate list?
						boolean gateFound = false;
						for(int i = 0; i < gateNames.length; i++){
							if(line.startsWith(gateNames[i])){
								gateFound = true;
								break;
							}
						}
									
						
						/**********************************GATE DETECTION***************************************/
						//If a gate is found that we have a definition for, parse it
						if (gateFound) {
							

							// INPUTS
							if (line.startsWith("input")) {
								inputsName.addAll(findIoNames(line, bufhm));
							}

							// OUTPUTS
							else if (line.startsWith("output")) {
								outputsName.addAll(findIoNames(line, bufhm));
							}
							
							
							//AND
							else if (line.startsWith("AND")) {
								if(!hm.containsKey("AND"))
									hm.put("AND", new Gate());
								(hm.get("AND")).and.add(findOutputInputsName(line, bufhm));
								(hm.get("AND")).count++;
							} 
							//OR
							else if (line.startsWith("OR")) {
								if(!hm.containsKey("OR"))
									hm.put("OR", new Gate());
								(hm.get("OR")).or.add(findOutputInputsName(line, bufhm));
								(hm.get("OR")).count++;
							} 
							//INV
							else if (line.startsWith("INV")) {
								if(!hm.containsKey("INV"))
									hm.put("INV", new Gate());
								(hm.get("INV")).inv.add(findOutputInputsName(line, bufhm));
								(hm.get("INV")).count++;
							} 
							//NAND
							else if (line.startsWith("NAND")) {
								if(!hm.containsKey("NAND"))
									hm.put("NAND", new Gate());
								(hm.get("NAND")).nand.add(findOutputInputsName(line, bufhm));
								(hm.get("NAND")).count++;
							} 
							//NOR
							else if (line.startsWith("NOR")) {
								if(!hm.containsKey("NOR"))
									hm.put("NOR", new Gate());
								(hm.get("NOR")).nor.add(findOutputInputsName(line, bufhm));
								(hm.get("NOR")).count++;
							} 
							//FF
							else if (line.startsWith("DFFSRX1") || line.startsWith("DFFNX2") 
									|| line.startsWith("DFFX2") || line.startsWith("SDFFX1")) {
								List<String> temp = new ArrayList<String>();
								if (line.contains(".QN(") && line.contains(".Q(")) {// Both Q and QN
									String[] tokens = line.split("QN");
									if(!hm.containsKey("FF"))
										hm.put("FF", new Gate());
									//FF add
									(hm.get("FF")).ff.add(findOutputInputsNameForDff(tokens[0], clockSignal, ".Q", bufhm));
									(hm.get("FF")).count++;
									//INVERTER add
									if (tokens.length > 1) {
										String[] tokensInv;
										String[] invOuttokens;
										tokensInv = tokens[1].split("[)]");
										invOuttokens = tokensInv[0].split("[(]");
										
										if(invOuttokens.length >= 1){//not .Q()
											//inverter out
											temp.add(findOutputInputsNameForDff(tokens[0], clockSignal, ".Q", bufhm).get(1));
											//inverter in, 
											temp.add(invOuttokens[1]);
											//adding the inverter gate
											if(!hm.containsKey("INV"))
												hm.put("INV", new Gate());
											(hm.get("INV")).inv.add(temp);
											(hm.get("INV")).count++;
										}
										
									}
								} 
								else if (line.contains(".Q(")) {
									if(!hm.containsKey("FF"))
										hm.put("FF", new Gate());
									(hm.get("FF")).ff.add(findOutputInputsNameForDff(line, clockSignal, ".Q", bufhm));
									(hm.get("FF")).count++;
								} 
								else if (line.contains(".QN(")) {
									if(!hm.containsKey("INV"))
										hm.put("INV", new Gate());
									(hm.get("INV")).inv.add(findOutputInputsNameForDff(line, clockSignal, ".QN", bufhm));
									(hm.get("INV")).count++;
								}
							}
							
							
							//USER DEFINED GATES
							else if (line.startsWith("AOI22X1")) {
								if(!hm.containsKey("AOI22X1"))
									hm.put("AOI22X1", new AOI22X1());
								((AOI22X1) hm.get("AOI22X1")).addAOI22X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("AOI22X1")).count++;
							}
							else if (line.startsWith("AOI21X1")) {
								if(!hm.containsKey("AOI21X1"))
									hm.put("AOI21X1", new AOI21X1());
								((AOI21X1) hm.get("AOI21X1")).addAOI21X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("AOI21X1")).count++;
							}
							else if (line.startsWith("OAI21X1")) {
								if(!hm.containsKey("OAI21X1"))
									hm.put("OAI21X1", new OAI21X1());
								((OAI21X1) hm.get("OAI21X1")).addOAI21X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("OAI21X1")).count++;
							}
							else if (line.startsWith("OA22X1")) {
								if(!hm.containsKey("OA22X1"))
									hm.put("OA22X1", new OA22X1());
								((OA22X1) hm.get("OA22X1")).addOA22X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("OA22X1")).count++;
							}
							else if (line.startsWith("OA221X1")) {
								if(!hm.containsKey("OA221X1"))
									hm.put("OA221X1", new OA221X1());
								((OA221X1) hm.get("OA221X1")).addOA221X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("OA221X1")).count++;
							}
							else if (line.startsWith("AOI221X1")) {
								if(!hm.containsKey("AOI221X1"))
									hm.put("AOI221X1", new AOI221X1());
								((AOI221X1) hm.get("AOI221X1")).addAOI221X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("AOI221X1")).count++;
							}
							else if (line.startsWith("AO222X1")) {
								if(!hm.containsKey("AO222X1"))
									hm.put("AO222X1", new AO222X1());
								((AO222X1) hm.get("AO222X1")).addAO222X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("AO222X1")).count++;
							}
							else if (line.startsWith("AO22X1")) {
								if(!hm.containsKey("AO22X1"))
									hm.put("AO22X1", new AO22X1());
								((AO22X1) hm.get("AO22X1")).addAO22X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("AO22X1")).count++;
							}
							else if (line.startsWith("AO21X1")) {
								if(!hm.containsKey("AO21X1"))
									hm.put("AO21X1", new AO21X1());
								((AO21X1) hm.get("AO21X1")).addAO21X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("AO21X1")).count++;
							}
							else if (line.startsWith("ISOLANDX1")) {
								if(!hm.containsKey("ISOLANDX1"))
									hm.put("ISOLANDX1", new ISOLANDX1());
								((ISOLANDX1) hm.get("ISOLANDX1")).addISOLANDX1(findOutputInputsName(line, bufhm), hm);
								(hm.get("ISOLANDX1")).count++;
							}
							else if (line.startsWith("MUX21X1")) {
								if(!hm.containsKey("MUX21X1"))
									hm.put("MUX21X1", new MUX21X1());
								((MUX21X1) hm.get("MUX21X1")).addMUX21X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("MUX21X1")).count++;
							}
							else if (line.startsWith("MUX21X2")) {
								if(!hm.containsKey("MUX21X2"))
									hm.put("MUX21X2", new MUX21X1());
								((MUX21X1) hm.get("MUX21X2")).addMUX21X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("MUX21X2")).count++;
							}
							else if (line.startsWith("AO221X1")) {
								if(!hm.containsKey("AO221X1"))
									hm.put("AO221X1", new AO221X1());
								((AO221X1) hm.get("AO221X1")).addAO221X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("AO221X1")).count++;
							}
							else if (line.startsWith("OA21X1")) {
								if(!hm.containsKey("OA21X1"))
									hm.put("OA21X1", new OA21X1());
								((OA21X1) hm.get("OA21X1")).addOA21X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("OA21X1")).count++;
							}
							else if (line.startsWith("OA222X1")) {
								if(!hm.containsKey("OA222X1"))
									hm.put("OA222X1", new OA222X1());
								((OA222X1) hm.get("OA222X1")).addOA222X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("OA222X1")).count++;
							}
							else if (line.startsWith("OAI22X1")) {
								if(!hm.containsKey("OAI22X1"))
									hm.put("OAI22X1", new OAI22X1());
								((OAI22X1) hm.get("OAI22X1")).addOAI22X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("OAI22X1")).count++;
							}
							else if (line.startsWith("OAI222X1")) {
								if(!hm.containsKey("OAI222X1"))
									hm.put("OAI222X1", new OAI222X1());
								((OAI222X1) hm.get("OAI222X1")).addOAI222X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("OAI222X1")).count++;
							}
							else if (line.startsWith("AOI222X1")) {
								if(!hm.containsKey("AOI222X1"))
									hm.put("AOI222X1", new AOI222X1());
								((AOI222X1) hm.get("AOI222X1")).addAOI222X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("AOI222X1")).count++;
							}
							else if (line.startsWith("OAI221X1")) {
								if(!hm.containsKey("OAI221X1"))
									hm.put("OAI221X1", new OAI221X1());
								((OAI221X1) hm.get("OAI221X1")).addOAI221X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("OAI221X1")).count++;
							}
							else if (line.startsWith("XOR2X1")) {
								if(!hm.containsKey("XOR2X1"))
									hm.put("XOR2X1", new XOR2X1());
								((XOR2X1) hm.get("XOR2X1")).addXOR2X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("XOR2X1")).count++;
							}
							else if (line.startsWith("XNOR2X1")) {
								if(!hm.containsKey("XNOR2X1"))
									hm.put("XNOR2X1", new XNOR2X1());
								((XNOR2X1) hm.get("XNOR2X1")).addXNOR2X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("XNOR2X1")).count++;
							}
							else if (line.startsWith("XOR3X1")) {
								if(!hm.containsKey("XOR3X1"))
									hm.put("XOR3X1", new XOR3X1());
								((XOR3X1) hm.get("XOR3X1")).addXOR3X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("XOR3X1")).count++;
							}
							else if (line.startsWith("XNOR3X1")) {
								if(!hm.containsKey("XNOR3X1"))
									hm.put("XNOR3X1", new XNOR3X1());
								((XNOR3X1) hm.get("XNOR3X1")).addXNOR3X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("XNOR3X1")).count++;
							}
							else if (line.startsWith("XOR4X1")) {
								if(!hm.containsKey("XOR4X1"))
									hm.put("XOR4X1", new XOR4X1());
								((XOR4X1) hm.get("XOR4X1")).addXOR4X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("XOR4X1")).count++;
							}
							else if (line.startsWith("XNOR4X1")) {
								if(!hm.containsKey("XNOR4X1"))
									hm.put("XNOR4X1", new XNOR4X1());
								((XNOR4X1) hm.get("XNOR4X1")).addXNOR4X1(findOutputInputsName(line, bufhm), hm);
								(hm.get("XNOR4X1")).count++;
							}
						} 
						//IF YOU DON'T RECOGNIZE THE GATE, ADD TO LIST AND PRINT IT OUT
						else if (!line.contains("wire") && !line.contains("module") && line.contains("(")) {
							String[] search = line.split(" ");
							if(!noRecognize.contains(search[0])){
								System.out.println("Does not recognize gate " + search[0] + " on line" + lineNum);
								noRecognize.add(search[0]);
							}
						}
					}
				}
			}

			//Printwriter to fileName.bench
			PrintWriter out = new PrintWriter((file.getName()).replaceAll(".v", "") + ".bench");
			String key = "";

			//Printing totals amount of each gate
			//System.out.println("# " + inputsName.size() + " inputs");
			out.print("# " + inputsName.size() + " inputs\n");//total inputs
			//System.out.println("# " + outputsName.size() + " outputs");
			out.print("# " + outputsName.size() + " outputs\n");//total outputs
			out.print(printTotals(hm));//print totals of all other gates
			
			//PRINT INPUTS AND OUTPUTS
			out.print(printSignalIO("INPUT", inputsName));//input total
			out.print(printSignalIO("OUTPUT", outputsName));//output total
			//System.out.println("");
			
			//Loop through hash map and print each simple gate for every type
			for(Map.Entry<String, Gate> entry : hm.entrySet()){
		    	key = entry.getKey();
		    	out.print((hm.get(key)).printSignal("DFF", (hm.get(key)).ff)  );
			}
			//System.out.println("");
			out.print("\n");
			for(Map.Entry<String, Gate> entry : hm.entrySet()){
		    	key = entry.getKey();
		    	out.print((hm.get(key)).printSignal("NOT", (hm.get(key)).inv)  );
		    }
			//System.out.println("");
			out.print("\n");
			for(Map.Entry<String, Gate> entry : hm.entrySet()){
		    	key = entry.getKey();
		    	out.print((hm.get(key)).printSignal("AND", (hm.get(key)).and)  );
		    }
			//System.out.println("");
			out.print("\n");
			for(Map.Entry<String, Gate> entry : hm.entrySet()){
		    	key = entry.getKey();
		    	out.print((hm.get(key)).printSignal("NAND", (hm.get(key)).nand)  );
		    }
			out.print("\n");
			for(Map.Entry<String, Gate> entry : hm.entrySet()){
		    	key = entry.getKey();
		    	out.print((hm.get(key)).printSignal("OR", (hm.get(key)).or)  );
			}
			//System.out.println("");
			out.print("\n");
			for(Map.Entry<String, Gate> entry : hm.entrySet()){
		    	key = entry.getKey();
		    	out.print((hm.get(key)).printSignal("NOR", (hm.get(key)).nor)  );
		    }
			//System.out.println("");
			out.print("\n");
			for(Map.Entry<String, Gate> entry : hm.entrySet()){
		    	key = entry.getKey();
		    	out.print((hm.get(key)).printSignal("XOR", (hm.get(key)).xor)  );
		    }
			//System.out.println("");
			out.print("\n");
			for(Map.Entry<String, Gate> entry : hm.entrySet()){
		    	key = entry.getKey();
		    	out.print((hm.get(key)).printSignal("XNOR", (hm.get(key)).xnor)  );
		    }
			//System.out.println("");
			out.print("\n");
			for(Map.Entry<String, Gate> entry : hm.entrySet()){
		    	key = entry.getKey();
		    	out.print((hm.get(key)).printSignal("BUF", (hm.get(key)).buf)  );
		    }
			out.print("\n");
			//System.out.println("");

			out.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// Finds the names of input/outputs of any type gates
	public static List<String> findOutputInputsName(String line, HashMap<String, String> bufhm) {
			try {
				List<String> name = new ArrayList<String>();
				String[] tokens = line.split("[)]");// SPLIT INTO CHUNKS

				int numberOfInputs = tokens.length - 3;
				if (numberOfInputs == 1) {
					String[] out1 = tokens[1].split("[(]");
					String[] input1 = tokens[0].split("[(]");
					name.add(input1[2].trim());
					name.add(out1[1].trim());
				} else if (numberOfInputs == 2) {
					String[] input1 = tokens[0].split("[(]");
					name.add(input1[2].trim());
					String[] input2 = tokens[1].split("[(]");
					name.add(input2[1].trim());
					String[] out1 = tokens[2].split("[(]");
					name.add(out1[1].trim());
				} else if (numberOfInputs == 3) {
					String[] out1 = tokens[3].split("[(]");
					String[] input1 = tokens[0].split("[(]");
					name.add(input1[2].trim());
					String[] input2 = tokens[1].split("[(]");
					name.add(input2[1].trim());
					String[] input3 = tokens[2].split("[(]");
					name.add(input3[1].trim());
					name.add(out1[1].trim());
				} else if (numberOfInputs == 4) {
					String[] out1 = tokens[4].split("[(]");
					String[] input1 = tokens[0].split("[(]");
					name.add(input1[2].trim());
					String[] input2 = tokens[1].split("[(]");
					name.add(input2[1].trim());
					String[] input3 = tokens[2].split("[(]");
					name.add(input3[1].trim());
					String[] input4 = tokens[3].split("[(]");
					name.add(input4[1].trim());
					name.add(out1[1].trim());
				}else if (numberOfInputs == 5) {
					String[] out1 = tokens[5].split("[(]");
					String[] input1 = tokens[0].split("[(]");
					name.add(input1[2].trim());
					String[] input2 = tokens[1].split("[(]");
					name.add(input2[1].trim());
					String[] input3 = tokens[2].split("[(]");
					name.add(input3[1].trim());
					String[] input4 = tokens[3].split("[(]");
					name.add(input4[1].trim());
					String[] input5 = tokens[4].split("[(]");
					name.add(input5[1].trim());
					name.add(out1[1].trim());
				}  
				else if (numberOfInputs == 6) {
					String[] out1 = tokens[6].split("[(]");
					String[] input1 = tokens[0].split("[(]");
					name.add(input1[2].trim());
					String[] input2 = tokens[1].split("[(]");
					name.add(input2[1].trim());
					String[] input3 = tokens[2].split("[(]");
					name.add(input3[1].trim());
					String[] input4 = tokens[3].split("[(]");
					name.add(input4[1].trim());
					String[] input5 = tokens[4].split("[(]");
					name.add(input5[1].trim());
					String[] input6 = tokens[5].split("[(]");
					name.add(input6[1].trim());
					name.add(out1[1].trim());
				}  
				else if (numberOfInputs == 7) {
					String[] out1 = tokens[7].split("[(]");
					String[] input1 = tokens[0].split("[(]");
					name.add(input1[2].trim());
					String[] input2 = tokens[1].split("[(]");
					name.add(input2[1].trim());
					String[] input3 = tokens[2].split("[(]");
					name.add(input3[1].trim());
					String[] input4 = tokens[3].split("[(]");
					name.add(input4[1].trim());
					String[] input5 = tokens[4].split("[(]");
					name.add(input5[1].trim());
					String[] input6 = tokens[5].split("[(]");
					name.add(input6[1].trim());
					String[] input7 = tokens[6].split("[(]");
					name.add(input7[1].trim());
					name.add(out1[1].trim());
				}  
				else {
					System.out.println(
							"Gate inputs is over the limit: " + numberOfInputs + "Update findOutputInputsName.\n");
					return null;
				}
				
				//Update for buffer values
				String temp = "";
				for(int i = 0; i < name.size(); i++){
					temp = name.get(i);
					while(bufhm.containsKey(temp)){
						temp = bufhm.get(temp);
						name.set(i, bufhm.get(name.get(i)));
					}
				}

				return name;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}
	
	// Prints comments at top of file of how many of each simple gate was found
	private static String printTotals(HashMap<String, Gate> hm) {
		String out = "";
		if(hm.containsKey("FF")){	
			//System.out.println("# " + (hm.get("DFF")).count + " D-type flipflops");
			out += "# " + (hm.get("FF")).count + " D-type flipflops\n";
		}if(hm.containsKey("INV")){	
			//System.out.println("# " + (hm.get("INV")).count + " inverters");
			out += "# " + (hm.get("INV")).count + " inverters\n";
		}
		
		//find total count
		int count = 0;
		if(hm.containsKey("AND")){	
			//System.out.print((hm.get("AND")).count + " ANDS + ");
			count += (hm.get("AND")).count;
		}if(hm.containsKey("NAND")){	
			//System.out.print((hm.get("NAND")).count + " NANDS + ");
			count += (hm.get("NAND")).count;
		}if(hm.containsKey("OR")){	
			//System.out.print((hm.get("OR")).count + " ORS + ");
			count += (hm.get("OR")).count;
		}if(hm.containsKey("NOR")){	
			//System.out.print((hm.get("NOR")).count + " NORS + ");
			count += (hm.get("NOR")).count;
		}if(hm.containsKey("BUF")){	
			//System.out.print((hm.get("XOR")).count + " XORS");
			count += (hm.get("BUF")).count;
		}	
		out += "# " + count +" gates (";
		
		//Find individual count
		if(hm.containsKey("AND")){	
			//System.out.print((hm.get("AND")).count + " ANDS + ");
			out += (hm.get("AND")).count + " ANDs";
		}if(hm.containsKey("NAND")){	
			//System.out.print((hm.get("NAND")).count + " NANDS + ");
			if(out.charAt(out.length()-1) != '(')
				out += " + ";
			out += (hm.get("NAND")).count + " NANDs";
		}if(hm.containsKey("OR")){	
			//System.out.print((hm.get("OR")).count + " ORS + ");
			if(out.charAt(out.length()-1) != '(')
				out += " + ";
			out += (hm.get("OR")).count + " ORs";
		}if(hm.containsKey("NOR")){	
			//System.out.print((hm.get("NOR")).count + " NORS + ");
			if(out.charAt(out.length()-1) != '(')
				out += " + ";
			out += (hm.get("NOR")).count + " NORs";
		}if(hm.containsKey("XOR")){	
			//System.out.print((hm.get("XOR")).count + " XORS");
			if(out.charAt(out.length()-1) != '(')
				out += " + ";
			out += (hm.get("XOR")).count + " XORs";
		}	
		if(hm.containsKey("XNOR")){	
			//System.out.print((hm.get("XOR")).count + " XORS");
			if(out.charAt(out.length()-1) != '(')
				out += " + ";
			out += (hm.get("XNOR")).count + " XNORs";
		}
		if(hm.containsKey("BUF")){	
			//System.out.print((hm.get("XOR")).count + " XORS");
			if(out.charAt(out.length()-1) != '(')
				out += " + ";
			out += (hm.get("BUF")).count + " BUFs";
		}
		//System.out.println("\n");
		out += ")\n\n";
		return out;
	}

	// Prints input and output signals in bench format
	public static String printSignalIO(String name, List<String> insOuts) {
		String master = "";
		for (int i = 0; i < insOuts.size(); i++) {
			master += (name + "(" + insOuts.get(i) + ")\n");
		}
		if (master != "") {
			//System.out.println(master);
			return (master + "\n");
		} else
			return "";
	}

	// Finds the names for input/output signals
	private static List<String> findIoNames(String line, HashMap<String, String> bufhm) {
		List<String> result = new ArrayList<String>();
		
		//ONE IN THE LINE
		if (!line.contains(",")) {
			String[] tokens = line.split(" ");
			
			//Is a list:: input [2:0] name
			if(tokens[1].contains("[")){
				
				String endString = tokens[1].substring(1, tokens[1].indexOf(":"));
				int end = Integer.parseInt(endString);
				String beginningString = tokens[1].substring(tokens[1].indexOf(":")+1, tokens[1].indexOf("]"));
				int beg = Integer.parseInt(beginningString);
				
				for(int i = beg; i <= end; i++){
					result.add(tokens[2].replaceAll(";", "") + "[" + i + "]");
				}
			}
			else{
				String inputName = tokens[1].substring(0, tokens[1].length() - 1);
				result.add(inputName);
			}
		} 
		
		//A WHOLE LIST
		else {
			String[] tokens = line.split(" ");
			int num = tokens.length;				
			//no spaces between commas
			if(tokens[1].indexOf(",") != tokens[1].length()-1){
				String redo = "";
				redo += ",";
				for(int i = 1; i < tokens.length; i++){
					redo += tokens[i];
				}
				tokens = redo.split(",");
				num = tokens.length;
			}
			
			for (int i = 1; i < num; i++) {//loop through whole list
				if (!tokens[i].equals("")) {
					
					//IF IS A BUS
					if(tokens[i].contains("[")){
						
						//Find beginning and ending indexes
						String endString = tokens[i].substring(1, tokens[i].indexOf(":"));
						int end = Integer.parseInt(endString);
						String beginningString = tokens[i].substring(tokens[i].indexOf(":")+1, tokens[i].indexOf("]"));
						int beg = Integer.parseInt(beginningString);
												
						//Add every signal to result
						for(int j = beg; j <= end; j++){
							if(tokens[i].contains(";") || tokens[i].contains(",")){
								result.add(tokens[i].substring(tokens[i].indexOf("]")+1, tokens[i].length()-1) + "[" + j + "]");
							}
							else
								result.add(tokens[i].substring(tokens[i].indexOf("]")+1) + "[" + j + "]");
						}
					}
					else{
						if(tokens[i].contains(";") || tokens[i].contains(","))
							result.add(tokens[i].substring(0, tokens[i].length() - 1));
						else
							result.add(tokens[i]);
					}
						
				}
			}
		}
		
		//Update for buffer values
		String temp = "";
		for(int i = 0; i < result.size(); i++){
			temp = result.get(i);
			while(bufhm.containsKey(temp)){
				temp = bufhm.get(temp);
				result.set(i, bufhm.get(result.get(i)));
			}
		}
		
		return result;
	}

	// Finds the i/o names for DFFs
	public static List<String> findOutputInputsNameForDff(String line, String ClockName, String spilitStr, HashMap<String, String> bufhm) {
		try {
			List<String> name = new ArrayList<String>(); //input, output
			String[] tokens = line.split("[)]");
			
			String output = "";
			String input = "";
			
			//OUTPUT Q
			for(int i = 0; i < tokens.length; i++){
				if(tokens[i].contains(".Q")){
					int beg = tokens[i].lastIndexOf('(');
					
					output = tokens[i].substring(beg+1);
					//System.out.println("temp" + output);
					for(int j = 0; j < output.length(); j++){
						//System.out.println("charAt" + output.charAt(j)+"farts");

						if(output.charAt(j) != ' '){
							//System.out.println("succ");
							output = output.substring(j);
							break;
						}
					}
					
					//System.out.println("OUTPUT" + output + "mmmmm");
					break;
				}
			}
			
			//INPUT D
			for(int i = 0; i < tokens.length; i++){
				if(tokens[i].contains(".D")){
					int beg = tokens[i].lastIndexOf('(');
					input = tokens[i].substring(beg+1);
					for(int j = 0; j < input.length(); j++){
						//System.out.println("charAt" + input.charAt(j)+"farts");
						if(input.charAt(j) != ' '){
							//System.out.println("succ");
							input = input.substring(j);
							break;
						}
					}
					//System.out.println("intput" + input + "mmmmm");
					break;
				}
			}
			
			name.add(input);
			name.add(output);

			//Update for buffer values
			String temp = "";
			for(int i = 0; i < name.size(); i++){
				temp = name.get(i);
				while(bufhm.containsKey(temp)){
					temp = bufhm.get(temp);
					name.set(i, bufhm.get(name.get(i)));
				}
			}
			
			return name;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
