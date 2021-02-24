package storage_management;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class File {
	
	private int fileSize_bytes;
	private String fileId;
	private int[] numberOfBlocksAllocated;
	public static final int KB_INTO_BYTES=1024;
	
	
	public File(int fileSize_bytes, String fileId,double blockSize) {
		
		try {
		double requiredBlockSize=(double)fileSize_bytes/(blockSize*KB_INTO_BYTES);
		this.fileSize_bytes = fileSize_bytes;
		this.fileId = fileId;
		this.numberOfBlocksAllocated =new int [(int)Math.ceil(requiredBlockSize)];
		}
		catch(Exception e)
		{
			System.out.println("You might have entered wrong type Please Restart the System:- "+e);
		}
		
	}
	
	public int getFileSize_bytes() {
		return fileSize_bytes;
	}
	
	public void setFileSize_bytes(int fileSize_bytes) {
		this.fileSize_bytes = fileSize_bytes;
	}
	
	public String getFileId() {
		return fileId;
	}
	
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	public int[] getNumberOfBlocksAllocated() {
		return numberOfBlocksAllocated;
	}

	
	
	
	
	
	
	
}
