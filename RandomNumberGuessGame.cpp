#include <iostream>
#include <ctime>

using namespace std;

void menu();
void randomNumberGuessGame();

void menu(){
	int choice=0;

	cout << "<--GAME MAIN MENU-->"<<endl;
	cout << "---------------------------\n"<<endl;

	cout<<"1. Start a New Game\n2. Exit"<<endl;
	cin>>choice;

	switch(choice){
		case 1: randomNumberGuessGame(); break;
		case 2: exit(0);
		default: cout << "Wrong Input"<<endl;
	}
}

void randomNumberGuessGame(){
	srand(time(0));

	int randomNumber = rand()%100;
	int yourNumber = 0;

	cout<<"Enter Your Number: ";
	cin>>yourNumber;

	do
	{
		if(randomNumber > yourNumber){
			cout<<"The number is Greater than "<<yourNumber<<endl;
			cout<<"Enter Again: ";
			cin>>yourNumber;
		}
		else if(randomNumber < yourNumber){
			cout<<"The number is less than "<<yourNumber<<endl;
			cout<<"Enter Again: ";
			cin>>yourNumber;
		}

	} while (yourNumber != randomNumber);

	cout<<"Congratulation. You Find the Number\n";
	cout<<"Your Number is: "<<randomNumber<<endl;
}

int main(){
	menu();
	return 0;
}
