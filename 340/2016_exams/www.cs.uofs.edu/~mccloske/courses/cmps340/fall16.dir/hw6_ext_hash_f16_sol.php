<html>
<head>
  <title>CMPS 340 Fall 2016 HW #6 Sample Solutions: Extendible Hashing</title>
</head>
<body>
<h2>
CMPS 340 Fall 2016 <br />
HW #6: Extendible Hashing <br />
Sample Solutions
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

</p><p>
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

<h3>Solutions</h3>

</p><p>
<b>(a)</b> DUCK is inserted into the (101) bucket without incident

</p><p>
<b>(b)</b> YAK cannot be placed into bucket (11) because it is already
full.  So we split that bucket into buckets (110) and (111).
DOG is then placed into bucket (110) and both ANT and CAT are placed
into bucket (111).  (Of course, these placements are based upon the
first three bits of H(DOG), H(ANT), and H(CAT).)
The pointers in the cells at addresses 6 and 7 (110 and 111 in binary)
of the Directory are adjusted to point to buckets (110) and (111),
respectively.
Finally, we once again attempt to insert YAK. 
This time it is placed into bucket (110) without incident.

</p><p>
<b>(c)</b> BEAR is removed from bucket (01).  As that bucket has
a sibling (namely, bucket (00)) and the two of them can be merged
(because the total quantity of data in the two of them can fit into 
a single bucket), we merge them to form bucket (0) containing 
ELK, LION, and WOLF.
The pointers in cells 0 through 3 of the
Directory are adjusted to point to bucket (0).

</p><p>
<b>(d)</b> BUG is removed from bucket (100).  As that bucket has
a sibling (namely, bucket (101)) and the two can be merged,
we do so, resulting in bucket (10) containing COW, PIG, and RAT. 
Because now no bucket has a label exceeding length two, the
directory's depth can be decreased from 3 to 2, which is to
say that we can halve the directory so that it has only four
cells.  Of course, into each cell must be placed a pointer to
the appropriate bucket.

</p><p>
<b>(e)</b> GORN cannot be placed into bucket (100) because it is already
full.  So we split that bucket into buckets (1000) and (1001).
Based upon their hash values, PIG is placed into bucket (1000)
and both COW and BUG are placed into bucket (1001).
Now that there are buckets whose labels are of length four,
the directory's depth must be increased to match it, which is to
say that the directory must be doubled.
Of course, into each cell must be placed a pointer to
the appropriate bucket.
Finally, we once again attempt to insert GORN. 
This time it is placed into bucket (1001) without incident.


</p><p>
<b>(f)</b> ANT is removed from bucket (11) without incident.
There is no possibility of a merge because bucket (11) does
not have a sibling.

</body>
</html>

</body>
</html> 

