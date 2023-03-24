package taskManagement;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

public class WeekTaskReflect {

	public WeekTaskReflect() {
		// 現在日時と最終使用日を取得
		Calendar today = Calendar.getInstance();
		Calendar lastUseDate = getLastUseDate();

		//現在日時を最終使用日に更新
		setLastUseDate(today);

	}

	public Calendar getLastUseDate() {
		//最終仕様日(問題があった場合は現在日時を返答するため初期値は現在日時)
		Calendar lastUseDate = Calendar.getInstance();

		return lastUseDate;
	}

	public void setLastUseDate(Calendar lastUseDate) {
		try {
			//ファイル読み込み
			File saveFile = new File("lastUseDate.csv");
			FileWriter fileWriter = new FileWriter(saveFile);

			//セーブデータの内容書き込み
			fileWriter.write(lastUseDate.get(Calendar.YEAR) + "," + lastUseDate.get(Calendar.MONTH) + ","
					+ lastUseDate.get(Calendar.DATE) + "\r\n");
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
