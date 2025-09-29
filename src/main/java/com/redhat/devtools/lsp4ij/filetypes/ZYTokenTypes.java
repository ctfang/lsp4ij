package com.redhat.devtools.lsp4ij.filetypes;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.intellij.psi.xml.XmlTokenType;
import com.intellij.lang.Language;

public interface ZYTokenTypes {
    Language LANGUAGE = ZYLanguage.INSTANCE;

    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

    IElementType LINE_COMMENT = new IElementType("ZY_LINE_COMMENT", LANGUAGE);
    IElementType BLOCK_COMMENT = new IElementType("ZY_BLOCK_COMMENT", LANGUAGE);
    IElementType STRING = new IElementType("ZY_STRING", LANGUAGE);
    IElementType NUMBER = new IElementType("ZY_NUMBER", LANGUAGE);
    IElementType VARIABLE = new IElementType("ZY_VARIABLE", LANGUAGE);
    IElementType KEYWORD = new IElementType("ZY_KEYWORD", LANGUAGE);
    IElementType IDENTIFIER = new IElementType("ZY_IDENTIFIER", LANGUAGE);
    IElementType FUNCTION = new IElementType("ZY_FUNCTION", LANGUAGE);
    IElementType TYPE = new IElementType("ZY_TYPE", LANGUAGE);
    IElementType ANNOTATION = new IElementType("ZY_ANNOTATION", LANGUAGE);
    IElementType OPERATOR = new IElementType("ZY_OPERATOR", LANGUAGE);
}

