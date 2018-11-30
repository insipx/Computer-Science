
/**
* Andrew Plaza
* Dr. Bishop
* Various Muxes
*
*/

// 1-bit select, 2 1-byte inputs, 1 1-byte output
module twoOneMux(select, in, out);
    input[0:1][0:7] in; // 2 byte inputs
    input select;
    output[0:7] out; // one byte output
    assign out = in[select];
endmodule


// two bit select, 4 1-byte inputs, 1 1-byte output
module fourOneMux(select, in, out);
    input[0:3][0:7] in;
    input[0:1] select;
    output[0:7] out;

    wire[1:0][7:0] level0;
    twoOneMux FIRST(select[0], in[0:1], level0[0]);
    twoOneMux SECOND(select[0], in[2:3], level0[1]);

    twoOneMux OUT(select[1], level0, out);

endmodule

// three bit select, 8 1-byte inputs, 1 1-byte output
module eightOneMux(select, in, out);
    input[0:7][0:7] in; // 8 1-byte inputs
    input[0:2] select; // 3 bit select
    output[0:7] out; // 1 1-byte output

    wire[0:1][0:7] first;

    // Level 0
    fourOneMux LEVEL0_0(select[0:1], in[0:3], first[0]);
    fourOneMux LEVEL0_1(select[0:1], in[4:7], first[1]);

    // Level 1
    twoOneMux LEVEL1_0(select[1], first, out);
endmodule
