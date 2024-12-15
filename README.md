# Advent of Code 2024
A place to hold my [Advent of Code 2024](https://adventofcode.com/2024) solutions.

#### Day 1: Historian Hysteria
Compare two lists, first find the total differences then the similarity (count of values that match)
- Task 1: 3574690
- Task 2: 22565391

#### Day 2: Red-Nosed Reports
Red-Nosed Reindeer nuclear fusion/fission plant practices extremely lax safety protocols. 
- Task 1: 624
- Task 2: 658

It is day 2, I'm just going to check every input and sum it up. Why not. Only trip up was realizing the current value might not be the one that needs to be removed.

#### Day 3: Mull It Over
Build our own mini interpreter and discard all non-token values from a scrambled input.
- Task 1: 160672468
- Task 2: 84893551

Regex and Integer.parseInt to the rescue!

#### Day 4: Ceres Search
XMAS and X-MAS searches. 
- Task 1: 2458
- Task 2: 1945

I went for a simple grid traversal solution in both cases. Part 2 was actually easier this way as there were fewer variations to check for.

#### Day 5: Print Queue
The day we parsed a whole lot of arbitrary page ordering rules, checked a bunch of inputs for validity in part 1, and then re-ordered the bad ones for part 2. Calculation was the sum or the mid points of good arrays.
- Task 1: 6505
- Task 2: 6897

I've officially made it further than I did my first year! Yay.

Annoyingly the thing that took the longest today was parsing my inputs. I probably could have got my scanner delimiter regex working better (my guess is I needed to include or keep newLine and white-spaces as well as adding the | pipe or ,) but gave up and split the strings and parsed those into Ints instead.

#### Day 6: Guard Gallivant
Grid traversal then find some traversal loops.
- Task 1: 4647
- Task 2: 1723

Traversing the grind and finding the first set of paths was easy enough, like a much more simplified pathing calculation for a game and I used the same logic I would for that. For part 2, I just updated the 'map' (my grid) to add the obstacles in each place on the original path. 

I finally read up on DFS (depth first search) for grids after completing this, but turns out that's basically what I did here and now I know it this will be helpful in future. My main hurdle on this was when I realised she could get stuck in a cycle that didn't involve the new obstacle, but re-checking the 'visited' list for my valid break condition fixes that.

#### Day 7: Bridge Repair
The day elephants stole our equation operators. Check if sum can be solved (left to right) with limited operation list.
- Task 1: 8401132154762
- Task 2: 95297119227552

This one felt too easy but in a good way. Part two only required adding two lines of code because I had a suspicion that I would end up having to add additional operators to the part 1 check. Building my cute little Calibration Equation class probably took longer than coding up the solver.

#### Day 8: Resonant Collinearity
The day we had to calculate the grid positions of 'antinodes' based on pairs of 'antennae' (chars in the grid).
- Task 1: 276
- Task 2: 991

This is another one where I knew what I wanted the solution to be but spent a lot of time debugging + vs - and traversed the wrong direction for a while.

#### Day 9: Disk Fragmenter
The day we defragged (re-frag?) an imaginary hardrive for a very eccentric crustacean then multiplied the stored values by their resulting locations.
- Task 1: 6390180901651
- Task 2: 6412390114238

This one should have been very easy but I messed up the block size calculations on part 2 for ages. It took me a long time to see that I wasn't decrementing my counter correctly leading additional iterations and misplaced blocks. Still happy that my gut solution was correct just poorly typed on the first attempt.

#### Day 10: Hoof It
The day we traversed a grid again, this time looking for 0-9 trail paths. A reindeer was involved.
- Task 1: 538
- Task 2: 1110

Lucky day today, spent a lot of time debugging my part 1 (turned out to be the equality operator on my custom Coord class) only to find that my bug in part 1 was the solution to part 2!

#### Day 11: Plutonian Pebbles
The day we had to count many many many permutations of magic stones.
- Task 1: 198089
- Task 2: 236302670835517

Today I learned about *dynamic programming* and have thought more about the *heap* allocation in Java than ever before. My somewhat naive solution for part 1 was never going to work for part 2, ArrayLists can't more than int.MAX_VALUE items and my final count is much higher but I was getting heap overflow errors before then anyway. 

Then I realised the stones don't need to stay in order (hurrah) to calculate the next iterations, so I only need to know how many there are of each value to figure out the next iteration, from there it was my old mates `Hashmap` and `stream()` to the rescue for a more memory efficient solution. 

I am now storing the count (as the value) of each distinct number (as the key) in a Hashmap rather than an ArrayList. I'm also only calculating the permutation result for each distinct value once and storing the resulting iteration in a separate hashmap with initial long number (key) and a `long[]` (value) for the resulting stones.

#### Day 12: Garden Groups
The one where we count contagious char blocks on a grid... I mean garden plots!
- Task 1: 1387004
- Task 2: 844198

Today I learned *breadth first search*! 

I also counted the 'sides' for Part 2 by finding corners on my existing mapped area (using a custom 'Plot' class because I misread the wording of the questions initially). The `equals()` override on my Coord object continues to be problematic for `contains()` checks, but converting `toString()` and comparing the strings works for now.

#### Day 13: Claw Contraption
The day with the very special CLAW machines.
- Task 1: 38714
- Task 2: 74015623345775

Much googling of linear algebra, and some reliance on co-pilot to translate mathematical descriptions into code, but eventually we got there. Scanning the input and forgetting about the blank line between machines also took much longer than it should have! Renaming my variables from aX, bY to a1, a2, b1, b2 etc also helped me get my head around what var went where in the actual equations - tricked that dyslexic brain!

#### Day 14: Restroom Redoubt
The day with the robots.
- Task 1: 228410028
- Task 2: 8258

For some reason my regex and scanner combo skills failed me today and I spent hours fluffing about to get the input file parsed and ended up just substring and splitting it in the end.

Part 1 just required my custom Robot class to estimate steps. I am glad that I decided to step individually through `i < seconds` even though I suspected just multiplying the traversals would be quicker. 

Part 2 was a nice surprised despite the needless foundations for various possible further optimizations. Good old Grid class to the rescue (with a few updates I considered at the start and did end up needing anyway). I just searched for a block of robots at the middle of the grid, but had prepped to allow for multiple re-iterations. Found the tree on the first go through and didn't need to improve the grid by counting robots or allowing for any further loops which was a lucky bonus from my particular input and test condition.

#### Day 15: Warehouse Woes
- Task 1: 1577255
- Task 2:

#### Day 16
- Task 1:
- Task 2:

#### Day 17
- Task 1:
- Task 2:

#### Day 18
- Task 1:
- Task 2:

#### Day 19
- Task 1:
- Task 2:

#### Day 20
- Task 1:
- Task 2:

#### Day 21
- Task 1:
- Task 2:

#### Day 22
- Task 1:
- Task 2:

#### Day 23
- Task 1:
- Task 2:

#### Day 24
- Task 1:
- Task 2:

#### Day 25
- Task 1:
- Task 2:
