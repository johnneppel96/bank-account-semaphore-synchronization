# bank-account-semaphore-synchronization

I completed this project for my Operating Systems Analysis class. I was tasked with writing a thread-safe program using semaphores
that simulated multiple users (threads) acccessing a shared bank account variable. It was demonstrated how code that is not thread-safe
can lead to the values of certain variables becoming nondeterministic. In order to ensure that my code is thread-safe, I made
all the threads will deposit and withdraw the same amount of money at the end of their execution and I tested at the end of the program
whether the final balance of the bank accounts is the same as the initial.
