import java.util.Arrays;

public class ArrayConversion
{
	public static Object[] deepToDouble(Object[] original)
	{
		Object[] ret = new Object[original.length];
		for(int i = 0; i < ret.length; i++)
			if (original[i] instanceof Object[])
				ret[i] = deepToDouble((Object[])original[i]);
			else
				ret[i] =
				(
					original[i] instanceof Number
						? ((Number)original[i]).doubleValue()
						: Double.NaN
				);
		return ret;
	}
	
	public static void main(String... args)
	{
		Object[] test = new Object[]{1, new Object[]{1, 2, 3}, 3};
		System.out.println(Arrays.deepToString(test));
		System.out.println(Arrays.deepToString(deepToDouble(new Object[]{1, new Object[]{1, 2, 3}, 3})));
	}
}
