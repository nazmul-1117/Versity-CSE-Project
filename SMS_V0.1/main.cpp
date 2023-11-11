#include <bits/stdc++.h>
#include <iostream>
#include <fstream>
#include <string>
#include <string.h>
#include <iomanip>
using namespace std;

int tStudent = -1;  //initialy count start with -1,

int totalStudent(void);
void signUp(void);
void welcomePage(void);
void ResisterWindow(void);
void writeToFile(void);
void readFromFile(void);
void loginWindow(void);
void modifyStudent(void);
void heading(void);

#include "studentClass.h"
#include "menu.h"
#include "SignUp.h"
#include "welcomePage.h"
#include "loginNresister.h"
#include "CRUDS.h"
#include "readWriteFile.h"
#include "Display.h"

int totalStudent(){
    int tStudent=0;

    ifstream fin;
    fin.open("record.bin", ios :: in | ios :: binary);
    if(!fin.is_open()){
        return 0;
    }
    while(fin.read((char*)&st, sizeof(Student))){
           tStudent++;
    }
    fin.close();

    return tStudent;
}

int main(){
    system("color 0A");
    welcomePage();
    menu();

    cout<<endl<<endl;
}
