import java.io.IOException;
import java.util.HashMap;

import jxl.read.biff.BiffException;

public class Main {

	public static void main(String[] args) throws IOException, BiffException {
		// TODO Auto-generated method stub
		int[] indexArr = new int[]{0,1,2};
		AlphaAlg aa = new AlphaAlg();
		aa.StartAlgorithm(indexArr, "C:\\Users\\TOSHIBA\\Desktop\\Workspace\\Java\\AlphaAlgorithm\\input.xls");
	}

}
