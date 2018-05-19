import java.io.File;
import java.util.Date;

/**
 *
 */

/**
 * 生成FileFolderCleaner自动代理模式下需要的timeCell。
 * <p>
 * 属性：
 * <br>timeCell
 * <br>目标硬盘"witchDisk"
 * <br>目录witchFolder
 * <br>预设磁盘空闲率emptyRate
 * <p>
 * 构造器：磁盘，文件路径,timeCell
 * <p>
 * 方法：
 * <br>
 * 1：获取当前硬盘信息diskInfoGetter
 * <br>
 * 2：获取处理目录大小的方法folderSizeGetter
 * <br>
 * 3：计算时间的方法timeCellCalculator
 * <br>
 * 4：timeCellGetter
 *
 * @author CC
 *
 */
public class TimeCellMaker {

	private int timeCell;

	private double emptyRate;
	public void setEmptyRate(double emptyRate) {
		this.emptyRate = emptyRate;
	}



	private String witchDisk;
	private String witchFolder;//完整路径
//	File root = new File(witchDisk + ":");
//	File folder = new File(witchFolder);


	/**
	 * 构造器1（针对windows）：需要盘符和目录，硬盘无需输入冒号
	 *
	 * @param witchDisk
	 * @param witchFolder
	 */
	public TimeCellMaker(String witchDisk,String witchFolder) {
		this.witchDisk = witchDisk;// 无需输入冒号
		this.witchFolder = witchFolder;
	}
	/**
	 * 构造器1（针对Linux）：只需要目录
	 *
	 * @param witchFolder
	 */
	public TimeCellMaker(String witchFolder) {
		this.witchFolder = witchFolder;
	}
	/**
	 * 构造器1（针对windows）：需要盘符和目录，硬盘无需输入冒号
	 *
	 * @param witchDisk  目标磁盘
	 * @param witchFolder 目标文件夹
	 * @param emptyRate 空闲率
	 */
	public TimeCellMaker(String witchDisk,String witchFolder,double emptyRate) {
		this.witchDisk = witchDisk;// 无需输入冒号
		this.witchFolder = witchFolder;
		this.emptyRate=emptyRate;
	}
	/**
	 * 构造器1（针对Linux）：只需要目录
	 *
	 * @param witchFolder
	 */
	public TimeCellMaker(String witchFolder,double emptyRate) {
		this.witchFolder = witchFolder;
		this.emptyRate=emptyRate;
	}


	long size = 0L;
	/**
	 * 获取指定目录中某一时间节点前文件的总大小,需要指定File和时间节点,单位字节b
	 *
	 * @param dayAgo
	 * @return size
	 * @throws Exception
	 *
	 */
	public long folderSize(int dayAgo) throws Exception {
		File file=new File(witchFolder);
		//确保是存在并且是目录
		if(!file.exists()){
			return 0L;
		}
		if(!file.isDirectory()){
			return 0L;
//			return 	file.length() ;
		}

		File[] children = file.listFiles();
		//递归法！！！
		for(int i=0;i<children.length;i++){
//			temp=new File(witchFolder+File.separator+children[i]);
			if(children[i].isFile()){
				if (this.judger(children[i]) >= dayAgo) {
					 size +=children[i].length();//1兆字节(Mb)=1048576字节(b)
				}
			}
			else if(children[i].isDirectory()){
				if(children[i].list().length>0){
					this.setWitchFolder(children[i].getAbsolutePath());
					this.folderSize(dayAgo);

				}

			}
		}

		return size;

	}


	public void setWitchFolder(String witchFolder) {
		this.witchFolder = witchFolder;
	}

	/**
	 * 判断某一个文件是几天前的，其中不到一天的按“0”算，不到两天的按“1”算，类推.....。
	 *
	 * @param file
	 * @return dayAgo
	 */
	public int judger(File witchFolder) {
		long modiTime = witchFolder.lastModified();
		Date now = new Date();
		long nowTime = now.getTime();
		int dayAgo = (int) ((nowTime - modiTime) / (24L * 60 * 60 * 1000));
		return dayAgo;
	}

	/**
	 * 计算出满足磁盘空闲率超过预设的emptyRate，并且保留文件不少于7天的timeCell,timeCell的预设值为60.
	 *
	 * @return timeCell
	 * @throws Exception
	 */
	public int timeCellCalculator() throws Exception {
		File root = new File(witchDisk + ":");
		timeCell = 60;

		long freeSize = root.getFreeSpace() ;//单位：b
		long totalSize = root.getTotalSpace();//单位：b
		double freeRate = ((double) (freeSize))/((double)(totalSize));
		while ((freeRate < emptyRate) && timeCell > 10) {
			timeCell--;
			freeRate = ((double) ((freeSize + this.folderSize(timeCell)))) / ((double)(totalSize));
			size=0L;//调用folderSize前，必须归零size
		};
		return timeCell;
	}
	//Mac调用
	public int timeCellCalculatorMac() throws Exception {
		File[] root = File.listRoots();
		timeCell = 600;

		long freeSize = root[0].getFreeSpace() ;//单位：b
		System.out.println(freeSize);
		long totalSize = root[0].getTotalSpace();//单位：b
		System.out.println(totalSize);
		double freeRate = ((double) (freeSize))/((double)(totalSize));
		System.out.println(freeRate);
		while ((freeRate <emptyRate)&&(timeCell > 10)) {

			freeRate = ((double) ((freeSize + this.folderSize(timeCell)))) / ((double)(totalSize));
			timeCell--;
			size=0L;//调用folderSize前，必须归零size
			this.setWitchFolder("/Users/mac/影片的副本");
		};
		return timeCell;
	}

	/**
	 * 本程序所有方法的统一出口
	 *
	 * @return timeCell
	 * @throws Exception
	 */
	public int getTimeCellMac() throws Exception {
		timeCell = this.timeCellCalculatorMac();
		return timeCell;
	}

	public int getTimeCell() throws Exception {
		timeCell = this.timeCellCalculator();
		return timeCell;
	}
}
