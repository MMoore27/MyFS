README
==========
Description:
 
Enacts a UNIX-like file system that allows operations
to create, delete, read, write, and list directory
 
Details about the file system include a size of 128KB, one root directory, containing at most 16 files with each file having at most 8 blocks
with each block 1KB in size,  a file name no longer than 8 characters , allows duplicated file names, and data that can only be accessed
or set in 1KB blocks.


-The file system creates a file by scanning the index nodes and looking for one that is available. 
-The file system claims the first one it sees and takes the requested number of blocks. 
-If the requested amount exceeds the number allowed or exceeds the disk's available capacity, an error message is printed to the console. 
-The file system then writes the changes to disk. To delete a file, the file system scans the index nodes and compares their filenames to the 
provided filename. 
-If they match, the file system completely clears the index node, and all changes are saved to disk.  
-The file system writes the data from the buffer to the block. To read a file, the file system scans the nodes. 
The file system \obtains a pointer from the data pointer block and reads the block into the buffer.



Implementation Notes
 
 
1) Once the program is running, a copy of the super block is stored in memory
in a ByteBuffer, which can be modified byte-wise. Then the entire super block is
updated on disk.
 
