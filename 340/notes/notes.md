# CS Notes





### Indexing structures for files





Only need one pointer per block

- b/c even though looking for someone not listed in index, can point us to correct block
  - Index only needs to contain as many records as there are blocks
- Smaller index file, faster it can be searched





| Ordering | Non Ordering |
| -------- | ------------ |
| Primary  |              |
|          |              |
|          |              |
|          |              |
|          |              |
|          |              |
|          |              |
|          |              |
|          |              |





Dense Secondary Index

- Index must have a distinct value for each possible value in the field
  - purpose: find stuff
    - if want to know where record containing 11 is, find 11 and follow pointer to bucket where 11 is.

If employee in Dept 5 is scattered all over the place

- search in index
- gives us a pointer



| Field Values | Pointer          | Pointer          | Pointer          | Pointer          |
| ------------ | ---------------- | ---------------- | ---------------- | ---------------- |
| X            | point to a block | point to a block | point to a block | point to a block |

Go on for infinity



do block pointers have to be physical addresses?



| Name   | Bucket |
| ------ | ------ |
| Beth   | 3      |
| Beth   | 1      |
| Calvin | 3      |

Every name points to a bucket with name, ID, Occupation

**Four buckets consisting of** 

**ID, Name, Occupation**

| ID   | Bucket |
| ---- | ------ |
| A43  | 2      |

Only would have to change the relationship between ID-Bucket

# Buffering

```
f.open();

while (!f.eof()) {

	x = f.getRecord();
	/* < do something with x >
}

```

### Input buffer



### Double Buffering

every time a program does a write/print, it doesn't always write to its destination immediately

- when the buffer is full, then it gets deposited to the output device

### <u>Sequential file Update</u>

-----------------------------

master File

Transformation File

-----------------------------------

 - apply transformations from Transformation file
- get a 'new' master file

Time stamp associated with each transaction



Stable sort :

- when two keys have same sort key order, want to keep them 



# Master Records

```
// Sequential file update algorithm
MF.open("input"); NMF("output"); TF.open("input");

// read first record from each input file
MR := MF.getRec(); TR := TF.getRec();

while (MR != null) {

  // apply to current master record all relevant transactions
  while (TR.fkey() == MR.key()) {
    // apply TR to MR
    if (isAdd(TR) { 
    	
    })
    else if (isChange(TR)) { 
    	if(!MR_is_empty) {TR.change(MR);} 
   		else { INVALID: deleting nonexistent record } 
    }
    else if (isDelete(TR)) { 
   		if(!MR_is_empty) { MR_is_empty := true; }
   		else { INVALID: deleting nonexistent record }
    }
    else { throw new Error("Invalid transaction type") }
    TR := TF.getRec();
  }
  //write it to disk
  if(!MR_is_empty) {
    NMF.putRec(MR); // append MR to new master file
  } else { MR_is_empty := false; }
  MR := Mf.getRec(); // read next master record
}
```

MR_is_empty -> True

MR: Alan 40 ~~~

TR: Cathy 27

Invalid Transactions:

- Charge or Delete non-existent record
- adds a record with duplicate key


# AVL Trees and Flat Indexes, B-Trees

Number of children exactly one more than the number of  keys

(b) delete 12

minimum nodes is a factor of m-1

underflowing - removing one too little stuff

overflowing - adding one too much stuff

**what happens when a leaf node is full?**

- imagine we stuck it in there
- clustering of information is moving some info out of one node and into a sibling node
  - can 're-organize' info
  - redistribute between two siblings
    - need to make sure that it's balanced correctly
    - IE in the example, cannot insert 16 all the way to the right with the node [6]9]
    - because 16 is less than 19
    - so, swap out parent node, 19, with 16
    - now 19 is greater than 16 so it can go to the rightmost leafzj=
- Take smallest dude in largest subtree, and replace parent node with it
- leafs must be equidistant from the root



if you got m keys and split them down the middle

[www.cs.uofs.edu/~mccloske/courses/cmps340/btree_example_sol.html]: BtreeExampleSolutions

www.cs.uofs.edu/~mccloske/courses/cmps340/btree_example_sol.html



max number of child, $m$, min [$\frac{m}{2}$]

when redistribution is not possible:

- split nodes into two
  - see if structure follows rules
  - if nodes overflowing
    - follow chain up and see if can still work
    - split into two if too many nodes

  [$\frac{m}{2}$] -1

$ + $[$\frac{m}{2} - 2$]

= 2 $\frac{m}{2}$ - 3

= m - 3 (if m is even)

2($\frac{m-1}{2}) - 3 = m + 1 + 2 -3 = m - 2

