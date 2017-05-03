/*
ID: cynical
LANG: JAVA
TASK: namenum
*/
import java.io.*;
import java.util.*;
import java.math.*;

public class namenum {
	
	public static void main(String args[]) throws Exception {
		//BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader s = new BufferedReader(new FileReader("namenum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		BufferedReader x = new BufferedReader(new FileReader("dict.txt"));
		StringBuilder y = new StringBuilder();
		String name = s.readLine();
		ArrayList<String> possible = new ArrayList<String>();
		for(int i = 0; i < name.length(); i++){
			int idx = Integer.parseInt(name.substring(i, i+1));
			if(i == 0){
				while(true){
					String st = x.readLine();
					if(st == null)break;
					if(idx == 2){
						if(st.length() <= name.length() && ((st.charAt(i) == 'A')||(st.charAt(i) == 'B')||(st.charAt(i) == 'C')))possible.add(st);
					} else if(idx == 3){
						if(st.length() <= name.length() && ((st.charAt(i) == 'D')||(st.charAt(i) == 'E')||(st.charAt(i) == 'F')))possible.add(st);
					} else if(idx == 4){
						if(st.length() <= name.length()  && ((st.charAt(i) == 'G')||(st.charAt(i) == 'H')||(st.charAt(i) == 'I')))possible.add(st);
					} else if(idx == 5){
						if(st.length() <= name.length() && ((st.charAt(i) == 'J')||(st.charAt(i) == 'K')||(st.charAt(i) == 'L')))possible.add(st);
					} else if(idx == 6){
						if(st.length() <= name.length() && ((st.charAt(i) == 'M')||(st.charAt(i) == 'N')||(st.charAt(i) == 'O')))possible.add(st);
					} else if(idx == 7){
						if(st.length() <= name.length() && ((st.charAt(i) == 'P')||(st.charAt(i) == 'R')||(st.charAt(i) == 'S')))possible.add(st);
					} else if(idx == 8){
						if(st.length() <= name.length() && ((st.charAt(i) == 'T')||(st.charAt(i) == 'U')||(st.charAt(i) == 'V')))possible.add(st);
					} else {
						if(st.length() <= name.length() && ((st.charAt(i) == 'W')||(st.charAt(i) == 'X')||(st.charAt(i) == 'Y')))possible.add(st);
					}
				}
			} else {
				ArrayList<String> temp = new ArrayList<String>();
				for(String st : possible){
					if(i >= st.length())continue;
					if(idx == 2){
						if(st.length() <= name.length() && ((st.charAt(i) == 'A')||(st.charAt(i) == 'B')||(st.charAt(i) == 'C')))temp.add(st);
					} else if(idx == 3){
						if(st.length() <= name.length() && ((st.charAt(i) == 'D')||(st.charAt(i) == 'E')||(st.charAt(i) == 'F')))temp.add(st);
					} else if(idx == 4){
						if(st.length() <= name.length() && ((st.charAt(i) == 'G')||(st.charAt(i) == 'H')||(st.charAt(i) == 'I')))temp.add(st);
					} else if(idx == 5){
						if(st.length() <= name.length() && ((st.charAt(i) == 'J')||(st.charAt(i) == 'K')||(st.charAt(i) == 'L')))temp.add(st);
					} else if(idx == 6){
						if(st.length() <= name.length() && ((st.charAt(i) == 'M')||(st.charAt(i) == 'N')||(st.charAt(i) == 'O')))temp.add(st);
					} else if(idx == 7){
						if(st.length() <= name.length() && ((st.charAt(i) == 'P')||(st.charAt(i) == 'R')||(st.charAt(i) == 'S')))temp.add(st);
					} else if(idx == 8){
						if(st.length() <= name.length() && ((st.charAt(i) == 'T')||(st.charAt(i) == 'U')||(st.charAt(i) == 'V')))temp.add(st);
					} else {
						if(st.length() <= name.length() && ((st.charAt(i) == 'W')||(st.charAt(i) == 'X')||(st.charAt(i) == 'Y')))temp.add(st);
					}
				}
				possible = temp;
			}
		}
		if(possible.size() == 0)out.println("NONE");
		for(String st : possible){
			out.println(st);
		}
		out.close();
	}
}
