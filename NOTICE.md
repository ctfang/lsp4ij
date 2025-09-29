# NOTICE

This project is based on the upstream project:

- Name: LSP4IJ
- Upstream: https://github.com/redhat-developer/lsp4ij
- License: Eclipse Public License v2.0 (EPL-2.0)

We acknowledge and thank the original authors and contributors of LSP4IJ.

## Additional changes in this fork

Date: 2025-09-29

- Added ZY language file type and mapped the `*.zy` extension.
- Enabled custom icon for ZY files at `src/main/resources/icons/zy-file.svg`.
- Implemented baseline syntax highlighting for ZY:
  - `ZYSimpleLexer`
  - `ZYSyntaxHighlighter`
  - `ZYSyntaxHighlighterFactory`
  - Registered in `META-INF/plugin.xml`
- Highlighting categories include: keywords, strings, numbers, variables, annotations, primitive types, class-like names, function names, operators.
- Keyword and operator sets were aligned with `origami.tmLanguage.json` in repo root.

No other modifications were intended to change upstream behavior.

## License

This fork and all modifications are provided under the same license as upstream: EPL-2.0. See `LICENSE`.
