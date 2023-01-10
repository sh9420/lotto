package kr.ac.green.model;

import java.util.Arrays;

public class Lotto {
	// 로또 상태 (자동/수동/미구매)
	private String state;
	// 로또 넘버 
	private int[] lottoNumber;
	/**
	 * 최초 생성 시, Default '수동' 및 6개의 로또번호 배열 생성
	 *
	 */
	public Lotto() {
		this.state = "미구매";
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
