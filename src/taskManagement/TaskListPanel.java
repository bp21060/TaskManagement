package taskManagement;

import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TaskListPanel {

	public JPanel MakePanel() {
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

		return p2;

	}

}
