package hw4;

import java.util.concurrent.Semaphore;

/* This class will follow the readers-writers
 *  synchronization pattern. This pattern follows
 *  the guidelines that multiple threads can "read"
 *  a shared variable at the same time, as long as no 
 *  thread is currently "writing" to it. Threads are only
 *  able to write to the shared variable if no other threads are
 *  currently reading/writing to it. The bank account
 *  will be the shared variable in this program and it will
 *  be synchronized accordingly using semaphores.+
 * 
 */
public class BankAccountSemaphore extends BankAccount {
	private Semaphore mutexRead;
	private Semaphore modifyBalance;
	private int numOfActiveViewers=0;
	
	

	public BankAccountSemaphore(int initialBalance) {
		super(initialBalance);
		mutexRead = new Semaphore(1);
		modifyBalance= new Semaphore(1);
	}

	@Override
	public void deposit(int amount) {
		startWriting();
		this.balance= balance+ amount;
		endWriting();
	}

	@Override
	public void withdraw(int amount) {
		startWriting();
		balance= balance - amount;
		endWriting();
		
	}
	
	private void startWriting() {
		try {
			//takes token away from here so no users can view and or write
			//the balance while it is being modified
			modifyBalance.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getBalance() {
		int balanceCopy;
		startReading();
		//stores a copy of the balance variable
		//while it's thread-safe from being modified
		 balanceCopy=super.getBalance();
		 endReading();
		 return balanceCopy;
		
	}
	
	private void startReading() {
		try {
			mutexRead.acquire();
			this.numOfActiveViewers++;
			
			if(numOfActiveViewers==1) {
				 //ensures that no threads can write to the balance
				this.modifyBalance.acquire();
			}
			 //ensures that other threads can read/view the balance
			mutexRead.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void endWriting() {
		modifyBalance.release();
	}
	
	private void endReading() {
		try {
			numOfActiveViewers--;
			if(numOfActiveViewers==0) {
				//ensures that the next thread can either write or read
				modifyBalance.release();
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
