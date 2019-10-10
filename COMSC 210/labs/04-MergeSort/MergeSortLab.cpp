// Programmer: Peter Ruszel
// Programmer's ID: 1611543
#include <iostream>
#include <vector>
#include <string>

using namespace std;

const vector<string> names{
    "Doug",
    "Brett",
    "Luke",
    "Eugenio",
    "Deadra",
    "Elle",
    "Kerri",
    "Idell",
    "Rima",
    "Lindsay",
    "Rupert",
    "Kyung",
    "Parthenia",
    "Peter",
    "Enola",
    "Wesley",
    "Rebbecca",
    "Stephani",
    "Theodora",
    "Roma",
};

vector<string> mergeSort(vector<string>);
vector<string> mergeSortCombine(vector<string>, vector<string>);

int main()
{
    cout << "Programmer: Peter Ruszel\n";
    cout << "Programmer's ID: 1611543\n";
    cout << "File: " << __FILE__ << endl << endl;

    // Display list of UNSORTED names
    cout << "UNSORTED list of names:" << endl;
    for(int i=0; i < names.size(); i++) {
        cout << "\t" << names.at(i) << endl;
    }
    cout << endl;

    cout << "Press [Enter] to sort names...";
    string empty;
    getline(cin, empty);
    cout << endl;

    vector<string> sortedNames = mergeSort(names);

    // Display list of SORTED names
    cout << "SORTED list of names:" << endl;
    for(int i=0; i < sortedNames.size(); i++) {
        cout << "\t" << sortedNames.at(i) << endl;
    }

    return 0;
}

vector<string> mergeSort(vector<string> listToSort)
{
    int size = listToSort.size();

    // base case, lists of 0 or 1 element are already sorted
    if (size < 2) {
        return listToSort;
    }

    // find middle point of list
    int middle = size / 2;

    // divide list into two lists
    vector<string> listA(listToSort.begin(), listToSort.begin() + middle);
    vector<string> listB(listToSort.begin() + middle, listToSort.end());

    // recursively divide each half until base case reached
    listA = mergeSort(listA);
    listB = mergeSort(listB);

    return mergeSortCombine(listA, listB);
};

vector<string> mergeSortCombine(vector<string> listA, vector<string> listB)
{
    vector<string> mergedList;

    // Add smaller item from either listA or listB
    // into mergedList, and remove from listA or listB
    while (listA.size() > 0 && listB.size() > 0)
    {
        if (listA.at(0) > listB.at(0)) {
            mergedList.push_back(listB.at(0));
            listB.erase(listB.begin());
        }
        else {
            mergedList.push_back(listA.at(0));
            listA.erase(listA.begin());
        }
    }

    // Add remaining elements from first list
    while (listA.size() > 0) {
        mergedList.push_back(listA.at(0));
        listA.erase(listA.begin());
    }

    // Add remaining elements from second list
    while (listB.size() > 0) {
        mergedList.push_back(listB.at(0));
        listB.erase(listB.begin());
    }

    return mergedList;
}
