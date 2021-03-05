package storage_management;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestCaseForProject {

	/*
	 * Initalizing block size and harddrive Size
	*/
	static double  sizeOfHardDisk_Mb=1;
	static double  blockSize_kb=1;
	public static final int MB_INTO_KB=1024;
	static IOperations memory;
	
	
	
	@BeforeAll
	 static void setUp(){
		double calcuateBlocks=(sizeOfHardDisk_Mb*MB_INTO_KB)/blockSize_kb;
		int totalBlocks_kb=(int) calcuateBlocks;
	    memory=Memory.memory(totalBlocks_kb);
	}
	
	
	@Test
	 void testSaveFile() {
		int data[]=new int[1];
		data[0]=0;
		File file=new File(1024,"hp",blockSize_kb);
		assertEquals("\nFile is saved\n\nTotal Availaible blocks after saving File are:-"+1023+"\nBlocks allocated to the File in the memoryhp ---> "+Arrays.toString(data),memory.savingFile(file),"Save Method Should Save the file name HP");
	}
	
	
}
	
