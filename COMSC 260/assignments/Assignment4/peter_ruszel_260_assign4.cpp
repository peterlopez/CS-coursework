// NOTE: The ONLY files that should be #included for this assignment are iostream, vector, and string
// No other files should be #included

#include <iostream>
#include <vector>
#include <string>

// NOTE: The ONLY files that should be #included for this assignment are iostream, vector, and string
// No other files should be #included


using namespace std;

int bin_to_dec(string);
int hex_to_dec(string);
int calcPower(int, int);

string padbin(string, int);
string trimbin(string);
int asciihextoint(int);

string addbin(string, string);
string addhex(string, string);

string multbin(string, string);
string multhex(string, string);

int main()
{
    // My tests
    // cout<<"binary 0000 * 00 = "<<multbin("0000", "00")<<endl;
    // cout<<"binary 0001 * 1111 = "<<multbin("0001", "1111")<<endl;
    // cout<<"binary 10 * 1 = "<<multbin("10", "1")<<endl;
    // cout<<"binary 111 * 0 = "<<multbin("111", "0")<<endl;
    // cout<<"binary 1 * 1 = "<<multbin("1", "1")<<endl;
    // cout<<"binary 0 * 0 = "<<multbin("0", "0")<<endl;
    // cout<<"binary 101 * 11 = "<<multbin("101", "11")<<endl;
    // cout<<"binary 11110 * 00001 = "<<multbin("11110", "00001")<<endl;
    // cout<<"binary 110101 * 00101 = "<<multbin("110101", "00101")<<endl;
    // cout<<"binary 111111 * 11111 = "<<multbin("111111", "11111")<<endl<<endl;

    // cout<<"hexadecimal 0 * 0 = "<<multhex("0", "0")<<endl;
    // cout<<"hexadecimal 1 * 1 = "<<multhex("1", "1")<<endl;
    // cout<<"hexadecimal 1 * 0 = "<<multhex("1", "0")<<endl;
    // cout<<"hexadecimal 0 * 1 = "<<multhex("0", "1")<<endl;
    // cout<<"hexadecimal 0000001 * 00000 = "<<multhex("0000001", "00000")<<endl;
    // cout<<"hexadecimal 0ACE * 1 = "<<multhex("0ACE", "1")<<endl;
    // cout<<"hexadecimal 1 * FBB = "<<multhex("1", "FBB")<<endl;
    // cout<<"hexadecimal CD * 8 = "<<multhex("CD", "8")<<endl;
    // cout<<"hexadecimal 9 * B = "<<multhex("9", "B")<<endl;
    // cout<<"hexadecimal A5123 * 111 = "<<multhex("A5123", "111")<<endl;
    

    // Professor's tests
    cout<<"binary 10001 * 11 = "<<multbin("10001", "11")<<endl; //you should get 110011
    cout<<"binary 100 * 110001 = "<<multbin("100", "11001")<<endl; //you should get 1100100
    cout<<"binary 110 * 1010 = "<<multbin("110", "1010")<<endl; //you should get 111100
    cout<<"binary 11111111 * 10 = "<<multbin("11111111", "10")<<endl; //you should get 111111110
    cout<<"binary 10101010 * 1 = "<<multbin("10101010", "1")<<endl;  //you should get 10101010
    cout<<"binary 0 * 11110000 = "<<multbin("0", "11110000")<<endl<<endl; //you should get 0

    cout<<"hexadecimal F * A = "<<multhex("F", "A")<<endl; //you should get 96
    cout<<"hexadecimal 1A * 5 = "<<multhex("1A", "5")<<endl; //you should get 82
    cout<<"hexadecimal FF * 2 = "<<multhex("FF", "2")<<endl; //you should get 1FE
    cout<<"hexadecimal 104 * 3 = "<<multhex("104", "3")<<endl; //you should get 30C
    cout<<"hexadecimal FABC * 1 = "<<multhex("FABC", "1")<<endl; //you should get FABC
    cout<<"hexadecimal 0 * EFDCAB = "<<multhex("0", "EFDCAB")<<endl<<endl; //you should get 0

    // system("PAUSE");
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

// Adds 0s to left of binary string until reaching given size
// if string is already greater or equal to size, do nothing
string padbin(string bin, int size)
{
  for (int i = bin.size(); i < size; i++) {
      bin = "0" + bin;
  }
  return bin;
}

// Remove superflous 0s padding left of binary string
string trimbin(string bin)
{
  if (bin.size() == 1) {
    return bin;
  }

  for (int i=0; i < bin.size(); i++) {
    // stop as soon as non-zero character reached
    if (bin[0] != '0') {
      return bin;
    }
    // erase leftmost '0'
    bin.erase(0, 1);

    // ran into weird bug where .erase() wont trim "00" down to "0"
    if (bin == "00") {
      return "0";
    }
  }
  return bin;
}

string addbin(string bin1, string bin2)
{
  string result = "";

  bin1 = trimbin(bin1); bin2 = trimbin(bin2);

  // make strings the same length
  int size = (bin1.size() > bin2.size()) ? bin1.size() : bin2.size();
  bin1 = padbin(bin1, size); bin2 = padbin(bin2, size);
  
  int sum = 0, carry = 0;
  int num1, num2;

  // loop through columns from right to left
  for (int i = size-1; i >= 0; i--)
  {
    num1 = static_cast<int>(bin1[i]) - 48;
    num2 = static_cast<int>(bin2[i]) - 48;

    // add
    sum = carry + num1 + num2;

    if (sum > 1) {
      sum = sum % 2;
      carry = 1;
    }
    else {
      carry = 0;
    }

    // append to left
    result = to_string(sum) + result;

    // deal with overflow
    if (i==0 && carry > 0) {
      result = "1" + result;
    }
  }

  return result;
}

int asciihextoint(int hex)
{
  // convert ASCII number 0-9
  if (hex < 65) {
    return hex - 48;
  }
  // convert ASCII letter A-F
  else {
    return hex - 55;
  }
}

string addhex(string hex1, string hex2)
{
  string result = "";

  // make strings the same length
  int size = (hex1.size() > hex2.size()) ? hex1.size() : hex2.size();
  hex1 = padbin(hex1, size);
  hex2 = padbin(hex2, size);

  int sum = 0, carry = 0;
  int num1, num2;

  // loop through columns from right to left
  for (int i = size-1; i >= 0; i--)
  {
    num1 = static_cast<int>(hex1[i]);
    num2 = static_cast<int>(hex2[i]);
    num1 = asciihextoint(num1);
    num2 = asciihextoint(num2);

    // add
    sum = carry + num1 + num2;

    if (sum > 15) {
      sum = sum % 16;
      carry = 1;
    }
    else {
      carry = 0;
    }

    // convert decimal to hex
    char char1;
    if (sum > 9) {
      char1 = 'A' + (sum-10);
    }
    else {
      char1 = '0' + sum;
    }

    // append to left
    result = char1 + result;

    // deal with overflow
    if (i==0 && carry > 0) {
      result = "1" + result;
    }
  }

  return result;    
}

string multbin(string bin1, string bin2)
{
    string result = "";

    // remove any extra 0s on left of binary strings
    bin1 = trimbin(bin1); bin2 = trimbin(bin2);

    // base case, multiplication by 1
    if (bin2 == "1") {
        return bin1;
    }
    // base case, multiplication by 1
    else if (bin1 == "1") {
        return bin2;
    }
    // base case, multiplication by 0
    else if (bin1 == "0" || bin2 == "0") {
        return "0";
    }

    // format both binary string inputs for multiplication
    int size = (bin1.size() > bin2.size()) ? bin1.size() : bin2.size();
    bin1 = padbin(bin1, size); bin2 = padbin(bin2, size);

    string largerbin, smallerbin;
    // get larger binary number, which will be added to itself
    if (bin_to_dec(bin1) > bin_to_dec(bin2)) {
        largerbin = bin1;
    } else {
        largerbin = bin2;
    }
    // get smaller binary number, for number of times it will be added
    if (bin_to_dec(bin1) < bin_to_dec(bin2)) {
        smallerbin = bin1;
    } else {
        smallerbin = bin2;
    }

    result = largerbin;
    // start at 1, since result is set to largerbin
    for (int i=1; i < bin_to_dec(smallerbin); i++)
    {
      result = addbin(result, largerbin);
    }

    return result;
}

string multhex(string hex1, string hex2)
{
    string result = "";

    // remove any extra 0s on left of hex strings
    hex1 = trimbin(hex1); hex2 = trimbin(hex2);

    // base case, multiplication by 1
    if (hex2 == "1") {
        return hex1;
    }
    // base case, multiplication by 1
    else if (hex1 == "1") {
        return hex2;
    }
    // base case, multiplication by 0
    else if (hex1 == "0" || hex2 == "0") {
        return "0";
    }

    // format both hex string inputs for multiplication
    int size = (hex1.size() > hex2.size()) ? hex1.size() : hex2.size();
    hex1 = padbin(hex1, size); hex2 = padbin(hex2, size);

    string largerhex, smallerhex;
    // get larger binary number, which will be added to itself
    if (hex_to_dec(hex1) > hex_to_dec(hex2)) {
        largerhex = hex1;
    } else {
        largerhex = hex2;
    }
    // get smaller binary number, for number of times it will be added
    if (hex_to_dec(hex1) < hex_to_dec(hex2)) {
        smallerhex = hex1;
    } else {
        smallerhex = hex2;
    }

    result = largerhex;
    // start at 1, since result is set to largerhex
    for (int i=1; i < hex_to_dec(smallerhex); i++)
    {
      result = addhex(result, largerhex);
    }

    return result;
}

