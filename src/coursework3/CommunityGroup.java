package coursework3;

import cw3interfaces.CommunityGroupInterface;
import java.util.ArrayList;



public class CommunityGroup implements CommunityGroupInterface {
    public ArrayList <Volunteer> group = new ArrayList<>();
   
    public CommunityGroup(ArrayList <Volunteer> vol){
    
    this.group = vol;
    }
       
    
    public ArrayList<Volunteer> GetvolunteerGroup(){
    return group;//return the ArrayList group
    
    }
    
    @Override
    public int howManyVolunteers(){
        if(group.size()>0){
       return group.size();
        }
       
//return the total number of volunteers in this community group       
        return 0;
    }
    
    @Override
    public String getSkillsTotals(){
        int a=0;
        int b=0;
        int c=0;
        int d=0;
        int e=0;
        int f=0;
        try{ 
            for(int i =0;i<group.size();i++){            
             String [] skill = group.get(i).getSkillSet().split("");;
            for(int j =0;j<3;j++){
             switch(skill[j]){
              case "A": 
                 a+=1;
                 break;
              case "B":           
                 b+=1;
                 break;
              case "C":
                 c+=1;
                 break;
              case "D":
                 d+=1;
              break;
              case "E":
                 e+=1;
               break;            
              default:
                 System.out.println("Wrong skill.");
             }
            }
            }
       }catch(ArrayIndexOutOfBoundsException E){
        System.out.println("WRONG SKILLSET DATA!");
       }     //calculate each skill number in a specific group
          f=a+b+c+d+e;
// return the total number of each skill in a String, example:
        //Skill A: 13, Skill B: 20, Skill C: 23, Skill D: 5, Skill E: 41       
        return "Skill A: "+a+", Skill B: "+b+", Skill C: "+c+", Skill D: "+d+", Skill E: "+e+", Total Skills : "+f;
    }
}
