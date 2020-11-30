package FileTP_Front.GUI.DeviceList;

import FileTP_Front.Net.Device;

import javax.swing.*;
import java.awt.*;
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

	}


	public void addDevice(Device dev)
	{
		mDevices.add(dev);
		updateUI();
	}

	public void clearList()
	{
		mDevices.clear();
		updateUI();
	}

}
