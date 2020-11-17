package com.ccz.canaldemo.es;


import com.ccz.canaldemo.config.SortEnum;
import com.google.common.collect.Lists;
import com.meetyou.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @PackageName: com.meetyou.common.data.es
 * @ClassName: EsSearchService
 * @Author: eddia
 * @date: 2019/12/13 11:40
 * @Description: ES-REST 客户端
 * @Corporation: meetyou
 */
@Component
@Slf4j
public class EsRestClient {

    private RestHighLevelClient client;

    /**
     * 初始化client
     * @param address
     */
    public synchronized void buildClient(String address) {
        String[] hostAndPort = address.split(Constants.DEFAULT_SPLIT);
        int length = hostAndPort.length;
        HttpHost[] list = new HttpHost[length];
        if (length > 0) {
            for (int i = 0; i < hostAndPort.length; i++) {
                if (hostAndPort[i].indexOf(Constants.DEFAULT_COLON) == -1) {

                } else {
                    String[] arr = hostAndPort[i].split(Constants.DEFAULT_COLON);
                    HttpHost host = new HttpHost(arr[0], Integer.valueOf(arr[1]));
                    list[i] = host;
                }
            }
        }
        if(client == null){
            client = new RestHighLevelClient(RestClient.builder(list));
        }
    }

