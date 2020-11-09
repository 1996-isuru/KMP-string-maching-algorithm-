import java.io.*;
//import java.io.FileReader;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
import java.util.*;
//import java.util.Scanner;

public class kmp
{
	public static void main(String[] args)
	{ 
		
		Vector<Character> list = new Vector<Character>();

		File f = new File("pi.txt");
			try
		{
			list=readLines(f);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("\n\t       KMP Algorithum\n");
		String fileName="results.txt";
		    	
		    	String e = new String();
		    	//input string want to search
		    	Scanner suu = new Scanner(System.in);
				System.out.println("Enter your string: ");
				e = suu.nextLine();
				try
				{    
		           BufferedWriter results=new BufferedWriter(new FileWriter(fileName,true));
		           results.write("\n\t       KMP Algorithum");
		           results.write("\nSearch results of : "+e+"\n");
		           results.close();    
		        }
		        catch(Exception e3)
		        {	
		        	System.out.println(e3);
		        }
				
				//Create array for prefix table
				int[] table = new int[e.length()];
				int g = 0;
				//array of integer store suffix-prefix table
				table[0] = 0;
				
				for (int b = 1; b < e.length();) {
					if (e.charAt(b) == e.charAt(g)) {
					g++;
					table[b] = g;
					b++;
				} else {
					if (g != 0) {
						g = table[g - 1];
					} else {
						table[b] = 0;
						b++;
					}
				  }
				}
				int count=0;
				boolean status = false;
				int txtPos = 0;
				int patternPos = 0;
				
					while (txtPos<list.size()&&patternPos < e.length()) {
					   if(e.charAt(patternPos) == list.get(txtPos)) {
						patternPos++;
						txtPos++;		
						if (patternPos == e.length()) {
							status = true;
							System.out.println("Matching substring found, starting at :"+ (txtPos - patternPos ));
							try
				        	{ 
		   
				            	BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
				            	out.write("Matching substring found, starting at : "+(txtPos - patternPos)+"\n"); 
				            	out.close(); 
				        	} 
				        	catch(IOException e4)
				        	{	 
				            	System.out.println("exception occoured" + e4); 
				        	}
							txtPos = (txtPos - (patternPos - 1));
							patternPos = table [0];
							count++;
						}
					}
					 else if (e.charAt(patternPos) != list.get(txtPos)) {
						status = false;
						if (patternPos != 0)
							patternPos = table[patternPos - 1];
						else
							txtPos++;
					}
				  }
				if (count == 0) {
					System.err.println("\nPattern not found.");
				}
				else
				{
					System.out.println("Numbers of matching pattern: "+count);
				}	
	}
	
	public static Vector readLines(File f) throws IOException
	{
		Vector<Character> vectorlist = new Vector<Character>();
		try
		{
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line;
			int numberOfLines = 0;
			int n,i,k;
			int charCount = 0;
		
			while((line = br.readLine()) != null)
			{
				numberOfLines++;
				n = numberOfLines;
				if (n >= 22)
				{
					if (!line.isEmpty())
					{
						char[] sChars = line.toCharArray();
						
						for(int l = 0; l < 54; l++)
						{
							if(sChars[l] != ' ')
							{
								vectorlist.add(sChars[l]); 
								charCount += line.length();
							    charCount = 0;
							} 	
						}		
					}			
				}	
			}
			br.close();
			fr.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return vectorlist;
	}
}