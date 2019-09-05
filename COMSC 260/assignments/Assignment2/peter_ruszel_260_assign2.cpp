// NOTE: The ONLY files that should be #included for this assignment are iostream, vector, and string
// No other files should be #included

#include <iostream>
#include <vector>
#include <string>

// NOTE: The ONLY files that should be #included for this assignment are iostream, vector, and string
// No other files should be #included


using namespace std;

int bin_to_dec(string);
string dec_to_bin(int);
int hex_to_dec(string);  
string dec_to_hex(int);  

int calcPower(int, int);

int main() 
{
	
	cout<<"10000011 binary = "<<bin_to_dec("10000011")<<" decimal\n"; // you should get 131
	cout<<"01010101 binary = "<<bin_to_dec("01010101")<<" decimal\n"; // you should get 85
	cout<<"1111111111111111 binary = "<<bin_to_dec("1111111111111111")<<" decimal\n"; // you should get 65,535
	cout<<"0111111111111111 binary = "<<bin_to_dec("0111111111111111")<<" decimal\n\n"; // you should get 32,767
	
	cout<<"255 decimal = "<<dec_to_bin(255)<<" binary\n"; //you should get 11111111
	cout<<"65532 decimal = "<<dec_to_bin(65532)<<" binary\n"; //you should get 1111111111111100
	cout<<"12 decimal = "<<dec_to_bin(12)<<" binary\n"; //you should get 1100
	cout<<"1000000 decimal = "<<dec_to_bin(1000000)<<" binary\n\n"; //you should get 11110100001001000000
	
	cout<<"ABC hexadecimal = "<<hex_to_dec("ABC")<<" decimal\n"; //you should get 2,748
	cout<<"F5 hexadecimal = "<<hex_to_dec("F5")<<" decimal\n"; //you should get 245
	cout<<"1234 hexadecimal = "<<hex_to_dec("1234")<<" decimal\n"; //you should get 4,660
	cout<<"FDECB hexadecimal = "<<hex_to_dec("FDECB")<<" decimal\n\n"; //you should get 1,040,075
	
	cout<<"512 decimal = "<<dec_to_hex(512)<<" hexadecimal\n"; //you should get 200
	cout<<"5000 decimal = "<<dec_to_hex(5000)<<" hexadecimal\n"; //you should get 1388
	cout<<"900000 decimal = "<<dec_to_hex(900000)<<" hexadecimal\n"; //you should get DBBA0
	cout<<"65525 decimal = "<<dec_to_hex(65525)<<" hexadecimal\n\n"; //you should get FFF5

	// My personal test cases
    cout<<"101010 binary = "<<bin_to_dec("101010")<<" decimal\n"; // you should get 42
    cout<<"0000001 binary = "<<bin_to_dec("0000001")<<" decimal\n"; // you should get 1
    cout<<"8675309 decimal = "<<dec_to_bin(8675309)<<" binary\n"; //you should get 100001000101111111101101
    cout<<"1492 decimal = "<<dec_to_bin(1492)<<" binary\n"; //you should get 10111010100
    cout<<"3FF2 hexadecimal = "<<hex_to_dec("3FF2")<<" decimal\n"; //you should get 16370
    cout<<"1 hexadecimal = "<<hex_to_dec("1")<<" decimal\n"; //you should get 1
    cout<<"1000 decimal = "<<dec_to_hex(1000)<<" hexadecimal\n"; //you should get 3E8
    cout<<"912383 decimal = "<<dec_to_hex(912383)<<" hexadecimal\n"; //you should get DEBFF
    cout<<"1 decimal = "<<dec_to_hex(1)<<" hexadecimal\n"; //you should get 1
    cout<<"16 decimal = "<<dec_to_hex(16)<<" hexadecimal\n"; //you should get 10

	
	system("PAUSE");
	return 0;

}

int calcPower(int base, int exponent)
{
    int result = 1;
    for (int i=0; i < exponent; i++) {
        result *= base;
    }
    return result;
}

// Converts any UNsigned binary number to decimal

int bin_to_dec(string s) 
{
    int result = 0;

    // start reading string at MSB (j)
    // decrease power of two for each bit (i)
    for (int i = s.size()-1, j = 0; j < s.size(); i--, j++) {
        if (s.at(j) == '1') {
            result += calcPower(2, i);
        }
    }

    return result;
}	

//Converts any non-negative decimal number to binary

string dec_to_bin(int n)  
{
    string result = "";
    int q, r; // quotient and remainder of integer division
    while (n > 0) {
        q = n / 2;
        r = n % 2;
        n = q; // take quotient and make it next divisor
        result = to_string(r) + result;
    }

    return result;
}


//Converts any UNsigned hexadecimal number to decimal

int hex_to_dec(string s) 
{
    int result = 0;

    // start reading string at MSHex (j)
    // decrease power of 16 for each hex (i)
    for (int i = s.size()-1, j = 0; j < s.size(); i--, j++) {
        // convert ASCII to hex value
        int hexVal = static_cast<int>(s.at(j));
        // 0 - 9
        if (hexVal >= 48 && hexVal < 58) {
            hexVal = hexVal - 48;
        }
        // A - F
        else if (hexVal >= 65 && hexVal < 71) {
            hexVal = hexVal - 55;
        }

        result += hexVal * calcPower(16, i);
    }

    return result;
}	


//Converts any non-negative decimal number to hexadecimal

string dec_to_hex(int n)  
{
    string result = "";
    int q, r; // quotient and remainder of integer division
    while (n > 0) {
        q = n / 16;
        r = n % 16;
        n = q; // take quotient and make it next divisor

        // Convert remainder into appropriate hex character
        char c;
        if (r < 10) {
            c = '0' + static_cast<char>(r);
        }
        else if (r >= 10) {
            c = 'A' + (r - 10);
        }
        result = c + result;
    }

    return result;
}