    /**
     * 创建索引
     * @return boolean 成功或者失败
     * @param idxName
     * @throws IOException
     */
    public boolean createIdx(final String idxName, String type, String settingFile) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(idxName);
        /*client.indices().putMapping();
        request.mapping()*/
        //Settings setting = Settings;
        XContentBuilder builder = XContentFactory.jsonBuilder(new FileOutputStream(settingFile));
        request.settings(builder);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        if (response.isAcknowledged())
            log.info("创建索引成功");
        boolean flag = response.isAcknowledged();
        client.close();
        return flag;
    }

    /**
     * 创建带mapping的索引
     * @param idxName
     * @param type
     * @param mapping
     * @return
     * @throws IOException
     */
    public boolean createIdxWithMapping(String idxName, String type, String mapping) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(idxName);
        request.mapping(type, mapping, XContentType.JSON);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        if (response.isAcknowledged())
            log.info("创建索引成功");
        boolean flag = response.isAcknowledged();
        client.close();
        return flag;
    }

    /**
     * 删除索引
     * @param idxName
     * @return
     * @throws IOException
     */
    public boolean deleteIdx(final String idxName) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(idxName);
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        if (response.isAcknowledged())
            log.info("删除索引: " + idxName + " 成功");
        boolean flag = response.isAcknowledged();
        return flag;
    }

    /**
     * 关闭rest链接
     * @throws Exception
     */
    public synchronized void close() throws Exception {
        if (client != null) {
            client.close();
        }
    }

    /**
     * 是否存在索引
     * @param idxName
     * @return 是、否
     * @throws Exception
     */
    public boolean existIndex(final String idxName) throws Exception {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(idxName);
        boolean flag = client.indices().exists(request, RequestOptions.DEFAULT);
        return flag;
    }

    /**
     * 获得索引列表
     * @param idxName
     * @return
     * @throws IOException
     */
    public String[] getIndex(String idxName) throws IOException {
        GetIndexRequest request = new GetIndexRequest().indices(idxName);
        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);
        String[] res = response.getIndices();
        return res;
    }

    /**
     * 插入文档信息
     * @param idxName
     * @param type
     * @param map
     * @return
     * @throws IOException
     */
    public String insertDocument(String idxName, String type, Map<String, String> map) throws IOException {
        if(map == null){
            return null;
        }else{
            IndexRequest request = new IndexRequest(idxName, type).source(map);
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            String id = response.getId();
            return id;
        }
    }

    /**
     * 删除文档信息
     * @param idxName
     * @param type
     * @param documentId
     * @return
     * @throws IOException
     */
    public String deleteDocument(String idxName,String type,String documentId) throws IOException{
        DeleteRequest request = new DeleteRequest(idxName, type, documentId);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        String id = response.getId();
        return id;
    }

    /**
     * 更新文档信息
     * @param idxName
     * @param type
     * @param documentId
     * @param map
     * @return
     * @throws IOException
     */
    public String updateDocument(String idxName, String type, String documentId,Map<String, String> map) throws IOException{
        UpdateRequest request = new UpdateRequest(idxName, type, documentId).doc(map);
        request.timeout(TimeValue.timeValueSeconds(3));
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        request.scriptedUpsert(true);
        request.docAsUpsert(true);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        String id = response.getId();
        return id;
    }

    /**
     * 通过名称或者id获取文档信息
     * @param idxName
     * @param type
     * @param id
     * @return
     * @throws IOException
     */
    public Map<String, Object> getDocumentByNameAndTypeWithId(String idxName, String type, String id) throws IOException {
        GetRequest request = null;
        if (StringUtils.isEmpty(type) && StringUtils.isEmpty(id)) {
            request = new GetRequest(idxName, type, id);
        } else {
            request = new GetRequest(idxName, type, id);
        }
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        Map<String, Object> result = response.getSource();
        //client.close();
        return result;
    }

    /**
     * 判断是否存在文档
     * @param idxName
     * @param type
     * @param id
     * @return
     * @throws IOException
     */
    public boolean existsDocument(String idxName, String type, String id) throws IOException {
        GetRequest request = null;
        if (StringUtils.isEmpty(type) && StringUtils.isEmpty(id)) {
            request = new GetRequest(idxName, type, id);
        } else {
            request = new GetRequest(idxName, type, id);
        }
        boolean flag = client.exists(request, RequestOptions.DEFAULT);
        client.close();
        return flag;
    }

    /**
     * 批量插入文档到索引
     * @param idxName
     * @param type
     * @param params
     * @throws IOException
     */
    public void bulkCreateIndexDocuments(String idxName, String type, List<Map<String, String>> params) throws IOException {
        BulkRequest request = new BulkRequest();
        if (params.size() > 0) {
            params.forEach(e -> {
                IndexRequest indexRequest = new IndexRequest(idxName, type);
                request.add(indexRequest.source(e));
            });
        }
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
        if (responses.hasFailures()) {
            List<DocWriteRequest<?>> list = request.requests();
            Iterator<BulkItemResponse> iter = responses.iterator();
            while (iter.hasNext()) {
                BulkItemResponse bir = iter.next();
                if (bir.isFailed()) {
                    String reason = "HTTP status: [" + bir.getFailure().getStatus().getStatus() + "], Cause by: " + bir.getFailureMessage() + ".";
                    log.error(reason);
                    int docIndex = bir.getItemId();
                    IndexRequest ir = (IndexRequest) list.get(docIndex);
                    log.error("RESP DETAIL: [" + ir.source().utf8ToString() + "]");
                }
            }
        }
    }

    public BoolQueryBuilder getBuilder(Integer type, Map<String, List<String>> params) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (type == 0) {
            if (params != null && params.entrySet().size() > 0) {
                for (Map.Entry<String, List<String>> entry : params.entrySet()) {
                    List<String> values = entry.getValue();
                    if (!values.isEmpty() && values.size() > 0) {
                        for (String value : values) {
                            boolQueryBuilder.should(QueryBuilders.matchQuery(entry.getKey(), value));
                        }
                    }
                }
            }
        }
        return boolQueryBuilder;
    }

    /**
     * OR批量查询
     * @param idxName
     * @param params
     * @return
     */
    public List<Map<String, Object>> mutiSearchDocument(String idxName, Map<String, List<String>> params) {
        List<Map<String, Object>> result = new ArrayList<>();
        MultiSearchRequest msRequest = new MultiSearchRequest();
        if (params != null && params.entrySet().size() > 0) {
            for (Map.Entry<String, List<String>> entry : params.entrySet()) {
                List<String> values = entry.getValue();
                if (!values.isEmpty() && values.size() > 0) {
                    for (String value : values) {
                        SearchRequest request = new SearchRequest(idxName);
                        SearchSourceBuilder builder = new SearchSourceBuilder();
                        builder.query(QueryBuilders.matchQuery(entry.getKey(), value));
                        request.source(builder);
                        msRequest.add(request);
                    }
                    try {
                        MultiSearchResponse responses = client.msearch(msRequest, RequestOptions.DEFAULT);
                        Arrays.asList(responses.getResponses()).stream()
                                .map(item -> item.getResponse())
                                .map(response -> response.getHits())
                                .filter(hit -> hit != null)
                                .forEach(hit -> hit.forEach(e->{
                                    Map<String, Object> datas = e.getSourceAsMap();
                                    result.add(datas);
                                }));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    public List<Map<String, Object>> searchDocument(String idxName, String field,String value, String sortField, Integer sortType,Integer length) throws IOException {
        List<Map<String, Object>> result = new ArrayList<>();

        SearchRequest request = new SearchRequest();
        request.indices(idxName);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //AggregationBuilder aggregationBuilder = AggregationBuilders.terms("agg").field("name");
        //builder.query(QueryBuilders.matchQuery(conditionLabel, conditionValue).fuzziness(Fuzziness.AUTO).prefixLength(3).maxExpansions(10)); //设置模糊匹配，前缀长度为5位，最大匹配不超过10位
        builder.query(QueryBuilders.matchQuery(field,value)).from(0).size(length); //设置模糊匹配，前缀长度为5位，最大匹配不超过10位
        builder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        if(sortType == SortEnum.ASC.getValue()){
            builder.sort(new FieldSortBuilder(sortField).order(SortOrder.ASC));
        }else{
            builder.sort(new FieldSortBuilder(sortField).order(SortOrder.DESC));
        }
        //builder.aggregation(aggregationBuilder);
        builder.from(0);
        builder.size(length);
        builder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        request.source(builder);
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();

        SearchHit[] searchHits = hits.getHits();

        log.info("SearchHit:" + searchHits.length);

        for (SearchHit hit : searchHits) {
            Map<String, Object> datas = hit.getSourceAsMap();
            result.add(datas);
        }
        // client.close();
        return result;
    }

    public String termDocumentId(String idxName,String field,String value)throws IOException{
        List<Map<String, Object>> result = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest(idxName);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery(field, value));
        builder.timeout(new TimeValue(60,TimeUnit.SECONDS));
        searchRequest.source(builder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();

        SearchHit[] searchHits = hits.getHits();
        if(searchHits !=null && searchHits.length>0)
            return searchHits[0].getId();
        else{
            return "";
        }
    }

    /**
     * 多条件查询文档
     * @param idxName
     * @param params
     * @param sortField
     * @param sortType
     * @param length
     * @return
     * @throws IOException
     */
    public List<Map<String, Object>> searchDocument(String idxName, Map<String, List<String>> params, String sortField, Integer sortType,Integer length) throws IOException {
        List<Map<String, Object>> result = new ArrayList<>();

        SearchRequest request = new SearchRequest();
        request.indices(idxName);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //AggregationBuilder aggregationBuilder = AggregationBuilders.terms("agg").field("name");
        //builder.query(QueryBuilders.matchQuery(conditionLabel, conditionValue).fuzziness(Fuzziness.AUTO).prefixLength(3).maxExpansions(10)); //设置模糊匹配，前缀长度为5位，最大匹配不超过10位
        builder.query(getBuilder(0, params)).from(0).size(length); //设置模糊匹配，前缀长度为5位，最大匹配不超过10位
        builder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        if(sortType == SortEnum.ASC.getValue()){
            builder.sort(new FieldSortBuilder(sortField).order(SortOrder.ASC));
        }else{
            builder.sort(new FieldSortBuilder(sortField).order(SortOrder.DESC));
        }
        //builder.aggregation(aggregationBuilder);
        builder.from(0);
        builder.size(length);
        builder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        request.source(builder);
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();

        SearchHit[] searchHits = hits.getHits();

        log.info("SearchHit:" + searchHits.length);

        for (SearchHit hit : searchHits) {
            Map<String, Object> datas = hit.getSourceAsMap();
            result.add(datas);
        }
       // client.close();
        return result;
    }

    /**
     * 查询建议词分词
     * @param idxName
     * @param fieldName
     * @param value
     * @param suggestName
     * @throws Exception
     */
    public void termSuggest(String idxName, String fieldName, String value, String suggestName) throws
            Exception {
        SearchRequest request = new SearchRequest(idxName);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.size(0);

        SuggestionBuilder termSuggestionBuilder = SuggestBuilders.termSuggestion(fieldName).analyzer("ik_smart").text(value);

        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion(suggestName, termSuggestionBuilder);
        builder.suggest(suggestBuilder);

        request.source(builder);
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);

        if (RestStatus.OK.equals(searchResponse.status())) {
            // 获取建议结果
            Suggest suggest = searchResponse.getSuggest();
            TermSuggestion termSuggestion = suggest.getSuggestion(suggestName);
            for (TermSuggestion.Entry entry : termSuggestion.getEntries()) {
                log.info("text: " + entry.getText().string());
                for (TermSuggestion.Entry.Option option : entry) {
                    String suggestText = option.getText().string();
                    log.info("   suggest option : " + suggestText);
                }
            }
        }
    }

    /**
     * 查询检索建议词
     * @param idxName
     * @param fieldName
     * @param value
     * @param suggestName
     * @return
     * @throws Exception
     */
    public List<String> completionSuggest(String idxName, String fieldName, String value, String
            suggestName) throws Exception {
        List<String> result = Lists.newArrayList();
        SearchRequest request = new SearchRequest(idxName);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.size(0);

        SuggestionBuilder termSuggestionBuilder = SuggestBuilders.completionSuggestion(fieldName).prefix(value).skipDuplicates(true);

        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion(suggestName, termSuggestionBuilder);
        builder.suggest(suggestBuilder);

        request.source(builder);
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);

        if (RestStatus.OK.equals(searchResponse.status())) {
            // 获取建议结果
            Suggest suggest = searchResponse.getSuggest();
            Suggest.Suggestion termSuggestion = suggest.getSuggestion(suggestName);
            List<CompletionSuggestion.Entry> list = termSuggestion.getEntries();
            for (int i = 0; i < list.size(); i++) {
                List<? extends Suggest.Suggestion.Entry.Option> options = list.get(i).getOptions();
                for (Suggest.Suggestion.Entry.Option op : options) {
                    result.add(op.getText().toString());
                }
            }
        }
        client.close();
        return result;
    }

    public void searchScroll(String idxName,Map<String,String> params){
        SearchScrollRequest request = new SearchScrollRequest();

    }

    public List<Map<String, Object>> queryString(String idxName, String type, String query, String[] fields) {
        List<Map<String, Object>> result = new ArrayList<>();

        SearchRequest request = new SearchRequest();

        request.indices(idxName).types(type);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        Map<String,Float> fieldsMap = new HashMap();
        int length = fields.length;
        for (int i = 0; i < fields.length; i++) {
            fieldsMap.put(fields[i], Float.valueOf(length-i));
        }

        builder.query(QueryBuilders.queryStringQuery(query).fields(fieldsMap));
        builder.from(0);
        builder.size(100);
        builder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        request.source(builder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHits hits = searchResponse.getHits();

        SearchHit[] searchHits = hits.getHits();

        log.info("SearchHit:" + searchHits.length);

        for (SearchHit hit : searchHits) {
            Map<String, Object> datas = hit.getSourceAsMap();
            result.add(datas);
        }
        return result;
    }

}
