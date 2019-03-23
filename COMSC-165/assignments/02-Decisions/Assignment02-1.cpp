/***************************************************************
Problem: Decisions
Question: 01
Name: Peter Lopez
ID: 1611543
Date: 2/9/19
Status: complete
****************************************************************/

#include <iostream>
#include <ctype.h>
#include <cmath>
#include <string>
#include <sstream>
#include <iomanip>

using namespace std;

const double PKG_A_RATE = 9.95;
const double PKG_A_LIMIT = 10;
const double PKG_A_OVERAGE_RATE = 2.00;

const double PKG_B_RATE = 14.95;
const double PKG_B_LIMIT = 20;
const double PKG_B_OVERAGE_RATE = 1.00;

const double PKG_C_RATE = 19.95;
// package c is unlimited

/**
 *
 * @param rate - flat monthly rate
 * @param hoursUsed
 * @param hourLimit - number of hours until overageRate is used
 * @param overageRate - cost per hour if hoursUsed exceeds hourLimit
 * @return
 */
double calcBill(double rate, double hoursUsed, double hourLimit, double overageRate)
{
    double result = rate;

    // Calculate overage
    result += (hoursUsed > hourLimit) ? (hoursUsed - hourLimit) * overageRate : 0;

    return result;
}

int main()
{
    stringstream ss;
    string input;

    // Get package from user
    char package;
    cout << "Please select a package (A, B, or C) : ";
    getline(cin, input);
    ss << input;
    ss >> package;
    ss.clear();

    // Check user selected valid package
    switch(static_cast<int>(package))
    {
        case 'A':
        case 'B':
        case 'C':
            break;
        default:
            cout << "Not a valid package" << endl;
            exit(1);
    }

    // Get month from user
    string month;
    cout << "Please input a month (ex. January) : ";
    getline(cin, input);
    ss << input;
    ss >> month;
    ss.clear();

    // Get hours used from user
    int hoursUsed;
    cout << "Please input number of hours used : ";
    getline(cin, input);
    ss << input;
    ss >> hoursUsed;
    ss.clear();

    // Check hours used does not exceed hours in month
    int daysInMonth = 30 + (month.find_first_of("ycg") != string::npos) + -3*(month == "February");
    int hoursInMonth = daysInMonth * 24;
    if (hoursUsed > hoursInMonth) {
        cout << "Hours entered exceeds limit of hours in month" << endl;
        exit(1);
    }

    // Calculate bills for all packages
    double pkgAAmt = calcBill(PKG_A_RATE, hoursUsed, PKG_A_LIMIT, PKG_A_OVERAGE_RATE);
    double pkgBAmt = calcBill(PKG_B_RATE, hoursUsed, PKG_B_LIMIT, PKG_B_OVERAGE_RATE);
    double pkgCAmt = calcBill(PKG_C_RATE, hoursUsed, 1000, 0);

    // Display bill for selected package
    // along with savings for other packages
    switch(static_cast<int>(package))
    {
        case 'A':
            cout << "Your bill is $" << pkgAAmt << endl;
            if (pkgBAmt < pkgAAmt) {
                cout << "You would save $" << pkgAAmt - pkgBAmt << " by switching to Package B" << endl;
            }
            if (pkgCAmt < pkgAAmt) {
                cout << "You would save $" << pkgAAmt - pkgCAmt << " by switching to Package C" << endl;
            }
            break;
        case 'B':
            cout << "Your bill is $" << pkgBAmt << endl;
            if (pkgAAmt < pkgBAmt) {
                cout << "You would save $" << pkgBAmt - pkgAAmt << " by switching to Package A" << endl;
            }
            if (pkgCAmt < pkgBAmt) {
                cout << "You would save $" << pkgBAmt - pkgCAmt << " by switching to Package C" << endl;
            }
            break;
        case 'C':
            cout << "Your bill is $" << pkgCAmt << endl;
            if (pkgBAmt < pkgCAmt) {
                cout << "You would save $" << pkgCAmt - pkgBAmt << " by switching to Package B" << endl;
            }
            if (pkgAAmt < pkgCAmt) {
                cout << "You would save $" << pkgCAmt - pkgAAmt << " by switching to Package A" << endl;
            }
            break;
        default:
            break;
    }

    return 0;
}
