package coursework3;

import cw3interfaces.SkillSorterInterface;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class SkillSorter implements SkillSorterInterface{
    private  ArrayList<CommunityGroup> myGroups = new ArrayList<>();
    public ArrayList<Volunteer> group1 = new ArrayList<>();
    public ArrayList<Volunteer> group2 = new ArrayList<>();
    public ArrayList<Volunteer> group3 = new ArrayList<>();
    public ArrayList<Volunteer> group4 = new ArrayList<>();
    public ArrayList<Volunteer> group5 = new ArrayList<>();
    public CommunityGroup Group1 = new CommunityGroup(group1);
    public CommunityGroup Group2 = new CommunityGroup(group2);
    public CommunityGroup Group3 = new CommunityGroup(group3);
    public CommunityGroup Group4 = new CommunityGroup(group4);
    public CommunityGroup Group5 = new CommunityGroup(group5);
    
//
    public SkillSorter(){
    myGroups.add(Group1);
    myGroups.add(Group2); 
    myGroups.add(Group3);
    myGroups.add(Group4);
    myGroups.add(Group5);
    
    }
    @Override
    
    public void addVolunteer(Volunteer vol){    
        boolean []judge=new boolean[5];
        for(int k=0;k<5;k++){
        if(getCommunityGroups().get(k).howManyVolunteers()<500)
            judge[k]=true;
        else if(getCommunityGroups().get(k).howManyVolunteers()==500)
            judge[k]=false;            
        }// judge wheather the group is full .if it is full, boolean value is false.if it is not full ,boolean value is true
        
        
        double[] Deviations = {Deviation(Group1,Group2,Group3,Group4,Group5,vol),
        Deviation(Group2,Group1,Group3,Group4,Group5,vol),Deviation(Group3,Group2,Group1,Group4,Group5,vol),
        Deviation(Group4,Group2,Group3,Group1,Group5,vol),Deviation(Group5,Group2,Group3,Group4,Group1,vol)
        };
        
        int index;
        int secindex;
       double deviation;
       double secdeviation;
        if(Deviations[0]>Deviations[1]){
         deviation=Deviations[1];
        secdeviation=Deviations[0];
        index=1;
        secindex=0;
        }else{
         deviation=Deviations[0];
        secdeviation=Deviations[1];
        index=0;
        secindex=1;
        }
        
       for(int i=2;i<5;i++){
        if(Deviations[i]<=deviation ){
           secdeviation=deviation;
            deviation=Deviations[i];            
             secindex=index;
             index=i;       
        }else{
        if(Deviations[i]<=secdeviation)
        secdeviation=Deviations[i];
        secindex=i;
        }         
        }//get the min deviation value and the second min deviation value along with the index of the group.
         int Maxindex;
        int secMaxindex;            
       double Maxdeviation;
       double secMaxdeviation;
        if(Deviations[0]>Deviations[1]){
         Maxdeviation=Deviations[0];
        secMaxdeviation=Deviations[1];
        Maxindex=0;
        secMaxindex=1;
        }else{
         Maxdeviation=Deviations[1];
        secMaxdeviation=Deviations[0];
        Maxindex=1;
        secMaxindex=0;
        }
               
       for(int i=2;i<5;i++){
        if(Deviations[i]>=Maxdeviation ){
           secMaxdeviation=Maxdeviation;
            Maxdeviation=Deviations[i];            
             secMaxindex=Maxindex;
             Maxindex=i;       
        }else{
        if(Deviations[i]>=secMaxdeviation)
        secMaxdeviation=Deviations[i];
        secMaxindex=i;
        }         
        }//get the max deviation value and the second max deviation value along with the index of the group.
       int[] Allindex={index,secindex,Maxindex,secMaxindex};
       int[] allIndex={0,1,2,3,4};
        int mediaIndex=0;
       boolean []judgeMediaValue=new boolean[5];
        for(int g=0;g<5;g++){
        judgeMediaValue[g]=false;         
        for(int q=0;q<4;q++){
            if(allIndex[g]==Allindex[q]){
            judgeMediaValue[g]=true;
              }
        }
       }
       for(int i=0;i<5;i++){
       if(judgeMediaValue[i]==false){
           mediaIndex=i;}
       }//To get the media value of the five deviations
       
       
       if(judge[index]==true){
        getCommunityGroups().get(index).GetvolunteerGroup().add(vol);
        System.out.println("Volunteer is allocated"+" to group"+(index+1)+".");
       }else{
        if(judge[secindex]==true){
        getCommunityGroups().get(secindex).GetvolunteerGroup().add(vol);
        System.out.println("Volunteer is allocated"+" to group"+(secindex+1)+".");
        }else{
            if(judge[mediaIndex]==true){
            getCommunityGroups().get(mediaIndex).GetvolunteerGroup().add(vol);
            System.out.println("Volunteer is allocated"+" to group"+(mediaIndex+1)+".");
            }else{
            if(judge[secMaxindex]==true){
            getCommunityGroups().get(secMaxindex).GetvolunteerGroup().add(vol);          
            System.out.println("Volunteer is allocated"+" to group"+(secMaxindex+1)+".");
            }else{
            if(judge[Maxindex]==true){
            getCommunityGroups().get(Maxindex).GetvolunteerGroup().add(vol);
            System.out.println("Volunteer is allocated"+" to group"+(Maxindex+1)+".");
            }else{
            System.out.println("All the CommunityGroups are full.");
            }
            
            }          
            }          
        }      
        }      // use if to judge which group to add volunteer.
        
    }
     
    
    
    
    @Override
    public void moveVolunteer(String skillSet, CommunityGroup from, CommunityGroup to){
      boolean judge =false;
         Volunteer vols = new Volunteer(skillSet);
       ListIterator <Volunteer> iterator=from.GetvolunteerGroup().listIterator();
        while(iterator.hasNext()){
         if(Arrays.equals(iterator.next().getSkillNumber(),vols.getSkillNumber())){
         iterator.remove();
        //judge the two int arrays if they are same remove this volunteer object
        to.GetvolunteerGroup().add(vols);
         System.out.println("Volunteer having skill "+skillSet+" is moved from "+toString(from)+" to "+toString(to));
        judge=true;        
         break;
         }            
        }      
        if(judge==false)
          System.out.println("Volunteer with skill "+skillSet+" does not exist in "+toString(from));
    }// use iterator to check a object in volunteer arraylist having same skill with skillset. If it has,delete it and add a new
    //volunteer object to the Volunteer arraylist.
    
    @Override
    public void deleteVolunteer(String skillSet, CommunityGroup from){
       boolean judge=false;
      Volunteer vol = new Volunteer(skillSet);      
       ListIterator <Volunteer> iterator=from.GetvolunteerGroup().listIterator();
        while(iterator.hasNext()){
         if(Arrays.equals(iterator.next().getSkillNumber(),vol.getSkillNumber())){
         iterator.remove();
         //judge the two int arrays if they are same remove this volunteer object
         System.out.println("Volunteer having skill "+skillSet+" is deleted from "+toString(from));
        judge=true;        
         break;
         }            
        }      
        if(judge==false)
          System.out.println("Volunteer with skill "+skillSet+" does not exist in "+toString(from));
    }// use iterator to check a object in volunteer arraylist having same skill with skillset. If it has,delete it.
    
    @Override
    public void deleteAllVolunteers(){
       for(int i=0;i<getCommunityGroups().size();i++){
       getCommunityGroups().get(i).GetvolunteerGroup().clear();
       }
    System.out.println("All volunteers have been deleted.");
    }// use for loop to clear all object in Arraylist<Volunteer>

    @Override
    public ArrayList<CommunityGroup> getCommunityGroups(){             
//return an ArrayList of all this application's CommunityGroups
        
return myGroups;
    }
  
    
  public void display(){
  System.out.println("The data is shown below:");
      for(int i=0;i<5;i++){
        System.out.println("Group "+(i+1)+"  "+getCommunityGroups().get(i).getSkillsTotals()+" Total volunteers: "+getCommunityGroups().get(i).howManyVolunteers());
        
        }//display the data
  
  }
 
private String toString(CommunityGroup group){
for(int i=0;i<getCommunityGroups().size();i++){
if(group==getCommunityGroups().get(i)){
return "Group"+(i+1);
}
}

return "The group does not exist.";
}

private int[] EachSkillValue(CommunityGroup group){
int[] Allvalue = new int[5];
try{ 
            for(int i =0;i<group.GetvolunteerGroup().size();i++){            
             String [] skill = group.GetvolunteerGroup().get(i).getSkillSet().split("");;
            for(int j =0;j<3;j++){
             switch(skill[j]){
              case "A": 
                 Allvalue[0]+=1;
                 break;
              case "B":           
                 Allvalue[1]+=1;
                 break;
              case "C":
                 Allvalue[2]+=1;
                 break;
              case "D":
                 Allvalue[3]+=1;
              break;
              case "E":
                 Allvalue[4]+=1;
               break;            
              default:
                 System.out.println("Wrong skill.");
             }
            }
            }
       }catch(ArrayIndexOutOfBoundsException E){
        System.out.println("WRONG SKILLSET DATA!");
       }     
return Allvalue;//return a integer array with the value of each skill number in a specific ArrayList<Volunteer>
} 

private int[] ChangedSkillValue(CommunityGroup group,Volunteer vol){
int[] skillvalue =EachSkillValue(group);
String[] eachskill=vol.getSkillSet().split("");
for(int i=0;i<3;i++){
if(eachskill[i].equals("A"))
    skillvalue[0]+=1;
else if(eachskill[i].equals("B"))
    skillvalue[1]+=1;
else if(eachskill[i].equals("C"))
    skillvalue[2]+=1;
else if(eachskill[i].equals("D"))
    skillvalue[3]+=1;
else if(eachskill[i].equals("E"))
    skillvalue[4]+=1;
}
return skillvalue;//return a integer value with the value of each skill number in a specific ArrayList<Bolunteer>,after
//getting the volunteer skillset and increasing corresponding skill.
}

private double Deviation(CommunityGroup Changegroup,CommunityGroup group2,CommunityGroup group3,CommunityGroup group4,CommunityGroup group5,Volunteer vol){
int [][] values = {ChangedSkillValue(Changegroup,vol),EachSkillValue(group2),EachSkillValue(group3),EachSkillValue(group4),EachSkillValue(group5)};
    int sum=0;
    for(int i=0;i<5;i++){
    for(int j=0;j<5;j++){
    sum=sum+values[i][j];//calculate the sum of all skills
    }
    }
    double average=sum/25;//calculate the average value of all numbers of skills
    double Deviation;
    double Powersum=0;//calculate the Σ(values[i][j]-average)^2
    
    for(int i=0;i<5;i++){
    for(int j=0;j<5;j++){
        Powersum =Powersum+Math.pow((values[i][j]-average),2);
    }
    }
    
    Deviation=Math.sqrt(Powersum/24);//Calculate (powersum/24)^1/2
     
return Deviation;
}

  public void start(){
      
 
  String[] group={"Group1","Group2","Group3","Group4","Group5"};
  String line="";
  boolean judge=true;
  int counter=0;
  try{
 
  for( int i=0;i<group.length;i++){
       File file = new File("CommunityGroup"+(i+1)+".txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));      
      if((line=reader.readLine()).equals(group[i])){
      while((line=reader.readLine())!=null){        
              if(line.toUpperCase().length()==3){ 
                  String [] skillArray = line.split("");
                      for(int j =0;j<1;j++){
                    boolean []judges=new boolean[3];
                      for(int k=0;k<3;k++){
                if(skillArray[k].matches("[A-E]")==true)// use regular expression to 
    //check the string in skillArray whether is equal to letters from letter A
    //to letter E,if they are equal it will return a boolean value true through 
    // method matches,else it will return false             
                judges[k]=true;
                  else{
               judges[k]=false;
            }
      }    
              for(int n=0;n<3;n++){
              if(judges[n]==true){
              counter++;
               }else{
               counter+=0;
                }
            }    
            } 
              if(counter==3){
              getCommunityGroups().get(i).GetvolunteerGroup().add(new Volunteer(line.toUpperCase()));
              counter=0;
              }
              else{
                  System.out.println("Wrong skillset "+line);
              counter=0;
              }
              }
              else{
                  System.out.println("Wrong skillset "+line);
              counter=0;
              }    
      }   
      }   
      
  file.delete();//Delete the original file
  }
  }catch(IOException e){
      judge =false;
      System.out.println("Error reading file");
  }
  if(judge==true){
  System.out.println("Success in reading data.");
  }
  }
  
  
  public void exit(){
   
 String[] group={"Group1","Group2","Group3","Group4","Group5"};
  for(int i=0;i<5;i++){
    File file = new File("CommunityGroup"+(i+1)+".txt");
         
      try{      
       BufferedWriter fw = new BufferedWriter(new FileWriter(file,true)); 
            fw.write(group[i]);
            fw.newLine();
           for(int j =0;j<getCommunityGroups().get(i).GetvolunteerGroup().size();j++){
       fw.write(getCommunityGroups().get(i).GetvolunteerGroup().get(j).getSkillSet());
       fw.newLine();
       
       }
       fw.flush();
       
       fw.close();
       System.out.println("Data is stored in file(CommunityGroup"+(i+1)+".txt). ");
   }catch (IOException ex) {
            System.out.println("Error writing to file(CommunityGroup"+(i+1)+".txt). ");
        }
       
       
       
   }

//Save data devided by groups into five texts 
}
}