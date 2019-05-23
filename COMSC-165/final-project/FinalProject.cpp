/***************************************************************
Final Project - Calculator

Name: Peter Lopez Ruszel
ID: 1611543
Date: 5/20/19
Status: complete
****************************************************************/

#include <string>
#include <sstream>
#include <iostream>
#include <fstream>
#include <vector>
#include <stdlib.h>
#include <iomanip>
#include <cmath>

using namespace std;

// Filename to save history
const string HIST_FILE = "calc.txt";
// Number of lines displayed for history
const int HIST_SIZE = 5;

/**
 *
 * Perform series of math operations
 * according to PEMDAS
 *
 *  1. Find index of first (leftmost) operator within string of operations (i.e. '*', '/', '+', '-')
 *
 *  2. Extract left and right sides of expression
 *
 *  3. Calculate result of expression
 *
 *  4. Substitute result back into string containing all operations
 *
 */
double doOperation(const string &operationStr);

/**
 * Print out list of most recent history items
 * number of items according to HIST_SIZE
 */
void displayRecentHistory(vector<string> hist);

/**
 * Print out list of all history items
 */
void displayAllHistory(vector<string> hist);

/**
 * Read history file
 */
vector<string> loadHistory();

/**
 * Write history items to history file
 * each on their own line
 */
void saveHistory(const vector<string> &hist);

/**
 * Wipe history file
 */
void clearHistory();

/**
 * Outputs result of calculation in 3 ways:
 * decimal, hex, and binary format
 */
void displayResult(const string &operation, const double &result);

/**
 * Checks user input is a valid mathematical expression
 */
bool isValidExpression(const string expression);

/**
 * Removes all spaces from given string
 */
string stripSpaces(string str);

/**
 * Prompts user for calculator input
 */
void mainMenu(vector<string> &hist);

int main()
{
    vector<string> hist = loadHistory();
    mainMenu(hist);
    return 0;
}

void mainMenu(vector<string> &hist)
{
    const string CODE_EXIT = "exit";
    const string CODE_CLEAR = "clear";
    const string CODE_HIST = "history";

    string operation = "";
    double result = 0;

    while (operation != CODE_EXIT)
    {
        system("clear");
        cout << "Welcome to Calculator" << endl << endl;
        displayRecentHistory(hist);

        cout << "Please input operation: ";
        string input;
        getline(cin, input);
        operation = stringstream(input).str();

        if (operation == CODE_EXIT) {
            // do nothing
        }
        else if (operation == CODE_CLEAR) {
            clearHistory();
            cout << "History cleared." << endl;
            cout << endl << "Press enter to continue..";
            getline(cin, input);
        }
        else if (operation == CODE_HIST) {
            displayAllHistory(hist);
            cout << endl << "Press enter to continue..";
            getline(cin, input);
        }
        else if (toupper(operation.at(0)) == 'H') {
            int histNum = stoi(string(operation.begin() + 1, operation.end()));
            if (histNum > hist.size() || histNum < 0) {
                cout << "No history entry for 'H" << histNum << "'" << endl;
                cout << endl << "Press enter to continue..";
                getline(cin, input);
            }
            else {
                result = doOperation(hist.at(histNum - 1));
                displayResult(hist.at(histNum - 1), result);
                cout << endl << "Press enter to continue..";
                getline(cin, input);
            }
        }
        else {
            if (isValidExpression(operation)) {
                result = doOperation(operation);
                displayResult(operation, result);
                hist.push_back(operation);
                cout << endl << "Press enter to continue..";
                getline(cin, input);
            } else {
                cout << "Invalid operation!" << endl;
                cout << endl << "Press enter to continue..";
                getline(cin, input);
            }
        }
    }

    saveHistory(hist);
    cout << endl << "Good Bye!" << endl << endl;
}

void displayResult(const string &operation, const double &result)
{
    cout << "Result: " << operation << " = " << fixed << setprecision(2) << result;
    cout << '\t' << "Hex: " << hex << static_cast<int>(result);
    cout << '\t' << "Oct: " << oct << static_cast<int>(result);
    cout << '\t' << "Bin: " << bitset<4>(result) << endl;
}

