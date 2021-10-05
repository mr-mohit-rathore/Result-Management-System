import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMarksheetModel extends MarksheetModel{

public static void main(String [] agrs)throws Exception{

    Scanner input=new Scanner(System.in);

    String rollNo=null;
    String name=null;
    int physics=0;
    int chemistry=0;
    int maths=0;
    boolean checkRollNO;
    boolean checkr;
    boolean checkDigit;


char yn='y';
MarksheetModel mm=new MarksheetModel();
Marksheet m=new Marksheet();


System.out.println("Welcome to Our Console Result Management System.");
System.out.println("Let's Start!!!");

do{
    Scanner sc=new Scanner(System.in);
try{

//set classpath=;C:\Program Files\MySQL\Connector J 8.0\mysql-connector-java-8.0.26.jar
   


System.out.println("If you want to add your Marksheet ,Please Press 1");
System.out.println("If you want to Update your Marksheet ,Please Press 2");
System.out.println("If you want to Get your Marksheet ,Please Press 3");
System.out.println("If you want to see Merit List ,please Press 4");
System.out.println("If you to delete you marksheet,please Press 5");
System.out.println("If you want to exit, press 6 \n");

int choice=sc.nextInt();
switch(choice){
    case 1:
    
    System.out.print("Enter Student Roll no. : ");// entering roll number of student
        rollNo = sc.next();

         checkRollNO=validatRollNo(rollNo);
        if(checkRollNO==false){
             
            throw new MyException("Roll Number Format should be like 0873CS181010");
        }
        
            m.setRollNo(rollNo);// we must set here
        checkr=checkRecord(m);
        if(checkr){
          throw new MyException("This record already exists.");
        }
        

        System.out.print("Enter Student Name : ");// entering name of student
        name=sc.next();
        name+=sc.nextLine();
       
        if(name.length()>30){
            throw new MyException("Please Enter your name less than 30 characters.");
        }
       
         checkDigit=true;

        if(name !=null && !name.isEmpty()){
            for(char a:name.toCharArray()){
                if(checkDigit=Character.isDigit(a)){
                    throw new MyException("Please Enter Student's Name without any special Character or Digit");
                }
            }
        }
        else{
            System.out.println("please Enter student name.");
        }

    
        System.out.print("Enter Student's physics Marks : ");  // entering their physics marks
        
       physics= sc.nextInt();

       if(physics>100 || physics<0){
           throw new MyException("Please Enter your Marks between 0 to 100 only.");
       }
      
        

        System.out.print("Enter Student's chemistry Marks : ");//entering chemistry marks
        
       chemistry= sc.nextInt();
        
       if(chemistry<0 || chemistry>100){
            throw new MyException("Please Enter your Marks between 0 to 100 only.");
       }
      
    

        System.out.print("Enter Student's Mathematics Marks : ");
        maths= sc.nextInt();

        if(maths<0 || maths>100){
            throw new MyException("Please Enter your Marks between 0 to 100 only.");
       }

//set all user input value through marksheet object accept rollno because we have to check record in database
        
        m.setName(name);
        m.setPhysics(physics);
        m.setChemistry(chemistry);
        m.setMaths(maths);



    boolean b=mm.add(m);
    if(b){
        System.out.println("Message ==> Record Inserted Successfully!!");
    }
    else{
        System.out.println("Message ==> Something went Wrong!!!");
    }
    
    break;


//case  2 for update result

    case 2:

         System.out.print("Enter Students Roll Number: ");
   
             rollNo=sc.next();

         checkRollNO=validatRollNo(rollNo);
        if(checkRollNO==false){
             
            throw new MyException("Roll Number Format should be like 0873CS181010");
        }
        
            m.setRollNo(rollNo);// we must set here
        checkr=checkRecord(m);
        if(checkr){
       
    
        System.out.print("Enter Student's physics Marks : ");  // entering their physics marks
        
       physics= sc.nextInt();

       if(physics>100 || physics<0){
           throw new MyException("Please Enter your Marks between 0 to 100 only.");
       }
      
        

        System.out.print("Enter Student's chemistry Marks : ");//entering chemistry marks
        
       chemistry= sc.nextInt();
        
       if(chemistry<0 || chemistry>100){
            throw new MyException("Please Enter your Marks between 0 to 100 only.");
       }
      
    

        System.out.print("Enter Student's Mathematics Marks : ");
        maths= sc.nextInt();

        if(maths<0 || maths>100){
            throw new MyException("Please Enter your Marks between 0 to 100 only.");
       }

//set all user input value through marksheet object accept rollno because we have to check record in database
        
        m.setName(name);
        m.setPhysics(physics);
        m.setChemistry(chemistry);
        m.setMaths(maths);


         boolean b1=mm.update(m); 

         if(b1){
             System.out.println("Message ==> Student's Result Data updated Successfully.");
         }
         else{
             System.out.println("Message ==> Something went wrong, TRY AGAIN !!!");
         }
        
        }
        else{
            throw new MyException("Student Record not exist.");
        }

    

    break;

// get reuslt of student
   
   // case 3 is from here
    case 3:

    System.out.print("Enter your Roll no. : ");
    rollNo=sc.next();
checkRollNO=validatRollNo(rollNo);
        if(checkRollNO==false){
             
            throw new MyException("Roll Number Format should be like 0873CS181010");
        }
        
            m.setRollNo(rollNo);// we must set here
        checkr=checkRecord(m);

        if(checkr==false){
            throw new MyException("Student Record not exists.");
        }
        else{

            Marksheet m2=mm.get(rollNo);
    
            System.out.println("Student Roll No. : "+m2.getRollNo());
            System.out.println("Student Name : "+m2.getName());
            System.out.println("Student Physics Marks : "+m2.getPhysics());
            System.out.println("Student Chemistry Marks : "+m2.getChemistry());
            System.out.println("Student Mathematics Marks : "+m2.getMaths());

            // System.out.println();
            // System.out.print("Student result is"+);
        }

    
    
    break;
 // case 4 is from here   
 // get merit list
    case 4:
    ArrayList al=mm.getMeritList();

    if(al.size()<=0){
        throw new MyException("There is no student who is pass with Merit");
    }
    else{

  
	System.out.println("Student who pass with merit are:  "+al);
    
    }
    break;


//case 5 is here
// delete particular data from database
    case 5:
   
    System.out.print("Enter Student's Roll No. : ");
    rollNo=sc.next();

    
         checkRollNO=validatRollNo(rollNo);
        if(checkRollNO==false){
             
            throw new MyException("Roll Number Format should be like 0873CS181010");
        }
        
            m.setRollNo(rollNo);// we must set here
        checkr=checkRecord(m);
        if(checkr==false){
          throw new MyException("This record already exists.");
        }
        else{
            boolean b2=mm.delete(m);
    
    if(b2){
        System.out.println("Message ==> Student's Result deleted Successfully !!");
    }
    else{
    System.out.println("Message ==> Something went Wrong ,TRI AGAIN");

    }
        }
    
    break;

    case 6: System.exit(0);
    break;
    default :
    System.out.println("Please enter your choice less than 7");
}//end of switch
}//end of try
    
    catch(InputMismatchException imme){
					System.out.println("Your Entered Wrong Value.");
				}
	catch(MyException me){
      				System.out.println(me);
      			}
    catch(NoSuchElementException nsee){
        System.out.print("NOT ENTERED ANY ELEMENT");
    }

System.out.print("If You want to continue press 'Y' otherwise press Any Character : ");
yn=input.next().charAt(0);

}while(yn=='Y' || yn=='y');


input.close();

}

// this method is check the roll number is follow rule set or no
public static boolean validatRollNo(String rollno){
	final String regex = "0873[A-Za-z][A-za-z][0-9][0-9][0-9][0-9][0-9][0-9]";

		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(rollno);

		if (matcher.find()){
			return true;
		}else{
			return false;
		}	
}
}