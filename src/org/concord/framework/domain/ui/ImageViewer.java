package org.concord.framework.domain.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

import javax.swing.JComponent;

import org.concord.framework.simulation.Simulation;
import org.concord.framework.simulation.SimulationListener;

public class ImageViewer extends JComponent implements Simulation
{

protected String origPath;
Image offImage = null;
int pixels[];
	public ImageViewer(){
		initialize("");
	}
	
	public ImageViewer(String str){
		initialize(str);
	}
	
	public ImageViewer(String str,boolean startThread){
		initialize(str,startThread);
	}
	
	
	ImageThread t = null;
	public void initialize(String str){
		initialize(str,true);
	}
	public void initialize(String str,boolean startThread){
		origPath = str;
		reloadImage(startThread);
	}
	GIFImage gifImage;
	public synchronized void reloadImage(){
		reloadImage(true);
	}
	public synchronized void reloadImage(boolean startThread){
		flushImage();
		try{

			URL testURL = new URL(origPath);
			InputStream stream = testURL.openStream();
			byte []buffer = getBufferFromInputStream(stream);
			gifImage = new GIFImage(buffer,this);
			pixels = new int[getImageWidth()*getImageHeight()];
		//	for(int i = 0 ; i < pixels.length; i++) pixels[i]=0xFF0000FF;
			repaint();
		}catch(Exception e ){
			System.out.println("reloadImage Exception "+e);
			gifImage = null;
		}
		if(startThread) t = new ImageThread(this);
		
	}


	public void setImageURL(String str){
		initialize(str);
	}

	public void setSize(Dimension d){
		super.setSize(d);
		newsize();
	}
	
	public void setSize(int w,int h){
		super.setSize(w,h);
		newsize();
	}
	
	protected void createEmptyImage(JComponent c){

		if(pixels == null) return;
		java.awt.image.MemoryImageSource ms = new java.awt.image.MemoryImageSource(getImageWidth(),getImageHeight(),pixels,0,getImageWidth());
		setBackgroundImage( c.getToolkit().createImage(ms));

//		if(offImage != null) offImage.flush();
//		offImage = c.createImage(gifImage.getWidth(),gifImage.getHeight());
/*
		ClearFilter cfilter = new ClearFilter();
		java.awt.image.ImageProducer ip = offImage.getSource();
		ip = new java.awt.image.FilteredImageSource(ip,cfilter);
		return getToolkit().createImage(ip);
*/
	}
	
	public void  setBackgroundImage(Image backgroundImage){
		if(gifImage != null) gifImage.setBackgroundImage(backgroundImage);
	}
	public Image  getBackgroundImage(){
		if(gifImage != null) return gifImage.getBackgroundImage();
		return null;
	}
	
	private void newsize(){
	}
	public synchronized void flushImage(){
		if(t != null){
			t.stopThread();
			try{
				t.join();
			}catch(Exception e){}
		}
		if(gifImage != null){
			gifImage.flush();
			gifImage = null;
		}
		if(offImage != null){
			offImage.flush();
			offImage = null;
		}
		
	}
	int currImage = 0;
	int cyclesDone = 0;
	int	previousDispMethod = ControlDescriptor.disposalUndefined;
	public synchronized void drawFirstImage(IndImage firstImage,int iw,int ih){
		if(iw < 1 || ih < 1) return;
		Graphics g = getGraphics();
		if(g != null){
			if(firstImage != null){
				if(offImage == null) offImage = createImage(iw,ih);
				java.awt.MediaTracker tracker = new java.awt.MediaTracker(this);
				tracker.addImage(firstImage.getJavaImage(),0);
				try{
					tracker.waitForAll();
				}catch(Exception e){
				}
				Graphics offg = offImage.getGraphics();
				if(offg != null){
					if(firstImage.getDisposalMethod() == ControlDescriptor.disposalRestoreToBackground){
						drawBackground(offg);
					}
					firstImage.drawImage(offg,this);
					offg.dispose();
					g.drawImage(offImage,0,0,this);
				}
			}
			g.dispose();
		}
	}
	
	public void drawBackground(Graphics g,JComponent c,int ix,int iy,int iw,int ih){
		if(g == null) return;
		if(gifImage != null && gifImage.getBackgroundImage() == null) createEmptyImage(c);
		Image img = (gifImage != null) ? gifImage.getBackgroundImage() : null;
		if(img != null) g.drawImage(img,ix,iy,c);
	}
	
	public void drawBackground(Graphics g){
		if(g == null) return;
		if(gifImage != null && gifImage.getBackgroundImage() == null) createEmptyImage(this);
		Image img = (gifImage != null) ? gifImage.getBackgroundImage() : null;
		if(img != null) g.drawImage(img,0,0,this);
	}

	public int getImageWidth(){
		if(gifImage == null) return getSize().width;
		return gifImage.getWidth();
	}
	public int getImageHeight(){
		if(gifImage == null) return getSize().height;
		return gifImage.getHeight();
	}