string stripSpaces(string str) {
    str.erase(remove(str.begin(), str.end(), ' '), str.end());
    return str;
}

void clearHistory()
{
    ofstream histFile;
    histFile.open(HIST_FILE, ios::out | ios::trunc);
    histFile.close();
}
void displayRecentHistory(vector<string> hist)
{
    if (hist.empty()) {
        cout << "No history" << endl << endl;
        return;
    }

    if (hist.size() <= HIST_SIZE) {
        for(int i = 0; i < hist.size(); i++) {
            cout << 'H' << i+1 << '\t' << hist.at(i) << endl;
        }
    }
    else {
        for (vector<string>::iterator it = hist.end() - HIST_SIZE; it < hist.end(); it++) {
            cout << 'H' << it - hist.begin() + 1 << '\t' << *it << endl;
        }
    }
    cout << endl;
}
void displayAllHistory(vector<string> hist)
{
    if (hist.empty()) {
        cout << "No history" << endl << endl;
    }
    for(int i = 0; i < hist.size(); i++) {
        cout << 'H' << i+1 << '\t' << hist.at(i) << endl;
    }
    cout << endl;
}
vector<string> loadHistory()
{
    vector<string> hist;

    // Open history file
    ifstream histFile(HIST_FILE);
    if (histFile) {
        string line;
        while (getline(histFile, line)) {
            hist.push_back(line);
        }
        histFile.close();
    }
    return hist;
}
void saveHistory(const vector<string> &hist)
{
    ofstream histFile;
    histFile.open(HIST_FILE, ios::out | ios::app);
    if (histFile) {
        for(string s : hist) {
            histFile << s << endl;
        }
        histFile.close();
    }
}

