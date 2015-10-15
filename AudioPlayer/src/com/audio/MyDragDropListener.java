package com.audio;

import java.util.List;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.Timer;


class MyDragDropListener implements DropTargetListener {
	
	File[] listOfFiles;
	static String dat = "nothing";
    @Override
    public void drop(DropTargetDropEvent event) {

        // Accept copy drops
        event.acceptDrop(DnDConstants.ACTION_COPY);

        // Get the transfer which can provide the dropped item data
        Transferable transferable = event.getTransferable();

        // Get the data formats of the dropped item
        DataFlavor[] flavors = transferable.getTransferDataFlavors();

        // Loop through the flavors
        for (DataFlavor flavor : flavors) {

            try {

                // If the drop items are files
                if (flavor.isFlavorJavaFileListType()) {

                    // Get all of the dropped files
                    List files = (List) transferable.getTransferData(flavor);
//                    while (files.s) {
                    System.out.println(files.size());
                    if(files.size()==1)
                    {
                    	String name = files.get(0).toString();
                    	File folder = new File(name);
                   	 listOfFiles = folder.listFiles();
                   	 try
                   	 {
                   System.out.println(listOfFiles.length);
                   	    for (int j = 0; j < listOfFiles.length; j++) {
                   	      if (listOfFiles[j].isFile()) {
                   	        System.out.println("File " + listOfFiles[j].getName());
                   	      } else if (listOfFiles[j].isDirectory()) {
                   	        System.out.println("Directory " + listOfFiles[j].getName());
                   	      }
                   	    }
                    }
                    
                    catch (NullPointerException e) {
						// TODO: handle exception
                    	System.out.print(e);                    	
					}
                    
                    for (int i = 0; i < files.size(); i++) 
                    {
                    	int count = 0;
                    	 System.out.println("File path is '" + files.get(i) + "'.");
                    	 File fil = (File) files.get(i);
                    	 String path = fil.getName() ;
//                    	 String[] data = null;data[count]=fil.toString();

                    	
                    	 String[] parts = path.split("\\.(?=[^\\.]+$)");
                    	 
                    	 
                    	 if(parts.length==1)
                    		 
                    	 {
                    		 for (int j = 0; j < listOfFiles.length; j++) {
                    		 parts = listOfFiles[j].toString().split("\\.(?=[^\\.]+$)");
                    		 System.out.println(parts[1]);
                    		 String part2 = parts[1];
//                    		 int count = 0;
//                        	 System.out.println(part2);
                        	 if(part2.equals("wav")||part2.equals("ogg"))
                        	 {
                     		fil = listOfFiles[j];
//                     		data.clone();
//                     		data[count]=fil.toString();
//                     		
//                     		AudioMain.timer(data);
                     		dat = fil.toString();
                     		ButtonGroup group = new ButtonGroup();
                     		JRadioButton table;
                     		count++;
                     		table = new JRadioButton(dat);
                     		group.add(table);
                     	    AudioMain.pnlButton.add(table);
                        		 System.out.println("wav file"); 
                        		 commonData.setFLAG(true);
                        		 System.out.println( commonData.isFLAG());
                        		 if(commonData.isFLAG())
                        	       {
                        			 
                        			
                        			 AudioMain player = new AudioMain();
                      	           player.play(fil);  	
                        		    	
                        		    }
                        	    	  
                        	       }
                    		 }
//                    		 System.out.println(listOfFiles[0].toString().substring(1, listOfFiles[0].toString().lastIndexOf('.')));
                    	 }
                    	 else
                    	 {
                    	 String part2 = parts[1];
                    	 System.out.println(part2);
                    	 if(part2.equals("wav"))
                    	 {
                 		 commonData com = null;
                    		 System.out.println("wav file"); 
                    		 commonData.setFLAG(true);
                    		 System.out.println( commonData.isFLAG());
                    		 if(commonData.isFLAG())
                    	       {
                    			 
                    			
                    			 AudioMain player = new AudioMain();
                  	           player.play(fil);  	
                    		    	
                    		    }
                    	    	  
                    	       }
                    	 }
                    }
					
                }
            }
            }
             catch (Exception e) {

                // Print out the error stack
                e.printStackTrace();

            }
        }

        // Inform that the drop is complete
        event.dropComplete(true);

    }

    @Override
    public void dragEnter(DropTargetDragEvent event) {
    }

    @Override
    public void dragExit(DropTargetEvent event) {
    }

    @Override
    public void dragOver(DropTargetDragEvent event) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent event) {
    }
    

}

