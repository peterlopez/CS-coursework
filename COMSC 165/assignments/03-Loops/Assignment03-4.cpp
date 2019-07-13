/***************************************************************
Problem: Loops
Question: 4 - Checking Palindromes
Name: Peter Lopez
ID: 1611543
Date: 2/13/19
Status: complete
****************************************************************/

#include <iostream>
#include <string>
#include <sstream>

using namespace std;

int main()
{
    stringstream ss;
    string input;
    string userString;

    cout << "Enter a string: ";

    getline(cin, input);
    ss << input;
    ss >> userString;
    ss.clear();

    string reverseUserString = userString;
    reverse(reverseUserString.begin(), reverseUserString.end());

    string wordNot = (reverseUserString != userString) ? "not " : "";

    cout << userString << " is " << wordNot << "a palindrome" << endl;

    return 0;
}
