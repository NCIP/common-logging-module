package test;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String DISPLAY_DATE_FORMAT = "MM/dd/yyyy , HH:mm a";
		final String DATE_FORMAT = "MM/dd/yyyy";
		final String TIME_FORMAT = "HH:mm a";
		final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DISPLAY_DATE_FORMAT);
		final String ll ="1155756542578";
		
		java.util.Date d = new java.util.Date();
		long l = new Long(ll).longValue();
		d.setTime(l);
		System.out.println( sdf.format(d));
		long time = d.getTime();
		
		
		java.util.Date dd= new java.util.Date();
		dd.setTime(time);
		
		System.out.println( sdf.format(dd));
		

	}

}
