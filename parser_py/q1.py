import sys
next = None
    
def S():
    print "\n\nSource :",
    scan()
    if next == '$':
        sys.exit(1)
    if next == 'b':
    	scan()
    	S1()
    elif A():
    	if next== 'a':
    		scan()
    	elif next== 'c':
    		scan()
    	else:
    		print ", REJECTED"
    		exit(0)
    elif B():
    	if next== 'c':
    		scan()
    else:
    	print ", REJECTED"
    	exit(0)
    		
    if next == '$':
        sys.stdout.write(", ACCEPTED"),
    else:
        print ", REJECTED"
    	exit(0)
    	

def A():
    if next == 'd':
        scan()
        return True


def B():
    if next == 'd':
        scan()
        return True

def S1():
    if A():
        if next=='c':
        	scan()
        	return True
        elif next == 'a':
        	scan()
        	return True
    elif B():
		if next=='a':
			scan()
			return True
    else:
        print ", REJECTED"
    	exit(0)


def getch():
    c = sys.stdin.read(1)
    if len(c) > 0:
        sys.stdout.write(c) # echo input
        return c
    else:
        return None

def scan():
    global next
    next = getch()
    if next == None:
        sys.exit(1)
    while next.isspace(): # skip whitesp
        next = getch()

while True:
    S(),
