/***************************************************************
Problem: Functions
Question: 2 - Swap case
Name: Peter Lopez
ID: 1611543
Date: 2/13/19
Status: complete
****************************************************************/

#include <iostream>
#include <string>
#include <sstream>

using namespace std;

/**
 *
 * @param s
 * @return
 */
string swapCase(const string& s)
{
    string result = "";
    for (int i = 0; i < s.size(); i++)
    {
        int isUpper = (s[i] >= 'A' && s[i] <= 'Z');
        int isLower = (s[i] >= 'a' && s[i] <= 'z');

        char otherCase = s[i];
        otherCase -= 32 * isLower;
        otherCase += 32 * isUpper;
        result.push_back(otherCase);
    }

    return result;
}

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

    cout << "The new string is: " << swapCase(userString) << endl;

    return 0;
}
