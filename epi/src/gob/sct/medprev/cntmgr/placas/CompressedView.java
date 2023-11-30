/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.sct.medprev.cntmgr.placas;

/**
 *
 * @author Ivan Santiago Méndez
 */
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;

public class CompressedView extends JComponent {
	private BufferedImage originalImage, compressedImage;
	private float quality = 0.1f;
	private int compressedSize;

	public float getQuality() {
		return quality;
	}

	public int getCompressedSize() {
		return compressedSize;
	}

	public void setQuality(float quality) {
		this.quality = quality;
		calculateCompressedImage();
	}

	private void calculateCompressedImage() {
		compressedImage = null;
		if (originalImage == null) {
			compressedSize = 0;
		} else {
			byte[] buff = ImageKit.toByteArray(originalImage, quality);
			compressedImage = ImageKit.read(buff);
			compressedSize = buff.length;
		}
		repaint();
	}

	public void setImage(BufferedImage image) {
		originalImage = image;
		calculateCompressedImage();
	}

	public int getImageWidth() {
		return (originalImage == null) ? 0 : originalImage.getWidth();
	}

	public int getImageHeight() {
		return (originalImage == null) ? 0 : originalImage.getHeight();
	}

	public Dimension getPreferredSize() {
		return new Dimension(getImageWidth() * 2, getImageHeight());
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (originalImage == null)
			return;
		g.drawImage(originalImage, 0, 0, null);
		g.drawImage(compressedImage, originalImage.getWidth(), 0, null);
	}

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://today.java.net/jag/bio/JagHeadshot-small.jpg");
		BufferedImage image = ImageIO.read(url);
		final CompressedView view = new CompressedView();
		view.setImage(image);
		final JLabel percent = new JLabel();
		final JLabel size = new JLabel();
		JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			NumberFormat format = NumberFormat.getIntegerInstance();

			public void stateChanged(ChangeEvent e) {
				JSlider sl = (JSlider) e.getSource();
				percent.setText(sl.getValue() + "%");
				if (!sl.getValueIsAdjusting()) {
					view.setQuality(sl.getValue() / 100f);
					size.setText(format.format(view.getCompressedSize())
							+ " bytes");
				}
			}
		});
		slider.setValue((int) (100 * view.getQuality()));
		JToolBar tb = new JToolBar();
		tb.add(percent);
		tb.add(slider);
		tb.add(size);
		JFrame f = new JFrame("CompressedView");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = f.getContentPane();
		cp.add(tb, BorderLayout.NORTH);
		cp.add(view);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
