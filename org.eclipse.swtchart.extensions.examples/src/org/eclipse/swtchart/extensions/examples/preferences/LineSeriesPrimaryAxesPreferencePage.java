/*******************************************************************************
 * Copyright (c) 2017, 2022 Lablicate GmbH.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 * Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.swtchart.extensions.examples.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swtchart.extensions.examples.Activator;
import org.eclipse.swtchart.extensions.preferences.PreferenceSupport;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class LineSeriesPrimaryAxesPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public LineSeriesPrimaryAxesPreferencePage() {

		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setTitle("Line series axis settings");
		setDescription("");
	}

	@Override
	public void createFieldEditors() {

		addField(new SpacerFieldEditor(getFieldEditorParent()));
		addField(new LabelFieldEditor("X-Axis", getFieldEditorParent()));
		addField(new SpacerFieldEditor(getFieldEditorParent()));
		//
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_TITLE, "Primary X-Axis Title:", getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_DESCRIPTION, "Primary X-Axis Description:", getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_DECIMAL_FORMAT_PATTERN, "Primary X-Axis Format Pattern:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_DECIMAL_FORMAT_LOCALE, "Primary X-Axis Format Locale:", PreferenceSupport.LOCALES, getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_COLOR, "Primary X-Axis Color:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_POSITION, "Primary X-Axis Position:", PreferenceSupport.AXIS_POSITIONS, getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_VISIBLE, "Primary X-Axis Visible", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_GRID_LINE_STYLE, "Primary X-Axis Grid Line Style:", PreferenceSupport.LINE_STYLES, getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_ENABLE_LOG_SCALE, "Primary X-Axis Enable Log Scale", getFieldEditorParent()));
		addField(new DoubleFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_LOG_SCALE_BASE, "Primary X-Axis Log Scale Base", getFieldEditorParent()));
		addField(new IntegerFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_EXTRA_SPACE_TITLE, "Primary X-Axis Extra Space Title:", getFieldEditorParent()));
		//
		addField(new SpacerFieldEditor(getFieldEditorParent()));
		addField(new LabelFieldEditor("Y-Axis", getFieldEditorParent()));
		addField(new SpacerFieldEditor(getFieldEditorParent()));
		//
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_TITLE, "Primary Y-Axis Title:", getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_DESCRIPTION, "Primary Y-Axis Description:", getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_DECIMAL_FORMAT_PATTERN, "Primary Y-Axis Format Pattern:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_DECIMAL_FORMAT_LOCALE, "Primary Y-Axis Format Locale:", PreferenceSupport.LOCALES, getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_COLOR, "Primary Y-Axis Color:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_POSITION, "Primary Y-Axis Position:", PreferenceSupport.AXIS_POSITIONS, getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_VISIBLE, "Primary Y-Axis Visible", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_GRID_LINE_STYLE, "Primary Y-Axis Grid Line Style:", PreferenceSupport.LINE_STYLES, getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_ENABLE_LOG_SCALE, "Primary Y-Axis Enable Log Scale", getFieldEditorParent()));
		addField(new DoubleFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_LOG_SCALE_BASE, "Primary Y-Axis Log Scale Base", getFieldEditorParent()));
		addField(new IntegerFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_EXTRA_SPACE_TITLE, "Primary Y-Axis Extra Space Title:", getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {

	}
}