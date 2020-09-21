import com.dusinski.holidaycalendar.model.CalendarEvent;
import com.dusinski.holidaycalendar.model.EventConfirmationToken;
import com.dusinski.holidaycalendar.model.User;
import com.dusinski.holidaycalendar.repository.CalendarEventRepository;
import com.dusinski.holidaycalendar.repository.EventConfirmationTokenRepository;
import com.dusinski.holidaycalendar.services.EventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;


public class EventServiceTest {


//    @Autowired
//    private EventConfirmationTokenRepository eventConfirmationTokenRepository;
//    @Autowired
//    private EventService  eventService;



    @Test
    public void tokenExist_tokenNull_false(){

        //given
        String confirmationToken=null;

        EventService  eventService =  new EventService();

        //EventConfirmationToken eventConfirmationToken = new EventConfirmationToken(new CalendarEvent());
//        eventConfirmationTokenRepository.save(new EventConfirmationToken(new CalendarEvent()));


//        @Autowired
        CalendarEventRepository calendarEventRepository;

        CalendarEvent calendarEvent =new CalendarEvent();
        User testUser =new User();
        testUser.setEmail("dd@ww.ww");

        calendarEvent.setEventUser(testUser);
        calendarEvent.setTitle("Test");
        calendarEvent.setStart(LocalDate.now());
        calendarEvent.setEnd(LocalDate.now().plusDays(5));
        eventService.saveCalendarEvent(calendarEvent);

        //when
        boolean result = eventService.tokenExist(confirmationToken);

        //then

        Assertions.assertFalse(result);

    }
}
