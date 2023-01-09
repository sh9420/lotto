package kr.ac.green.model;

import java.util.Arrays;

public class Lotto {
	// �ζ� ���� (�ڵ�/����/�̱���)
	private String state;
	
	// �ζ� �ѹ� 
	private int[] lottoNumber;
	
	private int[] winNumber;
	/**
	 * ���� ���� ��, Default '����' �� 6���� �ζǹ�ȣ �迭 ����
	 * 
	 */
	public Lotto() {
		this.state = "�̱���";
		this.lottoNumber = new int[6];
		this.winNumber = new int[7];
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
	public void setWinNumber(int[] winNumber) {
		this.winNumber = winNumber;
	}
	
	@Override
	public String toString() {
		return "Lotto [state=" + state + ", lottoNumber=" + Arrays.toString(lottoNumber) + "]";
	}
}
