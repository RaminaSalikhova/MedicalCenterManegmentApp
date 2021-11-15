package app.handlers.implementations;

import app.entity.*;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetDoctorListForAdminAtm;
import app.models.DataTransferModels.GetDoctorListForAdminDto;
import app.services.*;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetDoctorsForAdmin extends RequestHandler<GetDoctorListForAdminDto, List<GetDoctorListForAdminAtm>> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getDoctorsForAdmin;
    }

    @Override
    protected List<GetDoctorListForAdminAtm> execute(GetDoctorListForAdminDto getDoctorListForAdminDto) throws Exception {
        List<GetDoctorListForAdminAtm> getDoctorListForAdminAtms = new ArrayList<>();

        DoctorService doctorService = new DoctorService();
        Optional<List<Doctor>> doctors = Optional.ofNullable(doctorService.findAll());

        UserService userService = new UserService();
        for (Doctor doctor : doctors.get()) {
            GetDoctorListForAdminAtm getDoctorListForAdminAtm = new GetDoctorListForAdminAtm();

            getDoctorListForAdminAtm.setUserID(doctor.getUserId());
            getDoctorListForAdminAtm.setDoctorID(doctor.getId());

            User user = userService.findById(doctor.getUserId());
            getDoctorListForAdminAtm.setFirstName(user.getFirstName());
            getDoctorListForAdminAtm.setLastName(user.getLastName());
            getDoctorListForAdminAtm.setPatronymic(user.getPatronymic());
            getDoctorListForAdminAtm.setPhoneNum(user.getPhoneNum());

            ScheduleService scheduleService = new ScheduleService();
            Optional<Long> scheduleID = Optional.ofNullable(doctor.getScheduleId());

            if (scheduleID.isPresent()) {
                Schedule schedule = scheduleService.findById(doctor.getScheduleId());
                Optional<Time> startTime=Optional.ofNullable(schedule.getStartTime());
                if (startTime.isPresent()) {
                    getDoctorListForAdminAtm.setFromWorkTime(schedule.getStartTime().toString());
                } else {
                    getDoctorListForAdminAtm.setFromWorkTime("Не установлено");
                }
                Optional<Time> endTime=Optional.ofNullable(schedule.getEndTime());
                if (endTime.isPresent()) {
                    getDoctorListForAdminAtm.setTillWorkTime(schedule.getEndTime().toString());
                } else {
                    getDoctorListForAdminAtm.setTillWorkTime("Не установлено");
                }
                Optional<String> room=Optional.ofNullable(schedule.getRoomNumber());
                if (room.isPresent()) {
                    getDoctorListForAdminAtm.setRoomNumber(schedule.getRoomNumber());
                } else {
                    getDoctorListForAdminAtm.setRoomNumber("Не установлено");
                }
            } else {
                getDoctorListForAdminAtm.setFromWorkTime("Не установлено");
                getDoctorListForAdminAtm.setTillWorkTime("Не установлено");
                getDoctorListForAdminAtm.setRoomNumber("Не установлено");
            }


            SpecializationService specializationService = new SpecializationService();
            Optional<Long> specializationID = Optional.ofNullable(doctor.getSpecializationId());
            if (specializationID.isPresent()) {
                Specialization specialization = specializationService.findById(doctor.getSpecializationId());
                getDoctorListForAdminAtm.setSpecialization(specialization.getName());
            } else {
                getDoctorListForAdminAtm.setSpecialization("Не установлено");
            }

            DistrictService districtService = new DistrictService();
            Optional<Long> districtID = Optional.ofNullable(doctor.getDistrictId());
            if (districtID.isPresent()) {
                District district = districtService.findById(doctor.getDistrictId());
                getDoctorListForAdminAtm.setDistrictName(district.getName());
            } else {
                getDoctorListForAdminAtm.setDistrictName("Не установлено");
            }

            Optional<Double> salary = Optional.ofNullable(doctor.getSalary());
            if (salary.isPresent()) {
                getDoctorListForAdminAtm.setSalary(salary.get().toString());
            } else {
                getDoctorListForAdminAtm.setSalary("Не установлено");
            }

            Optional<Double> experience = Optional.ofNullable(doctor.getExperience());
            if (experience.isPresent()) {
                getDoctorListForAdminAtm.setExperience(experience.get().toString());
            } else {
                getDoctorListForAdminAtm.setExperience("Не установлено");
            }

            getDoctorListForAdminAtms.add(getDoctorListForAdminAtm);
        }

        return getDoctorListForAdminAtms;
    }
}
