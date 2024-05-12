#include <bits/stdc++.h>
#define lld long long int
using namespace std;

const string decimalToBinary(lld n, int dividentLength=0){
    string binaryNum;
    while (n > 0) {
        int a = n % 2;
        binaryNum.push_back(a + '0');
        n /= 2;
    }
    if(binaryNum.length() <= dividentLength){
        for(int i=binaryNum.length()+1; i <=  dividentLength; i++){
            binaryNum.push_back(0+'0');
        }
    }
    reverse(binaryNum.begin(), binaryNum.end());
    return binaryNum;
}

const lld binaryToDecimal(const string& binaryString) {
    lld value = 0;
    int indexCounter = 0;
    for (int i = binaryString.length() - 1; i >= 0; i--) {
        if (binaryString[i] == '1') {
            value += pow(2, indexCounter);
        }
        indexCounter++;
    }
    return value;
}

string add(string A, string M) {
	int carry = 0; string sum;

	for (int i = A.length() - 1; i >= 0; i--) {
		int temp = A[i] - '0' + M[i] - '0' + carry;

		if (temp > 1) {
			sum.push_back('0' + (temp % 2));
			carry = 1;
		}
		else {
			sum.push_back('0' + temp);
			carry = 0;
		}
	}
	reverse(sum.begin(), sum.end());
	return sum;
}

string complement(string m) {
	string M;
	for (int i = 0; i < m.length(); i++) {
		M.push_back('0' + ((m[i] - '0' + 1) % 2));
	}
	string plusOne = string(m.length()-1, '0')+'1';
	M = add(M, plusOne);
	return M;
}

void displaySteps(string M, string A, string Q){
    cout << M << "\t------>\t" << A << "\t------>\t" << Q ;
}

void restoringDivision(const string M, string A, string Q){
    int n = Q.length(), i=0;
    string negativeM = M;
    cout <<"\n\t\t<--Restoring Devision-->\n-----------------------------------------------------\n";
    cout << "Dividend = " << binaryToDecimal(Q) <<" = "<< Q << "\n" ;
    cout << "Divisor = " << binaryToDecimal(M) <<" = "<< M << "\n";
    cout << "Accumulator = 0 = " << A << "\nCounter = " <<n<<
    "\n-----------------------------------------------------\n\nStart Process-->\n";

    cout << "\nM\t------>\tA\t------>\tQ\t------>\tOperations\n";
    cout << "----------------------------------------------------------------------------------------\n\n";
    cout << M << "\t------>\t" << A << "\t------>\t" << Q << "\t------>\tInitialization"<<endl;
    cout << "------------------------- "<<i+1<<" Step -------------------------------------------------------\n\n";

    while(n > i++){
		A = A.substr(1) + Q[0]; Q = Q.substr(1) + '_';  //LS AQ

		displaySteps(M, A, Q);
		cout << "\t------>\t Shift Left AQ"<<endl;

		negativeM = complement(M); //M=-M
		A = add(A, negativeM); //A=A-M

		displaySteps(M, A, Q);
		cout << "\t------>\t A = A-M"<<endl;

		if(A[0] == '1'){
            Q[Q.length()-1] = '0';
            A = add(A, M); //restore; A = A+M

            displaySteps(M, A, Q);
            cout << "\t------>\t A<0(-), so Q[0] = 0 and A=A+M (Restore A)\n"<<endl;

		}else{
            Q[Q.length()-1] = '1';

            displaySteps(M, A, Q);
            cout << "\t------>\t 0<=A(+), so Q[0] = 1. (No Restore)\n"<<endl;
		}

        if(i < n)
            cout << "------------------------- "<<i+1<<" Step -------------------------------------------------------\n\n";
        else
            cout << "----------------------------------------------------------------------------------------\n\n";
    }

    cout<<"Reminder (A) = " << A <<" = " <<binaryToDecimal(A)<<endl;
    cout<<"Result (Q) = " << Q <<" = " <<binaryToDecimal(Q)<<endl<<endl<<endl;
    system("pause"); system("cls");
}

