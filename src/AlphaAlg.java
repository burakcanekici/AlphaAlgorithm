import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class AlphaAlg {
	public HashMap<String, NodeObj> petriNet;
	private int indexCaseID;
	private int indexActivity;
	private int indexTimestamp;
	
	AlphaAlg(){
		petriNet = new HashMap<String, NodeObj>();
	}
	
	public void StartAlgorithm(int[] arr, String eventLogDoc) throws IOException, BiffException{
		this.indexCaseID = arr[0];
		this.indexActivity = arr[1];
		this.indexTimestamp = arr[2];
		
		FileInputStream	fis = new FileInputStream(eventLogDoc);
		Workbook w = Workbook.getWorkbook(fis);
		Sheet s = w.getSheet("Sheet1");
		int totalRowNum = s.getRows();
		int totalColumnNum = s.getColumns();
		for(int row = 0;row<totalRowNum;row++){
			for(int column = 0;column<totalColumnNum;column++){
				System.out.println(s.getCell(column,row).getContents() + "\t");
			}
			System.out.println();
		}
		
	}
}
