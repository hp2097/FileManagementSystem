package storage_management;

import java.util.List;

public interface IOperations {

	   String  savingFile(File file);
	   String deleteFile(String fileId);
	   String readFileFromMemory(String fileId);
	   int getAvailaibleBlock();
	   List<File> getTotalFilesInMemory();
	   String[] getBlockArray();
}
