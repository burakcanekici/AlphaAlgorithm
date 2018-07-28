import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class AlphaAlg {
	public HashMap<String, NodeObj> petriNet;
	public HashMap<String, Integer> listDirectSuccession;	// > list
	public ArrayList<String> listCausality;					// -> list
	public ArrayList<String> listParallel;					// # list
	private int indexCaseID;
	private int indexActivity;
	private int indexTimestamp;
	
	AlphaAlg(){
		petriNet = new HashMap<String, NodeObj>();
		listDirectSuccession = new HashMap<String, Integer>();
		listCausality = new ArrayList<String>();
		listParallel = new ArrayList<String>();
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
		for(int row = 1;row<totalRowNum-1;row++){
			String e1 = s.getCell(this.indexActivity,row).getContents();
			String e2 = s.getCell(this.indexActivity+1,row).getContents();
			
			if(listDirectSuccession.get(e2 + ">" + e1) != null){
				listParallel.add(e1 + "#" + e2);
				listParallel.add(e2 + "#" + e1);
				listCausality.remove(e1 + "->" + e2);
			}
			else{
				if(!listCausality.contains(e1 + "->" + e2))
					listCausality.add(e1 + "->" + e2);
				listDirectSuccession.put(e1 + ">" + e2, listDirectSuccession.get(e1 + ">" + e2) == null ? 0 : listDirectSuccession.get(e1 + ">" + e2) + 1);
			}
		}
		
	}
}
