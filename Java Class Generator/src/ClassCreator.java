
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ClassCreator {

	public static void main(String[] args) throws Exception {
		
		String className = "Student";
		
		ArrayList<Prop> properties = new ArrayList<Prop>();
		Scanner fin = new Scanner(new File("students.txt"));
		PrintWriter pw = new PrintWriter(className + ".java");
		String[] props = fin.nextLine().split("\t");
		String[] samp = fin.nextLine().split("\t");
		fin.close();
		
		for(int i = 0; i < props.length; i++)
			properties.add(new Prop(props[i], getDataType(samp[i])));
		
		//========================================= Now Create The Class!
		
		pw.println("public class " + className  + " {");
		
		pw.println("\n\t//======================================== Properties");
		for(Prop p : properties)
			pw.println(p.createDeclaration());
		
		
		
		pw.println("\n\t//======================================== Constructors");

		pw.println("\n\t//======================================== Methods");

		pw.println("\n\t//======================================== Getters/Setters");
		for(Prop p : properties)
			pw.println(p.createGetter());
		
		pw.println();
		for(Prop p : properties)
			pw.println(p.createSetter());
		
		pw.println("}");
		pw.close();
		
		System.out.println(className + ".java Created...");
	}
	
	public static String getDataType(String val) {
		try {	Integer.parseInt(val); 	 return "int";		} catch (Exception e) { }	
		try {	Long.parseLong(val);	 return "long";		} catch (Exception e) { }
		try {	Double.parseDouble(val); return "double";	} catch (Exception e) { }
		if(val.equalsIgnoreCase("True") || val.equalsIgnoreCase("False")) 
			return "boolean";
		
		return "String";
	}

	
	//================================================ INNER CLASS
	public static class Prop {
		//...................................... Properties
		private String name;
		private String dataType;
		
		//...................................... Constructors
		public Prop(String name, String dataType) {
			setName(name);
			setDataType(dataType);
		}

		//...................................... Methods
		public String createDeclaration() {
			return String.format("\tprivate %s %s;", dataType, name );
		}
		
		public String createGetter() {
			return String.format("\tpublic %s get%s() {\n\t\treturn %s;\n\t}", 
					dataType, up(name), name);
		}
		
		public String createSetter() {
			return "\tset " + name;		
		}
		
		private String up(String val) {
			return val.substring(0,1).toUpperCase() + val.substring(1);
		}
		
		private String down(String val) {
			return val.substring(0,1).toLowerCase() + val.substring(1);
		}		
		
		//...................................... Getters/Setters
		public String getName() 	{	return name;		}
		public String getDataType() {	return dataType;	}

		public void setName(String name) 			{	this.name = down(name);		}
		public void setDataType(String dataType) 	{	this.dataType = dataType;	}
	}
}
