/***************************************************************
Problem: Functions
Question: 2 - Palindrome integer
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
 * @param num
 * @return
 */
int flip(int num) {
    string reverseNum = to_string(num);
    reverse(reverseNum.begin(), reverseNum.end());

    return stoi(reverseNum);
}

/**
 *
 * @param num
 * @return
 */
bool isPalindrome(int num) {
    return (num == flip(num));
}

int main()
{
    stringstream ss;
    string input;
    int num;

    cout << "Enter an integer: ";

    getline(cin, input);
    ss << input;
    ss >> num;
    ss.clear();

    string wordNot = !isPalindrome(num) ? "not " : "";

    cout << num << " is " << wordNot << "a palindrome" << endl;

    return 0;
}
