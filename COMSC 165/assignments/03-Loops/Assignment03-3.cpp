/***************************************************************
Problem: Loops
Question: 3 - Count uppercase letters
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
    userString = ss.str();
    ss.clear();

    int numUppercase = 0;
    for (int i = 0; i < userString.size(); i++) {
        numUppercase += isupper(userString[i]);
    }

    cout << "The number of uppercase letters is " << numUppercase << endl;

    return 0;
}
