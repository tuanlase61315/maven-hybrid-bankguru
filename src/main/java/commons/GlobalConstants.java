package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PROJECT_LOCATION = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String UPLOAD_FOLDER_LOCATION = PROJECT_LOCATION + getDirectorySlash("uploadFiles");
	public static final String DOWLOAD_FOLDER_LOCATION = PROJECT_LOCATION + getDirectorySlash("dowloadFiles");

	public static String getDirectorySlash(String folderName) {
		String separator = File.separator;
		return separator + "src" + separator + "main" + separator + "resources" + separator + folderName + separator;
	}		
}
