package tr.com.bacompany.bacrm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tr.com.bacompany.bacrm.data.entity.Customer;
import tr.com.bacompany.bacrm.data.entity.Proposal;
import tr.com.bacompany.bacrm.data.entity.Work;
import tr.com.bacompany.bacrm.data.entity.leave.EnumLeaveStatus;
import tr.com.bacompany.bacrm.data.entity.leave.EnumLeaveType;
import tr.com.bacompany.bacrm.data.entity.leave.Leave;
import tr.com.bacompany.bacrm.data.entity.leave.LeaveApproveStatus;
import tr.com.bacompany.bacrm.data.entity.timesheet.EnumTimesheetStatus;
import tr.com.bacompany.bacrm.data.entity.timesheet.Timesheet;
import tr.com.bacompany.bacrm.data.entity.timesheet.TimesheetItem;
import tr.com.bacompany.bacrm.data.entity.user.Role;
import tr.com.bacompany.bacrm.data.entity.user.User;
import tr.com.bacompany.bacrm.service.CustomerService;
import tr.com.bacompany.bacrm.service.LeaveService;
import tr.com.bacompany.bacrm.service.ProposalService;
import tr.com.bacompany.bacrm.service.RoleService;
import tr.com.bacompany.bacrm.service.TimesheetService;
import tr.com.bacompany.bacrm.service.UserService;
import tr.com.bacompany.bacrm.service.WorkService;

import java.util.Set;

@Slf4j
@Component
public class ApplicationStartup {
    private final UserService userService;

    private final RoleService roleService;

    private final WorkService workService;

    private final TimesheetService timesheetService;

    private final LeaveService leaveService;

    private final CustomerService customerService;

    private final ProposalService proposalService;

    @Autowired
    public ApplicationStartup(UserService userService, RoleService roleService, WorkService workService, TimesheetService timesheetService,
                              LeaveService leaveService, CustomerService customerService, ProposalService proposalService) {
        this.userService = userService;
        this.roleService = roleService;
        this.workService = workService;
        this.timesheetService = timesheetService;
        this.leaveService = leaveService;
        this.customerService = customerService;
        this.proposalService = proposalService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup() {
        Role role = new Role();
        role.setName("ADMIN");
        role.setDescription("ADMIN DESC");
        role.setCreationDate(1L);
        role = roleService.add(role);
        Role role2 = new Role();
        role2.setName("USER");
        role2.setDescription("USER DESC");
        role2.setCreationDate(1L);
        role2 = roleService.add(role2);
        // ---
        User user = new User();
        user.setEmail("volkan.ulutas@bacompany.com.tr");
        user.setName("Volkan");
        user.setSurname("Ulutaş");
        user.setEnabled(true);
        user.setPassword("123456");
        user.setProfilePicture("profile pic");
        user.setRoles(Set.of(role, role2));
        user = userService.save(user);
        //user.setRoles(Set.of(role));
        // user = userService.save(user);
        // ----
        Work work = new Work();
        work.setPlanningDate(23L);
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
        timesheet.setUser(user);
        timesheet = timesheetService.saveTimesheet(timesheet);
        timesheet.addTimesheetItem(new TimesheetItem(null, 1L, 9, timesheet, work));
        timesheetService.saveTimesheet(timesheet);
        // ----
        Leave leave = new Leave();
        leave.setType(EnumLeaveType.FREE_LEAVE);
        leave.setStatus(EnumLeaveStatus.WAITING);
        leave.setUser(user);
        leave.setStartDate(1);
        leave.setEndDate(2);
        leave.setDefinition("açıklama");
        leave = leaveService.add(leave);
        LeaveApproveStatus leaveApproveStatus = new LeaveApproveStatus();
        leaveApproveStatus.setStatus(EnumLeaveStatus.APPROVED);
        leaveApproveStatus.setLeave(leave);
        leave.setLeaveApproveStatus(leaveApproveStatus);
        leave = leaveService.add(leave);
        // ----
        Customer customer = new Customer();
        customer.setName("Medical Park");
        customer.setAddress("Medical Park Keçiören/Ankara");
        customer.setTelephone("03123212121");
        customer.setDefinition("Hastane");
        customer = customerService.add(customer);
        // ----
        Proposal proposal = new Proposal();
        proposal.setDefinition("proposalDef");
        proposal.setDate(System.currentTimeMillis());
        proposal.setProposalId("BA-001");
        proposal.setCustomer(customer);
        proposal = proposalService.add(proposal);
        customer.setProposals(Set.of(proposal));
        customerService.add(customer);


        log.info("Data initialized.");



    }
}
