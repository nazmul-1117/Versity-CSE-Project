//before run this code input user define Header File
//put main file and header file in one folder

#include "header.h"

void addNewStudent(){
            system("cls");
            heading(1);
            tStudent = totalStudent();

            int n;
            n = ((st.id)%10)+1;
            st.id = 223002000 + n;

            SetConsoleTextAttribute(hConsole, 2);
            cout<<"----------------------------------"<<endl;
            cout<<"\tRESISTER NEW STUDENT"<<endl;
            cout<<"----------------------------------"<<endl;

            cin.ignore();
            cout<<"\nEnter New Student Name: ";
            cin.getline(st.name, 256);

            cout<<"\nEnter Student Department: ";
            cin.getline(st.department, 256);

            while(true){
                    int a;
                    SetConsoleTextAttribute(hConsole, 2);
                    cout<<"\nEnter Student Semester: "; cin>>a;

                    if(a <= 0 || a > 8){
                            SetConsoleTextAttribute(hConsole, 4);
                            cout<<"Please input valid CGPA\n"<<endl;
                            continue ;
                    }

                    if(cin.fail()){
                        cin.clear(); cin.ignore(10, '\n');
                        SetConsoleTextAttribute(hConsole, 4);
                        cout<<"Please input valid Semester\n"<<endl;
                        continue;
                    }
                    st.semester = a; break;
            }

            while(true){
                    double a;
                    SetConsoleTextAttribute(hConsole, 2);
                    cout<<"\nEnter Student CGPA: "; cin>>a;

                    if(a < 0.0 || a > 4.00){
                            SetConsoleTextAttribute(hConsole, 4);
                            cout<<"Please input valid CGPA\n"<<endl;
                            continue ;
                    }

                    if(cin.fail()){
                        cin.clear(); cin.ignore(10, '\n');
                        SetConsoleTextAttribute(hConsole, 4);
                        cout<<"Please input valid CGPA\n"<<endl;
                        continue;
                    }
                    st.cgpa = a; break;
            }
            SetConsoleTextAttribute(hConsole, 10);

            writeToFile();
            cout<<"\nRecord Save successfully."<<endl;
            cout<<"\nThanks "<<st.name<<" for admission on our University"<<endl;
            cout<<"Your ID Number is: "<<st.id<<endl<<endl<<endl;
}

void modifyStudent(){
    system("cls");
    heading(1);
    SetConsoleTextAttribute(hConsole, 10);
    cout<<"----------------------------------"<<endl;
    cout<<"\tUPDATE STUDENT DETAILS"<<endl;
    cout<<"----------------------------------"<<endl;

    long long int idNo, position;
    bool notFound = false;
    cout<<"\nEnter ID No: ";
    cin>>idNo; cin.ignore();

    fstream fio;
    fio.open("record.csv", ios :: in | ios :: out);
    if(!fio.is_open()){
        SetConsoleTextAttribute(hConsole, 4);
        cout<<"\n\tFile Unable To Open\n"<<endl;
        SetConsoleTextAttribute(hConsole, 2);
        return ;
    }

    while(fio.read((char*)&st, sizeof(st))){

        if(idNo == st.id){
            position = fio.tellg();
            fio.seekg(position-sizeof(st));

            cout<<"\nStudent Found...\n";
            showStudent();

            SetConsoleTextAttribute(hConsole, 14);
            cout<<"\n------------------------------"<<endl;
            cout<<"Enter New Details\n";
            cout<<"------------------------------"<<endl;
            cout<<"\nEnter Student New Name: ";
            cin.getline(st.name, 256);

            cout<<"\nEnter Student New Department: ";
            cin.getline(st.department, 256);

            while(true){
                    int a;
                    cout<<"\nEnter Student Semester: ";
                    cin>>a;
                    if(cin.fail()){
                        cin.clear(); cin.ignore(10, '\n');
                        SetConsoleTextAttribute(hConsole, 4);
                        cout<<"Please input valid Semester"<<endl;
                        SetConsoleTextAttribute(hConsole, 14);
                        continue;
                    }
                    st.semester = a;
                    break;
            }

            fio.write((char*)&st, sizeof(st));
            notFound = false;
            break ;
        }
        notFound = true;
    }
    fio.close();

    if(notFound){
        cout<<"\nSorry..!\nStudent ID not matched"<<endl;
    }
    SetConsoleTextAttribute(hConsole, 10);
}

