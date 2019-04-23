//
// Recursion Lab 2
//
#include <iostream>
#include <string>
#include <sstream>
using namespace std;

void reverseDisplay(int i)
{
	if (i < 10) {
		cout << i;
	}
	else {
		cout << i % 10;
		reverseDisplay(i / 10);
	}
}

int main()
{
	// Lab 2
	cout << "Enter an integer: ";
	string input;
	int i;
	getline(cin, input);
	stringstream(input) >> i;
	reverseDisplay(i);
}
