package hw4;

public abstract class BankAccount {
   protected int balance;
   
  public BankAccount(int initialBalance) {
	  this.balance= initialBalance;
  }
  
  public abstract void deposit(int amount);
  
  public abstract void withdraw(int amount);
  
  public int getBalance() {
	  return balance;
  }
}
