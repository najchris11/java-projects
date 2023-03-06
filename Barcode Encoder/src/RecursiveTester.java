import java.util.HashMap;

public class RecursiveTester {
	 public static HashMap <Character, String> code = new HashMap<Character, String>();

	    

     public static void main(String[] args) {

          code.put('0', "||:::");

          code.put('1', ":::||");

          code.put('2', "::|:|");

          code.put('3', "::||:");

          code.put('4', ":|::|");

          code.put('5', ":|:|:");

          code.put('6', ":||::");

          code.put('7', "|:::|");

          code.put('8', "|::|:");

          code.put('9', "|:|::");

          System.out.println(zipToBar("45056-1234"));

     }

 

     public static String zipToBar(String zip) {

          return "|" + _zipToBar(zip + getCheckDigit(zip, 0)) + "|";

     }

    

     private static String _zipToBar(String zip) {

          if(zip.length() == 0) return "";

          return (code.containsKey(zip.charAt(0)) ? code.get(zip.charAt(0)) : "")

                   + _zipToBar(zip.substring(1));

     }

    

     private static int getCheckDigit(String zip, int total) {

          if(zip.length() == 0) return (10-total%10)%10;

          return getCheckDigit(zip.substring(1),

                   total +

                   (Character.isDigit(zip.charAt(0)) ? zip.charAt(0) - '0' : 0));

     }
}
