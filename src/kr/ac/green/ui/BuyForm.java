package kr.ac.green.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.ac.green.data.DataCenter;
import kr.ac.green.model.Lotto;
import kr.ac.green.ui.component.MyLottoPanel;

/**
 * @author qortm
 *
 */
public class BuyForm extends JFrame {

	private JLabel lblImage;
	private JLabel lblCount;
	private JButton btnCheck;
	private JButton btnAdd;

	private JPanel pnlNorth;
	private JPanel pnlCenter;
	private JPanel pnlSouth;

	private DataCenter dataCenter;
	private MainForm start;


	public BuyForm(MainForm start) {
		this.start = start;
		init();
		setDisplay();
		addListener();
		showFrame();
	}

	public void init() {
		this.dataCenter=DataCenter.getInstance();

		Font font = new Font("���� ���", Font.BOLD, 15); // �۲� ���߿�
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("logo.png");
		Image back = kit.getImage("Back.png");
		ImageIcon icon = new ImageIcon(img);
		ImageIcon iconback = new ImageIcon(back);
		Image newImage = img.getScaledInstance(100,100,Image.SCALE_SMOOTH);


		lblImage = new JLabel(new ImageIcon(newImage));
		lblImage.setPreferredSize(new Dimension(100,100));

		lblCount = new JLabel(" ���� ����   :   " + dataCenter.getLottoList().size());
		lblCount.setPreferredSize(new Dimension(200,30));
		lblCount.setFont(font);
		btnCheck = new JButton("��÷ Ȯ��");
		btnCheck.setFont(font);
		btnAdd = new JButton("�߰� ����");
		btnAdd.setFont(font);
	}

	public void setDisplay() {
		pnlNorth = new JPanel();
		pnlSouth = new JPanel();
		pnlCenter = new JPanel(new GridLayout(0,1));

		pnlNorth.add(lblImage);
		pnlNorth.add(lblCount);
		pnlNorth.setLayout(new FlowLayout(FlowLayout.LEFT));

		for(int i = 0 ; i < dataCenter.getLottoList().size() ; i++) {
			// �ζ� ���ٿ� ���� Panel
			pnlCenter.add(new MyLottoPanel(i,this));
		}

		pnlSouth.add(btnCheck);
		pnlSouth.add(btnAdd);
		pnlSouth.setLayout(new FlowLayout(FlowLayout.RIGHT,20,10));


		pnlNorth.setBackground(Color.WHITE);
		pnlCenter.setBackground(Color.WHITE);
		pnlSouth.setBackground(Color.WHITE);

		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

	}

	public void addListener() {
		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnAdd == e.getSource()) {
					int size = dataCenter.getLottoList().size();
					if(size < 10) {
						dataCenter.addLottoList();
						updatePanel();
					}else {
						JOptionPane.showMessageDialog(BuyForm.this, "10�� �̻� ���� �Ұ��� �մϴ�.");
					}
				}else if(btnCheck == e.getSource()) {
					try{

						int next = JOptionPane.showConfirmDialog(
								null,
								"�������� ���� ��ȣ�� �ڵ����� �Է� �˴ϴ�.",
								"����",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE
						);

						if(next == JOptionPane.YES_OPTION) {

							for(int index = 0 ; index < dataCenter.getLottoList().size(); index++) {
								if(dataCenter.getLottoList().get(index).getState().equals("�̱���")) {
									autoBuy(index);
								}
							}
							setVisible(false);
							new ResultForm().setVisible(true);
						}
					}catch(NumberFormatException ae) {

					}
				}
			}
		};
		btnAdd.addActionListener(aListener);
		btnCheck.addActionListener(aListener);
	}


	/**
	 * �г� UPDATE (�ζ� ������ ������ �ǰų�, �����Ǵ� ���)
	 */
	public void updatePanel() {
		lblCount.setText(" ���� ����   :   " + dataCenter.getLottoList().size());

		remove(pnlNorth);
		remove(pnlCenter);
		remove(pnlSouth);

		setDisplay();
		pack();
		setVisible(true);
	}

	/**
	 * �ζ� �ڵ� ���� �޼���
	 * @param index
	 */
	public void autoBuy(int index) {
		Random r = new Random();
		Lotto lotto = dataCenter.getLottoList().get(index);
		int[] rNum = new int[6];
		for(int i = 0 ; i < 6 ; i++) {
			rNum[i] = r.nextInt(45)+1;
			for(int j = 0 ; j < i ; j++) {
				if(rNum[i] == rNum[j]) {
					i--;
				}
			}
		}
		Arrays.sort(rNum);
		lotto.setLottoNumber(rNum);
		lotto.setState("�ڵ�");
		dataCenter.updateLottoList(index, lotto);
		updatePanel();
	}

	public void showFrame() {
		setTitle("BUY");
		pack();
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		setVisible(true);
	}
}
