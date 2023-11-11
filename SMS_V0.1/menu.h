using namespace std;

void menu(void){
            system("cls");
            cout<<"\n\tWelcome to our SMS\n"<<endl;
            cout<<"-----------------------------"<<endl;
            cout<< "[1] ADD NEW STUDENT"<<endl;
            cout<< "[2] SEARRCH STUDENT"<<endl;
            cout<< "[3] MODIFY STUDENT"<<endl;
            cout<< "[4] SHOW ALL STUDENT"<<endl;
            cout<< "[5] DELETE STUDENT"<<endl;
            cout<< "[6] ABOUT ME"<<endl;
            cout<< "[7] LOG OUT"<<endl;
            cout<< "[8] EXIT"<<endl;
             cout<<"-----------------------------"<<endl<<endl;

            char choice;
            cout<<"Enter Your Choice: ";
            cin>>choice;

            switch(choice){
                case '1': {
                        st.addNewStudent();
                        break ;
                    }

                case '2':{
                        st.searchStudent();
                        break ;
                    }

                case '3': {
                        st.modifyStudent();
                        break ;
                    }

                case '4': {
                        st.readFromFile();
                        break ;
                    }

                case '5': {
                        st.deleteStudent();
                        break ;
                    }

                case '6': {
                        st.aboutMe();
                        break ;
                    }

                case '7': {
                         system("cls");
                         welcomePage();
                         break;
                        //cout<<"NULL"<<endl
                    }

                case '8':
                    {
                         exit(0);
                    }
                default:
                    {
                        cout<<"Wrong Choice"<<endl;
                    }
                 }
                 system("pause");
                 system("cls");
                 menu();
}
