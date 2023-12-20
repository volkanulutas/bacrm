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
import tr.com.bacompany.bacrm.data.entity.user.Department;
import tr.com.bacompany.bacrm.data.entity.user.Role;
import tr.com.bacompany.bacrm.data.entity.user.User;
import tr.com.bacompany.bacrm.service.CustomerService;
import tr.com.bacompany.bacrm.service.DepartmentService;
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

    private final DepartmentService departmentService;

    @Autowired
    public ApplicationStartup(UserService userService, RoleService roleService, WorkService workService, TimesheetService timesheetService,
                              LeaveService leaveService, CustomerService customerService, ProposalService proposalService,
                              DepartmentService departmentService) {
        this.userService = userService;
        this.roleService = roleService;
        this.workService = workService;
        this.timesheetService = timesheetService;
        this.leaveService = leaveService;
        this.customerService = customerService;
        this.proposalService = proposalService;
        this.departmentService = departmentService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup() {
        Department department1 = new Department();
        department1.setName("Yönetim");
        department1.setDescription("Yönetim");
        department1 = departmentService.save(department1);
        Department department2 = new Department();
        department2.setName("Proje Ofisi");
        department2.setDescription("Proje Ofisi");
        department2 = departmentService.save(department2);
        Department department3 = new Department();
        department3.setName("Dizayn Ofisi");
        department3.setDescription("Dizayn Ofisi");
        department3 = departmentService.save(department3);
        Department department4 = new Department();
        department4.setName("Teknik Ofis");
        department4.setDescription("Teknik Ofis");
        department4 = departmentService.save(department4);
        // ---
        Role role = new Role();
        role.setName("ADMIN");
        role.setDescription("ADMIN DESC");
        role.setCreationDate(1L);
        role = roleService.save(role);
        Role role2 = new Role();
        role2.setName("USER");
        role2.setDescription("USER DESC");
        role2.setCreationDate(1L);
        role2 = roleService.save(role2);
        // ---
        User user7 = new User();
        user7.setEmail("yesim.tarimci@bacompany.com.tr");
        user7.setName("Yeşim");
        user7.setSurname("Tarımcı");
        user7.setEnabled(true);
        user7.setPassword("123456");
        user7.setProfilePicture(null);
        user7.setDepartment(department2);
        user7.setTitle("Resmi İşler ve Ruhsatlandırma");
        user7.setBirthdate(System.currentTimeMillis());
        user7.setStartDate(System.currentTimeMillis());
        user7.setCellPhone("123");
        user7.setInternalPhone("123");
        user7.setAddress("İstanbul");
        user7.setRoles(Set.of(role, role2));
        user7 = userService.save(user7);
        //
        User user6 = new User();
        user6.setEmail("dilara.beke@bacompany.com.tr");
        user6.setName("Dilara");
        user6.setSurname("Beke");
        user6.setEnabled(true);
        user6.setPassword("123456");
        user6.setProfilePicture(null);
        user6.setDepartment(department4);
        user6.setTitle("Teknik Ofis Mühendisi");
        user6.setBirthdate(System.currentTimeMillis());
        user6.setStartDate(System.currentTimeMillis());
        user6.setCellPhone("123");
        user6.setInternalPhone("123");
        user6.setAddress("İstanbul");
        user6.setRoles(Set.of(role, role2));
        user6 = userService.save(user6);
        //
        User user5 = new User();
        user5.setEmail("kadriye.yasa@bacompany.com.tr");
        user5.setName("Kadriye");
        user5.setSurname("Yasa");
        user5.setEnabled(true);
        user5.setPassword("123456");
        user5.setProfilePicture(null);
        user5.setDepartment(department3);
        user5.setTitle("Dizayn Ofis Mimarı");
        user5.setBirthdate(System.currentTimeMillis());
        user5.setStartDate(System.currentTimeMillis());
        user5.setCellPhone("123");
        user5.setInternalPhone("123");
        user5.setAddress("İstanbul");
        user5.setRoles(Set.of(role, role2));
        user5 = userService.save(user5);
        //
        User user4 = new User();
        user4.setEmail("nezih.atasoy@bacompany.com.tr");
        user4.setName("Nezih");
        user4.setSurname("Atasoy");
        user4.setEnabled(true);
        user4.setPassword("123456");
        user4.setProfilePicture(null);
        user4.setDepartment(department2);
        user4.setTitle("Proje Koordinatörü");
        user4.setBirthdate(System.currentTimeMillis());
        user4.setStartDate(System.currentTimeMillis());
        user4.setCellPhone("123");
        user4.setInternalPhone("123");
        user4.setAddress("İstanbul");
        user4.setRoles(Set.of(role, role2));
        user4 = userService.save(user4);
        //
        User user3 = new User();
        user3.setEmail("nihal.sanli@bacompany.com.tr");
        user3.setName("Nihal");
        user3.setSurname("Şanlı");
        user3.setEnabled(true);
        user3.setPassword("123456");
        user3.setProfilePicture(null);
        user3.setDepartment(department1);
        user3.setTitle("Genel Müdür Yardımcısı");
        user3.setBirthdate(System.currentTimeMillis());
        user3.setStartDate(System.currentTimeMillis());
        user3.setCellPhone("123");
        user3.setInternalPhone("123");
        user3.setAddress("İstanbul");
        user3.setRoles(Set.of(role, role2));
        user3 = userService.save(user3);
        //
        User user2 = new User();
        user2.setEmail("suayip.altay@bacompany.com.tr");
        user2.setName("Şuayip");
        user2.setSurname("Altay");
        user2.setEnabled(true);
        user2.setPassword("123456");
        user2.setProfilePicture(null);
        user2.setDepartment(department1);
        user2.setTitle("CEO");
        user2.setBirthdate(System.currentTimeMillis());
        user2.setStartDate(System.currentTimeMillis());
        user2.setCellPhone("123");
        user2.setInternalPhone("123");
        user2.setAddress("İstanbul");
        user2.setRoles(Set.of(role, role2));
        user2 = userService.save(user2);
        //
        User user = new User();
        user.setEmail("volkan.ulutas@bacompany.com.tr");
        user.setName("Volkan");
        user.setSurname("Ulutaş");
        user.setEnabled(true);
        user.setPassword("123456");
        user.setProfilePicture(null);
        user.setDepartment(department1);
        user.setTitle("CTO");
        user.setBirthdate(System.currentTimeMillis());
        user.setStartDate(System.currentTimeMillis());
        user.setRoles(Set.of(role, role2));
        user.setCellPhone("123");
        user.setInternalPhone("123");
        user2.setAddress("Ankara");
        user = userService.save(user);


        // ----
        Work work = new Work();
        work.setPlanningDate(System.currentTimeMillis());
        work.setEndDate(System.currentTimeMillis());
        work.setStartDate(System.currentTimeMillis());
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
        timesheet.setWeekStartDate(System.currentTimeMillis());
        timesheet.setUser(user);
        timesheet = timesheetService.saveTimesheet(timesheet);
        timesheet.addTimesheetItem(new TimesheetItem(null, 1L, 9, timesheet, work));
        timesheetService.saveTimesheet(timesheet);
        // ----
        Leave leave = new Leave();
        leave.setType(EnumLeaveType.FREE_LEAVE);
        leave.setStatus(EnumLeaveStatus.WAITING);
        leave.setUser(user);
        leave.setStartDate(System.currentTimeMillis());
        leave.setEndDate(System.currentTimeMillis()+1000);
        leave.setDefinition("açıklama");
        leave = leaveService.save(leave);
        LeaveApproveStatus leaveApproveStatus = new LeaveApproveStatus();
        leaveApproveStatus.setStatus(EnumLeaveStatus.APPROVED);
        leaveApproveStatus.setLeave(leave);
        leave.setLeaveApproveStatus(leaveApproveStatus);
        leave = leaveService.save(leave);
        // ----
        Customer customer = new Customer();
        customer.setName("Medical Park");
        customer.setAddress("Medical Park Keçiören/Ankara");
        customer.setTelephone("03123212121");
        customer.setDefinition("Hastane");
        customer = customerService.save(customer);

        Customer customer2 = new Customer();
        customer2.setName("Medicana Sağlık");
        customer2.setAddress("Medicana Ankara Hastanesi");
        customer2.setTelephone("03123212121");
        customer2.setDefinition("Hastane");
        customer2 = customerService.save(customer2);
        // ----
        Proposal proposal = new Proposal();
        proposal.setDefinition("proposalDef");
        proposal.setDate(System.currentTimeMillis());
        proposal.setProposalId("BA-001");
        proposal.setCustomer(customer);
        proposal = proposalService.save(proposal);

        customer.setProposals(Set.of(proposal));
        customerService.save(customer);

        Proposal proposal2 = new Proposal();
        proposal2.setDefinition("teklif 2 ");
        proposal2.setDate(System.currentTimeMillis());
        proposal2.setProposalId("BA-002");
        proposal2.setCustomer(customer2);
        proposal2 = proposalService.save(proposal2);
        customer.setProposals(Set.of(proposal2));
        customerService.save(customer2);

        log.info("Data initialized.");

    }
}
