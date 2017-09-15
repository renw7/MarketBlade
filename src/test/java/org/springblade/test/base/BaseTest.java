package org.springblade.test.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BaseTest {
	
	public String getType() {
		String str = "";
		try {
			BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("please input something...");
			str = strin.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}
	
}
