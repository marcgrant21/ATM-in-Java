import java.util.Scanner;

public class ATM {
	
	public static void main (String[]args) {
		
		Scanner sc = new Scanner(System.in);
		
		Bank TheBankofRicardo = new Bank ("The Bank of Ricardo");
		
		
		User aUser = TheBankofRicardo.addUser("Jack","Jill","1234");
		
		Account newAccount = new Account("Checking", aUser, TheBankofRicardo);
		
		aUser.addAccount(newAccount);
		TheBankofRicardo.addAccount(newAccount);
		
		User curUser;
		while (true) {
		
			curUser = ATM.mainMenuPrompt(TheBankofRicardo,sc);
			
			ATM.printUserMenu(curUser,sc);
			
			
		}
		
	}
	
	
	public static User mainMenuPrompt(Bank TheBankofRicardo, Scanner sc) {
		
		String userID;
		String pin;
		User authUser;
		
		do {
			
			System.out.printf("\n\n Welcome to %s\n\n", TheBankofRicardo.getname());
			System.out.print("Enter user ID: ");
			userID = sc.nextLine();
			System.out.print("Enter pin: /n ");
			pin =sc.nextLine();
			
			authUser = TheBankofRicardo.userlogin(userID, pin);
			if (authUser==null) {
				System.out.println("Incorrect user ID/pin Combination."+" Please try again");
				
				
			}
		}while(authUser==null);
		
		return authUser;
	}
	
	
	public static void printUserMenu(User theUser, Scanner sc) {
		
		theUser.printAccountsSummary();
		
		
		int choice;
		
		
		do {
			System.out.printf("Welcome %s, what would you like to do ? ",theUser.getfirstname());
			System.out.println("*1** Show account transaction history");
			System.out.println("*2** Withdraw");
			System.out.println("*3** Deposit");
			System.out.println("*4** Transfer");
			System.out.println("*5** Quit");
			System.out.println();
			System.out.println(" Enter choice: ");
			choice=sc.nextInt();
			
			if (choice < 1 || choice>5) {
				System.out.println("Invalid syntax");
			}
		}while(choice < 1 || choice>5);
		
		switch (choice) {
		
		case 1:
			ATM.showTransactionHistory(theUser,sc);
			break;
		case 2:
			ATM.withdrawFunds(theUser , sc);
			break;
		case 3:
			ATM.depositFunds(theUser, sc);
			break;
		case 4:
			ATM.transferFunds(theUser,sc);
			break;
		}
		if (choice!= 5) {
			ATM.printUserMenu(theUser, sc);
		}
		
		
	}
	
	public static void showTransactionHistory(User theUser, Scanner sc) {
		int theAcct;
		do {
			System.out.printf("Enter the number (1-%d of the account"+"whose transaction you want to see", theUser.numAccount());
			
			theAcct=sc.nextInt()-1;
			if(theAcct<0||theAcct>= theUser.numAccount()) {
				System.out.println("Invalid account");
				
			}
			
			
		}while(theAcct<0||theAcct>= theUser.numAccount());
		
		theUser.printAcctTransHistory(theAcct);
		
		
	}
	
	
	public static void transferFunds(User theUser, Scanner sc) {
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
		
		
		do {
			System.out.printf("Enter the number (1-%d) of the account\n"+"to transfer from:");
			fromAcct = sc.nextInt()-1;
			if (fromAcct <0|| fromAcct>= theUser.numAccount()) {
				System.out.println("Invalid account");
			} 
			
			
		}while(fromAcct<0||fromAcct>= theUser.numAccount());
		acctBal=theUser.getAcctBalance(fromAcct);
		
		do {
			System.out.printf("Enter the number (1-%d) of the account\n"+"to transfer to:");
			toAcct = sc.nextInt()-1;
			if (toAcct <0|| toAcct>= theUser.numAccount()) {
				System.out.println("Invalid account");
			} 
			
			
		}while(toAcct<0||toAcct>= theUser.numAccount());
		
		do {
			System.out.printf("Enter the amount to transfer (max $%.02f): $",acctBal);
			
			amount= sc.nextDouble();
			if (amount < 0) {
				
				System.out.println("Amount mucst be greater than zero.");
			}else if (amount>acctBal) {
				System.out.printf("Amount cannot be greater than balance of $%.02f\n",acctBal);
			}
				
			
			
			
		}while(amount<0 || amount> acctBal);
		
		theUser.addAcctTransaction(fromAcct, -1*amount, String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));
		
		
		
		
		
	}
	
	
	public static void withdrawFunds(User theUser, Scanner sc) {
		
	}
	
	public static void depositFunds(User theUser, Scanner sc) {
		
	}
	
	
	

}
