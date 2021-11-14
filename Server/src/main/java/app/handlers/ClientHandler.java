package app.handlers;

import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.handlers.implementations.*;
import app.helpers.ObjectMessenger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    protected final Socket s;
    DataInputStream dis;
    DataOutputStream dout;

    private ArrayList<RequestHandler> requestHandlers;

    public ClientHandler(Socket s, DataInputStream dis,
                         DataOutputStream dout) {
        this.s = s;
        this.dis = dis;
        this.dout = dout;

        requestHandlers = new ArrayList<>();
        requestHandlers.add(new LoginHandler());
        requestHandlers.add(new RegistrationHandler());
        requestHandlers.add(new GetUserDataHandler());
        requestHandlers.add(new UpdateUserByPatientHandler());
        requestHandlers.add(new GetDoctorsListHandler());
        requestHandlers.add(new GetAvailableTimeHandler());
        requestHandlers.add(new SetAppointmentHandler());
        requestHandlers.add(new GetScheduleHandler());
        requestHandlers.add(new SendComplaintHandler());
        requestHandlers.add(new GetPatientListHandler());
        requestHandlers.add(new UpdateAppointmentHandler());
        requestHandlers.add(new GetPatientAppointmentHandler());
        requestHandlers.add(new GetUserListHandler());
        requestHandlers.add(new GetCommentListHandler());
        requestHandlers.add(new GetDoctorsAndAppointmentCountHandler());
        requestHandlers.add(new SendMailHandler());
        requestHandlers.add(new DeleteUserHandler());
        requestHandlers.add(new DeleteCommentHandler());
    }

    public void run() {
        System.out.println("Server started");
        try {
            ObjectMessenger om = new ObjectMessenger(s);
            while (true) {

                ClientRequest req = (ClientRequest) om.receiveObject();
                boolean handlerFound = false;
                ServerResponse serverResponse = new ServerResponse();
                for (var handler : requestHandlers) {
                    if (handler.handlerType() == req.getType()) {
                        serverResponse = handler.handleRequest(req);
                        handlerFound = true;
                        break;
                    }else if(handler.handlerType().equals(HANDLER_TYPE.close)){
                        s.close();
                        dis.close();
                        dout.close();
                        return;
                    }
                }

                if (handlerFound) {
                    om.sendObject(serverResponse);
                }
//
//                Schedule schedule=new Schedule();
////                Date todaysDate = new Date();
////                DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
//                schedule.setDate(java.sql.Date.valueOf(LocalDate.now()));
//                schedule.setStartTime(Time.valueOf(LocalTime.of(12, 0 )));
//                schedule.setEndTime(Time.valueOf(LocalTime.of(9, 0 )));
//                schedule.setRoomNumber("213");
//                schedule.setIsWeekend((byte) 0);
//                ScheduleService scheduleService=new ScheduleService();
//                scheduleService.save(schedule);
//                Specialization specialization=new Specialization();
//                specialization.setName("Дерматолог");
//                SpecializationService specializationService=new SpecializationService();
//                specializationService.save(specialization);
//                District district=new District();
//                district.setName("1");
//                DistrictService districtService=new DistrictService();
//                districtService.save(district);

//                DistrictService districtService = new DistrictService();
//                var searchedDistrict = districtService.findById(1);
//
//                ScheduleService scheduleService = new ScheduleService();
//                var searchedSchedule = scheduleService.findById(1);
//
//                SpecializationService specializationService = new SpecializationService();
//                var searchedSpecialization = specializationService.findById(1);
//
//                UserService userService1 = new UserService();
//                var searchedUser = userService1.findById(2);
//
//
//                Doctor doctor = new Doctor();
//                doctor.setUserByUserId(searchedUser);
//                doctor.setScheduleByScheduleId(searchedSchedule);
//                doctor.setSpecializationBySpecializationId(searchedSpecialization);
//                doctor.setDistrictByDistrictId(searchedDistrict);
//                doctor.setExperience(2.0);
//                doctor.setSalary(840.0);
//                DoctorService doctorService1 = new DoctorService();
//                doctorService1.save(doctor);
//                UserService userService1 = new UserService();
//                var searchedUser = userService1.findById(1);
//
//                DistrictService districtService = new DistrictService();
//                var searchedDistrict = districtService.findById(1);
//
//                AddressService addressService = new AddressService();
//                Address address = new Address();
//                address.setName("Минская");
//                address.setHouseNumber(24);
//                address.setFlatNumber(10);
//                address.setDistrictByDistrictId(searchedDistrict);
//                addressService.save(address);
//                var searchedAddress = addressService.findById(1);
//
//                Patient patient = new Patient();
//                patient.setUserByUserId(searchedUser);
//                patient.setAddressByAddressId(searchedAddress);
//                patient.setHeight(172.0);
//                patient.setSex("жен");
//                patient.setWeight(55.0);
//                patientService.save(patient);

//                DoctorService doctorService1 = new DoctorService();
//                var searchedDoctor = doctorService1.findById(1);
//                PatientService patientService = new PatientService();
//                var searchedPatient = patientService.findById(1);
//
//                Appointment appointment1 = new Appointment();
//                appointment1.setDoctorByDoctorId(searchedDoctor);
//                appointment1.setPatientByPatientId(searchedPatient);
////                Date dateDay = new SimpleDateFormat("dd.MM").parse("06.11");
////                Date dateTime = new SimpleDateFormat("hh:mm").parse("19:00");
//                String ddate="06.11";
//                String dtime="19:00";
//                String dd=ddate+" "+dtime;
//                Date dateDate= new SimpleDateFormat("dd.MM hh:mm").parse(dd);
//                dateDate=DateUtils.setYears(dateDate,2021);
//                Timestamp ts=new Timestamp(dateDate.getTime());
//                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM hh:mm");
//                appointment1.setDateTimeOfAppointment(ts);
//                appointment1.setIsVisited((byte) 0);
//
//                AppointmentService appointmentService1 = new AppointmentService();
//                appointmentService1.save(appointment1);

//                PatientService patientService1=new PatientService();
//                UserService userService1 = new UserService();
//                DoctorService doctorService1=new DoctorService();
//
//                Patient patientSitsko=new Patient();
//                var searchedUserSitsko = userService1.findById(4);
//                patientSitsko.setUserByUserId(searchedUserSitsko);
//                patientService1.save(patientSitsko);
//
//                Patient patientEugen=new Patient();
//                var searchedUserEugen=userService1.findById(5);
//                patientEugen.setUserByUserId(searchedUserEugen);
//                patientService1.save(patientEugen);
//
//                Doctor doctorDima=new Doctor();
//                var searchedDima=userService1.findById(6);
//                doctorDima.setUserByUserId(searchedDima);
//                doctorService1.save(doctorDima);
//
//                Doctor doctorLev=new Doctor();
//                var searchedLev=userService1.findById(7);
//                doctorLev.setUserByUserId(searchedLev);
//                doctorService1.save(doctorLev);
//
//                Patient patientKlara=new Patient();
//                var searchedUserKlara=userService1.findById(8);
//                patientKlara.setUserByUserId(searchedUserKlara);
//                patientService1.save(patientKlara);

//                switch (req.getType()) {
//                    case login: {
//                        ServerResponse response;
//
//                        var loginDto = (LoginDto) req.getData();
//                        AuthorizationHandler authorization = new AuthorizationHandler();
//                        response = authorization.authorizeUser(loginDto);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case register: {
//                        ServerResponse response;
//
//                        RegistrationDto registrationDto;
//                        registrationDto = (RegistrationDto) req.getData();
//                        AuthorizationHandler authorization = new AuthorizationHandler();
//                        response = authorization.registerUser(registrationDto);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case getUserData: {
//                        ServerResponse response = new ServerResponse<UserAtm>();
//
//                        var loginDto = (LoginDto) req.getData();
//                        UserService userService = new UserService();
//                        User user = userService.findByLogin(loginDto.getLogin());
//                        UserAtm userAtm = new UserAtm();
//                        userAtm.setId(user.getId());
//                        userAtm.setRole(user.getRole());
//                        userAtm.setFirstName(user.getFirstName());
//                        userAtm.setLogin(user.getLogin());
//                        userAtm.setDateOfBirth(user.getDateOfBirth());
//                        userAtm.setPatronymic(user.getPatronymic());
//                        userAtm.setPhoneNum(user.getPhoneNum());
//                        userAtm.setLastName(user.getLastName());
//                        response.setStatus(true);
//                        response.setData(userAtm);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case updateUserByPatient: {
//                        ServerResponse response = new ServerResponse<String>();
//
//                        UpdateUserByPatientDto updateUserByPatientDto;
//                        updateUserByPatientDto = (UpdateUserByPatientDto) req.getData();
//                        UserService userService = new UserService();
//                        userService.updateUsernameAndPhone(updateUserByPatientDto);
//                        response.setStatus(true);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case getDoctorsList: {
//                        ServerResponse response = new ServerResponse<GetDoctorListAtm>();
//
//                        DoctorService doctorService = new DoctorService();
//                        List<Doctor> doctorList = doctorService.findAll();
//                        List<GetDoctorListAtm> doctorListAtmArrayList = new ArrayList<>();
//                        for (Doctor doctor : doctorList) {
//                            GetDoctorListAtm getDoctorListAtm = new GetDoctorListAtm();
//                            getDoctorListAtm.setId(doctor.getId());
//                            getDoctorListAtm.setFirst_name(doctor.getUserByUserId().getFirstName());
//                            getDoctorListAtm.setLast_name(doctor.getUserByUserId().getLastName());
//                            getDoctorListAtm.setPatronymic(doctor.getUserByUserId().getPatronymic());
//                            getDoctorListAtm.setExperience(doctor.getExperience());
//                            getDoctorListAtm.setRoomNumber(doctor.getScheduleByScheduleId().getRoomNumber());
//                            getDoctorListAtm.setSpecialization(doctor.getSpecializationBySpecializationId().getName());
//                            doctorListAtmArrayList.add(getDoctorListAtm);
//                        }
//                        response.setStatus(true);
//                        response.setData(doctorListAtmArrayList);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case getAvailableTime: {
//                        ServerResponse response = new ServerResponse<GetAvailableTimeListAtm>();
//                        List<GetAvailableTimeListAtm> getAvailableTimeListAtms = new ArrayList<>();
//
//                        var getAvailableTimeDto = (GetAvailableTimeListDto) req.getData();
//                        DoctorService doctorService = new DoctorService();
//                        Doctor doctor = doctorService.findById(getAvailableTimeDto.getId());
//
//                        ScheduleService scheduleService = new ScheduleService();
//                        var searchedSchedule = scheduleService.findById(doctor.getScheduleId());
//
//                        AppointmentService appointmentService = new AppointmentService();
//                        List<Appointment> appointments = appointmentService.findAllByDoctorID(getAvailableTimeDto.getId());
//
//                        Date doctorEndTime = searchedSchedule.getEndTime();
////                        if (appointments.size() == 0) {
//                        Date startDate = new Date();
//                        startDate = DateUtils.setSeconds(startDate, 0);
//                        startDate = DateUtils.setMilliseconds(startDate, 0);
//
//                        for (int i = 0; i < 7; i++) {
//                            Date doctorStartTime = searchedSchedule.getStartTime();
//
//                            if (startDate.getHours() > doctorStartTime.getHours()) {
//                                startDate = DateUtils.setHours(startDate, startDate.getHours());
//                            } else {
//                                startDate = DateUtils.setHours(startDate, doctorStartTime.getHours());
//                            }
//
//                            if (startDate.getMinutes() < 30 && startDate.getMinutes() != 0) {
//                                startDate = DateUtils.setMinutes(startDate, 30);
//                            } else if (startDate.getMinutes() != 0) {
//                                startDate = DateUtils.setMinutes(startDate, 0);
//                                startDate = DateUtils.addHours(startDate, 1);
//                            }
//
//                            while (startDate.getHours() < doctorEndTime.getHours() ||
//                                    (startDate.getHours() == doctorEndTime.getHours() &&
//                                            startDate.getHours() < doctorEndTime.getHours())) { // если надо, то сюда можно добавить проверку на Субботу/Вс
//
//                                boolean timeReserved = false;
//                                for (var appointment : appointments) {
//                                    var appTime = new Date(appointment.getDateTimeOfAppointment().getTime());
//                                    if (appTime.getDate() == startDate.getDate() &&
//                                            appTime.getHours() == startDate.getHours() &&
//                                            appTime.getMinutes() == startDate.getMinutes()) {
//                                        timeReserved = true;
//                                        break;
//                                    }
//                                }
//
//                                if (!timeReserved) {
//                                    GetAvailableTimeListAtm getAvailableTimeListAtm = new GetAvailableTimeListAtm();
//
//                                    getAvailableTimeListAtm.setDate(new SimpleDateFormat("dd.MM").format(startDate));
//                                    getAvailableTimeListAtm.setTime(new SimpleDateFormat("HH:mm").format(startDate));
//
//                                    getAvailableTimeListAtms.add(getAvailableTimeListAtm);
//                                }
//
//                                startDate = DateUtils.addMinutes(startDate, 30);
//                            }
//                            startDate = DateUtils.addDays(startDate, 1);
//                            startDate = DateUtils.setHours(startDate, 0);
//                        }
//                        response.setStatus(true);
//                        response.setData(getAvailableTimeListAtms);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case setAppointment: {
//                        ServerResponse response = new ServerResponse();
//
//                        SetAppointmentDto setAppointmentDto = (SetAppointmentDto) req.getData();
//                        DoctorService doctorService = new DoctorService();
//                        var searchedDoctor = doctorService.findById(setAppointmentDto.getDoctorID());
//                        PatientService patientService = new PatientService();
//                        var searchedPatient = patientService.findPatientByUserID(setAppointmentDto.getUserID());
//
//                        Appointment appointment = new Appointment();
//                        appointment.setDoctorByDoctorId(searchedDoctor);
//                        appointment.setPatientByPatientId(searchedPatient);
//                        String ddate = setAppointmentDto.getDate();
//                        String dtime = setAppointmentDto.getTime();
//                        String dd = ddate + " " + dtime;
//                        Date dateDate = new SimpleDateFormat("dd.MM hh:mm").parse(dd);
//                        dateDate = DateUtils.setYears(dateDate, 2021);
//                        Timestamp ts = new Timestamp(dateDate.getTime());
//                        appointment.setDateTimeOfAppointment(ts);
//                        appointment.setIsVisited((byte) 0);
//
//                        AppointmentService appointmentService = new AppointmentService();
//                        appointmentService.save(appointment);
//
//                        response.setStatus(true);
//                        om.sendObject(response);
//                        break;
//                    }
//                    case getSchedule: {
//                        ServerResponse response = new ServerResponse<GetScheduleListAtm>();
//
//                        List<GetScheduleListAtm> getScheduleListAtms = new ArrayList<>();
//
//                        DoctorService doctorService = new DoctorService();
//                        List<Doctor> doctorList = doctorService.findAll();
//
//                        for (Doctor doctor : doctorList) {
//                            GetScheduleListAtm scheduleListAtm = new GetScheduleListAtm();
//                            UserService userService = new UserService();
//                            var searchedUser = userService.findById(doctor.getUserId());
//
//                            ScheduleService scheduleService = new ScheduleService();
//                            Schedule schedule = scheduleService.findById(doctor.getScheduleId());
//
//                            scheduleListAtm.setFirst_name(searchedUser.getFirstName());
//                            scheduleListAtm.setLast_name(searchedUser.getLastName());
//                            scheduleListAtm.setStartTime(schedule.getStartTime().toString());
//                            scheduleListAtm.setEndTime(schedule.getEndTime().toString());
//                            scheduleListAtm.setRoomNumber(schedule.getRoomNumber());
//                            getScheduleListAtms.add(scheduleListAtm);
//                        }
//
//                        response.setStatus(true);
//                        response.setData(getScheduleListAtms);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case sendComplaint: {
//                        ServerResponse response = new ServerResponse();
//
//                        SendComplaintDto sendComplaintDto;
//                        sendComplaintDto = (SendComplaintDto) req.getData();
//                        ComplaintService complaintService = new ComplaintService();
//                        Complaint complaint = new Complaint();
//                        complaint.setMessage(sendComplaintDto.getMessage());
//                        complaintService.save(complaint);
//                        response.setStatus(true);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case getPatientList: {
//                        ServerResponse response = new ServerResponse<GetPatientListAtm>();
//
//                        GetPatientListDto getPatientListDto = (GetPatientListDto) req.getData();
//
//                        List<GetPatientListAtm> getPatientListAtmList = new ArrayList<>();
//                        DoctorService doctorService = new DoctorService();
//                        Doctor searchedDoctor = doctorService.findAllByUserID(getPatientListDto.getUserID());
//
//                        PatientService patientService = new PatientService();
//                        List<Patient> patientList = patientService.findAll();
//
//                        for (Patient patient : patientList) {
//
//                            AppointmentService appointmentService = new AppointmentService();
//                            List<Appointment> appointments = appointmentService.findAllByPatientIDAndDoctorID(patient.getId(), searchedDoctor.getId());
//                            for (Appointment appointment : appointments) {
//                                GetPatientListAtm getPatientListAtm = new GetPatientListAtm();
//                                UserService userService = new UserService();
//                                var searchedUser = userService.findById(patient.getUserId());
//
//                                AddressService addressService = new AddressService();
//                                Address searchedAddress = addressService.findById(patient.getId());
//
//                                DistrictService districtService = new DistrictService();
//                                District searchedDistrict = districtService.findById(searchedAddress.getDistrictId());
//
//                                String strAddress = searchedAddress.getName();
//                                strAddress.concat(" ");
//                                strAddress.concat(searchedAddress.getHouseNumber().toString());
//                                strAddress.concat(" ");
//                                strAddress.concat(searchedAddress.getFlatNumber().toString());
//                                strAddress.concat(" /");
//                                strAddress.concat(searchedDistrict.getName().toString());
//
//                                getPatientListAtm.setPatientID(patient.getId());
//                                getPatientListAtm.setAppointmentID(appointment.getId());
//                                getPatientListAtm.setFirst_name(searchedUser.getFirstName());
//                                getPatientListAtm.setLast_name(searchedUser.getLastName());
//                                getPatientListAtm.setPatronymic(searchedUser.getPatronymic());
//
//                                getPatientListAtm.setAddress(strAddress);
//                                getPatientListAtm.setSex(patient.getSex());
//
//                                getPatientListAtm.setTime(appointment.getDateTimeOfAppointment().toString());
//                                getPatientListAtmList.add(getPatientListAtm);
//                            }
//                        }
//
//                        response.setStatus(true);
//                        response.setData(getPatientListAtmList);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case updateAppointmentDto: {
//                        ServerResponse response = new ServerResponse<String>();
//
//                        UpdateAppointmentDto updateAppointmentDto;
//                        updateAppointmentDto = (UpdateAppointmentDto) req.getData();
//                        PatientService patientService = new PatientService();
//                        patientService.updateWeightAndHeight(updateAppointmentDto.getWeight(), updateAppointmentDto.getHeight(), updateAppointmentDto.getPatientID());
//                        AppointmentService appointmentService = new AppointmentService();
//                        appointmentService.updateVisit(updateAppointmentDto);
//                        response.setStatus(true);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case getPatientAppointment: {
//                        ServerResponse response = new ServerResponse<GetPatientListAtm>();
//
//                        DoctorAppointmentPatientDto doctorAppointmentPatientDto = (DoctorAppointmentPatientDto) req.getData();
//
//                        DoctorAppointmentPatientAtm doctorAppointmentPatientAtm = new DoctorAppointmentPatientAtm();
//
//                        AppointmentService appointmentService = new AppointmentService();
//                        Appointment appointment = appointmentService.findById(doctorAppointmentPatientDto.getAppointmentID());
//
//                        doctorAppointmentPatientAtm.setPatientID(doctorAppointmentPatientDto.getPatientID());
//                        doctorAppointmentPatientAtm.setDiagnose(appointment.getDiagnosis());
//                        doctorAppointmentPatientAtm.setRecommendation(appointment.getRecommendation());
//                        doctorAppointmentPatientAtm.setReport(appointment.getReport());
//
//                        PatientService patientService = new PatientService();
//                        Patient patient = patientService.findById(doctorAppointmentPatientDto.getPatientID());
//
//                        doctorAppointmentPatientAtm.setHeight(patient.getHeight());
//                        doctorAppointmentPatientAtm.setWeight(patient.getWeight());
//                        doctorAppointmentPatientAtm.setVisited(Boolean.parseBoolean(appointment.getIsVisited().toString()));
//
//                        response.setStatus(true);
//                        response.setData(doctorAppointmentPatientAtm);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case getUserList: {
//                        ServerResponse response = new ServerResponse<GetUserListAtm>();
//
//                        UserService userService = new UserService();
//                        List<User> userList = userService.findAll();
//                        List<GetUserListAtm> userListAtms = new ArrayList<>();
//                        for (User user : userList) {
//                            GetUserListAtm getUserListAtm = new GetUserListAtm();
//                            getUserListAtm.setUserID(user.getId());
//                            getUserListAtm.setFirst_name(user.getFirstName());
//                            getUserListAtm.setLast_name(user.getLastName());
//                            getUserListAtm.setPatronymic(user.getPatronymic());
//                            getUserListAtm.setLogin(user.getLogin());
//                            getUserListAtm.setRole(user.getRole());
//                            getUserListAtm.setPhoneNum(user.getPhoneNum());
//                            getUserListAtm.setStatus(user.getStatus());
//                            userListAtms.add(getUserListAtm);
//                        }
//                        response.setStatus(true);
//                        response.setData(userListAtms);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case getCommentList: {
//                        ServerResponse response = new ServerResponse<GetCommentListAtm>();
//
//                        ComplaintService complaintService = new ComplaintService();
//                        List<Complaint> complaints = complaintService.findAll();
//                        List<GetCommentListAtm> commentListAtms = new ArrayList<>();
//                        for (Complaint complaint : complaints) {
//                            GetCommentListAtm getCommentListAtm = new GetCommentListAtm();
//                            getCommentListAtm.setCommentID(complaint.getId());
//                            getCommentListAtm.setMessage(complaint.getMessage());
//                            commentListAtms.add(getCommentListAtm);
//                        }
//                        response.setStatus(true);
//                        response.setData(commentListAtms);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case getDoctorsAndAppointmentCountDto: {
//                        ServerResponse response = new ServerResponse<GetDoctorsAndAppointmentCountAtm>();
//
//                        DoctorService doctorService = new DoctorService();
//                        List<Doctor> doctorList = doctorService.findAll();
//
//                        List<GetDoctorsAndAppointmentCountAtm> getDoctorsAndAppointmentCountAtmList = new ArrayList<>();
//                        GetDoctorsAndAppointmentCountAtm getDoctorsAndAppointmentCountAtm = new GetDoctorsAndAppointmentCountAtm();
//                        for (Doctor doctor : doctorList) {
//
//                            UserService userService = new UserService();
//                            User user = userService.findById(doctor.getUserId());
//
//                            AppointmentService appointmentService = new AppointmentService();
//                            List<Appointment> appointmentList = appointmentService.findAllByDoctorID(doctor.getId());
//
//                            String name = user.getFirstName();
//                            name.concat(" ");
//                            name.concat(user.getLastName());
//                            name.concat(" ");
//                            name.concat(user.getPatronymic());
//                            getDoctorsAndAppointmentCountAtm.setDoctorName(name);
//                            getDoctorsAndAppointmentCountAtm.setAppointmentsCount(appointmentList.size());
//                        }
//                        getDoctorsAndAppointmentCountAtmList.add(getDoctorsAndAppointmentCountAtm);
//                        response.setStatus(true);
//                        response.setData(getDoctorsAndAppointmentCountAtmList);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case sendMail: {
//                        UserService userService = new UserService();
//                        List<User> users = userService.findAll();
//                        List<String> emails = new ArrayList<>();
//                        for (User user : users) {
//                            emails.add(user.getLogin());
//                        }
//                        EmailService emailService = new EmailService();
//                        SendMailDto sendMailDto = (SendMailDto) req.getData();
//                        emailService.send(sendMailDto.getText(), emails);
//
//                        ServerResponse response = new ServerResponse<String>();
//                        response.setData("Успешно");
//                        response.setStatus(true);
//                        break;
//                    }
//                    case deleteUser: {
//                        ServerResponse response = new ServerResponse<String>();
//
//                        DeleteUserDto deleteUserDto;
//                        deleteUserDto = (DeleteUserDto) req.getData();
//                        UserService userService = new UserService();
//                        userService.updateUserStatus(deleteUserDto.getUserID());
//                        response.setStatus(true);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case deleteComment: {
//                        ServerResponse response = new ServerResponse<String>();
//
//                        DeleteCommentDto deleteCommentDto;
//                        deleteCommentDto = (DeleteCommentDto) req.getData();
//                        ComplaintService complaintService = new ComplaintService();
//                        complaintService.deleteByID(deleteCommentDto.getCommentID());
//                        response.setStatus(true);
//
//                        om.sendObject(response);
//                        break;
//                    }
//                    case close: {
//                        s.close();
//                        dis.close();
//                        dout.close();
//                        return;
//                    }
//                }
            }
        } catch (
                Exception e) {
            System.out.println(e);
        }
    }
}