
void displayResult(const string& operation, const double& result)
{
	cout << "Result: " << operation << " = " << fixed << setprecision(2) << result;
	cout << '\t' << "Hex: " << hex << static_cast<int>(result);
	cout << '\t' << "Oct: " << oct << static_cast<int>(result);
	cout << '\t' << "Bin: " << bitset<4>(result) << endl;
}
