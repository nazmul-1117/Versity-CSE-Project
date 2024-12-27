
account = {
    "ac_no" : 101,
    "name" : "Md. Nazmul Hossain",
    "age" : 22,
    "address" : {
        "village" : "Shreedharpur",
        "post" : "Tilakpur",
        "district" : "Naogaon",
        "division" : "Rajshahi"
    },

    "total_amount" : 0,
    "deposit" : [],
    "withdraw" : [],
    "active": False
}


def deposit_amount():
    
    print("\t Deposit Amount")

    if account['total_amount'] > 100_000_000:
        # print(f"\t You cannot deposit🙂\n\t Your balance is {account['total_amount']}😏")
        print(f"\t Eto BoroloX kobe hoili?🙂")
    
    else:
        # print(f"\t Enter Your Deposit amount: ")
        a = input("\t Enter Your Deposit Amount: ")

        try:
            a = int(a)

            if a > 0:
                account['total_amount'] += a
                account["deposit"].append(a)
                account["active"] = True
            else:
                print("\t Please enter some $")
                
        except:
            print("\t Enter Valid Amount")

    print(f"\t {a}$ Deposit Successfully Done😍\n\t Thanks For using us🥰\n\n")


def withdraw_amount():
    
    print("\t Withdraw amount")

    if account['total_amount'] <= 0:
        print(f"\t You cannot Withdraw😐\n\t Your balance is {account['total_amount']}😪")
    
    else:
        a = input("\t Enter Your Withdraw Amount: ")

        try:
            a = int(a)

            if a <= account["total_amount"] and a != 0:
                account['total_amount'] -= a
                account["withdraw"].append(a)
                account["active"] = True
                print(f"\t {a}$ Withraw Successfully Done😍\n\t Thanks For using us🥰\n\n")
            else:
                print(f"\t {a}$ Withraw Failed😥")
                print("\t You don't have sufficient balance😐 \n\n")
                
        except:
            print("\t Enter Valid Amount")


def show_amount():

    print("\t Show amount")
    print(f"\t Your Amount is: {account['total_amount']}🤩 \n\n")


def history():

    if account['active']:
        print(f"\t Your Deposit History: {account['deposit']}😏" )
        print(f"\t Your Withdraw History: {account['withdraw']}😣 \n\n" )
    
    else:
        print("\t You haven't any history😂 \n\n")


def __main():
    menu = True
    while menu:

        print("\t Welcome to Our Bank Management System")
        print("\t ***MAIN MENU***")
        print("\t ===========================================")
        print("\t [1] DEPOSIT AMOUNT\n\t [2] WITHDRAW AMOUNT\n\t [3] SHOW AMOUNT\n\t [4] History\n\t [5] EXIT")
        choice = input("\t Enter Your Choice: ")
        print("\t ======================================================================")

        match choice:

            case '1':
                deposit_amount()
            
            case '2':
                withdraw_amount()
            
            case '3':
                show_amount()

            case '4':
                history()

            case '5':
                print("\t Exiting...")
                menu = False
            
            case default:
                print("\t Please enter valid option")




__main();
