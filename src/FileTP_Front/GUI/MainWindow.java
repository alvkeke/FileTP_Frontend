package FileTP_Front.GUI;

import FileTP_Front.GUI.DeviceList.DeviceListView;
import FileTP_Front.GUI.FileList.FileListView;
import FileTP_Front.GUI.FileList.FileListViewCallback;
import FileTP_Front.Net.Device;
import FileTP_Front.Resource.ResLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class MainWindow extends JFrame implements FileListViewCallback {

	JSplitPane mClientPanel;
	FileListView mFileList;
	JScrollPane mSpFileList;
	DeviceListView mDeviceList;
	JScrollPane mSpDeviceList;

	private final int mPadding = 5;

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

		mClientPanel.setDividerLocation(150);

		mClientPanel.setLeftComponent(mSpDeviceList);
		mClientPanel.setRightComponent(mSpFileList);

		try {
			Inet4Address address = (Inet4Address) Inet4Address.getByName("127.0.0.1");
			mDeviceList.addDevice(new Device(address, Device.PLATFORM_WINDOW));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

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
