import java.io.File;
import java.util.Date;

/**
 * <p>按时间的文件夹清理器。
 * <p>给定一个文件夹路径filePath和一个时间timeCell，清理这个路径中的设定的时间之前的文件
 * <p>包含两个属性：路径和时间
 * <p>包含一个构造：需要两个属性
 * <p>包含4个方法：
 * 	<br>1：设置时间
 * 	<br>2：设置路径
 * 	<br>3：判断文件（或者文件夹）是否符合时间要求
 * 	<br>4：删除过时的文件或者空的过时的文件夹
 * @author CC
 *
 */
public class FileFolderCleaner {

	//两个属性
	private String filePath;
	private int timeCell;

	//构造函数1
	public FileFolderCleaner(){

	}
	//构造函数2，需要两个数据
	public FileFolderCleaner(String filePath,int timeCell){
		this.filePath=filePath;
		this.timeCell=timeCell;
	}

	//路径的setter
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	//时间的setter
	public void setTimeCell(int timeCell) {
		this.timeCell = timeCell;
	}

	//删除过时的文件或者空的过时的文件夹,给定路径String：
	public void deleter() throws Exception{
		File file=new File(filePath);
		if(!file.exists()){
			return;
		}
		if(!file.isDirectory()){
			return;
		}
		//子目录的字符串数组：tempList
		File[] tempList=file.listFiles();

		//递归法！！！
		for(int i=0;i<tempList.length;i++){
			if(tempList[i].isFile()){
				if(this.judger(tempList[i])){
					tempList[i].delete();
				}
			}
			else if(tempList[i].isDirectory()){
				if(tempList[i].list().length>0){
					this.setFilePath(tempList[i].getAbsolutePath());
					this.deleter();
				}
				if(tempList[i].list().length==0){
					if(this.judger(tempList[i])){
						tempList[i].delete();
					}
				}
			}
		}
		return;
	}

	//判断文件（或者文件夹）是否符合timeCell的时间要求,不满足（过时）返回true
	public boolean judger(File file){
		boolean flag=false;
		long modiTime=file.lastModified();
		Date now=new Date();
		long nowTime=now.getTime();
		int dayAgo=(int)((nowTime-modiTime)/(24L*60*60*1000));
		if(dayAgo>=timeCell){
			flag=true;
		}
		return flag;
	}
}
