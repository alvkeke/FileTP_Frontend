package FileTP_Front.GUI.FileList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Objects;
import java.util.Vector;

public class FileListView extends JList<File> {

	private File mCurrentPath = null;
	private Vector<File> mFiles;
	private FileListViewCallback mCallback;
	private JPopupMenu mRightPopMenu = null;

	private void listInit()
	{
		mFiles = new Vector<>();

		setListData(mFiles);
		setFont(new Font("宋体", Font.PLAIN, 20));

		FileListRender listRender = new FileListRender();
		setCellRenderer(listRender);
		addMouseListener(new FileListMouseListener());
	}

	private class FileListMouseListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2)
			{
				int index = getSelectedIndex();
				File f_sel = mFiles.get(index);
				mCallback.ItemDBClick(f_sel);
			}
			else if (e.getButton() == MouseEvent.BUTTON3)
			{
				int index = locationToIndex(e.getPoint());
				setSelectedIndex(index);
				if (mRightPopMenu != null)
				{
					mRightPopMenu.show(FileListView.this, e.getX(), e.getY());
				}
			}
			else if (e.getButton() == 4)
			{
				backParent();
			}
//				else
//				{
//					System.out.println(e.getButton());
//				}
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	}

	public FileListView(FileListViewCallback callback)
	{
		this.mCallback = callback;
		listInit();
	}

	public FileListView(FileListViewCallback callback, String rootPath)
	{
		this.mCallback = callback;
		listInit();
		File path = new File(rootPath);
		if (path.exists() && path.isDirectory())
		{
			changeDir(path);
		}
	}

	public FileListView(FileListViewCallback callback, File rootPath)
	{
		this.mCallback = callback;
		listInit();
		if (rootPath.exists() && rootPath.isDirectory())
		{
			changeDir(rootPath);
		}
	}

	private boolean isFileHide(File f)
	{
		return f.isHidden();
	}

	public void changeDir(File path)
	{
		if (path == null) return;
		if (!path.exists()) return;
		if (!path.isDirectory()) return;

		mFiles.clear();
		mFiles.add(new File(path, "."));
		mFiles.add(new File(path, ".."));
		for (File e : Objects.requireNonNull(path.listFiles()))
		{
			if (isFileHide(e)) continue;
			mFiles.add(e);
		}
		updateUI();
		mCurrentPath = path;
		mCallback.pathChanged(path);
	}

	public File getCurrentPath()
	{
		return mCurrentPath;
	}

	public void backParent()
	{
		if (mCurrentPath == null)
		{
			return;
		}
		changeDir(mCurrentPath.getParentFile());
	}

	public void setRightPopMenu(JPopupMenu mRightPopMenu) {
		this.mRightPopMenu = mRightPopMenu;
	}

	public File getItem(int index)
	{
		return mFiles.get(index);
	}

}