// Programmer: Peter Ruszel
// Programmer's ID: 1611543
#include <iostream>
#include <array>
#include <string>

using namespace std;

class Musicians
{
protected:
    string strings[5];
    string winds[5];
    string percussion[5];
public:
	void stringf(); // string() caused namespace collision with std::string
	void wind();
	void perc();
};

class TypeIns : public Musicians
{
public:
	void get();
	void show(string);
};

int main()
{
	cout << "Programmer: Peter Ruszel\n";
	cout << "Programmer's ID: 1611543\n";
	cout << "File: " << __FILE__ << endl << endl;

    TypeIns t;
    t.get();

	return 0;
}

void Musicians::stringf() {
    // Initialize strings
    strings[0] = "veena";
    strings[1] = "guitar";
    strings[2] = "sitar";
    strings[3] = "sarod";
    strings[4] = "mandolin";
    
    // Display strings
    for(int i=0; i < strings->size() - 1; i++) {
        cout << strings[i] << ", ";
    }
	cout << "and " <<  strings[strings->size()-1] << endl;
}
void Musicians::wind() {
    // Initialize winds
    winds[0] = "flute";
    winds[1] = "clarinet";
    winds[2] = "saxophone";
    winds[3] = "nadhaswaram";
    winds[4] = "piccolo";
    
    // Display winds
    for(int i=0; i < winds->size() - 1; i++) {
		cout << winds[i] << ", ";
    }
	cout << "and " <<  winds[winds->size() - 1] << endl;
}
void Musicians::perc() {
    // Initialize percussion
    percussion[0] = "tabla";
    percussion[1] = "mridangam";
    percussion[2] = "bangos";
    percussion[3] = "drums";
    percussion[4] = "tambour";
    
    //Display percussion
    for(int i=0; i < percussion->size() - 1; i++) {
        cout << percussion[i] << ", ";
    }
    cout << "and " << percussion[percussion->size() - 1] << endl;
}

void TypeIns::get() {
    string buf;
    while(true)
    {
        cout << "Type of instruments to be displayed" << endl;
        cout << "\ta. String instruments" << endl;
        cout << "\tb. Wind instruments" << endl;
        cout << "\tc. Percussion instruments" << endl;
        cout << "> ";
        cin >> buf;
		cout << endl;
        
        if (tolower(buf[0]) == 'q') {
            break;
        }
        else if (tolower(buf[0]) == 'a') {
			cout << "Showing string instruments.." << endl;
            show("string");
        }
        else if (tolower(buf[0]) == 'b') {
			cout << "Showing wind instruments.." << endl;
            show("wind");
        }
        else if (tolower(buf[0]) == 'c') {
			cout << "Showing percussion instruments.." << endl;
            show("percussion");
        }
        else {
            cout << "Invalid input. Please try again.";
        }

        cout << endl << "--------------------------" << endl << endl;
    }
}

void TypeIns::show(string type) {
    if (type == "string") {
        stringf();
    }
    else if (type == "wind") {
        wind();
    }
    else if (type == "percussion") {
        perc();
    }
}
