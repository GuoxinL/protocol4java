# Protocol
简介: 类Json通用传输协议（Emulate the Json universal transport protocol）

## 协议结构
这个协议是由一个协议头和多个数据段组成的，数据段中的数据包含两种：一种是基本类型数据另一种是数组类型数据

1. 基本类型数据：
基本类型数据包含八种基本类型`byte` `short` `int` `long` `float` `double` `boolean` `string`
- 协议头 

|Length|definition|remark|
|----|-----|:-----:|
|2|Command|命令（描述此次通信代表做什么事情）|
|2|Version|版本（描述这次命令的版本）|
|2|Total packed|数据段总包数（描述这次命令的版本）|

- 基本类型数据段 

|Length|definition|remark|
|----|-----|:-----:|
|2|Code index|字段索引（描述字段对应的索引）|
|1|Type index|类型索引（描述类型对应的索引）|
|2|Data Length|该字段用于描述此数据段中Data的长度|
|变长|Data|版本（描述这次命令的版本）| 

- 数组类型数据段 

|Length|definition|remark|
|----|-----|:-----:|
|2|Code index|字段索引（描述字段对应的索引）|
|1|Type index|类型索引（描述类型对应的索引）|
|2|Data Length|该字段用于描述此数据段中Data的长度|
|变长|Data|版本（描述这次命令的版本）| 

> Tip:
>> 该协议暂时还不支持

