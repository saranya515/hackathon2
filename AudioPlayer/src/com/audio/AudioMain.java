package com.audio;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
 
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.plaf.SliderUI;
 
/**
 * This is an example program that demonstrates how to play back an audio file
 * using the Clip in Java Sound API.
 * @author www.codejava.net
 *
 */
public class AudioMain implements LineListener {
     
    /**
     * this flag indicates whether the playback completes or not.
     */
    boolean playCompleted;
    static JSlider slider = new JSlider();
    static JPanel pnlButton = new JPanel();

    static int i=0;
    
    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
    void play(File audioFile) {
    	
       
 
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(Clip.class, format);
 
            Clip audioClip = (Clip) AudioSystem.getLine(info);
 
            audioClip.addLineListener(this);
 
            audioClip.open(audioStream);
             
            audioClip.start();
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while(audioClip.isRunning()) {
//                        try {
//                            System.out.println(audioClip.getMicrosecondPosition());
//                            Thread.sleep(1000 / 10);
//                        } catch(InterruptedException ignored) {}
//                    }
//                }
//            }).start();
//
//            System.in.read();

             
            while (!playCompleted) {
                // wait for the playback completes
                try {
                	 slider.setMinimum(0);
                     slider.setMaximum((int)audioClip.getMicrosecondLength());
                     slider.setValue((int)audioClip.getMicrosecondPosition());

                    System.out.println(audioClip.getMicrosecondPosition());

                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
             
            audioClip.close();
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
         
    }
     
    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
         
        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");
             
        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }
 
    }
 
    public static void main(String[] args) {
//    	commonData com = null;
//       boolean flag =  com.isFLAG() ;
    	 JFrame frame = new JFrame();
        
        pnlButton.setBounds(100,100,100,100);
        frame.setTitle("Simple Audio Player");
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        pnlButton.setBackground(Color.yellow);
        pnlButton.add(slider);
        frame.setBackground(Color.black);
        frame.add(pnlButton);
        frame.setLocation(100, 100);
        JButton quitButton = new JButton("Play sound");
        quitButton.setSize(50,50);
        quitButton.setBounds(20, 20, 50, 50);
       pnlButton.add(quitButton);
       JLabel myLabel = new JLabel("Drag something here!", SwingConstants.CENTER);
       pnlButton.add(myLabel);
       quitButton.addActionListener(new ActionListener() {
    	   
           public void actionPerformed(ActionEvent e)
           {
        	   File audioFile = new File("C:/Users/user/wave.wav");
//        	   String audioFilePath = "E:/Test/Audio.wav";
               //Execute when button is pressed
               System.out.println("You clicked the button");
//               timer();
               AudioMain player = new AudioMain();
               player.play(audioFile);
               
           }
       });      
       
      
//timer();
       // Create the drag and drop listener
       MyDragDropListener myDragDropListener = new MyDragDropListener();
     //  commonData com = null;
//		 System.out.println("wav file"); 
//		flag =  com.isFLAG() ;
//   System.out.println(flag);
       // Connect the label with a drag and drop listener
       new DropTarget(myLabel, myDragDropListener);

        
       
    }
    public static  void timer(String[] data)
    {
//    	 String[] columns = new String[] {
//        "Id", "Location"
//    };
     
    //actual data for the table in a 2d array
//    Object[][] data = new Object[][] {
//        {1, "John", 40.0, false },
//        {2, "Rambo", 70.0, false },
//        {3, "Zorro", 60.0, true },
//    };

    //create table with data
    JLabel table = new JLabel(data[0]);
    pnlButton.add(table);
    }
 
}
