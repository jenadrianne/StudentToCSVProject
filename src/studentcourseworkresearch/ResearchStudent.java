package studentcourseworkresearch;

public class ResearchStudent extends Student{

	   private double finalOralPresentation;

	   private double finalThesis;

	 

	   private double overallMark;

	 

	   private String finalGrade;

	 

	   public ResearchStudent(Student student){

	       this.setTitle(student.getTitle());

	       this.setFirstName(student.getFirstName());

	       this.setLastName(student.getLastName());

	       this.setStudentId(student.getStudentId());

	       this.setBirthDate(student.getBirthDate());

	       this.setBirthMonth(student.getBirthMonth());

	       this.setBirthYear(student.getBirthYear());

	   }

	 

	   public double getFinalOralPresentation() {

	       return finalOralPresentation;

	   }

	 

	   public void setFinalOralPresentation(double finalOralPresentation) {

	       this.finalOralPresentation = finalOralPresentation;

	   }

	 

	   public double getFinalThesis() {

	       return finalThesis;

	   }

	 

	   public void setFinalThesis(double finalThesis) {

	       this.finalThesis = finalThesis;

	   }

	 

	 

	   public String getFinalGrade() {

	       finalGrade = calculateFinalGrade();

	       return finalGrade;

	   }

	 

	   public double calculateOverallMark() {

	       double finalThesisWeighted = finalThesis * 0.8;
	       double oralPresentation = finalOralPresentation * 0.20;
	       overallMark = oralPresentation + finalThesisWeighted;

	       return overallMark;

	   }

	 

	 

	   @Override

	   public String toString() {

	       return "ResearchStudent{" +

	               "studentId=" + getStudentId() +

	               ", title='" + getTitle() + '\'' +

	               ", firstName='" + getFirstName() + '\'' +

	               ", lastName='" + getLastName() + '\'' +

	               ", birthDate=" + getBirthDate() +

	               ", birthMonth=" + getBirthMonth() +

	               ", birthYear=" + getBirthYear() +

	               ", finalOralPresentation=" + finalOralPresentation +

	               ", finalThesis=" + finalThesis +

	               ", overallMark=" + calculateOverallMark() +

	               ", finalGrade='" + getFinalGrade() + '\'' +

	               '}';

	   }
	   
	   /**
		  * To CSV Format
		  * @return
		  */
		 public String toCSVFormt() {	
			 	String CSV_SEPARATOR = ",";
				String result = super.toCSVFormt(); 
				result +=  CSV_SEPARATOR
				          + CSV_SEPARATOR
				          + CSV_SEPARATOR
				          + CSV_SEPARATOR+
				          + this.finalOralPresentation + CSV_SEPARATOR
				          + this.finalThesis + CSV_SEPARATOR
				          + this.overallMark + CSV_SEPARATOR
				          + this.finalGrade + CSV_SEPARATOR ;
				return result; 
		}

	}
