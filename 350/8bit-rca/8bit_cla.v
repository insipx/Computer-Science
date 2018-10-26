/*
* Andrew Plaza
* Dr. Bishop
* VeriLog Assignment 2: Adders - Carry Lookahead Adder
*/

module test_cla;
    reg[7:0] a, b;
    reg cin;
    wire cout;
    wire[7:0] sum;

    cla_8bit CLA0(a, b, cin, sum, cout);

    initial
        begin
            $monitor($time,,"a=%b, b=%b, cin=%b, sum=%b cout=%b",a,b,cin,sum,cout);
            #10 a=8'b01111111; b=8'b00000000; cin=0;
            #10 a=8'b01111111; b=8'b00000000; cin=1;
            #10 a=8'b00001110; b=8'b00000011; cin=0;
            #10 a=8'b01111111; b=8'b00000001; cin=0;
            #10 a=8'b00000010; b=8'b00000101; cin=0;
            #10 a=8'b10000010; b=8'b00000101; cin=0;
            #10 a=8'b11111110; b=8'b00000101; cin=0;
            #10 $finish;
        end
endmodule

module cla_8bit(a, b, cin, sum, cout);
    input[7:0] a;
    input[7:0] b;
    input cin;
    output[7:0] sum;
    output cout;

    wire[1:0] gpt0, gpt1, gpt2, gpt3, gpt4, gpt5, gpt6, gpt7; //top level
    wire[1:0] gpb0_0, gpb1_0, gpb2_0, gpb3_0; // first middle level
    wire[1:0] gpb0_1, gpb1_1; // second middle level
    wire[1:0] gpb_last;  // last level

    // Compute  generates and propogates
    // top level
    gp_top TOP_0(a[7], b[7], gpt0[1], gpt0[0]);
    gp_top TOP_1(a[6], b[6], gpt1[1], gpt1[0]);
    gp_top TOP_2(a[5], b[5], gpt2[1], gpt2[0]);
    gp_top TOP_3(a[4], b[4], gpt3[1], gpt3[0]);
    gp_top TOP_4(a[3], b[3], gpt4[1], gpt4[0]);
    gp_top TOP_5(a[2], b[2], gpt5[1], gpt5[0]);
    gp_top TOP_6(a[1], b[1], gpt6[1], gpt6[0]);
    gp_top TOP_7(a[0], b[0], gpt7[1], gpt7[0]);

    // mid-level
    gp_mid MID0_0(gpt0, gpt1, gpb0_0[1], gpb0_0[0]);
    gp_mid MID1_0(gpt2, gpt3, gpb1_0[1], gpb1_0[0]);
    gp_mid MID2_0(gpt4, gpt5, gpb2_0[1], gpb2_0[0]);
    gp_mid MID3_0(gpt6, gpt7, gpb3_0[1], gpb3_0[0]);

    // second-to-last
    gp_mid MID0_1(gpb0_0, gpb1_0, gpb0_1[1], gpb0_1[0]);
    gp_mid MID1_1(gpb2_0, gpb3_0, gpb1_1[1], gpb1_1[0]);

    // last level
    gp_mid LAST_1(gpb0_1, gpb1_1, gpb_last[1], gpb_last[0]);
    assign cout = gpb_last[1]; // at this point know the overall carry-out

    // carries
    wire [1:0]c0;
    wire [1:0]c1_0, c1_1;
    wire [1:0]c2_0, c2_1, c2_2, c2_3;

    // compute carries
    carry LAST_0(cin, gpb1_1[1], gpb1_1[0], c0[0], c0[1]);

    carry SEC_0(c0[0], gpb3_0[1], gpb3_0[0], c1_0[0], c1_0[1]);
    carry SEC_1(c0[1], gpb1_0[1], gpb1_0[0], c1_1[0], c1_1[1]);

    carry FIN_0(c1_0[0], gpt7[1], gpt7[0], c2_0[0], c2_0[1]);
    carry FIN_1(c1_0[1], gpt5[1], gpt5[0], c2_1[0], c2_1[1]);
    carry FIN_2(c1_1[0], gpt3[1], gpt3[0], c2_2[0], c2_2[1]);
    carry FIN_3(c1_1[1], gpt1[1], gpt1[0], c2_3[0], c2_3[1]);

    // add up everything
    cla_hf HF0(a[7], b[7], c2_3[1], sum[7]);
    cla_hf HF1(a[6], b[6], c2_3[0], sum[6]);
    cla_hf HF2(a[5], b[5], c2_2[1], sum[5]);
    cla_hf HF3(a[4], b[4], c2_2[0], sum[4]);
    cla_hf HF4(a[3], b[3], c2_1[1], sum[3]);
    cla_hf HF5(a[2], b[2], c2_1[0], sum[2]);
    cla_hf HF6(a[1], b[1], c2_0[1], sum[1]);
    cla_hf HF7(a[0], b[0], c2_0[0], sum[0]);
endmodule

module gp_top(a, b, gen, prop);
    input a, b;
    output gen, prop;
    assign gen = a & b;
    assign prop = a | b;
endmodule

module gp_mid(high, low, gen, prop);
    // [gen, prop]
    input[1:0] high;
    input[1:0] low;
    output gen, prop;
    assign gen = high[1] | (high[0] & low[1]);
    assign prop = high[0] & low[0];
endmodule

module carry(cin, g_low, p_low, clow, chigh);
    input cin, g_low, p_low;
    output clow, chigh;
    assign clow = cin;
    assign chigh = g_low | (p_low & cin);
endmodule

// cla half adder
module cla_hf(a, b, cin, sum);
    output sum;
    input a, b, cin;
    assign sum = (a & ~b & ~cin) | (~a & ~b & cin) | (a & b & cin) | (~a & b & ~cin);
endmodule

