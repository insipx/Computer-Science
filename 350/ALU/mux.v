
/**
* Andrew Plaza
* Dr. Bishop
* Various Muxes
*
*/

// 1-bit select, 2 1-byte inputs, 1 1-byte output
module twoOneMux(select, in, out);
    input[1:0][7:0] in; // 2 byte inputs
    input select;
    output[7:0] out; // one byte output
    assign out = in[select];
endmodule


// two bit select, 4 1-byte inputs, 1 1-byte output
module fourOneMux(select, in, out);
    input[3:0][7:0] in;
    input[1:0] select;
    output[7:0] out;

    wire[1:0][7:0] level0;
    twoOneMux FIRST(select[0], in[1:0], level0[0]);
    twoOneMux SECOND(select[0], in[3:2], level0[1]);

    twoOneMux OUT(select[1], level0, out);

endmodule

// three bit select, 8 1-byte inputs, 1 1-byte output
module eightOneMux(select, in, out);
    input[7:0][7:0] in; // 8 1-byte inputs
    input[2:0] select; // 3 bit select
    output[7:0] out; // 1 1-byte output

    wire[1:0][7:0] first;

    // Level 0
    fourOneMux LEVEL0_0(select[1:0], in[3:0], first[0]);
    fourOneMux LEVEL0_1(select[1:0], in[7:4], first[1]);

    // Level 1
    twoOneMux LEVEL1_0(select[2], first, out);
endmodule
