package coursework3;

import cw3interfaces.VolunteerInterface;



public class Volunteer implements VolunteerInterface {
   public String skills ;
 
    public Volunteer(String skill){
    this.skills = skill;
    }
   
    
    
   @Override
    public String getSkillSet(){
     return skills;   //return the string skills
    }
    
    
    public int[] getSkillNumber(){
    String[] skillValue={"A","B","C","D","E"};
    String skillset =getSkillSet();
    String[] Judge =skillset.split("");
       int[] judge = new int[5];
    for(int i=0;i<judge.length;i++){
        for (String Judge1 : Judge) {
            if (skillValue[i].equals(Judge1)) {
                judge[i]++;
            }
        }
    
    }
    return judge;
    }
}
