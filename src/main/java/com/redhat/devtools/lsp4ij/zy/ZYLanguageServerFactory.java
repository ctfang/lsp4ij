/*******************************************************************************
 * Copyright (c) 2025
 *
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-v20.html
 ******************************************************************************/
package com.redhat.devtools.lsp4ij.zy;

import com.intellij.openapi.project.Project;
import com.redhat.devtools.lsp4ij.LanguageServerFactory;
import com.redhat.devtools.lsp4ij.client.LanguageClientImpl;
import com.redhat.devtools.lsp4ij.client.features.LSPClientFeatures;
import com.redhat.devtools.lsp4ij.server.StreamConnectionProvider;
import org.jetbrains.annotations.NotNull;

/**
 * LanguageServerFactory for ZY Language Server.
 */
public class ZYLanguageServerFactory implements LanguageServerFactory {

    @Override
    public @NotNull StreamConnectionProvider createConnectionProvider(@NotNull Project project) {
        return new ZYStreamConnectionProvider(project);
    }

    @Override
    public @NotNull LanguageClientImpl createLanguageClient(@NotNull Project project) {
        // Default client is enough for now
        return LanguageServerFactory.super.createLanguageClient(project);
    }

    @Override
    public @NotNull LSPClientFeatures createClientFeatures() {
        // Use default LSP client features
        return LanguageServerFactory.super.createClientFeatures();
    }
}


