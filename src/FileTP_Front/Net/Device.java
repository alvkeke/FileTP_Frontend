package FileTP_Front.Net;

import java.net.Inet4Address;
import java.net.URL;

public class Device {

	public static final int PLATFORM_WINDOW = 1;
	public static final int PLATFORM_ANDROID = 2;
	public static final int PLATFORM_LINUX = 3;
	public static final int PLATFORM_UBUNTU = 4;
	public static final int PLATFORM_MANJARO = 5;
	public static final int PLATFORM_DEBIAN = 6;
	public static final int PLATFORM_DEEPIN = 7;
	public static final int PLATFORM_APPLE = 8;


	private final Inet4Address mAddress;
	private final int mPlatform;
	private String mName = null;

	public Device(Inet4Address address, int platform)
	{
		this.mAddress = address;
		this.mPlatform = platform;
	}

	public Inet4Address getAddress()
	{
		return mAddress;
	}

	public int getPlatform()
	{
		return mPlatform;
	}

	public String getName()
	{
		return mName;
	}

	public void setName(String name)
	{
		this.mName = name;
	}
}
