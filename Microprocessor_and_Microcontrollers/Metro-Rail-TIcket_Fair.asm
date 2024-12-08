
print_msg macro string
    lea dx, string
    mov ah, 09h
    int 21h
endm

input MACRO
    mov ah, 01h
    int 21h
    
endm

new_line MACRO
    mov ah, 02h
    mov dl, 0dh
    int 12h
    
    mov dl, 0ah
    int 21h
endm

print MACRO n
    mov dl, n
    mov ah, 02h
    int 21h 
endm
    
    

.model small
.stack 100h

.data
    
    ;Home/Main Menu Page
    wlc_msg DB 13, 10, "------------------> Welcome to Metro Rail Ticket Fare Calculator <---------------------$"
    intro_msg DB 13, 10, 13, 10, "This is Mirpur-10 Metro Rail$"                                        
    menu_msg DB 13, 10, 13, 10, "--------Main Menu-------$"
    menu_item1 DB 13, 10, "[1] North Bound.$"
    menu_item2 DB 13, 10, "[2] South Bound.$"
    exit_msg DB 13, 10, "[3] Exit.$"
    
    line_msg DB 13, 10, "-------------------------------$"
    menu_choose_msg DB 13, 10, "What side do you want to go: $"
    
    
    
    
    ;North_Bound Page
    north_intro_msg DB 13, 10, "Welcome to North Side$"
    north_station_fare DB 13, 10, "Northbound Station and Fare$"
    
    ;station name print
        mirpur11 DB 13, 10, "[1] Mirpur 11 --------------------- 20 BDT$"
        pallabi DB 13, 10, "[2] Pallabi --------------------- 20 BDT$"
        uttara_south DB 13, 10,"[3] Uttara South --------------------- 30 BDT$"
        uttara_center DB 13, 10,"[4] Uttara Center --------------------- 30 BDT$"
        uttara_north DB 13, 10,"[5] Uttara North --------------------- 40 BDT$"
    
    north_station_select DB 13, 10, "Which Station you wanna travel: $"
    passenger_number  DB 13, 10, "Enter Passengers Number: $"
    total_fare_msg DB 13, 10, "Total Fare: $"    
        
        
    
    
    
    
    
    
    ;South_Bound Page
    
    


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
            print_msg wlc_msg
            print_msg intro_msg
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
        print_msg mirpur11
        print_msg pallabi
        print_msg uttara_south
        print_msg uttara_center
        print_msg uttara_north
        new_line
        print_msg line_msg
        
        print_msg north_station_select
        input
        mov bl, al
        sub bl, 48
        
        cmp bl, 5
        je Fourty
        
        ;print_msg passenger_number
        ;input
        ;mov cl, al
        ;sub cl, 48
        
        ;mov al, cl
        ;mul bl 
        ;AAM
        
        ;mov cx, ax
        
        ;add cl, 48
        ;add ch, 48
        
        ;print_msg total_fare_msg
        
        ;print ch
        ;print cl
        ;mov bl, 0
        ;print bl
        
        
    northbound endp
    
    
    
    
    
    
    
    
    
    
    ;south bound
    southbound proc
    
    
    southbound endp
    
    
    
    
    digit_calculate proc
        
        Fourty:
            mov bl,4
            
            lea dx, passenger_number             
            mov ah,,09h
            int 21h 
            
            
            
            MOV AH,01h
            INT 21H
            SUB AL,48
             
            
            
            MUL BL 
            AAM 
         
            MOV CX,AX 
            ADD CH,48
            ADD CL,48
            
            LEA DX,total_fare_msg              
            MOV AH,9
            INT 21H
            
            MOV AH,2
            MOV DL,CH
            INT 21H
            
            
            MOV DL,CL
            INT 21H
            
            MOV DL,'0'
            INT 21H 
            
            
            ;FOR /- PRINT
            MOV DL,47
            INT 21H
            MOV DL,45
            INT 21H
    
    digit_calculate endp
    
        
    
    
    Exit:
        mov ah, 4ch
        int 21h
        
end main
