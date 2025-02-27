/*******************************************************************************
 * Copyright (c) 2010, 2019 VectorGraphics2D project.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 * Erich Seifert - initial API and implementation
 * Michael Seifert - initial API and implementation
 *******************************************************************************/
package org.eclipse.swtchart.vectorgraphics2d.intermediate;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import org.eclipse.swtchart.vectorgraphics2d.intermediate.commands.Command;
import org.junit.Test;

public class MutableCommandSequenceTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testIteratorContainsAddedCommands() {

		MutableCommandSequence commands = new MutableCommandSequence();
		Command<?> command = new Command<Object>(null) {
		};
		commands.add(command);
		assertThat(commands, hasItem(command));
	}
}
