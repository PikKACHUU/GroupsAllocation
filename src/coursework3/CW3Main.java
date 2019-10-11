
package coursework3;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class CW3Main {
   
    public static void main(String[] args){
        CW3Main pros = new CW3Main();
        
    }
    
    
    public CW3Main(){
        
        menu();
       
    }
    
    public void menu(){      
    Scanner s = new Scanner(System.in);
    boolean exit = false;
    SkillSorter sorter = new SkillSorter();
    ArrayList<CommunityGroup> MyGroups = sorter.getCommunityGroups();
    sorter.start();
    
    while(!exit==true){   
    try{
        System.out.println("\nPlease enter number (1-6) to select option.");
    System.out.println("1.Add a volunteer");
    System.out.println("2.Move a volunteer");
    System.out.println("3.Delete a volunteer");
    System.out.println("4.Delete all volunteers");
    System.out.println("5.Display groups");
    System.out.println("6.Save and exit");
        int input = s.nextInt();
        Scanner inputs = new Scanner(System.in);        
         switch(input){
            case 1:                
                System.out.println("Please input the volunteer's skillset: ");
                String skill = inputs.nextLine().toUpperCase();
                int counter=0;
                if(skill.length()==3){
                   String [] skillArray = skill.split("");
                      for(int j =0;j<1;j++){
                    boolean []judge=new boolean[3];
                      for(int i=0;i<3;i++){
                if(skillArray[i].matches("[A-E]")==true)// use regular expression to 
    //check the string in skillArray whether is equal to letters from letter A
    //to letter E,if they are equal it will return a boolean value true through 
    // method matches,else it will return false             
                judge[i]=true;
                  else{
               judge[i]=false;
            }
      }    
              for(int k=0;k<3;k++){
              if(judge[k]==true){
              counter++;
               }else{
               counter+=0;
                }
            }    
            } 
                if(counter==3){
                Volunteer vol = new Volunteer(skill);                               
                sorter.addVolunteer(vol);}
                else{
                    System.out.println("Wrong skillset.");
                    break;}               
                }
                else{
                    System.out.println("Wrong skillset.");
                    break;}
                break;
            case 2:
                int count=0;
                System.out.println("Please input the volunteer's skillset:");
                String skillset = inputs.nextLine().toUpperCase();
                Volunteer vol = new Volunteer(skillset);
                System.out.println("The volunteers having this skillset are in the following group :");                
                for(int i=0;i<5;i++){
                   ArrayList <Volunteer> vols = MyGroups.get(i).GetvolunteerGroup();
                    for(int j =0;j<vols.size() ;j++){
                     if(Arrays.equals(vols.get(j).getSkillNumber(),vol.getSkillNumber())){
                     System.out.println((i+1)+".Group "+(i+1));                                        
                     count+=1;
                     break;//judge the two int arrays if they are same print the corresponding group index
                     }                     
                }              
             }
                try{
                    if(count>0){
                    System.out.println("\nInput the index of group that volunteer moving from :");           
                int movefrom = inputs.nextInt();
               
                
                System.out.println("Please select the group that volunteer moving to:");
               for(int i=1;i<6;i++){
                System.out.println(i+".Group "+i);               
               }
               System.out.println("\nInput the index of group:");
               int moveto = inputs.nextInt();           
               
               try{
                   sorter.moveVolunteer(skillset,MyGroups.get(movefrom-1),MyGroups.get(moveto-1));
               }catch(IndexOutOfBoundsException e){
               System.out.println("WRONG INPUT INDEX! ");
               }
                    }else{
                    System.out.println("No volunteer has this skill.");
                    }

                }catch(java.util.InputMismatchException e){
                System.out.println("WRONG INPUT INDEX! ");
                }
                break;
             case 3:
                int counters=0;
                 System.out.println("Please input the skillset of the volunteer:");
                String deleteVol = inputs.nextLine().toUpperCase();
                Volunteer VOL = new Volunteer(deleteVol);
                System.out.println("The volunteers having this skillset are in the following group :");
                for(int i=0;i<5;i++){
                   ArrayList <Volunteer> vols = MyGroups.get(i).GetvolunteerGroup();
                    for(int j =0;j<vols.size() ;j++){
                     if(Arrays.equals(vols.get(j).getSkillNumber(),VOL.getSkillNumber())){
                     System.out.println((i+1)+".Group "+(i+1));                                        
                     counters+=1;
                     break;//judge the two int arrays if they are same print the corresponding group index
                     }  
                }
                }
                try{
                    if(counters>0){
                    System.out.println("\nPlease input the index of the group that you want to delete the volunteer:");
                int deleteNo = inputs.nextInt();
                try{
                    sorter.deleteVolunteer(deleteVol,MyGroups.get(deleteNo-1));
                }
                catch(IndexOutOfBoundsException e){
                    System.out.println("WRONG INPUT INDEX! ");
                }
                }else{
                    System.out.println("\nNo volunteer has this skill.");
                    }
                }
                catch( java.util.InputMismatchException e ){
                    System.out.println("WRONG INPUT INDEX! ");
                }
                break;             
                case 4:
                 sorter.deleteAllVolunteers();
                 break;
             case 5:
                 sorter.display();
                 break;
             case 6:
                 exit = true;
                 sorter.exit();
                  break;
             default:      
                  System.out.println("Invalid Option.");
    }   
    }catch(java.util.InputMismatchException e){
        System.out.println("Errors happened.");
        exit=true;
        sorter.exit();
        menu();
    }}//use switch to judge the number user input and execute related function
}
}