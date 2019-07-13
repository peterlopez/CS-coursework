/***************************************************************
Problem#: File compression
Question: 
Name: Peter Lopez
ID: 1611543
Date: 3/12/19
Status: complete
****************************************************************/
#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <sstream>

using namespace std;

void compressFile(string filename)
{
	string line;
	int numChars = 1;
	fstream inFile(filename, ios::in);
	fstream outFile(filename + ".rle", ios::out);

	while (getline(inFile, line))
	{
		for (int i = 0; i < line.size(); i++)
		{
			// Check next char matches current char
			if ((i+1) != line.size() && line.at(i) == line.at(i+1)) {
				numChars++;
			}
			// Write 1) numDigits 2) numChars and 3) char
			else {
				outFile.put('0' + ((int)log10((double) numChars) + 1));
				outFile << numChars;
				outFile.put(line.at(i));
				numChars = 1; // reset
			}
		}
		outFile.put('\n');
	}

	inFile.close();
	outFile.close();
}


void decompressFile(string filename)
{
	string line;
	int numDigits, numChars;
	fstream inFile(filename, ios::in);
	fstream outFile(filename.substr(0, filename.find(".rle")), ios::out);

	while (getline(inFile, line))
	{
		for (int i = 0; i < line.size(); i += 1 + numDigits + 1)
		{
			numDigits = line.at(i) - '0';
			numChars = stoi(line.substr(i+1, numDigits));
			outFile << string(numChars, line.at(i + numDigits + 1));
		}
		outFile.put('\n');
	}

	inFile.close();
	outFile.close();
}

void mainMenu()
{
	string input, filename;
	char choice = '0';

	while (choice != '3') {
		cout << "Main menu" << endl;
		cout << "(1) Compress file" << endl;
		cout << "(2) De-compress file" << endl;
		cout << "(3) Exit" << endl << endl;

		getline(cin, input);
		stringstream(input) >> choice;

		switch (choice) {
		case '1':
			cout << "Enter name of file to compress : ";
			getline(cin, input);
			stringstream(input) >> filename;
			compressFile(filename);
			cout << "Done." << endl << endl;
			break;
		case '2':
			cout << "Enter name of file to de-compress : ";
			getline(cin, input);
			stringstream(input) >> filename;
			decompressFile(filename);
			cout << "Done." << endl << endl;
			break;
		case '3':
			cout << "Exiting" << endl;
		default:
			cout << "Invalid option." << endl << endl;
		}
	}
}

int main()
{
	mainMenu();

	return 0;
}
