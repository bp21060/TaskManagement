package taskManagement;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

public class WeekTaskListPanel extends JPanel {

	public WeekTaskListPanel() {

		//タスク一覧タスクの表示
		JPanel mainPanel = new JPanel();
		GroupLayout layout2 = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout2);

		//間隔開ける
		layout2.setAutoCreateGaps(true);
		layout2.setAutoCreateContainerGaps(true);
	}
}
