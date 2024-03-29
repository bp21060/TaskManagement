package taskManagement;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class TaskAddPanel extends JPanel {

	//コンポーネント
	Label title = new Label("課題の追加");
	Label nameLabel = new Label("名前");
	JTextField nameField = new JTextField(15);
	Label yearLabel = new Label("年");
	JTextField yearField = new JTextField(4);
	Label monthLabel = new Label("月");
	JTextField monthField = new JTextField(2);
	Label dayLabel = new Label("日");
	JTextField dayField = new JTextField(2);
	Label detaiLabel = new Label("詳細");
	JTextArea detailField = new JTextArea(4, 20);

	JButton returnButton = new JButton("元に戻る");
	JButton addButton = new JButton("課題追加");

	public TaskAddPanel() {

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

		//現在時刻を取得
		Calendar calendar = Calendar.getInstance();

		//初期の日付を現在時刻にする
		yearField.setText(String.valueOf(calendar.get(Calendar.YEAR)));
		monthField.setText(String.valueOf(calendar.get(Calendar.MONTH) + 1));
		dayField.setText(String.valueOf(calendar.get(Calendar.DATE)));

		//水平グループ
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(title)
								.addComponent(nameLabel)
								.addComponent(nameField)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup()
												.addComponent(yearLabel)
												.addComponent(yearField))
										.addGap(10, 10, 10)
										.addGroup(layout.createParallelGroup()
												.addComponent(monthLabel)
												.addComponent(monthField))
										.addGap(10, 10, 10)
										.addGroup(layout.createParallelGroup()
												.addComponent(dayLabel)
												.addComponent(dayField))
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
								.addComponent(yearLabel)
								.addComponent(monthLabel)
								.addComponent(dayLabel))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(yearField)
								.addComponent(monthField)
								.addComponent(dayField))
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
				String command = e.getActionCommand();
				TaskManagement.cardLayout.show(TaskManagement.cardLayoutPanel, command);
			}
		});
		returnButton.setActionCommand("taskList");

		//課題追加ボタンの操作
		taskadd();
		operationButton.setLayout(layout2);
		operationButton.add(returnButton);
		operationButton.add(addButton);

		//全体の画面を表示
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(operationButton, BorderLayout.PAGE_END);
	}

	//タスクの追加をするメソッド
	public void taskadd() {
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//入力内容からタスクを読み込む
				String nameString = nameField.getText();
				String yearString = yearField.getText();
				String monthString = monthField.getText();
				String dayString = dayField.getText();
				String detailString = detailField.getText();
				if (!inputCheck(nameString, yearString, monthString, dayString, detailString)) {
					TaskManagement.taskList.add(new Task(nameString, detailString, Integer.parseInt(yearString),
							Integer.parseInt(monthString) - 1, Integer.parseInt(dayString)));

					//更新情報をセーブする
					new SaveData().taskSave();

					//taskListの内容更新
					JPanel taskListJPanel = new TaskListPanel();
					TaskManagement.cardLayoutPanel.add(taskListJPanel, "taskList");

					//現在時刻を取得
					Calendar calendar = Calendar.getInstance();

					//記入内容の帳消し
					nameField.setText("");
					yearField.setText(String.valueOf(calendar.get(Calendar.YEAR)));
					monthField.setText(String.valueOf(calendar.get(Calendar.MONTH) + 1));
					dayField.setText(String.valueOf(calendar.get(Calendar.DATE)));
					detailField.setText("");
				}
			}
		});
	}

	//入力内容が適切かどうか判断するメソッド
	public boolean inputCheck(String nameString, String yearString, String monthString, String dayString,
			String detailString) {
		boolean result = true;
		//入力内容が適切かどうか判断
		StringBuilder stringCheck = new StringBuilder();
		stringCheck.append(yearString);
		stringCheck.append(monthString);
		stringCheck.append(dayString);
		String digitCheckString = stringCheck.toString();
		boolean digitCheck = true;
		for (int i = 0; i < digitCheckString.length(); i++) {
			if (!Character.isDigit(digitCheckString.charAt(i))) {
				digitCheck = false;
			}
		}
		if (nameString.contains(",")) {
			JOptionPane.showMessageDialog(addButton, "タイトルに「,」を含んでいます", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (nameString.isEmpty()) {
			JOptionPane.showMessageDialog(addButton, "タイトルが書かれていません", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (!digitCheck || yearString.isEmpty() || monthString.isEmpty() || dayString.isEmpty()) {
			JOptionPane.showMessageDialog(addButton, "期限年月日が適切な数字ではありません", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			result = false;
		}
		return result;
	}

}