	public synchronized void externalPaintComponent(Graphics g,JComponent c,int ix,int iy,int iw,int ih){
		if(c == null){
			paintComponent(g);
			return;
		}
		if(g == null) return;

		int duration = getDuration();
		if(duration < 10) duration = 10;
		if(frameTime + duration <= System.currentTimeMillis()){
			nextStep();
			frameTime = System.currentTimeMillis();
		}
		Graphics offg = null;
		if(offImage == null && gifImage != null){
			offImage = c.createImage(gifImage.getWidth(),gifImage.getHeight());
			currImage = 0;
		}else if(offImage != null){
			offg = offImage.getGraphics();
		}
		if(gifImage != null && offImage != null){
			if(offg != null){
				IndImage indImage = gifImage.getNthIndImage(currImage);
				if(indImage != null){
					if(currImage == 0) previousDispMethod = indImage.getDisposalMethod();
					int dispMethod = indImage.getDisposalMethod();
					if(indImage.getDisposalMethod() == ControlDescriptor.disposalUndefined){
						dispMethod = previousDispMethod;
					}
					if(dispMethod == ControlDescriptor.disposalRestoreToBackground){
						offg.dispose();
						offg = null;
						offImage.flush();
						offImage = c.createImage(gifImage.getWidth(),gifImage.getHeight());
						createEmptyImage(c);
						if(offImage != null) offg = offImage.getGraphics();
						if(offg != null) drawBackground(offg,c,0,0,gifImage.getWidth(),gifImage.getHeight());
					}
					indImage.drawImage(offg,c);
					previousDispMethod = indImage.getDisposalMethod();
				}
				g.drawImage(offImage,ix,iy,iw,ih,c);
				if(offg != null) offg.dispose();
			}
		}
	}

	
	protected void clearImage(){
	    if(offImage == null) return;
		Graphics offg = offImage.getGraphics();
        if(offg != null){
            //offg.setColor(new Color(1,1,1,0));
            //offg.fillRect(0,0,offImage.getWidth(null),offImage.getHeight(null));
            offg.clearRect(0,0,offImage.getWidth(null),offImage.getHeight(null));
            offg.dispose();
        }
	}
	
	public synchronized void paintComponent(Graphics g){
		if(g == null) return;
		Graphics offg = null;
		if(offImage == null && gifImage != null){
			offImage = createImage(gifImage.getWidth(),gifImage.getHeight());
			currImage = 0;
		}else if(offImage != null){
			offg = offImage.getGraphics();
		}
		if(gifImage != null && offImage != null){
			if(offg != null){
				IndImage indImage = gifImage.getNthIndImage(currImage);
				if(indImage != null){
					if(currImage == 0) previousDispMethod = indImage.getDisposalMethod();
					int dispMethod = indImage.getDisposalMethod();
					if(indImage.getDisposalMethod() == ControlDescriptor.disposalUndefined){
						dispMethod = previousDispMethod;
					}
					if(dispMethod == ControlDescriptor.disposalRestoreToBackground){
						offg.dispose();
						offg = null;
						offImage.flush();
						offImage = createImage(gifImage.getWidth(),gifImage.getHeight());
						createEmptyImage(this);
						if(offImage != null) offg = offImage.getGraphics();
						if(offg != null) drawBackground(offg);
					}
					indImage.drawImage(offg,this);
					previousDispMethod = indImage.getDisposalMethod();
				}
				g.drawImage(offImage,0,0,this);
				if(offg != null) offg.dispose();
			}
		}
	}
	public synchronized int getDuration(){
		if(gifImage == null) return 0;
		IndImage indImage = gifImage.getNthIndImage(currImage);
		if(indImage == null) return 0;
		return indImage.getDuration();
	}

	public boolean	isInfiniteLoop(){
		if(gifImage == null) return false;
		return gifImage.isInfiniteLoop();
	}
	public void	setInfiniteLoop(){
		if(gifImage == null) return;
		gifImage.setInfiniteLoop();
	}
	
	public void	setNumbLoops(int n){
		if(gifImage == null) return;
		gifImage.setNumbLoops(n);
	}
	
    public Dimension getMinimumSize() {
    	int iw = 10;
    	int ih = 10;
 		if(iw < 10) iw = 10;    	
 		if(ih < 10) ih = 10;    	
      	return new Dimension(iw,ih);
    }
	
    public Dimension getPreferredSize() {
      	return getMinimumSize();
    }
    
    protected int imageState = SIM_UNDEF_STATE;
 	public synchronized void stop(){
 		imageState = SIM_STOP_STATE;
 	}
 	public synchronized void reset(){
 		stop();
 		cyclesDone = 0;
 		imageState = SIM_RESET_STATE;
 		clearImage();
		currImage = 0;
		repaint();
 	}
 	
