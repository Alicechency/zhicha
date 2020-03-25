package com.ctbri.utils.dataimport.core;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class ESTemplate {

	private Logger log = Logger.getLogger(getClass());

	private GlobalProperty globalProperty = GlobalProperty.newInstance();

	@SuppressWarnings("resource")
	public TransportClient getClient() {
		Settings settings = Settings.builder().put("cluster.name", globalProperty.getEsClusterName()).build();
		TransportClient client = null;
		try {
			client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(
					InetAddress.getByName(globalProperty.getEsIp()), globalProperty.getEsPort()));
		} catch (Exception e) {
			log.error("建立ElasticSearch连接失败", e);
		}
		return client;
	}

	public void batchInsert(List<List<String>> values) {
		TransportClient client = getClient();
		BulkRequestBuilder bulkRequest = null;
		bulkRequest = client.prepareBulk();
		for (List<String> value : values) {
			int categoryname = 4;
			String subtagname = value.get(14);
			if (value.get(13) != null) {
				if (value.get(13).contains("刑事")) {
					categoryname = 0;
				} else if (value.get(13).contains("治安")) {
					categoryname = 1;
				} else if (value.get(13).contains("纠纷")) {
					categoryname = 2;
				} else if (value.get(13).contains("民意")) {
					categoryname = 3;
					subtagname = value.get(13);
				}
			}
			try {
				if (value.get(11) == null || "null".equals(value.get(11)) || value.get(12) == null || "null".equals(value.get(12))) {
					bulkRequest.add(
							client.prepareIndex(globalProperty.getEsDefaultIndex(), "cyterm", value.get(0).toString())
									.setSource(jsonBuilder().startObject().field("id", value.get(0))
											.field("title", value.get(1))
											.field("case_abstract ", value.get(2))
											.field("submiteDatetime", value.get(3))
											.field("author", value.get(4))
											.field("local_substation", value.get(5))
											.field("victim_tel", value.get(6))
											.field("contents", value.get(8))
											.field("identity", value.get(9))
											.field("PT", value.get(10))
											.field("tagename", value.get(13))
											.field("readornot", 1)
											.field("categoryname", categoryname)
											.field("subtagname",subtagname)
											.endObject()));
				} else {
					double lng = Double.parseDouble(value.get(11));
					double lat = Double.parseDouble(value.get(12));
					bulkRequest.add(
							client.prepareIndex(globalProperty.getEsDefaultIndex(), "cyterm", value.get(0).toString())
									.setSource(jsonBuilder().startObject()
											.field("id", value.get(0))
											.field("title", value.get(1))
											.field("case_abstract ", value.get(2))
											.field("submiteDatetime", value.get(3))
											.field("author", value.get(4))
											.field("local_substation", value.get(5))
											.field("victim_tel", value.get(6))
											.field("contents", value.get(8))
											.field("identity", value.get(9))
											.field("PT", value.get(10)).startObject("location_point").field("lat", lat).field("lon", lng).endObject()
											.field("tagename", value.get(13))
											.field("readornot", 1)
											.field("categoryname", categoryname)
											.field("subtagname",subtagname)
											.endObject()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BulkResponse responses = bulkRequest.get();
		if (responses.hasFailures()) {
			Iterator<BulkItemResponse> it = responses.iterator();
			while (it.hasNext()) {
				BulkItemResponse res = (BulkItemResponse) it.next();
				if (res.getFailure() != null)
					log.info(res.getFailureMessage());
			}
		}
	}
}
