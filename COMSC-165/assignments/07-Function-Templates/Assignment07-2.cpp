/***************************************************************
Problem: Function Templates
Question: 2 - Absolute Value Template
Name: Peter Lopez Ruszel
ID: 1611543
Date: 4/9/19
Status: complete
****************************************************************/

#include <iostream>
#include <string>
#include <vector>

using namespace std;


template<typename T>
T absValue(T value)
{
    return (value < 0) ? -value : value;
}


int main()
{
    cout << "Absolute value of 1 is: " << absValue(1) << endl;
    cout << "Absolute value of -4 is: " << absValue(-4) << endl;
    cout << "Absolute value of 25.11 is: " << absValue(25.11) << endl;
    cout << "Absolute value of -65.3 is: " << absValue(-65.3) << endl;

    return 0;
}
