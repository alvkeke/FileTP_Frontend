package FileTP_Front.Resource;

import javax.swing.*;
import java.net.URL;

public class ResLoader {

	public ImageIcon loadIcon(String name)
	{
		String prefix = "./";
		URL url = getClass().getResource(prefix + name);
		if (url == null) return null;
		return new ImageIcon(url);
	}


}
