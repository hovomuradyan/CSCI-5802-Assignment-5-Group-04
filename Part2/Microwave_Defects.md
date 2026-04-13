**Microwave Package Defect Report**

**Obvious Bug Fix:** ticker.run() -> ticker.start() to run on not main thread

Source: Controller.java, Microwave.java, Timer.java

Program compiled using <javac *.java>

Spotbugs ran from Command Line using <spotbugs -textui .> using version 4.9.8

**Spotbugs Output:**

H D UC: Useless condition: it's known that arg0 <= 10 at this point  At Controller.java:[line 132]

Analysis: The check powerLevel < 1 && powerLevel > 10 can never be true because a number cannot be both less than 1 and greater than 10 at the same time. The solution is using the || (OR) operator to check if the value is outside that range.

M B CT: Exception thrown in class microwave.Controller at new microwave.Controller(int, Preset[]) will leave the constructor. The object under construction remains partially initialized and may be vulnerable to Finalizer attacks.  At Controller.java:[line 32]

Analysis: If an error occurs during the reset() call inside the constructor, the object is left in a "broken" half-finished state. This is risky because certain Java features (like finalizers) could still interact with this broken object, potentially causing security or stability issues.

M M AT: Shared primitive variable "doorOpen" in one thread may not yield the value of the most recent write from another thread  At Controller.java:[line 125]

Analysis: Since multiple threads (the timer and the user input) are looking at the door status, one thread might see an old "cached" value instead of the latest change. This happens because the variable isn't marked as volatile, which would force threads to always fetch the most recent version from memory.

M M AT: Shared primitive variable "powerLevel" in one thread may not yield the value of the most recent write from another thread  At Controller.java:[line 38]

Analysis: Similar to the door status, the power level might not be updated correctly across different threads. If the main thread changes the power while the timer thread is running, the timer might keep cooking with the old power level because it hasn't "seen" the update yet.

M M IS: Inconsistent synchronization of microwave.Controller.mode; locked 58% of time  Unsynchronized access at Controller.java:[line 127]

Analysis: The mode variable is sometimes used inside a synchronized block and sometimes not, which can lead to data races. This specific warning points out that an assertion is checking the mode without holding a lock, meaning the mode could change right while it's being checked.

M M IS: Inconsistent synchronization of microwave.Controller.powerLevel; locked 50% of time  Unsynchronized access at Controller.java:[line 160]

Analysis: The getPowerLevel() method isn't synchronized, even though other methods that change the power level are. This means a thread could call "get" and receive a value that is currently in the middle of being changed by another thread.

M M IS: Inconsistent synchronization of microwave.Controller.prog; locked 66% of time  Unsynchronized access at Controller.java:[line 40]

Analysis: The prog variable is being accessed without a lock inside the reset() method, but it is locked in other places. This inconsistency means the microwave's programming state might not reset cleanly if another thread is trying to press a button at the same time.

H I Dm: Found reliance on default encoding in microwave.Microwave.main(String[]): new java.util.Scanner(InputStream)  At Microwave.java:[line 32]

Analysis: The Scanner is reading input using the computer's default text format, which might change depending on the operating system. If someone runs this on a system with a different default encoding, special characters or input might not be read correctly.

M V MS: microwave.Microwave.preProg should be package protected  At Microwave.java:[line 9]

Analysis: The preProg array is currently public, which means any other class can reach in and change your microwave's preset recipes. It’s safer to make it package-protected or private so that only the microwave code itself can control those settings.

M P SBSC: microwave.Timer.toString() concatenates strings using + in a loop  At Timer.java:[line 66]

Analysis: The issue with this line is that it uses the “+” operator which creates a new string object. This inefficiently handles memory and CPU usage. The solution to this is using the .append() method instead.