package com.dusinski.holidaycalendar.serializer;

import com.dusinski.holidaycalendar.model.CalendarEvent;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CalendarEventSerializer extends StdSerializer<CalendarEvent> {
    public CalendarEventSerializer() {
        this(CalendarEvent.class);
    }

    protected CalendarEventSerializer(Class<CalendarEvent> calendarEventClass) {
        super(calendarEventClass);
    }

    @Override
    public void serialize(CalendarEvent calendarEvent, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        String textColor;

        switch(calendarEvent.getEventType()){
            case HOLIDAY:textColor="#FFFF00";break; //yellow
            case SICKNESS:textColor="#0000FF";break;//blue
            case TRAINING:textColor="#FF0000";break;//red
            default:textColor="#00FFFF"; // bright blue
        }

        jgen.writeStartObject();
        jgen.writeStringField("title", calendarEvent.getTitle());
        jgen.writeStringField("start", calendarEvent.getStart().toString());
        jgen.writeStringField("end", calendarEvent.getEnd().toString());
        jgen.writeStringField("color",textColor);
        jgen.writeEndObject();


//        return "CalendarEvent{"+
//                "title: "+title+
//                ", start: "+start+
//                ", end:"+end+
//                "}";
    }

}
