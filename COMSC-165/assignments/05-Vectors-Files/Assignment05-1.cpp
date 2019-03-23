/***************************************************************
Problem: Vectors and Files
Question: 1 - Theatre Seating
Name: Peter Lopez
ID: 1611543
Date: 3/16/19
Status: complete
****************************************************************/
#include <iostream>
#include <iomanip>
#include <sstream>
#include <fstream>
#include <vector>

using namespace std;

const string SETUP_FILENAME = "setup.txt";
const string SEATING_FILENAME = "seating.txt";


double getSalesTotal(double addTo = 0)
{
    static double total = 0;
    total += addTo;
    return total;
}


/**
 * Load settings file
 * see file contents for formatting
 */
vector<string> loadSetup()
{
    vector<string> settings = {"15", "30", "10.5", "", ""};

    ifstream setupFile(SETUP_FILENAME);
    if (setupFile)
    {
        string line;
        int lineNum = 0;
        while(getline(setupFile, line)) {
            if (line.empty()) {
                continue;
            }
            switch (line.at(0)) {
                case '#': // ignore
                case ' ': // ignore
                case '\n': // ignore
                    break;
                default:
                    settings.at(lineNum) = line;
                    lineNum++;
                    break;
            }
        }
    }
    else {
        // create setup file
        ofstream file(SETUP_FILENAME);
        stringstream out;
        bool first = true;
        for (string s : settings) {
            if (!first) {
                out << endl;
            }
            out << s;
            first = false;
        }
        file << out.str();
        file.close();
    }

    return settings;
}


/**
 * Following functions used for reading settings
 */
int getNumRows() {
    vector<string> settings = loadSetup();
    return stoi(settings.at(0));
}
int getNumSeats() {
    vector<string> settings = loadSetup();
    return stoi(settings.at(1));
}
double getPricePerSeat() {
    vector<string> settings = loadSetup();
    return stod(settings.at(2));
}
string getSpecialRowPrices() {
    vector<string> settings = loadSetup();
    return settings.at(3);
}
vector<string> getBlockedSeats() {
    vector<string> settings = loadSetup();

    vector<string> blockedSeats;
    if (!settings.at(4).empty()) {
        string blockedSeatsString = settings.at(4);
        stringstream ss(blockedSeatsString);
        istream_iterator<string> begin(ss);
        istream_iterator<string> end;
        vector<string> blockedSeats(begin, end);
    }

    return blockedSeats;
}


/**
 * Parse seat strings formatted like 'R4S2'
 */
int extractRowNum(string s) {
    int result = 0;
    if (!s.empty()) {
        result = stoi(s.substr(s.find('R') + 1, s.find('S') - 1));
    }
    return result;
}
int extractSeatNum(string s) {
    int result = 0;
    if (!s.empty()) {
        result = stoi(s.substr(s.find('S') + 1));
    }
    return result;
}


/**
 * Returns default price or special price
 * depending on row parameter
 */
double getSeatPrice(int forRow)
{
    double price = getPricePerSeat();

    // Convert string of special row prices to vector
    string specialRowPrices = getSpecialRowPrices();
    if (!specialRowPrices.empty()) {
        stringstream ss(specialRowPrices);
        istream_iterator<string> begin(ss);
        istream_iterator<string> end;
        vector<string> specialRows(begin, end);

        // Loop through rows with special pricing
        for (int i = 0; i < specialRows.size(); i++) {
            string s = specialRows.at(i);

            int row = stoi(s.substr(s.find('R') + 1, s.find('$') - 1));
            if (forRow == row) {
                price = stod(s.substr(s.find('$') + 1));
            }
        }
    }

    return price;
}


/**
 * Reads file with seating chart
 * then blocks off seats according to setup.txt file
 */
vector<char> getSeats()
{
    vector<char> seats(getNumRows() * getNumSeats(), '#');

    // Open file with seating chart
    ifstream file(SEATING_FILENAME);
    if (file)
    {
        string line;
        char c;
        int i = 0;
        while (getline(file, line)) {
            stringstream(line) >> c;
            seats.at(i) = c;
            i++;
        }
        file.close();
    }

    // Loop through blocked seats
    vector<string> blockedSeats = getBlockedSeats();
    for (string s : blockedSeats) {
        int row = extractRowNum(s);
        int seat = extractSeatNum(s);
        int seatIndex = ((row-1) * getNumSeats() + (seat-1));
        seats.at(seatIndex) = 'X';
    }

    return seats;
}


/**
 * Each seat is saved as a character
 * on its own line
 */
void saveSeats(vector<char> seats)
{
    ofstream file(SEATING_FILENAME);
    if (file) {
        bool first = true;
        for (char c : seats) {
            if (!first) {
                file.put('\n');
            }
            file.put(c);
            first = false;
        }
        file.close();
    }
    else {
        cout << endl << "Error saving seats!" << endl;
    }
}


/**
 * Display theatre seats
 *
 * Seats are stored in a single vector of chars:
 * '*' = taken
 * 'X' = blocked
 * '#' = available
 *
 * The seat index is determined by number of seats per row
 * for example:
 *      assume 10 seats per row
 *      seat 5 in row 4 would be 44th index in vector i.e. (4 * 10) + 4
 */
