/*
ID: cynical
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;
import java.math.*;

public class ride {

	public static void main(String args[]) throws IOException{
		//BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader f = new BufferedReader(new FileReader("ride.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
		String comet = f.readLine();
		String group = f.readLine();
		long ctotal = comet.charAt(0) - 'A' + 1;
		if(comet.length() == 1)ctotal = comet.charAt(0)-'A'+1;
		for(int i = 1; i < comet.length(); i++){
			ctotal *= comet.charAt(i) - 'A' + 1;
		}
		ctotal %= 47;
		long gtotal = group.charAt(0) - 'A' + 1;
		if(group.length() == 1)gtotal = group.charAt(0)-'A'+1;
		for(int i = 1; i < group.length(); i++){
			gtotal *= group.charAt(i) - 'A' + 1;
		}
		gtotal %= 47;
		if(ctotal == gtotal)out.println("GO");
		else out.println("STAY");
		out.close();
	}
	
}
