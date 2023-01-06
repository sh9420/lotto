package kr.ac.green.data;

/**
 * @author �����
 * �̱��� ������ Ȱ���Ͽ�, �ϳ��� �ν��Ͻ� ����
 * ������ ������ ���⼭���� Ȱ���� �ʿ��Ͽ�, ���� �߰� �� ,�ּ� �ʼ��� �߰�
 * 
 * ��� ��, DataCenter.getInstance().�޼ҵ��(); ���·� Ȱ���Ͽ��� ��.
 */
public class DataCenter {
	
	private static DataCenter instance;
	
	//���� ����
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

