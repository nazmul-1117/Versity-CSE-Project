#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <string.h>

void display();
void addStudent();
void showStudent();
void searchStudent();
void updateStudent();
void deleteStudent();
void aboutUs();

struct Student
{
    char name[30];
    char id[12];
    char department[5];
    float cgpa;

} student;

 FILE *file;
long  std_size = sizeof (student);

int main()
{
    int choice;

    display();

    while(1)
    {
        printf("\n\t\t\t    Main Menu\n");
        printf("\t\t=================================\n\n");
        printf("\t\t[1] Add a New Student\n");
        printf("\t\t[2] Show All Student\n");
        printf("\t\t[3] Search by ID\n");
        printf("\t\t[4] Update Students Details\n");
        printf("\t\t[5] Delete a Student\n");
        printf("\t\t[6] About Us\n");
        printf("\t\t[7] Exit\n\n");
        printf("\t\t=================================\n\n");
        printf("\t\tEnter Your Choice ");
        scanf("%d", &choice);

        switch(choice)
        {
        case 1:
            addStudent();
            break;
        case 2:
            showStudent();
            break;
        case 3:
            searchStudent();
            break;
        case 4:
           updateStudent();
            break;
        case 5:
          deleteStudent();
            break;
        case 6:
           aboutUs();
            break;
        case 7:
           exit(1);
            break;
        default:
                printf("Please input valid option.");
        }
    }
    return 0;
}

void display()
{
    printf("\t\t\t\t\t\t\t");
    printf("Student Record Management System\n");
    printf("\t\t\t\t\t\t\t");
    printf("Green University Of Bangladesh\n");
    printf("\t=================================");

    printf("\n\n\n\n\n\tPress any Key to Continue.");
    getch();
    system("cls");
}

void addStudent()
{
    system("cls");
    char option = 'y';
    file = fopen("record.txt", "ab+");

    while(option == 'y')
    {
                    printf("\n\t\t\t<=====Input a New Student=====>");
                    printf("\n\t\t------------------------------------------------\n\n");
                    printf("Input Student Name: ");
                    fflush(stdin);
                    gets(student.name);

                    printf("Input ID Number: ");
                    scanf("%s", student.id);
                    fflush(stdin);

                    printf("Enter Your Department: ");
                    scanf("%s", student.department);
                    fflush(stdin);

                    printf("Input Your CGPA: ");
                    scanf("%f", &student.cgpa);

                    fwrite(&student, std_size, 1, file);
                    fflush(stdin);

                    printf("\n\nA new Student has been successfully added. Thank You\n\n");
                    printf("Want to add more Students? 'y' or 'n' ");
                    option = getch();
                    fflush(stdin);
                    system("cls");
    }
     fclose(file);
    fflush(stdin);
    system("cls");
}

void showStudent()
{
    int sl = 1;
    system("cls");
    printf("\n\t\t\t\t<===VIEW RECORDS==>");
    printf("\n\t\t\t----------------------------------\n\n");
    printf(" %-15s %-28s %-15s %-22s %-20s",  "Sl No", "Name", "ID", "Department", "CGPA");
    printf("\n--------------------------------------------------------------------------------------------\n\n");

    file = fopen("record.txt", "rb");

    while(fread(&student, std_size, 1, file) == 1)
    {
        printf(" %-13d %-28s %-20s %-20s %-20.2f\n", sl, student.name, student.id, student.department, student.cgpa);
        sl++;
    }
    fclose(file);
    printf("\n\nPress any Key to Continue.");
    getch();
    system("cls");
}

void searchStudent()
{
    system("cls");
        char _id[12];
        printf("\n\nSearch by ID Number. \n");
        printf("Enter ID: ");
        fflush(stdin);
        scanf("%s", _id);

        file = fopen("record.txt", "rb");
        int result = 0;

       while(fread(&student, std_size, 1, file) == 1)
       {
           if(strcmpi(student.id, _id) == 0)
           {
               printf("\nCongratulation. The Student found.\n\n");
               printf("Student Details.\n");
               printf("----------------\n\n");
               printf("Name: %s\n", student.name);
               printf("ID: %s\n", student.id);
               printf("Department: %s\n", student.department);
               printf("CGPA: %.2f\n", student.cgpa);

               fclose(file);
               result ++;
               break;
           }
       }

       if(result==0)
       {
            printf("\nSorry!, Student data not available.\n");
        }
        fflush(stdin);
        printf("\n\n\nPress any key to continue.");
        getch();
        system("cls");
}

void updateStudent()
{
    system("cls");
        char _id[12];
        printf("\n\nUpdate by ID Number. \n");
        printf("Enter ID: ");
        fflush(stdin);
        scanf("%s", _id);

        file = fopen("record.txt", "rb+");
        int r = 0;

       while(fread(&student, std_size, 1, file) == 1)
       {
           if(strcmpi(student.id, _id) == 0)
           {
                    printf("\nUpdate Student.\n");
                    printf("Student Old Name is: %-15s\n\n", student.name);
                    printf("Input New Name: ");   //name update
                    fflush(stdin);
                    gets(student.name);

                    printf("Input New ID Number: ");    //id
                    scanf("%s", student.id);
                    fflush(stdin);

                    printf("Input New Department: ");      //dept
                    scanf("%s", student.department);
                    fflush(stdin);

                    printf("Input New CGPA: ");        //cgpa
                    scanf("%f", &student.cgpa);

                    fseek(file, -std_size, 1);
                    fwrite(&student, std_size, 1, file);

                    fflush(stdin);
                    fclose(file);
                    printf("\n\nStudent Update completed successfully...\n\n");
                    fclose(file);
                    r++;
               break;
           }
       }
       if(r==0)
           {
                printf("\nSorry!, Student data not available.\n");
           }
      printf("\n\nPress any key to continue.");
     getch();
     system("cls");
}

void deleteStudent()
{
    system("cls");
   char _id[12];
        printf("\nDelete by ID Number. \n");
        printf("Enter ID: ");
        fflush(stdin);
        scanf("%s", _id);

        FILE *ft;
        file = fopen("record.txt", "rb+");
        ft = fopen("Temp.txt", "wb+");
        int r=0;

       while(fread(&student, std_size, 1, file) == 1)
       {
           if(strcmpi(student.id, _id) != 0)
           {
                fwrite(&student, std_size, 1, ft);
           }
       }

       fclose(file);
       fclose(ft);
       remove("record.txt");
       rename("Temp.txt", "record.txt");

       printf("\n\nPress any key to continue.");
       getch();
       system("cls");
}

void aboutUs()
{
     struct Student nazmul, fuad;
     system("cls");

     strcpy(nazmul.name, "Md. Nazmul Hossain");
     strcpy(nazmul.id,        "223002089");
     strcpy(nazmul.department, "CSE");

     strcpy(fuad.name, "Abdullah Al  Fuad");
     strcpy(fuad.id,  "223002080");
     strcpy(fuad.department, "CSE");

     printf("\n\t\t\t%-50s", "<=====Developer Details=====>\n");
     printf("\t-----------------------------\n");
     printf("\n\tName\t\t\t  ID\t\t\tDepartment\t\n");
     printf("==================================================================\n");
     printf("\n%s\t\t%s\t\t%s\n\n", nazmul.name, nazmul.id, nazmul.department);
     printf("%s\t\t%s\t\t%s\n\n", fuad.name, fuad.id, fuad.department);

     printf("\n\n\n\nPress any Key to Continue.");
     getch();
     system("cls;");
}
