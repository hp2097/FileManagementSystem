package storage_management;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Memory implements IOperations {

	private String[] blockArray;
	private int totalBlocksAvailaible;
	private List<File> totalFilesInMemory; 
	int currentIndex;
	static Memory memoryObject;

	//Strictly Private Constructor for SingletonPattern
	private Memory(int totalBlocks) {

		//Initializing The block array and total Available blocks
		blockArray= new String[totalBlocks];
		totalBlocksAvailaible=totalBlocks;
		totalFilesInMemory=new ArrayList<File>();

	}

	public static Memory memory(int totalBlocks){
		if(memoryObject !=null)
		{
			return memoryObject;
		}
		memoryObject = new Memory(totalBlocks);
		return memoryObject;
	}


	@Override
	public int getAvailaibleBlock() {
		return totalBlocksAvailaible;
	} 

	public String[] getBlockArray() {
		return blockArray;
	}


	@Override
	public List<File> getTotalFilesInMemory() {
		return totalFilesInMemory;
	}


	//Method to get the file from the Memory
	public File getFileFromMemory(String fileId){

		try {
			File foundFile=totalFilesInMemory.stream().
					filter(file-> fileId.equals(file.getFileId())).
					findAny().
					orElse(null);
			if(foundFile!=null)
			{
				return foundFile;
			}
		}
		catch(Exception e)
		{
			System.out.println("Error"+ e);
		}
		return null;
	}


	@Override	
	public   String  savingFile(File file) {

		//Checking if the File ID name is Already in the system
		if(totalFilesInMemory.stream().anyMatch(data -> file.getFileId().equals(data.getFileId()))) {
			return "\nThe File Id is already Present in the System Please enter some other ID";
		}

		//If the file size (in Blocks is greater than in memory)
		if(file.getNumberOfBlocksAllocated().length>totalBlocksAvailaible)
		{
			return "\nThere is no space availaible, please delete some!";
		}
		else {
			int count =0;
			for(int i=0;i<blockArray.length;i++)
			{
				if(blockArray[i]==null)
				{
					if(file.getNumberOfBlocksAllocated().length==count)
					{
						break;
					}
					file.getNumberOfBlocksAllocated()[count]=i;
					blockArray[i]=file.getFileId();
					totalBlocksAvailaible-=1;
					count++;
				}
			}

			totalFilesInMemory.add(file);

			return "\nFile is saved"+"\n\nTotal Availaible blocks after saving File are:-"+
			totalBlocksAvailaible+"\nBlocks allocated to the File in the memory"+
			file.getFileId()+" ---> "+Arrays.toString(file.getNumberOfBlocksAllocated());
		}


	}


	@Override
	public String deleteFile(String fileId) {

		//Getting File from the List with specific Id with the help of getFileFromMemoryMethod
		File foundFile=getFileFromMemory(fileId);

		//File Found
		if(foundFile!=null)
		{
			int allocatedBlock=0;

			//Adding totalAvalaibleBlocks after deleting
			totalBlocksAvailaible+=foundFile.getNumberOfBlocksAllocated().length;

			//Removing files from the block
			for(int i=0;i<foundFile.getNumberOfBlocksAllocated().length;i++)
			{
				allocatedBlock=foundFile.getNumberOfBlocksAllocated()[i];
				blockArray[allocatedBlock]=null;
			}

			//Removing the file Stored in Memory
			totalFilesInMemory.remove(foundFile);
			return "\nYour file:- "+ fileId +" is Deleted " ;
		}

		else {
			return "\nNo File Found Pleae try Again";
		}

	}


	@Override
	//Get the Blocks from the file
	public String readFileFromMemory(String fileId) {

		//Checking if the file is in the memory with the help of 
		File founFile=getFileFromMemory(fileId);
		if(founFile!=null)
		{
			return "\nFor the File:- "+founFile.getFileId()+" the blocks are allocated in the memory---->  "+Arrays.toString(founFile.getNumberOfBlocksAllocated());
		}
		return "\nNo File for the name: "+ fileId +" is stored";

	}
}
