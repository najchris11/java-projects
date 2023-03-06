import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Tester {
	public static HashMap <Integer, String> code = new HashMap<Integer, String>();

	public static void main(String[] args) {
		//==================== Adds values to the HashMap
		populate(code);
		//==================== Presents menu options
		greet();

	}

	private static void populate(HashMap<Integer, String> code) {
		code.put(1, ":::||");
		code.put(2, "::|:|");
		code.put(3, "::||:");
		code.put(4, ":|::|");
		code.put(5, ":|:|:");
		code.put(6, ":||::");
		code.put(7, "|:::|");
		code.put(8, "|::|:");
		code.put(9, "|:|::");
		code.put(0, "||:::");
	}

	private static void greet() {
		Scanner kb = new Scanner(System.in);
		int choice = 0;

		while (choice != 3) {
			System.out.println("Menu:");
			System.out.println("1) Enter a zip code:");
			System.out.println("2) Enter a bar code:");
			System.out.println("3) Quit");
			System.out.print(">> ");
			try {
				choice = Integer.parseInt(kb.nextLine().trim());
			} catch (Exception e) {
				System.out.println("Invalid Option. Please enter an integer.\n");
				continue;
			}
			
			switch (choice) {
			case 1:
				zipToBar();
				break;
			case 2:
				barToZip();
				break;
			case 3:
				System.out.println("Thank you.");
				break;

			default:
				System.out.println("Invalid Option. Choose between 1 and 3.\n");
			}
		}
	}

	private static void barToZip() {
		Scanner kb = new Scanner(System.in);
		String bar;
		System.out.print("Enter a bar code: ");
		bar = kb.nextLine();
		if (bar.length() != 32 && bar.length() != 52) {
			System.out.println("Barcode improperly entered. Try again.");
			return;
		}
		for (int i = 0; i < bar.length(); i++) {
			if(!(bar.charAt(i)==':') && (!(bar.charAt(i)=='|'))) {
				System.out.println("Barcode improperly entered. Try again.");
				return;
			}			
		}
		printBar(bar);
	}
	private static void printBar(String bar) {
		char[] list = bar.toCharArray();
		String temp, zip = "";
		boolean isValid = true;
			for (int i = 1; i < list.length-6; i = i + 5) {
				temp = "" + list[i] + list[i + 1] + list[i + 2] + list[i + 3] + list[i + 4];
					for (Entry<Integer, String> s : code.entrySet()) {
						if (s.getValue().equals(temp)) {
							zip +=s.getKey();
							isValid = true;
							break;
						}
						else {
//							System.out.println("Barcode not valid.");
							isValid = false;
//							break outer;
						}
					}
			}
			if (list[0] != '|' || list[list.length-1] != '|') 	isValid = false;
		if(isValid) {
			if (zip.length() == 5) {
				System.out.println(zip);
			}
			else {
				System.out.println(zip.substring(0, 5) + "-" + zip.substring(5));
			}
		}
		else {
			System.out.println("Barcode not valid.");
		}
	}

	private static void zipToBar() {
		String zip;
		Scanner kb = new Scanner(System.in);
		boolean isValid = true;
		System.out.print("Enter a zip code: ");
		zip = kb.nextLine().trim();
		if (zip.contains("-")) {
			if (zip.length() != 10) {
				System.out.println("Zipcode improperly entered. Try again.");
				isValid = false;
				zipToBar();
			}
			else {
				zip = zip.replace("-", "");
			}
		}
		else {
			if (zip.length() != 5 && zip.length() != 9) {
				System.out.println("Zipcode improperly entered. Try again.");
				isValid = false;
				zipToBar();
			}
		}
		if (isValid)	printZip(zip);
	}


	private static void printZip(String zip) {
		int check = 0;
		ArrayList<Integer> zipCode = new ArrayList<Integer>();
		for (int i = 0; i < zip.length(); i++) {
			try {
				zipCode.add(Integer.parseInt("" + zip.charAt(i)));
			} catch (Exception e) {
				System.out.println("Zipcode improperly entered. Try again.");
				return;
			}
		}
		System.out.print("|");
		for (Integer i : zipCode) {
			System.out.print(code.get(i));
			check += i;
		}
		check = (10 - check%10)%10;
		System.out.println(code.get(check) + "|");
	}

}
