package com.elasticsearch.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.elasticsearch.document.Employee;

@Service
public class QueryDSLServiceImpl implements QueryDSLService {
	@Autowired
	private RestHighLevelClient restHighLevelClient;

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;
	
	private Logger logger=LoggerFactory.getLogger(QueryDSLServiceImpl.class);


	@Override
	public Map<String, Object> getPersonByAddress(String address) {
		Map<String,Object> map=new HashMap<>();
		try
		{

			NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders
					.multiMatchQuery(address).field("name").field("email"))
			.build();
			
			SearchHits<Employee> persons = elasticsearchOperations.search(searchQuery, Employee.class,
			IndexCoordinates.of("employee"));
			
			map.put("persons", persons.stream().map(p->p.getContent()));
		}catch(Exception e)
		{
			logger.error("Error while getting person ",e);
		}
		return map;
	}


	@Override
	public Map<String, Object> updateEmployee(String departmentName) {
		Map<String,Object> map=new HashMap<>();
		try
		{

			Map<String,Object> departmentParam=new HashMap<>();
			departmentParam.put("departmentName","New dept" );
			departmentParam.put("departmentInfo","New dept INdo" );
			
			Map<String,Object> paramMap=new HashMap<>();
			paramMap.put("params", departmentParam);
			
 		    UpdateByQueryRequest request = new UpdateByQueryRequest("employee");
		    request.setConflicts("proceed");
		    request.setQuery(QueryBuilders
					.matchQuery("name", "Aman Mate"));
		    
		    request.setScript(  new Script(
                    ScriptType.INLINE, "painless",
                    "ctx._source.departments.add(params.params)",                    
                    paramMap));
		    request.setRefresh(true);
		    try {
		        BulkByScrollResponse bulkResponse = restHighLevelClient.updateByQuery(request, RequestOptions.DEFAULT);
		        long totalDocs = bulkResponse.getTotal();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}catch(Exception e)
		{
			logger.error("Error while getting person ",e);
		}
		return map;
	}

}
