/**
 * 
 */

/**
 * @author marcgrant
 *
 */

import java.util.ArrayList;
import java.security.MessageDigest;


public class User {

	private String firstname;
	
	private String lastname;
	
	private String uuid;
	
	private byte pinHash[];
	
	private ArrayList<Account> accounts;
	
	public User(String firstname, String lastname, String pin, Bank TheBankofRicardo) {

		this.firstname = firstname;
		this.lastname = lastname;
		
		//md5 hash
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash=md.digest(pin.getBytes());
			
		
		} catch (Exception e) {
			System.err.println(" Error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		this.uuid = TheBankofRicardo.getNewUserUUID();
		
		this.accounts = new ArrayList<Account>();
		
		System.out.printf("New User %s, %s with ID %s created.\n", lastname, firstname, this.uuid);
		
		
	}
	
	public void addAccount(Account onAcct) {
		this.accounts.add(onAcct);
		
	}
	
	public String getUUID() {
		
		return this.uuid;
	}
	
	public boolean validatePin(String aPin) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()),this.pinHash);
			
		
		} catch (Exception e) {
			System.err.println(" Error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		return false;
		
	}
	
	public String getfirstname() {
		return this.firstname;
	}
	
	
	public void printAccountsSummary() {
		
		System.out.printf("\n\n %s accounts summary", this.firstname);
		for(int a=0; a<this.accounts.size();a++ ) {
			System.out.printf("%d) %s\n",a+1, this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
	}
	
	public int numAccount() {
		return this.accounts.size();
	}
	
	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printAccTransHistory();
	}
	
	
	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}
	
	
	
}
