/***************************************************************
Problem: Recursion
Question: 1 -  Occurrences of specified character in a string
Name: Peter Lopez Ruszel
ID: 1611543
Date: 4/23/19
Status: complete
****************************************************************/

#include <string>
#include <sstream>
#include <iostream>

using namespace std;

int count(const string& s, char a)
{
    static int pos = 0;

    if (pos >= s.size()) {
        return 0;
    }
    if (s.at(pos) == a) {
        pos++;
        return 1 + count(s, a);
    } else {
        pos++;
        return count(s, a);
    }
}

int main()
{
    string input, s;
    char a;
    cout << "Enter a string: ";
    getline(cin, input);
    stringstream sstream;
    sstream << input;
    s = sstream.str();

    cout << "Enter a character: ";
    getline(cin, input);
    stringstream(input) >> a;

    cout << endl << "Number of occurrences for '" << a << "' in '" << s << "' is: " << count(s, a) << endl;

    return 0;
}
