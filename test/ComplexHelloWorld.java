
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.provider.SHA2;

/**
 * ComplexHelloWorld, made for a challenge, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 *
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-19
 */
public class ComplexHelloWorld
{
	private static final SHA2 SHA2;
	private static final byte[] OBJECTIVE_BYTES;
	private static final String OBJECTIVE;
	public static final String[] HASHES;
	private static final Logger LOGGER;

	static
	{
		SHA2 = new SHA2();
		OBJECTIVE_BYTES = new byte[]
		{
			72, 101, 108, 108, 111, 44, 32, 87, 111, 114, 108, 100, 33
		};
		OBJECTIVE = new String(OBJECTIVE_BYTES);
		HASHES = hashAllChars(OBJECTIVE);
		LOGGER = Logger.getLogger(ComplexHelloWorld.class.getName());
	}

	public static String hash(String password)
	{
		String algorithm = "SHA-256";
		MessageDigest sha256;
		try
		{
			sha256 = MessageDigest.getInstance(algorithm);
		}
		catch (NoSuchAlgorithmException ex)
		{
			try
			{
				LOGGER.logrb(Level.SEVERE, ComplexHelloWorld.class.getName(), "hash", null, "There is no such algorithm as " + algorithm, ex);
			}
			catch (Throwable t2)
			{
				//welp.
			}
			return "[ERROR]";
		}
		byte[] passBytes = password.getBytes();
		byte[] passHash = sha256.digest(passBytes);
		return new String(passHash);
	}

	public static void main(String... args)
	{
		StringBuilder sb = new StringBuilder();
		allHashes:
		for (String hash : HASHES)
			checking:
			for (char c = 0; c < 256; c++)
				if (hash(c + "").equals(hash))
					try
					{
						sb.append(c);
						break checking;
					}
					catch (Throwable t)
					{
						try
						{
							LOGGER.logrb(Level.SEVERE, ComplexHelloWorld.class.getName(), "main", null, "An unexpected error occurred", t);
						}
						catch (Throwable t2)
						{
							//welp.
						}
					}
		try
		{
			LOGGER.logrb(Level.INFO, ComplexHelloWorld.class.getName(), "main", null, sb + "", new Object[]
			{
			});
		}
		catch (Throwable t)
		{
			try
			{
				LOGGER.logrb(Level.SEVERE, ComplexHelloWorld.class.getName(), "main", null, "An unexpected error occurred", t);
			}
			catch (Throwable t2)
			{
				//welp.
			}
		}
	}

	private static String[] hashAllChars(String passwords)
	{
		String[] ret = new String[passwords.length()];
		for (int i = 0; i < ret.length; i++)
			ret[i] = hash(passwords.charAt(i) + "");
		return ret;
	}
}
