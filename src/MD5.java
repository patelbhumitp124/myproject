import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5 {

	public static String getMD5(String input){
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte messageDigest[] = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashText = number.toString(16);
			while (hashText.length()<32) {
				hashText = "0" + hashText;
			}
			return hashText;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	
}
