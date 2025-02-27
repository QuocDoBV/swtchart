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
package org.eclipse.swtchart.vectorgraphics2d.intermediate.commands;

import java.awt.geom.AffineTransform;

public class TransformCommand extends AffineTransformCommand {

	private final AffineTransform transform;

	public TransformCommand(AffineTransform transform) {

		super(transform);
		this.transform = new AffineTransform(transform);
	}

	public AffineTransform getTransform() {

		return transform;
	}
}