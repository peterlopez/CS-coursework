// Programmer: Peter Ruszel
// Programmer's ID: 1611543
#include <iostream>
#include <vector>
#include <string>

using namespace std;

class HashTable {
    vector<vector<int>> buckets{
        {}, //0
        {}, //1
        {}, //2
        {}, //3
        {}, //4
        {}, //5
        {}, //6
        {}, //7
        {}, //8
        {}, //9
    };
public:
    void display();
    void insert(int);
    int search(int);
    int remove(int);
    int getBucketNum(int);
};

int main()
{
    cout << "Programmer: Peter Ruszel\n";
    cout << "Programmer's ID: 1611543\n";
    cout << "File: " << __FILE__ << endl << endl;

    HashTable ht;
    int target; // used for new item, search key, or key to remove

    string buf;
    while(true)
    {
        cout << "Hash Table functions:" << endl;
        cout << "\ta. Display" << endl;
        cout << "\tb. Insert" << endl;
        cout << "\tc. Search" << endl;
        cout << "\td. Remove" << endl;
        cout << "\tq. Quit" << endl;
        cout << "> ";
        cin >> buf;
        cout << endl;
        
        if (tolower(buf[0]) == 'q') {
            break;
        }
        // Display
        else if (tolower(buf[0]) == 'a') {
            ht.display();
        }
        // Insert
        else if (tolower(buf[0]) == 'b')
        {
            cout << "Enter integer value: ";
            cin >> buf;
            target = atoi(buf.c_str());
            ht.insert(target);
            cout << target << " added." << endl;
        }
        // Search
        else if (tolower(buf[0]) == 'c')
        {
            cout << "Enter integer to search for: ";
            cin >> buf;
            target = atoi(buf.c_str());
            int result = ht.search(target);
            if (result == -1) {
                cout << target << " not found in Hash Table" << endl;
            } else {
                cout << "Found item in Hash Table" << endl;
            }
        }
        // Remove
        else if (tolower(buf[0]) == 'd') {
            cout << "Enter integer to remove: ";
            cin >> buf;
            target = atoi(buf.c_str());
            int result = ht.remove(target);
            if (result == -1) {
                cout << target << " not found in Hash Table" << endl;
            } else {
                cout << "Item removed from Hash Table" << endl;
            }
        }
        else {
            cout << "Invalid input. Please try again.";
        }

        cout << endl << "--------------------------" << endl << endl;
    }

    return 0;
}

void HashTable::display()
{
    cout << "Hash Table buckets:" << endl;
    for (int i=0; i < buckets.size(); i++)
    {
        cout << i << ": ";
        for (int j=0; j < buckets.at(i).size(); j++) {
            cout << buckets.at(i).at(j);
            if (j + 1 < buckets.at(i).size()) {
                cout << ", ";
            }
        }
        if (buckets.at(i).empty()) {
            cout << "empty";
        }
        cout << endl << "--" << endl;
    }
}

void HashTable::insert(int item)
{
    int bucket = getBucketNum(item);

    buckets.at(bucket).push_back(item);
}

/**
 * @return item or -1 if not found
 */
int HashTable::search(int item)
{
    int bucket = getBucketNum(item);
 
    vector<int>& list = buckets.at(bucket);
    for (int i=0; i < list.size(); i++)
    {
        if (list.at(i) == item) {
            return item;
        }
    }

    return -1;
}

/**
 * @return item or -1 if not found
 */
int HashTable::remove(int item)
{
    int bucket = getBucketNum(item);
    vector<int>& list = buckets.at(bucket);

    for (int i=0; i < list.size(); i++)
    {
        if (list.at(i) == item) {
            list.erase(list.begin() + i);
            return item;
        }
    }

    return -1;
}

/**
 * To find item's bucket,
 * repeatedly apply modulo until less than 10
 */
int HashTable::getBucketNum(int item)
{
    int result = item;
    while (result > 10) {
        result = result % 10;
    }
    return result;
}
