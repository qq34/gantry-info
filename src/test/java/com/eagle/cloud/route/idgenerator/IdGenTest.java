package com.eagle.cloud.route.idgenerator;

import com.eagle.cloud.route.utils.idgenerator.IdGenerator;
import com.eagle.cloud.route.utils.idgenerator.IdPair;
import org.junit.Test;

public class IdGenTest {

//	@Test
	public void testIdPair(){
		IdPair pair= IdGenerator.getIdPairOfDay(2019, 05, 8);
		System.out.println("min:"+pair.getSmallestId());
		System.out.println("max:"+pair.getLargestId());
		for (int i = 0; i < 10; i++) {
			System.out.println(IdGenerator.nextId());
		}
	}
}
