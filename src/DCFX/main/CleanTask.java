import java.util.TimerTask;

public class CleanTask extends Thread {

	private String name;

	public CleanTask(String name) {
		this.name=name;
	}

	@Override
	public void run() {
		while(true){
			TimeCellMaker dayCellMaker=new TimeCellMaker("/Users/mac/影片的副本", 0.35);

			int timecell = 600 ;

			try {
				timecell=dayCellMaker.getTimeCellMac();
			} catch (Exception e) {
				// 抛出异常
				e.printStackTrace();
			}
			System.out.println(timecell);
			FileFolderCleaner cleaner=new FileFolderCleaner("/Users/mac/影片的副本",timecell);

			try {
				cleaner.deleter();
			} catch (Exception e) {
				// 抛出异常
				e.printStackTrace();
			}
			try {
				sleep(6*60*60*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//每6个小时执行一次
  	}
	}
//	public void run() {
//		int timecell=0;
//		FileFolderCleaner test=new FileFolderCleaner("/Users/mac/微信小程序", timecell);
//		try {
//			test.deleter();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
