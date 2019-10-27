#coded :u4cse16115
import sys
global string,i,l
i=0
l=''
string=''

def E():
	global l
	if l=='':
		getch()

	if T():
		A()
	else:
		print "REJECTED"
		exit(0)

def A():
	if l=='+':
		match('+')
		T()
		A()
	elif l ==' ':
		match(' ')
	else:
		print "REJECTED"
		exit(0)

def T():
	if F():
		B()
		return True

def F():
	if l=='(':
		match('(')
		E()
		match(')')
		return True
	elif l=='i':
		match('i')
		return True
	else:
		print "REJECTED"
		exit(0)

def B():
	if l=='*':
		match('*')
		F()
		B()
	elif l==' ':
		match(' ')
	else:
		print "REJECTED"
		exit(0)

def getch():
	global i,l
	if i<len(string):
		l=string[i]
		i+=1

def match(c):
	if c == l:
		getch()
    	return
	print "REJECTED"
	exit(0)

print "Note : ' ' is the input to be given for epsilon.\n'id' here is 'i' in input.\n"

while True:
	if string=='':
		string=raw_input("Enter input string (Type exit to quit.) : ")
	if string=='exit':
		exit(0)
	E()
	if l=='$':
		print "ACCEPTED"
		l=''
		i=0
		string=''