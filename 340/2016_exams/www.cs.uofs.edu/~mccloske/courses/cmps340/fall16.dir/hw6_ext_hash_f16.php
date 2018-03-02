<html>
<head>
  <title>CMPS 340 Fall 2016 HW #6: Extendible Hashing</title>
</head>
<body>
<h2>
CMPS 340 Fall 2016 <br />
HW #6: Extendible Hashing <br />
Due: 1pm, Wednesday, Nov. 2
</h2>
<html>
<head></head>
<body>

</p><p>
Suppose that we are using an extendible hash table with bucket size 3 and
suppose that our hash function H is such that

<pre>
H(ANT)  = 11110...  H(BEAR) = 01000...  H(BUG)  = 10011...  H(CAT)  = 11101...
H(COW)  = 10010...  H(DOG)  = 11010...  H(DUCK) = 10110...  H(ELK)  = 00001...
H(GORN) = 10011...  H(LION) = 01000...  H(PIG)  = 10001...  H(RAT)  = 10101...
H(WOLF) = 01101...  H(YAK)  = 11000...
</pre>

<p>
The hash table is as follows:

<pre>
       Directory                        Buckets

         +----+                   +-----+-----+-----+
(000)  0 |  *-+--->---+---------->| ELK |     |     | (00)
         +----+      /            +-----+-----+-----+
(001)  1 |  *-+--->-+
         +----+                   +------+------+------+
(010)  2 |  *-+-----+------------>| LION | BEAR | WOLF | (01) 
         +----+    /              +------+------+------+
(011)  3 |  *-+--->        
         +----+                   +-----+-----+-----+
(100)  4 |  *-+------------------>| COW | PIG | BUG | (100)
         +----+                   +-----+-----+-----+
(101)  5 |  *-+------------------>+-----+-----+-----+
         +----+                   | RAT |     |     | (101)
(110)  6 |  *-+--->-+             +-----+-----+-----+
         +----+      \            +-----+-----+-----+
(111)  7 |  *-+--->---+---------->| DOG | ANT | CAT | (11)
         +----+                   +-----+-----+-----+ </pre> 

</p><p>
To the right of each bucket is shown its <em>label</em>
(or <em>signature</em>), which describes those cells in the directory
that point to it: namely, any cell having an address whose
binary representation has the label as a prefix.

<!--
<p>
Arrows depicting the pointers from the directory to the buckets
are omitted because your instructor is artistically challenged.
-->

<p>
For each of the following operations, apply it to the hash table above
(not to the result of applying the previous operations) and show the
hash table that results.  (It suffices to show the portion of the table
that has changed, plus a little surrounding context.  If the directory
is doubled or halved, show its new state.)
If a deletion results in two "sibling" buckets having a total number
items equal to the capacity of one bucket, then merge the two buckets.
(In real life, we probably would not do that, because it would result
in a bucket likely to be split in the near future.)

</p><p>
<center>
<table border="0" cellpadding="3">
<tr><td><b>(a)</b> Insert DUCK</td>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td><b>(b)</b> Insert YAK</td>
</tr>
<tr><td><b>(c)</b> Delete BEAR</td>
    <td> </td>
    <td><b>(d)</b> Delete BUG</td>
</tr>
<tr><td><b>(e)</b> Insert GORN</td>
    <td> </td>
    <td><b>(f)</b> Delete ANT</td>
</tr>
</table>
</center>

</body>
</html>

</body>
</html> 

