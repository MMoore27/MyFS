//Michael Moore
//Andrew Grossane
//Ryan Lee
//Jacob Porter


class MyFS
{
	int blockSize = 1024 ; //Declare the number of bytes per block
	private byte[] list ; //Will tell whether a block is free or used
	private byte[] block ; //This is an array for the block
	private ByteBuffer SuperBlock; //Declare the superblock
	private RandomAccessFile randomAccessFile; //Random access file
	
	
	
	
	
	public MyFS(char diskName[16])
	
	{
	   // open the file with the above name
	   
	   list=new byte [128];   
	  
	  // this file will act as the "disk" for your file system

	  //open file system
	  randomAccessFile = new RandomAccessFile(diskName,"rwd");
	  
	  //read super block 
	    block = new byte[blockSize];
	    randomAccessFile.seek(0);
		block_buf =//SuperBlock buffer
		list = new byte[128];//make a list
	    block_buf.get(list);	
		
		//error messages
		err.println ("Error creating Disk.");
	}


	public int create(char name[8], int size)
	{ //create a file with this name and this size
	   int freeBlocks=0;
	  // high level pseudo code for creating a new file

	  // Step 1: check to see if we have sufficient free space on disk by
	  // reading in the free block list. To do this:
	  // move the file pointer to the start of the disk file.
	  // Read the first 128 bytes (the free/in-use block information)
	  // Scan the list to make sure you have sufficient free blocks to
	  // allocate a new file of this size
	  
	  for (byte b: free_list)
	{
	    if (b==0) 
	    {
	    	freeBlocks++;     //Count the number of unused blocks
	    }
	}

	  // Step 2: we look  for a free inode om disk
	  // Read in a inode
	  // check the "used" field to see if it is free
	  // If not, repeat the above two steps until you find a free inode
	  // Set the "used" field to 1
	  // Copy the filename to the "name" field
	  // Copy the file size (in units of blocks) to the "size" field
	int base = 128;
	while(base+56<=blockSize && block_buf.getInt(base+52)==1)
	{
	    base = base+56;      //This checks to see if there is a free spot for a new file
	}
		if (base+56>blockSize)
		{
				err.println("Maximum 16 files reached. "
					   +"Can not create new file.");    //This error message prints if the file system is already full (16 files)
			return 1;
		}
	
	
	  // Step 3: Allocate data blocks to the file
	  // for(i=0;i<size;i++)
		// Scan the block list that you read in Step 1 for a free block
		// Once you find a free block, mark it as in-use (Set it to 1)
		// Set the blockPointer[i] field in the inode to this block number.
		// 
	 // end for
	 
	 
	 block_buf.position(base);
	block_buf.put(//FILE name goes here );//write name
	block_buf.putInt(base+16,size); //write size
	int mark = 0;
	for (int f = 0;f<size;f++)
	{ //write block pointers
	    for (int n=mark;n<list.length;n++)
	    {
	    	
	    	if (list[n]==0)
	    	{
	    		list[n]=1;
	    		block_buf.putInt(base+20+4*f,n);
	    		break;
	    	}
	    }
	    mark++;
	}

	  // Step 4: Write out the inode and free block list to disk
	  //  Move the file pointer to the start of the file 
	  // Write out the 128 byte free block list
	  // Move the file pointer to the position on disk where this inode was stored
	  // Write out the inode
	  
	  private int writeSuperBlock()
  {
	try
	{
	    randomAccessFile.seek(0);  //Moves back to start of file
	    randomAccessFile.write(block_buf.array());
	}
	
	writeSuperBlock();

} // End Create



	public int delete(char name[8])
	{
	  // Delete the file with this name

	  // Step 1: Locate the inode for this file
	  // Move the file pointer to the 1st inode (129th byte)
	  // Read in a inode
	  // If the iinode is free, repeat above step.
	  // If the iinode is in use, check if the "name" field in the
	  // inode matches the file we want to delete. IF not, read the next
	  //  inode and repeat
	  
	  int base = findInode(name);
	if (base < 0)
	{
	    err.printf("file %s not found%n", name);   //find inode for this file, exit if not found
	    return 1;
	}
	  
	  // Step 2: free blocks of the file being deleted
	  // Read in the 128 byte free block list (move file pointer to start of the disk and read in 128 bytes)
	  // Free each block listed in the blockPointer fields as follows:
	  // for(i=0;i< inode.size; i++) 
		 // freeblockList[ inode.blockPointer[i] ] = 0;
		 
		 for(int i=0;i<size; i++)
	{
	    int block_i = block_buf.getInt(base+20+4*i);    	//update free block list
	    list[block_i]=0;
	}

	  // Step 3: mark inode as free
	  // Set the "used" field to 0.
	  
	  block_buf.putInt(base+52,0);
		

	  // Step 4: Write out the inode and free block list to disk
	  //  Move the file pointer to the start of the file 
	  // Write out the 128 byte free block list
	  // Move the file pointer to the position on disk where this inode was stored
	  // Write out the inode

	  writeSuperBlock();
	} // End Delete




	public int ls(void)
	{ 
	  // List names of all files on disk

	  // Step 1: read in each inode and print!
	  // Move file pointer to the position of the 1st inode (129th byte)
	  // for(i=0;i<16;i++)
		// REad in a inode
		// If the inode is in-use
		  // print the "name" and "size" fields from the inode
	 // end for
		
		/**if the file is found
		 *  print all free blocks **/
		 for(i=0;i<16;i++);
		 /**try to read inode**/
		 /**if the inode is in use
		  *   print the inode's name and size
		  *catch(exception e)
		  *  print "error in reading the file
		  *  return 1**/
		 
		 

	} // End ls

	public int read(char name[8], int blockNum, char buf[1024])
	{

	   // read this block from this file

	   // Step 1: locate the inode for this file
	   // Move file pointer to the position of the 1st inode (129th byte)
	   // Read in a inode
	   // If the inode is in use, compare the "name" field with the above file
	   // IF the file names don't match, repeat
		
		/**if the file is found:
		 * 	print the name and block number of the file
		 *  name = name2
		 * if name2 = name:
		 * 	break
		 * else
		 * 	return name2**/

		
	   // Step 2: Read in the specified block
	   // Check that blockNum < inode.size, else flag an error
	   // Get the disk address of the specified block
	   // That is, addr = inode.blockPointer[blockNum]
	   // move the file pointer to the block location (i.e., to byte #addr*1024 in the file)

		// Read in the block! => Read in 1024 bytes from this location into the buffer "buf"
		
		if (base < 0)
			println("Cannot find file")
		if (blockNum < inode.size)
			/**Print the file
			 * find the inode
			 * address = inode.blockPointer[blockNum]
			 * blockNum = addr*1024**/
		/**print block**/
		else
			/**print error**/
		try
		/**find the address of the inode using blockNum**/
		catch 
		/**print "Error reading from disk"**/
		
		
		
	} // End read


	public int write(char name[8], int blockNum, char buf[1024])
	{

	   // write this block to this file

	   // Step 1: locate the inode for this file
	   // Move file pointer to the position of the 1st inode (129th byte)
	   // Read in a inode
	   // If the inode is in use, compare the "name" field with the above file
	   // IF the file names don't match, repeat
		
		/**if the file is found:
		 * 	print the name and block number of the file
		 *  name = name2
		 * if name2 = name:
		 * 	break
		 * else
		 * 	return name2**/

		
	   // Step 2: Write to the specified block
	   // Check that blockNum < inode.size, else flag an error
	   // Get the disk address of the specified block
	   // That is, addr = inode.blockPointer[blockNum]
	   // move the file pointer to the block location (i.e., byte # addr*1024)
		
		if (base < 0)
			println("Cannot find file")
		if (blockNum < inode.size)
			/**Print the file
			 * find the inode
			 * address = inode.blockPointer[blockNum]
			 * blockNum = addr*1024**/
		/**print block**/
		else
			/**print error**/
		try
		/**find the address of the inode using blockNum
		 * write a new file**/
		catch 
		/**print "Error writing to disk"**/
		
		// Write the block! => Write 1024 bytes from the buffer "buff" to this location

		
		
	} // end write

}