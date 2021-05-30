import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Geektrust extends StationInfo{

	private ArrayList<String> bogiesarraylist(String[] train_individual_bogies)
	{
		ArrayList<String> train_boggies_arraylist = new ArrayList<String>();
		for(int i=0;i<train_individual_bogies.length;i++)
		{
			train_boggies_arraylist.add(train_individual_bogies[i]);			
		}
		return train_boggies_arraylist;
	}
	private ArrayList<String> boggiesBeforemerging(ArrayList<String> train_boggies_arraylist)
	{
		 Iterator trainstations = train_boggies_arraylist.iterator();
		 while(trainstations.hasNext())
		    {
			 String destination=(String)trainstations.next();
		     if((destination.equals("SLM"))||(destination.equals("BLR"))||(destination.equals("KRN"))||(destination.equals("TRAIN_A"))||(destination.equals("TRAIN_B"))||(destination.equals("PNE"))||(destination.equals("MAO")))
		     {
		    	 trainstations.remove();
		     }
		    }
		 return train_boggies_arraylist;	
	}
	private void printTrainAorB(ArrayList<String> trainboggiesarraylist,String train,String situation)
	{
		if(situation.equals("ARRIVAL"))
		{
			System.out.print(situation+" "+train+"\t");
		     for(int i=0;i<trainboggiesarraylist.size();i++)
		     {
			   System.out.print(trainboggiesarraylist.get(i)+"\t");
		     }
		     System.out.println("");			
		}
	}
	private ArrayList<StationInfo> trainAfterMerge(ArrayList<StationInfo> trainAB, ArrayList<String> trainboggiesarraylist)
	{
		if(trainboggiesarraylist.size()>1)
	     {
		   for(int i=0;i<trainboggiesarraylist.size();i++)
		   {
			 StationInfo stations = new StationInfo(); 
			 stations.distance_finder(trainboggiesarraylist.get(i));
			 trainAB.add(stations);
		   }
      	 }
		return trainAB;
	}
	private ArrayList<StationInfo> trainAfterArrangingOrder(ArrayList<StationInfo> trainAB)
	{
		for(int i=0;i<trainAB.size();i++)
	     {
		   for(int j=i+1;j<trainAB.size();j++)
		   {
			 if(trainAB.get(i).Distance>trainAB.get(j).Distance)
			 {
				StationInfo temp=trainAB.get(i);
				trainAB.set(i, trainAB.get(j));
				trainAB.set(j, temp);
			 }
		   }
	     }
		return trainAB;	
	}
	private void printTrainAB(ArrayList<StationInfo>trainABarray,String train,String situation)
	{
		System.out.print(situation +" "+train+" ");
		for(int i=trainABarray.size()-1;i>=0;i--)
	     {
		   if(trainABarray.get(i).station_code==null)
		   {
			   trainABarray.remove(i);
		   }
		   System.out.print(trainABarray.get(i).station_code+" ");	
		   System.out.print(" ");
	     }
	}
	public static void main(String[] args) throws FileNotFoundException 
	{
		try
		{
			 ArrayList<String> trainAboggiesarraylist = new ArrayList<String>();
			 ArrayList<String> trainBboggiesarraylist = new ArrayList<String>();
			 File file = new File("Input.txt");
			 Scanner scanner = new Scanner(file);
			 String trainAallboggies = scanner.nextLine();
			 String trainBallboggies = scanner.nextLine();	
		     scanner.close();
		     Geektrust trainA = new Geektrust(); 
		     String[] trainA_individual_boggies=trainAallboggies.split(" ");
		     trainAboggiesarraylist=trainA.bogiesarraylist(trainA_individual_boggies);
		     Geektrust trainB = new Geektrust();
		     String[] trainB_individual_boggies=trainBallboggies.split(" ");
		     trainBboggiesarraylist= trainB.bogiesarraylist(trainB_individual_boggies);	
		    //updated boggies in each before merging in Hyderabad
		     trainAboggiesarraylist=trainA.boggiesBeforemerging(trainAboggiesarraylist);
		     trainBboggiesarraylist=trainB.boggiesBeforemerging(trainBboggiesarraylist);
		     //print TrainA boggies
		     trainA.printTrainAorB(trainAboggiesarraylist,"TRAIN_A","ARRIVAL");
             //print TrainB boggies
		     trainB.printTrainAorB(trainBboggiesarraylist,"TRAIN_B","ARRIVAL");
		     Geektrust trainABmerge=new Geektrust();
		     ArrayList<StationInfo> trainAB=new ArrayList<>();
		     trainAB=trainABmerge.trainAfterMerge(trainAB, trainAboggiesarraylist);
		     trainAB=trainABmerge.trainAfterMerge(trainAB, trainBboggiesarraylist);
		     trainAB=trainABmerge.trainAfterArrangingOrder(trainAB);
		     trainABmerge.printTrainAB(trainAB,"TRAIN_AB","DEPARTURE");
	    }
		catch(FileNotFoundException e)
		{
			System.out.println("Input File Not Found");
		}
	}
	 
}

 class StationInfo
{
  String station_code;
  int Distance;
  StationInfo()
  {
	  
  }
  StationInfo(String stations, String destination)
  {  
	  
  }

  void distance_finder(String destination)
  {
	  switch(destination)
	  {
	  case "NGP":Distance=400;
	             station_code="NGP";
	             break;
	  case "ITJ":Distance=700;
	             station_code="ITJ";
	             break;
	  case "BPL":Distance=800;
	             station_code="BPL";
	             break;
	  case "PTA":Distance=1800;
	             station_code="PTA";
	             break;
	  case "AGA":Distance=1300;
	             station_code="AGA";
	             break;
	  case "NDL":Distance=1500;
	             station_code="NDL";
	             break;
	  case "NJP":Distance=2200;
	             station_code="NJP";
	             break;
	  case "GHY":Distance=2700;
	             station_code="GHY";
	             break;
   case "ENGINE":Distance=3000;
                 station_code="Engine";
                 break;              
	  default   :break;        
	  }
  }
   
}
