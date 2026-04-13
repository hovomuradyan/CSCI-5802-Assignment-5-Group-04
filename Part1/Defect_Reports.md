**Defect Report**

**Bad Practice:**

Source: Bad_Practice.java

Program compiled using <javac Bad_Practice.java>

Spotbugs ran from Command Line using <spotbugs -textui Bad_Practice.class> using version 4.9.8

Spotbugs Output: 

H C SA: Self comparison of $L1 with itself Bad_Practice.main(String[])  At Bad_Practice.java:[line 5]

M B ES: Comparison of String objects using == or != in Bad_Practice.main(String[])  At Bad_Practice.java:[line 5]

Analysis:

Used "==" to compare two strings rather than the .equals() method. This is considered bad coding practice and was caught by spotbugs. This is a real issue because the "==" method compared the two addresses rather than the value of the two strings. To fix this, we should use the .equals method.

**Correctness:**

Source: Correctness.java

Program compiled using <javac Correctness.java>

Spotbugs ran from Command Line using <spotbugs -textui Correctness.class> using version 4.9.8

Spotbugs Output: 

H C IL: There is an apparent infinite loop in Correctness.main(String[])  At Correctness.java:[line 4]

Analysis:

Had a while loop running on a counter that did not increment properly. This is a real issue because the loop runs forever when executed which is not desired. To fix this we would avoid while loops or ensure that the loop terminates.

**Performance:**

Source: Performance.java

Program compiled using <javac Performance.java>

Spotbugs ran from Command Line using <spotbugs -textui Performance.class> using version 4.9.8

Spotbugs Output: 

M P Dm: Performance.main(String[]) invokes inefficient new String(String) constructor  At Performance.java:[line 4]

Analysis:

Copying one string into another before printing the new string. This is a real issue because this is inefficient and unnecessary. To fix this we would avoid unnecessary copies and memory movement. The operation as a whole takes more time and memory than we need.

**Dodgy Code and Internationalization:**

Source: DodgyCode_Internationalization.java

Program compiled using <javac DodgyCode_Internationalization.java>

Spotbugs ran from Command Line using <spotbugs -textui DodgyCode_Internationalization.class> using version 4.9.8

Spotbugs Output: 

M D NP: Dereference of the result of readLine() without nullcheck in DodgyCode_Internationalization.main(String[])  At DodgyCode_Internationalization.java:[line 10]

H I Dm: Found reliance on default encoding in DodgyCode_Internationalization.main(String[]): new java.io.InputStreamReader(InputStream)  At DodgyCode_Internationalization.java:[line 7]

Analysis:

The program takes in a user provided input and prints the length of the input. The two bugs caught by spotbugs are: the code not checking if line (result of readLine()) is null before printing the length and reliance on default encoding when converting the user input to string. These are real errors although more rare and less likely. The fix would be to check for a null result and solidify the encoding before dealing with automated conversion between bytes and String.

**Malicious Code Vulnerability:**

Source: Malicious_Code_Vulnerability.java

Program compiled using <javac Malicious_Code_Vulnerability.java>

Spotbugs ran from Command Line using <spotbugs -textui Malicious_Code_Vulnerability.class> using version 4.9.8

Spotbugs Output: 

H V MS: Malicious_Code_Vulnerability.a isn't final but should be  At Malicious_Code_Vulnerability.java:[line 2]

Analysis:

The program declares a public static string a and prints it out in the main. Spotbugs says this is a real error because the static string is not final, so it can be changed maliciously later on. A fix can be just to use a final static String declaration.