void deleteStudent(){
    system("cls");
    heading(1);
    SetConsoleTextAttribute(hConsole, 10);
    cout<<"-----------------------------"<<endl;
    cout<<"DELETE STUDENT"<<endl;
    cout<<"-----------------------------"<<endl;

    long long int idNo;
    bool notFound = true, rightPassword = true;
    char password[256];

    cout<<"\nDelete by ID No: ";
    cin>>idNo; cin.ignore();

    ifstream fin;
    ofstream fout;

    fin.open("record.csv");
    fout.open("temp.csv");

    if(!fin.is_open()){
        SetConsoleTextAttribute(hConsole, 4);
        cout<<"\n\tFile Unable To Open\n"<<endl;
        SetConsoleTextAttribute(hConsole, 10);
        return ;
    }

    while(fin.read((char*)&st, sizeof(Student))){
        if(idNo == st.id){
            cout<<"\n----------------------------\n";
            cout<<"Student Found...";
            cout<<"\n----------------------------\n";
            notFound = false;

            SetConsoleTextAttribute(hConsole, 14);
            showStudent();
            SetConsoleTextAttribute(hConsole, 4);
            cout<<"Do You Want to Delete(Permanently)?\nNote: Data cannot be undone"<<endl;
            cout<<"\n1. Enter Password (Type)\n2. Back (Enter 2)\n\nEnter : ";
            cin.getline(password, 256);

                if(strcmp(password, logIn.password)){

                    fin.close();
                    fout.close();
                    rightPassword = false;
                    remove("temp.csv");
                    cout<<endl<<idNo<<" not be Deleted"<<endl;
                    break;
                }
                 if(!strcmp(password, logIn.password)) continue ;
        }
        fout.write((char*)&st, sizeof(Student));
    }
    fin.close();
    fout.close();

    if(rightPassword){
        remove("record.csv");
        rename("temp.csv", "record.csv");
        cout<<endl<<idNo<<" Delete Successfully.."<<endl;
    }

    if(notFound){
        SetConsoleTextAttribute(hConsole, 4);
        cout<<"ID not matched"<<endl;
    }
    SetConsoleTextAttribute(hConsole, 10);
}

void searchStudent(){
    system("cls");
    heading(1);
    SetConsoleTextAttribute(hConsole, 10);
    cout<<"-----------------------------"<<endl;
    cout<<"\tSEARCH STUDENT"<<endl;
    cout<<"-----------------------------"<<endl;

    long long int idNo;
    bool notFound = false;
    cout<<"\nEnter ID No: ";
    cin>>idNo; cin.ignore();

    ifstream fin;
    fin.open("record.csv");
    if(!fin.is_open()){
        SetConsoleTextAttribute(hConsole, 4);
        cout<<"\n\tFile Unable To Open\n"<<endl;
        SetConsoleTextAttribute(hConsole, 10);
        return ;
    }

    while(fin.read((char*)&st, sizeof(Student))){
        if(idNo == st.id){
            cout<<"\nStudent Found..."<<endl;
            fin.close();
            SetConsoleTextAttribute(hConsole, 14);
            showStudent();
            SetConsoleTextAttribute(hConsole, 10);
            return ;
        }
        notFound = true;
    }
    fin.close();
    if(notFound){
            SetConsoleTextAttribute(hConsole, 4);
        cout<<"ID not matched"<<endl;
    SetConsoleTextAttribute(hConsole, 10);
    }
}

void writeToFile(){
                            ofstream fout;
                            fout.open("record.csv", ios :: app);
                            fout.write((char*)&st, sizeof(st));
                            fout.close();
}

 void readFromFile(){
                system("cls");
                heading(1);

                ifstream fin;
                fin.open("record.csv");
                if(!fin.is_open()){
                    cout<<"\n\tFile Unable To Open\n"<<endl;
                    return ;
                }
    SetConsoleTextAttribute(hConsole, 10);
    cout<<"-------------------------------------------"<<endl;
    cout<<"\tSHOW ALL STUDENTS"<<endl;
    cout<<"-------------------------------------------\n"<<endl;
    heading(0);

        while(fin.read((char*)&st, sizeof(st))){
            cout<<setw(24)<<left<<st.name<<"| ";
            cout<<setw(22)<<left<<st.id<<"| ";
            cout<<setw(17)<<left<<st.department<<"| ";
            cout<<setw(24)<<left<<st.semester<<"|";
            cout<<setw(7)<<right<<st.cgpa<<"|";
            cout<<endl;
        }
        fin.close();
        cout<<endl<<endl;
}

