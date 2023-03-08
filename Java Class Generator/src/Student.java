import java.util.Scanner;
public class Student {
//======================== Properties
private int ID;
private String Name;
private int Age;
private boolean IsMale;
private double GPA;
private String Crime;

//======================== Constructors
public Student(int ID, String Name, int Age, boolean IsMale, double GPA, String Crime) {
setID(ID);
setName(Name);
setAge(Age);
setIsMale(IsMale);
setGPA(GPA);
setCrime(Crime);
}
public Student(Scanner fin) throws Exception {
String[] parts = fin.nextLine().split("\t");
setID(Integer.parseInt(parts[0]));
setName(parts[1]);
setAge(Integer.parseInt(parts[2]));
setIsMale(Boolean.parseBoolean(parts[3]));
setGPA(Double.parseDouble(parts[4]));
setCrime(parts[5]);
}

//======================== Methods
public boolean equals(Object obj) {
if(!(obj instanceof Student)) return false;
Student s = (Student)obj;
return getEqualsString().equals(s.getEqualsString());
}
private String getEqualsString() {
return ID + "~" + Name + "~" + Age + "~" + IsMale + "~" + GPA + "~" + Crime;
}
public String toString() {
return"ID: " + ID + "\t\t" + "Name: " + Name + "\t\t" + "Age: " + Age + "\t\t" + "IsMale: " + IsMale + "\t\t" + "GPA: " + GPA + "\t\t" + "Crime: " + Crime;
}

//======================== Getters/Setters
public void setID(int ID) {
this.ID = ID;
}
public int getID() {
return ID;
}
public void setName(String Name) {
this.Name = Name;
}
public String getName() {
return Name;
}
public void setAge(int Age) {
this.Age = Age;
}
public int getAge() {
return Age;
}
public void setIsMale(boolean IsMale) {
this.IsMale = IsMale;
}
public boolean getIsMale() {
return IsMale;
}
public void setGPA(double GPA) {
this.GPA = GPA;
}
public double getGPA() {
return GPA;
}
public void setCrime(String Crime) {
this.Crime = Crime;
}
public String getCrime() {
return Crime;
}
}
