package kr.ac.green.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import kr.ac.green.model.Lotto;


/**
 *
 * 싱글턴 패턴을 활용하여, 하나의 인스턴스 보장
 * 데이터 관리는 여기서부터 활용이 필요하여, 내용 추가 시 ,주석 필수로 추가
 *
 * 사용 시, DataCenter.getInstance().메소드명(); 형태로 활용하여야 함.
 */

public class DataCenter {

	private static DataCenter instance;

	//로또 리스트 생성
	private ArrayList<Lotto> lottoList;
	private int[] winNum;
	
	private DataCenter() {
		lottoList = new ArrayList<Lotto>();
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

	

	public int[] getWinNum() {
		return winNum;
	}

	public void setWinNum(int[] winNum) {
		this.winNum = winNum;
	}

	/**
	 * 로또 구매량 입력 시, 갯수만큼 ArrayList 추가
	 * @param buyLotto
	 */
	public void setBuyLotto(int buyLotto) {

		for(int i=0;i<buyLotto;i++) {
			this.lottoList.add(new Lotto());
		}
	}

	/**
	 * 로또 정보 수정 
	 *
	 * @param index
	 * @param lotto
	 */

	public void updateLottoList(int index, Lotto lotto) {
		this.lottoList.set(index, lotto);
	}

	/**
	 * 로또 정보 추가
	 * @ 로또 추가
	 *
	 */
	public void addLottoList() {
		this.lottoList.add(new Lotto());
	}

	/**
	 * 로또 리스트 호출
	 *
	 * @return
	 */
	public ArrayList<Lotto> getLottoList() {
		return this.lottoList;
	}
	
	public int[] resultAuto() {
		HashSet<Integer> hashSet = new HashSet<>();

		while(hashSet.size() < 7) {
			hashSet.add(new Random().nextInt(45) + 1);
		}
		ArrayList<Integer> numbers = new ArrayList<>(hashSet);
		Collections.sort(numbers.subList(0,6));
		int[] arr = new int[numbers.size()];
		for(int i = 0; i < numbers.size(); i++) {
			arr[i] = numbers.get(i);
		}
		return arr;

	}

}