void loginWindow(){
    system("cls");
    heading(1);
    cout<<"---------------------------"<<endl;
    cout<<"ADMIN LOG IN PAGE"<<endl;
    cout<<"---------------------------"<<endl;

    SetConsoleTextAttribute(hConsole, 2);
     char uName[256], uPass[256];
     bool notFound = false;

     cout<<"Enter Username: ";
     cin.ignore(); cin.getline(uName, 256);
     cout<<"Enter Password: ";
     cin.getline(uPass, 256);

     ifstream fin;
     fin.open("Log_In_Record.csv");

     if(fin.is_open()){
        while(fin.read((char*)&logIn, sizeof(logIn))){
            if(!(strcmp(uName, logIn.user_name)) && !(strcmp(uPass, logIn.password))){
                    fin.close();
                    notFound = false;
                    loadingbar();
                    cout<<"Log In Successfully...\n"<<endl;
                    system("pause");
                    system("cls");
                    menu();
                    break ;
            }
            else notFound = true;
        }
        fin.close();
        SetConsoleTextAttribute(hConsole, 4);
        if(notFound) cout<<"Username or Password Not Found\nPlease try Agein\n"<<endl;
     }
     else{
        cout<<"Log In Error. Please Try Again\n"<<endl;
     }
     SetConsoleTextAttribute(hConsole, 10);

     system("pause");
     system("cls");
     welcomePage();
}

void ResisterWindow(){
    system("cls");
    heading(1);
    cout<<"---------------------------"<<endl;
    cout<<"ADMIN RESISTER PAGE"<<endl;
    cout<<"---------------------------"<<endl;

     SetConsoleTextAttribute(hConsole, 4);
     ofstream fout;
     fout.open("Log_In_Record.csv");

     cout<<"Enter Username: ";
     cin.ignore();
     cin.getline(logIn.user_name, 256);

     cout<<"Enter Password: ";
     cin.getline(logIn.password, 256);

     if(fout.is_open()){
        fout.write((char*)&logIn, sizeof(logIn));
        fout.close();
        loadingbar();
        cout<<"Register Complete\n"<<endl;
     }
     else{
        cout<<"Register Error. Please Try Again\n"<<endl;
     }
     system("pause");
     system("cls");
     welcomePage();
}

void signUp(){
    SetConsoleTextAttribute(hConsole, 9);
    cout<<"1. Log in"<<endl;
    cout<<"2. Resister"<<endl;
    cout<<"3. Log In as a Guest"<<endl;
    cout<<"4. Exit"<<endl;
    SetConsoleTextAttribute(hConsole, 10);

    try{
        int choice;
        cout<<"Enter Your Choice: ";
        cin>>choice;
         cout<<"------------------------------------\n\n";

        if(cin.fail()){
            cin.clear();
            cin.ignore(10, '\n');
            throw runtime_error("Invalid Input. ");
        }

          switch(choice){

            case 1: loginWindow(); break;
            case 2: ResisterWindow(); break;
            case 3: searchStudent(); break;
            case 4: exit(0); break;

            default: cout<<"wrong Choose"<<endl;
          }
    }
    catch(runtime_error& er){
        SetConsoleTextAttribute(hConsole, 4);
        cout<<er.what()<<"Please Input as an Integer.\n"<<endl;
        SetConsoleTextAttribute(hConsole, 10);
    }
    cout<<"\n------------------------------------\n\n";
           system("pause");
           system("cls");
           welcomePage();
}

void menu(){
            system("cls");
            SetConsoleTextAttribute(hConsole, 10);
            cout<<"\n\t-------------------------------------------------------------------------"<<endl;
            cout<<"\t\t\tSTUDENT MANAGEMENT SYSTEM MAIN MENU"<<endl;
            cout<<"\t-------------------------------------------------------------------------\n"<<endl;
            cout<< "[1] ADD NEW STUDENT"<<endl;
            cout<< "[2] SEARRCH STUDENT"<<endl;
            cout<< "[3] UPDATE STUDENT"<<endl;
            cout<< "[4] SHOW ALL STUDENT"<<endl;
            cout<< "[5] DELETE STUDENT"<<endl;
            cout<< "[6] ABOUT ME"<<endl;
            cout<< "[7] LOG OUT"<<endl;
            cout<< "[8] EXIT"<<endl;
             cout<<"-----------------------------"<<endl<<endl;

             try{
                int choice;
                cout<<"Enter Your Choice: ";
                cin>>choice;
                    if(cin.fail()){
                        cin.clear();
                        cin.ignore(10, '\n');
                        throw runtime_error("-> Invalid Input");
                    }
            switch(choice){

                case 1: addNewStudent(); break;
                case 2: searchStudent(); break ;
                case 3: modifyStudent(); break ;
                case 4: readFromFile(); break;
                case 5: deleteStudent(); break;
                case 6: aboutMe(); break ;
                case 7: system("cls"); welcomePage(); break;
                case 8: exit(0);
                default: cout<<"-> Wrong Choice"<<endl;
            }
        }
        catch(runtime_error& er){
            SetConsoleTextAttribute(hConsole, 4);
            cout<<er.what()<<". Please input as an Integer.\nThank You...\n"<<endl;
            SetConsoleTextAttribute(hConsole, 10);
        }
                 system("pause"); system("cls");
                 menu();
}

