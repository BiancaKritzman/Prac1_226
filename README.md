# Prac1_226

## name        student num
Bianca        25144465
Lillian       25253990
Hayley        25101821

# How to run code

javac *.java
java main


# Explain the purpose of mutual exclusion

To prevent 2 threads from entering the critical section at the same time and causing error in the result

# Demonstrate the behaviour of concurrent threads


# Explain why certain algorithms fail

LockOne fails with concurrent threads if execution overlaps where both threads set their flags to true before checking the boolean of the other threads flag. Then both threads will check the other threads flag and see it is true and enter a permanent state of waiting causing deadlock.

LockTwo works correctly with concurrent threads because when the second thread attempts to acquire the lock, it sets victim to its own ID, allowing the first thread to enter the critical section However, LockTwo fails with sequential threads. If a single thread attempts to acquire the lock first, it waits indefinitely because no other thread changes victim. Thus causing deadlock.

# Explain how Peterson’s Lock resolves these failures
Peterson's lock combines the flag array from LockOne and the victim variable from LockTwo. 

This fixes LockOne's deadlock by using victim as a tie-breaker. Like LockOne, each thread sets its flag to true but each thread also immediately writes to the shared victim variable. If both threads write to victim at nearly the same time, only one write can be the last one. Whichever thread is in victim is forced to wait. This forces one thread to go to the critical section instead of both threads wating on each other.

This fixes LockTwo's deadlock by only checking victim when the other thread is actually interested. If only one thread ever attempts to acquire the lock, the second thread's flag is never true, since that thread never ran. This means the waiting thread's condition fails, regardless of what victim holds, so the thread enters right away rather than waiting on a victim value that would otherwise never change.
