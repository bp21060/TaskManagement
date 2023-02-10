package taskManagement;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TaskListPanel extends JPanel implements ActionListener {

	JPanel cardLayoutPanel;
	CardLayout cardLayout;

	public TaskListPanel(JPanel cardLayoutPanel, CardLayout cardLayout) {

		//カードレイアウトを初期設定する
		this.cardLayoutPanel = cardLayoutPanel;
		this.cardLayout = cardLayout;

		//タスク一覧タスクの表示
		JPanel mainPanel = new JPanel();
		GroupLayout layout2 = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout2);

		//間隔開ける
		layout2.setAutoCreateGaps(true);
		layout2.setAutoCreateContainerGaps(true);

		List<JComponent> taskList = new ArrayList<>();

		//デバック
		int taskAmount = 20;

		for (int i = 0; i < taskAmount; i++) {
			JComponent label = new JLabel("課題" + i);
			JComponent detailButton = new JButton("詳細");
			JComponent completeButton = new JButton("完了");
			taskList.add(label);
			taskList.add(detailButton);
			taskList.add(completeButton);
		}

		//水平グループ
		SequentialGroup hGroup = layout2.createSequentialGroup();

		for (int i = 0; i < 3; i++) {
			ParallelGroup pGroup = layout2.createParallelGroup();
			for (int j = 0; j < taskAmount; j++) {
				pGroup.addComponent(taskList.get(3 * j + i));
			}
			hGroup.addGroup(pGroup);
		}

		layout2.setHorizontalGroup(hGroup);

		//垂直グループ
		SequentialGroup vGroup = layout2.createSequentialGroup();

		for (int i = 0; i < taskAmount; i++) {
			ParallelGroup pGroup2 = layout2.createParallelGroup();
			for (int j = 0; j < 3; j++) {
				pGroup2.addComponent(taskList.get(3 * i + j));
			}
			vGroup.addGroup(pGroup2);
		}

		layout2.setVerticalGroup(vGroup);

		//スクロールの追加
		JScrollPane taskScroll = new JScrollPane(mainPanel);
		taskScroll.getVerticalScrollBar().setUnitIncrement(20);

		//追加、削除ボタンのパネルを追加
		JPanel operationButton = new JPanel();
		GridLayout layout = new GridLayout(1, 2);
		layout.setHgap(100);

		JButton addButton = new JButton("課題の追加");
		addButton.addActionListener(this);
		addButton.setActionCommand("taskAdd");

		JButton deleteButton = new JButton("完了した課題の削除");
		operationButton.setLayout(layout);
		operationButton.add(addButton);
		operationButton.add(deleteButton);

		//全体の画面を表示
		this.setLayout(new BorderLayout());
		this.add(taskScroll, BorderLayout.CENTER);
		this.add(operationButton, BorderLayout.PAGE_END);
	}

	//ボタンがクリックされた時の操作
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String command = actionEvent.getActionCommand();

		cardLayout.show(cardLayoutPanel, command);
	}

}