void welcomePage(){
    system("cls");
    cout<<endl;
    cout<<"\t\t\t\t"<<"*****************************************************************"<<endl;
    cout<<"\t\t\t\t*"<<"\t\t\t\t\t\t\t\t*"<<endl;
    cout<<"\t\t\t\t*"<<"\t\t\t\t\t\t\t\t*"<<endl;
    cout<<"\t\t\t\t*"<<"\t---------------------------------------------\t\t*"<<endl;
    cout<<"\t\t\t\t*"<<"\t\t\t\t\t\t\t\t*"<<endl;
    cout<<"\t\t\t\t*"<<"\t    Welcome To Student Management System\t\t*"<<endl;
    cout<<"\t\t\t\t*"<<"\t\t\t\t\t\t\t\t*"<<endl;
    cout<<"\t\t\t\t*"<<"\t---------------------------------------------\t\t*"<<endl;
    cout<<"\t\t\t\t*"<<"\t\t\t\t\t\t\t\t*"<<endl;
    cout<<"\t\t\t\t*"<<"\t\t\t\t\t\t\t\t*"<<endl;
    cout<<"\t\t\t\t"<<"*****************************************************************"<<endl<<endl;

    cout<<"\t\t\t\t\t\t"<<setw(30)<<left<<"Green University Of Bangladesh"<<endl;
    SetConsoleTextAttribute(hConsole, 11);
    cout<<"\t\t\t\t\t\t"<<setw(18)<<left<<"Md. Nazmul Hossain"<<endl;
    SetConsoleTextAttribute(hConsole, 10);
     cout<<endl<<endl;
     signUp();
}

void heading(int h=0){
    if(h == 1){
        system("cls");
        SetConsoleTextAttribute(hConsole, 9);
        cout<<"\n\t\t\t\t---------------------------------------------------\n";
        cout<<"\t\t\t\t\tSTUDENT MANAGEMENT SYSTEM";
        cout<<"\n\t\t\t\t\tStore Record by using C++";
        SetConsoleTextAttribute(hConsole, 12);
        cout<<"\n\t\t\t\t\t\t\t\tDevoloped by Nazmul";
        SetConsoleTextAttribute(hConsole, 9);
        cout<<"\n\t\t\t\t---------------------------------------------------\n\n\n"<<endl;
        return ;
    }
        SetConsoleTextAttribute(hConsole, 11);
        cout<<"|====================================================================================================|";
        cout<<endl;
        cout<<setw(24)<<left<<"|    Name";
        cout<<setw(24)<<left<<"|    ID";
        cout<<setw(17)<<left<<"|    Department    ";
        cout<<setw(17)<<left<<"|      Semester";
        cout<<setw(17)<<right<<"|    CGPA"<<"|"<<endl;
        cout<<"|====================================================================================================|";
        cout<<endl;
        SetConsoleTextAttribute(hConsole, 10);
}

void showStudent(){
    cout<<"Name: "<<st.name<<endl;
    cout<<"ID: "<<st.id<<endl;
    cout<<"Department: "<<st.department<<endl;
    cout<<"Semester: "<<st.semester<<endl;
    cout<<"CGPA: "<<st.cgpa<<endl<<endl;
}

void aboutMe(){
    system("cls");
    heading(1);
    SetConsoleTextAttribute(hConsole, 14);
    cout<<"------------------------------"<<endl;
    cout<<"\tABOUT ME PAGE"<<endl;
    cout<<"------------------------------"<<endl;

    cout<<"\n\t-------------------------------------------------------------------"<<endl;
    cout<<"\n\t\tAbout Me Features is coming soon...\n\t\tTill day stay with us...\n\t\t\t\t\tThank You...\n\t\t\t\t\t\t\t Md. Nazmul Hossain"<<endl;
    cout<<"\t--------------------------------------------------------------------"<<endl;
}

int totalStudent(){
    ifstream fin;
    fin.open("record.csv");
    if(!fin.is_open()){
        return 0;
    }
    while(fin.read((char*)&st, sizeof(st))){
           tStudent++;
    }
    fin.close();
    return tStudent;
}

void loadingbar(){
    for (int i=15;i<=100;i+=5){
            system("cls");
            SetConsoleTextAttribute(hConsole, 14);
            cout<<"\n\n\n\n\n\n\n\t\t\t";
            cout<<i<<" %% Loading...\n\n\t\t";
            cout<<" ";
            for (int j=0; j<i;j+=2){
                cout<<" ";
            }
            Sleep(50);
            if(i==90 || i==50 || i==96 || i==83){
                Sleep(50);
            }
	}
	Sleep(200);
	cout<<endl;
	SetConsoleTextAttribute(hConsole, 10);
}

int main(){
    system("color 0A");
    welcomePage();
}
