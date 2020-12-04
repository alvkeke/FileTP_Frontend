package FileTP_Front.GUI.FileList;

import sun.awt.shell.ShellFolder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class FileListRender extends DefaultListCellRenderer {

	private ImageIcon getBigIcon(File f)
	{
		if (f!=null && f.exists())
		{
			try{
				ShellFolder sf = ShellFolder.getShellFolder(f);
				return new ImageIcon(sf.getIcon(true));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private Icon getSmallIcon(File f)
	{
		if (f!= null && f.exists())
		{
			FileSystemView fsv = FileSystemView.getFileSystemView();
			return fsv.getSystemIcon(f);
		}
		return null;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list,Object value,int index,boolean isSelected,boolean cellHasFocus) {

		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		File f = (File)value;

		ImageIcon ico = getBigIcon(f);
		setIcon(ico);
		setText(f.getName());
		setBorder(new EmptyBorder(5, 10, 5, 0));

		return this;
	}

}
