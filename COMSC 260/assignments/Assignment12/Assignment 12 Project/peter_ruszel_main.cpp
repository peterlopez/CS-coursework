// main.cpp - Testing the LongestIncreasingSequence subroutine.
#include <iostream>
#include "peter_ruszel_longest.h"

using namespace std;

int main()
{
	const unsigned ARRAY_SIZE = 7;
	long array[] = { 3,3,3,3,3,3,3 };
	long result;
	result = LongestIncreasingSequence(array, ARRAY_SIZE);
	cout << "The longest increasing sequence is " << result << endl;
	
	system("PAUSE");
	return 0;
}
