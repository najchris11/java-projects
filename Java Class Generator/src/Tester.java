import java.io.File;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		ArrayList<Property> properties = new ArrayList<Property>();
//		ArrayList<Student> students = new ArrayList<Student>();
		props(properties);
		fileCreator(properties);
		// These methods were used to ensure that my code worked properly
//		objectCreator(students);
//		System.out.println(students.get(0));
	}
	
//	private static void objectCreator(ArrayList<Student> students) {
//		Scanner fileIn = null;
//		try {
//			fileIn = new Scanner(new File("students.txt"));
//			fileIn.nextLine();
//			while (fileIn.hasNextLine()) {
//				students.add(new Student (fileIn));
//			}
//		} catch (Exception e) {
//			System.out.println("Error: " + e.getMessage());
//		} finally {
//			try {
//				fileIn.close();
//			} catch (Exception e) {
//			}
//		}
//	}

	private static void fileCreator(ArrayList<Property> properties) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("src/Student.java"));
			pw.println("import java.util.Scanner;");
			pw.println("public class Student {");
			pw.println("//======================== Properties");
			for (Property p : properties) {
				pw.println("private " + p.getDataType() + " " + p.getFieldName() + ";");
			}
			pw.println();
			pw.println("//======================== Constructors");
			pw.print("public Student(");
			for (int i = 0; i < properties.size(); i++) {
//				pw.println(i < properties.size() - 1 ? properties.get(i).getDataType() + " " + properties.get(i).getFieldName() + ", " : properties.get(i).getDataType() + " " + properties.get(i).getFieldName());
				if (i < properties.size() - 1) {
				pw.print(properties.get(i).getDataType() + " " + properties.get(i).getFieldName() + ", ");
				}
				else 
					pw.print(properties.get(i).getDataType() + " " + properties.get(i).getFieldName());
			}
			pw.println(") {");
			for (Property p : properties) {
				pw.println("set" + p.getFieldName() + "(" + p.getFieldName() + ");");
			}
			pw.println("}");
			pw.println("public Student(Scanner fin) throws Exception {");
			pw.println("String[] parts = fin.nextLine().split(\"\\t\");");
			int index = 0;
			for (Property p : properties) {
				if (p.getDataType().equalsIgnoreCase("String")) {
					pw.println("set" + p.getFieldName() + "(" + "parts[" + index + "]);");
				} else if (p.getDataType().equalsIgnoreCase("Int")) {
					pw.println("set" + p.getFieldName() + "(" + p.getDataType().substring(0,1).toUpperCase() + p.getDataType().substring(1) + "eger.parseInt(parts[" + index + "]));");
				} else {
					pw.println("set" + p.getFieldName() + "(" + p.getDataType().substring(0,1).toUpperCase() + p.getDataType().substring(1) + ".parse" + p.getDataType().substring(0,1).toUpperCase() + p.getDataType().substring(1) + "(parts[" + index + "]));");
				}
				index++;
			}
			
			pw.println("}");
			pw.println();
			pw.println("//======================== Methods");
			pw.println("public boolean equals(Object obj) {");
			pw.println("if(!(obj instanceof Student)) return false;");
			pw.println("Student s = (Student)obj;");
			pw.println("return getEqualsString().equals(s.getEqualsString());");
			pw.println("}");
			pw.println("private String getEqualsString() {");
			pw.print("return ");
			for (int i = 0; i < properties.size(); i++) {
				if (i < properties.size() - 1) {
				pw.print(properties.get(i).getFieldName() + " + \"~\" + ");
				}
				else 
					pw.println(properties.get(i).getFieldName() + ";");
			}
			pw.println("}");
			pw.println("public String toString() {");
			pw.print("return");
			for (int i = 0; i < properties.size(); i++) {
				if (i < properties.size() - 1) {
				pw.print("\"" + properties.get(i).getFieldName() + ": \" + " + properties.get(i).getFieldName() + " + \"\\t\\t\" + ");
				}
				else 
					pw.println("\"" + properties.get(i).getFieldName() + ": \" + " + properties.get(i).getFieldName() + ";");
			}
			pw.println("}");
			pw.println();
			pw.println("//======================== Getters/Setters");
			for (Property p : properties) {
				pw.println("public void set" + p.getFieldName() + "(" + p.getDataType() + " " + p.getFieldName() + ") {");
				pw.println("this." + p.getFieldName() + " = " + p.getFieldName() + ";");
				pw.println("}");
				pw.println("public " + p.getDataType() + " get" + p.getFieldName() + "() {");
				pw.println("return " + p.getFieldName() + ";");
				pw.println("}");
			}
			pw.println("}");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				pw.close();
			} catch (Exception e) {
			}
		}
	}

	//Property Classifier
	public static void props(ArrayList<Property> properties) {
		String[] props = null;
		String[] data = null;
//		String[] data = {"1", "Tom Ryan", "22", "True", "3.1"};	
//		for(String s : data) {
//			System.out.println(s + " isInt: " + isInt(s));
//			System.out.println(s + " isLong: " + isLong(s));
//			System.out.println(s + " isFloat: " + isFloat(s));
//			System.out.println(s + " isBoolean: " + isBoolean(s));
//			System.out.println("======================================");
//		}

		Scanner fileIn = null;
		try {
			fileIn = new Scanner(new File("students.txt"));
			props = fileIn.nextLine().split("\t");
			data = fileIn.nextLine().split("\t");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				fileIn.close();
			} catch (Exception e) {
			}
		}
		String dataType;
		for (int i = 0; i < data.length; i++) {
			if (isInt(data[i])) dataType = "int";
			else if (isLong(data[i])) dataType = "long";
			else if (isFloat(data[i])) dataType = "double";
			else if (isBoolean(data[i])) dataType = "boolean";
			else dataType = "String";
			properties.add(new Property(props[i], dataType));
		}
	}
	
	// Methods for checking for datatype o
		public static boolean isInt(String val) {
			try {
				Integer.parseInt(val);
				return true;
			} catch (Exception e) {  return false;  }
		}
		
		public static boolean isLong(String val) {
			try {
				Long.parseLong(val);
				return true;
			} catch (Exception e) {  return false;  }
		}
			
		public static boolean isFloat(String val) {
			try {
				if(val.contains(".")) {
					Double.parseDouble(val);
					return true;
				}
				return false;
			} catch (Exception e) {  return false;  }
			
		}
		
		public static boolean isBoolean(String val) {
			return val.equalsIgnoreCase("True") || val.equalsIgnoreCase("False");		
		}


}
