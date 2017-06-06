import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class MicListen {
	
	private TargetDataLine line;
	private SourceDataLine sourceLine;
	
	private AudioFormat format;
	private byte[] data;
	
	
	
	public MicListen()
	{

	}//end constructor
	
	
	public void listen() throws Exception
	{
		format = new AudioFormat(44100, 16, 2, true, true);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		DataLine.Info sourceInfo = new DataLine.Info(SourceDataLine.class, format);
		
		if (!AudioSystem.isLineSupported(info)) 
		{
		    throw new Exception();
		}
		
		try{
			  line = (TargetDataLine) AudioSystem.getLine(info);
			  line.open(format);
			  // Begin audio capture.
			  line.start();
			  
			  //for playback
			  sourceLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);
			  sourceLine.open(format);
			  sourceLine.start();
			
			  ByteArrayOutputStream out  = new ByteArrayOutputStream();
			  int numBytesRead = 0;
			  data = new byte[line.getBufferSize() / 5];

			  

			  System.out.println("Recording starting");
			  
			  while(true)
			  {
				  numBytesRead = line.read(data, 0, data.length);
				  out.write(data, 0, numBytesRead);
				  
				  sourceLine.write(data, 0, numBytesRead);
			  }
			 
		}catch(LineUnavailableException e)
		{
			e.printStackTrace();
		}
			
		
	}//end listen()
	
	public void printByteArray()
	{
		for(int i = 0; i < data.length;i++)
		{
			//System.out.print(data[i] + " ");
			System.out.println(Arrays.toString(data));
		}
	}
	
	
	public void closeMic()
	{
		line.close();
	}//end closeMic()


}//end class
