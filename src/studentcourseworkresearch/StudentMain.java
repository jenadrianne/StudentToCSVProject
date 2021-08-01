package studentcourseworkresearch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainclass {
	private List<Student> studentList = new ArrayList<>();
	public static Scanner scanners = new Scanner(System.in); 
	public static boolean isSorted = false;
	
	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String [] args) {
		StudentMain sr = new StudentMain();
		int option = 0;
				
		//load students from student.txt
	    sr.loadStudents();
	     
		//Show the menu    
	     try {
			do {
				option = sr.showMenu(); 
			    switch(option) {
			    	case 1:
			    		  System.exit(0);
			    		  break;
			        case 2:
			        	  System.out.println("\nEnter what student info to read: \n"
			        	  		+ "1-CourseWorkStudent.txt\n"
			        	  		+ "2-ResearchStudent.txt "); 
			    	      int txtopt = scanners.nextInt();
			    	      if(txtopt==1) {
				              sr.loadCourseWorkStudentMarks();
			    	      }else {
				              sr.loadResearchStudentMarks();
			    	      }
			    	      
			              break; 
			       case 3:
			    	      System.out.println("\nEnter Student id to delete: "); 
			    	      int studentid = scanners.nextInt();
			    	   	  sr.deleteStudentRecord(studentid);
			    	   	  break;
			       case 4:
			    	      sr.printStudentRecords();
			    	      break;
			       case 5:
			    	  System.out.println("\nEnter what student info to compute: \n"
			        	  		+ "1-CourseWorkStudent\n"
			        	  		+ "2-ResearchStudent "); 
		    	      txtopt = scanners.nextInt();
		    	      if(txtopt==1) {
				    	  sr.computeAndOutputOverAllMark(true);
		    	      }else {
				    	  sr.computeAndOutputOverAllMark(false);
		    	      }
			       case 6:
			    	    System.out.println("\nEnter what student info to display: \n"
			        	  		+ "1-CourseWorkStudent\n"
			        	  		+ "2-ResearchStudent "); 
			    	      txtopt = scanners.nextInt();
			    	      if(txtopt==1) {
					    	   	 sr.studentGradeStatistics(true);
			    	      }else {
					    	   	 sr.studentGradeStatistics(false);
			    	      }
			    	     break;
			       case 7:
			    	      System.out.print("\n Enter Student ID to search: ");
				          long id = scanners.nextLong();
				          sr.printStudentRecord(id);
			    	      break;
			       
			       case 8:
				          System.out.print("\n Enter student's Last name to search: ");
				          String lastName = scanners.next();
				          System.out.print("\n Enter student's First name to search: ");
				          String firstName = scanners.next();
				          sr.printStudentRecord(firstName, lastName);
				          break;
			       case 9 : 
			    	   	  isSorted = sr.sortRecords();
			    	   	  break; 
			       case 10: 
			    	   	  if(isSorted) {
			    	   		  sr.writeToCsv();
			    	   	  }else {
			    	   		  System.out.println("Please Sort Array first"); 
			    	   	  }
			    	   	  break;
			       default:
			    	   	   System.out.print("\n Invalid choice!!");
			    }
			 }while(option != 1);
		} catch (Exception e) {
			System.out.println("Invalid input. Terminating the program....");
		}  
	     
	     scanners.close();
	  }
	
	 /**
	  * Show menu options
	  * @return
	  */
	  public static  int showMenu()
	  {
		 int option = 0; 
	     System.out.print("\n\n ************** MENU ************** ");
	     System.out.print("\n\t 1 - Quit "
	     					+ "\n\t 2 - Add CourseWork or Research Information"
	     					+ "\n\t 3 - Remove Student" 
	     					+ "\n\t 4 - Show All Student Information "
	     					+ "\n\t 5 - Compute Grades"
	     					+ "\n\t 6 - Students Below or Above average" 
	     					+ "\n\t 7 - Search Student by ID "
	     					+ "\n\t 8 - Search Student by name"
	     					+ "\n\t 9 - Sort Student by ID"
	     					+ "\n\t 10 - Output Sorted Array"
	     					+ "\n\n What is your choice? ");
	     option = scanners.nextInt();
	     return option; 
	  }
	  
	  /**
	   * Load basic student information
	   */
	   public void loadStudents() {
	       try {
	
	           InputStream inputStream = new FileInputStream("student.txt");
	           int counter = 0;
	
	           BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
	           for (String line; (line = br.readLine()) != null; counter++) {
	               String[] studentInfo = line.split(" ");
	               Student student = new Student();
	
	 
	               student.setTitle(studentInfo[0]);
	               student.setFirstName(studentInfo[1]);
	               student.setLastName(studentInfo[2]);
	               student.setStudentId(Long.valueOf(studentInfo[3]));
	               student.setBirthDate(Integer.valueOf(studentInfo[4]));
	               student.setBirthMonth(Integer.valueOf(studentInfo[5]));
	               student.setBirthYear(Integer.valueOf(studentInfo[6]));
	
	 
	
	               System.out.println("Loaded : " + student);
	               studentList.add(student);
	
	           }
	
	       } catch (IOException e) {
	    	   e.printStackTrace();
	    	}
	
	   }

}
