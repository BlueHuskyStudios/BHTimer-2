
import bht.tools.Constants;
import java.io.FilePermission;

/**
 * AppDataGetter, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-03-03
 */
public class AppDataGetter
{
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		System.out.println(System.getenv("APPDATA"));
		java.io.FilePermission f = new FilePermission(Constants.SANDBOX + "test\\log.txt", "read,write");
	}
}
