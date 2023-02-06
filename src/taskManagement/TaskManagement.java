package taskManagement;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TaskManagement extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TaskManagement frame = new TaskManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TaskManagement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel p = new JPanel();
		GridLayout layout = new GridLayout(1, 2);
		layout.setHgap(100);
		Container contentPane = getContentPane();

		//追加、削除ボタン
		JButton addButton = new JButton("課題の追加");
		JButton deleteButton = new JButton("完了した課題の削除");

		p.setLayout(layout);
		p.add(addButton);
		p.add(deleteButton);

		//タスクの表示
		JPanel p2 = new JPanel();
		GroupLayout layout2 = new GroupLayout(p2);
		p2.setLayout(layout2);

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

		//課題一覧パネルにスクロールを追加
		JScrollPane taskScroll = new JScrollPane(p2);
		taskScroll.getVerticalScrollBar().setUnitIncrement(20);

		//削除追加ボタンパネルと課題一覧パネルをcontentPaneに追加
		contentPane.add(p, BorderLayout.PAGE_END);
		contentPane.add(taskScroll, BorderLayout.CENTER);
	}

}
