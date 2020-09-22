import com.dusinski.holidaycalendar.model.CalendarEvent;
import com.dusinski.holidaycalendar.model.User;
import com.dusinski.holidaycalendar.repository.CalendarEventRepository;
import com.dusinski.holidaycalendar.services.EmailSenderService;
import com.dusinski.holidaycalendar.services.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {
    @InjectMocks
    private EventService eventService;
    @Mock
    private EmailSenderService emailSenderService;

    @Test
    public void shouldSendAnEmail() {
        //given
        String expectedEmail = "confirmationholiday@gmail.com";
        String emailAddress = "dusiu2@gmail.com";
        String confToken = "testCsrfToken";
        ArgumentCaptor<SimpleMailMessage> emailMessageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);

        //when
        boolean result = eventService.sendEmail(confToken, emailAddress);
        Mockito.verify(emailSenderService, Mockito.times(1)).sendEmail(emailMessageCaptor.capture());

        //then
        SimpleMailMessage sentMessage = emailMessageCaptor.getValue();
        assertThat(result).isTrue();
        assertThat(sentMessage.getTo()).contains(emailAddress);
        assertThat(sentMessage.getFrom()).contains(expectedEmail);
        assertThat(sentMessage.getSubject()).isNotBlank();
        assertThat(sentMessage.getText()).isNotBlank();
        assertThat(sentMessage.getText()).contains(confToken);
    }


    @Test
    public void tokenExist_tokenNull_false() {

        //given
        String confirmationToken = null;

        //EventConfirmationToken eventConfirmationToken = new EventConfirmationToken(new CalendarEvent());
//        eventConfirmationTokenRepository.save(new EventConfirmationToken(new CalendarEvent()));


//        @Autowired
        CalendarEventRepository calendarEventRepository;

        CalendarEvent calendarEvent = new CalendarEvent();
        User testUser = new User();
        testUser.setEmail("dd@ww.ww");

//        calendarEvent.setEventUser(testUser);
        calendarEvent.setTitle("Test");
        calendarEvent.setStart(LocalDate.now());
        calendarEvent.setEnd(LocalDate.now().plusDays(5));
        eventService.saveCalendarEvent(calendarEvent);

        //when
        boolean result = eventService.tokenExist(confirmationToken);

        //then

    }
}
