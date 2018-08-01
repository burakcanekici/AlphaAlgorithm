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
	//public HashMap<String, NodeObj> petriNet;
	//public HashMap<String, Integer> listDirectSuccession;	// > list
	public CustomList listDirectSuccession;					// > list
	public ArrayList<String> listCausality;					// -> list
	public ArrayList<String> listParallel;					// # list
	private int indexCaseID;
	private int indexActivity;
	private int indexTimestamp;
	
	AlphaAlg(){
		//petriNet = new HashMap<String, NodeObj>();
		//listDirectSuccession = new HashMap<String, Integer>();
		listDirectSuccession = new CustomList();
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
		String caseIDControl = "";
		for(int row = 1;row<s.getRows();row++){
			NodeObj no = null;
			
			if(caseIDControl == "" || !caseIDControl.equals(s.getCell(this.indexCaseID,row).getContents())){
				no = new NodeObj(s.getCell(this.indexActivity,row).getContents(), s.getCell(this.indexActivity,row+1).getContents(), true, false);
				caseIDControl = s.getCell(this.indexCaseID,row).getContents();
			}
			else if(row == s.getRows()-1 || !caseIDControl.equals(s.getCell(this.indexCaseID,row+1).getContents())){
				no = new NodeObj(s.getCell(this.indexActivity,row).getContents(), "", false, true);
			}
			else{
				String e1 = s.getCell(this.indexActivity,row).getContents();
				String e2 = s.getCell(this.indexActivity,row+1).getContents();
				no = new NodeObj(e1, e2, false, false);
			}
			
			System.out.println(no.getValue());
			
			int indexOfCurr = listDirectSuccession.IndexOfCurrent(no.getValue());
			NodeObj curr = indexOfCurr == -1 ? null : listDirectSuccession.get(indexOfCurr);
			if(curr == null){
				if(!no.isFinish()){
					if(listDirectSuccession.IndexOfCurrent(no.getFinishEvent() + ">" + no.getStartEvent()) != -1){
						listParallel.add(no.getStartEvent() + "#" + no.getFinishEvent());
						listParallel.add(no.getFinishEvent() + "#" + no.getStartEvent());
						listCausality.remove(no.getStartEvent() + "->" + no.getFinishEvent());
						listCausality.remove(no.getFinishEvent() + "->" + no.getStartEvent());
					}
					else{
						if(!listCausality.contains(no.getStartEvent() + "->" + no.getFinishEvent()))
							listCausality.add(no.getStartEvent() + "->" + no.getFinishEvent());
					}
				}
				listDirectSuccession.add(no);
			}
			else{
				curr.IncreaseNumber();
			}	
			/*
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
			*/
		}
		System.out.println("====================================");
		for(int i=0;i<listDirectSuccession.size();i++){
			System.out.println(listDirectSuccession.get(i).getValue() + " ...//... " + listDirectSuccession.get(i).getConnectionNumber());
		}
		System.out.println("--------------------------------------");
		for(int i=0;i<listCausality.size();i++){
			System.out.println(listCausality.get(i));
		}
		System.out.println("--------------------------------------");
		for(int i=0;i<listParallel.size();i++){
			System.out.println(listParallel.get(i));
		}
	}
}
