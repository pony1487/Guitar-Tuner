/*
 * TO DO:
 * Byte array has to be fast fourier transformed(?? Dunno how) This is how we get pitch
 * --Look up: Cooley–Tukey_FFT_algorithm
 * 
 * --You will need complex numbers
 * 
 * 	///http://blog.bjornroche.com/2012/07/frequency-detection-using-fft-aka-pitch.html
 * 	1)Read enough data to fill the FFT
 *	2)Low-pass the data
 *	3)Apply a window to the data
 * 	4)Transform the data using the FFT
 *  5)Find the peak value in the transformed data
 * 	6)Compute the peak frequency from from the index of the peak value in the transformed data
 */

import javax.swing.*;

public class Tuner {

	private int width, height;
	private JFrame frame;
	
	private PitchTracker pitchTracker;
	private MicListen micListen;
	
	
	
	public Tuner()
	{
		width = 800;
		height = 500;
		
		
		
		frame = new JFrame("Guitar Tuner");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
		
		pitchTracker = new PitchTracker();
		micListen = new MicListen();
	}
	
	public void test()
	{
		String a = pitchTracker.spell(293.66f);
		System.out.println(a);
		
		try {
			micListen.listen();
			micListen.printByteArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args)
	{
		Tuner t = new Tuner();
		t.test();
	}
}
