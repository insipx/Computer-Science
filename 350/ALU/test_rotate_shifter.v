`include "rotate_shifter.v"

module test_mux;
    reg[7:0] a;
    reg[2:0] b;
    wire[7:0] out;

    rotateShifter ROTATE(a, b, out);

    initial
        begin
            $monitor($time,,"a=%b, b=%b, out=%b",a,b,out);
            #10 a=8'b01111011; b=3'b000;
            #10 a=8'b01111011; b=3'b001;
            #10 a=8'b01111011; b=3'b010;
            #10 a=8'b01111011; b=3'b011;
            #10 a=8'b01111011; b=3'b100;
            #10 a=8'b01111011; b=3'b101;
            #10 a=8'b01111011; b=3'b110;
            #10 a=8'b01111011; b=3'b111;
            #10 $finish;
        end
endmodule
