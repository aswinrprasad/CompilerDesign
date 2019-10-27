import java.util.Scanner;
import java.lang.String;
class Main
{
	public static void main(String[] args)
	{
		Scanner s =  new Scanner(System.in);
		String st = s.next();
		checkIdentifier(st);
		checkFunctionName(st);
		checkifkeyword(st);
		checkComments(st);
	}
	static void checkIdentifier(String st)
	{
		int flag = 1;
		if(!((st.charAt(0) >=97 && st.charAt(0) <= 122) || (st.charAt(0) >= 65 && st.charAt(0) <= 90)))
		{
			System.out.println("Not an identifier");
			flag = 0;
		}
		char c;
		for(int i=1;i<st.length();i++)
		{
			if(flag==0)
			{
				break;
			}
			c = st.charAt(i);
			if(!Character.isDigit(c) && !((c >=97 && c <= 122) || (c >= 65 && c <= 90)) && c!='_')
			{
				System.out.println("Not an identifier");
				flag = 0;
				break;
			}
		}
		if(flag == 1)
		System.out.println("Yes it is an identifier");
	}
	static void checkFunctionName(String st)
	{
		int flag = 1, flag2=0;
		int q = 1;
		if(!((st.charAt(0) >=97 && st.charAt(0) <= 122) || (st.charAt(0) >= 65 && st.charAt(0) <= 90)))
		{
			System.out.println("Not an function name");
			q = 0;
		}
		char c;
		if(q==1)
		{
			for(int i=1;i<st.length();i++)
			{
				c = st.charAt(i);
				if(!Character.isDigit(c) && !((c >=97 && c <= 122) || (c >= 65 && c <= 90)) && c!='_')
				{
					if(st.charAt(i) == '(' || st.charAt(i) == ')')
					{
						if(st.charAt(st.length()-1) == ')')
						flag2 =1;
						break;
					}
					else
					{	
						flag = 0;
					}
				}
			
			}
			if(flag == 1 && flag2 == 1)
				System.out.println("Yes it is an function name");
			else
			{
			System.out.println("Not an function name");
			}
		}
		
	}
	static void checkifkeyword(String st)
	{
		int flag = 0;
		int b = 0;
		int q=1;
		if(!(st.charAt(0) == 'i' && st.charAt(1) == 'f' ))
		{
			System.out.println("Not an if keyword");
			q = 0;
		}
		char c;
		if(q==1)
		{
			for(int i=1;i<st.length();i++)
			{
				c = st.charAt(i);
				if(c == '(')
				{
					b++;
				}
				if(c==')' && b==1)
				{
					b--;
					flag = 1;
				}
				if(c=='{')
				{
					flag = 0;
					if(b==1)
					{
						break;
					}
					else
					{
						if(st.charAt(st.length()-1) == '}')
						{
							flag = 1;
						}
					}
				}
			}
			if(flag == 1)
				System.out.println("Yes it is an if keyword");
			else
			{
			System.out.println("Not an if keyword");
			}	
		}	
	}

	static void checkComments(String st)
	{
		String c = st.substring(0,2);
		String ce = st.substring(st.length()-2, st.length());
		if(c.equals("//"))
		{
			System.out.println("It is a single line comment");
		}
		else if (c.equals("/*"))
		{
			if(ce.equals("*/"))
			{
				System.out.println("It is an multiline comment");
			}
			else
			{
				System.out.println("It is an invalid comment");
			}
		}
		else
		{
			System.out.println("Not an comment");
		}
		
	}
	
}