double doOperation(const string &operationStr)
{
    //
    // when each step is solved the answer
    // is substituted back into newOperationStr
    //
    // eventually, this gets to be a string with only
    // a single number after all substitutions have been made
    //
    stringstream newOperationStr(operationStr);
    string oldOperationStr;

    // this stores a single operation within each step
    // ex. operation inside parenthesis
    string localExpression;
    double localResult;

    // Left and right sides of operation
    string leftStr, rightStr;
    double left, right = 0;

    // Indexes within newOperationStr
    // used to substitute in result
    int start, end;

    // Remove all spaces
    newOperationStr.str( stripSpaces(newOperationStr.str()) );

    // -----------------
    // Do (P)arenthesis
    //
    while (newOperationStr.str().find('(') != string::npos && newOperationStr.str().find(')') != string::npos)
    {
        // Extract and calculate operation within parenthesis
        localExpression = string(newOperationStr.str(), newOperationStr.str().find_first_of('(') + 1, newOperationStr.str().find_first_of(')') - newOperationStr.str().find_first_of('(') - 1);
        localResult = doOperation(localExpression);

        //
        // Substitute result back in
        //
        oldOperationStr = newOperationStr.str();
        newOperationStr.str("");
        newOperationStr << oldOperationStr.substr(0, oldOperationStr.find_first_of('('));
        newOperationStr << fixed << setprecision(2) << localResult;
        newOperationStr << oldOperationStr.substr(oldOperationStr.find_first_of(')') + 1);
    }

    // -----------------
    // Do square root (E)xponents
    //
    while (newOperationStr.str().find("sqrt") != string::npos)
    {
        int operatorLoc = newOperationStr.str().find_first_of("sqrt");
        start = operatorLoc;
        operatorLoc += 3; // go to end of word

        //
        // Find end of expression (right-hand side)
        //
        end = operatorLoc + 1;
        rightStr = newOperationStr.str().substr(operatorLoc + 1);
        if (rightStr.size() > 1) {
            int nextOperation = rightStr.find_first_not_of("0123456789.~");
            if (nextOperation == string::npos) {
                end = operatorLoc + 1 + rightStr.size();
            } else {
                end = operatorLoc + 1 + nextOperation;
            }
            rightStr = rightStr.substr(0, rightStr.size() - end);
        }

        //
        // Calculate square root
        //
        localResult = sqrt(stod(rightStr));

        //
        // Substitute result back in
        //
        oldOperationStr = newOperationStr.str();
        newOperationStr.str("");
        newOperationStr << oldOperationStr.substr(0, start);
        newOperationStr << fixed << setprecision(2) << localResult;
        if (end < oldOperationStr.size()) {
            newOperationStr << oldOperationStr.substr(end);
        }
    }

    // -----------------
    // Do (E)xponents, (M)ultiplication, (D)ivision, (A)ddition, (S)ubtraction
    //
    vector<string> operators = {"^", "*/", "+-"};
    for (const string &operatorType : operators) {
        while (newOperationStr.str().find_first_of(operatorType) != string::npos) {

            //
            // Find first instance of operation
            //
            int operatorLoc = newOperationStr.str().find_first_of(operatorType);

            //
            // Find start of expression (left-hand side)
            //
            start = operatorLoc - 1;
            leftStr = newOperationStr.str().substr(0, operatorLoc);
            if (leftStr.size() > 1) {
                start = leftStr.find_last_not_of("0123456789.~") + 1;
                leftStr = leftStr.substr(start);
            }

            //
            // Find end of expression (right-hand side)
            //
            end = operatorLoc + 1;
            rightStr = newOperationStr.str().substr(operatorLoc + 1);
            if (rightStr.size() > 1) {
                int nextOperation = rightStr.find_first_not_of("0123456789.~");
                if (nextOperation == string::npos) {
                    // no next operator, right is rest of rightStr
                    end = operatorLoc + 1 + rightStr.size();
                }
                else {
                    // found next operator, set end to its index
                    end = operatorLoc + 1 + nextOperation;
                    rightStr = rightStr.substr(0, nextOperation);
                }
            }

            //
            // Account for negative numbers
            // denoted with tilde '~'
            //
            left = (leftStr.find('~') == string::npos) ? stod(leftStr) : -1 * stod(string(leftStr, leftStr.find('~') + 1));
            right = (rightStr.find('~') == string::npos) ? stod(rightStr) : -1 * stod(string(rightStr, leftStr.find('~') + 1));

            //
            // Extract and calculate single operation
            //
            localExpression = newOperationStr.str().substr(start, end);
            switch (newOperationStr.str().at(operatorLoc)) {
                case '^':
                    localResult = pow(left, right);
                    break;
                case '*':
                    localResult = left * right;
                    break;
                case '/':
                    localResult = left / right;
                    break;
                case '+':
                    localResult = left + right;
                    break;
                case '-':
                    localResult = left - right;
                    break;
                default:
                    localResult = 9871234;
                    break;
            }

            //
            // Substitute result back in
            //
            // if result is negative, use '~' instead of '-'
            //
            oldOperationStr = newOperationStr.str();
            newOperationStr.str("");
            newOperationStr << oldOperationStr.substr(0, start);
            if (localResult < 0) {
                newOperationStr << '~' << fixed << setprecision(2) << abs(localResult);
            } else {
                newOperationStr << fixed << setprecision(2) << localResult;
            }
            if (end < oldOperationStr.size()) {
                newOperationStr << oldOperationStr.substr(end);
            }
        }
    }

    // If result is negative must replace tilde '~'
    if (newOperationStr.str().find('~') != string::npos) {
        oldOperationStr = newOperationStr.str().substr(1);
        return -stod(oldOperationStr);
    } else {
        return stod(newOperationStr.str());
    }
}

bool isValidExpression(const string expression)
{
    // assume the worst
    bool result = false;

    string allowedChars = "0123456789.+-*/^sqrt ";
    if (expression.find_first_not_of(allowedChars) == string::npos) {
        if (expression.find("exit"))
        result = true;
    }

    return result;
}
