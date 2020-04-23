package com.ojas.util;

import java.util.Random;

public class Random_IdGenerator {
	
	static public String getVehicleID() {
		Random r = new Random();
		int num = r.nextInt((10000 - 1) + 1) + 1;

		return "Vehi_" + num;
	}

}