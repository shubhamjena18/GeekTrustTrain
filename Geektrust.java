import java.util.*;

public class Geektrust {
              
	static class TrainData
	{
	  String station;
	 // String train;
	  int Distance;
	  TrainData(String destination)
	  {  
		  switch(destination)
		  {
		  case "NGP":Distance=400;
		             station="NGP";
		             break;
		  case "ITJ":Distance=700;
		             station="ITJ";
		             break;
		  case "BPL":Distance=800;
		             station="BPL";
		             break;
		  case "PTA":Distance=1800;
		             station="PTA";
		             break;
		  case "AGA":Distance=1300;
		             station="AGA";
		             break;
		  case "NDL":Distance=1500;
		             station="NDL";
		             break;
		  case "NJP":Distance=2200;
		             station="NJP";
		             break;
		  case "GHY":Distance=2700;
		             station="GHY";
		             break;
	   case "ENGINE":Distance=2700;
                     station="Engine";
                     break;              
		  default   :break;        
		  }
	  }
	}
	public static void main(String[] args)
	{
		
		ArrayList<String> trainA = new ArrayList<String>();
		ArrayList<String> trainB = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		String str1=sc.nextLine();
		String[] srr1=str1.split(" ");
		
			for(int j=0;j<srr1.length;j++)
			{
				trainA.add(srr1[j]);	
			}			
		String str2=sc.nextLine();
		String[] srr2=str2.split(" ");
			for(int j=1;j<srr2.length;j++)
			{
				trainB.add(srr2[j]);
			}	
		Iterator trainAstation = trainA.iterator();
		while(trainAstation.hasNext())
		{
			String destination=(String)trainAstation.next();
		    if((destination.equals("SLM"))||(destination.equals("BLR"))||(destination.equals("KRN"))||(destination.equals("TRAIN_A")))
		    {
		    	trainAstation.remove();
		    }
		}
		
		Iterator trainBstation = trainB.iterator();
		while(trainBstation.hasNext())
		{
			String destination=(String)trainBstation.next();
			if((destination.equals("SRR"))||(destination.equals("MAQ"))||(destination.equals("MAO"))||(destination.equals("PNE"))||(destination.equals("TRAIN_B")))
		    {
		    	trainBstation.remove();
		    }
		}
		System.out.print("ARRIVAL TRAIN_A"+"\t");
		for(int i=0;i<trainA.size();i++)
		{
			System.out.print(trainA.get(i)+"\t");
		}
		System.out.println("");
		System.out.print("ARRIVAL TRAIN_B"+"\t");
		for(int i=0;i<trainB.size();i++)
		{
			System.out.print(trainB.get(i)+"\t");
		}
		System.out.println("");
		ArrayList<TrainData> trainAB=new ArrayList<>();
		if(trainA.size()>1)
		{
			for(int i=0;i<trainA.size();i++)
			{
				trainAB.add(new TrainData(trainA.get(i)));
			}
		}
		if(trainB.size()>1)
		{
			for(int i=0;i<trainB.size();i++)
			{
				trainAB.add(new TrainData(trainB.get(i)));
			}
		}
		for(int i=0;i<trainAB.size();i++)
		{
			for(int j=i+1;j<trainAB.size();j++)
			{
				if(trainAB.get(i).Distance>trainAB.get(j).Distance)
				{
					TrainData temp=trainAB.get(i);
					trainAB.set(i, trainAB.get(j));
					trainAB.set(j, temp);
				}
			}
		}
		System.out.print("DEPARTURE TRAIN_AB"+"\t");
		for(int i=trainAB.size()-1;i>=0;i--)
		{
			if(trainAB.get(i).station==null)
			{
				trainAB.remove(i);
			}
			System.out.print(trainAB.get(i).station+" ");	
			System.out.print(" ");
		}
		sc.close();
	}
}
