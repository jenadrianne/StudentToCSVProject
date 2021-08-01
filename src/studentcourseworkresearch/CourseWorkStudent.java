package studentcourseworkresearch;

public class CourseWorkStudent extends Student{

	   private double assignment1;

	 

	   private double assignment2;

	 

	   //total of weekly work, max of 20 marks

	   private double weeklyPracticalWork;

	 

	   //exactly one exam, Integer rather of int as it's assumed nullable, student may have taken or not the final exams

	   private double finalExam;

	 

	   private double overallMark;

	 

	   private String finalGrade;

	 

	 

	   public CourseWorkStudent(Student student){

	       this.setTitle(student.getTitle());

	       this.setFirstName(student.getFirstName());

	       this.setLastName(student.getLastName());

	       this.setStudentId(student.getStudentId());

	       this.setBirthDate(student.getBirthDate());

	       this.setBirthMonth(student.getBirthMonth());

	       this.setBirthYear(student.getBirthYear());

	   }

	 

	   public double getAssignment1() {

	       return assignment1;

	   }

	 

	   public void setAssignment1(double assignment1) {

	       this.assignment1 = assignment1;

	   }

	 

	   public double getAssignment2() {

	       return assignment2;

	   }

	 

	   public void setAssignment2(double assignment2) {

	       this.assignment2 = assignment2;

	   }

	 

	   public double getWeeklyPracticalWork() {

	       return weeklyPracticalWork;

	   }

	 

	   public void setWeeklyPracticalWork(double weeklyPracticalWork) {

	       if(weeklyPracticalWork > 20){

	           System.out.println("Invalid weekly work marks!" );

	       } else {

	           this.weeklyPracticalWork = weeklyPracticalWork;

	       }

	   }

	 

	   public double getFinalExam() {

	       return finalExam;

	   }

	 

	   public void setFinalExam(double finalExam) {

	       this.finalExam = finalExam;

	   }

	 

	   public String getFinalGrade() {

	       finalGrade = calculateFinalGrade();

	       return finalGrade;

	   }

	 

	   public double calculateOverallMark() {

	       double assignmentWeightedMark = calculateWeightedAssignments();

	       double finalExamWeightedMark = finalExam * 0.3;

	       overallMark = assignmentWeightedMark + weeklyPracticalWork + finalExamWeightedMark;

	 

	       return overallMark;

	   }

	 

	   public double calculateWeightedAssignments(){

	       return ((assignment1 + assignment2)/2) * 0.5;

	   }

	 

	 

	   @Override

	   public String toString() {

	       return "CourseWorkStudent{" +

	               "studentId=" + getStudentId() +

	               ", title='" + getTitle() + '\'' +

	               ", firstName='" + getFirstName() + '\'' +

	               ", lastName='" + getLastName() + '\'' +

	               ", birthDate=" + getBirthDate() +

	               ", birthMonth=" + getBirthMonth() +

	               ", birthYear=" + getBirthYear() +

	               ", assignment1=" + assignment1 +

	               ", assignment2=" + assignment2 +

	               ", weeklyPracticalWork=" + weeklyPracticalWork +

	               ", finalExam=" + finalExam +

	               ", overallMark=" + calculateOverallMark() +

	               ", finalGrade =" + getFinalGrade() +

	               '}';

	   }
	   
	   /**
	  * CSV format
	  * @return
	  */
	 public String toCSVFormt() {	
		 	String CSV_SEPARATOR = ",";
			String result = super.toCSVFormt(); 
			result += this.assignment1 + CSV_SEPARATOR
			          + this.assignment2 + CSV_SEPARATOR
			          + this.weeklyPracticalWork + CSV_SEPARATOR
			          + this.finalExam + CSV_SEPARATOR+CSV_SEPARATOR+CSV_SEPARATOR
			          + this.overallMark + CSV_SEPARATOR
			          + this.finalGrade + CSV_SEPARATOR ;
			return result; 
	}

	}

	 