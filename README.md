# Protocol
类Json通用传输协议（Emulate the Json universal transport protocol）

##1. 协议结构

|Length|definition|remark| |
|----|-----|-----|-----|
|2|Command|命令（描述此次通信代表做什么事情）| 协议头 \ |
|2|Version|版本（描述这次命令的版本）| 协议头 \ |
|2|Total packed|数据段总包数（描述这次命令的版本）| \ |
|2|Code index|字段索引（描述字段对应的索引）||
|1|Type index|类型索引（描述类型对应的索引）||
|2|Data Length|该字段用于描述此数据段中Data的长度||
|变长|Data|版本（描述这次命令的版本）||

