package com.go2wheel.message;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.go2wheel.message.WxInEvent.WxEventType;
import com.go2wheel.message.event.ClickEvent;
import com.go2wheel.message.event.LocationEvent;
import com.go2wheel.message.event.ScanEvent;
import com.go2wheel.message.event.SubscribeEvent;
import com.go2wheel.message.event.UnSubscribeEvent;
import com.go2wheel.message.event.ViewEvent;
import com.go2wheel.message.in.ImageMessage;
import com.go2wheel.message.in.LinkMessage;
import com.go2wheel.message.in.LocationMessage;
import com.go2wheel.message.in.ShortVedioMessage;
import com.go2wheel.message.in.TextMessage;
import com.go2wheel.message.in.VideoMessage;
import com.go2wheel.message.in.VoiceMessage;
import com.go2wheel.message.WxBody.WxMessageType;

@Component
public class WxBodyUtil {

	private ObjectMapper xmlObjectMapper;
	
	
	public <T extends WxBody> String serialize(T wxm) throws JsonProcessingException {
		return xmlObjectMapper.writeValueAsString(wxm);
	}
	
	public WxBody deserialize(String content) throws JsonProcessingException, IOException {
		JsonNode jn = xmlObjectMapper.readTree(content);
		String tp = jn.get("MsgType").asText();
		WxMessageType wxt = WxMessageType.valueOf(tp);
		switch (wxt) {
		case text:
			return xmlObjectMapper.convertValue(jn, TextMessage.class);
		case image:
			return xmlObjectMapper.convertValue(jn, ImageMessage.class);
		case voice:
			return xmlObjectMapper.convertValue(jn, VoiceMessage.class);
		case video:
			return xmlObjectMapper.convertValue(jn, VideoMessage.class);
		case shortvideo:
			return xmlObjectMapper.convertValue(jn, ShortVedioMessage.class);
		case location:
			return xmlObjectMapper.convertValue(jn, LocationMessage.class);
		case link:
			return xmlObjectMapper.convertValue(jn, LinkMessage.class);
		case event:
			String et = jn.get("Event").asText();
			WxEventType wxe = WxEventType.valueOf(et);
			switch (wxe) {
			case subscribe:
				return xmlObjectMapper.convertValue(jn, SubscribeEvent.class);
			case unsubscribe:
				return xmlObjectMapper.convertValue(jn, UnSubscribeEvent.class);
			case SCAN:
				return xmlObjectMapper.convertValue(jn, ScanEvent.class);
			case CLICK:
				return xmlObjectMapper.convertValue(jn, ClickEvent.class);
			case LOCATION:
				return xmlObjectMapper.convertValue(jn, LocationEvent.class);
			case VIEW:
				return xmlObjectMapper.convertValue(jn, ViewEvent.class);
			default:
				break;
			}
		default:
			break;
		}
		return null;
	}
	

	@PostConstruct
	public void pc() {
		JacksonXmlModule module = new JacksonXmlModule();
		xmlObjectMapper = new XmlMapper(module);
		xmlObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		xmlObjectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
	}
}
