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
 * Frank Buloup - Internationalization
 *******************************************************************************/
package org.eclipse.swtchart.extensions.internal.marker;

import java.text.DecimalFormat;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swtchart.ICircularSeries;
import org.eclipse.swtchart.ISeries;
import org.eclipse.swtchart.extensions.core.BaseChart;
import org.eclipse.swtchart.extensions.core.IAxisScaleConverter;
import org.eclipse.swtchart.extensions.core.IAxisSettings;
import org.eclipse.swtchart.extensions.core.IExtendedChart;
import org.eclipse.swtchart.extensions.marker.AbstractPositionPaintListener;
import org.eclipse.swtchart.extensions.marker.IPositionPaintListener;
import org.eclipse.swtchart.internal.series.CircularSeries;
import org.eclipse.swtchart.model.Node;

public class LegendMarker extends AbstractPositionPaintListener implements IPositionPaintListener {

	private StringBuilder stringBuilder;
	private String[] axisLabelsX;
	private DecimalFormat decimalFormatX;
	private String[] axisLabelsY;
	private DecimalFormat decimalFormatY;

	public LegendMarker(BaseChart baseChart) {

		super(baseChart);
		//
		stringBuilder = new StringBuilder();
		axisLabelsX = baseChart.getAxisLabels(IExtendedChart.X_AXIS);
		decimalFormatX = baseChart.getDecimalFormat(IExtendedChart.X_AXIS, BaseChart.ID_PRIMARY_X_AXIS);
		axisLabelsY = baseChart.getAxisLabels(IExtendedChart.Y_AXIS);
		decimalFormatY = baseChart.getDecimalFormat(IExtendedChart.Y_AXIS, BaseChart.ID_PRIMARY_Y_AXIS);
	}

	@Override
	public void paintControl(PaintEvent e) {

		if(isDraw()) {
			stringBuilder.delete(0, stringBuilder.length());
			e.gc.setForeground(getForegroundColor());
			e.gc.setBackground(getBackgroundColor());
			//
			BaseChart baseChart = getBaseChart();
			double primaryValueX = baseChart.getSelectedPrimaryAxisValue(getX(), IExtendedChart.X_AXIS);
			double primaryValueY = baseChart.getSelectedPrimaryAxisValue(getY(), IExtendedChart.Y_AXIS);
			// this is for circular charts
			boolean isCircularChart = false;
			for(ISeries<?> series : baseChart.getSeriesSet().getSeries()) {
				if(series instanceof ICircularSeries) {
					isCircularChart = true;
					drawNodes(primaryValueX, primaryValueY, (CircularSeries)series);
				}
			}
			// for Cartesian charts
			if(!isCircularChart) {
				drawXAxes(primaryValueX);
				drawYAxes(primaryValueY);
			}
			e.gc.drawText(stringBuilder.toString(), 10, 10);
		}
	}

	private void drawNodes(double primaryValueX, double primaryValueY, CircularSeries series) {

		String id = "---";
		String value = "---";
		String percentage = "---";
		//
		Node node = series.getPieSliceFromPosition(primaryValueX, primaryValueY);
		if(node != null) {
			id = node.getDescription().isEmpty() ? node.getId() : node.getDescription();
			double percent = ((node.getValue() * 100.0) / (node.getDataModel().getRootPointer().getValue()));
			DecimalFormat decimalFormat = new DecimalFormat();
			value = decimalFormat.format(node.getValue());
			percentage = decimalFormat.format(percent);
		}
		//
		String nodeClass = getBaseChart().getAxisSet().getXAxis(0).getTitle().getText();
		String valueClass = getBaseChart().getAxisSet().getYAxis(0).getTitle().getText();
		stringBuilder.append(nodeClass + " : " + id + "\n");
		stringBuilder.append(valueClass + " : " + value + "\n");
		//
		if(node != null) {
			stringBuilder.append("Percent of " + node.getDataModel().getRootPointer().getId() + " : " + percentage + "%\n");
		}
	}

	private void drawXAxes(double primaryValueX) {

		/*
		 * X Axes
		 */
		BaseChart baseChart = getBaseChart();
		IAxisSettings axisSettingsX = baseChart.getXAxisSettings(BaseChart.ID_PRIMARY_X_AXIS);
		if(axisSettingsX != null && axisSettingsX.isVisible()) {
			stringBuilder.append(axisLabelsX[BaseChart.ID_PRIMARY_X_AXIS]);
			stringBuilder.append(": "); //$NON-NLS-1$
			stringBuilder.append(decimalFormatX.format(primaryValueX));
		}
		//
		for(int id : baseChart.getAxisSet().getXAxisIds()) {
			if(id != BaseChart.ID_PRIMARY_X_AXIS) {
				IAxisSettings axisSettings = baseChart.getXAxisSettings(id);
				if(axisSettings != null) {
					IAxisScaleConverter axisScaleConverter = baseChart.getAxisScaleConverter(IExtendedChart.X_AXIS, id);
					if(axisSettings.isVisible() && axisScaleConverter != null) {
						if(stringBuilder.length() > 0) {
							stringBuilder.append("\n"); //$NON-NLS-1$
						}
						stringBuilder.append(axisLabelsX[id]);
						stringBuilder.append(": "); //$NON-NLS-1$
						stringBuilder.append(axisSettings.getDecimalFormat().format(axisScaleConverter.convertToSecondaryUnit(primaryValueX)));
					}
				}
			}
		}
	}

	private void drawYAxes(double primaryValueY) {

		/*
		 * Y Axes
		 */
		BaseChart baseChart = getBaseChart();
		IAxisSettings axisSettingsY = baseChart.getYAxisSettings(BaseChart.ID_PRIMARY_Y_AXIS);
		if(axisSettingsY != null && axisSettingsY.isVisible()) {
			if(stringBuilder.length() > 0) {
				stringBuilder.append("\n"); //$NON-NLS-1$
			}
			stringBuilder.append(axisLabelsY[BaseChart.ID_PRIMARY_Y_AXIS]);
			stringBuilder.append(": "); //$NON-NLS-1$
			stringBuilder.append(decimalFormatY.format(primaryValueY));
		}
		//
		for(int id : baseChart.getAxisSet().getYAxisIds()) {
			if(id != BaseChart.ID_PRIMARY_Y_AXIS) {
				IAxisSettings axisSettings = baseChart.getYAxisSettings(id);
				if(axisSettings != null) {
					IAxisScaleConverter axisScaleConverter = baseChart.getAxisScaleConverter(IExtendedChart.Y_AXIS, id);
					if(axisSettings.isVisible() && axisScaleConverter != null) {
						if(stringBuilder.length() > 0) {
							stringBuilder.append("\n"); //$NON-NLS-1$
						}
						stringBuilder.append(axisLabelsY[id]);
						stringBuilder.append(": "); //$NON-NLS-1$
						stringBuilder.append(axisSettings.getDecimalFormat().format(axisScaleConverter.convertToSecondaryUnit(primaryValueY)));
					}
				}
			}
		}
	}
}
