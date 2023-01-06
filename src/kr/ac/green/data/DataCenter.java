package kr.ac.green.data;

import java.util.ArrayList;

import kr.ac.green.model.Lotto;

/**
 * @author �����
 * �̱��� ������ Ȱ���Ͽ�, �ϳ��� �ν��Ͻ� ����
 * ������ ������ ���⼭���� Ȱ���� �ʿ��Ͽ�, ���� �߰� �� ,�ּ� �ʼ��� �߰�
 * 
 * ��� ��, DataCenter.getInstance().�޼ҵ��(); ���·� Ȱ���Ͽ��� ��.
 */
public class DataCenter {
	
	private static DataCenter instance;
	
	//�ζ� ����Ʈ ����
	private ArrayList<Lotto> lottoList;
	
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
	

	/**
	 * �ζ� ���ŷ� �Է� ��, ������ŭ ArrayList �߰�
	 * @param buyLotto
	 */
	public void setBuyLotto(int buyLotto) {
		
		for(int i=0;i<buyLotto;i++) {
			this.lottoList.add(new Lotto());
		}
		System.out.println(this.lottoList.size());
	}

	/**
	 * �ζ� ���� ���� 
	 * 
	 * @param index
	 * @param lotto
	 */
	public void updateLottoList(int index, Lotto lotto) {
		this.lottoList.add(index, lotto);
	}
	
	/**
	 * �ζ� ���� �߰�
	 * @ return �ζ� �߰� ��, ���� index���� ��ȯ
	 * 
	 */
	public int addLottoList() {
		this.lottoList.add(new Lotto());
		
		return lottoList.size()-1;
	}
	
	/**
	 * �ζ� ����Ʈ ȣ��
	 * 
	 * @return
	 */
	public ArrayList<Lotto> getLottoList() {
		return this.lottoList;
	}
}

