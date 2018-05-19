/**
 * The main program
 * @author BoscoGuo
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CleanTask thread1=new CleanTask("删除线程");
		thread1.run();
	}
}

