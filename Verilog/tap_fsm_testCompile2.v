/////////////////////////////////////////////////////////////
// Created by: Synopsys DC Expert(TM) in wire load mode
// Version   : K-2015.06-SP4
// Date      : Wed Jun  1 13:53:12 2016
/////////////////////////////////////////////////////////////


module tap_fsm ( capture_dr, shift_dr_pos, shift_dr, pause_dr, update_dr, 
	capture_ir, shift_ir_pos, shift_ir, pause_ir, update_ir, jtag_reset_n, 
	exit1_dr, exit2_dr, exit1_ir, exit2_ir, tms, tck, trst_n, 
	run_test_idle, IN0 );
input tms;
input tck;
input trst_n;
input IN0;
output capture_dr;
output shift_dr_pos;
output shift_dr;
output pause_dr;
output update_dr;
output capture_ir;
output shift_ir_pos;
output shift_ir;
output pause_ir;
output update_ir;
output jtag_reset_n;
output exit1_dr;
output exit2_dr;
output exit1_ir;
output exit2_ir;
output run_test_idle;
wire int_reset_n, n23, n123, n125, n126, n127, n128, n129, n130, n131,
	 n132, n133, n134, n135, n136, n137, n138, n139, n140, n141, n142,
	 n143, n144, n145, n146, n147, n148, n149, n150, n151, n152, n153,
	 n154, n155, n156, n157, n158, n159, n160, n161, n162, n163, n164,
	 n165, n166, n167, n168, n169, n170, n171, n172, n173, n174, n175,
	 n176, n177, n178, n179, n180, n181, n182, n183, n184, n185, n186,
	 n187, n188, n189, n190, n191, n192, n193, n194, n195, n196, n197,
	 n198, n199, n200, n201, n202, n203, n204, n205, n206, n207, n208,
	 n209, n210, n211, n212, n213, n214, n215, n216, n217, n218, n219,
	 n220, n221, n222, n223, n224, n225, n226, n227, n228, n229, n230,
	 n231, n232, n233, n234, n235, n236, n237, n238, n239, n240, n241,
	 n242, n243, n244, n245, n246, n247, n248, n249, n250, n251, n252,
	 n253, n254, n255, n256, n257;

