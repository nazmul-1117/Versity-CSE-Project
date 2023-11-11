using namespace std;

void Student :: addNewStudent(){
    if(tStudent < 0){
         tStudent = totalStudent();
    }
    id = 223002000 + tStudent;

    system("cls");
    cout<<"\n\tNew Student Add"<<endl;
    cout<<"\t-----------------------------------"<<endl;

    cin.ignore();
    cout<<"\n\tEnter New Student Name: ";
    getline(cin, name);

    cout<<"\n\tEnter Student Department: ";
    getline(cin, department);

    cout<<"\n\tEnter Student Semester: ";
    cin>>semester;

    if(semester == 1){
        cout<<"\n\tEnter Student GPA: ";
        cin>>gpa;
    }
    else{
        cout<<"\n\tEnter Student CGPA: ";
        cin>>cgpa;
    }

    writeToFile();
    tStudent++;
    cout<<"\n\tThanks "<<name<<" for admission on our University"<<endl;
    cout<<"\n\tYour ID Number is: "<<id<<endl;

}

void Student :: modifyStudent(){
    cout<<"\n\t-------------------------------------------------------------------"<<endl;
    cout<<"\n\t\tStudents Modify Features is coming soon...\n\t\tTill day stay with us...\n\t\t\t\t\tThank You...\n\t\t\t\t\t\t\t Md. Nazmul Hossain"<<endl;
    cout<<"\t--------------------------------------------------------------------"<<endl;
}

void Student :: deleteStudent(){
     cout<<"\n\t-------------------------------------------------------------------"<<endl;
    cout<<"\n\t\tStudent Delete Features is coming soon...\n\t\tTill day stay with us...\n\t\t\t\t\tThank You...\n\t\t\t\t\t\t\t Md. Nazmul Hossain"<<endl;
     cout<<"\t-------------------------------------------------------------------"<<endl;
    //ifstream fin;
    //fin.open("record.bin", ios :: in | ios :: binary);
    //fin.close();
   /* if(remove("record.bin") == 0){
        cout<<"Delete Succed"<<endl;
        return ;
    }
    cout<<"Delete Not Succed"<<endl;*/
}

void Student :: searchStudent(){
    cout<<"Search Student"<<endl;
    cout<<"---------------------------"<<endl;

    long long int idNo;
    bool notFound = false;
    cout<<"\nEnter ID No: ";
    cin>>idNo; cin.ignore();

    ifstream fin;
    fin.open("record.bin", ios :: in | ios :: binary);
    if(!fin.is_open()){
        cout<<"\n\tFile Unable To Open\n"<<endl;
        return ;
    }

    while(fin.read((char*)&st, sizeof(Student))){

        if(idNo == id){
            cout<<"\nStudent Found..."<<endl;
            showStudent();
            fin.close();
            return ;
        }

        notFound = true;
    }
    fin.close();

    if(notFound){
        cout<<"ID not matched"<<endl;
    }
}
