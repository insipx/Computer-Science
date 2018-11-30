/*
* Andrew Plaza
* Dr. Bishop
* VeriLog Assignment 3: Algorithmic Logic Unit
*/

`include "./mux.v"
`include "./8bit_rca.v"
`include "./rotate_shifter.v"

module alu(A, B, Op, Out);
    input[7:0] A;
    input[7:0] B;
    input[2:0] Op;
    output[7:0] Out;
    // output[7:0] Out0, Out1, Out2, Out3, Out4, Out5, Out6, Out7;

    wire[7:0] AddSub, Shift, Xor, Or, And, Undef;
    wire[7:0][7:0] muxIn;

    addSub DO_ADDSUB(A, B, Op[0], AddSub);
    assign muxIn[0] = AddSub;
    assign muxIn[1] = AddSub;
    rotateShifter DO_ROTATE(A, B[2:0], muxIn[2]);
    assign muxIn[3] = A ^ B;
    assign muxIn[4] = A | B;
    assign muxIn[5] = A & B;
    assign muxIn[6] = (A & B) & (A | B) ^ ~B;
    assign muxIn[7] = A;

    eightOneMux ALU(Op, muxIn, Out);
endmodule


module addSub(A, B, select, out);
    input[7:0] A;
    input[7:0] B;
    input select;
    output[7:0] out;
    wire cout;

    wire[7:0] notB = ~B;
    wire[1:0][7:0] addOrSub;
    assign addOrSub[0] = B; assign addOrSub[1] = notB;
    wire[7:0] bToUse;

    twoOneMux B_TO_USE(select, addOrSub, bToUse);
    rca_8bit DO_OP(A, bToUse, select, out, cout);
endmodule
