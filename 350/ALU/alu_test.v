
`include "alu.v"

module test_alu;
    reg[7:0] a;
    reg[7:0] b;
    reg[2:0] op;
    wire[7:0] out;

    alu ALU(a, b, op, out);

    initial
        begin
            $monitor($time,,"a=%b, b=%b, out=%b",a,b,out);
            #10 a=8'b00011001; b=8'b00011110; op=3'b000;
            #10 a=8'b00011110; b=8'b00011001; op=3'b001;
            #10 a=8'b00001010; b=8'b00000101; op=3'b010;
            #10 a=8'b00001111; b=8'b00001100; op=3'b011;
            #10 a=8'b00010101; b=8'b00001100; op=3'b101;
            #10 a=8'b00001100; b=8'b00000101; op=3'b100;
            #10 $finish;
        end
endmodule

