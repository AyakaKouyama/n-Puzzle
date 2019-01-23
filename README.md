# Artificial_Intelligence - n-Puzzle solver
Solving n-Puzzle with BFS, DFS and A* alghoritms.
To run program you have to pass arguments:
* method (astr - A*, dfs, bfs)
* order - order of searching in methods DFS and BFS, for example: LURD means searching in order L (left), U (up), R (right), D (down) or heuristic in method A*: hamm (hamming heuristic), manh (manhattan heuristic)
* file name of file which contains unsloved puzzle. Structure of file must be as shown below:
    
   4 4
   
   1  2  3  4
    
   5  6  7  8
   
   9 10 11 12
  
   13 14 15 0
  
  first line must contain dimensions of puzzle (width and height), others lines contain puzzle (0 means empty element).
* solution file name - name of file where solution will be saved. First line in this file means number of moves, second line contains letters which represent order of  moves.
* statistics file name - name of file where statistics will be saved. This file contains 5 lines: 
    - length of found solution
    - number of visited nodes
    - number of processed nodes
    - maximum recursion depth
    - time of searching solution (in millisecons)
   
For exapmle (using command line):

<b> -java -jar program.jar dfs lurd input.txt output.txt stats.txt </b>
Program made for university purpose.
