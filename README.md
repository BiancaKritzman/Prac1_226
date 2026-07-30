# Prac1_226
Practical focuses on mutual exclusion and locks

# name        student num
Bianca        25144465
Lillian       25253990

# How to run code




# Explain the purpose of mutual exclusion

To prevent 2 threads from entering the critical section at the same time and causing error in the result

# Demonstrate the behaviour of concurrent threads



# Explain why certain algorithms fail

LockOne fails with concurrent threads if execution overlaps where both threads set their flags to true before checking the boolean of the other threads flag. Then both threads will check the other threads flag and see it is true and enter a permanent state of waiting causing deadlock.
LockTwo works correctly with concurrent threads because when the second thread attempts to acquire the lock, it sets victim to its own ID, allowing the first thread to enter the critical section However, LockTwo fails with sequential threads. If a single thread attempts to acquire the lock first, it waits indefinitely because no other thread changes victim. Thus causing deadlock.

# Explain how Peterson’s Lock resolves these failures
