#code :u4cse16115

import sys
global string,i,l
i=0
l=''
string=''

def S():
	global l
	if l=='':
		getch()
	if l=='a':
		match('a')
	elif l=='^':
		match('^')
	elif l =='(':
		match('(')
		T()
		match(')')
	else:
		print "REJECTED"
		exit(0)


def T():
	S()
	T1()


def T1():
	global l
	if l==',':
		match(',')
		S()
		T1()
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

print "Note : ' ' is the input to be given for epsilon.\n"

while True:
	if string=='':
		string=raw_input("Enter input string (Type exit to quit.) : ")
	if string=='exit':
		exit(0)
	S()
	if l=='$':
		print "ACCEPTED"
		l=''
		i=0
		string=''