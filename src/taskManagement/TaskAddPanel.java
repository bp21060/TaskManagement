package taskManagement;

import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JPanel;

public class TaskAddPanel extends JPanel {

	public TaskAddPanel() {
		GridLayout layout = new GridLayout(3, 1);
		this.setLayout(layout);
		layout.setVgap(5);
		layout.setHgap(20);

		Label title = new Label("課題の追加");

	}

}
