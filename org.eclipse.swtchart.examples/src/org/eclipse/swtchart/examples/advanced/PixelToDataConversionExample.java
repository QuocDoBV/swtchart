/*******************************************************************************
 * Copyright (c) 2008, 2023 SWTChart project.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 * yoshitaka - initial API and implementation
 *******************************************************************************/
package org.eclipse.swtchart.examples.advanced;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtchart.Chart;
import org.eclipse.swtchart.IAxis;
import org.eclipse.swtchart.ILineSeries;
import org.eclipse.swtchart.ISeries.SeriesType;

/**
 * An example to convert pixel coordinate into data coordinate.
 */
public class PixelToDataConversionExample {

	private static final double[] ySeries = {0.0, 0.38, 0.71, 0.92, 1.0, 0.92, 0.71, 0.38, 0.0, -0.38, -0.71, -0.92, -1.0, -0.92, -0.71, -0.38};

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Pixel To Data Conversion");
		shell.setSize(500, 400);
		shell.setLayout(new FillLayout());
		createChart(shell);
		shell.open();
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	/**
	 * create the chart.
	 * 
	 * @param parent
	 *            The parent composite
	 * @return The created chart
	 */
	static public Chart createChart(Composite parent) {

		// create a chart
		final Chart chart = new Chart(parent, SWT.NONE);
		chart.getTitle().setText("Pixel To Data Conversion");
		chart.getPlotArea().setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		chart.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		// get axes
		final IAxis xAxis = chart.getAxisSet().getXAxis(0);
		final IAxis yAxis = chart.getAxisSet().getYAxis(0);
		// create line series
		ILineSeries<?> series = (ILineSeries<?>)chart.getSeriesSet().createSeries(SeriesType.LINE, "line series");
		series.setYSeries(ySeries);
		// adjust the axis range
		chart.getAxisSet().adjustRange();
		// add mouse move listener to show mouse position on tooltip
		chart.getPlotArea().addMouseMoveListener(e -> {
			double x = xAxis.getDataCoordinate(e.x);
			double y = yAxis.getDataCoordinate(e.y);
			chart.getPlotArea().setToolTipText("x:" + x + ", y:" + y);
		});
		return chart;
	}
}
