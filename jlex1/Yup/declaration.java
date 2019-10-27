import java_cup.runtime.Symbol;
import java_cup.runtime.Scanner;


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NOT_ACCEPT,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NOT_ACCEPT,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NOT_ACCEPT,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"0:10,43,0:2,43,0:18,7,11,35,0:5,8,9,4,2,6,3,37,5,34:10,0,1,12,10,13,0:2,36:" +
"26,41,0,42,0:3,26,25,28,14,21,17,32,23,19,40,27,24,40,20,15,30,40,18,31,16," +
"29,33,22,40:3,38,0,39,0:2,44:2")[0];

	private int yy_rmap[] = unpackFromString(1,106,
"0,1:9,2,3,4,5,6,1:6,7:4,8,9,7:5,1:2,10,7:2,1,7:8,11,7,12,13,14,15,16,17,18," +
"19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43," +
"44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68," +
"69")[0];

	private int yy_nxt[][] = unpackFromString(70,45,
"-1,1,2,3,4,5,6,7,8,9,10,46,11,12,13,47,49,51,90,53,76,78,100,47:2,101,47:2," +
"91,47,102,105,47,79,14,48,-1:2,50,-1,47,-1:2,15,16,-1:55,17,-1:44,19,-1:44," +
"20,-1:48,47,21,47:19,-1:2,52,-1:2,47,54,-1:37,14,-1:2,25,-1:21,47:21,-1:2,5" +
"2,-1:2,47,54,-1:37,25,-1:24,26:21,-1:2,52,-1:2,26,-1:45,54,-1:13,18,-1:48,5" +
"6:21,-1,56,-1:3,56,-1:18,47,22,47:19,-1:2,52,-1:2,47,54,-1:37,58,60,-1:23,4" +
"7,55,47:3,23,47:4,92,47:10,-1:2,52,-1:2,47,54,-1:17,26:20,-1:6,26,-1:18,47:" +
"3,24,47:2,57,47:14,-1:2,52,-1:2,47,54,-1:37,62,-1:24,47:4,27,47:16,-1:2,52," +
"-1:2,47,54,-1:17,56:21,32,56,-1:3,56,-1:18,47:2,28,47:18,-1:2,52,-1:2,47,54" +
",-1:9,77,-1:27,58,-1:4,33,-1:19,47:10,29,47:10,-1:2,52,-1:2,47,54,-1:17,60:" +
"21,64,60,-1:3,60,-1:18,30,47:20,-1:2,52,-1:2,47,54,-1:37,62,-1:7,34,-1:16,4" +
"7:4,31,47:16,-1:2,52,-1:2,47,54,-1:9,66,-1:32,37,-1:19,35,47:20,-1:2,52,-1:" +
"2,47,54,-1:38,60,-1:23,47:4,36,47:16,-1:2,52,-1:2,47,54,-1:17,47:2,38,47:18" +
",-1:2,52,-1:2,47,54,-1:17,47:7,39,47:13,-1:2,52,-1:2,47,54,-1:17,47:13,40,4" +
"7:7,-1:2,52,-1:2,47,54,-1:17,47:2,41,47:18,-1:2,52,-1:2,47,54,-1:17,47:2,42" +
",47:18,-1:2,52,-1:2,47,54,-1:17,47:6,43,47:14,-1:2,52,-1:2,47,54,-1:17,47:1" +
"8,44,47:2,-1:2,52,-1:2,47,54,-1:17,47:7,45,47:13,-1:2,52,-1:2,47,54,-1:17,4" +
"7:5,59,47:15,-1:2,52,-1:2,47,54,-1:37,58,-1:24,47:6,61,47:14,-1:2,52,-1:2,4" +
"7,54,-1:17,47:12,63,47:8,-1:2,52,-1:2,47,54,-1:17,47:2,97,47:9,65,47:8,-1:2" +
",52,-1:2,47,54,-1:17,47:12,67,47:8,-1:2,52,-1:2,47,54,-1:17,47:12,68,47:8,-" +
"1:2,52,-1:2,47,54,-1:17,47:10,69,47:10,-1:2,52,-1:2,47,54,-1:17,47:12,70,47" +
":8,-1:2,52,-1:2,47,54,-1:17,47:2,104,47:14,71,47:3,-1:2,52,-1:2,47,54,-1:17" +
",47:6,72,47:14,-1:2,52,-1:2,47,54,-1:17,47:4,73,47:16,-1:2,52,-1:2,47,54,-1" +
":17,47:6,74,47:14,-1:2,52,-1:2,47,54,-1:17,47:15,75,47:5,-1:2,52,-1:2,47,54" +
",-1:17,47:7,80,47:13,-1:2,52,-1:2,47,54,-1:17,47,95,47:7,81,47:11,-1:2,52,-" +
"1:2,47,54,-1:17,47,82,47:19,-1:2,52,-1:2,47,54,-1:17,47:5,83,47:15,-1:2,52," +
"-1:2,47,54,-1:17,47:7,84,47:13,-1:2,52,-1:2,47,54,-1:17,47:6,85,47:14,-1:2," +
"52,-1:2,47,54,-1:17,47:5,86,47:15,-1:2,52,-1:2,47,54,-1:17,47:15,87,47:5,-1" +
":2,52,-1:2,47,54,-1:17,47:5,88,47:15,-1:2,52,-1:2,47,54,-1:17,47:6,89,47:14" +
",-1:2,52,-1:2,47,54,-1:17,47:9,93,47:11,-1:2,52,-1:2,47,54,-1:17,47:4,94,47" +
":16,-1:2,52,-1:2,47,54,-1:17,47:4,96,47:16,-1:2,52,-1:2,47,54,-1:17,47:4,98" +
",47:16,-1:2,52,-1:2,47,54,-1:17,47:5,99,47:15,-1:2,52,-1:2,47,54,-1:17,47:2" +
",103,47:18,-1:2,52,-1:2,47,54,-1:3");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

