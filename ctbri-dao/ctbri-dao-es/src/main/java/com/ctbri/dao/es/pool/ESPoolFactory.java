package com.ctbri.dao.es.pool;

import java.net.InetAddress;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

/**
 * ES连接池工厂
 * 
 * @author Hogan
 *
 */
public class ESPoolFactory extends BasePooledObjectFactory<TransportClient> {

	private ESBean esBean;

	@SuppressWarnings("resource")
	@Override
	public TransportClient create() throws Exception {
		TransportClient client = null;
		Settings settings = Settings.builder()
				.put("cluster.name", esBean.getClusterName())
				.put("xpack.security.user", esBean.getXpackName())
				.build();
		try {
			client = new PreBuiltXPackTransportClient(settings).addTransportAddress(
					new InetSocketTransportAddress(InetAddress.getByName(esBean.getIp()), esBean.getPort()));
		} catch (Exception e) {
			client = null;
		}
		return client;
	}

	@Override
	public PooledObject<TransportClient> wrap(TransportClient obj) {
		return new DefaultPooledObject<TransportClient>(obj);
	}

	public ESBean getEsBean() {
		return esBean;
	}

	public void setEsBean(ESBean esBean) {
		this.esBean = esBean;
	}

}
