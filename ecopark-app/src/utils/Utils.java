package utils;

import entity.Bike;
import entity.BikeRental;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * @author nguyenlm Contains helper functions
 */
public class Utils {

	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static Logger LOGGER = getLogger(Utils.class.getName());
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
	}

	public static Logger getLogger(String className) {
		return Logger.getLogger(className);
	}

	public static String getCurrencyFormat(int num) {
		Locale vietname = new Locale("vi", "VN");
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(vietname);
		return defaultFormat.format(num);
	}

	/**
	 * Return a {@link String String} that represents the current time in the format of yyyy-MM-dd HH:mm:ss.
	 * 
	 * @author hieudm
	 * @return the current time as {@link String String}.
	 */
	public static String getToday() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = new Date();
	    return dateFormat.format(date);
	}

	/**
	 * Return a {@link String String} that represents the cipher text
	 * encrypted by md5 algorithm.
	 * 
	 * @author hieudm vnpay
	 * @param message - plain text as {@link String String}.
	 * @return cipher text as {@link String String}.
	 */
	public static String md5(String message) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes("UTF-8"));
			// converting byte array to Hexadecimal String
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			digest = sb.toString();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
			Utils.getLogger(Utils.class.getName());
			digest = "";
		}
		return digest;
	}

	/**
	 * calculate price based on time rented
	 * @param bikeRental
	 * @param bike
	 * @return
	 */
	public static long calculatePrice(BikeRental bikeRental, Bike bike) {
		long price;

		if(bikeRental.getTimeRented()/60 < 10)
			price = 0;
		else if(bikeRental.getTimeRented()/60 < 30)
			price = 10000;
		else
			price = 10000 + (bikeRental.getTimeRented()/60 - 30)/15 * 3000;

		if(bike.getBikeType().equals("xe dien") || bike.getBikeType().equals("Xe dap doi")) {
			return (long) (price * 1.5);
		}else {
			return price;
		}
	}

	/**
	 * tinh so tien dat coc dua tren loai xe
	 * @param bike
	 * @return
	 */
	public static int calDeposit(Bike bike){
		if(bike.getBikeType().equals("xe dap don")){
			return 400000;
		}else if(bike.getBikeType().equals("xe dap doi")){
			return 550000;
		}else{
			return 700000;
		}
	}

}