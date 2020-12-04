package FileTP_Front.GUI.DeviceList;

import FileTP_Front.Net.Device;
import FileTP_Front.Resource.ResLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DeviceListRender extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		Device dev = (Device)value;

		String name = dev.getName();
		if (name == null)
		{
			name = dev.getAddress().toString();
		}
		setText(name);

		ResLoader loader = new ResLoader();

		ImageIcon ico = null;
		switch (dev.getPlatform())
		{
			case Device.PLATFORM_WINDOW:
				ico = loader.loadIcon("windows.png");
				break;
			case Device.PLATFORM_ANDROID:
				ico = loader.loadIcon("android.png");
				break;
			case Device.PLATFORM_LINUX:
				ico = loader.loadIcon("linux.png");
				break;
			case Device.PLATFORM_UBUNTU:
				ico = loader.loadIcon("ubuntu.jpg");
				break;
			case Device.PLATFORM_MANJARO:
				ico = loader.loadIcon("manjaro.png");
				break;
			case Device.PLATFORM_DEBIAN:
				ico = loader.loadIcon("debian.jpg");
				break;
			case Device.PLATFORM_DEEPIN:
				ico = loader.loadIcon("deepin.png");
				break;
			case Device.PLATFORM_APPLE:
				ico = loader.loadIcon("apple.png");
				break;
			default:
				ico = loader.loadIcon("none.jpg");
				break;
		}

		if (ico != null)
		{
			ico.setImage(ico.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
			setIcon(ico);
		}

		setBorder(new EmptyBorder(5, 10, 5, 0));

		return this;
	}
}
