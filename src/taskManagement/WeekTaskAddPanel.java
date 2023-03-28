package taskManagement;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class WeekTaskAddPanel extends JPanel {
	//コンポーネント
	Label title = new Label("課題の追加");
	Label nameLabel = new Label("名前");
	JTextField nameField = new JTextField(15);
	Label dayOfTheWeekLabel = new Label("開始曜日");
	JComboBox<String> dayOfTheWeekComboBox = new JComboBox<>();
	Label periodLabel = new Label("期限");
	JTextField periodField = new JTextField(2);
	Label periodEndLabel = new Label("日後");
	Label detaiLabel = new Label("詳細");
	JTextArea detailField = new JTextArea(4, 20);

	JButton returnButton = new JButton("元に戻る");
	JButton addButton = new JButton("週間課題追加");

	public WeekTaskAddPanel() {
		//タスク一覧タスクの表示
		JPanel mainPanel = new JPanel();
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);

		//間隔開ける
		layout.setAutoCreateGaps(false);
		layout.setAutoCreateContainerGaps(true);

		//コンポーネントの設定
		detailField.setLineWrap(true);
		detailField.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		//ComboBoxに曜日を追加
		dayOfTheWeekComboBox.addItem("日曜日");
		dayOfTheWeekComboBox.addItem("月曜日");
		dayOfTheWeekComboBox.addItem("火曜日");
		dayOfTheWeekComboBox.addItem("水曜日");
		dayOfTheWeekComboBox.addItem("木曜日");
		dayOfTheWeekComboBox.addItem("金曜日");
		dayOfTheWeekComboBox.addItem("土曜日");

		//水平グループ
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(title)
								.addComponent(nameLabel)
								.addComponent(nameField)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup()
												.addComponent(dayOfTheWeekLabel)
												.addComponent(dayOfTheWeekComboBox))
										.addGap(10, 10, 10)
										.addGroup(layout.createParallelGroup()
												.addComponent(periodLabel)
												.addGroup(layout.createSequentialGroup()
														.addComponent(periodField)
														.addComponent(periodEndLabel)))
										.addGap(40, 40, 40))
								.addComponent(detaiLabel)
								.addComponent(detailField)));

		//垂直グループ
		layout.setVerticalGroup(
				layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(title))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(nameLabel))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(nameField))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(dayOfTheWeekLabel)
								.addComponent(periodLabel))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(dayOfTheWeekComboBox)
								.addComponent(periodField)
								.addComponent(periodEndLabel))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(detaiLabel))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(detailField)));

		// コンボボックスのドロップダウンリストを最前面に表示するリスナーの設定
		dayOfTheWeekComboBox.addActionListener(e -> {
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			JPopupMenu popup = (JPopupMenu) cb.getUI().getAccessibleChild(cb, 0);
		});

		//追加、削除ボタンのパネルを追加
		JPanel operationButton = new JPanel();
		GridLayout layout2 = new GridLayout(1, 2);
		layout2.setHgap(100);

		//課題一覧画面への遷移ボタンの操作
		returnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TaskManagement.cardLayout.show(TaskManagement.cardLayoutPanel, "weekTaskList");
			}
		});

		//課題追加ボタンの操作
		taskAdd();
		operationButton.setLayout(layout2);
		operationButton.add(returnButton);
		operationButton.add(addButton);

		//全体の画面を表示
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(operationButton, BorderLayout.PAGE_END);
	}

	//タスクの追加をするメソッド
	public void taskAdd() {
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//入力内容からタスクを読み込む
				String nameString = nameField.getText();
				String periodString = periodField.getText();
				String detailString = detailField.getText();
				if (!inputCheck(nameString, periodString, detailString)) {
					//曜日を数字に変換
					int dayOfTheWeek = dayOfTheWeekChange();
					//毎週課題をweekTaskListに追加する
					TaskManagement.weekTaskList
							.add(new WeekTask(nameString, detailString, dayOfTheWeek, Integer.parseInt(periodString)));

					//更新情報をセーブする
					new SaveData().weekTaskSave();

					//taskListの内容更新
					JPanel weekTaskListJPanel = new WeekTaskListPanel();
					TaskManagement.cardLayoutPanel.add(weekTaskListJPanel, "weekTaskList");

					//記入内容の帳消し
					nameField.setText("");
					detailField.setText("");
					dayOfTheWeekComboBox.setSelectedItem("日曜日");
					periodField.setText("");
				}
			}
		});
	}

	//入力内容が適切かどうか判断するメソッド
	public boolean inputCheck(String nameString, String periodString, String detailString) {
		boolean result = true;
		//入力内容が適切かどうか判断
		StringBuilder stringCheck = new StringBuilder();
		stringCheck.append(periodString);
		String digitCheckString = stringCheck.toString();
		boolean digitCheck = true;
		for (int i = 0; i < digitCheckString.length(); i++) {
			if (!Character.isDigit(digitCheckString.charAt(i))) {
				digitCheck = false;
			}
		}
		if (nameString.contains(",")) {
			JOptionPane.showMessageDialog(addButton, "タイトルに「,」を含んでいます", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (!digitCheck) {
			JOptionPane.showMessageDialog(addButton, "期限年月日が適切な数字ではありません", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			result = false;
		}
		return result;
	}

	//曜日を数字に変換するメソッド
	public int dayOfTheWeekChange() {
		int result = 1;
		String dayOfTheWeek = dayOfTheWeekComboBox.getSelectedItem().toString();
		switch (dayOfTheWeek) {
		case "土曜日":
			result = 7;
			break;
		case "金曜日":
			result = 6;
			break;
		case "木曜日":
			result = 5;
			break;
		case "水曜日":
			result = 4;
			break;
		case "火曜日":
			result = 3;
			break;
		case "月曜日":
			result = 2;
			break;
		}

		return result;
	}

}
