package com.chainsys.programmingchallenges;

import java.util.Scanner;

public class ValidationChecker {
	
	Scanner scan = new Scanner(System.in);
	
	
		public boolean Numerics(int Number) {
				if(Number < 0)
				{
					return false;
				}
			return true;
		}
		
		public boolean str(String name)
		{
			String pattern= "^[A-Za-z]+$";
			
			if(name.matches(pattern))
			{
				return true;
			}
			else {
				return false;
			}
			
		}
}