void nonRestoringDivision(const string M, string A, string Q){
    int count = Q.length(), i=0;
    string negativeM = complement(M);

    cout <<"\n\t\t<--Non-Restoring Devision-->\n-----------------------------------------------------\n";
    cout << "Dividend = " << binaryToDecimal(Q) <<" = "<< Q << "\n" ;
    cout << "Divisor = " << binaryToDecimal(M) <<" = "<< M << "\n";
    cout << "Accumulator = 0 = " << A << "\nCounter = " <<count<<
    "\n-----------------------------------------------------\n\nStart Process-->\n";

    cout << "\nM\t------>\tA\t------>\tQ\t------>\tOperations\n";
    cout << "----------------------------------------------------------------------------------------\n\n";
    cout << M << "\t------>\t" << A << "\t------>\t" << Q << "\t------>\tInitialization"<<endl;
    cout << "------------------------- "<<i+1<<" Step -------------------------------------------------------\n\n";

    while (count > i++){
        if(A[0] == '0'){
            A = A.substr(1) + Q[0]; Q = Q.substr(1) + '_'; //LS AQ

            displaySteps(M, A, Q);
            cout << "\t------>\t Shift Left AQ"<<endl;

            A = add(A, negativeM);  //A = A-M

            displaySteps(M, A, Q);
            cout << "\t------>\t 0 <= A(+), A = A-M"<<endl;

        }else{
            A = A.substr(1) + Q[0]; Q = Q.substr(1) + '_'; //LS AQ

            displaySteps(M, A, Q);
            cout << "\t------>\t Shift Left AQ"<<endl;

            A = add(A, M); //A = A+M

            displaySteps(M, A, Q);
             cout << "\t------>\t A< 0(-), A = A+M"<<endl;
        }

        if(A[0] == '0'){
            Q[Q.length()-1] = '1';  //Q[0] = 1

            displaySteps(M, A, Q);
            cout << "\t------>\t 0<=A(+), Q[0] = 1\n"<<endl;

        }else{
            Q[Q.length()-1] = '0';  //Q[0] = 0

            displaySteps(M, A, Q);
            cout << "\t------>\t A<0(-), Q[0] = 0\n"<<endl;
        }
        if(i < count)
            cout << "------------------------- "<<i+1<<" Step -------------------------------------------------------\n\n";
        else
            cout << "----------------------------------------------------------------------------------------\n\n";
    }
    if(A[0] == '1'){
        A = add(A, M);  //A<0 -> A = A+M
    }
    cout << M << "\t------>\t" << A << "\t------>\t" << Q << "\t------>\tOutput"<<endl;
    cout<<"\nReminder (A) = " << A <<" = " <<binaryToDecimal(A)<<endl;
    cout<<"Result (Q) = " << Q <<" = " <<binaryToDecimal(Q)<<endl<<endl<<endl;
    system("pause"); system("cls");
}

void aboutProject(){
    system("cls");
    cout<<"\n <-- About Project -->\n------------------------------\n";
    cout<<" <--Presented to-->\n-------------------------------\n";
    cout<<" Syed Ahsanul Kabir\n Associate Chairperson\n Green University Of Bangladesh\n\n";
    cout<<" About Me\n-------------------------------\n";
    cout<<" Name: Md. Nazmul Hossain\n ID: 223002089\n-------------------------------\n";
    cout<<" Name: Md. Abdullah Al Fuad\n ID: 223002080\n-------------------------------\n\n";
    system("pause"); system("cls");
}

void menuBar(){
    string dividend = "0000"; string divisor = "0000";
	string accumulator = string(dividend.length(), '0');
    int choice;

    cout << "\n***MAIN MENU***\n---------------------------------\n";
    cout << "[1] Restoring Division Algorithm\n[2] Non-Restoring Division Algorithm\n[3] About Project\n[4] Exit\n\n";
    cout << "Enter Your Choice: "; cin >> choice;
    cout << "-------------------------------------\n";
    system("cls");

    if(choice == 4) exit(0);
    if(choice == 1 || choice == 2){
            lld dvdnt, dvsr;
            cout << "Enter Dividend: "; cin >> dvdnt;
            cout << "Enter Divisor: "; cin >> dvsr;

            dividend = decimalToBinary(dvdnt);
            accumulator = string(dividend.length()+1, '0');
            divisor = decimalToBinary(dvsr, accumulator.length());

            cout<<"\n-------------------------------------------\n";
            cout << "Dividend: "<<dividend<<endl;
            cout << "Divisor: "<<divisor << endl;
            cout << "Accumulator: "<<accumulator << endl;
            cout<<"\n-------------------------------------------\n";

                system("pause"); system("cls");

                if(choice == 1)
                     restoringDivision(divisor, accumulator, dividend);
                else
                    nonRestoringDivision(divisor, accumulator, dividend);

            }else if(choice == 3){
                aboutProject();
            }
            else{
                cout << "Please Enter Valid Option\n";
                system("pause");
                system("cls");
            }
}

int main(){
    while(true)
        menuBar();
}
