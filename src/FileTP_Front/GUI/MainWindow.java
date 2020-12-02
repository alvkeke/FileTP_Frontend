package FileTP_Front.GUI;

import FileTP_Front.GUI.DeviceList.DeviceListView;
import FileTP_Front.GUI.FileList.FileListView;
import FileTP_Front.GUI.FileList.FileListViewCallback;
import FileTP_Front.Net.Device;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class MainWindow extends JFrame implements FileListViewCallback {

	// Split panel
	JSplitPane mSplitPane;

	// left side, device list
	JPanel mLeftPanel;
	JScrollPane mSpDeviceList;
	DeviceListView mDeviceList;

	// right side, file list and navigation bar
	JPanel mRightPanel;			/* Main Container of right side */
	JPanel mHeadPanel;			/* Navigation bar Container */
	JPanel mNavBtnBar;			/* Nav-button container */
	JButton mBtnBackward;		/* Nav-button backward */
	JButton mBtnForward;		/* Nav-button forward */
	JButton mBtnToParent;		/* Nav-button parent */
	JTextField mTxtPath;		/* Navigation Text Field: path */
	JButton mBtnGoto;			/* Nav-button goto */
	JScrollPane mSpFileList;	/* File List Scroll View */
	FileListView mFileList;		/* File List View */

	public MainWindow()
	{
		setSize(800, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Main Container
		mSplitPane = new JSplitPane();
		this.add(mSplitPane);

		mSplitPane.setDividerLocation(200);

		// Left side
		mLeftPanel = new JPanel(new BorderLayout());
		mDeviceList = new DeviceListView();
		mSpDeviceList = new JScrollPane(mDeviceList);
		mLeftPanel.add(mSpDeviceList);

		mSplitPane.setLeftComponent(mLeftPanel);

		// Right side
		mRightPanel = new JPanel(new BorderLayout());
		// address path and navigation buttons
		mHeadPanel = new JPanel(new BorderLayout());
		mNavBtnBar = new JPanel(new GridLayout());
		mBtnBackward = new JButton("←");
		mBtnForward = new JButton("→");
		mBtnToParent = new JButton("↑");
		mBtnGoto = new JButton("=>");
		mTxtPath = new JTextField();
		// file list
		mFileList = new FileListView(this, "C:/Users/alvis/desktop/");
		// file list scroll pane
		mSpFileList = new JScrollPane(mFileList);

		mRightPanel.add(mSpFileList);
		mRightPanel.add(mHeadPanel, BorderLayout.NORTH);
		mHeadPanel.add(mNavBtnBar, BorderLayout.WEST);
		mNavBtnBar.add(mBtnBackward);
		mNavBtnBar.add(mBtnToParent);
		mNavBtnBar.add(mBtnForward);
		mHeadPanel.add(mTxtPath);
		mHeadPanel.add(mBtnGoto, BorderLayout.EAST);
		mSplitPane.setRightComponent(mRightPanel);

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

		mBtnBackward.addActionListener(e -> mFileList.toBackward());
		mBtnForward.addActionListener(e -> mFileList.toForward());
		mBtnToParent.addActionListener(e -> mFileList.changeDir(mFileList.getCurrentPath().getParentFile()));

		mBtnGoto.addActionListener(e -> {
			File f = new File(mTxtPath.getText());
			if (!f.exists()) return;
			if (f.isDirectory())
			{
				mFileList.changeDir(f);
			}
			else
			{
				mFileList.changeDir(f.getParentFile());
			}

		});

		this.setVisible(true);
	}

	@Override
	public void itemTriggered(File f) {
		mFileList.changeDir(f);
	}

	@Override
	public void pathChanged(File path) {
		if (path == null) return;
		if (mTxtPath == null) return;
		mTxtPath.setText(path.getAbsolutePath());
	}
}
