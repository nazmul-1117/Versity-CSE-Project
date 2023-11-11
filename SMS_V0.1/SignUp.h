using namespace std;

void signUp(void){
    cout<<"1. Log in"<<endl;
    cout<<"2. Resister"<<endl;
    cout<<"3. Menu"<<endl;

    /*cout<<"\n\t--------------------------------------------------\n";
    cout<<"\tSign Up page is Coming soon...";
    cout<<"\n\t--------------------------------------------------\n";*/

    char choice;
    cout<<"Enter Your Choice: ";
    cin>>choice;

      switch(choice){

        case '1': loginWindow(); break;
        case '2': ResisterWindow(); break;
        case '3': menu(); break;

        default: cout<<"wrong Choose"<<endl;
      }
       system("pause");
       system("cls");

       signUp();
}
