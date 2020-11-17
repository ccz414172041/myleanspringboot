##简介
    canal我理解是基于Mysql binary log的数据同步中间件。mysql作为master，canal模拟slave，发送请求，作为master的Mysql推送binary log给canal，canal自己再解析出来，推送到其他介质。如（rds,es,mq）
