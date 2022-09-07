package com.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.elasticsearch.repository")
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration  {

	@Value("${elasticsearch.url}")
	public String elasticsearchUrl;

	@Bean
	@Override
	public RestHighLevelClient elasticsearchClient() {
		final ClientConfiguration config = ClientConfiguration.builder().connectedTo(elasticsearchUrl).build();

		return RestClients.create(config).rest();
	}

	@Bean
	public ElasticsearchOperations elasticsearchOperations() {
		return new ElasticsearchRestTemplate(elasticsearchClient());
	}
}
