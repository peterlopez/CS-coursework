// Programmer: Peter Ruszel
// Programmer's ID: 1611543
#include <iostream>
#include <ios>
#include <string>
#include <stdexcept>
#include <vector>
#include <sstream>

using namespace std;

#include <cstdlib>

struct InsufficientFundsException : public exception {};
struct WithdrawLimitReachedException : public exception {};

class CheckingAccount
{
    string customerName, customerSSN, customerBirthdate;
    unsigned int accountNumber;
    double balance;
    double dailyWithdrawLimit = 300;
    double dailyWithdrawAmt; // records withdraws to check if they reach limit
public:
    CheckingAccount() {}
    CheckingAccount(string, string, string);
    double withdraw(double);
    double deposit(double);
    int getAccountNumber();
    double getBalance();
};

CheckingAccount::CheckingAccount(string cusName, string cusSSN, string cusBirth) : balance(0), dailyWithdrawAmt(0)
{
    customerName = cusName;
    customerSSN = cusSSN;
    customerBirthdate = cusBirth;

    // Generate random six digit account number
    accountNumber = rand() % 100000 + 100000;

    cout << endl << "New account created!" << endl;
    cout << "Account number is: " << accountNumber << endl << endl;
}

void setupAccount(string&, string&, string&);
bool accountExists(vector<CheckingAccount>, int);
void mainMenu();

int main()
{
    cout << "Programmer: Peter Ruszel\n";
    cout << "Programmer's ID: 1611543\n";
    cout << "File: " << __FILE__ << endl;

    mainMenu();
}

void mainMenu()
{
    vector<CheckingAccount> accounts;

    // options for user
    const int CODE_NEW = 1;
    const int CODE_DEPOSIT = 2;
    const int CODE_WITHDRAW = 3;
    const int CODE_DISPLAY = 4;
    const int CODE_EXIT = 5;
    // selection by user
    int choice = 0;
    // used to display result of last operation
    string outputMsg;
    // used for converting strings to numbers
    // failed conversions will throw an exception
    stringstream ss;
    ss.exceptions(ios::failbit | ios::badbit);

    while (choice != CODE_EXIT) {
        system("CLEAR");
        
        if (!outputMsg.empty()) {
            cout << outputMsg << endl << endl;
            outputMsg = "";
        } else {
            cout << endl << endl;
        }
        
        cout << "---------" << endl;
        cout << "Banking main menu" << endl;
        cout << "(" << CODE_NEW << ") Setup new account" << endl;
        cout << "(" << CODE_DEPOSIT << ") Deposit funds" << endl;
        cout << "(" << CODE_WITHDRAW << ") Withdraw funds" << endl;
        cout << "(" << CODE_DISPLAY << ") Show account balance" << endl;
        cout << "(" << CODE_EXIT << ") Exit" << endl << endl;

        cout << "Make a selection : ";
        string buf;
        cin >> buf;
        choice = atoi(buf.c_str());
        cin.ignore(1000, 10);

        // Prompt user for account number to perform actions on it (deposit, withdraw, etc)
        CheckingAccount* selectedAccount;
        if (choice > CODE_NEW && choice < CODE_EXIT)
        {
            system("CLEAR");
            int accountNumber;

            cout << "Enter account number: ";
            cin >> buf;
            accountNumber = atoi(buf.c_str());
            if(accountExists(accounts, accountNumber))
            {
                // lookup account by account number
                for(int i = 0; i < accounts.size(); i++) {
                    if (accounts[i].getAccountNumber() == accountNumber) {
                        selectedAccount = &accounts[i];
                    }
                }
            }
            else {
                outputMsg = "Invalid account number '" + to_string(accountNumber) + "'";
                continue; // restart main menu while loop
            }
        }

        switch (choice)
        {
            case CODE_NEW: {
                system("CLEAR");
                string name, ssn, birthdate;
                setupAccount(name, ssn, birthdate);

                CheckingAccount newAccount = CheckingAccount(name, ssn, birthdate);
                accounts.push_back(newAccount);

                cout << "Press [Enter] to return to main menu." << endl;
                getline(cin, buf);
                break;
            }
            case CODE_DEPOSIT: {
                double amt;
                cout << "Enter amount to deposit: ";
                cin >> buf;
                try {
                    ss.str(""); ss.clear();
                    ss << buf;
                    ss >> amt;
                } catch (ios_base::failure& e) {
                    outputMsg = "Invalid amount given for deposit. Try again.";
                    break;
                }

                double newBal = selectedAccount->deposit(amt);
                outputMsg = "$"+to_string(amt)+" deposited! New balance is: $"+to_string(newBal);
                break;
            }
            case CODE_WITHDRAW: {
                double amt;
                cout << "Enter amount to withdraw: ";
                cin >> buf;
                try {
                    ss.str(""); ss.clear();
                    ss << buf;
                    ss >> amt;
                } catch(ios_base::failure& e) {
                    outputMsg = "Invalid amount given for withdrawl. Try again.";
                    break;
                }

                try {
                    double newBal = selectedAccount->withdraw(amt);
                    outputMsg = "$"+to_string(amt)+" withdrawn! New balance is: $"+to_string(newBal);
                }
                catch(InsufficientFundsException &e) {
                    outputMsg = "Cannot complete transaction. Insufficient funds in account.";
                }
                catch(WithdrawLimitReachedException& e) {
                    outputMsg = "Cannot complete transaction. Daily withdraw limit has been reached.";
                }
                break;
            }
            case CODE_DISPLAY:
                outputMsg = "Your balance is: $" + to_string(selectedAccount->getBalance());
                break;
            case CODE_EXIT:
                cout << "Exiting." << endl;
                break;
            default:
                cout << "Invalid option. Try again" << endl << endl;
        }
    }
}

bool accountExists(vector<CheckingAccount> accounts, int accountNumber)
{
    for(int i = 0; i < accounts.size(); i++)
    {
        if (accounts[i].getAccountNumber() == accountNumber) {
            return true;
        }
    }
    return false;
}

void setupAccount(string& customerName, string& ssn, string& birthdate)
{
    cout << "Enter customer name: ";
    getline(cin, customerName);
    cout << "Enter SSN: ";
    getline(cin, ssn);
    cout << "Enter birthdate as MM/DD/YY: ";
    getline(cin, birthdate);
}

double CheckingAccount::withdraw(double amt)
{
    if (amt > balance) {
        throw InsufficientFundsException();
    }
    else if ((amt + dailyWithdrawAmt) > dailyWithdrawLimit) {
        throw WithdrawLimitReachedException();
    }
    // proceed
    balance -= amt;
    dailyWithdrawAmt += amt;

    return balance;
}

double CheckingAccount::deposit(double amt)
{
    balance += amt;
    return balance;
}

double CheckingAccount::getBalance()
{
    return balance;
}

int CheckingAccount::getAccountNumber()
{
    return accountNumber;
}
