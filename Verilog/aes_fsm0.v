module aes_fsm0 (state_present, state_next, extra_FF, n10004, clk, data_stable, key_ready, finished, round_type_sel ); 
    output [1:0] round_type_sel;  
  
 input [2:0] state_present, [2:0] state_next; 
input [3:0] extra_FF; 
output n10004; 
 
 wire   n10000, n10001, n10002, n10003, n10004; 
    input clk, data_stable, key_ready;  
    output finished;  
    wire   n8, n9, n18, n19, n20, n21, n22, n23, n24, n25, n26, n27, n28, n29,  
           n30, n31, n32, n33, n34, n35, n36, n37, n38, n39, n40, n41, n42, n43,  
           n45, n46, n47, n48, n49, n50, n51;  
    wire   [2:0] FSM;  
    wire   [2:0] next_FSM;  
    wire   [3:0] round_index;  
    assign finished = round_type_sel[1];  
    
 assign round_index[0] = extra_FF[0];  
  assign round_index[3] = extra_FF[1];  
  assign FSM[2] = state_present[2];  
  assign FSM[0] = state_present[0];  
  assign FSM[1] = state_present[1];  
  assign round_index[1] = extra_FF[2];  
  assign round_index[2] = extra_FF[3];  
   
 OR2X1 U10010 ( .A(n10000), .B(n10001), .Y(n10003) );  
  OR2X1 U10011 ( .A(n10002), .B(n10003), .Y(n10004) );  
   
  INVX1 U10000 ( .A(extra_FF[0]),  .Y(n50) );  
  INVX1 U10001 ( .A(extra_FF[1]),  .Y(n51) );  
  XOR2X1 U10002 ( .A(next_FSM[2]), .B(state_next[2]), .Y(n10000) );  
  INVX1 U10003 ( .A(state_present[2]),  .Y(n47) );  
  XOR2X1 U10004 ( .A(next_FSM[0]), .B(state_next[0]), .Y(n10001) );  
  INVX1 U10005 ( .A(state_present[0]),  .Y(n49) );  
  XOR2X1 U10006 ( .A(next_FSM[1]), .B(state_next[1]), .Y(n10002) );  
  INVX1 U10007 ( .A(state_present[1]),  .Y(n45) );  
  INVX1 U10008 ( .A(extra_FF[2]),  .Y(n48) );  
  INVX1 U10009 ( .A(extra_FF[3]),  .Y(n46) );  
    INVX1 U13 ( .A(n33), .Y(n8) );  
    INVX1 U14 ( .A(n30), .Y(n9) );  
    INVX1 U15 ( .A(n37), .Y(round_type_sel[0]) );  
    INVX1 U23 ( .A(data_stable), .Y(n18) );  
    NOR2X1 U24 ( .A(n19), .B(n20), .Y(next_FSM[2]) );  
    NAND3X1 U25 ( .A(round_index[0]), .B(key_ready), .C(round_index[3]), .Y(n20)  
           );  
    NAND3X1 U26 ( .A(n48), .B(n46), .C(round_type_sel[0]), .Y(n19) );  
    AOI21X1 U27 ( .A0(n21), .A1(n22), .B0(n23), .Y(next_FSM[1]) );  
    AOI21X1 U28 ( .A0(n25), .A1(data_stable), .B0(n26), .Y(n21) );  
    NOR2X1 U29 ( .A(FSM[1]), .B(n49), .Y(n25) );  
    OAI21X1 U30 ( .A0(n27), .A1(n23), .B0(n28), .Y(next_FSM[0]) );  
    NAND3X1 U31 ( .A(n49), .B(n45), .C(key_ready), .Y(n28) );  
    AND2X1 U32 ( .A(n29), .B(FSM[0]), .Y(n27) );  
    AOI22X1 U33 ( .A0(FSM[1]), .A1(n24), .B0(n45), .B1(n18), .Y(n29) );  
    NAND4X1 U34 ( .A(round_index[3]), .B(round_index[0]), .C(n48), .D(n46), .Y(  
          n24) );  
    AOI22X1 U35 ( .A0(n31), .A1(n48), .B0(round_index[1]), .B1(n32), .Y(n30) );  
    OAI21X1 U36 ( .A0(n33), .A1(n46), .B0(n34), .Y(n41) );  
    NAND3X1 U37 ( .A(n31), .B(n46), .C(round_index[1]), .Y(n34) );  
    OAI21X1 U38 ( .A0(n35), .A1(n51), .B0(n36), .Y(n42) );  
    NAND4X1 U39 ( .A(round_index[2]), .B(round_index[1]), .C(n31), .D(n51), .Y(  
          n36) );  
    NOR2X1 U40 ( .A(n50), .B(n37), .Y(n31) );  
    AOI21X1 U41 ( .A0(round_type_sel[0]), .A1(n46), .B0(n8), .Y(n35) );  
    AOI21X1 U42 ( .A0(n48), .A1(round_type_sel[0]), .B0(n32), .Y(n33) );  
    OAI21X1 U43 ( .A0(round_index[0]), .A1(n37), .B0(n38), .Y(n32) );  
    OAI21X1 U44 ( .A0(n50), .A1(n38), .B0(n39), .Y(n43) );  
    AOI22X1 U45 ( .A0(n26), .A1(n47), .B0(round_type_sel[0]), .B1(n50), .Y(n39)  
           );  
    NOR2X1 U46 ( .A(n45), .B(FSM[0]), .Y(n26) );  
    OAI21X1 U47 ( .A0(FSM[0]), .A1(FSM[1]), .B0(FSM[2]), .Y(n38) );  
    NOR2X1 U48 ( .A(n47), .B(n40), .Y(round_type_sel[1]) );  
    NAND3X1 U49 ( .A(FSM[0]), .B(n47), .C(FSM[1]), .Y(n37) );  
    NAND2X1 U50 ( .A(n49), .B(n45), .Y(n40) );  
    NAND2X1 U51 ( .A(FSM[1]), .B(n24), .Y(n22) );  
    NAND2X1 U52 ( .A(key_ready), .B(n47), .Y(n23) );  
  endmodule   
 
