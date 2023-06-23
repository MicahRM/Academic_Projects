#This is a MIPS Implentation of the temperature conversion program using floating point numbers
#Micah Rose-Mighty
#6498935
#2020/11/17

#declaration of global data to ease programming and allow visualization of actual addressed

.globl greeting
.globl prompt
.globl text
.globl cQuestion
.globl greeting1
.globl prompt1
.globl text1
.globl text2
.globl greeting2
.globl prompt2

.data
CorForK:
    .asciiz "\n input c (Celsius) or f (Fahrenheit) or k (Kelvin)"
answer:
    .space 256
again:
    .asciiz "\n Again? (y or n) "
greeting1:
    .asciiz "\n This program converts Fahrenheit to Celsius and Kelvin \n\n"
prompt1:    
    .asciiz "\n Enter a Floating Point Fahrenheit temperature: "
text1:
    .asciiz "\n Celsius = "
text2:
    .asciiz "\n Kelvin = "
greeting:
    .asciiz "\n This program converts Celsius to Fahrenheit and Kelvin \n\n"
prompt:
    .asciiz "\n Enter a Floating Point Celsius temperature: "
text:
    .asciiz " \n Fahrenheit = "
greeting2:
    .asciiz "\n This program converts Kelvin to Celsius and Fahrenheit \n\n"
prompt2:
    .asciiz "\n Enter an Floating Point Kelvin temperature: "
nine: 
    .double 9.0
five:
    .double 5.0
thirtytwo:
    .double 32.0
twohundredseventythree:
    .double 273.15    


    
.text

cQuestion:
    li $v0, 4         #Prompts the user to input either c,f or k based upon the temperature unit they want to convert
    la $a0, CorForK
    syscall
    
    la $a0, answer
    li $a1, 3
    li $v0, 8
    syscall
    
    lb $t4, 0($a0)
    
    beq $t4, 'c', main #branch to celsius conversion
    beq $t4, 'f', main1 #branch to fahrenheit conversion
    beq $t4, 'k', main2 #branch to kelvin conversion
    j cQuestion
    
    
main:  #this procedure runs the celsius conversion

	ori $v0, $0, 4 #Displays the celsius welcome
	la $a0, greeting
	syscall
	
	
	ori $v0, $0, 4 #Displays the prompt to the user to enter a celsius value
	la $a0, prompt
	syscall
	
	ori $v0, $0, 7 #Reads the user given floating point number
	syscall
	
	ldc1 $f2, nine  #Converts celsius to fahrenheit
	mul.d $f2, $f2, $f0 #9*c
	ldc1 $f4, five
	div.d $f2, $f2, $f4 #9*c/5
	ldc1 $f6, thirtytwo
	add.d $f2, $f2, $f6 #9*c/5+32
	
	ldc1 $f8, twohundredseventythree #converts Celsius to Kelvin
	add.d $f8, $f0, $f8
	
	ori $v0, $0, 4 #Displays the text corresponding to fahrenheit conversion
	la $a0, text
	syscall 
	
	ori $v0, $0, 3 #Displays the result of celsius to fahrenheit conversion
	mov.d $f12, $f2
	syscall
	
	ori $v0, $0, 4 #Displays the text2 corresponding to kelvin conversion
	la $a0, text2
	syscall 
	
	ori $v0, $0, 3 #Displays the result of celsius to kelvin conversion
	mov.d $f12, $f8
	syscall
	
	j continue #jumps to continue procedure instead of immediate exit
	
main1:

	ori $v0, $0, 4 #Displays the  fahrenheit welcome
	la $a0, greeting1
	syscall
	
	ori $v0, $0, 4 #Displays the prompt telling the user ti input Fahrenheit temperature
	la $a0, prompt1
	syscall
	
	ori $v0, $0, 7 #Reads the user input as a given floating point number
	syscall
	
	ldc1 $f2, nine #converts the fahrenheit temperature to celsius
	ldc1 $f4, five
	ldc1 $f6, thirtytwo
	sub.d $f6, $f0, $f6 #f-32
	mul.d $f4, $f4, $f6 #5*(f-32)
	div.d $f2, $f4, $f2 #5*(f-32)/9

	ldc1 $f8, twohundredseventythree #Converts the fahrenheit temperature to kelvin using the previously calculated celsius temperature
	add.d $f8, $f2, $f8
	
	ori $v0, $0, 4 #Displays the text1 corresponding to the fahrentheit to celsius conversion
	la $a0, text1
	syscall
	
	ori $v0, $0, 3 #Displays the result of the Fahrenheit to celsius conversion
	mov.d $f12, $f2
	syscall
	
	ori $v0, $0, 4 #Displays the text2 corrsponding to the fahrenheit to kelvin conversion
	la $a0, text2
	syscall
	
	ori $v0, $0, 3 #Displays the Kelvin result after the fahrenheit to kelvin conversion
	mov.d $f12, $f8
	syscall
	
	j continue #jumps to continue procedure rather than immediate exit\
main2:

	
	ori $v0, $0, 4 #Displays the kelvin welcome
	la $a0, greeting2
	syscall
	
	ori $v0, $0, 4 #Displays the prompt for the user to enter a kelvin temperature
	la $a0, prompt2
	syscall
	
	ori $v0, $0, 7 #Reads the user-inputted given floating point number
	syscall
	
	ldc1 $f8, twohundredseventythree  # converts the kelvin temperature to celsius
	sub.d $f8, $f0, $f8 #c-273
	
	ldc1 $f2, nine #converts the kelvin temperature to fahrenheit using the previously calculated celsius temperature
	mul.d $f2, $f8, $f2 #9*c
	ldc1 $f4, five
	div.d $f4, $f2, $f4#9*c/5
	ldc1 $f6, thirtytwo
	add.d $f6, $f4, $f6 #9*c/5+32
	
	ori $v0, $0, 4 #Displays the text1 corresponding to a celsius conversion
	la $a0, text1
	syscall
	
	ori $v0, $0, 3 #Displays the result of the Kelvin to celsius conversion
	mov.d $f12, $f8
	syscall
	
	
	ori $v0, $0, 4 #Displays the text corresponding to a fahrenheit conversion
	la $a0, text
	syscall
	
	ori $v0, $0, 3 #Displays the result of the kelvin to fahrenheit conversion
	mov.d $f12, $f6
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
    
    beq $t4, 'y', cQuestion #ask conversion question again
    
    li $v0, 10 #otherwise finish program.
    syscall

	
	
    
