package com.marketplace.controller;

import com.marketplace.dto.*;
import com.marketplace.exception.CustomEntityNotFoundException;
import com.marketplace.repository.AdminRepository;
import com.marketplace.security.UserPrinciple;
import com.marketplace.service.CompanyService;
import com.marketplace.service.AdminService;
import com.marketplace.service.EmployeeService;
import com.marketplace.service.MediaService;
import com.marketplace.util.HttpStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.marketplace.util.HttpStatusHelper.success;

//controller => service => repository

@RestController
public class TestController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    HttpStatusHelper httpStatusHelper;

    @Autowired
    AdminRepository adminRepository;

    @GetMapping("/getProfile")
    public UserInfoDto getProfile(OAuth2Authentication auth2Authentication) throws CustomEntityNotFoundException {
        UserPrinciple userPrinciple = (UserPrinciple) auth2Authentication.getPrincipal();

        String email = userPrinciple.getUsername();
        Admin admin = adminRepository.findByEmail(email).orElseThrow(() -> new CustomEntityNotFoundException(Admin.class));

        UserInfoDto infoDto = new UserInfoDto();
        infoDto.setEmail(admin.getEmail());
        infoDto.setAge(admin.getAge());
        infoDto.setName(admin.getName());
        return infoDto;
    }

    @PreAuthorize("hasRole('ROLE_DEFAULT')")
    @GetMapping("/userAccess")
    public String getUserAccess() {
        return "user";
    }

//    ROLE_ADMIN > ROLE_DEFAULT

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/adminAccess")
    public String getAdminAccess() {
        return "admin";
    }

    @PostMapping("/addPicture")
    public ResponseEntity<Object> addPictureToEmployee(@RequestParam String fileName,
                                       @RequestParam Long employeeId,
                                       @RequestParam String type) {
        try {
            return success("ok", mediaService.addMediaToEmployee(fileName, employeeId, type));
        } catch (Exception e) {
            return httpStatusHelper.commonErrorMethod(e);
        }
    }

    @PostMapping("/addHobby")
    public ResponseEntity<Object> addHobbyToEmployee(@RequestBody EmployeeHobbyDto employeeHobbyDto) {
        return employeeService.addHobbyToEmployee(employeeHobbyDto);
    }

    @PostMapping("/createCompany")
    public String createCompany(@RequestBody CompanyDto companyDto) {
        return companyService.createCompany(companyDto);
    }

////    creezi un employee John Doe catre compania Euroins by Id
//    @PostMapping("/createEmployee")
//    public ResponseEntity<Object> createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
//        try {
//            return success("employee", employeeService.createEmployeeAndAssignToCompany(createEmployeeDto));
//        } catch (Exception e) {
//
//        }
//    }

    @GetMapping("/getCompanyByName")
    public ResponseEntity<Object> getCompanyByName(@RequestParam String companyName)
            throws Exception {
        return companyService.getCompanyByName(companyName);
    }


    @GetMapping("/publicEndpoint")
    public String getPublicEndpoint() {
        return "public";
    }

//    controller -> service -> repository
    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) throws Exception {
        try {
            return success("employee ",
                    employeeService.getEmployeeById(id));
        } catch (Exception e) {
            return httpStatusHelper.commonErrorMethod(e);
        }
    }

    @GetMapping("/getAll")
    public List<AdminDto> getAllAdmins() {
        return adminService.getAll();
    }

    @PostMapping("/register")
    public String addAdmin(@RequestBody AdminDto adminDto) {
        return adminService.addAdmin(adminDto);

    }

    @PostMapping("/get-admins")
    public AdminResponseDto getPaginatedAdmins(@RequestBody PagingDto pagingDto) {
        int page = pagingDto.getPage();
        int size = pagingDto.getSize();

        int firstResults = page * size;
        int maxResults = firstResults + size;

        List<Admin> Admins = adminRepository.getPaginatedAdmins(firstResults, maxResults);
        long totalAdmins = adminRepository.getTotalAdmins();

        AdminResponseDto responseDto = new AdminResponseDto();
        responseDto.setAdmins(admins);
        responseDto.setFrom(firstResults);
        responseDto.setTo(maxResults);
        responseDto.setTotalAdmins(totalAdmins);

        return responseDto;
    }

    @GetMapping("/test")
    public String getTest() {
        return "Ok";
    }

    @GetMapping("/add")
    public String addMethod(@RequestParam String value) {

        return value;
    }

    @GetMapping("/Admin/{id}")
    public int sendAdmin(@PathVariable int id) {
        return id;
    }

    @PostMapping("/catalog")
    public String addToCatalog(@RequestBody AdminDto adminDto) {
        System.out.println(adminDto.getName());
        System.out.println(adminDto.getAge());

        return "Ok";
    }

}
