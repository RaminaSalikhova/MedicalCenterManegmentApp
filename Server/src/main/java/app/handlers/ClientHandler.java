package app.handlers;

import app.connection.ClientRequest;
import app.dao.SpecializationDao;
import app.entity.*;
import app.helpers.ObjectMessenger;
import app.models.AnswerTransferModels.GetAvailableTimeListAtm;
import app.models.AnswerTransferModels.GetDoctorListAtm;
import app.models.AnswerTransferModels.GetScheduleListAtm;
import app.models.AnswerTransferModels.UserAtm;
import app.models.DataTransferModels.*;
import app.connection.ServerResponse;
import app.services.*;
import org.apache.commons.lang3.time.DateUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ClientHandler extends Thread {
    protected final Socket s;
    DataInputStream dis;
    DataOutputStream dout;

    public ClientHandler(Socket s, DataInputStream dis,
                         DataOutputStream dout) {
        this.s = s;
        this.dis = dis;
        this.dout = dout;
    }

    public void run() {
        System.out.println("Server started");
        try {
            ObjectMessenger om = new ObjectMessenger(s);
            while (true) {

                ClientRequest req = (ClientRequest) om.receiveObject();
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

                switch (req.getType()) {
                    case login: {
                        ServerResponse response;

                        var loginDto = (LoginDto) req.getData();
                        AuthorizationHandler authorization = new AuthorizationHandler();
                        response = authorization.authorizeUser(loginDto);

                        om.sendObject(response);
                        break;
                    }
                    case register: {
                        ServerResponse response;

                        RegistrationDto registrationDto;
                        registrationDto = (RegistrationDto) req.getData();
                        AuthorizationHandler authorization = new AuthorizationHandler();
                        response = authorization.registerUser(registrationDto);

                        om.sendObject(response);
                        break;
                    }
                    case getUserData: {
                        ServerResponse response = new ServerResponse<UserAtm>();

                        var loginDto = (LoginDto) req.getData();
                        UserService userService = new UserService();
                        User user = userService.findByLogin(loginDto.getLogin());
                        UserAtm userAtm = new UserAtm();
                        userAtm.setId(user.getId());
                        userAtm.setRole(user.getRole());
                        userAtm.setFirstName(user.getFirstName());
                        userAtm.setLogin(user.getLogin());
                        userAtm.setDateOfBirth(user.getDateOfBirth());
                        userAtm.setPatronymic(user.getPatronymic());
                        userAtm.setPhoneNum(user.getPhoneNum());
                        userAtm.setLastName(user.getLastName());
                        response.setStatus(true);
                        response.setData(userAtm);

                        om.sendObject(response);
                        break;
                    }
                    case updateUserByPatient: {
                        ServerResponse response = new ServerResponse<String>();

                        UpdateUserByPatientDto updateUserByPatientDto;
                        updateUserByPatientDto = (UpdateUserByPatientDto) req.getData();
                        UserService userService = new UserService();
                        userService.updateUsernameAndPhone(updateUserByPatientDto);
                        response.setStatus(true);

                        om.sendObject(response);
                        break;
                    }
                    case getDoctorsList: {
                        ServerResponse response = new ServerResponse<GetDoctorListAtm>();

                        DoctorService doctorService = new DoctorService();
                        List<Doctor> doctorList = doctorService.findAll();
                        List<GetDoctorListAtm> doctorListAtmArrayList = new ArrayList<>();
                        for (Doctor doctor : doctorList) {
                            GetDoctorListAtm getDoctorListAtm = new GetDoctorListAtm();
                            getDoctorListAtm.setId(doctor.getId());
                            getDoctorListAtm.setFirst_name(doctor.getUserByUserId().getFirstName());
                            getDoctorListAtm.setLast_name(doctor.getUserByUserId().getLastName());
                            getDoctorListAtm.setPatronymic(doctor.getUserByUserId().getPatronymic());
                            getDoctorListAtm.setExperience(doctor.getExperience());
                            getDoctorListAtm.setRoomNumber(doctor.getScheduleByScheduleId().getRoomNumber());
                            getDoctorListAtm.setSpecialization(doctor.getSpecializationBySpecializationId().getName());
                            doctorListAtmArrayList.add(getDoctorListAtm);
                        }
                        response.setStatus(true);
                        response.setData(doctorListAtmArrayList);

                        om.sendObject(response);
                        break;
                    }
                    case getAvailableTime: {
                        ServerResponse response = new ServerResponse<GetAvailableTimeListAtm>();
                        List<GetAvailableTimeListAtm> getAvailableTimeListAtms = new ArrayList<>();

                        var getAvailableTimeDto = (GetAvailableTimeListDto) req.getData();
                        DoctorService doctorService = new DoctorService();
                        Doctor doctor = doctorService.findById(getAvailableTimeDto.getId());

                        ScheduleService scheduleService = new ScheduleService();
                        var searchedSchedule = scheduleService.findById(doctor.getScheduleId());

                        AppointmentService appointmentService = new AppointmentService();
                        List<Appointment> appointments = appointmentService.findAllByDoctorID(getAvailableTimeDto.getId());

                        Date doctorEndTime = searchedSchedule.getEndTime();
//                        if (appointments.size() == 0) {
                        Date startDate = new Date();
                        startDate = DateUtils.setSeconds(startDate, 0);
                        startDate = DateUtils.setMilliseconds(startDate, 0);

                        for (int i = 0; i < 7; i++) {
                            Date doctorStartTime = searchedSchedule.getStartTime();

                            if (startDate.getHours() > doctorStartTime.getHours()) {
                                startDate = DateUtils.setHours(startDate, startDate.getHours());
                            } else {
                                startDate = DateUtils.setHours(startDate, doctorStartTime.getHours());
                            }

                            if (startDate.getMinutes() < 30 && startDate.getMinutes() != 0) {
                                startDate = DateUtils.setMinutes(startDate, 30);
                            } else if (startDate.getMinutes() != 0) {
                                startDate = DateUtils.setMinutes(startDate, 0);
                                startDate = DateUtils.addHours(startDate, 1);
                            }

                            while (startDate.getHours() < doctorEndTime.getHours() ||
                                    (startDate.getHours() == doctorEndTime.getHours() &&
                                            startDate.getHours() < doctorEndTime.getHours())) { // если надо, то сюда можно добавить проверку на Субботу/Вс

                                boolean timeReserved = false;
                                for (var appointment : appointments) {
                                    var appTime = new Date(appointment.getDateTimeOfAppointment().getTime());
                                    if (appTime.getDate() == startDate.getDate() &&
                                            appTime.getHours() == startDate.getHours() &&
                                            appTime.getMinutes() == startDate.getMinutes()) {
                                        timeReserved = true;
                                        break;
                                    }
                                }

                                if (!timeReserved) {
                                    GetAvailableTimeListAtm getAvailableTimeListAtm = new GetAvailableTimeListAtm();

                                    getAvailableTimeListAtm.setDate(new SimpleDateFormat("dd.MM").format(startDate));
                                    getAvailableTimeListAtm.setTime(new SimpleDateFormat("HH:mm").format(startDate));

                                    getAvailableTimeListAtms.add(getAvailableTimeListAtm);
                                }

                                startDate = DateUtils.addMinutes(startDate, 30);
                            }
                            startDate = DateUtils.addDays(startDate, 1);
                            startDate = DateUtils.setHours(startDate, 0);
                        }
//                        }
//                        for (int i = 0; i < appointments.size(); i++) {
//                            Date date = searchedSchedule.getDate();
//                            Date start = searchedSchedule.getStartTime();
//                            Date end = searchedSchedule.getEndTime();
//
//                            Timestamp dateTimeOfAppointment = appointments.get(i).getDateTimeOfAppointment();
//                            Date dateTimeOfAppointment1 = dateTimeOfAppointment;
//                            Date timeOfAppointment = new Date(dateTimeOfAppointment.getTime());
//                            Date dateOfAppointment = new Date(dateTimeOfAppointment.getDate());
//
//                            if (date.equals(dateOfAppointment)) {
//                                while (!start.equals(end)) {
//                                    if (!start.equals(timeOfAppointment)) {
//                                        getAvailableTimeListAtm.setDate(date.toString());
//                                        getAvailableTimeListAtm.setTime(start.toString());
//                                        getAvailableTimeListAtms.add(getAvailableTimeListAtm);
//                                    } else {
//                                        start.toInstant().plus(Duration.ofMinutes(30));
//                                    }
//                                }
//                            }
//
//                        }

                        response.setStatus(true);
                        response.setData(getAvailableTimeListAtms);

                        om.sendObject(response);
                        break;
                    }
                    case setAppointment: {
                        ServerResponse response = new ServerResponse();

                        SetAppointmentDto setAppointmentDto = (SetAppointmentDto) req.getData();
                        DoctorService doctorService = new DoctorService();
                        var searchedDoctor = doctorService.findById(setAppointmentDto.getDoctorID());
                        PatientService patientService = new PatientService();
                        var searchedPatient = patientService.findPatientByUserID(setAppointmentDto.getUserID());

                        Appointment appointment = new Appointment();
                        appointment.setDoctorByDoctorId(searchedDoctor);
                        appointment.setPatientByPatientId(searchedPatient);
                        String ddate = setAppointmentDto.getDate();
                        String dtime = setAppointmentDto.getTime();
                        String dd = ddate + " " + dtime;
                        Date dateDate = new SimpleDateFormat("dd.MM hh:mm").parse(dd);
                        dateDate = DateUtils.setYears(dateDate, 2021);
                        Timestamp ts = new Timestamp(dateDate.getTime());
                        appointment.setDateTimeOfAppointment(ts);
                        appointment.setIsVisited((byte) 0);

                        AppointmentService appointmentService = new AppointmentService();
                        appointmentService.save(appointment);

                        response.setStatus(true);
                        om.sendObject(response);
                        break;
                    }
                    case getSchedule: {
                        ServerResponse response = new ServerResponse<GetScheduleListAtm>();

                        List<GetScheduleListAtm> getScheduleListAtms=new ArrayList<>();

                        DoctorService doctorService = new DoctorService();
                        List<Doctor> doctorList=doctorService.findAll();

                        for(Doctor doctor:doctorList){
                            GetScheduleListAtm scheduleListAtm=new GetScheduleListAtm();
                            UserService userService = new UserService();
                            var searchedUser = userService.findById(doctor.getUserId());

                            ScheduleService scheduleService = new ScheduleService();
                            Schedule schedule= scheduleService.findById(doctor.getScheduleId());

                            scheduleListAtm.setFirst_name(searchedUser.getFirstName());
                            scheduleListAtm.setLast_name(searchedUser.getLastName());
                            scheduleListAtm.setStartTime(schedule.getStartTime().toString());
                            scheduleListAtm.setEndTime(schedule.getEndTime().toString());
                            scheduleListAtm.setRoomNumber(schedule.getRoomNumber());
                            getScheduleListAtms.add(scheduleListAtm);
                        }

                        response.setStatus(true);
                        response.setData(getScheduleListAtms);

                        om.sendObject(response);
                        break;
                    }
                    case close: {
                        s.close();
                        dis.close();
                        dout.close();
                        return;
                    }
                }
            }
        } catch (
                Exception e) {
            System.out.println(e);
        }
    }
}