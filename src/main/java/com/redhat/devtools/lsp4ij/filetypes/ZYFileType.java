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

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * File type for ZY language files.
 */
public class ZYFileType extends LanguageFileType {

    /**
     * Singleton instance of the ZY file type.
     */
    public static final ZYFileType INSTANCE = new ZYFileType();

    protected ZYFileType() {
        super(ZYLanguage.INSTANCE);
    }

    @Override
    public @NotNull String getName() {
        return "ZY";
    }

    @Override
    public @NotNull String getDescription() {
        return "ZY language file";
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "zy";
    }

    @Override
    public @Nullable Icon getIcon() {
        return IconLoader.getIcon("/icons/zy-file.svg", ZYFileType.class);
    }

}
