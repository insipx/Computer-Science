`include "mux.v"

/**
* Andrew Plaza
* Dr. Bishop
* Rotate Shifter
*/

// a gets rotated to the left by b places
module rotateShifter(a, b, out);
    input[7:0] a;
    input [2:0] b;
    output[7:0] out;

    wire[7:0] out0;
    wire[7:0] out1;
    wire[7:0] out2;

    shiftFour FOUR(a, b[2], out0);
    shiftTwo TWO(out0, b[1], out1);
    shiftOne ONE(out1, b[0], out2);
    assign out = out2;
endmodule

// A two-one mux
module shiftOne(in, s, out);
    input[7:0] in;
    input s;
    output[7:0] out;
    wire[7:0] shifted;
    wire[1:0][7:0] muxIn;

    assign shifted[7:0] = {in[6:0],in[7]};

    assign muxIn[0] = in;
    assign muxIn[1] = shifted;

    twoOneMux OUT(s, muxIn, out);
endmodule

module shiftTwo(in, s, out);
    input[7:0] in;
    input s;
    output[7:0] out;
    wire[7:0] shifted;
    wire[1:0][7:0] muxIn;

    assign shifted[7:0] = {in[5:0], in[7:6]};

    assign muxIn[0] = in;
    assign muxIn[1] = shifted;

    twoOneMux OUT(s, muxIn, out);
endmodule

module shiftFour(in, s, out);
    input[7:0] in;
    input s;
    output[7:0] out;
    wire[7:0] shifted;
    wire[1:0][7:0] muxIn;

    assign shifted[7:0] = {in[3:0], in[7:4]};

    assign muxIn[0] = in;
    assign muxIn[1] = shifted;

    twoOneMux OUT(s, muxIn, out);
endmodule


