/***************************************************************
Problem: Classes and Objects
Question: 1 - hospital
Name: Peter Lopez
ID: 1611543
Date: 3/6/19
Status: wrong results - unable to add items to patient bill
****************************************************************/

#include <iostream>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

const double DAILY_RATE = 123.4;
const string SPACER_1 = "======================================";
const string SPACER_2 = "--------------------------------------";

class BillItem {
public:
	string getName() {
		return name;
	}
	void setName(string s) {
		name = s;
	}
	double getPrice() {
		return price;
	}
	void setPrice(double p) {
		price = p;
	}
	bool getIsMedication() {
	    return isMedication;
	}
	void setIsMedication(bool b) {
	    isMedication = b;
	}
	BillItem(string s, double p, bool b) {
		name = s;
		price = p;
		isMedication = b;
	}
private:
	string name;
	double price;
	bool isMedication; // if false, then assumed to be surgery
};


class PatientAccount {
public:
	void printBill() {
	    double total = 0;

	    cout << SPACER_1 << endl;
		cout << "Patient bill" << endl << endl;
		cout << "Name: " << name << endl;
        cout << "Age: " << age << endl;
		cout << "Address: " << endl << address << endl;
		cout << "SSN: " << ssn << endl;
		cout << SPACER_1 << endl;

		cout << "Medications:" << endl;
		for (BillItem i : billItems) {
		    if (i.getIsMedication()) {
                cout << "  - " << i.getName() << " : $" << i.getPrice() << endl;
                total += i.getPrice();
		    }
		}
        cout << SPACER_2 << endl;

		cout << endl << "Surgeries: " << endl;
		for (BillItem i : billItems) {
		    if (!i.getIsMedication()) {
                    cout << "  - " << i.getName() << " : $" << i.getPrice() << endl;
                    total += i.getPrice();
		    }
		}
        cout << SPACER_2 << endl;

		cout << endl << "  - Days in hospital: " << numDaysInHospital << " : $" << DAILY_RATE*numDaysInHospital << endl;
        total += DAILY_RATE * numDaysInHospital;
        cout << SPACER_2 << endl;

		cout << "Total : $" << total << endl;
        cout << SPACER_1 << endl << endl;
	}
	string getName() {
		return name;
	}
	void setName(string s) {
		name = s;
	}
	int getAge() {
		return age;
	}
	void setAge(int newAge) {
		age = newAge;
	}
	string getAddress() {
		return address;
	}
	void setAddress(string s) {
		address = s;
	}
	string getSsn() {
		return ssn;
	}
	void setSsn(string s) {
		ssn = s;
	}
	int getNumDaysInHospital() {
		return numDaysInHospital;
	}
	void setNumDaysInHospital(int n) {
		numDaysInHospital = n;
	}
	vector<BillItem> getBillItems() {
		return billItems;
	}
	/**
	 * TODO fix this so that items can be added to the vector
	 * for some reason whenever I access billItems
	 * it is always empty
	 */
	void addBillItem(BillItem b) {
		billItems.push_back(b);
		cout << b.getName() << " added!" << endl << endl;
	}
private:
	string name;
	int age;
	string address;
	string ssn;
	int numDaysInHospital;
	vector<BillItem> billItems;
};


class Surgery {
public:
    vector<BillItem> getProcedures() {
        return procedures;
    }
	void printSurgeries() {
		cout << "Surgery prices" << endl;
		for (int i = 0; i < procedures.size(); i++) {
			cout << i+1 << ") " << procedures.at(i).getName() << " : $" << procedures.at(i).getPrice() << endl;
		}
        cout << SPACER_2 << endl;
	}

private:
	vector<BillItem> procedures = {
			{ "Appendectomy", 5192, 0 },
			{ "Cataract", 1287, 0 },
			{ "Cholecystectomy", 278, 0 },
			{ "ACL", 72, 0 },
			{ "Cesarean Section", 9218, 0 }
	};
};

class Pharmacy {
public:
	vector<BillItem> getMedications() {
	    return medications;
	}
	void printMedications() {
		cout << "Medication prices" << endl;
		for (int i = 0; i < medications.size(); i++) {
			cout << i+1 << ") " << medications.at(i).getName() << " : $" << medications.at(i).getPrice() << endl;
		}
        cout << SPACER_2 << endl;
	}
private:
	vector<BillItem> medications = {
			{ "Asprin", 10, 1 },
			{ "Vicodin", 30, 1 },
			{ "Lipitor", 43.15, 1 },
			{ "Amoxicillin", 12.5, 1 },
			{ "Penecillin", 20, 1 }
	};
};

class Hospital {
public:
	Surgery getSurgery() {
		return surgery;
	}
	Pharmacy getPharmacy() {
		return pharmacy;
	}
	vector<PatientAccount> getPatients() {
		return patients;
	}
	void addPatient(PatientAccount &newPatient) {
		patients.push_back(newPatient);
	}
	void removePatient(int i) {
	    if (i < patients.size()) {
            patients.erase(patients.begin() + i);
        }
	    else {
	        cout << "Invalid patient index '" << i << "'" << endl;
	    }
	}
	void displayPatientNames() {
	    cout << "Patients in hospital: " << endl;
		for (int i = 0; i < patients.size(); i++) {
			cout << i+1 << ") " << patients.at(i).getName() << endl;
		}
        cout << SPACER_2 << endl;
	}
private:
	Pharmacy pharmacy;
	Surgery surgery;

