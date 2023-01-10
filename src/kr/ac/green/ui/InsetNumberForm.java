package kr.ac.green.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kr.ac.green.data.DataCenter;
import kr.ac.green.model.Lotto;

public class InsetNumberForm extends JDialog {

	private ArrayList<Integer> selectNum;
	private JToggleButton[] buttons;
	private JLabel lbl1;
	private JButton btnDelete;
	private JButton btnOK;

	private int index;

	private BuyForm buy;

	private DataCenter dataCenter;
	private Lotto lotto;

	private int state = 0;
	public final static int MANUAL = 0;
	public final static int AUTO = 1;
	public final static int HALF = 2;


	public InsetNumberForm(int index, BuyForm buy) {

		super(new JFrame(), "����", true);
		this.index = index;
		this.buy = buy;
		init();
		setDisplay();
		setListener();
		setFrame();

	}

	private void init() {
		this.dataCenter = DataCenter.getInstance();
		lotto = dataCenter.getLottoList().get(index);
		buttons = new JToggleButton[45];
		selectNum = new ArrayList<Integer>();
		lbl1 = new JLabel("[6���� ��ȣ�� �����ϼ���]");


		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JToggleButton(String.valueOf(i + 1));
			buttons[i].setBackground(Color.WHITE);
		}

		if(!lotto.getState().equals("�̱���")) {
			for(int i = 0 ; i < lotto.getLottoNumber().length ; i++) {
				selectNum.add(lotto.getLottoNumber()[i]);
				buttons[selectNum.get(i)-1].setBackground(Color.LIGHT_GRAY);
				buttons[selectNum.get(i)-1].setSelected(true);
			}
			for (JToggleButton toggleButton : buttons) {
				toggleButton.setEnabled(false);
				if (toggleButton.isSelected()) {
					toggleButton.setEnabled(true);
				}
			}

			lbl1.setText(selectNum.toString());
		}

		btnDelete = new JButton("�ʱ�ȭ");
		btnDelete.setBackground(Color.WHITE);

		btnOK = new JButton("Ȯ��");
		btnOK.setBackground(Color.WHITE);




		new TitledBorder(new LineBorder(Color.BLACK, 4));




	}
	private void setDisplay() {
		JPanel pnlMain = new JPanel(new BorderLayout());

		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lbl1);

		JPanel pnlCenter = new JPanel(new GridLayout(0,5));
		pnlCenter.setBorder(new EmptyBorder(10,10,10,10));
		for(JToggleButton num: buttons) {
			pnlCenter.add(num);
		}


		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlSouth.add(btnDelete);
		pnlSouth.add(btnOK);



		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);

		add(pnlMain);
	}



	private void setListener() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				int exit = JOptionPane.showConfirmDialog(null, "���� �Է��� �����ұ��?", "����", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(exit == JOptionPane.YES_OPTION){
					//����ڰ� yes�� ������ ��
					dispose();
				} else{
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});

		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JToggleButton button = (JToggleButton) e.getSource();
				if (button.isSelected()) {
					int selNum = Integer.parseInt(button.getText());

					if (!selectNum.contains(selNum)) {
						selectNum.add(selNum);
						buttons[selNum-1].setBackground(Color.LIGHT_GRAY);
						lbl1.setText(selectNum.toString());

					}
				}

				if (selectNum.size() == 6) {
					for (JToggleButton toggleButton : buttons) {
						toggleButton.setEnabled(false);
						if (toggleButton.isSelected()) {
							toggleButton.setEnabled(true);
						}
					}
				}

				if (!button.isSelected()) {
					int selNum = Integer.valueOf(button.getText());

					selectNum.remove(Integer.valueOf(button.getText()));

					buttons[selNum-1].setBackground(Color.WHITE);
					if (selectNum.size() < 6) {
						for (JToggleButton toggleButton : buttons) {
							toggleButton.setEnabled(true);
						}
					}

					lbl1.setText(selectNum.toString());
				}
			}
		};
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if(btnOK == e.getSource()) {

					Random r = new Random();
					if(selectNum.size() != 6) {
						try{
							int next = JOptionPane.showConfirmDialog(
									null,
									"�������� ���� ��ȣ�� �ڵ����� �Է� �˴ϴ�.",
									"����",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.WARNING_MESSAGE
							);

							if(next == JOptionPane.YES_OPTION) {
								if(selectNum.size() == 0) {
									state = AUTO;
								}
								else {
									state = HALF;
								}
								for(int i = selectNum.size() ; i < 6 ; i++) {
									selectNum.add(r.nextInt(45)+1);
									for(int j = 0 ; j < i ; j++) {
										if(selectNum.get(j) == selectNum.get(i)) {
											i--;
										}
									}
									buttons[selectNum.get(i)-1].setBackground(Color.LIGHT_GRAY);
									buttons[selectNum.get(i)-1].setSelected(true);
								}
								lbl1.setText(selectNum.toString());
								if (selectNum.size() == 6) {
									for (JToggleButton toggleButton : buttons) {
										toggleButton.setEnabled(false);
										if (toggleButton.isSelected()) {
											toggleButton.setEnabled(true);
										}
									}
								}
							}
						}catch(NumberFormatException ae) {

						}

						//JOptionPane.showMessageDialog(ManualButton.this, "6���� ���ڸ� �������ּ���");
					} else {
						Collections.sort(selectNum);
						int[] array = new int[selectNum.size()];
						int size=0;
						for(int temp : selectNum){
							array[size++] = temp;
						}
						lotto.setLottoNumber(array);
						if(state == MANUAL) {
							lotto.setState("����");
						}else if (state == HALF) {
							lotto.setState("���ڵ�");
						}else {
							lotto.setState("�ڵ�");
						}
						dataCenter.updateLottoList(index, lotto);

						buy.updatePanel();

						dispose();
					}
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnDelete == e.getSource()) {
					try{
						int next = JOptionPane.showConfirmDialog(
								null,
								"������ ��ȣ�� �ʱ�ȭ �Ͻðڽ��ϱ�?",
								"�ʱ�ȭ",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE
						);

						if(next == JOptionPane.YES_OPTION) {
							selectNum = new ArrayList<>();
							lbl1.setText("[6���� ��ȣ�� �����ϼ���]");

							for (JToggleButton toggleButton : buttons) {
								toggleButton.setSelected(false);
								toggleButton.setEnabled(true);
								toggleButton.setBackground(Color.WHITE);
							}
						}
					}catch(NumberFormatException ae) {

					}
				}
			}
		});

		for (JToggleButton button : buttons) {
			button.addActionListener(actionListener);
		}
	}



	public void setSelectNum(ArrayList<Integer> selectNum) {
		this.selectNum = selectNum;
	}

	private void setFrame() {
		setTitle("���� �Է�");
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}