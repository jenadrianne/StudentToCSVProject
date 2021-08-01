package studentcourseworkresearch;

import java.io.*;
import java.util.*;
import java.util.Objects;

 

public class Student {

   private String title;

   private String firstName;

   private String lastName;

   private long studentId;

   private int birthDate;

   private int birthMonth;

   private int birthYear;

 

   public Student(){}

 

   public Student(String title, String firstName, String lastName, long studentId, int birthDate, int birthMonth, int birthYear) {

       this.title = title;

       this.firstName = firstName;

       this.lastName = lastName;

       this.studentId = studentId;

       this.birthDate = birthDate;

       this.birthMonth = birthMonth;

       this.birthYear = birthYear;

   }

 

   public String getTitle() {

       return title;

   }

 

   public void setTitle(String title) {

       this.title = title;

   }

 

   public String getFirstName() {

       return firstName;

   }

 

   public void setFirstName(String firstName) {

       this.firstName = firstName;

   }

 

   public String getLastName() {

       return lastName;

   }

 

   public void setLastName(String lastName) {

       this.lastName = lastName;

   }

 

   public long getStudentId() {

       return studentId;

   }

 

   public void setStudentId(long studentId) {

       this.studentId = studentId;

   }

 

   public int getBirthDate() {

       return birthDate;

   }

 

   public void setBirthDate(int birthDate) {

       this.birthDate = birthDate;

   }

 

   public int getBirthMonth() {

       return birthMonth;

   }

 

   public void setBirthMonth(int birthMonth) {

       this.birthMonth = birthMonth;

   }

 

   public int getBirthYear() {

       return birthYear;

   }

 

   public void setBirthYear(int birthYear) {

       this.birthYear = birthYear;

   }

 

   public void printVariables(){

       System.out.println("Student{" +

               "title='" + title + '\'' +

               ", firstName='" + firstName + '\'' +

               ", lastName='" + lastName + '\'' +

               ", studentId=" + studentId +

               ", birthDate='" + birthDate + '\'' +

               '}');

   }

 

   public String calculateFinalGrade() {

       double overallMark = calculateOverallMark();

       String finalGrade;

       if(overallMark >= 80){

           finalGrade = "HD";

       } else if (overallMark >= 70){

           finalGrade = "D";

       } else if (overallMark >= 60){

           finalGrade = "C";

       } else if (overallMark >=50){

           finalGrade = "P";

       } else{

           finalGrade = "N";

       }

       return finalGrade;

   }

 

   public double calculateOverallMark() {

       return 0;

   }

 

   @Override

   public boolean equals(Object o) {

       if (this == o) return true;

       if (o == null || getClass() != o.getClass()) return false;

       Student student = (Student) o;

       return studentId == student.studentId;

   }

 

   @Override

   public int hashCode() {

       return Objects.hash(studentId);

   }

 

   @Override

   public String toString() {

       return "Student{" +

               "title='" + title + '\'' +

               ", firstName='" + firstName + '\'' +

               ", lastName='" + lastName + '\'' +

               ", studentId=" + studentId +

               ", birthDate='" + birthDate + '\'' +

               '}';

   }
   
   /**
	 * to CSV foramt
	 * @return
	 */
	public String toCSVFormt() {
		String CSV_SEPARATOR = ",";
		String result = this.studentId +CSV_SEPARATOR
						+ this.title + CSV_SEPARATOR
						+ this.firstName +CSV_SEPARATOR
						+ this.lastName + CSV_SEPARATOR
						+  this.birthMonth + "/" + this.birthDate + "/" + this.birthYear +CSV_SEPARATOR ;
		return result; 
	}

}