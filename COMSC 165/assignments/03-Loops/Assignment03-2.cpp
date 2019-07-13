/***************************************************************
Problem: Loops
Question: 2 - Random Number Guessing Game
Name: Peter Lopez
ID: 1611543
Date: 2/13/19
Status: complete
****************************************************************/

#include <iostream>
#include <string>
#include <sstream>
#include <random>

using namespace std;

int main()
{
    // Random number will be between these
    const int RAND_LOW = 1;
    const int RAND_HIGH = 100;

    // Generate random number
    random_device rd;
    default_random_engine generator(rd());
    uniform_int_distribution<int> distribution(RAND_LOW, RAND_HIGH);
    int rand = distribution(generator);

    stringstream ss;
    string input;
    int guess;
    int numGuesses = 0;
    string typeOfMiss; // high or low

    cout << "Guess a number between " << RAND_LOW << " and " << RAND_HIGH << endl;
    do {
        getline(cin, input);
        ss << input;
        ss >> guess;
        ss.clear();

        numGuesses++;

        if (guess > rand || guess < rand) {
            typeOfMiss = (guess > rand) ? "high" : "low";
            cout << "Too " << typeOfMiss << ", try again." << endl;
        }
    } while (guess != rand);

    string wordGuess = (numGuesses > 1) ? "guesses" : "guess";
    cout << "Congratulations you took " << numGuesses << " " << wordGuess << endl;

    return 0;
}
