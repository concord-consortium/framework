package org.concord.framework.domain;


public final class DomainUtil
{
    public final static javax.swing.ImageIcon FRAME_ICON = new javax.swing.ImageIcon(java.awt.Toolkit.getDefaultToolkit().getImage(org.concord.framework.domain.DomainUtil.class.getResource("org/concord/framework/images/FrameIcon.gif")));

	protected static boolean theSameVM = false;

	public static void systemExit(){
		systemExit(false);
	}

	public static void systemExit(boolean forceSystemExit)
	{
		try
		{
			System.exit(0);
		}
		catch (SecurityException e)
		{
			if (forceSystemExit)
				System.exit(0);
		}
	}
	public static boolean isSingleVM()
	{
		return theSameVM;
	}
	
	public static void setSingleVM(boolean singleVM)
	{
		theSameVM = singleVM;
	}

}

