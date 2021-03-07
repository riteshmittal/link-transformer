package com.aem.community.core.services;

import java.util.Map;

import org.apache.sling.rewriter.Transformer;
import org.apache.sling.rewriter.TransformerFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(property = { "pipeline.type=linkrewriter" }, service = { TransformerFactory.class })
public class LinkRewriterDemo implements TransformerFactory {
	private static final Logger log = LoggerFactory.getLogger(EventListenerNotWorkingExample.class);

	@Activate
	protected void activate(Map<String, Object> properties) {
		log.info("Activating service");
	}

	@Deactivate
	protected void deactivate(ComponentContext context) {
		log.info("Deactivating service");
	}

	public Transformer createTransformer() {
		return new TransformerDemo();
	}

}