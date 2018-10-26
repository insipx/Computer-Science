/*
* Andrew Plaza
* Dr. Bishop
* VeriLog Assignment 1: Full Adder
*
*   a | b | Cin ||  S  | Cout
*   0   0    0  ||  0     0
*   0   0    1  ||  1     0
*   0   1    0  ||  1     0
*   0   1    1  ||  0     1
*   1   0    0  ||  1     0
*   1   0    1  ||  0     1
*   1   1    0  ||  0     1
*   1   1    1  ||  1     1
*
*/

module full_adder;
    wire sum, cout;
    reg a,b,cin;
    assign sum = (a & ~b & ~cin) | (~a & ~b & cin) | (a & b & cin) | (~a & b & ~cin);
    assign cout = (a & cin) | (b & cin) | (a & b);

    initial
        begin
            $monitor($time,,"a=%b, b=%b, cin=%b, sum=%b cout=%b",a,b,cin,sum,cout);
            #10 a=0; b=0; cin=0;
            #10 a=0; b=0; cin=1;
            #10 a=0; b=1; cin=0;
            #10 a=0; b=1; cin=1;
            #10 a=1; b=0; cin=0;
            #10 a=1; b=0; cin=1;
            #10 a=1; b=1; cin=0;
            #10 a=1; b=1; cin=1;
            #10 $finish;
        end
endmodule

