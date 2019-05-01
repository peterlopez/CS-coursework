/***************************************************************
Problem: Recursion
Question: 2 -  Print the characters in a string reversely
Name: Peter Lopez Ruszel
ID: 1611543
Date: 4/23/19
Status: complete
****************************************************************/

#include <string>
#include <sstream>
#include <iostream>

using namespace std;

void reverseDisplay(const string& s)
{
    static int pos = s.size() - 1;

    if (pos < 0) {
        return;
    }
    cout << s.at(pos);
    pos--;
    reverseDisplay(s);
}

int main()
{
    string input, s;
    cout << "Enter a string: ";
    getline(cin, input);
    stringstream(input) >> s;

    reverseDisplay(s);

    return 0;
}
