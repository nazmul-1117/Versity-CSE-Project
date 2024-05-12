#include <iostream>
#include "header.h"

using namespace std;

int main(){

    for(int i=0; i<16; i++){
        SetConsoleTextAttribute(hConsole, i);
        cout<<i<<". Nazmul"<<endl;
    }

    SetConsoleTextAttribute(hConsole, 9);
    cout<<"\n\t\t\t\t---------------------------------------------------\n";
    cout<<"\t\t\t\t\tSTUDENT MANAGEMENT SYSTEM";
    cout<<"\n\t\t\t\t\tStore Record by using C++";
    SetConsoleTextAttribute(hConsole, 12);
    cout<<"\n\t\t\t\t\t\t\t\tDevoloped by Nazmul";
    SetConsoleTextAttribute(hConsole, 9);
    cout<<"\n\t\t\t\t---------------------------------------------------\n\n\n"<<endl;

}
