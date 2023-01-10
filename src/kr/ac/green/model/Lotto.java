package kr.ac.green.model;

import java.util.Arrays;

public class Lotto {
	// �ζ� ���� (�ڵ�/����/�̱���)
	private String state;
	// �ζ� �ѹ� 
	private int[] lottoNumber;
	/**
	 * ���� ���� ��, Default '����' �� 6���� �ζǹ�ȣ �迭 ����
	 *
	 */
	public Lotto() {
		this.state = "�̱���";
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

	@Override
	public String toString() {
		return "Lotto [state=" + state + ", lottoNumber=" + Arrays.toString(lottoNumber) + "]";
	}
}