 	public synchronized void gotoFrame(int n){
		if(gifImage == null || gifImage.getNumbIndImages() < 1) return;
		currImage = n;
		if(currImage < 0) currImage = 0;
		else if(currImage >= gifImage.getNumbIndImages()) currImage = gifImage.getNumbIndImages() - 1;
		repaint();		
 	}
 	
	public boolean isRunning(){return imageState == SIM_RUN_STATE;}
	public synchronized void nextStep(){
		if(imageState != SIM_RUN_STATE) return;
		if(gifImage == null) return;
		int nloops = gifImage.getNumbLoops();
		if(cyclesDone >= nloops && nloops > 0) return;
		currImage++;
		if(currImage >= gifImage.getNumbIndImages()){
			cyclesDone++;
			if(cyclesDone < nloops || nloops == 0){
			    currImage = 0;
			    clearImage();
			}else					currImage = gifImage.getNumbIndImages() - 1;
		}
		repaint();
	}
	public synchronized void continueSteps(){
 		imageState = SIM_RUN_STATE;
	}
	
	
    public int getAvailableSteps(){
		if(gifImage == null) return 0;
		return gifImage.getNumbIndImages();
	}
	
	public int getCurrentStepNumber(){
		if(gifImage == null) return 0;
		return currImage;
	}
	
	public void goToStep(int stepNumber){
        gotoFrame(stepNumber);
	}
	
	public void gotoPreviousStep(){
        int currFrame = getCurrentStepNumber();
        if(currFrame < 1) return;
        currFrame--;
        goToStep(currFrame);
	}

	
	protected long startTime;
	protected long frameTime;
	
	public void start(){
		startTime = System.currentTimeMillis();
		frameTime = startTime;
		continueSteps();
	}
 	public void doOneStep(){
 		start();
 		nextStep();
 		stop();
 	}
	public int getSimulationState(){
		return imageState;
	}
  
 	static public	byte[]  getBufferFromInputStream(InputStream is){
		byte []buffer = null;
		if(is == null) return buffer;
		try{
			int	initBufferLength = 64000;
			buffer = new byte[initBufferLength];
			boolean	exitFromLoop = false;
			int		offset = 0;
			while(!exitFromLoop){
				if(buffer.length <= offset){
					byte []newbuffer = new byte[buffer.length + initBufferLength];
					System.arraycopy(buffer,0,newbuffer,0,buffer.length);
					buffer = newbuffer;
				}
				int readed = is.read(buffer,offset,buffer.length - offset);
				exitFromLoop = (readed < 0);
				if(!exitFromLoop){
					offset+=readed;
				}
			}
			if(buffer.length > offset){
				byte []newbuffer = new byte[offset];
				System.arraycopy(buffer,0,newbuffer,0,offset);
				buffer = newbuffer;
			}
		}catch(Exception e){
			buffer = null;
		}
		return buffer;
	}

	/**
	 * Add a simulation listener 
	 * @param l	simulation listener to add
	 */
	public void addSimulationListener(SimulationListener l)
	{
		System.err.println("Simulation Listeners are not supported yet in "+this.getClass().getName());
	}

	/**
	 * Remove a simulation listener 
	 * @param l	simulation listener to remove
	 */
	public void removeSimulationListener(SimulationListener l)
	{
		System.err.println("Simulation Listeners are not supported yet in "+this.getClass().getName());
	}
}
class AnimGIF {
	Vector indImages = null;
	int	   numbLoops = 1;
	public AnimGIF(int numbLoops){
		this.numbLoops = numbLoops;
	}
	
	public void addIndImage(IndImage img){
		if(img == null) return;
		if(indImages == null) indImages = new Vector();
		indImages.addElement(img);
	}
	
	public int getNumbLoops(){return numbLoops;}
	public void setNumbLoops(int n){
		numbLoops = n;
		if(numbLoops < 0) numbLoops = 0;
	}
	public int getNumbIndImages(){return (indImages == null)?0:indImages.size();}
	public IndImage getNthIndImage(int n){
		if(n < 0 || n >= getNumbIndImages()) return null;
		return (IndImage)indImages.elementAt(n);
	}
	public void flush(){
		if(indImages == null) return;
		for(int i = 0; i < indImages.size(); i++){
			IndImage img = (IndImage)indImages.elementAt(i);
			img.flush();
		}
		indImages.removeAllElements();
		indImages = null;
	}
}

