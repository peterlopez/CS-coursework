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

string getOperationLeft(const string &operationStr, const char &operatorType);

string getOperationRight(const string &operationStr, const char &operatorType);

/**
 *
 * Perform series of math operations
 * according to PEMDAS
 *
 * 1. Repeatedly search for the specific operation (paren, exp, mult, div, add, sub)
 * 2. Find first (leftmost) instance of specific operation
 * 3. Calculate result of specific operation
 * 4. Substitute result back into string containing all operations
 *
 * 12 - 5 + (13 + 1)
 * 12 - 5 + 14
 * 7 + 14
 * 21
 *
 */
double doOperation(const string &operationStr);

/**
 *
 * Performs simple math operations:
 * - add
 * - subtract
 * - multiply
 * - divide
 *
 * will look for the first (left-most) instance of operatorType
 * then split the given operationStr to get left and right halves
 *
 */
double doSimpleOperation(const string &operationStr, const char &operatorType);

/**
 * Print out list of history items
 * number of items according to HIST_SIZE
 */
void displayHistory(vector<string> hist);

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

    string operation = "";
    double result = 0;

    while (operation != CODE_EXIT)
    {
        system("clear");
        cout << "Welcome to Calculator" << endl << endl;
        displayHistory(hist);

        cout << "Please input operation: ";
        string input;
        getline(cin, input);
        stringstream(input) >> operation;

        if (operation == CODE_CLEAR) {
            clearHistory();
        }
        else if (operation.at(0) == 'H') {
            int histNum = stoi(string(operation.begin() + 1, operation.end()));
            if (histNum >= hist.size() || histNum < 0) {
                cout << "No history entry for '" << histNum << "'" << endl << endl;
            }
            else {
                result = doOperation(hist.at(histNum - 1));
                displayResult(hist.at(histNum - 1), result);
            }
        }
        else {
            result = doOperation(operation);
            displayResult(operation, result);
            hist.push_back(operation);
        }
    }

    saveHistory(hist);
    cout << endl << "Good Bye!" << endl << endl;
}

void displayResult(const string &operation, const double &result)
{
    cout << "Result: " << operation << " = " << fixed << setprecision(3) << result;
    cout << '\t' << "Hex: " << hex << static_cast<int>(result);
    cout << '\t' << "Oct: " << oct << static_cast<int>(result);
    cout << '\t' << "Bin: " << bitset<4>(result) << endl << endl;
}

void clearHistory()
{
    ofstream histFile;
    histFile.open(HIST_FILE, ios::out | ios::trunc);
    histFile.close();
}
void displayHistory(vector<string> hist)
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
    cout << endl << "-----------------" << endl;
    cout << "Doing operation on: " << operationStr << endl;

    // when each step is solved the answer
    // is substituted back into newOperationStr
    string newOperationStr = operationStr;

    // this stores a simple operation within each step
    // i.e. operation inside parenthesis
    string localExpression;
    double localResult;

    // Remove all spaces
    //

    // -----------------
    // (P)arenthesis
    while (newOperationStr.find('(') != string::npos && newOperationStr.find(')') != string::npos)
    {
        localExpression = string(operationStr, operationStr.find_first_of('(') + 1, operationStr.find_first_of(')') - operationStr.find_first_of('(') - 1);
        cout << "local expression: " << localExpression << endl;

        // Substitute back in
        localResult = doOperation(localExpression);
        newOperationStr = operationStr.substr(0, operationStr.find_first_of('(')) + to_string(localResult) + operationStr.substr(operationStr.find_first_of(')') + 1);
    }
    cout << "after parenthesis: " << newOperationStr << endl;

    // -----------------
    // (E)xponents


    // -----------------
    // (M)ultiplication


    // -----------------
    // (D)ivision


    // -----------------
    // (A)ddition
    while (newOperationStr.find('+') != string::npos)
    {
        string left = getOperationLeft(newOperationStr, '+');
        string right = getOperationRight(newOperationStr, '+');
        localResult = stod(left) + stod(right);

        // Substitute back in
        newOperationStr = operationStr.substr(0, operationStr.find_first_of('+')) + to_string(localResult) + operationStr.substr(operationStr.find_first_of(')') + 1);
    }

    // -----------------
    // (S)ubtraction
//    while (newOperationStr.find('-') != string::npos)
//    {
//
//    }

    return 0.00;
}

string getOperationLeft(const string &operationStr, const char &operatorType)
{
    string left = operationStr.substr(0, operationStr.find_first_of(operatorType));
    if (left.size() > 1) {
        left = left.substr(left.find_last_not_of("0123456789.") + 1);
    }
    return left;
}

string getOperationRight(const string &operationStr, const char &operatorType)
{
    string right = operationStr.substr(operationStr.find_first_of(operatorType) + 1);
    if (right.size() > 1) {
        // TODO
    }
    return right;
}

double doSimpleOperation(double left, double right, const char &operatorType)
{
    double result;

    switch(operatorType)
    {
        case '*':
            result = left * right;
            break;
        case '/':
            result = left / right;
            break;
        case '+':
            result = (double) left + (double) right;
            break;
        case '-':
            result = left - right;
            break;
        default:
            break;
    }

//    cout << "Doing " << operatorType << endl;
//    cout << "leftStr: " << leftStr << endl;
//    cout << "rightStr: " << rightStr << endl;
//    cout << "left: " << left << endl;
//    cout << "right: " << right << endl;
//    cout << "Result: " << result << endl;

    return result;
}