	vector<PatientAccount> patients;
};


void addPatient(Hospital &hospital)
{
    PatientAccount newPatient;
    string input, value;

    cout << SPACER_2 << endl;
    cout << endl << "Add patient" << endl;

    cout << "Enter the patient name : ";
    getline(cin, input);
    stringstream(input) >> value;
    newPatient.setName(value);

    cout << "Enter the patient address (3 lines) : " << endl;
    stringstream ss;
    for (int i = 0; i < 3; i++) {
        getline(cin, input);
        ss << input << '\n';
    }
    value = ss.str();
    newPatient.setAddress(value);

    cout << "Enter the patient's age : ";
    getline(cin, input);
    stringstream(input) >> value;
    newPatient.setAge(stoi(value)); // TODO input validation

    cout << "Enter the patient's SSN : ";
    getline(cin, input);
    stringstream(input) >> value;
    newPatient.setSsn(value);

    cout << "Enter number of days in hospital : ";
    getline(cin, input);
    stringstream(input) >> value;
    newPatient.setNumDaysInHospital(stoi(value)); // TODO input validation

    hospital.addPatient(newPatient);

    cout << endl << "Patient added" << endl << endl;
}

int getSelectionFromList(int listSize)
{
    char index;
    string input;
    bool flag = false;
    while (!flag) {
        cout << "Make a selection : ";
        getline(cin, input);
        stringstream(input) >> index;

        // if either is false ask again
        flag = isdigit(index) && (static_cast<int>(index) - '0') <= listSize;
    }

    // subtract 1 since indexes are output to user
    // starting at 1 instead of 0
    return (static_cast<int>(index) - '0') - 1;
}

void mainMenu() {
	Hospital hospital;

	// options for user
	const int CODE_ADD_PATIENT = 1;
	const int CODE_DISPLAY_BILL = 2;
	const int CODE_CHECK_OUT = 3;
	const int CODE_ADD_SURGERY = 4;
	const int CODE_ADD_PHARMACY = 5;
	const int CODE_EXIT = 6;

	// Selections by user
	int choice = 0;
    int patientIndex;
    int billItemIndex;

	while (choice != CODE_EXIT)
	{
		cout << "Main menu" << endl;
		cout << "(" << CODE_ADD_PATIENT << ") Add patient" << endl;
		cout << "(" << CODE_DISPLAY_BILL << ") Display patient bill" << endl;
		cout << "(" << CODE_CHECK_OUT << ") Check out patient " << endl;
		cout << "(" << CODE_ADD_SURGERY << ") Add surgery item for patient" << endl;
		cout << "(" << CODE_ADD_PHARMACY << ") Add pharmacy item for patient" << endl;
		cout << "(" << CODE_EXIT << ") Exit" << endl;
        cout << SPACER_2 << endl;
		cout << endl << "Make a selection : ";

		string input;
		getline(cin, input);
        choice = stoi(stringstream(input).str());
        stringstream(input) >> choice;

		switch (choice)
		{
			case CODE_ADD_PATIENT:
			    addPatient(hospital);
				break;
			case CODE_DISPLAY_BILL:
            case CODE_CHECK_OUT:
            case CODE_ADD_SURGERY:
            case CODE_ADD_PHARMACY:
                if (hospital.getPatients().empty()) {
                    cout << "No patients in hospital" << endl << endl;
                }
                else {
                    hospital.displayPatientNames();
                    patientIndex = getSelectionFromList(hospital.getPatients().size());

                    switch (choice)
                    {
                        case CODE_DISPLAY_BILL:
                            hospital.getPatients().at(patientIndex).printBill();
                            break;
                        case CODE_CHECK_OUT:
                            hospital.removePatient(patientIndex);
                            break;
                        case CODE_ADD_SURGERY:
                            hospital.getSurgery().printSurgeries();
                            billItemIndex = getSelectionFromList(hospital.getSurgery().getProcedures().size());
                            // add procedure to patient bill
                            hospital.getPatients().at(patientIndex).addBillItem(
                                    hospital.getSurgery().getProcedures().at(billItemIndex)
                                    );
                            break;
                        case CODE_ADD_PHARMACY:
                            hospital.getPharmacy().printMedications();
                            billItemIndex = getSelectionFromList(hospital.getPharmacy().getMedications().size());
                            // add medication to patient bill
                            hospital.getPatients().at(patientIndex).addBillItem(
                                    hospital.getPharmacy().getMedications().at(billItemIndex)
                                    );
                            break;
                    }
                }
                break;
			case CODE_EXIT:
				cout << "Exiting" << endl;
				break;
			default:
				cout << "Invalid option. Try again" << endl << endl;
		}
	}
}

int main()
{
	mainMenu();

	return 0;
}
