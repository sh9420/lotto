package kr.ac.green.model;

public class Lotto {
	// 로또 상태 (자동/수동)
	private String state;
	
	private int[] lottoNumber;
	
	/**
	 * 최초 생성 시, Default '수동'
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
	public void setLottoNumber(int[] lottoNumber) {
		this.lottoNumber = lottoNumber;
	}
	
	
}
