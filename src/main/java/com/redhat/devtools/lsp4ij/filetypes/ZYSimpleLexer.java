package com.redhat.devtools.lsp4ij.filetypes;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ZYSimpleLexer extends LexerBase implements ZYTokenTypes {
    private CharSequence buffer;
    private int startOffset;
    private int endOffset;
    private int tokenStart;
    private int tokenEnd;
    private IElementType tokenType;

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        this.buffer = buffer;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.tokenStart = startOffset;
        this.tokenEnd = startOffset;
        this.tokenType = null;
        advance();
    }

    @Override
    public int getState() { return 0; }

    @Override
    public @Nullable IElementType getTokenType() { return tokenType; }

    @Override
    public int getTokenStart() { return tokenStart; }

    @Override
    public int getTokenEnd() { return tokenEnd; }

    @Override
    public void advance() {
        if (tokenEnd >= endOffset) { tokenType = null; return; }
        tokenStart = tokenEnd;
        int i = tokenStart;
        // Emit whitespace as a token to keep sequence continuous
        char c = buffer.charAt(i);
        if (Character.isWhitespace(c)) {
            int j = i + 1;
            while (j < endOffset && Character.isWhitespace(buffer.charAt(j))) j++;
            tokenType = com.intellij.psi.TokenType.WHITE_SPACE; tokenStart = i; tokenEnd = j; return;
        }
        c = buffer.charAt(i);
        // Line comment //
        if (c == '/' && i + 1 < endOffset && buffer.charAt(i + 1) == '/') {
            int j = i + 2;
            while (j < endOffset && buffer.charAt(j) != '\n') j++;
            tokenType = LINE_COMMENT; tokenStart = i; tokenEnd = j; return;
        }
        // Block comment /* */
        if (c == '/' && i + 1 < endOffset && buffer.charAt(i + 1) == '*') {
            int j = i + 2;
            while (j + 1 < endOffset && !(buffer.charAt(j) == '*' && buffer.charAt(j + 1) == '/')) j++;
            tokenType = BLOCK_COMMENT; tokenStart = i; tokenEnd = Math.min(endOffset, j + 2); return;
        }
        // String "..." or '...'
        if (c == '"' || c == '\'') {
            char quote = c; int j = i + 1;
            while (j < endOffset) {
                char d = buffer.charAt(j);
                if (d == '\\') { j += 2; continue; }
                if (d == quote) { j++; break; }
                j++;
            }
            tokenType = STRING; tokenStart = i; tokenEnd = Math.min(endOffset, j); return;
        }
        // Number: float or int
        if (Character.isDigit(c)) {
            int j = i + 1; boolean dot = false;
            while (j < endOffset) {
                char d = buffer.charAt(j);
                if (Character.isDigit(d)) { j++; continue; }
                if (d == '.' && !dot) { dot = true; j++; continue; }
                break;
            }
            tokenType = NUMBER; tokenStart = i; tokenEnd = j; return;
        }
        // Variable $name
        if (c == '$') {
            int j = i + 1;
            while (j < endOffset) {
                char d = buffer.charAt(j);
                if (Character.isLetterOrDigit(d) || d == '_') j++; else break;
            }
            tokenType = VARIABLE; tokenStart = i; tokenEnd = j; return;
        }
        // Identifier / keyword / function / type / annotation
        if (Character.isLetter(c) || c == '_') {
            int j = i + 1;
            while (j < endOffset) {
                char d = buffer.charAt(j);
                if (Character.isLetterOrDigit(d) || d == '_') j++; else break;
            }
            String word = buffer.subSequence(i, j).toString();
            boolean startsUppercase = !word.isEmpty() && Character.isUpperCase(word.charAt(0));
            if (isKeyword(word)) tokenType = KEYWORD;
            else if (isPrimitiveType(word)) tokenType = TYPE;
            else {
                tokenType = IDENTIFIER;
                // function: next non-space char is '(' => treat name as function
                int k = j;
                while (k < endOffset && Character.isWhitespace(buffer.charAt(k))) k++;
                if (k < endOffset && buffer.charAt(k) == '(') {
                    tokenType = FUNCTION;
                } else if (startsUppercase) {
                    // Class-like identifiers (e.g., Server, Http) are types
                    tokenType = TYPE;
                }
            }
            tokenStart = i; tokenEnd = j; return;
        }
        // Annotation: @Name
        if (c == '@') {
            int j = i + 1;
            while (j < endOffset) {
                char d = buffer.charAt(j);
                if (Character.isLetterOrDigit(d) || d == '_') j++; else break;
            }
            tokenType = ANNOTATION; tokenStart = i; tokenEnd = j; return;
        }
        // Operators
        if (matchOp(i, "===") || matchOp(i, "!==") || matchOp(i, "==") || matchOp(i, "!=") ||
            matchOp(i, "<=") || matchOp(i, ">=") || matchOp(i, "->") || matchOp(i, "::") ||
            matchOp(i, "??") || matchOp(i, "&&") || matchOp(i, "||") ||
            matchOp(i, "+=") || matchOp(i, "-=") || matchOp(i, "*=") || matchOp(i, "/=") || matchOp(i, "%=") ) {
            tokenType = OPERATOR; tokenStart = i; tokenEnd = i + 2; return;
        }
        // single-char operators from origami: + - * / % = < > . ?
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '=' || c == '<' || c == '>' || c == '.' || c == '?') {
            tokenType = OPERATOR; tokenStart = i; tokenEnd = i + 1; return;
        }
        // Single char tokens
        tokenType = IDENTIFIER; tokenStart = i; tokenEnd = i + 1;
    }

    private boolean isKeyword(String w) {
        return w.matches("(?:(if|else|elseif|while|for|foreach|do|switch|case|default|break|continue|return|try|catch|finally|throw|match|spawn|like|instanceof|namespace|use|function|func|const|var|let|public|private|protected|static|abstract|final|parent|this|self|new|echo|print|include|require|require_once|include_once))");
    }

    private boolean isPrimitiveType(String w) { return w.matches("(int|string|bool|float|array|object|void|null)"); }

    private boolean matchOp(int i, String op) {
        int len = op.length();
        if (i + len > endOffset) return false;
        for (int k = 0; k < len; k++) if (buffer.charAt(i + k) != op.charAt(k)) return false;
        return true;
    }

    @Override
    public @NotNull CharSequence getBufferSequence() { return buffer; }

    @Override
    public int getBufferEnd() { return endOffset; }
}

