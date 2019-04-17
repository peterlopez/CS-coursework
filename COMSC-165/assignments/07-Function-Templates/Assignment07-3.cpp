/***************************************************************
Problem: Function Templates
Question: 3 - Total Template
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
vector<T> total(vector<T> values)
{
    T total = 0;
    T min = values.at(0);
    T max = values.at(0);

    for(int i=0; i < values.size(); i++) {
        total += values.at(i);

        max = (values.at(i) > max) ? values.at(i) : max;
        min = (values.at(i) < min) ? values.at(i) : min;
    }


    vector<T> result = {total, min, max};
    return result;
}


int main()
{
    vector<int> intValues = {1, 2, 3};
    vector<int> intTotals = total(intValues);
    cout << "Total of 1, 2, and 3 is: " << intTotals.at(0) << endl;
    cout << "Min of 1, 2, and 3 is: " << intTotals.at(1) << endl;
    cout << "Max of 1, 2, and 3 is: " << intTotals.at(2) << endl;
    cout << "--" << endl;

    vector<double> dblValues = {3.4, 2.2, 1.4};
    vector<double> dblTotals = total(dblValues);
    cout << "Total of 3.4, 2.2, and 1.4 is: " << dblTotals.at(0) << endl;
    cout << "Min of 3.4, 2.2, and 1.4 is: " << dblTotals.at(1) << endl;
    cout << "Max of 3.4, 2.2, and 1.4 is: " << dblTotals.at(2) << endl;

    return 0;
}
