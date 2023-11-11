using namespace std;

void Student :: writeToFile(void){
    ofstream fout;
    fout.open("record.bin", ios :: out | ios :: app | ios :: binary);
    if(!fout.is_open()){
        cout<<"\n\tFile Unable To Open\n"<<endl;
    }
    fout.write((char*)&st, sizeof(Student));
    fout.close();
    cout<<"\n\tRecord Save successfully."<<endl;
}

void Student :: readFromFile(void){
    ifstream fin;
    fin.open("record.bin", ios :: in | ios :: binary);
    if(!fin.is_open()){
        cout<<"\n\tFile Unable To Open\n"<<endl;
        return ;
    }
    heading();

    fin.seekg(0);
    while(fin.read((char*)&st, sizeof(Student))){
        cout<<setw(24)<<left<<name<<"| ";
        cout<<setw(22)<<left<<id<<"|";
        cout<<setw(17)<<left<<department<<"|";
        cout<<setw(24)<<left<<semester<<"|";
        cout<<setw(7)<<right<<cgpa<<"|";

        cout<<endl;
    }

    fin.close();

}
