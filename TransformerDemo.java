package com.aem.community.core.services;

import java.io.IOException;
import java.util.Optional;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.rewriter.ProcessingComponentConfiguration;
import org.apache.sling.rewriter.ProcessingContext;
import org.apache.sling.rewriter.Transformer;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class TransformerDemo implements Transformer {
	private static final String CONTENT_WE_RETAIL_US_EN = "/content/we-retail/us/en";

	private static final String CONTENT_DAM_WE_RETAIL_EN = "/content/dam/we-retail/en";

	private static final String HREF = "href";

	private ContentHandler handler;
	private SlingHttpServletRequest slingRequest;

	public void startElement(String uri, String name, String qName, Attributes pAttributes) throws SAXException {
		AttributesImpl attributes = new AttributesImpl(pAttributes);

		String hrefTag = attributes.getValue(HREF);

		if (Optional.ofNullable(hrefTag).isPresent()) {

			for (int i = 0; i < attributes.getLength(); i++) {
				if (HREF.equalsIgnoreCase(attributes.getQName(i))) {
					String value = "";
					if (attributes.getValue(i).contains(CONTENT_DAM_WE_RETAIL_EN)) {
						value = attributes.getValue(i).replaceAll(CONTENT_DAM_WE_RETAIL_EN, "https://mycdn.com");

						attributes.setValue(i, value);
						break;

					} else if (attributes.getValue(i).contains(CONTENT_WE_RETAIL_US_EN)) {
						value = attributes.getValue(i).replaceAll(CONTENT_WE_RETAIL_US_EN, "");

						attributes.setValue(i, value);
						break;
					}

				}
			}
		}

		handler.startElement(uri, name, qName, attributes);
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		handler.characters(ch, start, length);
	}

	public void dispose() {
	}

	public void endDocument() throws SAXException {
		handler.endDocument();
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		handler.endElement(uri, localName, qName);
	}

	public void endPrefixMapping(String prefix) throws SAXException {
		handler.endPrefixMapping(prefix);
	}

	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		handler.ignorableWhitespace(ch, start, length);
	}

	public void init(ProcessingContext context, ProcessingComponentConfiguration config) throws IOException {
		slingRequest = context.getRequest();
	}

	public void processingInstruction(String target, String data) throws SAXException {
		handler.processingInstruction(target, data);
	}

	public void setContentHandler(ContentHandler handler) {
		this.handler = handler;
	}

	public void setDocumentLocator(Locator locator) {
		handler.setDocumentLocator(locator);
	}

	public void skippedEntity(String name) throws SAXException {
		handler.skippedEntity(name);
	}

	public void startDocument() throws SAXException {
		handler.startDocument();
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		handler.startPrefixMapping(prefix, uri);

	}

}