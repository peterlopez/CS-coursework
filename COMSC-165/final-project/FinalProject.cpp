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

    cout << doOperation("1.1+(1.2+2.2)-1.1+5.1");
    cout << endl;

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
//    cout << "-----------------" << endl;
//    cout << "Start" << endl << "parsing operation: '" << operationStr << '\'' << endl;

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

    // Remove all spaces
    // TODO

    // -----------------
    // (P)arenthesis
    while (newOperationStr.str().find('(') != string::npos && newOperationStr.str().find(')') != string::npos)
    {
        // Extract and calculate operation within parenthesis
        localExpression = string(newOperationStr.str(), newOperationStr.str().find_first_of('(') + 1, newOperationStr.str().find_first_of(')') - newOperationStr.str().find_first_of('(') - 1);
//        cout << "doing expression in parenthesis: '" << localExpression << '\'' << endl;
        localResult = doOperation(localExpression);

        // Substitute result back in
        oldOperationStr = newOperationStr.str();
        newOperationStr.str("");
        newOperationStr << oldOperationStr.substr(0, oldOperationStr.find_first_of('('));
        newOperationStr << fixed << setprecision(2) << localResult;
        newOperationStr << oldOperationStr.substr(oldOperationStr.find_first_of(')') + 1);
    }
//    cout << "parenthesis done, new string: '" << newOperationStr.str() << '\'' << endl;
//    cout << "-----------------" << endl;

    // -----------------
    // (E)xponents
    // -----------------
    // (M)ultiplication
    // -----------------
    // (D)ivision
    // -----------------
    // (A)ddition
    string operatorType = "+-";
    while (newOperationStr.str().find_first_of(operatorType) != string::npos)
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
        int operatorLoc = newOperationStr.str().find_first_of(operatorType);

        //
        // Find start operation (left-hand side)
        //
        start = operatorLoc - 1;
        leftStr = newOperationStr.str().substr(0, operatorLoc);
        if (leftStr.size() > 1) {
            start = leftStr.find_last_not_of("0123456789.") + 1;
            leftStr = leftStr.substr(start);
        }

        //
        // Find end of operation (right-hand side)
        //
        end = operatorLoc + 1;
//        cout << "end: " << end << endl;
        rightStr = newOperationStr.str().substr(operatorLoc + 1);
//        cout << "rightStr: " << rightStr << endl;
        if (rightStr.size() > 1) {
            int nextOperation = rightStr.find_first_not_of("0123456789.");
            if (nextOperation == string::npos) {
                end = operatorLoc + 1 + rightStr.size();
//                cout << "end: " << end << endl;
            } else {
                end = operatorLoc + 1 + nextOperation;
//                cout << "end: " << end << endl;
            }
//            cout << "nextOperation: " << nextOperation << endl;
            rightStr = rightStr.substr(0, rightStr.size() - end);
//            cout << "rightStr: " << rightStr << endl;
        }

        //
        // Extract and calculate single operation
        //
        localExpression = newOperationStr.str().substr(start, end);
        switch(newOperationStr.str().at(operatorLoc))
        {
            case '+':
                localResult = stod(leftStr) + stod(rightStr);
                break;
            case '-':
                localResult = stod(leftStr) - stod(rightStr);
                break;
            default:
                localResult = 9871234;
                break;
        }

//        cout << "----" << endl;
//        cout << "Calculating '" << localExpression << '\'' << endl;
//        cout << "left: " << leftStr << endl;
//        cout << "right: " << rightStr << endl;
//        cout << "result: " << localResult << endl;
//        cout << endl;
//        cout << "Substituting back into '" << newOperationStr.str() << "'" << endl;
//        cout << "start: " << start << endl;
//        cout << "end: " << end << endl;

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

//        cout << "after substitution: '" << newOperationStr.str() << '\'' << endl;
//        cout << "----" << endl;
//        string input;
//        getline(cin, input);
    }

//    cout << "Returning '" << stod(newOperationStr.str()) << "' from '" << operationStr << '\'' << endl;
//    cout << "----" << endl;

    return stod(newOperationStr.str());
}
