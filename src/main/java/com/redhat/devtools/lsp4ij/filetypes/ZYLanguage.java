/*******************************************************************************
 * Copyright (c) 2025 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package com.redhat.devtools.lsp4ij.filetypes;

import com.intellij.lang.Language;

/**
 * Language definition for ZY language.
 */
public class ZYLanguage extends Language {

    /**
     * Singleton instance of the ZY language.
     */
    public static final ZYLanguage INSTANCE = new ZYLanguage();

    protected ZYLanguage() {
        super("ZY");
    }
}
