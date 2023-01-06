package kr.ac.green.model;

public class Lotto {
	// 로또 상태 (자동/수동)
	private String state;
	
	// 로또 넘버 
	private int[] lottoNumber;
	
	/**
	 * 최초 생성 시, Default '수동' 및 6개의 로또번호 배열 생성
	 * 
	 */
	public Lotto() {
		this.state = "수동";
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
	 * 지정된 index 넘버에 로또번호 기재
	 * 
	 * @param idx
	 */
	public void setLottoNumber(int index, int lottoNumber) {			
		this.lottoNumber[index] = lottoNumber;
	}
	
	
}
