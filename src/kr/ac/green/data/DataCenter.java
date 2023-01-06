package kr.ac.green.data;

/**
 * @author 백승훈
 * 싱글턴 패턴을 활용하여, 하나의 인스턴스 보장
 * 데이터 관리는 여기서부터 활용이 필요하여, 내용 추가 시 ,주석 필수로 추가
 * 
 * 사용 시, DataCenter.getInstance().메소드명(); 형태로 활용하여야 함.
 */
public class DataCenter {
	
	private static DataCenter instance;
	
	//구매 수량
	private int buyNum;
    
	private DataCenter() {
		this.buyNum = 0;
	}
	
	/**
	 * DataCenter 
	 * 
	 * @return
	 */
	public static DataCenter getInstance() {
		if(instance == null) {
			instance = new DataCenter();
		}
        return instance;
    }
	
	public int getBuyNum() {
		return buyNum;
	}
	
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
		
}

