
;string print from variable
print_msg macro string
    lea dx, string
    mov ah, 09h
    int 21h
endm

;take input from user
input MACRO
    mov ah, 01h
    int 21h
    
endm

;new line
new_line MACRO
    mov ah, 02h
    mov dl, 0dh
    int 12h
    
    mov dl, 0ah
    int 21h
endm

;print any character using resiser
print MACRO n
    mov dl, n
    mov ah, 02h
    int 21h 
endm

;calculate total fare and print it
calculator MACRO n
    mov bl,n
    print_msg passenger_number
    
    input
    sub al, 48
    
    mul bl
    aam 

    mov cx, ax
    add ch, 48
    add cl, 48
    
    print_msg total_fare_msg

    print ch
    print cl
    print '0' 
    print_msg bdt_msg
    print_msg line_msg2
endm
    
    

.model small
.stack 100h

.data
    
    ;General
    bdt_msg DB "/- BDT.$"
    
    ;Home/Main Menu Page
    wlc_line DB 13, 10,   " *****************************************************************$"
    wlc_msg DB 13, 10,    " **         Welcome to Metro Rail Ticket Fare Calculator        **$"
    intro_msg DB 13, 10, " **           Welcome to Shewrapara Metro Rail Station          **$"
    course_name DB 13, 10, " **           Microprocessor and Microcontrollers Project       **$"                                         
    developer_name DB 13, 10, " **           Develop by Md. Nazmul Hossain [223002089]         **$"                                         
                                             
    menu_msg DB 13, 10, 13, 10, "                 ***        MAIN MENU         ***$"
    menu_item1 DB 13, 10, "                 [1] North Bound.$"
    menu_item2 DB 13, 10, "                 [2] South Bound.$"
    exit_msg DB 13, 10, "                 [3] Exit.$"
    
    line_msg DB 13, 10, "                 --------------------------------$"
    menu_choose_msg DB 13, 10, "                 Which side do you want to go: $"
    
    line_msg2 DB 13, 10, " ---------------------------------$"
    
     
     
     
    
    
    ;North_Bound Page
    
    
    north_intro_msg DB 13, 10,          " **                   Welcome to North Side                     **$"
    north_bound_station_list DB 13, 10, " **                 North Bound Station List                    **$"                                         
    north_station_fare DB 13, 10,       " **               Northbound Station and Fare                   **$"
    
    ;station name print
        kazipara DB 13, 10, " [1] Kazipara        ------------------------------------   20 BDT$"
        mirpur10 DB 13, 10, " [2] Mirpur-10       ------------------------------------   20 BDT$"
        mirpur11 DB 13, 10, " [3] Mirpur 11       ------------------------------------   20 BDT$"
        pallabi DB 13, 10, " [4] Pallabi         ------------------------------------   30 BDT$"
        uttara_south DB 13, 10," [5] Uttara South    ------------------------------------   40 BDT$"
        uttara_center DB 13, 10," [6] Uttara Center   ------------------------------------   40 BDT$"
        uttara_north DB 13, 10," [7] Uttara North    ------------------------------------   50 BDT$"
    
    north_station_select DB 13, 10, " Which Station you wanna travel: $"
    passenger_number  DB 13, 10, " Enter Passengers Number: $"
    total_fare_msg DB 13, 10, " Total Fare: $"    
   
     
     
     
    
    ;South_Bound Page
    
    south_intro_msg DB 13, 10,          " **                   Welcome to South Side                     **$"
    south_bound_station_list DB 13, 10, " **                 South Bound Station List                    **$"                                         
    south_station_fare DB 13, 10,       " **               Southbound Station and Fare                   **$"
    
    ;station name print
        
        agargaon DB 13, 10," [1] Agargaon                ----------------------------   20 BDT$"
        bijoy_sarani DB 13, 10," [2] Bijoy Sarani            ----------------------------   20 BDT$"
        farmgate DB 13, 10," [3] Farmgate                ----------------------------   20 BDT$"
        kawran_bazar DB 13, 10," [4] Kawran Bazar            ----------------------------   30 BDT$"
        
        shahbagh DB 13, 10," [5] Shahbagh                ----------------------------   40 BDT$"
        dhaka_niversity DB 13, 10," [6] Dhaka University        ----------------------------   40 BDT$"
        bangladesh_secretariat DB 13, 10," [7] Bangladesh Secretariat  ----------------------------   50 BDT$"
        motijheel DB 13, 10," [8] Motijheel               ----------------------------   50 BDT$"
        kamlapur DB 13, 10," [9] Kamlapur                ----------------------------   60 BDT$"
        


