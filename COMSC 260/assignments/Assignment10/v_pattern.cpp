#include <iostream>
using namespace std;

void draw_v_pattern(int);

int main()
{

	draw_v_pattern(13);

	system("PAUSE");
	return 0;

}


void draw_v_pattern(int rows)
{
	int cols = 2*rows-1, mid_col = cols/2, curr_row, curr_col;

	// this for loop loops over the N-1 top rows of the V pattern
	for (curr_row = 0; curr_row<(rows - 1); curr_row++) 
	{
		// this for loop prints all of the spaces before the first asterik for the row
		for (curr_col = 0; curr_col<curr_row; curr_col++)
			cout << " ";

		//print the first asterik for the row
		cout << "*";

		// this for loop prints all of the spaces in between the two asterisks for the row
		for (curr_col = curr_row + 1; curr_col<(cols - curr_row - 1); curr_col++)
			cout << " ";

		//print the second asterik for the row, followed by a line return
		cout << "*\n";
	}

	// This for loop prints all of the spaces before the asterik on the bottom (final) row
	for (curr_col = 0; curr_col<mid_col; curr_col++) 
		cout << " ";

	//print the final asterik, followed by a line return
	cout << "*\n";

	cout << endl << endl;
}