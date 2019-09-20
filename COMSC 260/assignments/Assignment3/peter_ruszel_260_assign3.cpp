// NOTE: The ONLY files that should be #included for this assignment are iostream, vector, and string
// No other files should be #included

#include <iostream>
#include <vector>
#include <string>

// NOTE: The ONLY files that should be #included for this assignment are iostream, vector, and string
// No other files should be #included


using namespace std;

string addbin(string, string);
string addhex(string, string);

string padbin(string, int);
string trimbin(string);
int asciihextoint(int);

int main()
{
  // My tests
  cout<<"binary 100000 + 011111 = "<<addbin("100000", "011111")<<endl;
  cout<<"binary 01010 + 10101 = "<<addbin("01010", "10101")<<endl;
  cout<<"binary 1111 + 1111 = "<<addbin("1111", "1111")<<endl;
  cout<<"binary 0000001 + 110 = "<<addbin("0000001", "110")<<endl;
  cout<<"binary 1 + 1 = "<<addbin("1", "1")<<endl;
  cout<<"binary 0 + 0 = "<<addbin("0", "0")<<endl;
  cout<<"binary 1 + 0 = "<<addbin("1", "0")<<endl;
  cout<<"binary 00000 + 00 = "<<addbin("00000", "00")<<endl;
  cout<<"binary 11111 + 10000 = "<<addbin("11111", "10000")<<endl;
  cout<<"binary 1100011 + 0111110 = "<<addbin("1100011", "0111110")<<endl<<endl;

  cout<<"hexadecimal A4 + A5 = "<<addhex("A4", "A5")<<endl; 
  cout<<"hexadecimal 2B + C = "<<addhex("2B",  "C")<<endl;   
  cout<<"hexadecimal FABC + 789 = "<<addhex("FABC", "789")<<endl; 
  cout<<"hexadecimal FFFFFF + FF = "<<addhex("FFFFFF", "FF")<<endl;
  cout<<"hexadecimal 1 + 1 = "<<addhex("1", "1")<<endl;
  cout<<"hexadecimal A + F = "<<addhex("A", "F")<<endl;
  cout<<"hexadecimal 9 + 1 = "<<addhex("9", "1")<<endl;
  cout<<"hexadecimal CDEF + 0 = CDEF"<<addhex("CDEF", "0")<<endl;
  cout<<"hexadecimal 1234 + ABCD = "<<addhex("1234", "ABCD")<<endl;
  cout<<"hexadecimal 1A2B3C + 11111 = "<<addhex("1A2B3C", "11111")<<endl<<endl;

  // Professor's test
  // cout<<"binary 1101 + 1000 = "<<addbin("1101", "1000")<<endl;   //you should get 10101
  // cout<<"binary 11000 + 1011 = "<<addbin("11000", "1011")<<endl; //you should get 100011
  // cout<<"binary 11111111 + 1 = "<<addbin("11111111", "1")<<endl; //you should get 100000000
  // cout<<"binary 101010 + 10 = "<<addbin("101010", "10")<<endl<<endl; //you should get 101100

  // cout<<"hexadecimal A4 + A5 = "<<addhex("A4", "A5")<<endl;  //you should get 149
  // cout<<"hexadecimal 2B + C = "<<addhex("2B",  "C")<<endl;    //you should get 37
  // cout<<"hexadecimal FABC + 789 = "<<addhex("FABC", "789")<<endl;  //you should get 10245
  // cout<<"hexadecimal FFFFFF + FF = "<<addhex("FFFFFF", "FF")<<endl<<endl; //you should get 10000FE 

  // system("PAUSE");
  return 0;

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
