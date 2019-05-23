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

    doOperation("1.1+(1.2+2.2)-1.1+5.1");
    return;

    while (operation != CODE_EXIT)
    {
        system("clear");
        cout << "Welcome to Calculator" << endl << endl;
        displayRecentHistory(hist);

        cout << "Please input operation: ";
        string input;
        getline(cin, input);
        stringstream(input) >> operation;

        if (operation == CODE_CLEAR) {
            clearHistory();
        }
        else if (operation == CODE_HIST) {
            displayAllHistory(hist);
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
    cout << "-----------------" << endl;
    cout << "Start" << endl << "parsing operation: '" << operationStr << '\'' << endl;

    //
    // when each step is solved the answer
    // is substituted back into newOperationStr
    //
    // eventually, this is returned as a single number
    // after all substitutions have been made
    //
    string newOperationStr = operationStr;

    // this stores a single operation within each step
    // ex. operation inside parenthesis
    string localExpression;
    double localResult;

    // Remove all spaces
    // TODO

    // -----------------
    // (P)arenthesis
    while (newOperationStr.find('(') != string::npos && newOperationStr.find(')') != string::npos)
    {
        // Extract and calculate operation within parenthesis
        localExpression = string(newOperationStr, newOperationStr.find_first_of('(') + 1, newOperationStr.find_first_of(')') - newOperationStr.find_first_of('(') - 1);
        cout << "doing expression in parenthesis: '" << localExpression << '\'' << endl;

        // Substitute result back in
        localResult = doOperation(localExpression);
        newOperationStr = operationStr.substr(0, operationStr.find_first_of('(')) + to_string(localResult) + operationStr.substr(operationStr.find_first_of(')') + 1);
    }
    cout << "parenthesis done, new string: '" << newOperationStr << '\'' << endl;
    cout << "-----------------" << endl;

    // -----------------
    // (E)xponents
    // -----------------
    // (M)ultiplication
    // -----------------
    // (D)ivision
    // -----------------
    // (A)ddition
    string operatorType = "+-";
    while (newOperationStr.find_first_of(operatorType) != string::npos)
    {
        // Left and right sides of operation
        string leftStr, rightStr;
        double left, right, result = 0;

        // Indexes within newOperationStr
        // used to substitute in result
        int start, end;

        //
        // Find first instance of operation
        //
        int operatorLoc = newOperationStr.find_first_of(operatorType);

        //
        // Find start operation (left-hand side)
        //
        start = operatorLoc - 1;
        leftStr = newOperationStr.substr(0, operatorLoc);
        if (leftStr.size() > 1) {
            start = leftStr.find_last_not_of("0123456789.") + 1;
            leftStr = leftStr.substr(start);
        }

        //
        // Find end of operation (right-hand side)
        //
        end = operatorLoc + 1;
        rightStr = newOperationStr.substr(operatorLoc + 1);
        if (rightStr.size() > 1) {
            int nextOperation = rightStr.find_first_not_of("0123456789.");
            end = (nextOperation == string::npos) ? operatorLoc + rightStr.size() + 1 : nextOperation;
            rightStr = rightStr.substr(0, end);
        }

        //
        // Extract and calculate single operation
        //
        localExpression = newOperationStr.substr(start, end);
        switch(newOperationStr.at(operatorLoc))
        {
            case '+':
                localResult = stod(leftStr) + stod(rightStr);
                break;
            case '-':
                localResult = stod(leftStr) - stod(rightStr);
                break;
            default:
                break;
        }

        cout << "Calculating '" << localExpression << '\'' << endl;
        cout << "left: " << leftStr << endl;
        cout << "right: " << rightStr << endl;
        cout << "result: " << localResult << endl;

        cout << "----" << endl;
        cout << "Substituting back into '" << newOperationStr << "'" << endl;
        cout << "start: " << start << endl;
        cout << "end: " << end << endl;

        //
        // Substitute result back in
        //
        newOperationStr = newOperationStr.substr(0, start) + to_string(localResult) + newOperationStr.substr(end);

        cout << "after substitution: '" << newOperationStr << '\'' << endl;
        cout << "----" << endl;

        string input;
        getline(cin, input);
    }

    cout << "Returning '" << stod(newOperationStr) << "' from '" << operationStr << '\'' << endl;
    cout << "----" << endl;

    return stod(newOperationStr);
}

double doSimpleOperation(const string &operationStr, const char &operatorType)
{
    double left, right, result = 0;
    string leftStr, rightStr;

    leftStr = operationStr.substr(0, operationStr.find_first_of(operatorType));
    if (leftStr.size() > 1) {
        leftStr = leftStr.substr(leftStr.find_last_not_of("0123456789.") + 1);
    }
    rightStr = operationStr.substr(operationStr.find_first_of('+') + 1);
    if (rightStr.size() > 1) {
        // TODO
    }

    left = stod(leftStr);
    right = stod(rightStr);


//    cout << "Doing " << operatorType << endl;
//    cout << "leftStr: " << leftStr << endl;
//    cout << "rightStr: " << rightStr << endl;
//    cout << "left: " << left << endl;
//    cout << "right: " << right << endl;
//    cout << "Result: " << result << endl;

    return result;
}
