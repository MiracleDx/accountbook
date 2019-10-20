### 提交日志基本格式
```
<type>(<scope>): <subject>
<空行>
<body>
<空行>
<footer>
```
## header
第一行不能超过70个字符，第二行总是空白，其他行应该包含80个字符。类型和范围应该总是小写
## type
- feat（用户的新功能，而不是构建脚本的新功能）
- fix（对用户的错误修复，而不是构建脚本的修复）
- docs（对文档的更改）
- style（代码格式化改动，缺少分号等）
- refactor（重构代码，例如重命名变量）
- test（新增或修改测试用例）
- chore（更新咕噜任务等;没有生产代码的变化)
## scope
这个取值可以是空，通常用于指明修改内容的范围
## subject
用于概括一次提交行为囊括的内容
- 时态方面使用一般现在时，不要用过去时态。虽然查看 log 时，log 内容本身都发生在过去，然而对于主题来说，使用现在时的时态更简洁明确，并且更易达成一致性
- 句式使用祈使句式。即一般情况不要增加主语。因为在绝大情况下，主语都是作者「我」
- 句尾无需结束标点；如果使用英语，则句首同样无需大写。同样是因为主题（或称标题）本身不用形成完整的句子
## body
日志的内容主体 body 用来描述详细的提交内容，可写可不写
## footer 
日志的内容页脚 footer 用来描述一些补充信息，可写可不写。

例如修复了 GitHub 平台上某个 issue 或多个 issue：
```
Closes #120

Closes #123, #245, #992
```