class IndImage {
	byte []image 		= null;
	int left 			= 0;
	int top 			= 0;
	int duration 		= 0;
	int disposalMethod 	= 0;
	int	index = 0;
	Image javaImage = null;
	public IndImage(byte []img,int left,int top,int duration,int disposalMethod,int index){
		image = img;
		this.left = left;
		this.top = top;
		this.duration = duration;
		this.disposalMethod = disposalMethod;
		this.index = index;
		javaImage = java.awt.Toolkit.getDefaultToolkit().createImage(image);
	}
	public void flush(){
		image = null;
		if(javaImage != null){
			javaImage.flush();
			javaImage = null;
		}
	}
	public void drawImage(Graphics g,JComponent c){
		try{
			if(javaImage == null && image != null){
				javaImage = c.getToolkit().createImage(image);
				java.awt.MediaTracker tracker = new java.awt.MediaTracker(c);
				tracker.addImage(javaImage,0);
				tracker.waitForAll();
			}
			g.drawImage(javaImage,left,top,c);
		}catch(Exception e){
			System.out.println("IndImage["+index+"] Exception drawImage "+e);
			e.printStackTrace();
		}
	}

	public byte[]	getImage(){return image;}
	public int 		getLeft(){return left;}
	public int 		getTop(){return top;}
	public int 		getDuration(){return duration;}
	public int 		getDisposalMethod(){return disposalMethod;}
	
	public Image 	getJavaImage(){return javaImage;}
	
	public String toString(){
		String str = "IndImage "+index+"\n";
		str += "     ("+getLeft()+","+getTop()+")\n";
		str += "    Duration "+getDuration()+"\n";
		switch(getDisposalMethod()){
			case ControlDescriptor.disposalUndefined:
			default:
				str += ("    Disposal Undefined\n");
				break;
			case ControlDescriptor.disposalNotDispose:
				str += ("    Disposal Not Dispose\n");
				break;
			case ControlDescriptor.disposalRestoreToBackground:
				str += ("    Disposal Restore To Background\n");
				break;
			case ControlDescriptor.disposalRestoreToPrevious:
				str += ("    Disposal Restore To Previous\n");
				break;
		}
		return str;
	}
	
}

class GIFBlock {
	public final static byte	extensionIntroducer		=		(byte)0x21;
	public final static byte	blockTerminator			=		(byte)0x00;
	public final static byte	trailer					=		(byte)0x3B;

	public final static byte	extensionApplication		=	(byte)0xFF;
	public final static byte	extensionComment			=	(byte)0xFE;
	public final static byte	extensionControl			=	(byte)0xF9;
	public final static byte	extensionImageDescriptor	=	(byte)0x2C;
	public final static byte	extensionText				=	(byte)0x01;

	byte content[] = null;
	int	  offset = 0;
	ControlDescriptor controlBlock = null;
	public GIFBlock() {
	}
	
	public GIFBlock(byte []buffer,int startBytes) {
		offset = 0;
	}
	public void flush(){
		content = null;
		if(controlBlock != null){
			controlBlock.flush();
			controlBlock = null;
		}
	}
	public int getOffset(){return offset;}
	public byte []getContent(){return content;}
	
	public void setControlBlock(ControlDescriptor block){controlBlock = block;}
	public ControlDescriptor getControlBlock(){return controlBlock;}
	
	public void	skipBlock(byte []buffer,int startBytes){
		int currByte = startBytes;
		int blockSize = buffer[currByte++];
		blockSize &= 0xFF;
		currByte += blockSize;
		offset += (1 + blockSize);
	}
	
	public static int getIntFrom2Bytes(byte []buffer,int startBytes){
		int retValue = 0;
		retValue = buffer[startBytes + 1];
		retValue &= 0xFF;
		retValue <<= 8;
		retValue &= 0xFF00;
		int temp = buffer[startBytes];
		temp &= 0xFF;
		retValue += temp;
		return retValue;
	}
	
	public void checkForBlockTerminator(byte []buffer,int startBytes) 
	throws Exception
	{
		if(buffer[startBytes] != blockTerminator){
			throw new Exception("not terminated block");
		}
		offset++;
	}
}


class ControlDescriptor extends GIFBlock{
	public final static byte disposalUndefined 				= 0;
	public final static byte disposalNotDispose 			= 1;
	public final static byte disposalRestoreToBackground 	= 2;
	public final static byte disposalRestoreToPrevious 		= 3;
	byte	packed;
	int		duration = 0;
	int		transparentIndex = 0;
	int		disposalMethod = 0;
	boolean	transparentFlag = false;
	public ControlDescriptor(byte []buffer,int startBytes) throws Exception {
		super(buffer,startBytes);
		int	firstByte = startBytes - 2; //introducer + label
		int currByte = startBytes + getOffset();
		skipBlock(buffer,currByte);
		if(buffer[currByte] == 4){
			packed = buffer[currByte+1];
			duration = getIntFrom2Bytes(buffer,currByte + 2);
			duration *= 10; //msec
			transparentIndex = buffer[currByte + 4];
			transparentIndex &= 0xFF;

			disposalMethod = packed >> 2;
			disposalMethod = disposalMethod & 0x7;
			
			transparentFlag = ((packed & 0x01) != 0); 
		} 
		currByte = startBytes + getOffset();
		checkForBlockTerminator(buffer,currByte);
		currByte = startBytes + getOffset();
		int lengthContent = currByte - firstByte;
		content = new byte[lengthContent];
		System.arraycopy(buffer,firstByte,content,0,lengthContent);
	}
	
	
	public byte getPacked(){return packed;}
	public int  getDuration(){return duration;}
	public int  getTransparentIndex(){return transparentIndex;}
	public int  getDisposalMethod(){return disposalMethod;}
	public boolean  getTransparentFlag(){return transparentFlag;}
	
	
	public String toString(){
		String str = "Control Block\n";
		str += ("    Duration "+getDuration()+"msec\n");
		switch(getDisposalMethod()){
			case disposalUndefined:
			default:
				str += ("    Disposal Undefined\n");
				break;
			case disposalNotDispose:
				str += ("    Disposal Not Dispose\n");
				break;
			case disposalRestoreToBackground:
				str += ("    Disposal Restore To Background\n");
				break;
			case disposalRestoreToPrevious:
				str += ("    Disposal Restore To Previous\n");
				break;
		}
		str += ("    Transparent flag "+getTransparentFlag()+"\n");
		str += ("    Transparent Index "+getTransparentIndex()+"\n");
		return str;
	}
}

