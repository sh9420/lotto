package kr.ac.green.model;

public class Lotto {
	// �ζ� ���� (�ڵ�/����)
	private String state;
	
	private int[] lottoNumber;
	
	/**
	 * ���� ���� ��, Default '����'
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
	public void setLottoNumber(int[] lottoNumber) {
		this.lottoNumber = lottoNumber;
	}
	
	
}
