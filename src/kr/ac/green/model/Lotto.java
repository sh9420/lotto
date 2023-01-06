package kr.ac.green.model;

public class Lotto {
	// �ζ� ���� (�ڵ�/����)
	private String state;
	
	// �ζ� �ѹ� 
	private int[] lottoNumber;
	
	/**
	 * ���� ���� ��, Default '����' �� 6���� �ζǹ�ȣ �迭 ����
	 * 
	 */
	public Lotto() {
		this.state = "����";
		this.lottoNumber = new int[6];
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public int[] getLottoNumber() {
		return lottoNumber;
	}
	
	/**
	 * ������ index �ѹ��� �ζǹ�ȣ ����
	 * 
	 * @param idx
	 */
	public void setLottoNumber(int index, int lottoNumber) {			
		this.lottoNumber[index] = lottoNumber;
	}
	
	
}
