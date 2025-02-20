/*******************************************************************************
 * Copyright (c) 2008, 2024 SWTChart project.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 * yoshitaka - initial API and implementation
 * Frank Buloup - Internationalization
 * Philip Wenig - resource handling
 *******************************************************************************/
package org.eclipse.swtchart.internal;

import java.util.UUID;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtchart.Chart;
import org.eclipse.swtchart.ITitle;
import org.eclipse.swtchart.Resources;

/**
 * A base class for title.
 */
public class Title implements ITitle, PaintListener {

	/** the chart */
	private Chart chart;
	/** the title text */
	private String text;
	/** the foreground color */
	private Color foreground;
	/** the font */
	private Font font;
	/** the style ranges */
	private StyleRange[] styleRanges;
	/** the visibility state of axis */
	private boolean isVisible;
	/** the default font */
	private final Font defaultFont;
	/** the bounds of title */
	private Rectangle bounds;
	/** the layout data */
	private ChartLayoutData layoutData;
	/** the default font size */
	private static final int DEFAULT_FONT_SIZE = Resources.LARGE_FONT_SIZE;
	/** the default color */
	private static final int DEFAULT_FOREGROUND = SWT.COLOR_BLUE;
	/** the default text */
	private static final String DEFAULT_TEXT = ""; //$NON-NLS-1$
	/*
	 * Used internally to track the text layout.
	 */
	private String textLayoutUUID = null;

	public Title(Chart parent) {

		this.chart = parent;
		text = DEFAULT_TEXT;
		isVisible = true;
		defaultFont = Resources.getFont(Resources.DEFAULT_FONT_NAME, DEFAULT_FONT_SIZE, SWT.BOLD); // $NON-NLS-1$
		bounds = new Rectangle(0, 0, 0, 0);
		font = defaultFont;
		setForeground(Display.getDefault().getSystemColor(DEFAULT_FOREGROUND));
		parent.addPaintListener(this);
	}

	@Override
	public void setText(String text) {

		/*
		 * Text could have been changed to blank.
		 */
		this.text = (text == null) ? getDefaultText() : text;
		if(useTextLayout()) {
			TextLayout textLayout = Resources.getTextLayout(textLayoutUUID);
			textLayout.setText(text);
		}
		chart.updateLayout();
	}

	/**
	 * Gets the default title text.
	 * 
	 * @return the default title text
	 */
	protected String getDefaultText() {

		return DEFAULT_TEXT;
	}

	@Override
	public String getText() {

		return text;
	}

	/**
	 * Sets the font.
	 * 
	 * @param font
	 *            the font
	 */
	@Override
	public void setFont(Font font) {

		if(font == null) {
			this.font = defaultFont;
		} else if(font.isDisposed()) {
			throw new IllegalArgumentException(Messages.getString(Messages.DISPOSED_FONT_GIVEN));
		} else {
			this.font = font;
		}
		chart.updateLayout();
	}

	/**
	 * Gets the font.
	 * 
	 * @return the font
	 */
	@Override
	public Font getFont() {

		if(font.isDisposed()) {
			font = defaultFont;
		}
		return font;
	}

	/**
	 * Sets the foreground color.
	 * 
	 * @param color
	 *            the foreground color
	 */
	@Override
	public void setForeground(Color color) {

		if(color == null) {
			foreground = Display.getDefault().getSystemColor(DEFAULT_FOREGROUND);
		} else if(color.isDisposed()) {
			throw new IllegalArgumentException(Messages.getString(Messages.DISPOSED_COLOR_GIVEN));
		} else {
			foreground = color;
		}
	}

	/**
	 * Gets the foreground color.
	 * 
	 * @return the foreground color
	 */
	@Override
	public Color getForeground() {

		return foreground;
	}

	@Override
	public void setStyleRanges(StyleRange[] ranges) {

		initializeTextLayout();
		TextLayout textLayout = Resources.getTextLayout(textLayoutUUID);
		textLayout.setText(text);
		//
		styleRanges = ranges;
		if(styleRanges != null) {
			for(StyleRange styleRange : styleRanges) {
				if(styleRange != null) {
					textLayout.setStyle(styleRange, styleRange.start, styleRange.start + styleRange.length);
				}
			}
		}
		chart.updateLayout();
	}

	@Override
	public StyleRange[] getStyleRanges() {

		return styleRanges;
	}

	@Override
	public void setVisible(boolean isVisible) {

		if(this.isVisible == isVisible) {
			return;
		}
		this.isVisible = isVisible;
		chart.updateLayout();
	}

	@Override
	public boolean isVisible() {

		return isVisible;
	}

	/**
	 * Gets the state indicating if showing title horizontally.
	 * 
	 * @return the state indicating if showing title horizontally
	 */
	protected boolean isHorizontal() {

		return true;
	}

