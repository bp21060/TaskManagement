package taskManagement;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class WeekTaskAddPanel extends JPanel {
	//コンポーネント
	Label title = new Label("課題の追加");
	Label nameLabel = new Label("名前");
	JTextField nameField = new JTextField(15);
	Label dayOfTheWeekLabel = new Label("開始曜日");
	Label periodLabel = new Label("期限");
	JTextField periodField = new JTextField(2);
	Label periodEndLabel = new Label("日後");
	Label detaiLabel = new Label("詳細");
	JTextArea detailField = new JTextArea(4, 20);

	//ラジオボタン
	ButtonGroup dayOfWeek = new ButtonGroup();
	JRadioButton monday = new JRadioButton("月", true);
	JRadioButton tuseday = new JRadioButton("火");
	JRadioButton wednesday = new JRadioButton("水");
	JRadioButton thursday = new JRadioButton("木");
	JRadioButton friday = new JRadioButton("金");
	JRadioButton saturday = new JRadioButton("土");
	JRadioButton sunday = new JRadioButton("日");

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

		//ボタングループの設定
		dayOfWeek.add(monday);
		dayOfWeek.add(tuseday);
		dayOfWeek.add(wednesday);
		dayOfWeek.add(thursday);
		dayOfWeek.add(friday);
		dayOfWeek.add(saturday);
		dayOfWeek.add(sunday);

		//水平グループ
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(title)
								.addComponent(nameLabel)
								.addComponent(nameField)
								.addComponent(dayOfTheWeekLabel)
								.addGroup(layout.createSequentialGroup()
										.addComponent(monday)
										.addComponent(tuseday)
										.addComponent(wednesday)
										.addComponent(thursday)
										.addComponent(friday)
										.addComponent(saturday)
										.addComponent(sunday))
								.addGap(10, 10, 10)
								.addGroup(layout.createSequentialGroup()
										.addComponent(periodLabel)
										.addComponent(periodField)
										.addComponent(periodEndLabel)
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
								.addComponent(dayOfTheWeekLabel))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(monday)
								.addComponent(tuseday)
								.addComponent(wednesday)
								.addComponent(thursday)
								.addComponent(friday)
								.addComponent(saturday)
								.addComponent(sunday))
						.addGap(10, 10, 10)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(periodLabel)
								.addComponent(periodField)
								.addComponent(periodEndLabel))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(detaiLabel))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(detailField)));

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
					monday.setSelected(true);
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
		if (saturday.isSelected()) {
			result = 7;
		} else if (friday.isSelected()) {
			result = 6;
		} else if (thursday.isSelected()) {
			result = 5;
		} else if (wednesday.isSelected()) {
			result = 4;
		} else if (tuseday.isSelected()) {
			result = 3;
		} else if (monday.isSelected()) {
			result = 2;
		}

		return result;
	}

}
