import sys
global string, l, i 
i=0
l=''
string=''

def A():
	global l
	if l=='':
		getch()

	if I():
		if l=='=':
			match('=')
			E()
	elif E():
		k=0
	else:
		print "REJECTED"
		exit(0)
def E():
	if P():
		if O():
			P()
			return True
	elif P():
		return True
	else:
		print "REJECTED"
		exit(0)


def O():
	global l
	if l== '+':
		match('+')
		return True
	elif l=='-':
		match('-')
		return True
	elif l=='*':
		match('*')
		if match('*'):
			k=0
		return True
	elif l=='/':
		match('/')
		return True
	else:
		return False

def P():
	global l
	if I():
		return True
	elif L():
		return True
	elif U():
		I()
		return True
	elif U():
		L()
		return True
	elif l=='(':
		match('(')
		E()
		match(')')
		return True
	else :
		print "REJECTED"
		exit(0)

def U():
	global l
	if l == '+':
		match('+')
		return True
	elif l=='-':
		match('-')
		return True
	elif l=='!':
		match('!')
		return True
	else:
		return False

def I():
	if C():
		return True
	elif C():
		I()
		return True
	else:
		return False

def C():
	global l
	if l=='a':
		match('a')
		return True
	elif l=='b':
		match('b')
		return True
	elif l=='c':
		match('c')
		return True
	elif l=='d':
		match('d')
		return True
	elif l=='e':
		match('e')
		return True
	elif l=='f':
		match('f')
		return True
	elif l=='g':
		match('g')
		return True
	elif l=='h':
		match('h')
		return True
	elif l=='i':
		match('i')
		return True
	elif l=='j':
		match('j')
		return True
	elif l=='k':
		match('k')
		return True
	elif l=='l':
		match('l')
		return True
	elif l=='m':
		match('m')
		return True
	elif l=='n':
		match('n')
		return True
	elif l=='o':
		match('o')
		return True
	elif l=='p':
		match('p')
		return True
	elif l=='q':
		match('q')
		return True	
	elif l=='r':
		match('r')
		return True
	elif l=='s':
		match('s')
		return True
	elif l=='t':
		match('t')
		return True
	elif l=='u':
		match('u')
		return True
	elif l=='v':
		match('v')
		return True
	elif l=='w':
		match('w')
		return True
	elif l=='x':
		match('x')
		return True
	elif l=='y':
		match('y')
		return True
	elif l=='z':
		match('z')
		return True
	else:
		return False


def L():
	if D():
		return True
	elif D():
		L()
		return True
	else:
		return False

def D():
	global l
	if l=='0':
		match('0')
		return True
	elif l=='1':
		match('1')
		return True
	elif l=='2':
		match('2')
		return True
	elif l=='3':
		match('3')
		return True
	elif l=='4':
		match('4')
		return True
	elif l=='5':
		match('5')
		return True
	elif l=='6':
		match('6')
		return True
	elif l=='7':
		match('7')
		return True
	elif l=='8':
		match('8')
		return True
	elif l=='9':
		match('9')
		return True
	else:
		return False

	
def getch():
	global l,i
	if i<len(string):
		l=string[i]
		i+=1

def match(c):
	if c == l:
		getch()
		return True
	print "REJECTED"
	exit(0)

while True:
	if string=='':
		string=raw_input("Enter a input string :")
	if string=='exit':
		exit(0)
	A()
	if l == '$':
		print "ACCEPTED"
		l=''
		string=''
		i=0