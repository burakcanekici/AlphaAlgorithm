import java.util.ArrayList;

public class CustomList extends ArrayList<NodeObj>{
	
	int IndexOfCurrent(String pValue){
		int index = -1;
		for(int i=0;i<size();i++){
			if(get(i).getValue().equals(pValue))
				index = i;
		}
		return index;
	}
}
