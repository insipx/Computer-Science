/*
* Andrew Plaza
* Dr. Bishop
* VeriLog Assignment 2: Ripple-Carry Adder
*/


module rca_8bit(a, b, cin, sum, cout);
    input[7:0] a;
    input[7:0] b;
    input cin;
    output[7:0] sum;
    output cout;

    // intermediate carries
    wire c0,c1,c2,c3,c4,c5,c6;

    full_adder FA_0(a[0], b[0], cin, sum[0], c0);
    full_adder FA_1(a[1], b[1], c0, sum[1], c1);
    full_adder FA_2(a[2], b[2], c1, sum[2], c2);
    full_adder FA_3(a[3], b[3], c2, sum[3], c3);
    full_adder FA_4(a[4], b[4], c3, sum[4], c4);
    full_adder FA_5(a[5], b[5], c4, sum[5], c5);
    full_adder FA_6(a[6], b[6], c5, sum[6], c6);
    full_adder FA_7(a[7], b[7], c6, sum[7], cout);
endmodule


module full_adder(a, b, cin, sum, cout);
    output sum, cout;
    input a, b, cin;
    assign sum = (a & ~b & ~cin) | (~a & ~b & cin) | (a & b & cin) | (~a & b & ~cin);
    assign cout = (a & cin) | (b & cin) | (a & b);
endmodule

