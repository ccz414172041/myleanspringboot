package com.ccz.canaldemo.handler;

import com.alibaba.fastjson.JSON;

import com.ccz.canaldemo.constants.BinlogConstants;
import com.ccz.canaldemo.es.EsRestClient;
import com.ccz.canaldemo.transfer.DataConvertFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 消息处理器
 * </p>
 *
 * @package: com.xkcoding.mq.kafka.handler
 * @description: 消息处理器
 * @author: yangkai.shen
 * @date: Created in 2019-01-07 14:58
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Component
@Slf4j
public class MessageHandler {
    @Autowired
    DataConvertFactory dataConvertFactory;
    @Autowired
    EsRestClient esRestClient;
    @Value("${hostAndPort}")
    private String hostAndPort;

    @KafkaListener(topics = {"example","example_2"}, containerFactory = "ackContainerFactory")
    public void handleMessage(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            log.info("收到消息Str: {}", message);
            Map<String, Object> map = JSON.parseObject(message);
            List<Map<String, String>> dataList = (List<Map<String, String>>) map.get(BinlogConstants.BINLOG_DATA_KEY);
            if (dataList != null) {
                log.info("接受行数据:{}", JSON.toJSONString(dataList));
                String table = (String) map.get(BinlogConstants.BINLOG_TBL_KEY);
                // 进行格式转换的数据
                Map<String, String> params = dataConvertFactory.transferData(dataList.get(0), table);
                String type = (String) map.get(BinlogConstants.BINLOG_TYPE_KEY);
                ArrayList<Object> list = new ArrayList<>();
                esRestClient.buildClient(hostAndPort);
                switch (type) {
                    case BinlogConstants.INSERT_EVENT:
                        String doc = esRestClient.insertDocument(table, "_doc", params);
                        log.info("doc_id:{}", doc);
                        break;
                    case BinlogConstants.UPDATE_EVENT:
                        log.info("update---");
                        break;
                    case BinlogConstants.DELETE_EVENT:
                        log.info("delete");
                        break;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }
}
