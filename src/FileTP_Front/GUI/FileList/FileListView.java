package FileTP_Front.GUI.FileList;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Objects;
import java.util.Vector;

public class FileListView extends JList<File> {

	private File mCurrentPath = null;
	private Vector<File> mFiles;
	private Vector<File> mHistory;
	private int mHistoryIndex = -1;
	private FileListViewCallback mCallback;
	private JPopupMenu mRightPopMenu = null;
	private Boolean mOneClickMode = false;

	private void listInit()
	{
		mFiles = new Vector<>();
		mHistory = new Vector<>();

		setListData(mFiles);
		setFont(new Font("宋体", Font.PLAIN, 20));

		FileListRender listRender = new FileListRender();
		setCellRenderer(listRender);

		setClickMode(false);
		// one click mode by default.

	}

	private void doMainAction()
	{
		int index = getSelectedIndex();
		File f_sel = mFiles.get(index);
		mCallback.itemTriggered(f_sel);
	}

	private void showPopupMenu(Point pos)
	{
		if (mRightPopMenu != null)
		{
			mRightPopMenu.show(FileListView.this, pos.x, pos.y);
		}
	}

	private class DBClickMouseListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2)
			{
				doMainAction();
			}
			else if (e.getButton() == MouseEvent.BUTTON3)
			{
				int index = locationToIndex(e.getPoint());
				setSelectedIndex(index);
				showPopupMenu(e.getPoint());
			}
			else if (e.getButton() == 4)
			{
				mCallback.btn4Clicked();
			}
			else if (e.getButton() == 5)
			{
				mCallback.btn5Clicked();
			}
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

	private class OneClickMouseMotionListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e)
		{

		}

		@Override
		public void mouseMoved(MouseEvent e)
		{
			int index = locationToIndex(e.getPoint());
			setSelectedIndex(index);
		}
	}

	private class OneClickMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (e.getButton() == MouseEvent.BUTTON1)
			{
				doMainAction();
			}
			else if (e.getButton() == MouseEvent.BUTTON2)
			{
				showPopupMenu(e.getPoint());
			}
			else if (e.getButton() == 4)
			{
				mCallback.btn4Clicked();
			}
			else if (e.getButton() == 5)
			{
				mCallback.btn5Clicked();
			}
		}

		@Override
		public void mousePressed(MouseEvent e)
		{

		}

		@Override
		public void mouseReleased(MouseEvent e)
		{

		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
		}

		@Override
		public void mouseExited(MouseEvent e)
		{

		}
	}

	public void setClickMode(Boolean isDbClick)
	{
		for (MouseMotionListener e : getMouseMotionListeners())
		{
			removeMouseMotionListener(e);
		}
		for (MouseListener e : getMouseListeners())
		{
			removeMouseListener(e);
		}

		if (isDbClick)
		{
			addMouseListener(new DBClickMouseListener());
		}
		else
		{
			addMouseListener(new OneClickMouseListener());
			addMouseMotionListener(new OneClickMouseMotionListener());
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
		if (rootPath != null && rootPath.exists() && rootPath.isDirectory())
		{
			changeDir(rootPath);
		}
	}

	private boolean isFileHide(File f)
	{
		return f.isHidden();
	}

	private void refreshDirectory(@NotNull File path)
	{
		mFiles.clear();
		for (File e : Objects.requireNonNull(path.listFiles()))
		{
			if (isFileHide(e)) continue;
			mFiles.add(e);
		}
		updateUI();
	}

	public void changeDir(File path)
	{
		if (path == null) return;
		if (!path.exists()) return;
		if (!path.isDirectory()) return;

		refreshDirectory(path);

		if (mHistoryIndex >= 0)
		{
			mHistory.setSize(mHistoryIndex+1);
			mHistory.trimToSize();
		}
		else
		{
			mHistory.clear();
		}
		mCurrentPath = path;
		mHistory.add(mCurrentPath);
		mHistoryIndex = mHistory.size() - 1;    // index of last element
		System.out.println(mHistory);
		System.out.println(mHistoryIndex);
		mCallback.pathChanged(path);
	}

	public File getCurrentPath()
	{
		return mCurrentPath;
	}

	public void toBackward()
	{
		if (mHistoryIndex<=0) return;
		File path = mHistory.elementAt(--mHistoryIndex);
		mCurrentPath = path;
		refreshDirectory(path);
		mCallback.pathChanged(path);
	}

	public void toForward()
	{
		if (mHistoryIndex>=mHistory.size()-1) return;
		File path = mHistory.elementAt(++mHistoryIndex);
		mCurrentPath = path;
		refreshDirectory(path);
		mCallback.pathChanged(path);
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
