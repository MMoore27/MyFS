//Michael Moore
//Andrew Grossane
//Ryan Lee
//Jacob Porter

import java.io.*;

public class createFileSystem {

  public void createFS (String diskName) {
    System.out.println("Creating a 128 KB file [" + diskName + "]");
    System.out.println("This file will act as a dummy disk and will hold your filesystem.");

    FileOutputStream F = null;
    try {
      F = new FileOutputStream(diskName);
    } catch (Exception E) {
      System.err.println ("Error creating the file.");
      System.err.println ("Message : " + E.getMessage());
      System.err.println ("Format unsuccessful - disk unusable");
      System.exit (1);
    }

    System.out.println("Formatting filesystem...");
    byte [] memBlock1 = new byte [1024];
    int i;

    for (i = 0; i < memBlock1.length; i++) memBlock1[i] = 0;

    /* write super block */
    memBlock1 [0] = 1; /* mark superblock as allocated in the free block list
                      all other blocks are free, all inodes are zeroed out */

    /* write out the super block */
    try {
      F.write (memBlock1);
    } catch (Exception E) {
      System.err.println ("Error writin Block [0]");
      System.err.println ("Message : " + E.getMessage());
      System.err.println ("Format unsuccessful - disk unusable");
      System.exit (1);
    }

    /* write out the remaining 127 data blocks, all zeroed out */

    memBlock1[0]=0;
    for(i = 0; i < 127; i++)
      try {
        F.write (memBlock1);
      } catch (Exception E) {
        System.err.println ("Error writin Block [" + i + "]");
        System.err.println ("Message : " + E.getMessage());
        System.err.println ("Format unsuccessful - disk unusable");
        System.exit (1);
      }

    try {
      F.close();
      } catch (Exception E) {
        System.err.println ("Error finalizing format.");
        System.err.println ("Message : " + E.getMessage());
        System.err.println ("Format unsuccessful - disk unusable");
        System.exit (1);
    }

    System.out.println ("Format successful.");

  }

  public static void main (String [] args) {

    System.out.println();
    System.out.println();

    if (args.length == 0) {
      System.err.println ("Please pass the dummy disk filename as a parameter.");
      System.exit(0);
    }

    new createFileSystem().createFS (args[0]);
  }
}