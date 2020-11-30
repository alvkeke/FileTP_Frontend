package FileTP_Front.GUI.FileList;

import java.io.File;

public interface FileListViewCallback {
	void ItemDBClick(File f);
	void pathChanged(File path);

}