void displaySeatingChart()
{
    int numRows = getNumRows();
    int numSeats = getNumSeats();

    vector<char> seats = getSeats();

    // Display header with seat numbers
    cout << endl << setw(numSeats*2) << right << "Seats" << endl << setw(7) << " ";
    for (int i=0; i < numSeats; i++) {
        cout << setw(3) << left << i+1;
    }

    // Display seats
    for(int row = 0, seat = 0; seat < numRows*numSeats; seat++)
    {
        if (row == 0 || seat % numSeats == 0) {
            cout << endl << "Row " << setw(3) << left << row+1;
            row++;
        }
        cout << setw(3) << seats.at(seat);
    }

    cout << endl << endl;
}


void purchaseSeats()
{
    vector<char> seats = getSeats();

    bool flag;
    do {
        flag = false;
        cout << endl << "Enter row and seat number (ex. 'R7S4') : ";
        string input, seatDesc;
        getline(cin, input);
        stringstream(input) >> seatDesc;
        int purchaseRow = extractRowNum(seatDesc);
        int purchaseSeat = extractSeatNum(seatDesc);
        int purchaseSeatIndex = ((purchaseRow-1) * getNumSeats() + (purchaseSeat-1));

        // Check if blocked
        vector<string> blockedSeats = getBlockedSeats();
        for (string s : blockedSeats) {
            int blockedRow = extractRowNum(s);
            int blockedSeat = extractSeatNum(s);
            int blockedSeatIndex = ((blockedRow - 1) * getNumSeats() + (blockedSeat - 1));
            if (purchaseSeatIndex == blockedSeatIndex) {
                cout << "Error! Seat is blocked." << endl;
                flag = true;
            }
        }

        // Show price and confirm
        if (!flag) {
            double price = getSeatPrice(purchaseRow);
            cout << "Cost is: $" << price << endl;
            cout << "Confirm (y/n) : ";
            char confirm;
            getline(cin, input);
            stringstream(input) >> confirm;
            switch(toupper(confirm)) {
                case 'Y':
                    // Mark seat as purchased
                    seats.at(purchaseSeatIndex) = '*';
                    saveSeats(seats);

                    // Add price to sales totals
                    getSalesTotal(price);
                    break;
                case 'N':
                    cout << "Cancelling purchase" << endl;
                    break;
                default:
                    cout << "Invalid option." << endl;
            }
        }
    } while (flag); // do again if flag is raised (true)
}


/**
 * Create setup.txt file
 */
void setup()
{
    cout << "WARNING! New settings will override existing ones. Continue? (y/n) : ";
    string input;
    char choice;

    getline(cin, input);
    stringstream(input) >> choice;
    if (toupper(choice) == 'Y') {

        ofstream file(SETUP_FILENAME);
        stringstream out;

        cout << "Number of rows : ";
        getline(cin, input);
        out << stringstream(input).str() << endl;

        cout << "Number of seats per row : ";
        getline(cin, input);
        out << stringstream(input).str() << endl;

        cout << "Default seat price : ";
        getline(cin, input);
        out << stringstream(input).str() << endl;

        cout << "List special row prices : ";
        getline(cin, input);
        out << stringstream(input).str() << endl;

        cout << "List of blocked seats : ";
        getline(cin, input);
        out << stringstream(input).str();

        file << out.str();
        file.close();

        // save new seating chart
        vector<char> seats(getNumRows() * getNumSeats(), '#');
        saveSeats(seats);
    }
    else {
        cout << "Exiting setup." << endl;
    }
}


/**
 * Display main menu
 */
void mainMenu()
{
    const int CODE_PURCHASE = 1;
    const int CODE_TOTALS = 2;
    const int CODE_CLEAR = 3;
    const int CODE_SETUP = 4;
    const int CODE_EXIT = 5;

    int choice = 0;

    while (choice != CODE_EXIT)
    {
        displaySeatingChart();

        cout << "Main menu" << endl;
        cout << "---------------------------" << endl;
        cout << "(" << CODE_PURCHASE << ") Purchase Tickets" << endl;
        cout << "(" << CODE_TOTALS << ") View Sales Totals" << endl;
        cout << "(" << CODE_CLEAR << ") Clear All Sales" << endl;
        cout << "(" << CODE_SETUP << ") Setup" << endl;
        cout << "(" << CODE_EXIT << ") Exit" << endl;
        cout << endl << "Make a selection : ";

        string input;
        getline(cin, input);
        stringstream(input) >> choice;

        switch (choice)
        {
            case CODE_PURCHASE: {
                purchaseSeats();
                break;
            }
            case CODE_TOTALS: {
                cout << "Total sales: $" << getSalesTotal() << endl;
                break;
            }
            case CODE_CLEAR: {
                vector<char> seats(getNumRows() * getNumSeats(), '#');
                saveSeats(seats);
                cout << "Done." << endl;
                break;
            }
            case CODE_SETUP: {
                setup();
                break;
            }
            case CODE_EXIT: {
                cout << "Exiting" << endl;
                break;
            }
            default: {
                cout << "Invalid option. Try again" << endl << endl;
            }
        }
    }
}

int main()
{
    // Initialize seating.txt file with
    // blocked seats from setup.txt file
    vector<char> seats = getSeats();
    saveSeats(seats);

    mainMenu();

    return 0;
}
