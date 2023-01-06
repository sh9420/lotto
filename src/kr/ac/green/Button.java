package kr.ac.green;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Button extends JDialog {
    private ArrayList<Integer> selectNum;
    private JToggleButton[] buttons;
    private JLabel lbl1;
    private JButton btnDelete;
    private JButton btnOK;
    
    private Buy owner;



    public Button(Buy owner) {
		super(owner,"Button",true);
		this.owner = owner;
        init();
        setDisplay();
        setListener();
        setFrame();
    }

    private void init() {
        buttons = new JToggleButton[45];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JToggleButton(String.valueOf(i + 1));
            buttons[i].setBackground(Color.WHITE);
        }

        btnDelete = new JButton("초기화");
        btnDelete.setBackground(Color.WHITE);

        btnOK = new JButton("확인");
        btnOK.setBackground(Color.WHITE);


        lbl1 = new JLabel("-");
        
        new TitledBorder(new LineBorder(Color.BLACK, 4));

        selectNum = new ArrayList<Integer>();

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
					if (!selectNum.contains(button.getText())) {
						selectNum.add(Integer.parseInt(button.getText()));
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
					int index = selectNum.indexOf(Integer.parseInt(button.getText()));
					if (selectNum.size() < 6) {
						for (JToggleButton toggleButton : buttons) {
							toggleButton.setEnabled(true);
						}
					}
					selectNum.remove(Integer.valueOf(button.getText()));
					lbl1.setText(selectNum.toString());
				}
			}
		};
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnOK == e.getSource()) {
                	dispose();
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnDelete == e.getSource()) {
                    selectNum = new ArrayList<>();
                    for (JToggleButton toggleButton : buttons) {
                        toggleButton.setSelected(false);
                        toggleButton.setEnabled(true);
                    }
                }
            }
        });

        for (JToggleButton button : buttons) {
            button.addActionListener(actionListener);
        }
    }

    private void setFrame() {
        setTitle("숫자 입력");
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}