package studentcourseworkresearch;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class StudentMain {
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
			    	      break;
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
	               String[] studentInfo = line.split(",");
	               Student student = new Student();
	
	 
	               student.setTitle(studentInfo[0]);
	               student.setFirstName(studentInfo[1]);
	               student.setLastName(studentInfo[2]);
	               student.setStudentId(Long.valueOf(studentInfo[3]));
	               student.setBirthDate(Integer.valueOf(studentInfo[4]));
	               student.setBirthMonth(Integer.valueOf(studentInfo[5]));
	               student.setBirthYear(Integer.valueOf(studentInfo[6]));
	
	 
	
	               System.out.println("Loaded : \n" + student + "\n---------------------\n");
	               studentList.add(student);
	
	           }
	
	       } catch (IOException e) {
	    	   e.printStackTrace();
	    	}
	
	   }

   /**
    * Load course work student
    */
   public void loadCourseWorkStudentMarks() {
	   try {
		   InputStream inputStream = new FileInputStream("courseWorkStudent.txt");

           int counter = 0;

           BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

           for (String line; (line = br.readLine()) != null; counter++) {
        	   String[] studentMarks = line.split(",");
        	   int index = getIndexByStudentId(Long.valueOf(studentMarks[0]));
        	   
        	   if (index >= 0) {

                   CourseWorkStudent courseWorkStudent = new CourseWorkStudent(studentList.get(index));

                   courseWorkStudent.setAssignment1(Double.valueOf(studentMarks[1]));
                   courseWorkStudent.setAssignment2(Double.valueOf(studentMarks[2]));
                   courseWorkStudent.setWeeklyPracticalWork(Double.valueOf(studentMarks[3]));
                   courseWorkStudent.setFinalExam(Double.valueOf(studentMarks[4]));
                   courseWorkStudent.calculateOverallMark();
                   
	               System.out.println("Loaded : \n" + courseWorkStudent + "\n---------------------\n");
                   studentList.set(index, courseWorkStudent);
               }

           }

       } catch (IOException e) {
           e.printStackTrace();
       }

   }

 
   /**
    * Load Research student
    */
   public void loadResearchStudentMarks() {
       try {
           InputStream inputStream = new FileInputStream("researchStudent.txt");
           int counter = 0;

           BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

           for (String line; (line = br.readLine()) != null; counter++) {
               String[] studentMarks = line.split(",");
               int index = getIndexByStudentId(Long.valueOf(studentMarks[0]));

               if (index >= 0) {
                   ResearchStudent researchStudent = new ResearchStudent(studentList.get(index));
                   
                   researchStudent.setFinalOralPresentation(Double.valueOf(studentMarks[1]));
                   researchStudent.setFinalThesis(Double.valueOf(studentMarks[2]));
                   researchStudent.calculateOverallMark();
                   
	               System.out.println("Loaded : \n" + researchStudent + "\n---------------------\n");

                   studentList.set(index, researchStudent);
               }
           }

       } catch (IOException e) {
           e.printStackTrace();
       }

   }

   public void computeAndOutputOverAllMark(boolean isCourseWork) {
       for (Student student : studentList) {
           if (isCourseWork && student instanceof CourseWorkStudent) {
               System.out.println(student.getStudentId() + " " + student.getTitle() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.calculateOverallMark() + " " + ((CourseWorkStudent) student).getFinalGrade());
           } else if (!isCourseWork && student instanceof ResearchStudent) {
               System.out.println(student.getStudentId() + " " + student.getTitle() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.calculateOverallMark() + " " + ((ResearchStudent) student).getFinalGrade());
           }

       }

   }


   public void studentGradeStatistics(boolean isCourseWork) {
       double grades = 0;
       int totalStudent = 0;

       for (Student student : studentList) {
    	   if (isCourseWork && student instanceof CourseWorkStudent) {
               grades += student.calculateOverallMark();
               totalStudent++;
           } else if (!isCourseWork && student instanceof ResearchStudent) {
               grades += student.calculateOverallMark();
               totalStudent++;
           }

       }

 
       double averageGrade = grades / totalStudent;
       int belowAverage = 0;
       int equalOrAboveAverage = 0;

       for (Student student : studentList) {
           if (isCourseWork && student instanceof CourseWorkStudent) {
               if (student.calculateOverallMark() >= averageGrade) {
                   equalOrAboveAverage++;
               } else {
                   belowAverage++;
               }

           } else if (!isCourseWork && student instanceof ResearchStudent) {
               if (student.calculateOverallMark() >= averageGrade) {
                   equalOrAboveAverage++;
               } else {
                   belowAverage++;
               }

           }

       }

       System.out.println(equalOrAboveAverage + " students scored equal or above the average and " + belowAverage + " students fall below the average.");
   }

 
   /**
    * Get the position of the student in the list
    */
   public int getIndexByStudentId(long id) {
       int index = -1;
       for (Student student : studentList) {
           index++;
           if (student.getStudentId() == id) {
               return index;
           }

       }
       //student not found
       return -1;
   }

 
   /**
    * Get Student ID and Name
    */
   public String getStudentIdAndName(long id) {
       int index = getIndexByStudentId(id);
       if (index < 0) {
           //student not found
           return "Student with id " + id + " not found.";
       }

       Student student = studentList.get(index);

       return student.getStudentId() + " " + student.getFirstName() + " " + student.getLastName();
   }

 
   /**
    * Delete student record by id
    * @param id
    * @return
    */
   public boolean deleteStudentRecord(long id) {

       int index = getIndexByStudentId(id);

       if (index < 0) {
           //student not found
           System.out.println("Student with id " + id + " not found.");
           return false;

       }
       Student student = studentList.remove(index);
       return true;

   }

 
   /**
    * Print Student Records 
    */
   public void printStudentRecords() {
       for (Student student : studentList) {
           System.out.println(student.toString());
       }
   }

 
   /**
    * Print student record given the id
    */
   public void printStudentRecord(long id) {
       int index = getIndexByStudentId(id);
       
       if (index < 0) {
           //student not found
           System.out.println("Student with id " + id + " not found.");
           return;
       }
       Student student = studentList.get(index);
       System.out.println(student.toString());

   }

 
   /**
    * Print student record given the first name and the last name
    * @param firstName
    * @param lastName
    */
   public void printStudentRecord(String firstName, String lastName) {
       int count = 0;
       for (Student student : studentList) {
           if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
               System.out.println(student.toString());
               //there could be people with the same name, hence we're not exiting
               count++;
           }
       }

       if (count == 0) {
    	   System.out.println("Student with name " + firstName + " " + lastName + " not found.");
       }

   }

 
   /**
    * Sort Records
    */
   public boolean sortRecords() {
       Student[] studentArray = studentList.toArray(new Student[0]);
       
       try {
		mergeSort(studentArray, studentArray.length);
		   
		   studentList = Arrays.asList(studentArray);
		   
		   System.out.println("New sorted array: ");
		   
		   printStudentRecords();  
		   
		   return true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
   }

 
   /**
    * Mergo Sort
    * @param studentsArr
    * @param length
    */
   public static void mergeSort(Student[] studentsArr, int length) {
       if (length < 2) {
           return;

       }

       int mid = length / 2;
       Student[] left = new Student[mid];
       Student[] right = new Student[length - mid];

       for (int i = 0; i < mid; i++) {
           left[i] = studentsArr[i];
       }

       for (int i = mid; i < length; i++) {
           right[i - mid] = studentsArr[i];
       }

       //split and sort

       mergeSort(left, mid);
       mergeSort(right, length - mid);

       //merge

       int i = 0, j = 0, k = 0;
       while (i < mid && j < length - mid) {
           if (left[i].getStudentId() <= right[j].getStudentId()) {
               studentsArr[k++] = left[i++];
           } else {
               studentsArr[k++] = right[j++];
           }
       }

       while (i < mid) {
           studentsArr[k++] = left[i++];
       }

       while (j < length - mid) {
           studentsArr[k++] = right[j++];
       }
   }

 
   /**
    * Write to CSV;
    */
   public void writeToCsv() {
       try (PrintWriter writer = new PrintWriter("students.csv")) {
    	   StringBuilder sb = new StringBuilder();
    	   String CSV_SEPARATOR = ",";
 

    	   //header
    	   sb.append("ID" + CSV_SEPARATOR);
    	   sb.append("Title" + CSV_SEPARATOR);
    	   sb.append("First Name" + CSV_SEPARATOR);
    	   sb.append("Last Name" + CSV_SEPARATOR);
    	   sb.append("Birthday" + CSV_SEPARATOR);
    	   sb.append("Assignment 1" + CSV_SEPARATOR);
    	   sb.append("Assignment 2" + CSV_SEPARATOR);
    	   sb.append("Practical" + CSV_SEPARATOR);
    	   sb.append("Final Examination" + CSV_SEPARATOR);
    	   sb.append("Oral Presentation" + CSV_SEPARATOR);
    	   sb.append("Final Thesis" + CSV_SEPARATOR);
    	   sb.append("Weighted Average" + CSV_SEPARATOR);
    	   sb.append("Final Grade" + CSV_SEPARATOR);
           sb.append("\n");
           
           for (Student student : studentList) {
        	   sb.append(student.toCSVFormt());
        	   sb.append("\n");
           }
           
           writer.write(sb.toString());

           System.out.println("Done writing to CSV!");

       } catch (FileNotFoundException e) {
           System.out.println("Error saving into file!" + e.getMessage());
       }
   }

}