class ApplicationDescriptor extends GIFBlock{
	int	repetition = 1;
	public ApplicationDescriptor(byte []buffer,int startBytes) 	throws Exception {
		super(buffer,startBytes);
		int currByte = startBytes + getOffset();
		skipBlock(buffer,currByte);
		currByte = startBytes + getOffset();
		skipBlock(buffer,currByte);
		if(buffer[currByte] == 3 && buffer[currByte+1] == 1){
			repetition = getIntFrom2Bytes(buffer,currByte + 2);
		}
		currByte = startBytes + getOffset();
		checkForBlockTerminator(buffer,currByte);
	}
	public int getRepetition(){return repetition;}
}

class CommentDescriptor extends GIFBlock{
	public CommentDescriptor(byte []buffer,int startBytes)  throws Exception {
		super(buffer,startBytes);
		int currByte = startBytes + getOffset();
		skipBlock(buffer,currByte);
		currByte = startBytes + getOffset();
		checkForBlockTerminator(buffer,currByte);
	}
}

class TextDescriptor extends GIFBlock{
	ControlDescriptor controlDescriptor = null;
	public TextDescriptor(byte []buffer,int startBytes) {
		super(buffer,startBytes);
	}
}

class ImageDescriptor extends GIFBlock{
	int		top;
	int		left;
	int		width;
	int		height;
	byte	packed;
	boolean	isColorTableExist = false;
	boolean	interlaceFlag = false;
	boolean	sortFlag = false;
	byte	sizeOfColorTable = 0;
	int		sizeInBytesColorTable;
	public ImageDescriptor(byte []buffer,int startBytes)  throws Exception {
		super(buffer,startBytes);
		int leftByte = -1;
		int topByte = -1;
		int	firstByte = startBytes - 1; //label
		int currByte = startBytes + getOffset();
		left = getIntFrom2Bytes(buffer,currByte);
		leftByte= currByte - firstByte;
		currByte += 2;
		top = getIntFrom2Bytes(buffer,currByte);
		topByte= currByte - firstByte;
		currByte += 2;
		width = getIntFrom2Bytes(buffer,currByte);
		currByte += 2;
		height = getIntFrom2Bytes(buffer,currByte);
		currByte += 2;
		packed = buffer[currByte++];
		isColorTableExist = ((packed & 0x80) != 0);
		interlaceFlag = ((packed & 0x40) != 0);
		sortFlag = ((packed & 0x20) != 0);
		if(isColorTableExist){
			sizeOfColorTable = (byte)(packed & 0x7);
			sizeInBytesColorTable = 3 * (1 << (sizeOfColorTable+1));
		}else{
			sizeOfColorTable = 0;
			sizeInBytesColorTable = 0;
		}
		currByte += sizeInBytesColorTable; //skip color table;
		currByte++; //skip LZW min code size
		offset = currByte - startBytes;
		while(buffer[currByte] != 0){
			skipBlock(buffer,currByte);
			currByte = startBytes + getOffset();
		}
		offset++;
		currByte++;
		int lengthContent = currByte - firstByte;
		content = new byte[lengthContent];
		System.arraycopy(buffer,firstByte,content,0,lengthContent);
		if(leftByte >= 0) content[leftByte] = content[leftByte+1] = 0;
		if(topByte >= 0) content[topByte] = content[topByte+1] = 0;
	}
	
	public int getDuration(){
		if(controlBlock == null) return 0;
		return controlBlock.getDuration();
	}
	public int getDisposalMethod(){
		if(controlBlock == null) return ControlDescriptor.disposalUndefined;
		return controlBlock.getDisposalMethod();
	}
	
