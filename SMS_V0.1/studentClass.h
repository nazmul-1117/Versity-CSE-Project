using namespace std;

class Student{
    string name;
    long long int id = 223002000;
    string department;
    double cgpa=0, gpa = 0;
    int semester;

    public:
            void addNewStudent(void);
            void searchStudent(void);
            void modifyStudent(void);
            void showStudent(void);
            void deleteStudent(void);
            void writeToFile(void);
            void readFromFile(void);
            void aboutMe(void);

            void resisterNewStudent();
            void setData(void);
            void displayData(void);
}st;
