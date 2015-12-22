import java.util.*;

public class main {
	static Map<String,tagcl> alltags;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		alltags= new HashMap<String,tagcl>();
	}
	public static void newtag(int person,String tag)
	{	tagcl cltag;
		if(alltags.containsKey(tag))
			cltag=alltags.get(tag);
		else
			cltag=new tagcl(tag);
		cltag.person(person);
		alltags.put(tag, cltag);
	}
	//N is the number of total users in the system
	//tags are the tags of the current person which is p1, p2 is the person to be compared
	public static double similarity(String[] tags,int p1,int p2, int N)
	{	tagcl curtag = null;
		Set<String> tagset = new TreeSet<String>();
		for (int i=0;i<tags.length;i++)
		{	tagset.add(tags[i]);
		}
		String[] tagsar = new String[tagset.size()];
		tagsar= tagset.toArray(tagsar);
		double res=0;
		for (int i=0;i<tagsar.length;i++)
			curtag=alltags.get(tagsar[i]);
			res+=Math.log(N/curtag.hmpeople())*curtag.logfreq(p1)*curtag.logfreq(p2);
		return res;
	}
}