	int		getWidth(){return width;}
	int		getHeight(){return height;}
	int		getTop(){return top;}
	int		getLeft(){return left;}
	byte	getPacked(){return packed;} 
	boolean	getIsColorTableExist(){return isColorTableExist;} 
	boolean	getInterlaceFlag(){return interlaceFlag;} 
	boolean	getSortFlag(){return sortFlag;} 
	byte	getSizeOfColorTable(){return sizeOfColorTable;} 
	int		getSizeInBytesColorTable(){return sizeInBytesColorTable;}
	
	
	public String toString(){
		String str = "IMAGE ";
		str += ("("+getLeft()+","+getTop()+");");
		str += (getWidth()+"x"+getHeight()+";");
		str += ("color table exist "+getIsColorTableExist()+";");
		str += ("interlace "+getInterlaceFlag()+";");
		str += ("sort "+getSortFlag()+";");
		str += ("color table size "+getSizeInBytesColorTable()+";");
		if(getControlBlock() != null){
			str +=("\n"+getControlBlock().toString());
		}
		return str;
	}
}

class GIFImage {
	GIFHeader			header;
	GIFScreenDescriptor	globalScreenDescriptor;
	byte				globalColorTable[] = null;
	int				numbLoops = 1;
	Vector				images = new Vector();
	ImageViewer		owner;
	
	Image				backgroundImage;
	
	public GIFImage(byte []buffer,ImageViewer owner) {
		this.owner = owner;
		header = new GIFHeader(buffer,0);
		int nextOffset = header.getOffset();
		globalScreenDescriptor = new GIFScreenDescriptor(buffer,nextOffset);
		nextOffset += globalScreenDescriptor.getOffset();
		int colorTableSize = globalScreenDescriptor.getSizeInBytesColorTable();
		if(colorTableSize > 0){
			globalColorTable = new byte[colorTableSize];
			System.arraycopy(buffer,nextOffset,globalColorTable,0,colorTableSize);
			nextOffset += colorTableSize;

/*
			char chars[] = new char[2];
			for(int i = 0; i < colorTableSize; i++){
				int b = (int)globalColorTable[i] & 0xFF;
				chars[0] = (char)getHexaChar((byte)(b / 16));
				chars[1]= (char)getHexaChar((byte)(b % 16));
				String  str = new String(chars);
				System.out.println(str);
			}
*/
			boolean wasTrailer = false;
			ControlDescriptor lastControlDescriptor = null;
			boolean	firstApplicationBlock = true;
			while(!wasTrailer){
				byte  descriptor = buffer[nextOffset++];
				GIFBlock desc;
				if(descriptor == GIFBlock.extensionIntroducer){
					descriptor = buffer[nextOffset++];
				}
				try{
					switch(descriptor){
						default:
							break;
						case GIFBlock.trailer:
							wasTrailer = true;
							break;
						case GIFBlock.extensionApplication:
							desc = new ApplicationDescriptor(buffer,nextOffset);
							nextOffset += desc.getOffset();
							if(firstApplicationBlock){
								firstApplicationBlock = false;
								numbLoops = ((ApplicationDescriptor)desc).getRepetition();
							}
							break;
						case GIFBlock.extensionImageDescriptor:
							desc = new ImageDescriptor(buffer,nextOffset);
							nextOffset += desc.getOffset();
							if(lastControlDescriptor != null){
								desc.setControlBlock(lastControlDescriptor);
								lastControlDescriptor = null;
							}
							images.addElement(desc);
							break;
						case GIFBlock.extensionText:
							desc = new TextDescriptor(buffer,nextOffset);
							nextOffset += desc.getOffset();
							if(lastControlDescriptor != null){
								desc.setControlBlock(lastControlDescriptor);
								lastControlDescriptor = null;
							}
							break;
						case GIFBlock.extensionComment:
							desc = new CommentDescriptor(buffer,nextOffset);
							nextOffset += desc.getOffset();
							break;
						case GIFBlock.extensionControl:
							desc = new ControlDescriptor(buffer,nextOffset);
							nextOffset += desc.getOffset();
							lastControlDescriptor = (ControlDescriptor)desc;
							break;
					}
				}catch(Exception e){
					break;
				}
			}
		}
		animGIF = new AnimGIF(getNumbLoops());

		if(images != null){
			for(int i = 0; i < images.size(); i++){
				ImageDescriptor image = (ImageDescriptor)images.elementAt(i);
				byte []newImageByte = createIndImage(this,image);
				IndImage indImage = new IndImage(newImageByte,image.getLeft(),image.getTop(),image.getDuration(),image.getDisposalMethod(),i+1);
				animGIF.addIndImage(indImage);
				if(i == 0){
					if(owner != null) owner.drawFirstImage(indImage,getWidth(),getHeight());
				}
			}
		}
		
	}
	AnimGIF	animGIF;
	
	public void  setBackgroundImage(Image backgroundImage){
		this.backgroundImage = backgroundImage;
	}
	public Image getBackgroundImage(){return backgroundImage;}
	
