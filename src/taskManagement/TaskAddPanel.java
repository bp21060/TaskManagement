package taskManagement;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class TaskAddPanel extends JPanel {

	public TaskAddPanel() {

		//タスク一覧タスクの表示
		JPanel mainPanel = new JPanel();
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);

		//間隔開ける
		layout.setAutoCreateGaps(false);
		layout.setAutoCreateContainerGaps(true);

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
		detailField.setLineWrap(true);
		detailField.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

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

		JButton returnButton = new JButton("元に戻る");
		//課題一覧画面への遷移ボタンの操作
		returnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				TaskManagement.cardLayout.show(TaskManagement.cardLayoutPanel, command);
			}
		});
		returnButton.setActionCommand("taskList");

		JButton addButton = new JButton("課題追加");
		//課題追加ボタンの操作
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TaskManagement.taskList.add(null);
			}
		});

		operationButton.setLayout(layout2);
		operationButton.add(returnButton);
		operationButton.add(addButton);

		//全体の画面を表示
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(operationButton, BorderLayout.PAGE_END);
	}

}