DFFSRX1 curr_tap_state_reg_4_ ( .D(n123), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(shift_dr_pos) );
DFFSRX1 curr_tap_state_reg_5_ ( .D(n125), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(exit1_dr), .QN(n251) );
DFFSRX1 curr_tap_state_reg_0_ ( .D(n139), .CK(tck), .RN(1'b1), .SN(trst_n), .Q(n147), .QN(n256) );
DFFSRX1 curr_tap_state_reg_9_ ( .D(n133), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(n141), .QN(n248) );
DFFSRX1 curr_tap_state_reg_3_ ( .D(n132), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(capture_dr), .QN(n255) );
DFFSRX1 curr_tap_state_reg_6_ ( .D(n138), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(n146), .QN(n252) );
DFFSRX1 curr_tap_state_reg_7_ ( .D(n129), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(exit2_dr), .QN(n253) );
DFFSRX1 curr_tap_state_reg_8_ ( .D(n126), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(update_dr) );
DFFSRX1 curr_tap_state_reg_2_ ( .D(n137), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(n143), .QN(n254) );
DFFSRX1 curr_tap_state_reg_1_ ( .D(n131), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(run_test_idle) );
DFFSRX1 curr_tap_state_reg_10_ ( .D(n130), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(capture_ir), .QN(n257) );
DFFSRX1 curr_tap_state_reg_12_ ( .D(n136), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(n145), .QN(n249) );
DFFSRX1 curr_tap_state_reg_11_ ( .D(n128), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(shift_ir_pos), .QN(n148) );
DFFSRX1 curr_tap_state_reg_13_ ( .D(n135), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(n142), .QN(n247) );
DFFSRX1 curr_tap_state_reg_14_ ( .D(n134), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(n144), .QN(n250) );
DFFSRX1 curr_tap_state_reg_15_ ( .D(n127), .CK(tck), .RN(trst_n), .SN(1'b1), .Q(update_ir) );
DFFSRX1 int_reset_n_reg ( .D(n140), .CK(n23), .RN(trst_n), .SN(1'b1), .Q(int_reset_n) );
DFFSRX1 shift_ir_reg ( .D(shift_ir_pos), .CK(n23), .RN(jtag_reset_n), .SN(1'b1), .Q(shift_ir) );
DFFSRX1 shift_dr_reg ( .D(shift_dr_pos), .CK(n23), .RN(jtag_reset_n), .SN(1'b1), .Q(shift_dr) );

INVX1 U139 ( .A(tck), .Y(n23) );
NAND2X1 U140 ( .A(n149), .B(n150), .Y(n139) );
AND2X1 U141 ( .A(tms), .B(n151), .Y(n150) );
NAND2X1 U142 ( .A(n152), .B(n153), .Y(n149) );
INVX1 U143 ( .A(n151), .Y(n153) );
NAND2X1 U144 ( .A(n140), .B(n154), .Y(n151) );
NOR2X1 U145 ( .A(n155), .B(n156), .Y(n152) );
NAND2X1 U146 ( .A(n157), .B(n158), .Y(n156) );
AND2X1 U147 ( .A(n159), .B(n160), .Y(n157) );
NAND2X1 U148 ( .A(n161), .B(n162), .Y(n155) );
AND2X1 U149 ( .A(n163), .B(n164), .Y(n162) );
AND2X1 U150 ( .A(n165), .B(n166), .Y(n161) );
NOR2X1 U151 ( .A(tms), .B(n159), .Y(n138) );
NAND2X1 U152 ( .A(n167), .B(n168), .Y(n159) );
XOR2X1 U153 ( .A(n251), .B(n252), .Y(n168) );
NOR2X1 U154 ( .A(n158), .B(n169), .Y(n137) );
NOR2X1 U155 ( .A(n169), .B(n164), .Y(n136) );
NAND2X1 U156 ( .A(n170), .B(n171), .Y(n135) );
NAND2X1 U157 ( .A(n172), .B(n173), .Y(n171) );
NOR2X1 U158 ( .A(n249), .B(n144), .Y(n172) );
OR2X1 U159 ( .A(n166), .B(tms), .Y(n170) );
NOR2X1 U160 ( .A(n169), .B(n166), .Y(n134) );
NAND2X1 U161 ( .A(n174), .B(n175), .Y(n166) );
NOR2X1 U162 ( .A(n247), .B(n144), .Y(n175) );
NOR2X1 U163 ( .A(n145), .B(n176), .Y(n174) );
NOR2X1 U164 ( .A(n169), .B(n165), .Y(n133) );
NOR2X1 U165 ( .A(tms), .B(n165), .Y(n132) );
NAND2X1 U166 ( .A(n177), .B(n178), .Y(n165) );
NOR2X1 U167 ( .A(run_test_idle), .B(n254), .Y(n178) );
NOR2X1 U168 ( .A(tms), .B(n179), .Y(n131) );
AND2X1 U169 ( .A(n140), .B(n158), .Y(n179) );
AND2X1 U170 ( .A(n180), .B(n181), .Y(n158) );
NAND2X1 U171 ( .A(n177), .B(n182), .Y(n181) );
NOR2X1 U172 ( .A(IN0), .B(n143), .Y(n182) );
NOR2X1 U173 ( .A(exit2_dr), .B(n183), .Y(n177) );
NAND2X1 U174 ( .A(n184), .B(n185), .Y(n180) );
NOR2X1 U175 ( .A(capture_ir), .B(n186), .Y(n185) );
NAND2X1 U176 ( .A(n187), .B(n148), .Y(n186) );
XOR2X1 U177 ( .A(update_ir), .B(update_dr), .Y(n187) );
NOR2X1 U178 ( .A(n141), .B(n188), .Y(n184) );
NAND2X1 U179 ( .A(n189), .B(n190), .Y(n140) );
NOR2X1 U180 ( .A(n256), .B(n191), .Y(n190) );
NOR2X1 U181 ( .A(n192), .B(n141), .Y(n189) );
NOR2X1 U182 ( .A(tms), .B(n154), .Y(n130) );
NAND2X1 U183 ( .A(n193), .B(n194), .Y(n154) );
NOR2X1 U184 ( .A(n248), .B(n192), .Y(n193) );
NOR2X1 U185 ( .A(n195), .B(n196), .Y(n129) );
NAND2X1 U186 ( .A(tms), .B(n167), .Y(n196) );
INVX1 U187 ( .A(n197), .Y(n167) );
NAND2X1 U188 ( .A(n251), .B(n146), .Y(n195) );
NAND2X1 U189 ( .A(n198), .B(n199), .Y(n128) );
NAND2X1 U190 ( .A(n200), .B(n173), .Y(n199) );
AND2X1 U191 ( .A(n201), .B(n202), .Y(n173) );
NOR2X1 U192 ( .A(tms), .B(n142), .Y(n201) );
NOR2X1 U193 ( .A(n250), .B(n145), .Y(n200) );
OR2X1 U194 ( .A(n164), .B(tms), .Y(n198) );
NAND2X1 U195 ( .A(n203), .B(n204), .Y(n164) );
NOR2X1 U196 ( .A(n141), .B(n205), .Y(n204) );
OR2X1 U197 ( .A(update_dr), .B(update_ir), .Y(n205) );
NOR2X1 U198 ( .A(n188), .B(n206), .Y(n203) );
XOR2X1 U199 ( .A(shift_ir_pos), .B(n257), .Y(n206) );
INVX1 U200 ( .A(n194), .Y(n188) );
NOR2X1 U201 ( .A(n147), .B(n191), .Y(n194) );
NAND2X1 U202 ( .A(n207), .B(n208), .Y(n191) );
NOR2X1 U203 ( .A(n144), .B(n209), .Y(n208) );
NOR2X1 U204 ( .A(n145), .B(n142), .Y(n207) );
NOR2X1 U205 ( .A(n169), .B(n163), .Y(n127) );
NAND2X1 U206 ( .A(n210), .B(n202), .Y(n163) );
INVX1 U207 ( .A(n176), .Y(n202) );
NAND2X1 U208 ( .A(n211), .B(n212), .Y(n176) );
NOR2X1 U209 ( .A(n192), .B(n209), .Y(n212) );
NAND2X1 U210 ( .A(n255), .B(n213), .Y(n209) );
NOR2X1 U211 ( .A(n141), .B(n147), .Y(n211) );
NOR2X1 U212 ( .A(n214), .B(n142), .Y(n210) );
XOR2X1 U213 ( .A(n144), .B(n249), .Y(n214) );
NOR2X1 U214 ( .A(n215), .B(n169), .Y(n126) );
NOR2X1 U215 ( .A(n216), .B(n217), .Y(n215) );
INVX1 U216 ( .A(n218), .Y(n217) );
NOR2X1 U217 ( .A(n197), .B(n219), .Y(n216) );
NAND2X1 U218 ( .A(n252), .B(exit1_dr), .Y(n219) );
NAND2X1 U219 ( .A(n220), .B(n221), .Y(n197) );
NOR2X1 U220 ( .A(n222), .B(capture_dr), .Y(n220) );
NOR2X1 U221 ( .A(n169), .B(n223), .Y(n125) );
INVX1 U222 ( .A(tms), .Y(n169) );
NOR2X1 U223 ( .A(tms), .B(n160), .Y(n123) );
AND2X1 U224 ( .A(n218), .B(n223), .Y(n160) );
NAND2X1 U225 ( .A(n224), .B(n221), .Y(n223) );
INVX1 U226 ( .A(n225), .Y(n221) );
NOR2X1 U227 ( .A(n226), .B(n227), .Y(n224) );
NOR2X1 U228 ( .A(n228), .B(capture_dr), .Y(n227) );
NOR2X1 U229 ( .A(n229), .B(n230), .Y(n228) );
NAND2X1 U230 ( .A(n231), .B(shift_dr_pos), .Y(n230) );
NOR2X1 U231 ( .A(exit1_dr), .B(n232), .Y(n231) );
NAND2X1 U232 ( .A(n233), .B(n252), .Y(n229) );
NOR2X1 U233 ( .A(n255), .B(n213), .Y(n226) );
AND2X1 U234 ( .A(n234), .B(n251), .Y(n213) );
NOR2X1 U235 ( .A(n222), .B(n146), .Y(n234) );
NAND2X1 U236 ( .A(n233), .B(n235), .Y(n222) );
NOR2X1 U237 ( .A(shift_dr_pos), .B(run_test_idle), .Y(n235) );
NOR2X1 U238 ( .A(n143), .B(exit2_dr), .Y(n233) );
NAND2X1 U239 ( .A(n236), .B(n237), .Y(n218) );
NOR2X1 U240 ( .A(n253), .B(n143), .Y(n237) );
NOR2X1 U241 ( .A(n183), .B(n232), .Y(n236) );
INVX1 U242 ( .A(IN0), .Y(n232) );
NAND2X1 U243 ( .A(n238), .B(n239), .Y(n183) );
NOR2X1 U244 ( .A(exit1_dr), .B(n240), .Y(n239) );
OR2X1 U245 ( .A(n146), .B(shift_dr_pos), .Y(n240) );
NOR2X1 U246 ( .A(capture_dr), .B(n225), .Y(n238) );
NAND2X1 U247 ( .A(n241), .B(n242), .Y(n225) );
NOR2X1 U248 ( .A(n142), .B(n243), .Y(n242) );
NAND2X1 U249 ( .A(n249), .B(n250), .Y(n243) );
NOR2X1 U250 ( .A(n147), .B(n244), .Y(n241) );
OR2X1 U251 ( .A(n141), .B(n192), .Y(n244) );
NAND2X1 U252 ( .A(n245), .B(n246), .Y(n192) );
NOR2X1 U253 ( .A(update_ir), .B(update_dr), .Y(n246) );
NOR2X1 U254 ( .A(shift_ir_pos), .B(capture_ir), .Y(n245) );
AND2X1 U255 ( .A(trst_n), .B(int_reset_n), .Y(jtag_reset_n) );
endmodule