	public int getNumbIndImages(){return (animGIF == null)?0:animGIF.getNumbIndImages();}
	public IndImage getNthIndImage(int n){
		if(animGIF == null) return null;
		return animGIF.getNthIndImage(n);
	}

	public Vector 				getImages(){return images;}
	public GIFHeader 			getHeader(){return header;}
	public GIFScreenDescriptor 	getGlobalScreenDescriptor(){return globalScreenDescriptor;}
	public byte[] 				getGlobalColorTable(){return globalColorTable;}
	public int					getNumbLoops(){return numbLoops;}
	public int					getWidth(){return (globalScreenDescriptor == null)?0:globalScreenDescriptor.getWidth();}
	public int					getHeight(){return (globalScreenDescriptor == null)?0:globalScreenDescriptor.getHeight();}
	
	public boolean				isInfiniteLoop(){return (numbLoops == 0);}
	
	public void					setNumbLoops(int n){
		numbLoops = n;
		if(numbLoops < 0) numbLoops = 0;
		if(animGIF != null) animGIF.setNumbLoops(numbLoops);
	}
	
	public void					setInfiniteLoop(){
		setNumbLoops(0);
	}
	
	public void flush(){
		globalColorTable = null;
		if(images == null) return;
		for(int i = 0; i < images.size(); i++){
			Object o = images.elementAt(i);
			if(o instanceof GIFBlock){
				((GIFBlock)o).flush();
			}
		}
		images.removeAllElements();
		if(animGIF != null){
			animGIF.flush();
			animGIF = null;
		}
	}
	
	public static byte getHexaChar(byte b){
		if(b>=0 && b <= 9){
			return (byte)('0' + b);
		}else if(b >= 10 && b <= 15){
			return (byte)('A' + (b-10));
		}
		return (byte)'0';
	}
	
	static public byte []createIndImage(GIFImage mainImage,ImageDescriptor image){
		if(mainImage == null || image == null) return null;
		byte []retBytes = null;
		int bytesSize = 6; //header;
		bytesSize += 7; //descriptor;
		GIFScreenDescriptor globaldescriptor = mainImage.getGlobalScreenDescriptor();
		byte [] globalColorTable = mainImage.getGlobalColorTable();
		int colorTableSize = (globalColorTable != null)?globalColorTable.length:0;
		bytesSize += colorTableSize;
		ControlDescriptor imageControlBlock = image.getControlBlock();
		byte []controlBlockContent = null;
		byte []imageContent = image.getContent();
		if(imageControlBlock != null){
			controlBlockContent = imageControlBlock.getContent();
		}
		if(controlBlockContent != null) 	bytesSize += controlBlockContent.length;
		if(imageContent != null) 		bytesSize += imageContent.length;
		bytesSize++; //trailer
		retBytes = new byte[bytesSize];
		int currByte = 0;
		GIFHeader header = mainImage.getHeader();
		System.arraycopy(header.getSignature(),0,retBytes,currByte,3);
		currByte += 3;
		System.arraycopy(header.getVersion(),0,retBytes,currByte,3);
		currByte += 3;
		retBytes[currByte++] = imageContent[5];
		retBytes[currByte++] = imageContent[6];
		retBytes[currByte++] = imageContent[7];
		retBytes[currByte++] = imageContent[8];
		retBytes[currByte++] = globaldescriptor.getPacked();
		retBytes[currByte++] = globaldescriptor.getColorIndexByte();
		retBytes[currByte++] = globaldescriptor.getAspectRatio();
		if(colorTableSize > 0){
			System.arraycopy(globalColorTable,0,retBytes,currByte,globalColorTable.length);
			currByte += globalColorTable.length;
		}
		if(controlBlockContent != null){
			System.arraycopy(controlBlockContent,0,retBytes,currByte,controlBlockContent.length);
			currByte += controlBlockContent.length;
		}
		if(controlBlockContent != null){
			System.arraycopy(imageContent,0,retBytes,currByte,imageContent.length);
			currByte += imageContent.length;
		}
		retBytes[currByte] = GIFBlock.trailer;
		return retBytes;
	}
}

class GIFHeader{
	private final int offset = 6;
	private byte	signature[];
	private byte	version[];
	public  final int	versUnknown	=	0;
	public  final int	vers89		=	89;
	public  final int	vers87		=	87;
	GIFHeader(byte []buffer,int offs){
		signature 	= new byte[3];
		version 	= new byte[3];
		try{
			for(int i = 0; i < 3; i++) signature[i] = buffer[offs + i];
			for(int i = 0; i < 3; i++) version[i] 	= buffer[offs + i+3];
		}catch(Exception e){
		}
	}
	int	getOffset(){return offset;}
	byte []getSignature(){return signature;}
	byte []getVersion(){return version;}
	boolean isCorrectSignature(){
		return ((signature[0] == 'G') &&  (signature[1] == 'I') && (signature[2] == 'F'));
	}
	int getGIFVersion(){
		int retValue = versUnknown;
		if(version[0] == '8' && (version[1] == '9'))		retValue = vers89;
		else if(version[0] == '8' && (version[1] == '7'))	retValue = vers87;
		return retValue;
	}
	public String toString(){
		String str = "GIF Header: ";
		str += (new String(getSignature())) + ";";
		str += (new String(getVersion())) + ";";
		str += getGIFVersion();
		return str;
	}
}

