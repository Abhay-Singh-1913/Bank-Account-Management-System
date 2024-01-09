package com.jsp.otm.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jps.otm.protection.Validation;
import com.jps.otm.validationcontroller.ValidationController;
import com.jsp.otm.accountcontroller.AccountController;
import com.jsp.otm.bankcontroller.BankController;
import com.jsp.otm.model.Account;
import com.jsp.otm.model.Bank;

public class View {

	public static void main(String[] args) {
		ValidationController vController = new ValidationController();
		Scanner scanner = new Scanner(System.in);
		final String red = "\u001b[31;1m";
		final String white = "\u001b[37;1m";
		final String green = "\u001b[32;1m";
		final String reset = "\u001b[0m";
		final String black = "\u001b[37m";

		System.out.println("\u001b[33m" + " ___________");
		System.out.println("|   MENU    |");
		System.out.println("|-----------|");
		System.out.println("| 1.SignUp  |\n| 2.LogIn   |\n| 0.EIXT    |");
		System.out.println("|___________|");
		System.out.println("------------------------");
		System.out.print("Enter your choice:->");
		int choice = scanner.nextInt();
		scanner.nextLine();
		System.out.println("------------------------" + reset);

		switch (choice) {
		case 0:
			scanner.close();
			System.out.println(red);
			System.out.println("---------X---EXIT---X---------");
			System.out.println();
			System.out.println(white + "--VISIT-AGAIN--");
			break;
		case 1:
			System.out.print("Username : ");
			String New_username = scanner.nextLine();
			System.out.print("Password : ");
			String New_password = scanner.nextLine();
			System.out.println("Are you a Banker Y/N");
			String answer = scanner.nextLine();

			String persontype = null;
			if (answer.equalsIgnoreCase("Y")) {
				persontype = "banker";
			} else if (answer.equalsIgnoreCase("N")) {
				persontype = "user";
			}
			Validation validate = new Validation();
			validate.setUsername(New_username);
			validate.setUser_password(New_password);
			validate.setPerson(persontype);
			boolean signInStatus = vController.signIn(validate);
			if (signInStatus) {
				System.out.println(green + "-------------------------------");
				System.out.println("| SignIn detail Successfully |");
				System.out.println("-------------------------------");
			} else {
				System.out.println(red + "--------------------------");
				System.out.println("| Failed to SignIn detail |");
				System.out.println("--------------------------" + reset);
			}
			System.out.println();
			break;
		case 2:
			System.out.print("Username : ");
			String username = scanner.nextLine();
			System.out.print("Password : ");
			String password = scanner.nextLine();
			System.out.println();
			Validation validation_status = null;
			try {
				validation_status = vController.findUser(username, password);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (validation_status != null) {
				System.out.println(green + "-------------------------------");
				System.out.println("| Login Successfully |");
				System.out.println("-------------------------------");

				String PersonName = validation_status.getUsername();
				String personIs = validation_status.getPerson();
				int securityCode = 0;
				if (personIs.equalsIgnoreCase("banker")) {
					securityCode = 1;
				} else if (personIs.equalsIgnoreCase("user")) {
					securityCode = 2;
				}
				switch (securityCode){
				case 0:
					scanner.close();
					System.out.println();
					System.out.println(red + "--------------------------");
					System.out.println("| You are not a valid user/banker  |");
					System.out.println("--------------------------" + reset);
					break;
				case 1:
					BankController bankController = new BankController();
					System.out.println(green + "-------------------------------");
					System.out.println("| Welcome Banker " + PersonName + " |");
					System.out.println("-------------------------------");
					System.out.println();
					boolean Flag = true;
					do {

						System.out.println("\u001b[33m" + " ___________");
						System.out.println("|   MENU    |");
						System.out.println("|-----------|");
						System.out.println("| 1.INSERT  |\n| 2.UPDATE  |\n| 3.DELETE  |\n| 4.DISPLAY |\n| 0.EXIT    |");
						System.out.println("|___________|");
						System.out.println("------------------------");
						System.out.print("Enter your choice:->");
						int banker_choice = scanner.nextInt();
						scanner.nextLine();
						System.out.println("------------------------" + reset);

						switch (banker_choice) {
						case 0:
							scanner.close();
							System.out.println(red);
							System.out.println("---------X---EXIT---X---------");
							System.out.println();
							System.out.println(white + "--VISIT-AGAIN--");
							Flag = false;
							break;
						case 1:

							// INSERT CASE //

							System.out.println(green + "------------------------------------");
							System.out.println("Enter the details of Bank and Accounts:");
							System.out.println("------------------------------------");
							System.out.println("--------------------------");
							System.out.print("Bank_id:->");
							int insertId = scanner.nextInt();
							scanner.nextLine();
							System.out.println("--------------------------");
							System.out.println("--------------------------");
							System.out.print("Bank_name:->");
							String insertName = scanner.nextLine();
							System.out.println("--------------------------");
							System.out.println("--------------------------");
							System.out.print("Bank_city:->");
							String insertCity = scanner.nextLine();
							System.out.println("--------------------------");
							System.out.println("--------------------------");
							System.out.print("IFSC:->");
							String insertIFSC = scanner.nextLine();
							System.out.println("--------------------------");
							System.out.println("--------------------------");
							System.out.print("min_balance:->");
							double insertmin_balance = scanner.nextDouble();
							scanner.nextLine();
							System.out.println("--------------------------");

							Bank bank_ref = new Bank();
							bank_ref.setId(insertId);
							bank_ref.setBank_name(insertName);
							bank_ref.setCity_name(insertCity);
							bank_ref.setBank_IFSC_code(insertIFSC);
							bank_ref.setMin_balance(insertmin_balance);

							System.out.println("--------------------------");
							System.out.print("Number of account to add:->");
							int number = scanner.nextInt();
							scanner.nextLine();
							System.out.println("--------------------------");

							ArrayList<Account> accounts = new ArrayList();
							int no_of_account = 0;
							for (int i = 0; i < number; i++) {
								System.out.println("--------------------------");
								System.out.print("Account_id:->");
								int insert_Account_Id = scanner.nextInt();
								scanner.nextLine();
								System.out.println("--------------------------");
								System.out.println("--------------------------");
								System.out.print("Account_no:->");
								long insert_Account_no = scanner.nextLong();
								scanner.nextLine();
								System.out.println("--------------------------");
								System.out.println("--------------------------");
								System.out.print("Account_Holder_name:->");
								String insert_Account_name = scanner.nextLine();
								System.out.println("--------------------------");
								System.out.println("--------------------------");
								System.out.print("Account_balance:->");
								double insert_Account_balance = scanner.nextDouble();
								scanner.nextLine();
								System.out.println("--------------------------");
								System.out.println("--------------------------");
								System.out.print("Account_Holder_contact:->");
								long insert_Account_con = scanner.nextLong();
								scanner.nextLine();
								System.out.println("--------------------------");
								System.out.println("--------------------------");
								System.out.print("Account_pincode:->");
								int insert_Account_pincode = scanner.nextInt();
								scanner.nextLine();
								System.out.println("--------------------------");

								Account account1 = new Account();
								account1.setId(insert_Account_Id);
								account1.setAccount_number(insert_Account_no);
								account1.setAccount_holderName(insert_Account_name);
								account1.setBalance(insert_Account_balance);
								account1.setContact(insert_Account_con);
								account1.setPincode(insert_Account_pincode);
								account1.setBank(bank_ref);

								accounts.add(account1);

								no_of_account++;
							}
							bank_ref.setNo_of_account(no_of_account);
							bank_ref.setAccount(accounts);
							boolean fullDetailsOfBank = bankController.addBankAndAccounts(bank_ref, accounts);

							if (fullDetailsOfBank) {
								System.out.println(green + "-------------------------------");
								System.out.println("| Inserted detail Successfully |");
								System.out.println("-------------------------------");
							} else {
								System.out.println(red + "--------------------------");
								System.out.println("| Failed to insert detail |");
								System.out.println("--------------------------" + reset);
							}
							System.out.println();
							break;
						case 2:

							// UPDATE CASE //

							System.out.println(green + "-----------------------------------");
							System.out.println("Enter the detail of Bank to Update:");
							System.out.println("-----------------------------------");
							System.out.println("--------------------------");
							System.out.print("Enter id to update:->");
							int bankupdate_id = scanner.nextInt();
							scanner.nextLine();
							System.out.println("--------------------------");
							System.out.print("Bank_name:->");
							String updatedName = scanner.nextLine();
							System.out.println("--------------------------");

							if (bankController.updateBank(bankupdate_id, updatedName)) {
								System.out.println("------------------------------");
								System.out.println("| Bank as Updated Successfully |");
								System.out.println("------------------------------");
							} else {
								System.out.println(red + "---------------");
								System.out.println("| Bank no found |");
								System.out.println("---------------" + reset);
							}
							System.out.println();
							break;

						case 3:

							// DELETE CASE //

							System.out.println("\u001b[33m" + " ___________");
							System.out.println("|   MENU    |");
							System.out.println("|-----------");
							System.out.println(
									"|-> 1.Delect Bank & Accounts \n|-> 2.Delete one Account  \n|-> 0.EXIT    ");
							System.out.println("______________" + reset);
							System.out.println(green + "---------------------------------");
							System.out.print("| Enter the choice to delete :");
							byte deletechoice = scanner.nextByte();
							scanner.nextLine();
							System.out.println("---------------------------------");
							switch (deletechoice) {
							case 1:
								System.out.println("--------------------------");
								System.out.print("Enter Bank_id to delete:->");
								int deleteBankId = scanner.nextInt();
								scanner.nextLine();
								System.out.println("--------------------------");
								if (bankController.removeBank(deleteBankId)) {
									System.out.println("----------------------------------------");
									System.out.println("| Bank/Accounts as Deleted Successfully |");
									System.out.println("-----------------------------------------");
								} else {
									System.out.println(red + "---------------");
									System.out.println("| Bank no found |");
									System.out.println("---------------" + reset);
								}
								break;
							case 2:
								System.out.println("--------------------------");
								System.out.print("Enter Bank_id :->");
								int delete_bankId = scanner.nextInt();
								scanner.nextLine();
								System.out.println("--------------------------");

								List<Account> allAccount = bankController.allAccount();
								

								if (allAccount != null) {
									System.out.println("_________________________________");
									System.out.println("//////---Account DETAILS---//////");
									for (Account account : allAccount) {
										System.out.println("_________________________________");
										System.out.println("| Account id : " + account.getId());
										System.out.println("| Account number : " + account.getAccount_number());
										System.out.println("| Account holder name : " + account.getAccount_holderName());
										System.out.println("| Account balance : " + account.getBalance());
										System.out.println("| Account contact" + account.getContact());
										System.out.println("| Account pincode" + account.getPincode());
										System.out.println("_________________________________");
									}
								} else {
									System.out.println(red + "-------------------------");
									System.out.println("| Account List is Empty |");
									System.out.println("-------------------------" + reset);
								}
								
								System.out.println("--------------------------");
								System.out.print("Enter Account_id to delete:->");
								int delete_acccountId = scanner.nextInt();
								scanner.nextLine();
								System.out.println("--------------------------");

								if(bankController.removeAccounts(delete_bankId, delete_acccountId)) {
									System.out.println("----------------------------------");
									System.out.println("| Account as Delete Successfully |");
									System.out.println("----------------------------------");
								} else {
									System.out.println(red + "-------------------------");
									System.out.println("| Bank/Accounts no found |");
									System.out.println("-------------------------" + reset);
								}
								break;

							default:
								System.out.println(red + "--------------------------");
								System.out.println("| Enter the valid choice |");
								System.out.println("--------------------------" + reset);
								break;
							}
							break;

						case 4:

							// FIND CASE //

							System.out.println(green + "---------------------------------");
							System.out.println("Enter the id of bank to display:");
							System.out.println("---------------------------------");
							System.out.println("--------------------------");
							System.out.print("Enter id to find: ");
							int findId = scanner.nextInt();
							scanner.nextLine();
							System.out.println("--------------------------");

							System.out.println();
							Bank getDetail = bankController.findBank(findId);

							if (getDetail != null) {
								System.out.println(white + "_________________________________");
								System.out.println("////////---Bank DETAILS---///////");
								System.out.println("_________________________________");
								System.out.println("| Bank id: " + getDetail.getId());
								System.out.println("| Bank Name: " + getDetail.getBank_name());
								System.out.println("| Bank IFSC code: " + getDetail.getBank_IFSC_code());
								System.out.println("| Bank Minimum balance: " + getDetail.getMin_balance());
								System.out.println("| Bank City: " + getDetail.getCity_name());
								System.out.println("| No. of Accounts in bank is : " + getDetail.getNo_of_account());
								System.out.println("_________________________________");

								List<Account> account2 = getDetail.getAccount();

								for (Account account : account2) {
									System.out.println("_________________________________");
									System.out.println("//////---Account DETAILS---//////");
									System.out.println("_________________________________");
									System.out.println("| Account id : " + account.getId());
									System.out.println("| Account number : " + account.getAccount_number());
									System.out.println("| Account holder name : " + account.getAccount_holderName());
									System.out.println("| Account balance : " + account.getBalance());
									System.out.println("| Account contact" + account.getContact());
									System.out.println("| Account pincode" + account.getPincode());
									System.out.println("_________________________________");
								}
							} else {
								System.out.println(red + "------------------");
								System.out.println("| Data not found |");
								System.out.println("------------------");
							}

							System.out.println();
							break;

						default:
							System.out.println(red + "--------------------------");
							System.out.println("| Enter the valid choice |");
							System.out.println("--------------------------" + reset);
							break;
						}
					} while (Flag);

					break;
				case 2:
					AccountController accountController = new AccountController();
					System.out.println(green + "-------------------------------");
					System.out.println("| Welcome User " + PersonName + " |");
					System.out.println("-------------------------------");
					System.out.println();
					boolean Flag1 = true;
					do {
						System.out.println("\u001b[33m" + " ___________");
						System.out.println("|   MENU    |");
						System.out.println("|-----------|");
						System.out.println("| 1.INSERT  |\n| 2.UPDATE  |\n| 3.DISPLAY |\n| 0.EXIT    |");
						System.out.println("|___________|");
						System.out.println("------------------------");
						System.out.print("Enter your choice:->");
						int user_choice = scanner.nextInt();
						scanner.nextLine();
						System.out.println("------------------------" + reset);

						switch (user_choice) {
						case 0:
							scanner.close();
							System.out.println(red);
							System.out.println("---------X---EXIT---X---------");
							System.out.println();
							System.out.println(white + "--VISIT-AGAIN--");
							Flag1 = false;
							break;
						case 1:
							// INSERT CASE //
							break;
						case 2:
							// UPDATE CASE //
							System.out.println(green + "-----------------------------------");
							System.out.println("Enter Yuur details of Account to Update:");
							System.out.println("-----------------------------------");
							System.out.println("--------------------------");
							System.out.print("Enter your id to update:->");
							int Accountupdate_id = scanner.nextInt();
							scanner.nextLine();
							System.out.println("--------------------------");
							System.out.print("Updated Account Balance:->");
							double updatedBalance = scanner.nextDouble();
							System.out.println("--------------------------");

							if (accountController.updateAccount(Accountupdate_id, updatedBalance)) {
								System.out.println("------------------------------");
								System.out.println("| Account as Updated Successfully |");
								System.out.println("------------------------------");
							} else {
								System.out.println(red + "---------------");
								System.out.println("| Account no found |");
								System.out.println("---------------" + reset);
							}
							break;
						case 3:
							// FIND CASE //
							System.out.println(green + "---------------------------------");
							System.out.println("Enter the id of Account to display:");
							System.out.println("---------------------------------");
							System.out.println("--------------------------");
							System.out.print("Enter id to find: ");
							int findAccountId = scanner.nextInt();
							scanner.nextLine();
							System.out.println("--------------------------");

							Account account = accountController.findAccount(findAccountId);

							System.out.println("_________________________________");
							System.out.println("//////---Account DETAILS---//////");
							System.out.println("_________________________________");
							System.out.println("| Account id : " + account.getId());
							System.out.println("| Account number : " + account.getAccount_number());
							System.out.println("| Account holder name : " + account.getAccount_holderName());
							System.out.println("| Account balance : " + account.getBalance());
							System.out.println("| Account contact" + account.getContact());
							System.out.println("| Account pincode" + account.getPincode());
							System.out.println("_________________________________");

							Bank bank = account.getBank();

							System.out.println("_________________________________");
							System.out.println("////////---Bank DETAILS---///////");
							System.out.println("_________________________________");
							System.out.println("| Bank id: " + bank.getId());
							System.out.println("| Bank Name: " + bank.getBank_name());
							System.out.println("| Bank IFSC code: " + bank.getBank_IFSC_code());
							System.out.println("| Bank Minimum balance: " + bank.getMin_balance());
							System.out.println("| Bank City: " + bank.getCity_name());
							System.out.println("| No. of Accounts in bank is : " + bank.getNo_of_account());
							System.out.println("_________________________________");

							break;

						default:
							System.out.println(red + "--------------------------");
							System.out.println("| Enter the valid choice |");
							System.out.println("--------------------------" + reset);
							break;
						}
					} while (Flag1);

					break;

				default:
					System.out.println(red + "--------------------------");
					System.out.println("| Enter the valid choice |");
					System.out.println("--------------------------" + reset);
					break;
				}

			}else {
				System.out.println(red + "---------------------------------------------");
				System.out.println("| Failed to Login (Username/password invalid) |");
				System.out.println("---------------------------------------------" + reset);
			}
			break;

		default:
			System.out.println(red + "--------------------------");
			System.out.println("| Enter the valid choice |");
			System.out.println("--------------------------" + reset);
			break;
		}

	}
}
