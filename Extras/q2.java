import java.io.*;
import java.util.*;

class Main {
    static boolean checkIdentifier(String st) {
        int flag = 1;
        if (!((st.charAt(0) >= 97 && st.charAt(0) <= 122) || (st.charAt(0) >= 65 && st.charAt(0) <= 90))) {
            return false;
        }
        char c;
        for (int i = 1; i < st.length(); i++) {
            if (flag == 0) {
                break;
            }
            c = st.charAt(i);
            if (!Character.isDigit(c) && !((c >= 97 && c <= 122) || (c >= 65 && c <= 90)) && c != '_') {
                return false;
            }
        }
        if (flag == 1)
            return true;
        return false;
    }
    static boolean digit(String s){
        for(int i=0;i<s.length();i++){
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String st = new String();
        System.out.println("Enter code, press ctrl-D to stop");
        while(s.hasNextLine()){
            st += s.nextLine();
        }
        Hashtable h = new Hashtable();
        h.put("alpha", "ID");
        h.put("digit", "NUM");
        h.put("=", "Assign");
        h.put("+", "Sum");
        h.put("-", "Sub");
        h.put("*", "Product");
        h.put("/", "Quotient");
        h.put("%", "Mod");
        h.put(")", "RParen");
        h.put("(", "LParen");
        h.put(";", "Semicolon");
        h.put("<", "LT");
        h.put("<=", "LEQ");
        h.put(">", "GT");
        h.put(">=", "GEQ");
        h.put("==", "Equality");
        h.put("if", "IF");
        h.put("while", "WHILE");
        h.put("else", "ELSE");
        h.put("for", "FOR");
        h.put("switch", "SWITCH");
        h.put("case", "CASE");
        String[] split;
        split = st.split(" ");
        for(int i=0;i<split.length;i++){
            if(checkIdentifier(split[i]) == true){
                System.out.print("<"+h.get("alpha")+","+split[i]+">");
            }
            else if(digit(split[i])==true){
                System.out.print("<"+h.get("digit")+","+split[i]+">");
            }
            else{
                if(h.get(split[i])==null){
                    System.out.print("Invalid token" + split[i]);
                }
                else{
                    System.out.print(" < "+" "+h.get(split[i])+" , "+split[i]+" > ");
                }
            }
        }
        
    }
}