	/**
	 * Updates the title layout data.
	 */
	public void updateLayoutData() {

		int height;
		int width;
		if(isVisible() && !text.trim().equals("")) { //$NON-NLS-1$
			if(useTextLayout()) {
				TextLayout textLayout = Resources.getTextLayout(textLayoutUUID);
				Rectangle rectangle = textLayout.getBounds();
				width = rectangle.width;
				height = rectangle.height;
			} else {
				Point point = Util.getExtentInGC(getFont(), text);
				width = point.x;
				height = point.y;
			}
		} else {
			width = 0;
			height = 0;
		}
		if(isHorizontal()) {
			setLayoutData(new ChartLayoutData(width, height));
		} else {
			setLayoutData(new ChartLayoutData(height, width));
		}
	}

	/**
	 * Sets the layout data.
	 * 
	 * @param layoutData
	 *            the layout data
	 */
	public void setLayoutData(ChartLayoutData layoutData) {

		this.layoutData = layoutData;
	}

	/**
	 * Gets the layout data.
	 * 
	 * @return the layout data
	 */
	public ChartLayoutData getLayoutData() {

		return layoutData;
	}

	/**
	 * Disposes the resources.
	 */
	public void dispose() {

		if(!chart.isDisposed()) {
			chart.removePaintListener(this);
		}
	}

	@Override
	public void paintControl(PaintEvent e) {

		if(text == null || text.equals("") || !isVisible) { //$NON-NLS-1$
			return;
		}
		Font oldFont = e.gc.getFont();
		Color oldForeground = getForeground();
		e.gc.setFont(getFont());
		e.gc.setForeground(getForeground());
		if(isHorizontal()) {
			drawHorizontalTitle(e.gc);
		} else {
			drawVerticalTitle(e.gc);
		}
		e.gc.setFont(oldFont);
		e.gc.setForeground(oldForeground);
	}

	/**
	 * Sets the bounds on chart panel.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public void setBounds(int x, int y, int width, int height) {

		bounds = new Rectangle(x, y, width, height);
	}

	/**
	 * Gets the bounds on chart panel.
	 * 
	 * @return the bounds on chart panel
	 */
	public Rectangle getBounds() {

		return bounds;
	}

	/**
	 * Draws the horizontal title.
	 * 
	 * @param gc
	 *            The graphics context
	 */
	private void drawHorizontalTitle(GC gc) {

		int x = getBounds().x;
		int y = getBounds().y;
		if(useTextLayout()) {
			TextLayout textLayout = Resources.getTextLayout(textLayoutUUID);
			textLayout.draw(gc, x, y);
		} else {
			gc.drawText(text, x, y, true);
		}
	}

	/**
	 * Draws the vertical title.
	 * 
	 * @param gc
	 *            The graphics context
	 */
	private void drawVerticalTitle(GC gc) {

		int textWidth = getBounds().height;
		int textHeight = getBounds().width;
		if(getFont().getFontData()[0].getStyle() == SWT.ITALIC) {
			int margin = textHeight / 10;
			textWidth += margin;
		}
		/*
		 * Create image to draw text. If drawing text on rotated graphics
		 * context instead of drawing rotated image, the text shape becomes a
		 * bit ugly especially with small font with bold.
		 */
		if(textWidth > 0 && textHeight > 0) {
			Image image = new Image(Display.getCurrent(), textWidth, textHeight);
			GC tmpGc = new GC(image);
			tmpGc.setBackground(chart.getBackground());
			tmpGc.fillRectangle(image.getBounds());
			if(useTextLayout()) {
				TextLayout textLayout = Resources.getTextLayout(textLayoutUUID);
				textLayout.draw(tmpGc, 0, 0);
			} else {
				tmpGc.setForeground(getForeground());
				tmpGc.setFont(getFont());
				tmpGc.drawText(text, 0, 0);
			}
			/*
			 * Set transform to rotate.
			 */
			Transform oldTransform = new Transform(gc.getDevice());
			gc.getTransform(oldTransform);
			Transform transform = new Transform(gc.getDevice());
			transform.translate(0, textWidth);
			transform.rotate(270);
			gc.setTransform(transform);
			// draw the image on the rotated graphics context
			int x = getBounds().x;
			int y = getBounds().y;
			gc.drawImage(image, -y, x);
			gc.setTransform(oldTransform);
			// dispose resources
			oldTransform.dispose();
			tmpGc.dispose();
			transform.dispose();
			image.dispose();
		}
	}

	private boolean useTextLayout() {

		boolean useTextLayout = (styleRanges != null);
		if(useTextLayout && textLayoutUUID == null) {
			initializeTextLayout();
		}
		//
		return useTextLayout;
	}

	private void initializeTextLayout() {

		if(textLayoutUUID == null) {
			textLayoutUUID = UUID.randomUUID().toString();
		}
	}
}