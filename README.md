# Protocol for Java
## 简介
> 该协议主要用于对象与二进制流的序列化和反序列化，在物联网快速发展的时代，快速的迭代是发展的根本，但是在不断的迭代的过程中开发人员对产品的理解也在不断的升级，这就造成在协议的制定过程中虽然规则渐渐完善，但是硬件产品发出去后与服务端的接口将在也不能被修改，后端项目越往后走可维护性越差最终导致这个项目成为了一个硬骨头。

## 协议结构

- Herder(协议头)
> 协议头中包含命令和版本两个属性，`Command`表示此次通信代表做什么事情；命令索引全局唯一不能重复，而`Version`表示这个这次命令的版本号；版本号全局不唯一，在此次命令下唯一，通过命令和版本来决定某个协议。

|Length|definition|remark|
|----|-----|:-----|
|1|_Command_|命令（描述此次通信代表做什么事情）|
|1|_Version_|版本（描述这次命令的版本）|

 - PackedList(数据段集合)
> 数据段集合中包含多个数据段，`Total packed`表示数据段的数量

|Length|definition|remark|
|----|-----|:-----|
|1|_Total packed_|数据段总包数（描述数据段的数量）|
 
 - Packed(数据段) 
> 一个数据段表示一个字段，`Code index`字段索引；字段索引全局不唯一，在此次命令下唯一，`Type index`类型索引；类型索引全局唯一

|Length|definition|remark|
|----|-----|:-----|
|1|_Code index_|字段索引（描述字段对应的索引）|
|1|_Type index_|类型索引（描述字段的类型对应的索引）|

- ElementList(元素集合)  
> 元素集合中包含元素个数`Element size`此处将数据段的的数据的存储方式抽象成了数组，如果元素个数为`1`时表示只有一个元素，证明该字段不是一个数组，如果元素个数`>1`时证明该字段是一个数组

|Length|definition|remark|
|----|-----|:-----|
|2|_Element size_|元素个数（描述字段对应的索引）|

- Element(元素)
> `Element`表示数组中的元素；`Element[`i`] Length`表示第`i`个元素的长度；`Element[`i`]`表示元素`n`的数据（元素的大小长度是由元素`n`的`_Element[`i`] Length`指定的）

|Length|definition|remark|
|----|-----|:-----|
|2|_Element[`i`] Length_|元素`i`的长度（描述第`i`个元素的长度）| 
|变长|_Element[`i`]_|元素`i`的数据（元素的大小长度是由元素`i`的`_Element[`i`] Length`指定的）| 
|2|_Element[`n`] Length_|元素`n`的长度（描述这次命令的版本）| 
|变长|_Element[`n`]_|元素`n`的数据（元素的大小长度是由元素`n`的`_Element[`i`] Length`指定的）| 

## 协议支持类型
>因为最初的设计是与C语言程序交互所以兼容了C语言中大部分常用类型

|C|Java|Java `TypeConvert` implements|
|----|-----|:-----|
|`signed char`|`byte`|`SignedChar2byteTypeConvert`| 
|`signed short`|`short`|`SignedShort2shortTypeConvert`| 
|`signed int`|`int`|`SignedInt2integerTypeConvert`| 
|`signed long long`|`long`|`SignedLongLong2longTypeConvert`| 
|`float`|`float`|`FloatTypeConvert`| 
|`double`|`double`|`DoubleTypeConvert`| 
|`0/1`|`boolean`|`BooleanTypeConvert`| 
|`char array`|`String`|`StringTypeConvert`| 
|`unsigned char`|`byte`|`UnsignedChar2byteTypeConvert`| 
|`unsigned short`|`short`|`UnsignedShort2shortTypeConvert`| 
|`unsigned int`|`int`|`UnsignedInt2integerTypeConvert`| 
|`unsigned long long`|`long`|`UnsignedLongLong2longTypeConvert`| 

## 协议架构
![协议架构图-V1][protocol-framework-v1]


## 流程图
### 协议对象转换协议适配对象
> 图1 中详细的描述了协议对象转换为协议适配对象的流程

![协议对象转换协议适配对象][data-protocol-to-data-protocol-v1]
<p align="value">图1 协议对象转换协议适配对象</p>
上一行的兄dei你应该居中你造不，`<center/>`标签无效如果谁知道如何居中告诉我一下

## 后续开发计划
[开发计划](DEVELOPMENT_PLAN.md)

[protocol-framework-v1]:images/protocol-framework-v1.png "协议架构图-V1"
[data-protocol-to-data-protocol-v1]:images/data-protocol-to-data-protocol-v1.png "协议对象转换协议适配对象-V1"