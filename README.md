# DependencyAnalyser

This code determines the full set of transitive dependencies for a group of items. The code takes as input a set of lines where the first token is the name of an item. The remaining tokens are the names of things that this first item depends on. Given the following input, we know that A directly depends on B and C, B depends on C and E, and so on.


A B C

B C E

C G

D A F

E F

F H


The program should use this data to calculate the full set of dependencies.
The output of the program for the above input should look like:


A B C E F G H

B C E F G H

C G

D A B C E F G H

E F H

F H

## To run the program

Checkout the code locally and run com.zealousdev.DependencyAnalyserTest.testPositiveCase().
