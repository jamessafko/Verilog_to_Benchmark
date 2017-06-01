
module RSA_binary ( clk, start, finished );
  input clk, start;
  output finished;
  wire   n54, n55, n56, n57, n58, n59, n60, n61, n62, n63, n64, n65, n66, n67,
         n68, n69, n70, n71, n72, n73, n74, n75, n76, n77, n78, n79, n80, n81,
         n82, n83, n84, n85, n86, n87, n88, n89, n90, n91, n92, n93, n94, n95,
         n96, n97, n98, n99, n100, n101, n102, n103, n104, n105, n106, n107;
  wire   [2:0] next_FSM;
  wire   [3:0] round_index;

  DFFSRX1 \round_index_reg[0]  ( .D(n57), .CK(clk), .RN(1'b1), .SN(1'b1), .Q(
        round_index[0]), .QN(n63) );
  DFFSRX1 \round_index_reg[3]  ( .D(n56), .CK(clk), .RN(1'b1), .SN(1'b1), .Q(
        round_index[3]), .QN(n64) );
  DFFSRX1 \FSM_reg[0]  ( .D(next_FSM[0]), .CK(clk), .RN(1'b1), .SN(1'b1), .Q(
        n61), .QN(n107) );
  DFFSRX1 \FSM_reg[2]  ( .D(next_FSM[2]), .CK(clk), .RN(1'b1), .SN(1'b1), .Q(
        n58), .QN(n106) );
  DFFSRX1 \FSM_reg[1]  ( .D(next_FSM[1]), .CK(clk), .RN(1'b1), .SN(1'b1), .Q(
        n60), .QN(n105) );
  DFFSRX1 \round_index_reg[1]  ( .D(n54), .CK(clk), .RN(1'b1), .SN(1'b1), .Q(
        round_index[1]), .QN(n59) );
  DFFSRX1 \round_index_reg[2]  ( .D(n55), .CK(clk), .RN(1'b1), .SN(1'b1), .Q(
        round_index[2]), .QN(n62) );
  NAND2X1 U64 ( .A(n65), .B(n66), .Y(next_FSM[2]) );
  NAND2X1 U65 ( .A(n67), .B(n58), .Y(n66) );
  NAND2X1 U66 ( .A(n60), .B(n68), .Y(n67) );
  NAND2X1 U67 ( .A(n107), .B(n69), .Y(n68) );
  NAND2X1 U68 ( .A(n70), .B(n106), .Y(n65) );
  NOR2X1 U69 ( .A(n105), .B(n107), .Y(n70) );
  NAND2X1 U70 ( .A(n71), .B(n72), .Y(next_FSM[1]) );
  NAND2X1 U71 ( .A(n73), .B(n61), .Y(n72) );
  NOR2X1 U72 ( .A(n74), .B(n60), .Y(n73) );
  NAND2X1 U73 ( .A(n75), .B(n107), .Y(n71) );
  NOR2X1 U74 ( .A(n105), .B(n58), .Y(n75) );
  NAND2X1 U75 ( .A(n76), .B(n77), .Y(next_FSM[0]) );
  NAND2X1 U76 ( .A(n74), .B(n105), .Y(n77) );
  NOR2X1 U77 ( .A(start), .B(n58), .Y(n74) );
  NAND2X1 U78 ( .A(n107), .B(n78), .Y(n76) );
  NAND2X1 U79 ( .A(n79), .B(n80), .Y(n78) );
  INVX1 U80 ( .A(n69), .Y(n80) );
  NAND2X1 U81 ( .A(n81), .B(n82), .Y(n69) );
  NOR2X1 U82 ( .A(round_index[3]), .B(round_index[2]), .Y(n82) );
  NOR2X1 U83 ( .A(round_index[0]), .B(n59), .Y(n81) );
  NAND2X1 U84 ( .A(n83), .B(n84), .Y(n57) );
  NAND2X1 U85 ( .A(n85), .B(round_index[0]), .Y(n83) );
  NOR2X1 U86 ( .A(n106), .B(n107), .Y(n85) );
  NAND2X1 U87 ( .A(n86), .B(n87), .Y(n56) );
  NAND2X1 U88 ( .A(round_index[3]), .B(n88), .Y(n87) );
  NAND2X1 U89 ( .A(n89), .B(n90), .Y(n88) );
  NAND2X1 U90 ( .A(n91), .B(n62), .Y(n90) );
  NAND2X1 U91 ( .A(n92), .B(n64), .Y(n86) );
  NOR2X1 U92 ( .A(n93), .B(n62), .Y(n92) );
  NAND2X1 U93 ( .A(n94), .B(n95), .Y(n55) );
  OR2X1 U94 ( .A(n93), .B(round_index[2]), .Y(n95) );
  NAND2X1 U95 ( .A(n96), .B(n91), .Y(n93) );
  NOR2X1 U96 ( .A(n59), .B(n63), .Y(n96) );
  OR2X1 U97 ( .A(n62), .B(n89), .Y(n94) );
  NOR2X1 U98 ( .A(n97), .B(n98), .Y(n89) );
  NOR2X1 U99 ( .A(n99), .B(round_index[1]), .Y(n98) );
  NAND2X1 U100 ( .A(n100), .B(n101), .Y(n54) );
  NAND2X1 U101 ( .A(round_index[1]), .B(n97), .Y(n101) );
  NAND2X1 U102 ( .A(n102), .B(n84), .Y(n97) );
  NAND2X1 U103 ( .A(n91), .B(n63), .Y(n84) );
  INVX1 U104 ( .A(n99), .Y(n91) );
  NAND2X1 U105 ( .A(n61), .B(n58), .Y(n102) );
  NAND2X1 U106 ( .A(n103), .B(n59), .Y(n100) );
  NOR2X1 U107 ( .A(n63), .B(n99), .Y(n103) );
  NAND2X1 U108 ( .A(n79), .B(n107), .Y(n99) );
  NOR2X1 U109 ( .A(n105), .B(n106), .Y(n79) );
  NOR2X1 U110 ( .A(n60), .B(n104), .Y(finished) );
  NAND2X1 U111 ( .A(n107), .B(n106), .Y(n104) );
endmodule

