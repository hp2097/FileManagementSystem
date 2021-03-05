package storage_management;
import java.util.Arrays;
import java.util.Scanner;


public class FileManagementSystem {

	public static final int MB_INTO_KB=1024;
	public static boolean badNumber=false;
	public static void main(String args[]) {

		double  sizeOfHardDisk_Mb=0;
		double  blockSize_kb=0;
		int choice;

		Scanner sc = new Scanner(System.in);
		do {
			try {

				//Taking user-defined value for the Storage Size
				sizeOfHardDisk_Mb=forceUserToTakeNumericValue(sc, "Please enter a Disk Size in MB:");

				//Taking user-defined value for the Block size
				blockSize_kb=forceUserToTakeNumericValue(sc, "Please Enter the Block Size in KB");

				if(blockSize_kb> (sizeOfHardDisk_Mb*MB_INTO_KB))
				{
					System.out.println("Please Enter the block size Equal or less than the disk size");
				}
			}

			catch(Exception e)
			{
				System.out.println("You Generated an Error:-"+ e);
			}

		}//Checking if block size is greater than size of hard disk
		while(blockSize_kb > (sizeOfHardDisk_Mb*MB_INTO_KB));



		//Calculating Total Blocks
		double calcuateBlocks=(sizeOfHardDisk_Mb*MB_INTO_KB)/blockSize_kb;

		//Getting precise block
		int totalBlocks_kb=(int) calcuateBlocks;



		System.out.println("\nThe Hard Drive is the Size of--> "+sizeOfHardDisk_Mb+"MB");
		System.out.println("\nThe BlockSize is --> "+blockSize_kb+"KB");
		System.out.println("\nTotal Number of Blocks created:- "+totalBlocks_kb);

		//Singleton Pattern Used as memory object created once, Polymorphism is also used 
		IOperations memory=Memory.memory(totalBlocks_kb);


		// For Operations :- Save, Read and delete
		do {
			System.out.println("\nEnter your Choice:\n");
			System.out.println("1. Save a file\n");
			System.out.println("2. Delete a file\n");
			System.out.println("3. Read a file\n");
			System.out.println("4. Get The Block Array\n");
			System.out.println("0. Exit FS\n");
			choice = sc.nextInt();
			String fileId="";

			switch (choice) {
			//Saving the file
			case 1:{

				//Taking fileId from User
				fileId=userDefinedFileId(sc);

				//Taking user-defined value for the Block size
				int fileSize = (int) forceUserToTakeNumericValue(sc, "\nEnter File Size in bytes:");

				//Creating new object or creating new file when saving a file
				File file=new File(fileSize,fileId,blockSize_kb);
				System.out.println(memory.savingFile(file));

				//Getting the blocks of the system(null means block is empty or availaible to save the file)
				printBlocks(memory);
				break;
			}

			// Deleting file if exist
			case 2:{
				//Checking if the storage is not empty before deleting
				if(memory.getTotalFilesInMemory().size()!=0)
				{
					fileId=userDefinedFileId(sc);
					System.out.println(memory.deleteFile(fileId));
					printBlocks(memory);
				}
				else {
					System.out.println("\nNo files found in the Storage Please add some");
				}

				break;
			}

			//Reading the Memory blocks allocated to the user.
			case 3:{
				//Checking if the storage is not empty before Reading
				if(memory.getTotalFilesInMemory().size()!=0)
				{
					fileId=userDefinedFileId(sc);
					System.out.println(memory.readFileFromMemory(fileId));
				}
				else {
					System.out.println("\nNo Files in the Storage Please add some");
				}
				break;
			}

			//Getting Blocks for the storage device.
			case 4:{
				printBlocks(memory);
			}

			//Exit the system
			case 0:{
				break;
			}
			default:{
				System.out.println("Wrong Choice Please Enter Again");
			}   


			}
		}
		while(choice!=0);

	}

	public static void printBlocks(IOperations memory) {
		System.out.println("\nTotal Blocks in The Memory\nCurrent Available Blocks:- "+memory.getAvailaibleBlock()+", Total Blocks in Memory:- "+memory.getBlockArray().length+"\n\n(Null Represents Blocks are available to store File)\n\n"+Arrays.deepToString(memory.getBlockArray()));
	}


	//User will only take Numeric value for the disk size
	public static double forceUserToTakeNumericValue(Scanner sc,String printString) {
		double i=0;
		//Until The user doesnot enter size in value it will ask for it again while do while
		do{
			System.out.println(printString);
			try{
				i = sc.nextDouble(); 
				badNumber = false;
				System.out.println("\n");
				return i;
			}
			catch(Exception e){
				System.out.println("The value should be only Numeric for Size\n");
				sc.next();
				badNumber = true;
			}
		}while(badNumber);
		return i;
	}


	//File Id for the Reading Deleting or Saving the file
	public static String userDefinedFileId(Scanner sc) {
		String fileId;
		System.out.println("Enter File Id: ");
		fileId = sc.next().toUpperCase();
		return fileId;
	}
	
	public static String test(String data) {
		return data;
	}
}
