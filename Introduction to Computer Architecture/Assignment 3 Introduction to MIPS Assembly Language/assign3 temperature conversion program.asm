#This is a MIPS Implentation of the temperature conversion program
#Micah Rose-Mighty
#6498935
#2020/11/17

#declaration of global data to ease programming and allow visualization of actual addressed

.globl welcome
.globl prompt
.globl sumText
.globl conversionQuestion
.globl welcome1
.globl prompt1
.globl sumText1
.globl sumText2
.globl welcome2
.globl prompt2

.data
CForFC:
    .asciiz "\n input c (Celsius) or f (Fahrenheit) or k (Kelvin)"
answer:
    .space 256
again:
    .asciiz "\n Again? (y or n) "
welcome1:
    .asciiz "\n This program converts Fahrenheit to Celsius and Kelvin \n\n"
prompt1:    
    .asciiz "\n Enter an Integer Fahrenheit temperature: "
sumText1:
    .asciiz "\n Celsius = "
sumText2:
    .asciiz "\n Kelvin = "
welcome:
    .asciiz "\n This program converts Celsius to Fahrenheit and Kelvin \n\n"
prompt:
    .asciiz "\n Enter an Integer Celsius temperature: "
sumText:
    .asciiz " \n Fahrenheit = "
welcome2:
    .asciiz "\n This program converts Kelvin to Celsius and Fahrenheit \n\n"
prompt2:
    .asciiz "\n Enter an Integer Kelvin temperature: "

    
.text

conversionQuestion:
    li $v0, 4         #Prompts the user to input either c,f or k based upon the temperature unit they want to convert
    la $a0, CForFC
    syscall
    
    la $a0, answer
    li $a1, 3
    li $v0, 8
    syscall
    
    lb $t4, 0($a0)
    
    beq $t4, 'c', main #branch to celsius conversion
    beq $t4, 'f', main1 #branch to fahrenheit conversion
    beq $t4, 'k', main2 #branch to kelvin conversion
    j conversionQuestion
    
    
main:  #this procedure runs the celsius conversion

	ori $v0, $0, 4 #Displays the celsius welcome
	la $a0, welcome
	syscall
	
	
	ori $v0, $0, 4 #Displays the prompt to the user to enter a celsius value
	la $a0, prompt
	syscall
	
	ori $v0, $0, 5 #Reads the user given integer
	syscall
	
	addi $t0, $0, 9  #Converts celsius to fahrenheit
	mult $t0, $v0
	mflo $t0 #9*c
	addi $t1, $0, 5
	div $t0, $t1
	mflo $t0 #9*c/5
	addi $s0, $t0, 32 #9*c/5+32
	
	
	addi $t2, $0, 273 #converts Celsius to Kelvin
	add $t2, $v0, $t2
	
	ori $v0, $0, 4 #Displays the sumText corresponding to fahrenheit conversion
	la $a0, sumText
	syscall 
	
	ori $v0, $0, 1 #Displays the result of celsius to fahrenheit conversion
	add $a0, $s0, $0
	syscall
	
	ori $v0, $0, 4 #Displays the sumText2 corresponding to kelvin conversion
	la $a0, sumText2
	syscall 
	
	ori $v0, $0, 1 #Displays the result of celsius to kelvin conversion
	add $a0, $t2, $0
	syscall
	
	j continue #jumps to continue procedure instead of immediate exit
	
main1:

	ori $v0, $0, 4 #Displays the  fahrenheit welcome
	la $a0, welcome1
	syscall
	
	ori $v0, $0, 4 #Displays the prompt telling the user ti input Fahrenheit temperature
	la $a0, prompt1
	syscall
	
	ori $v0, $0, 5 #Reads the user input as a given integer
	syscall
	
	addi $t1, $0, 9 #converts the fahrenheit temperature to celsius
	addi $t0, $0, 5
	addi $s0, $0, 32
	sub $v0, $v0, $s0 #f-32
	mult $t0, $v0
	mflo $t0 #5*(f-32)
	div $t0, $t1
	mflo $t0 #5*(f-32)/9

	addi $t3, $0, 273 #Converts the fahrenheit temperature to kelvin using the previously calculated celsius temperature
	add $t2, $t0, $t3
	
	ori $v0, $0, 4 #Displays the sumText1 corresponding to the fahrentheit to celsius conversion
	la $a0, sumText1
	syscall
	
	ori $v0, $0, 1 #Displays the result of the Fahrenheit to celsius conversion
	add $a0, $t0, $0
	syscall
	
	ori $v0, $0, 4 #Displays the sumText2 corrsponding to the fahrenheit to kelvin conversion
	la $a0, sumText2
	syscall
	
	ori $v0, $0, 1 #Displays the Kelvin result after the fahrenheit to kelvin conversion
	add $a0, $t2, $0
	syscall
	
	j continue #jumps to continue procedure rather than immediate exit\
main2:

	
	ori $v0, $0, 4 #Displays the kelvin welcome
	la $a0, welcome2
	syscall
	
	ori $v0, $0, 4 #Displays the prompt for the user to enter a kelvin temperature
	la $a0, prompt2
	syscall
	
	ori $v0, $0, 5 #Reads the user-inputted given integer
	syscall
	
	addi $t0, $0, 273  # converts the kelvin temperature to celsius
	sub $t1, $v0, $t0 #c-273
	
	addi $t0, $0, 9 #converts the kelvin temperature to fahrenheit using the previously calculated celsius temperature
	mult $t0, $t1
	mflo $t0 #9*c
	addi $t2, $0, 5
	div $t0, $t2
	mflo $t0 #9*c/5
	addi $s0, $t0, 32 #9*c/5+32
	
	ori $v0, $0, 4 #Displays the sumText1 corresponding to a celsius conversion
	la $a0, sumText1
	syscall
	
	ori $v0, $0, 1 #Displays the result of the Kelvin to celsius conversion
	add $a0, $t1, $0
	syscall
	
	
	ori $v0, $0, 4 #Displays the sumText corresponding to a fahrenheit conversion
	la $a0, sumText
	syscall
	
	ori $v0, $0, 1 #Displays the result of the kelvin to fahrenheit conversion
	add $a0, $s0, $0
	syscall
	
	j continue #jumps to continue procedure rather than immediate exit\
					
continue: #after calculation prompts the user if they would like run another conversion or exit the program
    li $v0, 4
    la $a0, again
    syscall
    
    la $a0, answer
    li $a1, 3
    li $v0, 8
    syscall
    
    lb $t4, 0($a0)
    
    beq $t4, 'y', conversionQuestion #ask conversion question again
    
    li $v0, 10 #otherwise finish program.
    syscall

	
	
    
