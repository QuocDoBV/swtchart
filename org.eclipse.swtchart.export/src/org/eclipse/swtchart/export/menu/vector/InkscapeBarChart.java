/*******************************************************************************
 * Copyright (c) 2019, 2023 Lablicate GmbH.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 * Sanatt Abrol - SVG export code
 * Frank Buloup - Internationalization
 *******************************************************************************/
package org.eclipse.swtchart.export.menu.vector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swtchart.IAxis;
import org.eclipse.swtchart.IAxisSet;
import org.eclipse.swtchart.IBarSeries;
import org.eclipse.swtchart.ISeries;
import org.eclipse.swtchart.export.core.AxisSettings;
import org.eclipse.swtchart.extensions.core.BaseChart;
import org.eclipse.swtchart.extensions.core.IAxisScaleConverter;
import org.eclipse.swtchart.extensions.core.IAxisSettings;
import org.eclipse.swtchart.extensions.core.ScrollableChart;

public class InkscapeBarChart extends AbstractInkscapeTemplate {

	private static final String TEMPLATE_BAR_CHART = "Template_BarChart.svg";

	@Override
	public String generate(ScrollableChart scrollableChart, AxisSettings axisSettings) throws Exception {

		StringBuilder builder = new StringBuilder();
		//
		IAxisSettings axisSettingsX = axisSettings.getAxisSettingsX();
		IAxisSettings axisSettingsY = axisSettings.getAxisSettingsY();
		boolean isReversedX = axisSettingsX.isReversed();
		boolean isReversedY = axisSettingsY.isReversed();
		DecimalFormat formatX = axisSettingsX.getDecimalFormat();
		DecimalFormat formatY = axisSettingsY.getDecimalFormat();
		//
		BaseChart baseChart = scrollableChart.getBaseChart();
		boolean isShowAxisZeroMarker = baseChart.getChartSettings().isShowAxisZeroMarker();
		ISeries<?>[] series = baseChart.getSeriesSet().getSeries();
		String template = TEMPLATE_BAR_CHART;
		//
		try (BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(template)))) {
			double xTicks[] = baseChart.getAxisSet().getXAxis(axisSettings.getIndexAxisX()).getTick().getTickLabelValues();
			double yTicks[] = baseChart.getAxisSet().getYAxis(axisSettings.getIndexAxisY()).getTick().getTickLabelValues();
			String line;
			StringBuilder tickX = new StringBuilder("<path\n" + "                       inkscape:connector-curvature=\"0\"\n" + "                       id=\"path888\"\n" + "                       d=\"m %x-coordinate%,279.77439 v 5.05309\"\n" + "                       style=\"fill:none;stroke:#000000;stroke-width:0.26458332px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1\" />\n" + "                    <text\n" + "                       id=\"text892\"\n" + "                       y=\"289.01782\"\n" + "                       x=\"%x-coordinate%\"\n" + "                       style=\"font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;font-size:6.3499999px;line-height:1.25;font-family:arial;-inkscape-font-specification:arial;letter-spacing:0px;word-spacing:0px;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.26458332\"\n" + "                       xml:space=\"preserve\"><tspan\n" + "                         id=\"tspan890\"\n" + "                         style=\"font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;font-size:3.52777767px;font-family:arial;-inkscape-font-specification:arial;text-align:center;text-anchor:middle;stroke-width:0.26458332\"\n" + "                         y=\"289.01782\"\n" + "                         x=\"%x-coordinate%\"\n" + "                         sodipodi:role=\"line\">%X01%</tspan></text>");
			StringBuilder tickY = new StringBuilder("<path\n" + "                       inkscape:connector-curvature=\"0\"\n" + "                       id=\"path1299\"\n" + "                       d=\"m 15.387913,%y-coordinate% h 5.05309\"\n" + "                       style=\"fill:none;stroke:#000000;stroke-width:0.26458332px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1\" />\n" + "                    <text\n" + "                       xml:space=\"preserve\"\n" + "                       style=\"font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;font-size:6.3499999px;line-height:1.25;font-family:arial;-inkscape-font-specification:arial;letter-spacing:0px;word-spacing:0px;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.26458332\"\n" + "                       x=\"14.211229\"\n" + "                       y=\"%y-coordinate%\"\n" + "                       id=\"text1303\"><tspan\n" + "                         sodipodi:role=\"line\"\n" + "                         id=\"tspan1301\"\n" + "                         x=\"14.211229\"\n" + "                         y=\"%y-coordinate%\"\n" + "                         style=\"font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;font-size:3.52777767px;font-family:arial;-inkscape-font-specification:arial;text-align:end;text-anchor:end;stroke-width:0.26458332\">%Y01%</tspan></text>");
			StringBuilder legend = new StringBuilder("<path\n" + "                   style=\"fill:url(#linearGradient3662);fill-opacity:1;stroke:%COLOR%;stroke-width:0.5;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" + "                   d=\"m 209.71446,%y1-coordinate% h 20.93268\"\n" + "                   id=\"path1744\"\n" + "                   inkscape:connector-curvature=\"0\" />\n" + "                <text\n" + "                   xml:space=\"preserve\"\n" + "                   style=\"font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;font-size:6.3499999px;line-height:1.25;font-family:'Bold Oblique';-inkscape-font-specification:'Bold Oblique, ';letter-spacing:0px;word-spacing:0px;fill:url(#linearGradient4353);fill-opacity:1;stroke:none;stroke-width:0.26458332\"\n" + "                   x=\"230.91121\"\n" + "                   y=\"%y2-coordinate%\"\n" + "                   id=\"text1748\"><tspan\n" + "                     sodipodi:role=\"line\"\n" + "                     id=\"tspan1746\"\n" + "                     x=\"230.91121\"\n" + "                     y=\"%y2-coordinate%\"\n" + "                     style=\"font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;font-size:4.23333311px;font-family:Arial;-inkscape-font-specification:Arial;fill:url(#linearGradient4353);fill-opacity:1;stroke-width:0.26458332\">%SERIES A%</tspan></text>");
			StringBuilder axisLabel = new StringBuilder("<path\n" + "         style=\"fill:none;stroke:#000000;stroke-width:0.26499999;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1;stroke-miterlimit:4;stroke-dasharray:0.52999998,0.52999998;stroke-dashoffset:0;opacity:1\"\n" + "         d=\"M %x1-coordinate%,%y1-coordinate% L %x2-coordinate%,%y2-coordinate%\"\n" + "         id=\"path850\"\n" + "         inkscape:connector-curvature=\"0\" />");
			String regexX = ".*%X-AXIS_TICKS%.*";
			String regexY = ".*%Y-AXIS_TICKS%.*";
			String titleX = ".*%PLACEHOLDER X-AXIS%.*";
			String titleY = ".*%PLACEHOLDER Y-AXIS%.*";
			String data_series = ".*%DATA SERIES%.*";
			String regex_legend = ".*%LEGEND%.*";
			String axis_label = ".*%AXIS LABELS%.*";
			while((line = in.readLine()) != null) {
				if(Pattern.matches(regexX, line)) {
					int ticklabel_size = xTicks.length;
					double start = 20.306;
					double height = 307.216 - 20.306;
					StringBuilder out = new StringBuilder("");
					double upper = baseChart.getAxisSet().getXAxis(axisSettings.getIndexAxisX()).getRange().upper;
					double lower = baseChart.getAxisSet().getXAxis(axisSettings.getIndexAxisX()).getRange().lower;
					for(int count = 0; count < ticklabel_size; count++) {
						String split[] = tickX.toString().split("\\n");
						String match1 = ".*%x-coordinate%.*";
						String match2 = ".*%X01%.*";
						double x;
						if(!isReversedX) {
							x = start + ((xTicks[count] - lower) / (upper - lower) * height);
						} else {
							x = ((start + height) - (((xTicks[count] - lower) / (upper - lower) * height)));
						}
						for(String string : split) {
							if(Pattern.matches(match1, string)) {
								string = string.replace("%x-coordinate%", String.valueOf(x));
							} else if(Pattern.matches(match2, string)) {
								string = string.replace("%X01%", String.valueOf(formatX.format(xTicks[count])));
							}
							out.append(string);
							out.append("\n");
						}
						out.append("\n");
					}
					line = line.replaceAll(regexX, out.toString());
				} else if(Pattern.matches(regexY, line)) {
					int ticklabel_size = yTicks.length;
					double start = 279.90709;
					double height = 200.0;
					StringBuilder out = new StringBuilder("");
					double upper = baseChart.getAxisSet().getYAxis(axisSettings.getIndexAxisY()).getRange().upper;
					double lower = baseChart.getAxisSet().getYAxis(axisSettings.getIndexAxisY()).getRange().lower;
					for(int count = 0; count < ticklabel_size; count++) {
						String split[] = tickY.toString().split("\\n");
						String match1 = ".*%y-coordinate%.*";
						String match2 = ".*%Y01%.*";
						double y;
						if(!isReversedY) {
							y = start - (height - ((upper - yTicks[count]) / (upper - lower) * height));
						} else {
							y = ((start - height) + (height - ((upper - yTicks[count]) / (upper - lower) * height)));
						}
						for(String string : split) {
							if(Pattern.matches(match1, string)) {
								string = string.replace("%y-coordinate%", String.valueOf(y));
							} else if(Pattern.matches(match2, string)) {
								string = string.replace("%Y01%", String.valueOf(formatY.format(yTicks[count])));
							}
							out.append(string);
							out.append("\n");
						}
						out.append("\n");
					}
					line = line.replaceAll(regexY, out.toString());
				} else if(Pattern.matches(titleX, line)) {
					line = line.replace("%PLACEHOLDER X-AXIS%", axisSettingsX.getTitle());
				} else if(Pattern.matches(titleY, line)) {
					line = line.replace("%PLACEHOLDER Y-AXIS%", axisSettingsY.getTitle());
				} else if(Pattern.matches(regex_legend, line)) {
					double start1 = 100.5815;
					double start2 = 102.06668;
					StringBuilder out = new StringBuilder("");
					int count = 0;
					for(ISeries<?> serie : series) {
						if(serie.isVisible()) {
							IBarSeries<?> barSerie = (IBarSeries<?>)serie;
							Color color = barSerie.getBarColor();
							String col = getColor(color);
							/*
							 * 6 taken just to keep appropriate distance between the Series names in the legend.
							 */
							double y1 = start1 + 6 * count;
							double y2 = start2 + 6 * count;
							String des = serie.getDescription();
							String split[] = legend.toString().split("\\n");
							String match1 = ".*%y1-coordinate%.*";
							String match2 = ".*%y2-coordinate%.*";
							String match3 = ".*%COLOR%.*";
							String match4 = ".*%SERIES A%.*";
							for(String string : split) {
								if(Pattern.matches(match1, string)) {
									string = string.replace("%y1-coordinate%", String.valueOf(y1));
								} else if(Pattern.matches(match2, string)) {
									string = string.replace("%y2-coordinate%", String.valueOf(y2));
								} else if(Pattern.matches(match3, string)) {
									string = string.replace("%COLOR%", col);
								} else if(Pattern.matches(match4, string)) {
									string = string.replace("%SERIES A%", des);
								}
								out.append(string);
								out.append("\n");
							}
							out.append("\n");
							count++;
						}
					}
					line = line.replaceAll(regex_legend, out.toString());
				} else if(Pattern.matches(axis_label, line)) {
					if(isShowAxisZeroMarker) {
						StringBuilder out = new StringBuilder("");
						String split[] = axisLabel.toString().split("\\n");
						String match1 = ".*%x1-coordinate%.*";
						String match2 = ".*%x2-coordinate%.*";
						String match3 = ".*%y1-coordinate%.*";
						String match4 = ".*%y2-coordinate%.*";
						double start1 = 20.306;
						double height1 = 307.216 - 20.306;
						double start2 = 279.90709;
						double height2 = 200.0;
						double upper = baseChart.getAxisSet().getXAxis(axisSettings.getIndexAxisX()).getRange().upper;
						double lower = baseChart.getAxisSet().getXAxis(axisSettings.getIndexAxisX()).getRange().lower;
						double x1;
						if(!isReversedX) {
							x1 = start1 + ((0.0 - lower) / (upper - lower) * height1);
						} else {
							x1 = ((start1 + height1) - (((0.0 - lower) / (upper - lower) * height1)));
						}
						if(x1 >= start1 && x1 <= start1 + height1) {
							for(String string : split) {
								if(Pattern.matches(match1, string)) {
									string = string.replace("%x1-coordinate%", String.valueOf(x1));
								}
								if(Pattern.matches(match3, string)) {
									string = string.replace("%y1-coordinate%", String.valueOf(start2));
								}
								if(Pattern.matches(match2, string)) {
									string = string.replace("%x2-coordinate%", String.valueOf(x1));
								}
								if(Pattern.matches(match4, string)) {
									string = string.replace("%y2-coordinate%", String.valueOf(start2 - height2));
								}
								out.append(string);
								out.append("\n");
							}
						}
						out.append("\n");
						double upper2 = baseChart.getAxisSet().getYAxis(axisSettings.getIndexAxisY()).getRange().upper;
						double lower2 = baseChart.getAxisSet().getYAxis(axisSettings.getIndexAxisY()).getRange().lower;
						double y1;
						if(!isReversedY) {
							y1 = start2 - (height2 - ((upper2 - 0.0) / (upper2 - lower2) * height2));
						} else {
							y1 = ((start2 - height2) + (height2 - ((upper2 - 0.0) / (upper2 - lower2) * height2)));
						}
						if(y1 <= start2 && y1 >= start2 - height2) {
							for(String string : split) {
								if(Pattern.matches(match1, string)) {
									string = string.replace("%x1-coordinate%", String.valueOf(start1));
								}
								if(Pattern.matches(match3, string)) {
									string = string.replace("%y1-coordinate%", String.valueOf(y1));
								}
								if(Pattern.matches(match2, string)) {
									string = string.replace("%x2-coordinate%", String.valueOf(start1 + height1));
								}
								if(Pattern.matches(match4, string)) {
									string = string.replace("%y2-coordinate%", String.valueOf(y1));
								}
								out.append(string);
								out.append("\n");
							}
						}
						line = line.replaceAll(axis_label, out.toString());
					}
				} else if(Pattern.matches(data_series, line)) {
					StringBuilder out = new StringBuilder("");
					int widthPlotArea = baseChart.getPlotArea().getSize().x;
					int heightPlotArea = baseChart.getPlotArea().getSize().y;
					IAxisSet axisSet = baseChart.getAxisSet();
					int index = 0;
					for(ISeries<?> dataSeries : series) {
						if(dataSeries != null && dataSeries.isVisible()) {
							StringBuilder string = printBarData(dataSeries, widthPlotArea, heightPlotArea, axisSettings, index++, axisSet, isReversedX, isReversedY);
							out.append(string);
						}
					}
					line = line.replaceAll(data_series, out.toString());
				}
				builder.append(line);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		//
		return builder.toString();
	}

	/*
	 * returns the data series to be replaced in the data series in template
	 */
	private StringBuilder printBarData(ISeries<?> dataSeries, int widthPlotArea, int heightPlotArea, AxisSettings axisSettings, int index, IAxisSet axisSet, boolean isReversedX, boolean isReversedY) {

		StringBuilder out = new StringBuilder("");
		/* BarSeries to be added to the template during export */
		StringBuilder data = new StringBuilder("<rect\n" + "         style=\"opacity:1;fill:%COLOR%;fill-opacity:1;stroke:none;stroke-width:0.96499991;stroke-linecap:square;stroke-linejoin:round;stroke-miterlimit:4;stroke-dasharray:none;stroke-dashoffset:0;stroke-opacity:1\"\n" + "         id=\"rect901\"\n" + "         width=\"1\"\n" + "         height=\"%height%\"\n" + "         x=\"%x-coordinate%\"\n" + "         y=\"%y-coordinate%\"\n" + "         ry=\"0\" />");
		IBarSeries<?> barSeries = (IBarSeries<?>)dataSeries;
		Color barColor = barSeries.getBarColor();
		String color = getColor(barColor);
		int indexAxisX = axisSettings.getIndexAxisX();
		int indexAxisY = axisSettings.getIndexAxisY();
		IAxisScaleConverter axisScaleConverterX = axisSettings.getAxisScaleConverterX();
		IAxisScaleConverter axisScaleConverterY = axisSettings.getAxisScaleConverterY();
		//
		double[] xSeries = dataSeries.getXSeries();
		double[] ySeries = dataSeries.getYSeries();
		String split[] = data.toString().split("\\n");
		int size = dataSeries.getXSeries().length;
		//
		String match1 = ".*%COLOR%.*";
		String match2 = ".*%x-coordinate%.*";
		String match3 = ".*%y-coordinate%.*";
		String match4 = ".*%height%.*";
		for(int i = 0; i < size; i++) {
			/*
			 * Only export if the data point is visible.
			 */
			Point point = dataSeries.getPixelCoordinates(i);
			if((point.x >= 0 && point.x <= widthPlotArea)) {
				double offset = 0.25;
				double x = Double.parseDouble(printValueBarPlot(AXIS_X, index, xSeries[i], indexAxisX, axisSet, BaseChart.ID_PRIMARY_X_AXIS, axisScaleConverterX, isReversedX, isReversedY));
				double y = Double.parseDouble(printValueBarPlot(AXIS_Y, index, ySeries[i], indexAxisY, axisSet, BaseChart.ID_PRIMARY_Y_AXIS, axisScaleConverterY, isReversedX, isReversedY));
				double base = Double.parseDouble(printValueBarPlot(AXIS_Y, index, 0.0, indexAxisY, axisSet, BaseChart.ID_PRIMARY_Y_AXIS, axisScaleConverterY, isReversedX, isReversedY));
				double height = Math.abs(y - base);
				/* Width for BarSeries in the Export */
				double width = 1.0;
				/* Values for X,Y, height in the Export */
				double newX = x;
				double newY = y;
				double newWidth = width;
				double newHeight = height;
				/*
				 * Adjusting Bar Width For Export
				 * Have a look at the BarSeries implementation
				 */
				if(x < 23.5) {
					newX = -offset;
					newWidth += (x + offset);
				}
				if(y < 80.5) {
					newY = -offset;
					newHeight += y + offset;
				}
				double plotWidthArea = 255.5 - 23.5;
				if(x + width > plotWidthArea) {
					newWidth -= x + width - plotWidthArea + offset;
					if(newWidth < 0) {
						newWidth = 0;
					}
				}
				if(y > base) {
					if(base + height > y) {
						newHeight -= (base + height) - y + offset;
						if(newHeight < 0) {
							newHeight = 0;
						}
					}
				} else {
					if(y + height > base) {
						newHeight -= (y + height) - base + offset;
						if(newHeight < 0) {
							newHeight = 0;
						}
					}
				}
				for(String string : split) {
					if(Pattern.matches(match1, string)) {
						string = string.replace("%COLOR%", color);
					} else if(Pattern.matches(match2, string)) {
						string = string.replace("%x-coordinate%", String.valueOf(newX));
					} else if(Pattern.matches(match3, string)) {
						if(ySeries[i] >= 0) {
							string = string.replace("%y-coordinate%", String.valueOf(newY));
						} else {
							string = string.replace("%y-coordinate%", String.valueOf(base));
						}
					} else if(Pattern.matches(match4, string)) {
						string = string.replace("%height%", String.valueOf(newHeight));
					}
					out.append(string);
					out.append("\n");
				}
			}
		}
		return out;
	}

	/*
	 * returns value scaled to the appropriate coordinates in SVG
	 */
	private String printValueBarPlot(String axis, int index, double value, int indexAxis, IAxisSet axisSet, int indexPrimaryAxis, IAxisScaleConverter axisScaleConverter, boolean isReversedX, boolean isReversedY) {

		String ret = null;
		double x = 255.5 - 23.5;
		double y = 263.5 - 80.5;
		if(indexAxis == indexPrimaryAxis || axisScaleConverter == null) {
			if(axis.equals(AXIS_X)) {
				IAxis xAxis = axisSet.getXAxis(indexAxis);
				double xUpper = xAxis.getRange().upper;
				double xLower = xAxis.getRange().lower;
				value = (value > xUpper) ? xUpper : value;
				value = (value < xLower) ? xLower : value;
				double x1;
				if(!isReversedX) {
					x1 = 23.5 + ((value - xLower) / (xUpper - xLower) * x);
				} else {
					x1 = ((23.5 + x) - ((value - xLower) / (xUpper - xLower) * x));
				}
				ret = String.valueOf(x1);// $NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			} else if(axis.equals(AXIS_Y)) {
				IAxis yAxis = axisSet.getYAxis(indexAxis);
				double yUpper = yAxis.getRange().upper;
				double yLower = yAxis.getRange().lower;
				value = (value > yUpper) ? yUpper : value;
				value = (value < yLower) ? yLower : value;
				double y1;
				if(!isReversedY) {
					y1 = 263.5 - (y - ((yUpper - value) / (yUpper - yLower) * y));
				} else {
					y1 = ((263.5 - y) + (y - ((yUpper - value) / (yUpper - yLower) * y)));
				}
				ret = String.valueOf(y1);// $NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			}
		} else {
			if(axisScaleConverter != null) {
				if(axis.equals(AXIS_X)) {
					IAxis xAxis = axisSet.getXAxis(indexAxis);
					double xUpper = xAxis.getRange().upper;
					double xLower = xAxis.getRange().lower;
					value = axisScaleConverter.convertToSecondaryUnit(value);
					value = (value > xUpper) ? xUpper : value;
					value = (value < xLower) ? xLower : value;
					double x1;
					if(!isReversedX) {
						x1 = 23.5 + ((value - xLower) / (xUpper - xLower) * x);
					} else {
						x1 = ((23.5 + x) + ((value - xLower) / (xUpper - xLower) * x));
					}
					ret = String.valueOf(x1);// $NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				} else if(axis.equals(AXIS_Y)) {
					IAxis yAxis = axisSet.getYAxis(indexAxis);
					double yUpper = yAxis.getRange().upper;
					double yLower = yAxis.getRange().lower;
					value = axisScaleConverter.convertToSecondaryUnit(value);
					value = (value > yUpper) ? yUpper : value;
					value = (value < yLower) ? yLower : value;
					double y1;
					if(!isReversedY) {
						y1 = 263.5 - (y - ((yUpper - value) / (yUpper - yLower) * y));
					} else {
						y1 = ((263.5 - y) - ((yUpper - value) / (yUpper - yLower) * y));
					}
					ret = String.valueOf(y1);// $NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				}
			}
		}
		return ret;
	}
}