class GIFScreenDescriptor{
	private final int offset = 7;
	int		width;
	int 	height;
	byte 	packed;
	int 	colorIndex;
	byte 	colorIndexByte;
	byte 	aspectRatio;

	boolean	isColorTableExist = false;
	byte	colorResolution = 0;
	boolean	sortFlag = false;
	byte	sizeOfColorTable = 0;
	int		sizeInBytesColorTable;

	GIFScreenDescriptor(byte []buffer,int offs){
		int 	currOffset = offs;
		width = GIFBlock.getIntFrom2Bytes(buffer,currOffset);
		currOffset += 2;
		height = GIFBlock.getIntFrom2Bytes(buffer,currOffset);
		currOffset += 2;
		packed 		= buffer[currOffset++];
		colorIndexByte = buffer[currOffset++];
		colorIndex 	= colorIndexByte;
		colorIndex &= 0xFF;
		aspectRatio = buffer[currOffset++];
		
		isColorTableExist = ((packed & 0x80) != 0);
		
		colorResolution = (byte)(packed & 0x70);
		colorResolution >>= 4;
		colorResolution &= 0x07;
		sortFlag = ((packed & 0x8) != 0);
		sizeOfColorTable = (byte)(packed & 0x7);
		if(isColorTableExist){
			sizeInBytesColorTable = 3 * (1 << (sizeOfColorTable+1));
		}else{
			sizeInBytesColorTable = 0;
		}
		
	}
	int	getOffset(){return offset;}
	
	int		getWidth(){return width;}
	int		getHeight(){return height;}
	byte	getPacked(){return packed;} 
	int		getColorIndex(){return colorIndex;} 
	byte		getColorIndexByte(){return colorIndexByte;} 
	byte	getAspectRatio(){return aspectRatio;} 

	boolean	getIsColorTableExist(){return isColorTableExist;} 
	boolean	getSortFlag(){return sortFlag;} 
	byte	getColorResolution(){return colorResolution;} 
	byte	getSizeOfColorTable(){return sizeOfColorTable;} 
	int		getSizeInBytesColorTable(){return sizeInBytesColorTable;}

	public String toString(){
		String str = "GIFScreenDescriptor: ";
		str += (getWidth() +"x" +  getHeight()) + ";";
		str += ("color table "+getIsColorTableExist()) + ";";
		str += ("size color table "+getSizeOfColorTable()) + ";";
		str += ("sorted "+getSortFlag()) + ";";
		str += ("resolution "+getColorResolution()) + ";";
		str += ("ratio "+getAspectRatio()) + ";";
		str += ("backindex "+getColorIndex()) + ";";
		str += ("sizeInBytesColorTable "+getSizeInBytesColorTable()) + ";";
		return str;
	}

}

class ImageThread extends Thread{
ImageViewer owner;
int	duration = 100;
	public ImageThread(ImageViewer owner){
		super();
		this.owner = owner;
		start();
	}
	public void run(){
		while(owner != null){
			try{
				duration = owner.getDuration();
				owner.nextStep();
				if(duration < 10) duration = 10;
				sleep(duration);
			}catch(Exception e){}
		}
	}
	public synchronized void stopThread(){
		owner = null;
	} 
}


class ClearFilter extends java.awt.image.ImageFilter{
    public synchronized void setPixels(  int x, int y, int w, int h,
			  				java.awt.image.ColorModel model, byte pixels[], int off,
			  				int scansize) {
		if(model instanceof java.awt.image.IndexColorModel){//ImageConsumer.java
			int tp = ((java.awt.image.IndexColorModel)model).getTransparentPixel();
			if(tp >= 0){
				for(int i = x; i < x + w; i++){
					for(int j = y; j < y + h; j++){
						pixels[off + (j - y) * scansize + (i - x)] = (byte)tp;
					}
				}
			}
		}
		super.setPixels(x, y, w, h, model, pixels, off, scansize);
	}
    public synchronized void setPixels(	int x, int y, int w, int h,
			  				java.awt.image.ColorModel model, int pixels[], int off,
			  				int scansize) {
		if(model instanceof java.awt.image.DirectColorModel){
			for(int i = x; i < x + w; i++){
				for(int j = y; j < y + h; j++){
					pixels[off + (j - y) * scansize + (i - x)] = 0;
				}
			}
		}
		super.setPixels(x, y, w, h, model, pixels, off, scansize);
    }
}
