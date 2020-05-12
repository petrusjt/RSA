package rsa.keygeneration.helper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ExtendedEuclidean {
	public static List<BigInteger> extendedEuclidean(BigInteger a, BigInteger b)
	{
		List<BigInteger> s = new ArrayList<BigInteger>();
		s.add(new BigInteger("1"));
		s.add(new BigInteger("0"));
		List<BigInteger> t = new ArrayList<BigInteger>();
		t.add(new BigInteger("0"));
		t.add(new BigInteger("1"));
		List<BigInteger> q = new ArrayList<BigInteger>();
		List<BigInteger> r = new ArrayList<BigInteger>();
		r.add(a);
		r.add(b);

		while(!r.get(r.size() - 1).equals(BigInteger.ZERO))
		{
			q.add(r.get(r.size() - 2).divide(r.get(r.size() - 1)));
			r.add(r.get(r.size() - 2).subtract(q.get(q.size() - 1).multiply(r.get(r.size() - 1))));
			s.add(s.get(s.size() - 2).subtract(q.get(q.size() - 1).multiply(s.get(s.size() - 1))));
			t.add(t.get(t.size() - 2).subtract(q.get(q.size() - 1).multiply(t.get(t.size() - 1))));
		}

		List<BigInteger> result = new ArrayList<BigInteger>();
		result.add(r.get(r.size() - 2));
		result.add(s.get(s.size() - 2));
		result.add(t.get(t.size() - 2));
		return result;
	}
}
