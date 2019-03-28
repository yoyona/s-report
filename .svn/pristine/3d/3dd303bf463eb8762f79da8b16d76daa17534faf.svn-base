package net.greatsoft.core.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberUtils {
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * * 两个Double数相加 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double add(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.add(b2).doubleValue());
	}

	/**
	 * * 两个Double数相减 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double sub(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.subtract(b2).doubleValue());
	}

	/**
	 * * 两个Double数相乘 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double mul(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.multiply(b2).doubleValue());
	}

	/**
	 * * 两个Double数相除 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double div(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	/**
	 * * 两个Double数相除，并保留scale位小数 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @param scale
	 *            *
	 * @return Double
	 */
	public static Double div(Double v1, Double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Double b1=0.0000; System.out.println(b1==0);
		 * System.out.println(String.valueOf(b1).equals("0"));
		 * System.out.println(new String("0.00").equals("0"));
		 */

		/*
		 * BigDecimal bd = new BigDecimal("2.9636556822491705E7");
		 * System.out.println(bd.toPlainString());
		 */

		Double add = add(1.0, 0.565167);
		Double mul = mul(29636556.82, add);
		Double formatNumber2 = FormatNumber2(mul);
		System.out.println(formatNumber2);
		BigDecimal bd = new BigDecimal(mul);
		System.out.println(bd.toPlainString());
	}

	/**
	 * 格式化数字 保留4位小数
	 * 
	 * @param v
	 * @return
	 */
	public static Double FormatNumber4(Double v) {
		BigDecimal bd = new BigDecimal(v);
		bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	public static BigDecimal getBigDecimal(Object value) {
		BigDecimal ret = null;
		if (value != null) {
			if (value instanceof BigDecimal) {
				ret = (BigDecimal) value;
			} else if (value instanceof String) {
				ret = new BigDecimal((String) value);
			} else if (value instanceof BigInteger) {
				ret = new BigDecimal((BigInteger) value);
			} else if (value instanceof Number) {
				ret = new BigDecimal(((Number) value).doubleValue());
			} else {
				throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass()
						+ " into a BigDecimal.");
			}
		} else {
			return new BigDecimal(0);
		}
		return ret;
	}

	/**
	 * 格式化数字 保留2位小数
	 * 
	 * @param v
	 * @return
	 */
	public static Double FormatNumber2(Double v) {
		BigDecimal bd = new BigDecimal(v);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

}
