/*******************************************************************************
 * Copyright (c) 2025
 *
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-v20.html
 ******************************************************************************/
package com.redhat.devtools.lsp4ij.zy;

import com.intellij.openapi.project.Project;
import com.redhat.devtools.lsp4ij.server.OSProcessStreamConnectionProvider;
import com.redhat.devtools.lsp4ij.server.StreamConnectionProvider;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * StreamConnectionProvider that launches zy-lsp with proper defaults.
 */
public class ZYStreamConnectionProvider extends OSProcessStreamConnectionProvider implements StreamConnectionProvider {

    public ZYStreamConnectionProvider(@NotNull Project project) {
        super(
                // Command line resolved by IDE macros ($PROJECT_DIR$)
                com.redhat.devtools.lsp4ij.server.definition.launching.CommandUtils.createCommandLine(
                        "zy-lsp -log-level 5 -log-file $PROJECT_DIR$/zy-lsp.log",
                        new HashMap<>(),
                        true
                )
        );
    }
}


