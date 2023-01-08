package kr.ac.green.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

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

public class ManualButton extends JDialog {
	
    private ArrayList<Integer> selectNum;
    private JToggleButton[] buttons;
    private JLabel lbl1;
    private JButton btnDelete;
    private JButton btnOK;

    private int index;
    
    private Buy buy;
    
    private DataCenter dataCenter;
    private Lotto lotto;
    
    
    
    public ManualButton(int index, Buy buy) {
    	
    	super(new JFrame(), "수동", true);
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
        lbl1 = new JLabel("-");
        
        
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JToggleButton(String.valueOf(i + 1));
            buttons[i].setBackground(Color.WHITE);
        }
        
        if(!lotto.getState().equals("미구매")) {
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
        
        btnDelete = new JButton("초기화");
        btnDelete.setBackground(Color.WHITE);

        btnOK = new JButton("확인");
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
				
				if (selectNum.size() >= 6) {
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
                	if(selectNum.size() != 6) {
                		JOptionPane.showMessageDialog(ManualButton.this, "6개의 숫자를 선택해주세요");
                	} else {                		
                		Collections.sort(selectNum);
                		int[] array = new int[selectNum.size()];
                		int size=0;
                		for(int temp : selectNum){
                			array[size++] = temp;
                		}
                		lotto.setLottoNumber(array);
                		lotto.setState("수동");
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
                	selectNum = new ArrayList<>();
                	lbl1.setText(selectNum.toString());
                    
                    for (JToggleButton toggleButton : buttons) {
                        toggleButton.setSelected(false);
                        toggleButton.setEnabled(true);
                        toggleButton.setBackground(Color.WHITE);
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
        setTitle("숫자 입력");
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}