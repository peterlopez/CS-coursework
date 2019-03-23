/***************************************************************
Problem: Functions
Question: 3 - Phone keypads
Name: Peter Lopez
ID: 1611543
Date: 2/13/19
Status: complete
****************************************************************/

#include <iostream>
#include <string>
#include <sstream>

using namespace std;

int getNumber(char uppercaseLetter)
{
    return uppercaseLetter - (uppercaseLetter - '2') +
        (uppercaseLetter > 'C') +
        (uppercaseLetter > 'F') +
        (uppercaseLetter > 'I') +
        (uppercaseLetter > 'L') +
        (uppercaseLetter > 'O') +
        (uppercaseLetter > 'S') +
        (uppercaseLetter > 'V');
}

int main()
{
    stringstream ss;
    string input;
    string userString;

    cout << "Enter a phone number as a string: ";

    getline(cin, input);
    ss << input;
    userString = ss.str();
    ss.clear();

    string translatedNumber;
    for (int i=0; i < userString.size(); i++)
    {
        char c = isalpha(userString[i]) ? getNumber(toupper(userString[i])) : userString[i];
        translatedNumber.push_back(c);
    }

    cout << translatedNumber << endl;

    return 0;
}
