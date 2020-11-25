package GUI.FileList;

import sun.awt.shell.ShellFolder;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class ListRender extends DefaultListCellRenderer {

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

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list,Object value,int index,boolean isSelected,boolean cellHasFocus) {

		File f = (File)value;
		ImageIcon ico = getBigIcon(f);
		setIcon(ico);
		setText(f.getName());

		setBackground(Color.WHITE);
		if (isSelected)
		{
			setBackground(Color.GRAY);
		}

		return this;
	}

}
