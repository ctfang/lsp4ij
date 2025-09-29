package com.redhat.devtools.lsp4ij.filetypes;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class ZYSyntaxHighlighter extends SyntaxHighlighterBase implements ZYTokenTypes {

    public static final TextAttributesKey KEYWORD = createTextAttributesKey("ZY_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING = createTextAttributesKey("ZY_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("ZY_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey LINE_COMMENT_ATTR = createTextAttributesKey("ZY_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT_ATTR = createTextAttributesKey("ZY_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey VARIABLE_ATTR = createTextAttributesKey("ZY_VARIABLE", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final TextAttributesKey IDENTIFIER_ATTR = createTextAttributesKey("ZY_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey FUNCTION_ATTR = createTextAttributesKey("ZY_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey TYPE_ATTR = createTextAttributesKey("ZY_TYPE", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey ANNOTATION_ATTR = createTextAttributesKey("ZY_ANNOTATION", DefaultLanguageHighlighterColors.METADATA);
    public static final TextAttributesKey OPERATOR_ATTR = createTextAttributesKey("ZY_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new ZYSimpleLexer();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType == ZYTokenTypes.KEYWORD) return pack(KEYWORD);
        if (tokenType == ZYTokenTypes.STRING) return pack(STRING);
        if (tokenType == ZYTokenTypes.NUMBER) return pack(NUMBER);
        if (tokenType == ZYTokenTypes.LINE_COMMENT) return pack(LINE_COMMENT_ATTR);
        if (tokenType == ZYTokenTypes.BLOCK_COMMENT) return pack(BLOCK_COMMENT_ATTR);
        if (tokenType == ZYTokenTypes.VARIABLE) return pack(VARIABLE_ATTR);
        if (tokenType == ZYTokenTypes.IDENTIFIER) return pack(IDENTIFIER_ATTR);
        if (tokenType == ZYTokenTypes.FUNCTION) return pack(FUNCTION_ATTR);
        if (tokenType == ZYTokenTypes.TYPE) return pack(TYPE_ATTR);
        if (tokenType == ZYTokenTypes.ANNOTATION) return pack(ANNOTATION_ATTR);
        if (tokenType == ZYTokenTypes.OPERATOR) return pack(OPERATOR_ATTR);
        return TextAttributesKey.EMPTY_ARRAY;
    }
}


