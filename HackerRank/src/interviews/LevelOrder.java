package interviews;

public class LevelOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String input ="gg # # # # # 6";
		int oddSum=0;
		int evenSum =0;
		String[] values = input.split(" ");
		oddSum=Integer.parseInt(values[0]);
		boolean isOdd=false;
		int levelNodes=2;
		int validNodes=0;
		for(int i=1;i<values.length;i++){
			if(levelNodes>0){
				if(!values[i].equals("#")){
					if(isOdd){
						oddSum+=Integer.parseInt(values[i]);
					}else{
						evenSum+=Integer.parseInt(values[i]);
					}

					validNodes++;
				}
				levelNodes--;
			}
			if(levelNodes==0){
				levelNodes=2*validNodes;
				System.out.println(" --- "+levelNodes);
				validNodes=0;
				isOdd=!isOdd;
				//System.out.println("Odd : "+oddSum+" - Even : "+evenSum);
			}
			//System.out.println("Odd : "+oddSum+" - Even : "+evenSum + " values[i] : "+values[i] + "isOdd : "+isOdd);
		}
		System.out.println(oddSum+" - - Even - - "+evenSum);

	}

	private static void calValues(int oddSum, int evenSum,String[] values, int i,int last, boolean isOdd) {
		int nextIndex=1;
		//int right= last+2;
		if(isOdd){
			if(values[last+nextIndex].equals("#")){
				evenSum+=0;
			}else{
				evenSum+=Integer.parseInt(values[last+1]);
				calValues(oddSum, evenSum, values, nextIndex, last, isOdd);
			}
		}
	}

}
