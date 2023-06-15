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
package org.eclipse.swtchart.extensions.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swtchart.extensions.core.BaseChart;
import org.eclipse.swtchart.extensions.core.IMouseSupport;
import org.eclipse.swtchart.extensions.core.UserSelection;

public class MouseDownEvent extends AbstractHandledEventProcessor implements IHandledEventProcessor {

	@Override
	public int getEvent() {

		return IMouseSupport.EVENT_MOUSE_DOWN;
	}

	@Override
	public int getButton() {

		return IMouseSupport.MOUSE_BUTTON_LEFT;
	}

	@Override
	public int getStateMask() {

		return SWT.NONE;
	}

	@Override
	public void handleEvent(BaseChart baseChart, Event event) {

		/*
		 * Activate the selection if the user made a single click.
		 */
		baseChart.setClickStartTime(System.currentTimeMillis());
		UserSelection userSelection = baseChart.getUserSelection();
		if(isSingleClick(event)) {
			baseChart.setCursor(baseChart.getDisplay().getSystemCursor(SWT.CURSOR_CROSS));
			userSelection.setStartCoordinate(event.x, event.y);
			userSelection.setSingleClick(true);
		} else {
			userSelection.reset();
			userSelection.setSingleClick(false);
		}
	}
}