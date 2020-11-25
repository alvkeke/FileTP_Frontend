package GUI;

import GUI.FileList.FileListView;
import GUI.FileList.FileListViewCallback;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainWindow extends JFrame implements FileListViewCallback {

	FileListView mFileList;
	public MainWindow()
	{
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mFileList = new FileListView(this, "C:/Users/alvis/desktop/");
		JScrollPane jsp = new JScrollPane(mFileList);
		this.add(jsp);

		File path = mFileList.getCurrentPath();
		System.out.println(path.getAbsoluteFile());

		JPopupMenu  pmenu = new JPopupMenu();
		JMenuItem mI1, mI2, mI3;
		mI1 = new JMenuItem("item 1");
		mI2 = new JMenuItem("item 2");
		mI3 = new JMenuItem("item 3");

		pmenu.add(mI1);
		pmenu.add(mI2);
		pmenu.add(mI3);

		mFileList.setRightPopMenu(pmenu);

		mI1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = mFileList.getSelectedValue();
				if (f == null) return;
				System.out.println(f.getName());
			}
		});


		this.setVisible(true);
	}

	@Override
	public void ItemDBClick(File f) {
		mFileList.changeDir(f);
	}

	@Override
	public void pathChanged(File path) {

	}
}
