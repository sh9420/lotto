package kr.ac.green.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import kr.ac.green.model.Lotto;


/**
 *
 * �̱��� ������ Ȱ���Ͽ�, �ϳ��� �ν��Ͻ� ����
 * ������ ������ ���⼭���� Ȱ���� �ʿ��Ͽ�, ���� �߰� �� ,�ּ� �ʼ��� �߰�
 *
 * ��� ��, DataCenter.getInstance().�޼ҵ��(); ���·� Ȱ���Ͽ��� ��.
 */

public class DataCenter {

	private static DataCenter instance;

	//�ζ� ����Ʈ ����
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
	 * �ζ� ���ŷ� �Է� ��, ������ŭ ArrayList �߰�
	 * @param buyLotto
	 */
	public void setBuyLotto(int buyLotto) {

		for(int i=0;i<buyLotto;i++) {
			this.lottoList.add(new Lotto());
		}
	}

	/**
	 * �ζ� ���� ���� 
	 *
	 * @param index
	 * @param lotto
	 */

	public void updateLottoList(int index, Lotto lotto) {
		this.lottoList.set(index, lotto);
	}

	/**
	 * �ζ� ���� �߰�
	 * @ �ζ� �߰�
	 *
	 */
	public void addLottoList() {
		this.lottoList.add(new Lotto());
	}

	/**
	 * �ζ� ����Ʈ ȣ��
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

