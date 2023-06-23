#This is a MIPS Implentation of the Zibonacci Sequence program
#Micah Rose-Mighty
#6498935
#2020/12/03
.data
msg: .asciiz "Zibonacci 1-20\n "
space: .asciiz " "
S:
.text
.globl main
main:
	li $v0, 4
	la $a0, msg
	syscall #print msg to the user stating the program"s function
	li $s0, 1 #start point of the program
	la $s2, S
	
	while:
		move $a0, $s0 #save for procedure call
		jal zib #procedure call
		move $a0, $v0 #save for printing value
		li $v0, 1 #actually printing integer value
		syscall
		li $v0, 4
		la $a0, space #just for creating space between the integer numbers being printed
		syscall
		la $t1, 20 #marks the end of our loop since this is 1-20 Zibonacci Sequence
		beq $t1, $s0, endwhile #branches to the termination procedure called endwhile once we have reached 20
		addi $s0, $s0, 1 #increments counter by 1 after each run
		j while #immediately jumps back to the while loop
	endwhile: #termination procedure
	li $v0, 10
	syscall

zib: #main Zibonacci Sequence Procedure

	addi $29, $29, -8 #creates the activation record and updates the stack for use within this program
	sw $ra, 0($29) # saves return address to the stack
	sw $s0, 4($29) #saves the parameter value to the stack for when the procedure is returned
	
	add $s0, $a0, $zero #retrieves the argument
	
	addi $t1, $zero, 1
	beq $s0, $zero, return1  #branch to procedure called return1 if (n == 0)
	beq $s0, $t1, return1 #branch to procedure called return1 if ( n == 1)
	addi $t2, $zero, 2
	beq $s0, $t2, return2 #branch to procedure called return2 if (n == 2)
	
	remu $t3, $s0, $t2 #finds remainder of n/2 otherwise known as n%2
	beq $t3, $t1, odd #branch to odd procedure if (n%2 == 1)
	beq $t3, $zero, even #branch to even procedure if (n%2 == 0)
	
	exitzib: #proceudre to properly remove items from the stack and bring back stack pointer then return to the return address
	
		lw $ra, 0($29) #read registers from the stack
		lw $s0, 4($29)
		addi $29, $29, 8 #bring stack pointer back to correct place in memory
		jr $ra
	
	odd:  #procedure that must be run if n is an odd number greater than or equal to 3
		addi $s0, $s0, -1 #n-1
		div $s0, $s0, $t2 #(n-1)/2
		move $a0, $s0 #save the result as a parameter
		jal zib
		move $t1, $s0 #save temporary value
		add $s0, $zero, $v0 #retrieve return of previous call
		addi $a0, $t1, -1 #(n-1)
		jal zib
		add $s0, $s0, $v0 #zib((n-1)/2) + zib((n-1)/2-1)
		addi $v0, $s0, 1 # +1
		j exitzib
		
	even: #procedure that must be run if n is an even number greater than or equal to 4
		div $s0, $s0, $t2 #n/2
		move $a0, $s0
		jal zib
		addi $t1, $s0, 1 #n/2+1
		add $s0, $zero, $v0
		move $a0, $t1
		jal zib
		add $s0, $s0, $v0
		addi $v0, $s0, 1
		j exitzib
	return2:
		li $v0, 2
		j exitzib
	return1:
		li $v0,1
		j exitzib