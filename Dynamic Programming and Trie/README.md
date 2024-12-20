# Mandatory Assignment

## Student Name: Dion Kumnova

The code for this assignment is located in `src/main/java/assignment`, and the test files can be found in `src/test/java/assignment`.

To run the JUnit tests, you have several options:
- Use IntelliJ (community edition) or VS Code. If you're using IntelliJ, it should work out of the box. For VS Code, you'll need the Extension Pack for Java (recommended).
- Execute the command `gradle test`. Note that this requires Gradle to be installed, which isn't available on IFI machines.
- Download the JUnit jar and run it. However, this method is not recommended and hasn't been tested.

**Note:** 
Only the first option is compatible with IFI machines. You can run VS Code from the Horizon virtual machines.

# Information
- This assignment comprises three exercises. Each student must submit their own, independent solution for all exercises.
- A code skeleton is provided, the delivered work must be inside this folder and work with the provided tests. 
- You should not modify any of the tests or the signatures, but you may add more functions or tests.
- Your code should be readable. Comments are necessary where the code isn't self-explanatory. Remember, 
comments shouldn't be used as an excuse for poorly written code. 
[Read more about best practices for writing code comments.](https://stackoverflow.blog/2021/12/23/best-practices-for-writing-code-comments/)
- Exercises 2a and 3 require textual answers. Provide these answers within this README.md file.
- If there are minor errors or ambiguities in the assignment text, you're expected to make **reasonable** assumptions.
- You have two attempts for this assignment. It's recommended to attempt all tasks in the first go, 
even if you encounter challenges with some. Note that even if your solution is correct, it might be not be accepted if it's not readable. 
2nd attempt will only be granted for a reasonable first attempt i.e. you have to do a reasonable amount of work, but not
necessarily all of it.
- The provided tests don't guarantee the correctness of your solution; they only check the output.
- If you need a 3-day extension, please submit your request via the following nettskjema before the deadline: [nettskjema](https://nettskjema.no/a/356841/)
- While English is the preferred language for code, you can also use Norwegian. However, consistency is key. For textual answers, both English and Norwegian are acceptable.
- You can embed images in the markdown for textual answers using the format: `![Image Description](./image-path.jpg)`.
- Java 17 will be used for testing in java.
- You're allowed to create additional source files.

## Python
- Primarily designed for people who lack prior java knowledge, if you know java it's recommended to use java
- There might be slight differences between the text and the code as the assignment is designed for the java version
- Test can be executed with ```pytest``` after `````cd src/python`````
- Pycharm (community edition is free) allows for running the test py clicking the green play button left for a test
- You may alter the types in the precode or tests to use normal python or more numpy, numpy is generally recommended, but this need to be documented in the README.md file and in the code

# Exercise 1 (Tries)

Write a program that constructs an uncompressed trie (prefix-tree) from a collection of strings and then searches this trie 
for a set of given strings. For simplicity, assume that the strings are composed solely of lower-case letters. 
While repeated insertions of existing strings are allowed, they shouldn't modify the data structure. 
The trie should have a `lookUp` function to verify if a word has been added and a `printTree` function that outputs a 
string representation in alphabetical order.

For instance, if the first three strings inserted into the tree are “internet”, “interview”, and “inter”, the output should resemble:

```
internet: (internet)
interview: (inter(net)(view))
inter: (inter(net)(view))
```

Similarly, if the initial three strings inserted are “inter”, “inter”, and “internet”, the output should be:

```
inter: (inter)
inter: (inter)
internet: (inter(net))
```

A prefix representation of the trie containing the words “internet”, “interview”, “internally”, “algorithm”, “all”, “web”, and “world” would appear as:

```
((al(gorithm)(l))(inter(n(ally)(et))(view))(w(eb)(orld)))
```

### Implementation Tips

- Store the data in nodes that contain a data structure for holding child nodes.
- Each node should have a boolean flag to determine if it marks the end of a word.

# Exercise 2 (Dynamic Programming)

The problem “Sum of Selections” (SOS) is defined as follows: 
Given a sequence of n positive integers t1, t2, ... , tn and a positive integer K, 
we are asked to make a selection of the ti’s with sum exactly K 
(or determine that no such selection exists).

## 2A
Assume you have a Boolean table with dimensions [n] x [S] where n is the number of elements in the input sequence and S is the sum of the sequence. 
To verify if we can achieve K, we can inspect table\[last position][K] to see if it's true or false. 

1. Explain what determines a position as true or false
2. Describe how to construct the table using bottom-up dynamic programming
3. Detail how to populate the table using top-down dynamic programming, starting at table\[last position][K]. This should be a recursive, memoized algorithm 

### Answer 2A
1. The position is true if you can combine the element you are currently on and the previous elements to get the value of sum. 
2. So the idea is to fill out the table interatively, starting with the smaller problems and then building upwards, filling the table row by row. Its quite a systematic approach which gives us a complete table by the end.
3. Here we start with the final element and the target of sum k. Then we check if we can create k with or without each element which we recursively check samller and samlle subproblems. We also store the results in a table (memoization) so we dont recalculate them.  

## 2B
Implement the bottom-up algorithm that solves SOS. Assume K is never greater than S and never less than 1. The Class BottomUpSos precomputes 
the entire table before knowing k and takes the sequence as an Array in the class constructor. The checkSum method accepts k as an argument and returns an array of 
integers that constitute the sum. If the sum cannot be achieved, it returns null.

## 2C
Implement the top-down algorithm that addresses SOS. Assume K is never greater than S and never less than 1. 
The Class ToppDownSos initializes the table (populating the first column and row) before knowing k and accepts 
the sequence as an Array in the class constructor. The checkSum method takes k as an argument and invokes a recursive 
function to determine if k can be achieved by reading from or populating the table. It should only compute the values it needs.

### Implementation Tips
A Boolean can be true, false, or null (unlike boolean with a lowercase b). Null is crucial to determine if a value has 
been computed previously (important for 2c, not 2b). A table might be used multiple times.

# Exercise 3  (Undecidability vs Decidability)
Determine if the following problems (formal languages) are decidable. If a language is decidable, provide an algorithm for deciding it. If a language is undecidable, 
prove this by demonstrating a reduction from the halting problem.

L1 = {w ∈ {0 | 1}* | w is a sequence of one or more 1's } 

L2 = {< M > | Turing machine M decides L1} 

L3 = {< M > | Turing machine M decides L2} 

## Answer L1
This is decideable. An easy way to prove this is simlpy by making an algorithim that iterates through the sequence and idenitifies if there is a 0 there. And if it does find a 0 it can return true, else false. The input is also finite. 


## Answer L2
This is undecidable.

To decide L1 the turing machine needs to accept all strings in L1 and reject all strings not in L1.  

Now why is this undecdiable? Well we can reduce L2 down to the halting problem, which we know is undecidable. Proof by contradiction. 

First we need to assume that L2 is decidable. Meaning lets say we have a turing mashine D that is able to decide L2.

Now given an M and w, meaning the algorthim M that checks the string w for 1s and 0s.  
We then need a turing machine that decides L1 based on if M halts or not, lets call this N. 
If M halts on w, N decides L1. If it doesent halt, N does not decide L1.

Now M will either halt or not halt on an input of w, and N wil then also react accordingly. Meaning that N either is the deicder for L1, or it is not (might be in a loop for example). 

This will then be fed back to D, mentioned at the start, which will then either reject or accept it. Meaning it will know if it halted, or not. Thus contradicting the halting problem and also proving the our intial asumpotion about L2 being decidable is wrong. 


## Answer L3
Since there are no turing machine that decide L2, no turing machine could decide L2. Meaning L3 is empty. An algorihtm could therefore simply reject <M> no matter what. 


# Delivery Checklist
- Ensure your name is at the top of the README file.
- Make a copy of the `Oblig1` folder. In the copied folder, remove the following folders if they exist:
`build`, `bin`, `.idea`, and `.gradle` (note the gradle folder **with** a "." at the start) or any other generated folder or file.
- Zip the copied `Oblig1` folder (it should still be named `Oblig1`). 
Concatenate your username with "Oblig1" to name the zip file (e.g., `usernameOblig1.zip`).
- Upload to Devilry before the deadline. Multiple submissions are allowed; however, only the latest submission will be evaluated.
