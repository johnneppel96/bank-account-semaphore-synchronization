package hw4;

public class BankAccountSemaphoreTest {
 final static int numAccountHolders=6;
 final static int numBanks=3;
 final static int initialbalance=1000;
 
 public static void main(String[] args) throws Exception {
	 AccountHolder [] accountHolders= new AccountHolder[numAccountHolders];
	 BankAccount [] bankAccounts= new BankAccountSemaphore[numBanks];
	 
	 for(int i=0; i<bankAccounts.length; i++) {
		 bankAccounts[i]= new BankAccountSemaphore(initialbalance);
	 }
	 
	 for(int j=0; j<accountHolders.length; j++) {
		 if(j<=2) { //0-2
			 accountHolders[j]= new AccountHolder(bankAccounts[0],j,initialbalance,100);
		 }
		 else
		 if(j>=3 && j<5) { //3-4
			 accountHolders[j]= new AccountHolder(bankAccounts[1],j,initialbalance,100);
		 }
		 else
		 if(j==5) { //5
			 accountHolders[j]= new AccountHolder(bankAccounts[2],j,initialbalance,100);
		 }
	 }
	 
	 for(int k=0; k<accountHolders.length; k++) {
		 accountHolders[k].start();
	 }
	 
	 for(int l=0; l<accountHolders.length; l++) {
		 accountHolders[l].join();
	 }
	 
	 //The following is a test whether the system of semaphores
	 //truly keep the bank account thread-safe.
	 for(int m=0; m<bankAccounts.length;m++) {
		 if(bankAccounts[m].getBalance()==initialbalance) {
			 System.out.println("IT WORKED");
		 }
	 }
	 
	 
 }
}
