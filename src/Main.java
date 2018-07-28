import java.io.IOException;
import java.util.HashMap;

import jxl.read.biff.BiffException;

public class Main {

	public static void main(String[] args) throws IOException, BiffException {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
		
		int[] indexArr = new int[]{0,1,2};
		AlphaAlg aa = new AlphaAlg();
		aa.StartAlgorithm(indexArr, "C:\\Users\\TOSHIBA\\Desktop\\input.xls");
	}

}
