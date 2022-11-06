package cs146F22.truong.project2;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class MaxSubarrayTest {

	@Test
	void test() throws IOException
	{
		FileReader file = new FileReader("maxSumtest.txt");
		BufferedReader input = new BufferedReader(file);
		Scanner in = new Scanner(input);
		
		while(in.hasNextLine())
		{
			String line = in.nextLine(); // Each array of numbers is on a single line
			if(line.equals("")) // Takes care of the fact that each array is separated by an empty line
				line = in.nextLine();
			
			String filteredLine = line.substring(1).replaceAll("[^0-9-]", " "); // Replaces all brackets and commas with a space
			String[] valuesAsStr = filteredLine.split("\\s+"); // Fills valuesAsStr with strings containing a single integer value
			int[] values = new int[valuesAsStr.length]; // Creates the integer array that stores the values 
			
			for(int i = 0; i < valuesAsStr.length; i++) // Fills the final array with integers by parsing them one by one from valuesAsStr
			{
				values[i] = Integer.parseInt(valuesAsStr[i]);
			}
			
			System.out.println();
			
			int maxProfit = values[values.length-3]; //System.out.print(maxProfit + " ");
			int arriveDate = values[values.length-2]; //System.out.print(arriveDate + " ");
			int leaveDate = values[values.length-1]; //System.out.print(leaveDate + " ")
			
			int[] nums = Arrays.copyOfRange(values, 0, values.length-3);
			
			// Part A Test
			System.out.println("This is Part A Test");
			MaxSubarray maxSA = new MaxSubarray(nums);
			long startTime = System.nanoTime();
			maxSA.firstAlgorithm();
			long endTime = System.nanoTime();
			long timeElapse = endTime - startTime;
			System.out.println("Execution time: " + timeElapse + " ");
			
			System.out.print("Max Profit: " + maxProfit + " ");
			System.out.print(maxSA.profit);
			System.out.println();
			assertEquals(maxSA.profit, maxProfit);
			
			System.out.print("Arrive: " + arriveDate + " ");
			System.out.print(maxSA.arriveDate);
			System.out.println();
			assertEquals(maxSA.arriveDate, arriveDate);
			
			System.out.print("Leave: " + leaveDate + " ");
			System.out.print(maxSA.leaveDate);
			System.out.println();
			assertEquals(maxSA.leaveDate, leaveDate);
			
			
			// Part B Test
			System.out.println("This is Part B Test");
			MaxSubarray maxSA2 = new MaxSubarray(nums);
			startTime = System.nanoTime();
			maxSA2.findMaxSubArray(nums, 0, nums.length-1);
			endTime = System.nanoTime();
			timeElapse = endTime - startTime;
			System.out.println("Execution time: " + timeElapse + " ");
			
			System.out.print("Max Profit: " + maxProfit + " ");
			System.out.print(maxSA2.profit);
			System.out.println();
			assertEquals(maxSA2.profit, maxProfit);
			
			System.out.print("Arrive: " + arriveDate + " ");
			System.out.print(maxSA2.arriveDate);
			System.out.println();
			assertEquals(maxSA2.arriveDate, arriveDate);
			
			System.out.print("Leave: " + leaveDate + " ");
			System.out.print(maxSA2.leaveDate);
			System.out.println();
			assertEquals(maxSA2.leaveDate, leaveDate);
			
			
			// Part 3 Test
			System.out.println("This is Part C Test");
			MaxSubarray maxSA3 = new MaxSubarray(nums);
			startTime = System.nanoTime();
			maxSA3.kadaneAlgorithm();
			endTime = System.nanoTime();
			timeElapse = endTime - startTime;
			System.out.println("Execution time: " + timeElapse + " ");
			
			System.out.print("Max Profit: " + maxProfit + " ");
			System.out.print(maxSA3.profit);
			System.out.println();
			assertEquals(maxSA3.profit, maxProfit);
			
			System.out.print("Arrive: " + arriveDate + " ");
			System.out.print(maxSA3.arriveDate);
			System.out.println();
			assertEquals(maxSA3.arriveDate, arriveDate);
			
			System.out.print("Leave: " + leaveDate + " ");
			System.out.print(maxSA3.leaveDate);
			System.out.println();
			assertEquals(maxSA3.leaveDate, leaveDate);
			
			System.out.println();
			
			int a = 100;
			int b = 200;
			int c = 500;
			int d = 1000;
			for(int i = 1; i < 5; i++)
			{
				int k = 0;
				if(i == 1) k = a;
				else if(i == 2) k = b;
				else if(i == 3) k = c;
				else k = d;
				int[] arr = new int[k];
				for(int j = 0; j < k; j++)
				{
					Random ran = new Random();
					arr[j] = ran.nextInt((50 + 50) - 50);
				}
				
				MaxSubarray maxS = new MaxSubarray(arr);
				
				startTime = System.nanoTime();
				maxS.firstAlgorithm();
				endTime = System.nanoTime();
				timeElapse = endTime - startTime;
				System.out.println("*A* Execution time at array size " + k + " is: " + timeElapse + " ");
				
				startTime = System.nanoTime();
				maxS.findMaxSubArray(arr, 0, arr.length-1);
				endTime = System.nanoTime();
				timeElapse = endTime - startTime;
				System.out.println("*B* Execution time at array size " + k + " is: " + timeElapse + " ");
				
				startTime = System.nanoTime();
				maxS.kadaneAlgorithm();
				endTime = System.nanoTime();
				timeElapse = endTime - startTime;
				System.out.println("*C* Execution time at array size " + k + " is: " + timeElapse + " ");
			}
			
			
		}
		
		input.close();
	}

}
