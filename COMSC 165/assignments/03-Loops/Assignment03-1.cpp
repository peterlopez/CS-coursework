/***************************************************************
Problem: Loops
Question: 1 - Display pyramid
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
    int numLines;

    cout << "Enter the number of lines: ";

    getline(cin, input);
    ss << input;
    ss >> numLines;
    ss.clear();

    // numbers in row counting down to 1 ex. 3  2  1
    string line;
    // number of spaces before numbers start
    int padding;

    for (int i = 0; i < numLines; i++)
    {
        // Calculate padding
        padding = (numLines - (i+1)) * 3;

        line = "";
        for (int j = i+1; j > 0; j--)
        {
            line += to_string(j) + "  ";
        }
        line = line.substr(0, line.size() - 2); // cut off trailing spaces

        cout << string(padding, ' ');
        cout << line;

        reverse(line.begin(), line.end());
        cout << string(line.begin() + 1, line.end()) << endl;
    }

    return 0;
}
