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
package org.eclipse.swtchart.vectorgraphics2d.core;

import java.awt.RenderingHints;
import java.util.HashSet;
import java.util.Set;

/**
 * Hints to control quality settings and choices for vector graphics output.
 */
public abstract class VectorHints {

	protected VectorHints() {

		throw new UnsupportedOperationException();
	}

	/**
	 * Base type of all keys used along with the {@link VectorHints} class to
	 * control algorithm and output choices in the vector graphics output.
	 */
	public static class Key extends RenderingHints.Key {

		private final String description;

		public Key(int privateKey, String description) {

			super(privateKey);
			this.description = description;
		}

		public int getIndex() {

			return intKey();
		}

		@Override
		public boolean isCompatibleValue(Object val) {

			return val instanceof Value && ((Value)val).isCompatibleKey(this);
		}

		@Override
		public String toString() {

			return description;
		}
	}

	/**
	 * Base type of all values used along with the {@link VectorHints} class to
	 * control algorithm and output choices in the vector graphics output.
	 */
	public static class Value {

		private static final Set<String> values = new HashSet<>();

		private synchronized static void register(Value value) {

			String id = value.getId();
			if(values.contains(id)) {
				throw new ExceptionInInitializerError("Duplicate index: " + value.getIndex());
			}
			values.add(id);
		}

		private final Key key;
		private final int index;
		private final String description;

		public Value(Key key, int index, String description) {

			this.key = key;
			this.index = index;
			this.description = description;
			register(this);
		}

		public boolean isCompatibleKey(RenderingHints.Key key) {

			return this.key == key;
		}

		public int getIndex() {

			return index;
		}

		public String getId() {

			return key.getIndex() + ":" + getIndex();
		}

		@Override
		public String toString() {

			return description;
		}
	}

	public static final Key KEY_EXPORT = new Key(0, "Vector export mode");
	public static final Object VALUE_EXPORT_READABILITY = new Value(KEY_EXPORT, 0, "Maximize readability for humans");
	public static final Object VALUE_EXPORT_QUALITY = new Value(KEY_EXPORT, 1, "Maximize render quality");
	public static final Object VALUE_EXPORT_SIZE = new Value(KEY_EXPORT, 2, "Minimize data size");
	public static final Key KEY_TEXT = new Key(1, "Text export mode");
	public static final Object VALUE_TEXT_DEFAULT = new Value(KEY_TEXT, 0, "Keep text");
	public static final Object VALUE_TEXT_VECTOR = new Value(KEY_TEXT, 1, "Convert text to vector shapes");
}
