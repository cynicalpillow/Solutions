import java.util.*;
public class ICSQuestion2 {
	public static void main(String args[]) throws Exception {
		Scanner s = new Scanner(System.in);
		double standardAngle = s.nextDouble();
		int surface = s.nextInt();
		double angle = getAngle(standardAngle, surface);
		if(angle == -1)System.out.println("Does not hit the surface");
		else System.out.println(angle);
		s.close();
	}
	public static double getAngle(double standardAngle, int s){
		if(s == 0){
			if(standardAngle <= 180 || standardAngle >= 360)return -1;
			return 360 - standardAngle;
		} else if(s == 1){
			if(standardAngle >= 90 && standardAngle <= 270)return -1;
			if(standardAngle < 90)return 180 - standardAngle;
			return 180 + (360 - standardAngle);
		} else if(s == 2){
			if(standardAngle >= 180 || standardAngle <= 0)return -1;
			return 360 - standardAngle;
		} else if(s == 3){
			if(standardAngle <= 90 || standardAngle >= 270)return -1;
			if(standardAngle > 180)return 270 + (270 - standardAngle);
			return 180 - standardAngle;
		}
		return -1;
	}
}
