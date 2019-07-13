//
// Recursion Lab 1
//
#include <iostream>
#include <string>
#include <sstream>
using namespace std;

float m(float i)
{
	if (i == 1) {
		return 1.0 / 3.0;
	}
	else {
		return (i / ((2.0 * i) + 1.0)) + m(i - 1);
	}
}

int main()
{
	cout << "m(1) = " << m(1) << endl;
	cout << "m(2) = " << m(2) << endl;
	cout << "m(3) = " << m(3) << endl;
	cout << "m(4) = " << m(4) << endl;
	cout << "m(5) = " << m(5) << endl;
	cout << "m(6) = " << m(6) << endl;
	cout << "m(7) = " << m(7) << endl;
	cout << "m(8) = " << m(8) << endl;
	cout << "m(9) = " << m(9) << endl;
	cout << "m(10) = " << m(10) << endl;
}
