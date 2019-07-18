package hw4;

import java.util.Random;


public class AccountHolder extends Thread {
	private final static int MIN_WAIT=1;
	private final static int MAX_WAIT=3;
	
	private Random random = new Random();
	private int id;
	private BankAccount ba;
	private int balance;
	private int numTransactions;
	
	public AccountHolder(BankAccount ba, int id, int balance, int NumOfTransactions) throws Exception {
		//Number of transactions must be even because each thread will withdraw and deposit the same 
		//amount of times and the same amount of money. This is to test for thread-safety later.
		if(numTransactions % 2 !=0) {
			throw new Exception("Num of transactions must be even");
	}
	this.id=id;
	this.ba=ba;
	this.balance= balance;
	this.numTransactions=NumOfTransactions;
	
}
	@Override
	public void run() {
		try {
			//The following will simulate an account holder thread accessing a bank account
			//which will be the shared variable that will need to synchronize how its accessed.
			//Each thread will withdraw and deposit equal amounts of money into the bank account
			//during its execution.
			for(int i=0; i<numTransactions;i++) {
				randomWait();
				if(i%2==0) {
					System.out.println("Account Holder " + id + " wants to deposit");
					ba.deposit(balance);
					System.out.println("Account Holder " + id + " deposited");
				}
				
				else {
					System.out.println("Account Holder " + id + " wants to view the balance");
					ba.getBalance();
					System.out.println("Account Holder " + id + " viewed the balance");
					
					System.out.println("Account Holder " + id + " wants to withdraw");
					ba.withdraw(balance);
					System.out.println("Account Holder " + id + " withrew moneys");
				}
			}
			
		}
		catch(Exception e) {
			System.out.println("The account holder " + id + " has completed his transactions");
		}
		
	}
	//This simulates each thread taking an unspecified amount of time
	//to perform each action to the bank account. This makes it more realistic.
	private void randomWait() throws InterruptedException {
		Thread.sleep((random.nextInt(MAX_WAIT-MIN_WAIT+1)+MIN_WAIT));
	}

}
