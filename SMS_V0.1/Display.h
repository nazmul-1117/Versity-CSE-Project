using namespace std;

void heading(void){
    cout<<endl;
    cout<<setw(24)<<left<<"|    Name";
    cout<<setw(24)<<left<<"|    ID";
    cout<<setw(17)<<left<<"|    Department";
    cout<<setw(17)<<left<<"|    Semester";
    cout<<setw(17)<<right<<"|    CGPA"<<"|"<<endl;
    cout<<"|==================================================================================================|";
    cout<<endl;
}

void Student :: displayData(void){
    cout<<setw(24)<<left<<name<<"| ";
    cout<<setw(22)<<left<<id<<"|";
    cout<<setw(24)<<left<<department<<"|";
    cout<<setw(10)<<right<<cgpa<<"|";

    cout<<endl;
}

void Student :: showStudent(){
    cout<<"\n\tStudent Details"<<endl;
    cout<<"\n\t-----------------------"<<endl;

    cout<<"Name: "<<name<<endl;
    cout<<"ID: "<<id<<endl;
    cout<<"Department: "<<department<<endl;
    cout<<"Semester: "<<semester<<endl;
    cout<<"CGPA: "<<cgpa<<endl;

    cout<<endl;
}

void Student :: aboutMe(){
     cout<<"\n\t-------------------------------------------------------------------"<<endl;
    cout<<"\n\t\tAbout Me Features is coming soon...\n\t\tTill day stay with us...\n\t\t\t\t\tThank You...\n\t\t\t\t\t\nMd. Nazmul Hossain"<<endl;
    cout<<"\n\t--------------------------------------------------------------------"<<endl;
}
