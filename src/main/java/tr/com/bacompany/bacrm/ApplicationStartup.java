package tr.com.bacompany.bacrm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tr.com.bacompany.bacrm.data.dto.WorkDto;
import tr.com.bacompany.bacrm.data.dto.timesheet.TimesheetDto;
import tr.com.bacompany.bacrm.data.dto.timesheet.TimesheetItemDto;
import tr.com.bacompany.bacrm.data.dto.user.RoleDto;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.Work;
import tr.com.bacompany.bacrm.data.entity.timesheet.EnumTimesheetStatus;
import tr.com.bacompany.bacrm.data.entity.timesheet.Timesheet;
import tr.com.bacompany.bacrm.data.entity.timesheet.TimesheetItem;
import tr.com.bacompany.bacrm.data.entity.user.Role;
import tr.com.bacompany.bacrm.data.entity.user.User;
import tr.com.bacompany.bacrm.repository.UserRepository;
import tr.com.bacompany.bacrm.service.RoleService;
import tr.com.bacompany.bacrm.service.TimesheetService;
import tr.com.bacompany.bacrm.service.UserService;
import tr.com.bacompany.bacrm.service.WorkService;

import java.util.HashSet;
import java.util.Set;

@Component
public class ApplicationStartup {
    private final UserService userService;

    private final RoleService roleService;

    private final WorkService workService;

    private final TimesheetService timesheetService;

    private final UserRepository userRepository;

    @Autowired
    public ApplicationStartup(UserService userService, RoleService roleService, WorkService workService, TimesheetService timesheetService,
                              UserRepository userRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.workService = workService;
        this.timesheetService = timesheetService;
        this.userRepository = userRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup() {
        Role role = new Role();
        role.setName("ADMIN");
        role.setDescription("ADMIN DESC");
        role.setCreationDate(1L);
        role = roleService.add(role);
        // ---
        User user = new User();
        user.setEmail("volkan.ulutas@bacompany.com.tr");
        user.setName("Volkan");
        user.setSurname("Ulutaş");
        user.setEnabled(true);
        user.setPassword("123");
        user.setProfilePicture("profile pic");
        user = userService.save(user);
        //user.setRoles(Set.of(role));
        // user = userService.save(user);
        System.err.println(user.toString());
        // ----
        Work work = new Work();
        work.setEndDate(2L);
        work.setStartDate(1L);
        work.setName("Render Alınması");
        work.setDefinition("Render Alınması Açıklama");
        work.setWorkloadHour(45);
        work = workService.save(work);
        // adding user many to many.
        work.setUsers(Set.of(user));
        work = workService.save(work);
        // ----
        Timesheet timesheet = new Timesheet();
        timesheet.setStatus(EnumTimesheetStatus.SUBMITTED);

        timesheet.setWeekStartDate(1);

        try {
            timesheet = timesheetService.saveTimesheet(timesheet);

        }catch (Exception e){
            System.err.println(e);
        }

        try {
            timesheet.addTimesheetItem(new TimesheetItem(null, 1L, 9, timesheet, work));
            timesheet.setUser(user);
            timesheetService.saveTimesheet(timesheet);
        }catch (Exception e){
            System.err.println(e);
        }

        System.err.println("fin");
    }
}
