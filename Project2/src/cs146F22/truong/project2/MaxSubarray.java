package cs146F22.truong.project2;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

public class MaxSubarray 
{
	public MaxSubarray(int[] array)
	{
		this.array = array;
	}
	
	public int profit;
	public int arriveDate = -1;
	public int leaveDate = 0;
	public int[] array;
	
	// Part A
	public void firstAlgorithm()
	{
		int sum = 0;
		for(int i = 0; i < array.length; i++)
		{
			for(int k = i; k < array.length; k++)
			{
				sum = sum + array[k];
				if(sum > profit) 
				{
					profit = sum;
					arriveDate = i;
					leaveDate = k;
				}
			}
			sum = 0;
		}
		if(profit < 0) 
		{
			profit = 0; arriveDate = 0; leaveDate = 0;
		}
	}
	
	// Part B
	public int[] findMaxCrossSubArray(int[] array, int low, int mid, int high)
	{
		int[] result = new int[3];
		int max_left = 0;
		int max_right = 0;
		int left_sum = Integer.MIN_VALUE;
		int right_sum = Integer.MIN_VALUE;
		int sum = 0;
		
		for (int i = mid; i >= low; i--)
		{
			sum = sum + array[i];
			if (sum > left_sum)
			{
				left_sum = sum;
				max_left = i;
			}
		}
		sum = 0;
		for (int j = mid + 1; j <= high; j++)
		{ 
			sum = sum + array[j];
			if (sum > right_sum)
			{
				right_sum = sum;
				max_right = j;
			}
		}
		
		result[0] = max_left; result[1] = max_right; result[2] = left_sum + right_sum;
		return result;
	}
	
	public int[] findMaxSubArray(int[] array, int low, int high)
	{
		int[] result = new int[3];
		if(low == high)
		{
			result[0] = low; result[1] = high; result[2] = array[low];
			return result;
		}
		else
		{
			int[] sub_res_1 = new int[3];
			int[] sub_res_2 = new int[3];
			int[] cross_res = new int[3];
			int mid = (low + high)/2;
			
			sub_res_1 = findMaxSubArray(array, low, mid);
			int left_low = sub_res_1[0];
			int left_high = sub_res_1[1];
			int left_sum = sub_res_1[2];
			
			sub_res_2 = findMaxSubArray(array, mid+1, high);
			int right_low = sub_res_2[0];
			int right_high = sub_res_2[1];
			int right_sum = sub_res_2[2];
			
			cross_res = findMaxCrossSubArray(array, low, mid, high);
			int cross_low = cross_res[0];
			int cross_high = cross_res[1];
			int cross_sum = cross_res[2];
			
			if ((left_sum >= right_sum) && (left_sum >= cross_sum))
			{
				arriveDate = left_low; leaveDate = left_high; profit = left_sum;
				return sub_res_1;
			}
			else if ((right_sum >= left_sum) && (right_sum >= cross_sum))
			{
				arriveDate = right_low; leaveDate = right_high; profit = right_sum;
				return sub_res_2;
			}
			else
			{
				arriveDate = cross_low; leaveDate = cross_high; profit = cross_sum;
				return cross_res;
			}
		}
	}
	
	// Part C
	public void kadaneAlgorithm() 
	{
		int maxSum = 0;
		int maxTemp = 0;
		int arrive = -1;
		int leave = -1;
		int tempArrive = 0;
		int negativeCount = 0;
		if(array[0] > 0) 
		{
			arrive = 0;
		}
		for(int i = 0; i < array.length; i++)
		{
			maxTemp = maxTemp + array[i];
			if(maxTemp < 0) 
			{
				maxTemp = 0;
				arrive = i+1;
			}
			if(maxSum < maxTemp)
			{
				maxSum = maxTemp;
				leave = i;
				tempArrive = arrive;
			}
			if(array[i] < 0) 
			{
				negativeCount++;
			}
		}
		arrive = tempArrive;		
		if(negativeCount == array.length)
		{
			arrive = -1;
			leave = 0;
		}
		profit = maxSum;
		arriveDate = arrive;
		leaveDate = leave;
	}
}