System.exit(0);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						{System.out.println("LA "+yytext());return new Symbol(sym.SEMI);}
					case -2:
						break;
					case 2:
						{System.out.println("LA "+yytext());return new Symbol(sym.PLUS);}
					case -3:
						break;
					case 3:
						{System.out.println("LA "+yytext());return new Symbol(sym.MINUS);}
					case -4:
						break;
					case 4:
						{System.out.println("MULT "+yytext());return new Symbol(sym.MULT);}
					case -5:
						break;
					case 5:
						{System.out.println("LA "+yytext());return new Symbol(sym.DIV);}
					case -6:
						break;
					case 6:
						{System.out.println("LA "+yytext());return new Symbol(sym.COMMA);}
					case -7:
						break;
					case 7:
						{System.out.println("LA "+yytext());return new Symbol(sym.SPACE);}
					case -8:
						break;
					case 8:
						{System.out.println("OPENBR "+yytext());return new Symbol(sym.OPENBR);}
					case -9:
						break;
					case 9:
						{System.out.println("CLOSEBR "+yytext());return new Symbol(sym.CLOSEBR);}
					case -10:
						break;
					case 10:
						{System.out.println("Equal "+yytext());return new Symbol(sym.EQUALS);}
					case -11:
						break;
					case 11:
						{System.out.println("LT "+yytext());return new Symbol(sym.LT);}
					case -12:
						break;
					case 12:
						{System.out.println("GT "+yytext());return new Symbol(sym.GT);}
					case -13:
						break;
					case 13:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -14:
						break;
					case 14:
						{System.out.println("int "+yytext());return new Symbol(sym.INTEGER,new  Integer(yytext()));}
					case -15:
						break;
					case 15:
						{System.out.println("LA"+ "EOF");return new Symbol(sym.EOFILE);}
					case -16:
						break;
					case 16:
						
					case -17:
						break;
					case 17:
						{System.out.println("EQUAL TO "+yytext());return new Symbol(sym.EQUALTO);}
					case -18:
						break;
					case 18:
						{System.out.println("NOTEQUAL "+yytext());return new Symbol(sym.NOTEQUAL);}
					case -19:
						break;
					case 19:
						{System.out.println("LTE"+yytext());return new Symbol(sym.LTE);}
					case -20:
						break;
					case 20:
						{System.out.println("GTE "+yytext());return new Symbol(sym.GTE);}
					case -21:
						break;
					case 21:
						{System.out.println("DO "+yytext());return new Symbol(sym.DO);}
					case -22:
						break;
					case 22:
						{System.out.println("TO "+yytext());return new Symbol(sym.TO);}
					case -23:
						break;
					case 23:
						{System.out.println("FI "+yytext());return new Symbol(sym.FI);}
					case -24:
						break;
					case 24:
						{System.out.println("IF "+yytext());return new Symbol(sym.IF);}
					case -25:
						break;
					case 25:
						{System.out.println("float "+yytext());return new Symbol(sym.FLOATPOINT,new Integer(yytext()));}
					case -26:
						break;
					case 26:
						{System.out.println("identifier "+yytext());return new Symbol(sym.IDENTIFIER,new String(yytext()));}
					case -27:
						break;
					case 27:
						{System.out.println("FOR "+yytext());return new Symbol(sym.FOR);}
					case -28:
						break;
					case 28:
						{System.out.println("INT "+yytext());return new Symbol(sym.INT);}
					case -29:
						break;
					case 29:
						{System.out.println("NIL "+yytext());return new Symbol(sym.NIL);}
					case -30:
						break;
					case 30:
						{System.out.println("END "+yytext());return new Symbol(sym.END);}
					case -31:
						break;
					case 31:
						{System.out.println("VAR "+yytext());return new Symbol(sym.VAR);}
					case -32:
						break;
					case 32:
						{System.out.println("string "+yytext());return new Symbol(sym.STRINGVAL, new String(yytext()));}
					case -33:
						break;
					case 33:
						{System.out.println("array "+yytext());return new Symbol(sym.ARRAY,new String(yytext()));}
					case -34:
						break;
					case 34:
						{System.out.println("Array identifier "+yytext());return new Symbol(sym.ARRAYID,new String(yytext()));}
					case -35:
						break;
					case 35:
						{System.out.println("READ "+yytext());return new Symbol(sym.READ);}
					case -36:
						break;
					case 36:
						{System.out.println("CHAR "+yytext());return new Symbol(sym.CHAR);}
					case -37:
						break;
					case 37:
						{System.out.println("stringarray "+yytext());return new Symbol(sym.STRINGARRAY,new String(yytext()));}
					case -38:
						break;
					case 38:
						{System.out.println("FLOAT"+yytext());return new Symbol(sym.FLOAT);}
					case -39:
						break;
					case 39:
						{System.out.println("WHILE "+yytext());return new Symbol(sym.WHILE);}
					case -40:
						break;
					case 40:
						{System.out.println("BREAK "+yytext());return new Symbol(sym.BREAK);}
					case -41:
						break;
					case 41:
						{System.out.println("CONST "+yytext());return new Symbol(sym.CONST);}
					case -42:
						break;
					case 42:
						{System.out.println("PRINT "+yytext());return new Symbol(sym.PRINT);}
					case -43:
						break;
					case 43:
						{System.out.println("RETURN "+yytext());return new Symbol(sym.RETURN);}
					case -44:
						break;
					case 44:
						{System.out.println("STRING "+yytext());return new Symbol(sym.STRING);}
					case -45:
						break;
					case 45:
						{System.out.println("CONTINUE "+yytext());return new Symbol(sym.CONTINUE);}
					case -46:
						break;
					case 47:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -47:
						break;
					case 49:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -48:
						break;
					case 51:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -49:
						break;
					case 53:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -50:
						break;
					case 55:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -51:
						break;
					case 57:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -52:
						break;
					case 59:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -53:
						break;
					case 61:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -54:
						break;
					case 63:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -55:
						break;
					case 65:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -56:
						break;
					case 67:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -57:
						break;
					case 68:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -58:
						break;
					case 69:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -59:
						break;
					case 70:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -60:
						break;
					case 71:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -61:
						break;
					case 72:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -62:
						break;
					case 73:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -63:
						break;
					case 74:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -64:
						break;
					case 75:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -65:
						break;
					case 76:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -66:
						break;
					case 78:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -67:
						break;
					case 79:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -68:
						break;
					case 80:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -69:
						break;
					case 81:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -70:
						break;
					case 82:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -71:
						break;
					case 83:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -72:
						break;
					case 84:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -73:
						break;
					case 85:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -74:
						break;
					case 86:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -75:
						break;
					case 87:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -76:
						break;
					case 88:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -77:
						break;
					case 89:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -78:
						break;
					case 90:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -79:
						break;
					case 91:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -80:
						break;
					case 92:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -81:
						break;
					case 93:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -82:
						break;
					case 94:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -83:
						break;
					case 95:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -84:
						break;
					case 96:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -85:
						break;
					case 97:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -86:
						break;
					case 98:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -87:
						break;
					case 99:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -88:
						break;
					case 100:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -89:
						break;
					case 101:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -90:
						break;
					case 102:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -91:
						break;
					case 103:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -92:
						break;
					case 104:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -93:
						break;
					case 105:
						{System.out.println("identifier"+yytext());return new Symbol(sym.ID,new String(yytext()));}
					case -94:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
