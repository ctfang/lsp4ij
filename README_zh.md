# 项目说明（中文）

本仓库基于上游开源项目 redhat-developer/lsp4ij 二次开发，继续遵循 EPL-2.0 许可证，并保留上游版权与许可信息。上游地址与许可证见 NOTICE.md 与 LICENSE。

## 新增/调整内容

- `*.zy` 文件类型与图标：关联到 `ZY` 文件类型，图标位于 `src/main/resources/icons/zy-file.svg`。
- 基础语法高亮（内置）：实现 `ZYSimpleLexer`、`ZYSyntaxHighlighter`、`ZYSyntaxHighlighterFactory`，覆盖：
  - 关键字（参考根目录 `origami.tmLanguage.json`）
  - 字符串与转义、数字
  - 变量 `$name`
  - 注解 `@Name`
  - 类型：内置原始类型与以大写开头的类名
  - 函数名：标识符后紧跟 `(` 视为函数
  - 运算符：===、!==、==、!=、<=、>=、->、::、??、&&、||、+=、-=、_=、/=、%= 及单字符 + - _ / % = < > . ?

> 说明：当前为轻量内置 Lexer 方案，未依赖 TextMate；如需更细粒度高亮可后续迭代。

## 使用与构建

- 环境：IntelliJ IDEA 2023.3+，JDK 17+
- 开发与调试：`./gradlew runIde` 启动沙箱 IDE，打开 `*.zy` 查看图标与高亮
- 打包：`./gradlew buildPlugin`，产物位于 `build/distributions`

## 许可与致谢

- 许可证：EPL-2.0，详见 `LICENSE`
- 上游项目与本仓库的新增变更、致谢列表：见 `NOTICE.md`
