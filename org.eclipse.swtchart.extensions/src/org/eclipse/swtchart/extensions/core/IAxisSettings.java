/*******************************************************************************
 * Copyright (c) 2017, 2023 Lablicate GmbH.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.swtchart.extensions.core;

import java.text.DecimalFormat;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swtchart.IAxis.Position;
import org.eclipse.swtchart.LineStyle;

public interface IAxisSettings {

	/**
	 * This method returns the label of the axis.
	 * It's calculated by using the title and description.
	 * 
	 * @return String
	 */
	String getLabel();

	String getTitle();

	void setTitle(String title);

	boolean isTitleVisible();

	void setTitleVisible(boolean titleVisible);

	String getDescription();

	void setDescription(String description);

	DecimalFormat getDecimalFormat();

	void setDecimalFormat(DecimalFormat decimalFormat);

	Color getColor();

	void setColor(Color color);

	Font getTitleFont();

	void setTitleFont(Font titleFont);

	boolean isVisible();

	void setVisible(boolean visible);

	Position getPosition();

	void setPosition(Position position);

	Color getGridColor();

	void setGridColor(Color gridColor);

	LineStyle getGridLineStyle();

	/**
	 * LineStyle.SOLID
	 * LineStyle.DASH
	 * LineStyle.DOT
	 * LineStyle.DASHDOT
	 * LineStyle.DASHDOTDOT
	 * LineStyle.NONE
	 * 
	 * @param gridLineStyle
	 */
	void setGridLineStyle(LineStyle gridLineStyle);

	boolean isEnableLogScale();

	void setEnableLogScale(boolean enableLogScale);

	void setLogScaleBase(double base);

	double getLogScaleBase();

	boolean isReversed();

	void setReversed(boolean reversed);

	boolean isDrawAxisLine();

	void setDrawAxisLine(boolean drawAxisLine);

	boolean isDrawPositionMarker();

	void setDrawPositionMarker(boolean drawPositionMarker);

	int getExtraSpaceTitle();

	void setExtraSpaceTitle(int extraSpaceTitle);

	/**
	 * @return
	 *         true, if the axis is supposed to tick only at integer data points.
	 */
	boolean isIntegerDataPointAxis();

	void setIntegerDataPointAxis(boolean isIntegerDataPointAxis);

	void dispose();
}
