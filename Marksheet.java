public class Marksheet{
    public String rollNo=null;
    public String name=null;
    public int physics=0;
    public int chemistry=0;
    public int maths=0;


    // setter methods
    public void setRollNo(String rollNo){
        this.rollNo=rollNo;
    }

    public void setName(String name){
        this.name=name;
    }
    
    public void setPhysics(int physics){
        this.physics=physics;
    }

    public void setChemistry(int chemistry){
        this.chemistry=chemistry;
    }

    public void setMaths(int maths){
        this.maths=maths;
    }


    //getter methods
    public String getRollNo(){
        return rollNo;
    }

    public String getName(){
        return name;
    }

    public int getPhysics(){
        return physics;
    }
    
    public int getChemistry(){
        return chemistry;
    }
    public int getMaths(){
        return maths;
    }

}