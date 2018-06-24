# Protocol
简介: 类Json通用传输协议（Emulate the Json universal transport protocol）

## 协议结构
这个协议是由一个协议头和多个数据段组成的，数据段中的数据包含两种：一种是基本类型数据另一种是数组类型数据


- 协议头 

|Length|definition|remark|
|----|-----|:-----|
|2|_Command_|命令（描述此次通信代表做什么事情）|
|2|_Version_|版本（描述这次命令的版本）|
|2|_Total packed_|数据段总包数（描述这次命令的版本）|

- 基本类型数据段 

|Length|definition|remark|
|----|-----|:-----|
|2|_Code index_|字段索引（描述字段对应的索引）|
|1|_Type index_|类型索引（描述类型对应的索引）|
|2|_Element[`1`] Length_|该字段用于描述此数据段中Data的长度|
|变长|_Element[`1`]_|数据（数据的长度是由`Data Length`决定的）| 

- 数组类型数据段  

|Length|definition|remark|
|----|-----|:-----|
|2|_Code index_|字段索引（描述字段对应的索引）|
|1|_Type index_|类型索引（描述类型对应的索引）|
|2|_Data Length_|该字段用于描述此数据段中Data的长度|
|2|_Element[`i`] Length_|元素`i`的长度（描述第`i`个元素的长度）| 
|变长|_Element[`i`]_|元素`i`数据（元素的长度是由元素`i`的长度）| 
|2|_Element[`n`] Length_|元素`n`的长度（描述这次命令的版本）| 
|变长|_Element[`n`]_|元素`n`数据（元素的长度是由元素`i`的长度）| 

## 协议支持类型

1. 基本类型数据:  
基本类型数据包含八种基本类型`byte` `short` `int` `long` `float` `double` `boolean` `string`
2. 数组类型数据:  
基本类型数据包含八种基本类型`byte[]` `short[]` `int[]` `long[]` `float[]` `double[]` `boolean[]` `string[]`

> Tip:
>> 该协议暂时还不支持
