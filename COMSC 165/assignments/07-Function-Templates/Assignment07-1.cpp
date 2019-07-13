/***************************************************************
Problem: Function Templates
Question: 1 - Minimum/Maximum Templates
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
T maxValue(T value1, T value2)
{
    return (value1 > value2) ? value1 : value2;
}
template<typename T>
T minValue(T value1, T value2)
{
    return (value1 < value2) ? value1 : value2;
}


int main()
{
    cout << "Max between 1 and 5 is: " << maxValue(1, 5) << endl;
    cout << "Max between 5.1 and 7.2 is: " << maxValue(5.1, 7.2) << endl;
    cout << "Max between 'abc' and 'def' is: " << maxValue("abc", "def") << endl;
    cout << "--" << endl;
    cout << "Min between 1 and 5 is: " << minValue(1, 5) << endl;
    cout << "Min between 5.1 and 7.2 is: " << minValue(5.1, 7.2) << endl;
    cout << "Min between 'abc' and 'def' is: " << minValue("abc", "def") << endl;

    return 0;
}
