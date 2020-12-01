package FileTP_Front.GUI;

import FileTP_Front.GUI.DeviceList.DeviceListView;
import FileTP_Front.GUI.FileList.FileListView;
import FileTP_Front.GUI.FileList.FileListViewCallback;
import FileTP_Front.Net.Device;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class MainWindow extends JFrame implements FileListViewCallback {

	JSplitPane mClientPanel;
	FileListView mFileList;
	JScrollPane mSpFileList;
	DeviceListView mDeviceList;
	JScrollPane mSpDeviceList;

	public MainWindow()
	{
		setSize(800, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mClientPanel = new JSplitPane();
		this.add(mClientPanel);

		mFileList = new FileListView(this, "C:/Users/alvis/desktop/");
		mSpFileList = new JScrollPane(mFileList);

		mDeviceList = new DeviceListView();
		mSpDeviceList = new JScrollPane(mDeviceList);

		mClientPanel.setDividerLocation(200);

		mClientPanel.setLeftComponent(mSpDeviceList);
		mClientPanel.setRightComponent(mSpFileList);

		for (int i = 5; i<15; i++)
		{
			try
			{
				String path = "192.168.1." + i;
				Inet4Address address = (Inet4Address) Inet4Address.getByName(path);
				mDeviceList.addDevice(new Device(address, Device.PLATFORM_WINDOW + i - 5));
			}
			catch (UnknownHostException e)
			{
				e.printStackTrace();
			}
		}

		this.setVisible(true);
	}

	@Override
	public void itemTriggered(File f) {
		mFileList.changeDir(f);
	}

	@Override
	public void pathChanged(File path) {

	}
}
