## 分布式解决方案
# skill
## 1：skill-common 先创建common ，包含通用返回类型，code data msg
## 2：skill-mvc-config 包含 统一全局异常处理等
## 3:skill-nacos-client
## 5:skill-admin服务应用监控
## 6：skill-authority-center 鉴权授权中心
jwt 组成的三个部分 header payload signature 且用圆点连接xxx.yyyy.zzzz

header :由两部分(Token 类型，加密算法名称) 组成，并使用Base64编码

payload kv 形式的数据，即你想传递的数据(授权的话就是token信息)

signature ：为了得到签名部分，你必须有编码过的header，编码过的payload，一个密钥，签名算法是header中指定的那个，然后对他们签名即可

screw 生成数据库文档



