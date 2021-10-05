import java.util.*;
import java.sql.*;


public class MarksheetModel extends Marksheet{
    


//____________________________________________ Connection Establishment________________________________________________
 

    public static Connection createConnection() throws ClassNotFoundException,SQLException{
		 
        ResourceBundle rb= ResourceBundle.getBundle("connectivity"); // creating ResourceBundle Object

          //  System.out.println("Successfully Created Resource bundle objects");
       
     Class.forName(rb.getString("Driver")); // load the driver
      // System.out.println("Driver loaded successfully.");
    
       //driver was not uploaded due to path not set of j connector
     // use this to set path on cmd 
    // set classpath=;C:\Program Files\MySQL\Connector J 8.0\mysql-connector-java-8.0.26.jar

 Connection con = DriverManager.getConnection(rb.getString("url"),rb.getString("uid"),rb.getString("pwd"));// connection establishment
      // System.out.println("Connection establish successfully.");
    
   return con;
}


//____________________________________________ Insert Record In Database________________________________________________
 
// Case : 1 execute

    public boolean add(Marksheet m)throws Exception{
      boolean ans=true; 

        

       Connection con=createConnection();

      PreparedStatement ps=con.prepareStatement("insert into StudentData(rollNo,name,physics,chemistry,maths)values(?,?,?,?,?)");

    // set all correspoding values of student in database
      ps.setString(1,m.getRollNo());
      ps.setString(2,m.getName());
      ps.setInt(3,m.getPhysics());
      ps.setInt(4,m.getChemistry());
      ps.setInt(5,m.getMaths());

      int ch=ps.executeUpdate();//this is very important line here without writing this line we can not execute our query
       
       System.out.println(ch +" row(s) affected in database.");
       con.close();
       
       // System.out.println("You entered ="+m.rollNo+" "+m.name+" "+m.physics+" "+m.chemistry+" "+m.maths);    
    
    return ans;
    }


//____________________________________________ update Record In Database________________________________________________
//case 2 : execute
    public boolean update(Marksheet m)throws Exception{

    Connection con=createConnection();
  
  
    PreparedStatement ps=con.prepareStatement("update StudentData SET physics=?, chemistry=?, maths=? where rollNo=?");

    ps.setInt(1,m.getPhysics());
    ps.setInt(2,m.getChemistry());
    ps.setInt(3,m.getMaths());
    ps.setString(4,m.getRollNo());

    int ch=ps.executeUpdate();

    System.out.println(ch+" row(s) affected in Database.");

    con.close();
    return true;

    }


//____________________________________________ Get details of Student ________________________________________________
 
//case : 3 execute
// get marksheet of Student
    public Marksheet get(String rollNo) throws ClassNotFoundException,SQLException{

 Connection con=createConnection();
  Marksheet mk=new Marksheet();
  
    PreparedStatement ps=con.prepareStatement("select * from StudentData where rollNo=?");

         ps.setString(1,rollNo);


         ResultSet rs=ps.executeQuery();
  while(rs.next()){
    mk.setRollNo(rs.getString(1));
    mk.setName(rs.getString(2));
    mk.setPhysics(rs.getInt(3));
    mk.setChemistry(rs.getInt(4));
    mk.setMaths(rs.getInt(5));
  }
          con.close();
          return mk;
    }

//____________________________________________ Get MeritList from Database________________________________________________
// case 4 execute 
    public ArrayList getMeritList() throws ClassNotFoundException,SQLException{
	ArrayList al=new ArrayList();
  
 Connection con=createConnection();
  
  
    PreparedStatement ps=con.prepareStatement("select name from StudentData where (physics>=60 && chemistry>=60 && maths>=60)");

    ResultSet rs=ps.executeQuery();


    while(rs.next()){
      al.add(rs.getString(1));
    }
       con.close();
        return al;
    }


//____________________________________________DELETE Result from Database________________________________________________
// case 5 execute
    public boolean delete(Marksheet m) throws ClassNotFoundException,SQLException{
         Connection con=createConnection();
  
  
    PreparedStatement ps=con.prepareStatement("delete from StudentData where rollNo=?");

    ps.setString(1,m.getRollNo());

    int ch=ps.executeUpdate();
    System.out.println(ch+" row(s) affected in database");
        con.close();
        return true;
    }


	//____________________________________________ Check Record In Database________________________________________________
  
  
    public static boolean checkRecord(Marksheet ms) throws ClassNotFoundException,SQLException{
		
			Connection con=createConnection();
		PreparedStatement ps= con.prepareStatement("select * from studentData where rollNo=?");
		ps.setString(1,ms.getRollNo());

		ResultSet rs=ps.executeQuery();
		boolean result;
		if(rs.next()){
			result=true;
		}
		else{
			result=false;
		}
		con.close();
		return result;
	}

  
}