.code
    main proc
        
        mov ax, @data
        mov ds, ax
        
        ;reset all resister
        sub ax, ax
        sub bx, bx
        sub cx, cx
        sub dx, dx
        
        Home:
            print_msg wlc_line
            print_msg wlc_msg
            print_msg intro_msg
            print_msg course_name
            print_msg developer_name
            print_msg wlc_line
            
            print_msg menu_msg 
            print_msg line_msg
            print_msg menu_item1
            print_msg menu_item2 
            print_msg exit_msg
            
            print_msg line_msg
            print_msg menu_choose_msg
            input
            mov bl, al
            
            print_msg line_msg
            new_line
            
            sub bl, 48
            
            cmp bl, 1
            je CALL northbound
            
            cmp bl, 2
            je CALL southbound
            
            cmp bl, 3
            je Exit
            
            jmp Home

    main endp    
    
    
    ;north bound
    northbound proc
        
        ;north bound station list
        new_line new_line
        print_msg wlc_line
        print_msg north_intro_msg
        print_msg north_bound_station_list
        print_msg north_station_fare
        print_msg wlc_line
        
        new_line
        print_msg kazipara
        print_msg mirpur10
        print_msg mirpur11
        print_msg pallabi
        print_msg uttara_south
        print_msg uttara_center
        print_msg uttara_north
        new_line
        print_msg line_msg2
        
        print_msg north_station_select
        input
        mov bl, al
        sub bl, 48
        
        cmp bl, 1
        je Twenty
        
        cmp bl, 2
        je Twenty
        
        cmp bl, 3
        je Twenty
        
        cmp bl, 4
        je Thirty
        
        cmp bl, 5
        je Forty
        
        cmp bl, 6
        je Forty
        
        cmp bl, 7
        je Fifty
        
    northbound endp

    
    
    ;south bound
    southbound proc
        
        ;south bound station list
        new_line new_line
        print_msg wlc_line
        print_msg south_intro_msg
        print_msg south_bound_station_list
        print_msg south_station_fare
        print_msg wlc_line
        
        new_line 
        print_msg agargaon
        print_msg bijoy_sarani
        print_msg farmgate
        print_msg kawran_bazar
        print_msg shahbagh
        print_msg dhaka_niversity
        print_msg bangladesh_secretariat
        print_msg motijheel
        print_msg kamlapur
        new_line
        
        print_msg line_msg2
        print_msg north_station_select
        input
        
        mov bl, al
        sub bl, 48
        
        cmp bl, 1
        je Twenty
        
        cmp bl, 2
        je Twenty
        
        cmp bl, 3
        je Twenty
        
        cmp bl, 4
        je Thirty
        
        cmp bl, 5
        je Forty
        
        cmp bl, 6
        je Forty
        
        cmp bl, 7
        je Fifty
        
        cmp bl, 8
        je Fifty
        
        cmp bl, 9
        je Sixty
    
    southbound endp
    
    
    
    
    digit_calculate proc
        
        Twenty:
            calculator 2
            jmp Call_Home_Page
            
        Thirty:
            calculator 3
            jmp Call_Home_Page
            
        Forty:
            calculator 4
            jmp Call_Home_Page
        
        Fifty:
            calculator 5
            jmp Call_Home_Page
            
        Sixty:
            calculator 6
            jmp Call_Home_Page
            
        Seventy:
            calculator 7
            jmp Call_Home_Page
    
    digit_calculate endp
    
        
    Call_Home_Page:
        new_line new_line new_line
        jmp Home
    
    Exit:
        mov ah, 4ch
        int 21h
        
end main
