/***************************************************************
Problem: Characters, Strings & output formatting
Question: 01
Name: Peter Lopez
ID: 1611543
Date: 1/30/19
Status: complete
****************************************************************/

#include <iostream>
#include <ctype.h>
#include <cmath>
#include <string>
#include <sstream>
#include <iomanip>

using namespace std;

/**
 * @param letter - any letter of the alphabet
 * @param number - any positive integer
 * @param symbol - any symbol character in the alphabet
 * @return true if user input is valid
 */
bool checkUserInput(char letter, unsigned int number, char symbol)
{
    if (!letter || !number || !symbol) {
        return false;
    }
    if (isblank(letter) || isblank(symbol)) {
        return false;
    }
    return true;
}

/**
 * @return number of digits in given integer
 */
int getIntLength(unsigned int i)
{
    int result = 0;

    result += static_cast<int>(log10(i)) + 1;

    return result;
}

void outputCharacterFormatting(string name, char letter, unsigned int number, char symbol)
{
    if (name == "" || checkUserInput(letter, number, symbol) == false) {
        return;
    }

    int colWidth = 14;
    cout << setfill(' ');

    cout << name << " - Character Formatting" << endl << endl;
    cout << left << setw(colWidth) << "";
    cout << right << setw(colWidth - 1) << "letter = " << letter;
    cout << setw(colWidth - getIntLength(number)) << "number = " << number;
    cout << setw(colWidth - 1) << "symbol = " << symbol << endl;
    cout << "---------------------------------------------------------" << endl;

    cout << left << setw(colWidth) << "Decimal";
    cout << right << setw(colWidth) << static_cast<int>(letter);
    cout << setw(colWidth) << number;
    cout << setw(colWidth) << static_cast<int>(symbol) << endl;

    cout << left << setw(colWidth) << "Hex";
    cout << right << setw(colWidth) << showbase << hex << static_cast<int>(letter);
    cout << setw(colWidth) << showbase << hex << number;
    cout << setw(colWidth) << showbase << hex << static_cast<int>(symbol) << endl;

    cout << left << setw(colWidth) << "Octal";
    cout << right << setw(colWidth) << oct << static_cast<int>(letter);
    cout << setw(colWidth) << oct << number;
    cout << setw(colWidth) << oct << static_cast<int>(symbol) << endl;

    cout << left << setw(colWidth) << "Binary";
    cout << right << setw(colWidth) << bitset<8>(static_cast<int>(letter));
    cout << setw(colWidth) << bitset<8>(number);
    cout << setw(colWidth) << bitset<8>(static_cast<int>(symbol)) << endl;
}

int main()
{
    string input, name;

    while (name.empty()) {
        cout << "What is your name? : ";
        getline(cin, input);
        stringstream ss(input);
        ss >> name;
        ss.clear();
    }

    cout << endl << "Hello " << name << ", ";
    char letter, symbol;
    unsigned int number;
    bool userInputIsValid = false;
    while (userInputIsValid == false)
    {
        cout << "please input a letter, number, and a symbol\nseparated by a space : ";
        getline(cin, input);
        stringstream ss(input);
        ss >> letter >> number >> symbol;
        ss.clear();

        userInputIsValid = checkUserInput(letter, number, symbol);
    }

    cout << endl << endl;

    outputCharacterFormatting(name, letter, number, symbol);

    cout << endl << "Output succeeded" << endl;
    return 0;
}
