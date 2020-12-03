package FileTP_Front.GUI.DeviceList;

import FileTP_Front.Net.Device;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class DeviceListView extends JList<Device> {

	private Vector<Device> mDevices;

	public DeviceListView()
	{
		mDevices = new Vector<>();
		setFont(new Font("宋体", Font.PLAIN, 20));
		DeviceListRender listRender = new DeviceListRender();
		setCellRenderer(listRender);
		this.setListData(mDevices);

		setDragEnabled(true);
		DropTarget dt = new DropTarget(this, DnDConstants.ACTION_NONE, new FileDropTargetAdapter());

		setDropTarget(dt);

	}

	private void sendFile(File f)
	{
		System.out.println(f.getPath());
	}

	private class FileDropTargetAdapter extends DropTargetAdapter {

		@Override
		public void drop(DropTargetDropEvent dtde)
		{

			dtde.acceptDrop(dtde.getDropAction());

			Transferable t = dtde.getTransferable();
			for (DataFlavor e : t.getTransferDataFlavors())
			{
				try
				{
					if (e.equals(DataFlavor.stringFlavor))
					{
						String name = (String)t.getTransferData(e);
						File f = new File(name);
						if (f.exists())
						{
							sendFile(f);
						}
					}
					else if (e.equals(DataFlavor.javaFileListFlavor))
					{
						List<File> fs = (List) t.getTransferData(e);

						for (File f : fs)
						{
							sendFile(f);
						}

					}
				}
				catch (UnsupportedFlavorException | IOException unsupportedFlavorException)
				{
					unsupportedFlavorException.printStackTrace();
				}
			}

		}
	}

	public void addDevice(Device dev)
	{
		mDevices.add(dev);
		updateUI();
	}

	public void delDevice(Device dev)
	{
		mDevices.remove(dev);
	}

	public void delDevice(int index)
	{
		mDevices.remove(index);
	}

	public void clearList()
	{
		mDevices.clear();
		updateUI();
	}

}
