package taskManagement;

import java.awt.Label;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class TaskAddPanel extends JPanel {

	public TaskAddPanel() {
		/*
		GridLayout layout = new GridLayout(3, 1);
		this.setLayout(layout);
		layout.setVgap(5);
		layout.setHgap(20);
		*/

		Label title = new Label("課題の追加");
		JTextField nameField = new JTextField("課題名", 15);
		JTextField yearField = new JTextField("年(西暦)", 4);
		JTextField monthField = new JTextField("月", 2);
		JTextField dayField = new JTextField("日", 2);
		JTextArea detailField = new JTextArea("詳細", 3, 20);
		detailField.setLineWrap(true);
		detailField.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		this.add(title);
		this.add(nameField);
		this.add(yearField);
		this.add(monthField);
		this.add(dayField);
		this.add(detailField);
	}

}
