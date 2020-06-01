import java.util.ArrayList;
public class Account {
	private String name;
	private String uuid;
	private User holder;
	private ArrayList<Transaction> transactions;
	
	
	public Account(String name, User holder, Bank TheBankofRicardo) {
		
		this.name= name;
		this.holder = holder;
		
		this.uuid = TheBankofRicardo.getNewAccountUUID();
		
		
		this.transactions= new ArrayList<Transaction>();
		
		
	}
	
	public String getUUID() {
		
		return this.uuid;
	}
		
	public String getSummaryLine() {
		
		double balance = this.getBalance();
		
		if(balance >=0) {
			return String.format("%s: $%.02f : %s", this.uuid,balance,this.name);
		}else {
			return String.format("%s: $(%.02f) : %s", this.uuid,balance,this.name);
		}	
		
	}
	
	
	
	public double getBalance() {
		double balance = 0;
		for (Transaction t : this.transactions) {
			
			balance +=t.getamount();
		}
		return balance;
	}
	
	public void printAccTransHistory() {
		System.out.printf("\n Transaction history for account %s\n", this.uuid);
	
		for (int t = this.transactions.size()-1;t>=0;t--) {
			
			System.out.printf(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
	}
	
	
	}
	
	
	
	
	
	
	
