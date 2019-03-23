/***************************************************************
Problem#: Database Lab
Question:
    - Create indexes
    - Create formatted database file
Name: Peter Lopez
ID: 1611543
Date: 3/21/19
Status: complete
****************************************************************/
#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <sstream>

using namespace std;

const string DB_FILENAME = "main.db";
const int DEFAULT_FIELDSIZE = 30;


struct Index {
	string value;
	vector<int> locations; // row # in db
};


string trim(const string &str, const char &c)
{
    string result = "";

    size_t first = str.find_first_not_of(c);
    if (string::npos == first) {
        result = str;
    }
    size_t last = str.find_last_not_of(c);
    result = str.substr(first, (last - first + 1));

    return result;
}


/**
 * Compares {value} to Index.value in {list}
 *
 * @return position where value should be inserted into list
 */
int bin_search(const string &value, vector<Index> &list)
{
    int result = 0;

    int low = 0;
    int high = list.size() - 1;
    int middle = (low + high) / 2;

    while (low <= high) {
        if (value > list.at(middle).value) {
            low = middle + 1;
        }
        else if (value == list.at(middle).value) {
            result = middle;
            break;
        }
        else {
            high = middle - 1;
        }
        middle = (low + high) / 2;

        // low > high if item belongs at end of list
        result = (low > high) ? low : middle;
    }
    return result;
}


void createDatabase(const string &fileName, const int &fieldSize)
{
	ifstream inFile(fileName);
	bool error = false;

	if (!inFile) {
	    error = true;
        cout << "Error creating database. Could not read file '" << fileName << "'." << endl << endl;
    }

	if (!error) {
        ofstream outFile(DB_FILENAME);
        string line, field;
        while (getline(inFile, line)) {
            field = "";
            for (char c : line) {
                if (c == ',') {
                    field.resize(fieldSize, ' ');
                    outFile << field;
                    field = "";
                } else {
                    field += c;
                }
            }
            outFile.put('\n');
        }

        inFile.close();
        outFile.close();
        cout << "Database created." << endl << endl;
    }
}


void createIndexes(const string &fieldName, const int &fieldSize)
{
	ifstream dbFile(DB_FILENAME);
	bool error = false;

	if (!dbFile) {
	    error = true;
        cout << "No database file found. Create one first." << endl << endl;
	}

	// Check field exists and
	// get its position in dbFile
	int fieldStartPos;
	if (!error) {
        string line;
        getline(dbFile, line);
        fieldStartPos = line.find(fieldName);

        // Check given field name is complete, not partial match
        if (fieldStartPos >= 0) {
            string fullFieldName = trim(line.substr(fieldStartPos, fieldSize), ' ');
            if (fieldName != fullFieldName) {
                error = true;
                cout << "No field named '" << fieldName << "' exists." << endl << endl;
            }
        }
    }

	// Create index file
    if (!error) {
        ofstream idxFile(fieldName + ".idx");
        vector<Index> indexes;
        string line;

        int location = 1;   // record # i.e. row #
        int insertPos;      // location where new value will be inserted
        string value;       // value to be inserted
        while (getline(dbFile, line)) {
            value = line.substr(fieldStartPos, fieldSize);
            insertPos = bin_search(value, indexes);

            // add location for existing index
            if (insertPos < indexes.size() && value == indexes.at(insertPos).value) {
                indexes.at(insertPos).locations.push_back(location);
            }
            // add new index
            else {
                vector<int> l = {location};
                indexes.insert(indexes.begin() + insertPos, Index{value, l});
            }
            location++;
        }

        // Write indexes to file
        for (const Index &i : indexes) {
            idxFile << i.value;

            for (int l : i.locations) {
                idxFile << l << ",";
            }
            idxFile << endl;
        }

        dbFile.close();
        idxFile.close();
        cout << "Indexes for '" << fieldName << "' created." << endl << endl;
    }
}


void mainMenu()
{
	string input;
	int choice = 0;

    const int OPT_SEARCH = 1;
    const int OPT_CREATE_DB = 2;
    const int OPT_CREATE_IDX = 3;
	const int OPT_EXIT = 4;

	while (choice != OPT_EXIT) {
		cout << "Main menu" << endl;
        cout << "(" << OPT_SEARCH << ") Search database" << endl;
        cout << "(" << OPT_CREATE_DB << ") Create database" << endl;
        cout << "(" << OPT_CREATE_IDX << ") Create database indexes" << endl;
		cout << "(" << OPT_EXIT << ") Exit" << endl << endl;

		cout << "> ";
		getline(cin, input);
		stringstream(input) >> choice;

		switch (choice) {
            case OPT_CREATE_DB: {
                cout << "Enter name of source file : ";
                getline(cin, input);
                string fileName;
                stringstream(input) >> fileName;
                createDatabase(fileName, DEFAULT_FIELDSIZE);
                break;
            }
            case OPT_CREATE_IDX: {
                cout << "Enter name of field to index (no spaces) : ";
                getline(cin, input);
                string fieldName;
                stringstream(input) >> fieldName;
                createIndexes(fieldName, DEFAULT_FIELDSIZE);
                break;
            }
            case OPT_SEARCH: {
                cout << "To be done later" << endl << endl;
                break;
            }
            case OPT_EXIT:
                cout << "Exiting.";
                break;
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
