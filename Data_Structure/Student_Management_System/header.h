#ifndef HEADER_H
#define HEADER_H

#include <iostream>
#include <fstream>
#include <string>
#include <string.h>
#include <iomanip>
#include <stdexcept>
#include <windows.h>

using namespace std;
HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);

void addNewStudent(void);
void searchStudent(void);
void modifyStudent(void);
void deleteStudent(void);
void showStudent(void);

void signUp(void);
void ResisterWindow(void);
void loginWindow(void);

void welcomePage(void);
void heading(int);
void menu(void);
void displayData(void);
void aboutMe(void);
void loadingbar(void);

void writeToFile(void);
void readFromFile(void);

int totalStudent(void);

int tStudent = 0;

struct Student{
    char name[256] = "Anonymous";
    long long int id = 0;
    char department[256] = "NA";
    double cgpa = 0.0;
    int semester = 0;
}st;

struct Password{
    char user_name[256] = "admin";
    char password[256] = "admin";
}logIn;

#endif // HEADER_H
